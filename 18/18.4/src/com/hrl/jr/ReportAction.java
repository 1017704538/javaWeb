package com.hrl.jr;

import java.sql.Connection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport {
    private Connection conn = null;  //JDBC连接
    @Override
    public String execute() throws Exception {
        conn = DB.getConnection();
        return SUCCESS;
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
}
