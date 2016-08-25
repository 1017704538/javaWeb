package com.hrl.image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ImageUploadAction extends ActionSupport {
    private String msg = null;// 用户储存返回客户端的信息
    private Image imageObj = null;
    private List<Image> images = null;
    
    /**
     * 執行文件上傳
     */
    public String execute() throws Exception {
        DB db = new DB();
        db.saveImage(imageObj);
        images = db.queryAllImage();
        writeImages(images);
        setMsg("文件上传成功");
        return SUCCESS;
    }
    
    private void writeImages(List<Image> images) throws IOException {
        String path = ServletActionContext.getRequest().getRealPath("/");
        path = path + "/temp/";
        for (Image image : images) {
            File file = new File(path + image.getImage_FileName());
            FileUtils.writeByteArrayToFile(file, image.getImageByte());
        }
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Image getImageObj() {
        return imageObj;
    }
    
    public void setImageObj(Image imageObj) {
        this.imageObj = imageObj;
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
}
