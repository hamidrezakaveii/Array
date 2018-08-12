/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_db;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HAMIDREZA
 */
public class Demo_DB {

    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@144.217.163.57:1521:XE";
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        Class.forName(JDBC_DRIVER);
        
        con = DriverManager.getConnection(DB_URL, "oop", "ooppw");
        
        stm = con.createStatement();
        
        rs = stm.executeQuery("select * from client");
        
        while(rs.next()){
            int noClient = rs.getInt("NOCLIENT");
            String nomClient = rs.getString("NOMCLIENT");
            String noTelephone = rs.getString("NOTELEPHONE");
            
            System.out.println("No client est: "+ noClient + "Nom client est : " 
                    + nomClient + "No telephone: " + noTelephone );
        }
        
        
        rs.close();
        stm.close();
        con.close();
    }
    
}
