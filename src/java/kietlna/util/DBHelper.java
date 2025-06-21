/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kietlna.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBHelper {
    // JDBC API
    public static Connection makeConnection() throws ClassNotFoundException, SQLException  {
        //ClassNFExcep xuat hien khi
            // Driver chua add vao trong object
            // Add Driver sai
        //SQLException xuat hien khi
            // 1. Chuoi url viet sai
            // 2. TCP/IP Disable
            // 3. Sai password 
        // 1. Load Driver 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // 2. Tao connection String ket noi toi Container.
        // syntax: jdbc:sqlserver://ip:port;databaseName=....
        // neu mysql => jdbc:mysql://
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName = PRJSE1809";
        // 3. Mo ket noi su dung Driver Manager
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
}
