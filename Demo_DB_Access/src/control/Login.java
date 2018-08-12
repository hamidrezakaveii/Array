/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HAMIDREZA
 */
public class Login {

    Connection conn;
    Statement statement;
    ResultSet rs;

    JFrame frame = new JFrame("Login user");
    JLabel lb1 = new JLabel("Username: ");
    JLabel lb2 = new JLabel("Password: ");
    JTextField user = new JTextField(10);
    JTextField pass = new JTextField(10);
    JButton login = new JButton("Login");
    JPanel panel = new JPanel();

    public Login() {
        connect();
        frame();
    }

    public void connect() {

        try {

            //load driver for connect to database
            String driver = "jdbc:ucanaccess://E:\\OneDrive\\NetBeansProjects\\Demo_DB\\db1.mdb";
            //Class.forName(driver);

            //database url for connect
            //String db = "jdbc:odbc:db1";
            //con = DriverManager.getConnection(db);
            conn = DriverManager.getConnection(driver);
            statement = conn.createStatement();

//        } catch (ClassNotFoundException ex) {
//            System.out.println("Classforname Exception!!");
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Classforname Exception!!");
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("DriverManager Exception!!");
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void frame() {

        frame.setSize(600, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.add(lb1);
        panel.add(user);
        panel.add(lb2);
        panel.add(pass);
        panel.add(login);

        frame.setContentPane(panel);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String username = user.getText().trim();
                    String password = pass.getText().trim();

                    String sql = "select username, password from LoginTable where username = '" + username + "' and password = '" + password + "'";
                    rs = statement.executeQuery(sql);

                    int count = 0;
                    String duser = "";
                    String dpass = "";
                    while (rs.next()) {
                        duser = rs.getString("username");
                        dpass = rs.getString("password");
                        count = count + 1;
                    }

                    if (count == 1) {
                        JOptionPane.showMessageDialog(null, "User found, access granted! "+" Usrname: "+duser+" Password: "+dpass, "Login to system", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found!", "Login to system", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        new Login();

    }

}
