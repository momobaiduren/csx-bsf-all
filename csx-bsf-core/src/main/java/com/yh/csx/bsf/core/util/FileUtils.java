package com.yh.csx.bsf.core.util;

import com.yh.csx.bsf.core.base.BsfException;

import java.io.*;

/**
 * @author: chejiangyi
 * @version: 2019-09-25 15:35
 **/
public class FileUtils {
    public static boolean fileExsit(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }

    public static String getDirectoryPath(String path) {
        File file = new File(path);
        return file.getAbsolutePath();
    }

    public static String getDirectoryPath(Class cls) {
        File file = getJarFile(cls);
        if (file == null)
        {    return null;}
        if(!file.isDirectory())
        {    file=file.getParentFile();}
        return file.getAbsolutePath();
    }

    public static File getJarFile(Class cls)
    {
        String path = cls.getProtectionDomain().getCodeSource().getLocation().getFile();
        try
        {
            // 转换处理中文及空格
            path = java.net.URLDecoder.decode(path, "UTF-8");
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            return null;
        }
        return new File(path);
    }

    public static String getFilePath(String... paths) {
        StringBuffer sb = new StringBuffer();
        for (String path : paths) {
            sb.append(org.springframework.util.StringUtils.trimTrailingCharacter(path, File.separatorChar));
            sb.append(File.separator);
        }
        return org.springframework.util.StringUtils.trimTrailingCharacter(sb.toString(), File.separatorChar);
    }

    public static void createDirectory(String path) {
        File file = new File(path);
        if(!file.isDirectory())
        {   file=file.getParentFile();}
        //如果文件夹不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        } else {
        }
    }

    public static void appendAllText(String path, String contents) {
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File(path);
            try(FileWriter fw = new FileWriter(f, true))
            {
                try(PrintWriter pw = new PrintWriter(fw)) {
                    pw.println(contents);
                    pw.flush();
                    fw.flush();
                }
            }
        } catch (IOException exp) {
            throw new BsfException("追加文件异常", exp);
        }

    }

    public static void writeAllText(String path, String contents) {
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            } else {
            }
            //f.mkdirs();
            //不存在则创建
            f.createNewFile();
            try(BufferedWriter output = new BufferedWriter(new FileWriter(f))) {
                output.write(contents);
            }
        } catch (IOException exp) {
            throw new BsfException("写文件异常", exp);
        }
    }

    public static String readAllText(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                //获取文件长度
                Long filelength = f.length();
                byte[] filecontent = new byte[filelength.intValue()];
                try( FileInputStream in = new FileInputStream(f))
                {
                    in.read(filecontent);
                }
                //返回文件内容,默认编码
                return new String(filecontent);
            } else {
                throw new FileNotFoundException(path);
            }
        } catch (IOException exp) {
            throw new BsfException("读文件异常", exp);
        }
    }

    public static String lineSeparator() {
        return System.getProperty("line.separator");
    }
    
	/**
	 *  	根据文件路径获取文件名
	 *  
	 *  @param filePath 文件路径
	 *  @return filename 文件名
	 * */
	public static String getFileName(String filePath) {
		String path=filePath.replaceAll("\\\\", "/");
		return path.substring(path.lastIndexOf("/")+1,path.length());	
	}
}
