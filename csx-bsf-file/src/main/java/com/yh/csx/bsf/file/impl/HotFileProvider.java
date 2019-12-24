package com.yh.csx.bsf.file.impl;

import com.yh.csx.bsf.core.serialize.JsonSerializer;
import com.yh.csx.bsf.file.FileException;
import com.yh.csx.bsf.file.config.HotFileProperties;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Huang Zhaoping
 */
public class HotFileProvider extends AbstractFileProvider {

    private String uploadUrl;
    private String loginToken;

    public HotFileProvider(HotFileProperties properties) {
        if (!StringUtils.hasText(properties.getUploadUrl())) {
            throw new FileException("HotFile缺少uploadUrl配置");
        }
        if (!StringUtils.hasText(properties.getLoginToken())) {
            throw new FileException("HotFile缺少loginToken配置");
        }
        this.uploadUrl = properties.getUploadUrl();
        if (this.uploadUrl.endsWith("/")) {
            this.uploadUrl = this.uploadUrl.substring(0, this.uploadUrl.length() - 1);
        }
        this.loginToken = properties.getLoginToken();
    }

    private Response getResponse(HttpURLConnection connection) throws Exception {
        if (connection.getResponseCode() != 200) {
            return new Response(connection.getResponseCode(), connection.getResponseMessage(), null);
        }
        StringBuilder builder = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8")) {
            char[] buffer = new char[1024];
            for (int length; (length = reader.read(buffer)) >= 0; ) {
                builder.append(buffer, 0, length);
            }
        }
        return new Response(new JsonSerializer().deserialize(builder.toString(), Map.class));
    }


    @Override
    public String upload(InputStream input, String path, String name) {
        HttpURLConnection connection = null;
        try {
            connection = getURLConnection("POST", uploadUrl);
            try (OutputStream output = connection.getOutputStream()) {
                byte[] buffer = new byte[1024];
                for (int length; (length = input.read(buffer)) >= 0; ) {
                    output.write(buffer, 0, length);
                }
                output.flush();
            }
            Response response = getResponse(connection);
            if (response.isOk()) {
                String filePath = URLEncoder.encode(response.getResult(), "UTF-8");
                if (!filePath.startsWith("/")) {
                    filePath = "/" + filePath;
                }
                return uploadUrl + filePath;
            } else {
                throw new FileException("HotFile上传文件失败：[" + response.getCode() + "]" + response.getMessage());
            }
        } catch (Exception e) {
            throw new FileException("HotFile上传文件失败", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection getURLConnection(String requestMethod, String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(30000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("login-token", loginToken);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        return connection;
    }

    @Override
    public boolean delete(String url) {
        HttpURLConnection connection = null;
        try {
            connection = getURLConnection("DELETE", url);
            return getResponse(connection).isOk();
        } catch (Exception e) {
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public String info() {
        return "hotfile:" + com.yh.csx.bsf.core.util.StringUtils.nullToEmpty(uploadUrl);
    }

    @Getter
    private class Response {
        String code, message, result;

        public Response(Map<String, Object> values) {
            this.code = String.valueOf(values.get("Code"));
            this.message = String.valueOf(values.get("Message"));
            this.result = String.valueOf(values.get("Result"));
        }

        public Response(int httpCode, String message, String result) {
            this.code = String.valueOf(httpCode);
            this.message = message;
            this.result = result;
        }

        boolean isOk() {
            return "20000".equals(code);
        }
    }

	@Override
	public String upload(String filePath, String path, String name) {
		File file=new File(filePath);
		try {
			return this.upload(new FileInputStream(file), name);
		} catch (FileNotFoundException e) {
			 throw new FileException("HotFile上传文件失败", e);			
		}
		
	}
}
