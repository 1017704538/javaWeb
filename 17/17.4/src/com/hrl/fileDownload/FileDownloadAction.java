package com.hrl.fileDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport {
    private File myFile = null;// 文件数组
    private String myFileContentType = null; // 用户存储文件类型
    private String myFileFileName = null; // 用于储存文件名称（包括文件名称+扩展名称）
    private String userName = null;
    private String password = null;
    /**
     * 用户登陆
     * @return
     */
    public String login() {
        ServletActionContext.getRequest().getSession().setAttribute("userName",
                userName);
        return "inDownloadPage";
    }
    /**
     * 下载文件
     * @return
     * @throws Exception
     */
    public String download() throws Exception {
        String userName = (String) ServletActionContext.getRequest()
                .getSession().getAttribute("userName");
        if (userName.equals("张三")) {
            return "download";
        } else {
            return ERROR;
        }
    }
    /**
     * 为struts2文件下载提供输入流的方法
     * @return
     */
    public InputStream getInputStream() {
        InputStream in = null;
        try {
            this
                    .setMyFileFileName(new String("文本文件下载".getBytes(),
                            "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.setMyFileContentType("text/plain");
        String path = ServletActionContext.getRequest().getRealPath("/")
                + "//download//download.txt";
        try {
            in = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }
    
    public File getMyFile() {
        return myFile;
    }
    
    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }
    
    public String getMyFileContentType() {
        return myFileContentType;
    }
    
    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }
    
    public String getMyFileFileName() {
        return myFileFileName;
    }
    
    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
