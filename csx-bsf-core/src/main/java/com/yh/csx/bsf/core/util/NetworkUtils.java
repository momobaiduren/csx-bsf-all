package com.yh.csx.bsf.core.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author: chejiangyi
 * @version: 2019-08-09 19:56
 * 
 * 增加过滤IP
 * @author Robin.Wang
 * 2019-11-15 
 **/
public class NetworkUtils {
	
	public static final String IP_INCLUDE_REGEX_KEY="bsf.util.ip.include.regex";
	public static final String IP_EXCLUDE_REGEX_KEY="bsf.util.ip.exclude.regex";
	
	public static String getIpAddress() {
		String ipExclude= PropertyUtils.getPropertyCache(IP_EXCLUDE_REGEX_KEY,"");
    	if(org.springframework.util.StringUtils.hasText(ipExclude))
    	{
    		String regex=buildRegex(ipExclude.toString());
    		return getIpAddressExMatched(regex);
    	}
		
		String ipInclude= PropertyUtils.getPropertyCache(IP_INCLUDE_REGEX_KEY,"");
    	if(org.springframework.util.StringUtils.hasText(ipInclude))
    	{
    		String regex=buildRegex(ipInclude.toString());
    		return getIpAddressMatched(regex);
    	}
    	
       return getIpAddress0();
    }
	
    public static String getIpAddress0() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()|| netInterface.isPointToPoint()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }
    /**
     * 	获取指定网段地址
     * @param regex 10.0.18 网址前两个或前三个地址段
     * 
     * */
    public static String getIpAddressMatched(String regex) {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                        	String strIp=ip.getHostAddress();
                        	if(Pattern.matches(regex,strIp)) {//如果匹配网段则返回
                        		return strIp;
                        	}
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }    

    /**
     * 	获取指定网段地址，
     * @param regex 10.0.18 排除地址段，两个或前三个地址段
     * 
     * */
    public static String getIpAddressExMatched(String regex) {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                        	String strIp=ip.getHostAddress();                        	
                        	if(!Pattern.matches(regex, strIp)) {//如果不匹配匹配网段则返回
                        		return strIp;
                        	}
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    } 
    /**
     * 构建正在表达式
     * */
    private static String buildRegex(String source) {
		StringBuilder sb=new StringBuilder();
		String[] strSource=source.split(",");	
		for(int i=0;i<strSource.length;i++)
		{
			sb.append("|(^").append(strSource[i]).append(".*)");
		}
		String regex=sb.toString();
		if(!StringUtils.isEmpty(regex))
			return regex.substring(1);//去掉开头|号
		return "";
	}	
}
