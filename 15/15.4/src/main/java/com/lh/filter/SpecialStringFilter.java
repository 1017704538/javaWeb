
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.filter;

import com.lh.util.ResponseWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpecialStringFilter implements Filter {
    public SpecialStringFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        ServletOutputStream outStream = res.getOutputStream();
        ResponseWrapper wrapper = new ResponseWrapper(res);
        chain.doFilter(request, wrapper);
        String resStr = wrapper.toString();
        String newString = "";
        if (resStr.indexOf("bad") != -1) {
            newString = resStr.replace("bad", "**");
            outStream.write(newString.getBytes("UTF-8"));
        } else {
            outStream.write(resStr.getBytes("UTF-8"));
        }

        outStream.flush();
        outStream.close();
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
