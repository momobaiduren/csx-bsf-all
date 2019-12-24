package com.yh.csx.bsf.core.util;

import lombok.val;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: chejiangyi
 * @version: 2019-07-01 17:56
 * Web 上下文操作
 **/
public class WebUtils {
    private static final ThreadLocal<WebContext> threadContext = new ThreadLocal<WebContext>();

    public static WebContext getContext() {
        WebContext webContext = threadContext.get();
        return webContext;
    }

    public static HttpServletRequest getRequest() {
        WebContext webContext= getContext();
        return webContext ==null?null:webContext.request;
    }

    public static HttpServletResponse getResponse() {
        WebContext webContext= getContext();
        return webContext ==null?null:webContext.response;
    }

    public static void bindContext(HttpServletRequest request,HttpServletResponse response) {
        threadContext.set(new WebContext(request,response));
    }

    public static void clearContext() {
        threadContext.remove();
    }

    public static class WebContext {
        private HttpServletRequest request;
        private HttpServletResponse response;

        public WebContext(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

    }

    public static String getBaseUrl(){
        if(!ContextUtils.isWeb())
        {    return "";}
        val webServer= ContextUtils.getConfigurableWebServerApplicationContext().getWebServer();
        if(webServer==null){
            return "";
        }
       return "http://"+NetworkUtils.getIpAddress()+":"+webServer.getPort()+
               PropertyUtils.getPropertyCache("server.servlet.context-path","");
    }
}
