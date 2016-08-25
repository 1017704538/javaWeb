package com.hrl.image;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Image {
    private Integer image_id = null;
    private File image_ = null;// 文件
    private String image_ContentType = null; // 用户存储文件类型
    private String image_FileName = null; // 用于储存文件名称（包括文件名称+扩展名称）
    private byte[] imageByte = null;
    
    public Integer getImage_id() {
        return image_id;
    }
    
    public void setImage_id(Integer imageId) {
        image_id = imageId;
    }
    
    public File getImage_() {
        return image_;
    }
    
    public void setImage_(File image) {
        image_ = image;
    }
    
    public String getImage_ContentType() {
        return image_ContentType;
    }
    
    public void setImage_ContentType(String imageContentType) {
        image_ContentType = imageContentType;
    }
    
    public String getImage_FileName() {
        return image_FileName;
    }
    
    public void setImage_FileName(String imageFileName) {
        image_FileName = imageFileName;
    }
    
    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }
    
    public byte[] getImageByte() throws IOException {
        return imageByte == null ? FileUtils.readFileToByteArray(image_)
                : imageByte;
    }
}
