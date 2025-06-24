/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package kietlna.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import kietlna.registration.RegistrationDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name="DeleteAccountServlet", urlPatterns={"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // 1. get all user's information
    // 2. Controller call method Model.
    // 2.1 New DAO object.
    // 2.2 Call method of DAO object.
    // 3. Process
    private final String ERROR_PAGE="error.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pk = request.getParameter("pk");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String url = ERROR_PAGE;
        try  {
        // 2.1 New DAO object.
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.deleteAccount(pk);
            if(result){
                //refesh = goi lai chuc nang truoc do 1 lan nua.
                // --> remind  --> add requestParameter dua tren so luong nhap lieu cua control chuc nang truoc do
                url="DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue="+lastSearchValue;
            }//delete successfully
        }catch(SQLException ex){
            log("SQL: " +ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("Class Not Found: " +ex.getMessage());
        }finally{
            response.sendRedirect(url);
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
