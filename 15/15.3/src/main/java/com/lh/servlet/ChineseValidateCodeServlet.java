//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.servlet;

import com.lh.model.RandomColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChineseValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChineseValidateCodeServlet() {
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        int width = 166;
        int height = 45;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Random random = new Random();
        Font mFont = new Font("宋体", 2, 26);
        g.setColor(RandomColor.getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(mFont);
        g.setColor(RandomColor.getRandomColor(180, 200));
        String sRand = "";
        String ctmp = "";
        for(int i = 0; i < 4; ++i) {
            String[] rBase = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
            int r1 = random.nextInt(3) + 11;
            String str_r1 = rBase[r1];
            int r2;
            if (r1 == 13) {
                r2 = random.nextInt(7);
            } else {
                r2 = random.nextInt(16);
            }

            String str_r2 = rBase[r2];
            int r3 = random.nextInt(6) + 10;
            String str_r3 = rBase[r3];
            int r4;
            if (r3 == 10) {
                r4 = random.nextInt(15) + 1;
            } else if (r3 == 15) {
                r4 = random.nextInt(15);
            } else {
                r4 = random.nextInt(16);
            }

            String str_r4 = rBase[r4];
            byte[] bytes = new byte[2];
            String str_r12 = str_r1 + str_r2;
            int tempLow = Integer.parseInt(str_r12, 16);
            bytes[0] = (byte)tempLow;
            String str_r34 = str_r3 + str_r4;
            int tempHigh = Integer.parseInt(str_r34, 16);
            bytes[1] = (byte)tempHigh;
            ctmp = new String(bytes);
            sRand = sRand + ctmp;
            Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
            g.setColor(color);
            Graphics2D g2d_word = (Graphics2D)g;
            AffineTransform trans = new AffineTransform();
            trans.rotate((double)random.nextInt(45) * 3.14D / 180.0D, (double)(26 * i + 8), 7.0D);
            float scaleSize = random.nextFloat() + 0.7F;
            if (scaleSize > 1.0F) {
                scaleSize = 1.0F;
            }

            trans.scale((double)scaleSize, (double)scaleSize);
            g2d_word.setTransform(trans);
            g.drawString(ctmp, width / 6 * i + 23, height / 3);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("randCheckCode", sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
}
