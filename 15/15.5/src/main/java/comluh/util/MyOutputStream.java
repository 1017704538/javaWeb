
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package comluh.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class MyOutputStream extends ServletOutputStream {
    ByteArrayOutputStream stream;

    public void write(int b) throws IOException {
        this.stream.write(b);
    }

    public MyOutputStream(ByteArrayOutputStream stream) {
        this.stream = stream;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
