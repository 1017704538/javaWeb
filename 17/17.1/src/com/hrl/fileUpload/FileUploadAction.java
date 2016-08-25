package com.hrl.fileUpload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {
    private String msg = null;// 用户储存返回客户端的信息
    private File myFile[] = null;// 文件数组
    private String[] myFileContentType = null; // 用户存储文件类型
    private String[] myFileFileName = null; // 用于储存文件名称（包括文件名称+扩展名称）
    /**
     * 執行文件上傳
     */
    public String execute() throws Exception {
        String realPath = ServletActionContext.getRequest().getRealPath("/");
        for (int i = 0; i < myFile.length; i++) {
            File srcfile = myFile[i];
            if (srcfile != null) {
                FileUtil.writeFileTo(srcfile, realPath + "\\upload\\"
                        + reBuildFileName(myFileFileName[i]));
            }
        }
        setMsg("文件上传成功");
        return SUCCESS;
    }
    /**
     * 重新构建文件名称，文件名称为：文件名+日期+随机数+扩展名
     * @param fileName
     * @return
     */
    private String reBuildFileName(String fileName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyyMMddhhmmss");
        int index = fileName.indexOf(".");
        String name = fileName.substring(0, index);
        String exName = fileName.substring(index);
        String date = simpleDateFormat.format(new Date());
        return name + date + new Random().nextInt() + exName;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public File[] getMyFile() {
        return myFile;
    }
    
    public void setMyFile(File[] myFile) {
        this.myFile = myFile;
    }
    
    public String[] getMyFileContentType() {
        return myFileContentType;
    }
    
    public void setMyFileContentType(String[] myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    
    public String[] getMyFileFileName() {
        return myFileFileName;
    }
    
    public void setMyFileFileName(String[] myFileFileName) {
        this.myFileFileName = myFileFileName;
    }
    
}
