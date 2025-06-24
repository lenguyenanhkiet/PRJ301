/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kietlna.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kietlna.util.DBHelper;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) 
//        throws SQLException, ClassNotFoundException {
    public RegistrationDTO checkLogin(String username, String password) 
        throws SQLException, ClassNotFoundException {
//        boolean result = false;
        RegistrationDTO result = null;
        /* 1. Model connect Database.
         -- Khai bao bien va gan null
         -- Phai dong tat ca cac doi tuong bang moi cach
         -- Xu ly
         */
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2 model truy van du lieu tu DB 
                // 2.1 tao cau lenh SQL 
                // moi menh de cuacau lenh SQl phai viet tren 1 dong 
                // truoc khi xuong dong phai chen them 1 khoang trang neu khong 
                // cos loi syntaxFromNear
                // tat ca cac ten cot phai copy tu DB 
                // neu khong co loi OBJECT NOT FOUND 
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                // 2.2 to den cau lenh la qua trinh nap cau try van vao create statement Object 
                // check syntax va excute 
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // 2.3 execture query 
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        /* 2. Model truy van du lieu tu Database.
         2.1 Tao cau lenh SQL String.
         2.2 Tao ra Statement Object.
         2.3 Thuc thi (Execute Query)
         3. Model se get du lieu tu ResultSet, sau do
         model sets data toi properties cua model
         */

        return result;
    }

    private List<RegistrationDTO> accounts;
    //go get+ten bien viet in va ctrl space.

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastName(String searchValue)
        throws SQLException, ClassNotFoundException {
        boolean result = false;
        /* 1. Model connect Database.
         -- Khai bao bien va gan null
         -- Phai dong tat ca cac doi tuong bang moi cach
         -- Xu ly
         */
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2 model truy van du lieu tu DB 
                // 2.1 tao cau lenh SQL 
                // moi menh de cuacau lenh SQl phai viet tren 1 dong 
                // truoc khi xuong dong phai chen them 1 khoang trang neu khong 
                // cos loi syntaxFromNear
                // tat ca cac ten cot phai copy tu DB 
                // neu khong co loi OBJECT NOT FOUND 
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                // 2.2 to den cau lenh la qua trinh nap cau try van vao create statement Object 
                // check syntax va excute 
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 2.3 execture query 
                rs = stm.executeQuery();
                //3. Model se get du lieu tu ResultSet, sau do model sets data toi properties cua model
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //Model get du lieu tu ResultSet
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //set
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }// account is not available
                    this.accounts.add(dto);
                }//traverse each row in table
            }//conection is an available

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
         

    }
    
    public boolean deleteAccount(String username)
    throws SQLException, ClassNotFoundException{
        /* 1. Model connect Database.
         -- Khai bao bien va gan null
         -- Phai dong tat ca cac doi tuong bang moi cach
         -- Xu ly
         */
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // 2 model truy van du lieu tu DB 
                // 2.1 tao cau lenh SQL 
                // moi menh de cuacau lenh SQl phai viet tren 1 dong 
                // truoc khi xuong dong phai chen them 1 khoang trang neu khong 
                // cos loi syntaxFromNear
                // tat ca cac ten cot phai copy tu DB 
                // neu khong co loi OBJECT NOT FOUND 
                String sql = "DELETE "
                        + "FROM Registration "
                        + "WHERE username = ?";
                // 2.2 to den cau lenh la qua trinh nap cau try van vao create statement Object 
                // check syntax va excute 
                stm = con.prepareStatement(sql);
                // 2.3 execture query       
                stm.setString(1, username);
                int effectRows = stm.executeUpdate();
                if(effectRows > 0){
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        /* 2. Model truy van du lieu tu Database.
         2.1 Tao cau lenh SQL String.
         2.2 Tao ra Statement Object.
         2.3 Thuc thi (Execute Query)
         3. Model se get du lieu tu ResultSet, sau do
         model sets data toi properties cua model
         */

        return result;
    }
}
