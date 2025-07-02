/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package kietlna.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import kietlna.registration.RegistrationCreateError;
import kietlna.registration.RegistrationDAO;
import kietlna.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name="CreateAccountServlet", urlPatterns={"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        String url = ERROR_PAGE;
        
        // Verify all user'error.
        //Tao noi chua loi~
        RegistrationCreateError error = new RegistrationCreateError();
        boolean foundError = false;
        
        try {
            // Check loi username
            if(username.trim().length() < 6 || 
                    username.trim().length() > 12){
                foundError = true;
                error.setUsernameLengthError("Username is required typing from 6 to 12 characters ");
            }
            //Check loi password
            if(password.trim().length() < 6 || 
                    password.trim().length() > 20){
                foundError = true;
                error.setPasswordLengthError("Password is required typing from 6 to 20 characters ");
                
            //Kiem tra confirm match password hay khong.
            }else if(!confirmPassword.equals(password.trim())){
                foundError = true;
                error.setConfirmNotMatch("Confirm must match password.");
            }
            //Check loi fullName
            if(fullName.trim().length() < 2 || 
                    fullName.trim().length() > 40){
                foundError = true;
                error.setFullNameLengthError("Full name is required typing from 2 to 40 characters.");
            }
            
            if(foundError){
                request.setAttribute("CREATE_ACCOUNT", error);
            }else{ // no error
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(account);
                if(result){
                    url = LOGIN_PAGE;
                }
            }// no error   
        }catch(SQLException ex){
            String msg = ex.getMessage();
            log("SQL:" +msg);
            if(msg.contains("duplicate")){
                error.setUsernameIsExisted(username+"is EXISTED.");
                request.setAttribute("CREATE_ACCOUNT", error);
            }
        }catch(ClassNotFoundException ex){
            log("Class Not Found:");
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
