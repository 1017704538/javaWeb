package com.hrl.jr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public class ReportKit {
    /**
     * 编译jrxml文件为jasper文件
     * 
     * @param jrxmlFilePath
     * @param jasperFilePath
     * @throws JRException
     */
    public void compileAndPrintReport(HttpServletRequest request, JspWriter out) {
        JasperReport report = null;
        String jrxmlFilePath = request
                .getRealPath("/report/ajaxDownCount.jrxml");
        String jasperFilePath = request
                .getRealPath("/report/ajaxDownCount.jasper");
        String image = request
        .getRealPath("../report/image?image=");
        try {
            // 编译报表
            JasperCompileManager.compileReportToFile(jrxmlFilePath,
                    jasperFilePath);
            // 加载报表
            report = (JasperReport) JRLoader.loadObject(jasperFilePath);
            // 填充数据
            JasperPrint print = JasperFillManager.fillReport(report, null,
                    new DB().getConnection());
            // 输出html
            JRHtmlExporter exporter = new JRHtmlExporter();
            request.setAttribute(
                    ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
                    image);
            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,
                    "UTF-8");
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
}
