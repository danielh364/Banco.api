package com.fpmislata.banco.presentation.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterImplSecurity implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       String principal="permisos";
       
       if(principal.equals("permisos")){
        System.out.println("Start Regular");
         filterChain.doFilter(servletRequest, servletResponse);
           System.out.println("TRUE");
       }
       else{
            System.out.println("false");
       }
    }

    @Override
    public void destroy() {
    }

}
