package com.hrl.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import sun.awt.image.codec.JPEGImageEncoderImpl;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings( { "serial", "unused" })
public class GenerSmallImageAction extends ActionSupport {
    
    @SuppressWarnings("deprecation")
    public String execute() throws Exception {
        String path1 = ServletActionContext.getRequest().getRealPath("/")
                + "/image/Chrysanthemum.jpg";                                   //得到原图片的路径
        File src = new File(path1);  
        Image image = ImageIO.read(src);                                        //读取原图片
        int width = image.getWidth(null);                                       //取得原图片的宽度
        int height = image.getHeight(null);                                     //取得原图片的高度
        BufferedImage bufferedImage = new BufferedImage(width / 5, height / 5,
                BufferedImage.TYPE_INT_BGR);                                    //创建缩略图缓冲文件
        bufferedImage.getGraphics().drawImage(image, 0, 0, width / 5,
                height / 5, null);                                              //缩略图的高和宽都是原图片的1/5
        String path2 = ServletActionContext.getRequest().getRealPath("/")
                + "/image/Chrysanthemum_small.jpg";                             //缩略图路径
        FileOutputStream out = new FileOutputStream(path2); 
        JPEGImageEncoder encoder = new JPEGImageEncoderImpl(out);
        encoder.encode(bufferedImage);                                          //格式化缩略图并输出
        out.close(); 
        return SUCCESS;
    }
    
}
