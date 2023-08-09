package org.example.mvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    private final String DEFUALT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(DEFUALT_ENCODING);
        response.setCharacterEncoding(DEFUALT_ENCODING);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
