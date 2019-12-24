package com.yh.csx.bsf.core.filter;


import com.yh.csx.bsf.core.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: chejiangyi
 * @version: 2019-07-01 17:55
 * 上下文添加过滤器
 **/
public class WebContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        WebUtils.bindContext((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
        try {
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            WebUtils.clearContext();
        }
    }

    @Override
    public void destroy() {

    }
}
