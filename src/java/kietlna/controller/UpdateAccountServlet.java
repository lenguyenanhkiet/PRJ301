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
import java.util.HashMap;
import java.util.Map;
import kietlna.registration.RegistrationDAO;
import kietlna.registration.RegistrationUpdateError;

/**
 *
 * @author Admin
 */
@WebServlet(name="UpdateAccountServlet", urlPatterns={"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ERROR_PAGE="error.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastSearchValue = request.getParameter("lastSearchValue");
        boolean isAdmin = request.getParameter("checkAdmin") != null;
        
        Map<String,RegistrationUpdateError> errorsMap = new HashMap<>();
        RegistrationUpdateError error = new RegistrationUpdateError();
        boolean foundError = false;
        String url = ERROR_PAGE;
        try  {
            if(password.trim().length() < 6 || 
                    password.trim().length() > 20){
                foundError = true;
                error.setPasswordLengthError("Password is required typing from 6 to 20 characters ");
                errorsMap.put(username, error);
            }
            
            if(foundError){
                request.setAttribute("UPDATE_ACCOUNT", errorsMap);
                url = "DispatchServlet"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + lastSearchValue;
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, isAdmin);
                if (result) {
                    //refesh = goi lai chuc nang truoc do 1 lan nua.
                    // --> remind  --> add requestParameter dua tren so luong nhap lieu cua control chuc nang truoc do
                    url = "DispatchServlet"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + lastSearchValue;
                }
            }
            
        }catch(SQLException ex){
            log("SQL: " +ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("Class Not Found: " +ex.getMessage());            
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
