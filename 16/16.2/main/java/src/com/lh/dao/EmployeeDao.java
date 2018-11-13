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
            String sql = "select  * from tb_employee order by id limit " + firstResult + "," + pageSize + " ";
            PreparedStatement pstmt = con.prepareStatement(sql);
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
}
