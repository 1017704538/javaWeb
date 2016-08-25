package com.hrl.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ZipAction extends ActionSupport {
    private File file = null;
    private String fileContentType = null;
    private String fileFileName = null;
    /**
     * 压缩文件
     * @return
     */
    public String enCompress() {
        String s = fileFileName.substring(0, fileFileName.indexOf("."));
        String path = ServletActionContext.getRequest().getRealPath("/")
        + "/file/" + fileFileName.substring(0, fileFileName.indexOf(".")) + ".zip";
        ZipKit.compress(file,fileFileName, path);
        return "downloadZip";
    }
    /**
     * 获取下载的输入流
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public InputStream getInputStream() throws FileNotFoundException, UnsupportedEncodingException{
        String s = fileFileName.substring(0, fileFileName.indexOf("."));
        String path = ServletActionContext.getRequest().getRealPath("/")
        + "/file/" + fileFileName.substring(0, fileFileName.indexOf(".")) + ".zip";
        fileFileName = new String(s.getBytes(),"ISO8859-1");
        return  new FileInputStream(new File(path));
    }
    /**
     * 解压文件
     * @return
     */
    public String deCompress() {
        String path = ServletActionContext.getRequest().getRealPath("/")
        + "/file/";
        ZipKit.unzip(file, path);
        return INPUT;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public String getFileContentType() {
        return fileContentType;
    }
    
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    
    public String getFileFileName() {
        return fileFileName;
    }
    
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    
}
