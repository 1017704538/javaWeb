//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wgh.servlet;

import com.wgh.model.BidListForm;
import com.wgh.model.ResForm;
import com.wgh.tools.ConnDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class AuctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AuctionServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("getRes".equals(action)) {
            this.getRes(request, response);
        } else if ("getSingleRes".equals(action)) {
            this.getSingleRes(request, response);
        } else if ("getRemainTime".equals(action)) {
            this.getRemainTime(request, response);
        } else if ("getBidInfo".equals(action)) {
            this.getBidInfo(request, response);
        } else if ("getBidList".equals(action)) {
            this.getBidList(request, response);
        } else if ("logout".equals(action)) {
            this.logout(request, response);
        }

    }

    private void getRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int isEnd = Integer.parseInt(request.getParameter("state"));
        ConnDB conn = new ConnDB();
        String sql = "SELECT r.*,b.heightPrice FROM tb_res r LEFT JOIN (select resId ,max(bid) heightPrice FROM tb_biddinglist GROUP BY resId) b ON r.id=b.resId WHERE isEnd=" + isEnd;
        ResultSet rs = conn.executeQuery(sql);
        ArrayList list = new ArrayList();

        try {
            while(rs.next()) {
                ResForm res = new ResForm();
                res.setId(rs.getInt(1));
                res.setName(rs.getString(2));
                res.setStartPrice(rs.getFloat(3));
                res.setBreadth(rs.getInt(4));
                res.setStartTime(DateFormat.getDateTimeInstance().parse(rs.getString(5)));
                res.setEndTime(DateFormat.getDateTimeInstance().parse(rs.getString(6)));
                res.setHit(rs.getInt(7));
                res.setIsEnd(rs.getInt(8));
                res.setPicture("images/goods/" + rs.getString(9));
                res.setHeightPrice(rs.getString(10) == null ? 0.0F : Float.valueOf(rs.getString(10)));
                list.add(res);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        conn.close();
        request.setAttribute("resList", list);
        String state = isEnd == 0 ? "正在进行的拍卖" : "已经结束的拍卖";
        request.setAttribute("state", state);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    private void getSingleRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ConnDB conn = new ConnDB();
        String sql = "select t1.*,t2.username from (SELECT r.*,b.heightPrice,b.bidCount FROM tb_res r LEFT JOIN (select resId ,max(bid) heightPrice,count(*) bidCount FROM tb_biddinglist GROUP BY resId) b ON r.id=b.resId) t1 INNER JOIN (select r.id,u.username from tb_res r LEFT JOIN (select b.resId,u.userName from (select b.* from tb_biddinglist b inner join (select resId,max(bid) maxbid from tb_biddinglist group by resId) m ON b.resId=m.resId AND b.bid=m.maxbid) b INNER JOIN tb_user u ON b.bidder=u.id )u ON r.id=u.resId) t2 ON t1.id=t2.id WHERE t1.id=" + id;
        ResultSet rs = conn.executeQuery(sql);
        ResForm res = new ResForm();

        try {
            if (rs.next()) {
                res.setId(rs.getInt(1));
                res.setName(rs.getString(2));
                res.setStartPrice(rs.getFloat(3));
                res.setBreadth(rs.getInt(4));
                res.setStartTime(DateFormat.getDateTimeInstance().parse(rs.getString(5)));
                res.setEndTime(DateFormat.getDateTimeInstance().parse(rs.getString(6)));
                res.setHit(rs.getInt(7));
                res.setIsEnd(rs.getInt(8));
                res.setPicture("images/goods/" + rs.getString(9));
                res.setHeightPrice(rs.getString(10) == null ? 0.0F : Float.valueOf(rs.getString(10)));
                res.setBidCount(rs.getString(11) == null ? 0 : Integer.parseInt(rs.getString(11)));
                res.setBidder(rs.getString(12) == null ? "暂无" : rs.getString(12));
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        String sql_list = "SELECT b.*,u.userName FROM tb_biddinglist b INNER JOIN tb_user u ON b.bidder=u.id WHERE b.resId=" + id + " ORDER BY b.bid DESC";
        ResultSet rs_list = conn.executeQuery(sql_list);
        ArrayList list_bidlist = new ArrayList();

        try {
            boolean flag = false;

            while(rs_list.next()) {
                BidListForm bidlist = new BidListForm();
                if (!flag) {
                    bidlist.setState("<font style='color:red'>领先</font>");
                    flag = true;
                } else {
                    bidlist.setState("<font style='color:green'>出局</font>");
                }

                bidlist.setId(rs_list.getInt(1));
                bidlist.setBid(rs_list.getFloat(3));
                bidlist.setBidTime(DateFormat.getDateTimeInstance().parse(rs_list.getString(5)));
                bidlist.setBidder(rs_list.getString(6));
                list_bidlist.add(bidlist);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        String sql_updateHit = "UPDATE tb_res SET hit=hit+1 WHERE id=" + id;
        conn.executeUpdate(sql_updateHit);
        conn.close();
        request.setAttribute("resInfo", res);
        request.setAttribute("id", id);
        request.setAttribute("bidInfo", list_bidlist);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    private void getBidInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ConnDB conn = new ConnDB();
        String sql = "select t1.*,t2.username from (SELECT r.*,b.heightPrice,b.bidCount FROM tb_res r LEFT JOIN (select resId ,max(bid) heightPrice,count(*) bidCount FROM tb_biddinglist GROUP BY resId) b ON r.id=b.resId) t1 INNER JOIN (select r.id,u.username from tb_res r LEFT JOIN (select b.resId,u.userName from (select b.* from tb_biddinglist b inner join (select resId,max(bid) maxbid from tb_biddinglist group by resId) m ON b.resId=m.resId AND b.bid=m.maxbid) b INNER JOIN tb_user u ON b.bidder=u.id )u ON r.id=u.resId) t2 ON t1.id=t2.id WHERE t1.id=" + id;
        ResultSet rs = conn.executeQuery(sql);
        ResForm res = new ResForm();

        try {
            if (rs.next()) {
                res.setHeightPrice(rs.getString(10) == null ? 0.0F : Float.valueOf(rs.getString(10)));
                res.setBidCount(rs.getString(11) == null ? 0 : Integer.parseInt(rs.getString(11)));
                res.setBidder(rs.getString(12) == null ? "暂无" : rs.getString(12));
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(res.getHeightPrice() + "|" + res.getBidCount() + "|" + res.getBidder());
        conn.close();
    }

    private void getRemainTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ConnDB conn = new ConnDB();
        String sql = "SELECT *FROM tb_res WHERE id=" + id;
        ResultSet rs = conn.executeQuery(sql);
        Date endTime = null;

        try {
            if (rs.next()) {
                endTime = DateFormat.getDateTimeInstance().parse(rs.getString(6));
            }
        } catch (Exception var21) {
            var21.printStackTrace();
        }

        Date nowTime = new Date();
        long remainTime = endTime.getTime() - nowTime.getTime();
        long remainDay = remainTime / 86400000L;
        remainTime -= remainDay * 86400000L;
        long remainHour = remainTime / 3600000L;
        remainTime -= remainHour * 3600000L;
        long remainMinute = remainTime / 60000L;
        remainTime -= remainMinute * 60000L;
        long remainSecond = remainTime / 1000L;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (remainDay <= 0L && remainHour <= 0L && remainMinute <= 0L && remainSecond <= 0L) {
            out.println("end");
            String sql_updateState = "UPDATE tb_res SET isEnd=1 WHERE id=" + id;
            conn.executeUpdate(sql_updateState);
        } else {
            out.println("<font  style='color:red'>" + remainDay + "</font>天<font  style='color:red'>" + remainHour + "</font>时<font style='color: red'>" + remainMinute + "</font>分<font style='color: red'>" + remainSecond + "</font>秒");
        }

        conn.close();
    }

    private void getBidList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml");
        Document document = DocumentHelper.createDocument();
        Element bidList = DocumentHelper.createElement("bidList");
        document.setRootElement(bidList);
        int id = Integer.parseInt(request.getParameter("id"));
        ConnDB conn = new ConnDB();
        String sql = "SELECT b.*,u.userName FROM tb_biddinglist b INNER JOIN tb_user u ON b.bidder=u.id WHERE b.resId=" + id + " ORDER BY b.bid DESC";
        ResultSet rs = conn.executeQuery(sql);

        try {
            boolean flag = false;

            while(rs.next()) {
                Element bid = bidList.addElement("bid");
                Element idElement = bid.addElement("id");
                idElement.setText(String.valueOf(rs.getInt(1)));
                Element bidding = bid.addElement("bidding");
                bidding.setText(String.valueOf(rs.getFloat(3)));
                Element bidTime = bid.addElement("bidTime");
                SimpleDateFormat m = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                bidTime.setText(m.format(DateFormat.getDateTimeInstance().parse(rs.getString(5))));
                Element bidder = bid.addElement("bidder");
                bidder.setText(rs.getString(6));
                Element state = bid.addElement("state");
                if (!flag) {
                    state.setText("<font style='color:red'>领先</font>");
                    flag = true;
                } else {
                    state.setText("<font style='color:green'>出局</font>");
                }
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        conn.close();
        OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        XMLWriter writer = new XMLWriter(out, format);
        writer.write(document);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        out.println("<script>window.location.href='AuctionServlet?action=getSingleRes&id=" + id + "';</script>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            this.login(request, response);
        } else if ("bidding".equals(action)) {
            this.bid(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        ConnDB conn = new ConnDB();
        String sql = "SELECT * FROM tb_user WHERE username='" + username + "'";
        ResultSet rs = conn.executeQuery(sql);
        boolean flag = false;
        int userId = 0;

        try {
            if (rs.next()) {
                userId = rs.getInt(1);
                if (pwd.equals(rs.getString(3))) {
                    flag = true;
                }
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        conn.close();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if (flag) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("userId", userId);
            out.println("<script>window.location.href='AuctionServlet?action=getSingleRes&id=" + id + "';</script>");
        } else {
            out.println("<script>alert('您输入的用户名或密码错误！');history.back(-1);</script>");
        }

    }

    private void bid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        float bid = Float.valueOf(request.getParameter("bid"));
        ConnDB conn = new ConnDB();
        String sql = "SELECT * FROM tb_res WHERE id=" + id + " AND isEnd=0";
        ResultSet rs = conn.executeQuery(sql);
        int r = 0;
        boolean flag = false;

        String message;
        try {
            if (rs.next()) {
                message = "SELECT bid FROM tb_biddinglist WHERE resId=" + id + " ORDER BY bid DESC LIMIT 1";
                ResultSet rs_heightPrice = conn.executeQuery(message);
                if (rs_heightPrice.next()) {
                    if (rs_heightPrice.getFloat(1) < bid) {
                        flag = true;
                    }
                } else {
                    flag = true;
                }

                if (flag) {
                    String sql_ins = "INSERT INTO tb_biddinglist (resId,bid,bidder) VALUES(" + id + "," + bid + "," + userId + ")";
                    r = conn.executeUpdate(sql_ins);
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        message = "";
        if (r == 1) {
            message = "出价成功！";
        } else {
            message = "出价失败！";
        }

        conn.close();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script>alert('" + message + "');window.location.href='AuctionServlet?action=getSingleRes&id=" + id + "';</script>");
    }
}
