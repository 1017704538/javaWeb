//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package comluh.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTest {
    public WriterTest() {
    }

    public static void writeStrToFile() {
        try {
            FileWriter fw = new FileWriter("webContent/test.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write("你好！");
            bw.newLine();
            bw.write("明日科技有限公司！");
            bw.flush();
            bw.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) {
        writeStrToFile();
    }
}
