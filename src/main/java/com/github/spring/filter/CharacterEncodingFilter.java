package com.github.spring.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    public static final String DEFAULT_ENCODING = "UTF-8";
    private String encoding;
    private boolean forceEncoding;

    public CharacterEncodingFilter() {
        this(DEFAULT_ENCODING, false);
    }

    public CharacterEncodingFilter(String encoding, boolean forceEncoding) {
        this.encoding = encoding;
        this.forceEncoding = forceEncoding;
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (encoding != null || (forceEncoding && request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(getEncoding());
            if (forceEncoding) {
                response.setCharacterEncoding(getEncoding());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public boolean isForceEncoding() {
        return forceEncoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }
}
