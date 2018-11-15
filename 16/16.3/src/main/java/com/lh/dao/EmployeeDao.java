//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lh.dao;

import com.lh.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static EmployeeDao instance = null;

    public EmployeeDao() {
    }

    public static EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }

        return instance;
    }

    public int selectEmpCount() {
        Connection con = null;
        int rows = 0;

        try {
            con = DBCon.getConn();
            String sql = "select count(id) from tb_employee";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rows = rs.getInt(1);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var13) {
                var13.printStackTrace();
            }

        }

        return rows;
    }

    public List<Employee> selectEmp(int firstResult, int pageSize) {
        Connection con = null;
        ArrayList list = new ArrayList();

        try {
            con = DBCon.getConn();
            String sql = "select  * from tb_employee order by id limit ?,?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, firstResult);
            pstmt.setInt(2, pageSize);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("id"));
                emp.setEmpName(rs.getString("name"));
                emp.setEmpSex(rs.getString("sex"));
                emp.setEmpAge(rs.getInt("age"));
                emp.setDuty(rs.getString("duty"));
                emp.setDept(rs.getString("dept"));
                emp.setTelephoneNo(rs.getString("telephoneNo"));
                emp.setAddress(rs.getString("address"));
                list.add(emp);
            }

            rs.close();
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var16) {
                var16.printStackTrace();
            }

        }

        return list;
    }

    public List<Employee> selectEmpByCondition(int firstResult, int pageSize, String condition) {
        Connection con = null;
        ArrayList list = new ArrayList();

        try {
            con = DBCon.getConn();
            StringBuffer sb = new StringBuffer();
            sb.append("select * from tb_employee where 1=1 ");
            if (condition != null && !condition.equals("")) {
                if (condition.indexOf(",") != -1) {
                    String[] conditionStr = condition.trim().split(",");

                    for(int i = 0; i < conditionStr.length; ++i) {
                        sb.append(" and name like '%" + conditionStr[i] + "%'");
                    }
                } else {
                    sb.append(" and name like '%" + condition + "%'");
                }
            }

            sb.append("order by id limit ?,?");
            PreparedStatement pstmt = con.prepareStatement(sb.toString());
            pstmt.setInt(1, firstResult);
            pstmt.setInt(2, pageSize);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("id"));
                emp.setEmpName(rs.getString("name"));
                emp.setEmpSex(rs.getString("sex"));
                emp.setEmpAge(rs.getInt("age"));
                emp.setDuty(rs.getString("duty"));
                emp.setDept(rs.getString("dept"));
                emp.setTelephoneNo(rs.getString("telephoneNo"));
                emp.setAddress(rs.getString("address"));
                list.add(emp);
            }

            rs.close();
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var17) {
                var17.printStackTrace();
            }

        }

        return list;
    }

    public int selectEmpCountByCondition(String condition) {
        Connection con = null;

        try {
            con = DBCon.getConn();
            StringBuffer sb = new StringBuffer();
            sb.append("select count(id) from tb_employee where 1=1");
            if (condition != null && !condition.equals("")) {
                if (condition.indexOf(",") != -1) {
                    String[] conditionStr = condition.split(",");

                    for(int i = 0; i < conditionStr.length; ++i) {
                        sb.append(" and name like '%" + conditionStr[i] + "%'");
                    }
                } else {
                    sb.append(" and name like '%" + condition + "%'");
                }
            }

            PreparedStatement pstmt = con.prepareStatement(sb.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int var7 = rs.getInt(1);
                return var7;
            }

            rs.close();
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException var15) {
                var15.printStackTrace();
            }

        }

        return 0;
    }
}
