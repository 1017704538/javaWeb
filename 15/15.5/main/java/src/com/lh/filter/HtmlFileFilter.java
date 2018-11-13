//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.filter;

import comluh.util.ResponseWrapper;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HtmlFileFilter implements Filter {
    public HtmlFileFilter() {
        ServletOutputStream stream = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean debug = true;
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String jspUrl = httpRequest.getRealPath("index.jsp");
        File jspFile = new File(jspUrl);
        File htmlFile = new File(jspFile.getParentFile(), "index.html");
        ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);
        chain.doFilter(request, responseWrapper);
        responseWrapper.getWriter().flush();
        Date htmlDate = null;
        if (htmlFile.exists()) {
            htmlDate = new Date(htmlFile.lastModified());
        } else {
            htmlFile.createNewFile();
        }

        if (debug || htmlDate == null || htmlDate.getDate() != (new Date()).getDate()) {
            FileOutputStream fileStream = new FileOutputStream(htmlFile);
            DataOutputStream dataStream = new DataOutputStream(fileStream);
            DateFormat format = DateFormat.getDateTimeInstance(0, 0);
            dataStream.writeChars("");
            dataStream.writeUTF("生成时间：" + format.format(new Date()));
            dataStream.writeUTF(responseWrapper.getContent());
            dataStream.close();
        }

        request.getRequestDispatcher("index.html").forward(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }
}
