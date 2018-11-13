//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.servlet;

import com.lh.model.RandomColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValidateCodeServlet() {
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        int width = 60;
        int height = 20;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(RandomColor.getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", 0, 18));
        g.setColor(RandomColor.getRandomColor(160, 200));

        int i;
        for(i = 0; i < 130; ++i) {
            i = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(i, y, i + xl, y + yl);
        }

        String strCode = "";

        for(i = 0; i < 4; ++i) {
            String strNumber = String.valueOf(random.nextInt(10));
            strCode = strCode + strNumber;
            g.setColor(new Color(15 + random.nextInt(120), 15 + random.nextInt(120), 15 + random.nextInt(120)));
            g.drawString(strNumber, 13 * i + 6, 16);
        }

        request.getSession().setAttribute("Code", strCode);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
