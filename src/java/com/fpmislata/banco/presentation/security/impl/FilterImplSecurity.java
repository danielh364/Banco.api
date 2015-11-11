package com.fpmislata.banco.presentation.security.impl;

import com.fpmislata.banco.persistence.security.Authorization;
import com.fpmislata.banco.presentation.security.WebSession;
import com.fpmislata.banco.presentation.security.WebSessionProvider;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FilterImplSecurity implements Filter {

    @Autowired
    WebSessionProvider webSessionProvider;

    @Autowired
    Authorization authorization;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpservletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpservletResponse = (HttpServletResponse) servletResponse;

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpservletRequest.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
//    

        WebSession webSession = webSessionProvider.getWebSession(httpservletRequest, httpservletResponse);

        if (webSession != null) {
            if (authorization.authorizedURL(webSession.getUsuario(), httpservletRequest.getRequestURI(), httpservletRequest.getMethod())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpservletResponse.setStatus(403);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
