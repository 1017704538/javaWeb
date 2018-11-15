//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wgh.servlet;

import com.wgh.tools.ConnDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class GoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodsServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("getGoods".equals(action)) {
            this.getGoods(request, response);
        }

    }

    private void getGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        Document document = DocumentHelper.createDocument();
        Element goodses = DocumentHelper.createElement("goodses");
        document.setRootElement(goodses);
        ConnDB conn = new ConnDB();
        String sql = "SELECT * FROM tb_goods ORDER BY refreshTime DESC";
        ResultSet rs = conn.executeQuery(sql);
        String goodsName = "";

        try {
            while(rs.next()) {
                Element goods = goodses.addElement("goods");
                Element name = goods.addElement("name");
                goodsName = rs.getString(2);
                name.setText(goodsName);
                Element price = goods.addElement("price");
                price.setText(String.valueOf(rs.getFloat(3)));
                Element picture = goods.addElement("picture");
                picture.setText("images/goods/" + rs.getString(4));
            }
        } catch (SQLException var13) {
            var13.printStackTrace();
        }

        conn.close();
        OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        XMLWriter writer = new XMLWriter(out, format);
        writer.write(document);
        writer.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
