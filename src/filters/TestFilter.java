package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public final class TestFilter implements Filter {

    private String attribute = null;
    private FilterConfig filterConfig = null;

    public void destroy() {
        this.attribute = null;
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        if (attribute != null)
            request.setAttribute(attribute, this);
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long stopTime = System.currentTimeMillis();
        filterConfig.getServletContext().log(this.toString() + ": " + (stopTime - startTime) + " milliseconds");
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.attribute = filterConfig.getInitParameter("key");
    }
}
