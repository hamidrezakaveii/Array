package control;

import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HAMIDREZA
 */
public class AppCtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stm = null;

        Class.forName("oracle.jdbc.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "hr", "inf5180");
        //stm = con.createStatement();

        afficherEmployee(con, 90);
        //deleteRegion(con, 12); --3
        //deleteCountry(con, String.valueOf(20)); --2
        //deleteLocation(con, 63); --1

        //stm.close();
        con.close();
    }

    private static void afficherEmployee(Connection con, int deptID) throws SQLException {
//        String sql = "select e.*,d.DEPARTMENT_ID, j.JOB_TITLE from EMPLOYEES e join DEPARTMENTS d on(e.DEPARTMENT_ID = d.DEPARTMENT_ID) \n"
//                + "join JOBS j on(e.JOB_ID=j.JOB_ID) where d.DEPARTMENT_ID=?";
        String sql = "select * from employees natural join departments where department_id = ?";
        PreparedStatement stmp = con.prepareStatement(sql);
        stmp.setInt(1, deptID);

        ResultSet rs = stmp.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getObject(1) + " " + rs.getObject(2)
                    + " " + rs.getObject(12) + " " + rs.getObject(13));

        }

        stmp.close();
        rs.close();
    }

    private static void deleteRegion(Connection con, int region_id) throws SQLException {
        String sql = "delete from regions where region_id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, region_id);
        pstm.executeQuery();
        //pstm.close();
    }

    private static void deleteCountry(Connection con, String country_id) throws SQLException {
        String sql = "delete from COUNTRIES where country_id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, country_id);
        pstm.executeQuery();
        pstm.close();
    }

    private static void deleteLocation(Connection con, int location_id) throws SQLException {
        String sql = "delete from LOCATIONS where location_id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, location_id);
        pstm.executeQuery();
        pstm.close();
    }

}
