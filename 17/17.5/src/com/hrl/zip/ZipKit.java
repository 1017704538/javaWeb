package com.hrl.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipKit {
    /**
     * 压缩文件
     * @param file
     * @param fileName
     * @param outPath
     * @return
     */
    public static boolean compress(File file,String fileName, String outPath) {
        InputStream in = null;                                  // 创建输入流对象
        try {
            in = new FileInputStream(file);
            FileOutputStream fileOut = null;
            fileOut = new FileOutputStream(outPath);
            ZipOutputStream zipOut = new ZipOutputStream(fileOut); // 创建ZIP压缩格式的输出流对象
            ZipEntry zipEntry = new ZipEntry(new String(fileName.getBytes(),"UTF-8")); // 创建压缩文件进入点对象
            zipOut.putNextEntry(zipEntry);
            byte b[] = new byte[512];
            int len = 0;
            while ((len = in.read(b)) != -1) { // 循环读取文件
                zipOut.write(b, 0, len); // 向流中写数据
            }
            zipOut.closeEntry();
            zipOut.close(); // 关闭流
            in.close();
            fileOut.close();
            return true; // 返回true
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return false;
        }
    }
    /**
     * 解压文件
     * @param file
     * @param outputDirectory
     * @return
     */
    public static boolean unzip(File file, String outputDirectory) {
        ZipInputStream in;
        try {
            in = new ZipInputStream(new FileInputStream(file)); // 创建FileInputStream对象
            ZipEntry z;
            while ((z = in.getNextEntry()) != null) { // 获取目录进入点
                if (z.isDirectory()) { // 如果指定的目录进入点是一个路径
                    String name = z.getName(); // 获取条目名称
                    name = name.substring(0, name.length() - 1); // 对条目名称进行字符串截取
                    File f = new File(outputDirectory + File.separator + name); // 根据条目名称创建文件对象
                    f.mkdir(); // 创建抽象路径名指定的目录
                } else {
                    String str = z.getName(); // 获取条目名称
                    str = new String(str.getBytes("UTF-8"));
                    int index = str.lastIndexOf("\\"); // 获取条目名称中符号“\\”的索引
                    File f = new File(outputDirectory + "\\"
                            + str.substring(index + 1, str.length()));
                    // 创建包含解压后文件对象
                    f.createNewFile(); // 新建文件
                    FileOutputStream out = new FileOutputStream(f); // 创建FileOutputStream类对象
                    int b;
                    while ((b = in.read()) != -1) { // 循环读取文件
                        out.write(b); // 向文件中写数据
                    }
                    out.close(); // 关闭流
                }
            }
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
