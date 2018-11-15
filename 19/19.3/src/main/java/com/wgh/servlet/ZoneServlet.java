//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wgh.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ZoneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ZoneServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("getProvince".equals(action)) {
            this.getProvince(request, response);
        } else if ("getCity".equals(action)) {
            this.getCity(request, response);
        } else if ("getArea".equals(action)) {
            this.getArea(request, response);
        }

    }

    public void getProvince(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String fileURL = request.getRealPath("/xml/zone.xml");
        File file = new File(fileURL);
        Document document = null;
        Element country = null;
        String result = "";
        if (file.exists()) {
            SAXReader reader = new SAXReader();

            try {
                document = reader.read(new File(fileURL));
                country = document.getRootElement();
                List<Element> provinceList = country.elements("province");
                Element provinceElement = null;

                for(int i = 0; i < provinceList.size(); ++i) {
                    provinceElement = (Element)provinceList.get(i);
                    result = result + provinceElement.attributeValue("name") + ",";
                }

                result = result.substring(0, result.length() - 1);
            } catch (DocumentException var12) {
                var12.printStackTrace();
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    public void getCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String fileURL = request.getRealPath("/xml/zone.xml");
        File file = new File(fileURL);
        Document document = null;
        String result = "";
        if (file.exists()) {
            SAXReader reader = new SAXReader();

            try {
                document = reader.read(new File(fileURL));
                Element country = document.getRootElement();
                String selProvince = request.getParameter("parProvince");
                selProvince = new String(selProvince.getBytes("ISO-8859-1"), "GBK");
                Element item = (Element)country.selectSingleNode("/country/province[@name='" + selProvince + "']");
                List<Element> cityList = item.elements("city");
                Element cityElement = null;

                for(int i = 0; i < cityList.size(); ++i) {
                    cityElement = (Element)cityList.get(i);
                    result = result + cityElement.attributeValue("name") + ",";
                }

                result = result.substring(0, result.length() - 1);
            } catch (DocumentException var14) {
                var14.printStackTrace();
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    public void getArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String fileURL = request.getRealPath("/xml/zone.xml");
        File file = new File(fileURL);
        Document document = null;
        String result = "";
        if (file.exists()) {
            SAXReader reader = new SAXReader();

            try {
                document = reader.read(new File(fileURL));
                Element country = document.getRootElement();
                String selProvince = request.getParameter("parProvince");
                String selCity = request.getParameter("parCity");
                selProvince = new String(selProvince.getBytes("ISO-8859-1"), "GBK");
                selCity = new String(selCity.getBytes("ISO-8859-1"), "GBK");
                Element item = (Element)country.selectSingleNode("/country/province[@name='" + selProvince + "']");
                List<Element> cityList = item.elements("city");
                Element itemArea = (Element)item.selectSingleNode("city[@name='" + selCity + "']");
                result = itemArea.attributeValue("area");
            } catch (DocumentException var14) {
                var14.printStackTrace();
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
