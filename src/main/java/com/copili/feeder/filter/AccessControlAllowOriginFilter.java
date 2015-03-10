package com.copili.feeder.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessControlAllowOriginFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger( AccessControlAllowOriginFilter.class );

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        log.debug( "CORS Filter : Inicio" );
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader( "Access-Control-Allow-Origin", "*" );
        httpServletResponse.setHeader( "Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT" );
        httpServletResponse.setHeader( "Access-Control-Max-Age", "3600" );
        httpServletResponse.setHeader( "Access-Control-Allow-Headers", "x-requested-with, accept, content-type, ApiKey" );
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if( "OPTIONS".equals( request.getMethod() ) ) {
            log.debug( "Se ha detectado una peticion OPTIONS" );
            log.debug( "CORS Filter : Fin" );
            return;
        }
        filterChain.doFilter( servletRequest, servletResponse );
        log.debug( "CORS Filter : Fin" );
    }

    @Override
    public void destroy() {
    }
}
