package com.yh.csx.bsf.message.sms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.csx.bsf.core.base.BsfException;
import com.yh.csx.bsf.core.util.ReflectionUtils;
import com.yh.csx.bsf.core.util.StringUtils;
import com.yh.csx.bsf.message.base.MessageException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.Assert;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Zhaoping
 * 
 * 
 */
public class SmsProvider {
    private String apiUrl;
    private String apiUrlBak;
    private String authorization;
    private Map<String, String> basicBody;

    private ObjectMapper objectMapper = new ObjectMapper();

    public SmsProvider(SmsProperties smsProperties) {
        apiUrl = StringUtils.isEmpty(smsProperties.getSmsUrl())? ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smsurl",""):smsProperties.getSmsUrl();
        apiUrlBak= StringUtils.isEmpty(smsProperties.getSmsUrlBak())?ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smsurlbak",""):smsProperties.getSmsUrlBak();
        authorization = "Basic " + Base64.getEncoder().encodeToString(((StringUtils.isEmpty(smsProperties.getSmsUser())?ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smsuser",""):smsProperties.getSmsUser()) + ":" + (StringUtils.isEmpty(smsProperties.getSmsPassword())?ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smspassword",""):smsProperties.getSmsPassword())).getBytes());
        basicBody = new HashMap<>();
        basicBody.put("SMS_SERVER", StringUtils.isEmpty(smsProperties.getSmsServer())? ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smsserver",""):smsProperties.getSmsServer());
        basicBody.put("USER_ID", StringUtils.isEmpty(smsProperties.getSmsUser())? ReflectionUtils.tryGetStaticFieldValue("com.yh.csx.bsf.core.base.BsfBaseConfig","smsuser",""):smsProperties.getSmsUser());
    }
    /**
     * 新增接口
     * 发送短信
     * */
    public void sendText(String phone,String content,String systemCode)
    {
    	Assert.hasText(phone, "手机号码不能为空");
    	Assert.hasText(content, "短信内容不能为空");
    	Assert.hasText(systemCode, "系统编码不能为空");
    	
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("content", content);
    	map.put("phone", phone);
    	map.put("systemCode", systemCode);    	
    	   try {
               HttpUriRequest request = createPostRequest(apiUrlBak,map);
               try (CloseableHttpClient client = getHttpClient(apiUrlBak)) {
                   try (CloseableHttpResponse response = client.execute(request)) {
                       StatusLine status = response.getStatusLine();
                       if (status.getStatusCode() != 200) {
                           throw new BsfException("请求短信接口失败: " + status.getStatusCode() + ", Reason: " + status.getReasonPhrase());
                       }
                   }
               }
           }
           catch (Exception exp)
           {
               throw new MessageException(exp);
           }
    }
    /***
     * 
     * Recommand to use sendText(String phone,String content,String systemCode) method
     * */
    @Deprecated
    public  void sendText(String phone, String content)  {
        sendRequest("/sms/v1/yhsms/industrial", phone, content);
    }

    public  void sendVoiceCode(String phone, String code)  {
        if (code == null || !code.matches("\\d{4,6}")) {
            throw new MessageException("语音验证码内容只能是4~6位数字");
        }
        sendRequest("/sms/v1/yhsms/voice", phone, code);
    }

    private  void sendRequest(String servicePath, String phone, String content) {
        try {
            HttpUriRequest request = createRequest(apiUrl, servicePath, phone, content);
            try (CloseableHttpClient client = getHttpClient(apiUrl)) {
                try (CloseableHttpResponse response = client.execute(request)) {
                    StatusLine status = response.getStatusLine();
                    if (status.getStatusCode() != 200) {
                        throw new BsfException("请求短信接口失败: " + status.getStatusCode() + ", Reason: " + status.getReasonPhrase());
                    }
                }
            }
        }
        catch (Exception exp)
        {
            throw new MessageException(exp);
        }
    }

    private  HttpUriRequest createRequest(String basicUrl, String serviceUrl, String phone, String content) {
        if (basicBody == null || basicUrl == null || basicUrl.length() == 0) {throw new IllegalStateException("短信服务未初始化");}
        Map<String, String> body = new HashMap<>(basicBody);
        body.put("PHONENUMBER", phone);
        body.put("CONTENT", content);
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new BsfException("转换JSON失败");
        }
        return RequestBuilder.create("POST")
                .setUri(basicUrl + serviceUrl)
                .addHeader("Authorization", authorization)
                .setEntity(new StringEntity(requestBody, ContentType.create("application/json", "UTF-8")))
                .build();
    }

    
    private CloseableHttpClient getHttpClient(String url) throws Exception {
        String protocol = url.length() > 4 ? url.substring(0, 5).toLowerCase() : "";
        if ("https".equalsIgnoreCase(protocol)) {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            }, null);
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(ctx, new String[]{"TLSv1"}, null,
                    (host, session) -> true);
            return HttpClients.custom().setSSLSocketFactory(factory).build();
        } else {
            return HttpClients.createDefault();
        }
    }
    
    /**
     * 
     * 新增短信接口
     * */
    private  HttpUriRequest createPostRequest(String serviceUrl, Map<String,String> data) {
        if (basicBody == null || serviceUrl == null || serviceUrl.length() == 0) {throw new IllegalStateException("短信服务未初始化");}
        Map<String, String> body = new HashMap<>(basicBody);
        body.putAll(data);
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new BsfException("转换JSON失败");
        }
        return RequestBuilder.create("POST")
                .setUri(serviceUrl)
                .addHeader("Authorization", authorization)
                .setEntity(new StringEntity(requestBody, ContentType.create("application/json", "UTF-8")))
                .build();
    }    

}
