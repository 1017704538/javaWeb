//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package comluh.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private MyOutputStream stream;
    private ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    private PrintWriter out;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.stream = new MyOutputStream(this.byteStream);
        this.out = new PrintWriter(this.byteStream);
    }

    public PrintWriter getWriter() throws IOException {
        return this.out;
    }

    public String getContent() throws UnsupportedEncodingException {
        return this.byteStream.toString();
    }
}
