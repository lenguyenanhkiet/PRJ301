/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package kietlna.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import kietlna.registration.RegistrationDAO;
import kietlna.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckAccountServlet", urlPatterns = {"/CheckAccountServlet"})
public class CheckAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 1. get toan bo cookies
        // So do 1: Doc cookies
        Cookie[] cookies = request.getCookies();
        String url = LOGIN_PAGE;
        try {
            if (cookies != null) {
                // 2. check exists cookies
                // 2.1 get username and password
                Cookie recentCookie = cookies[cookies.length - 1];
                String username = recentCookie.getName();
                String password = recentCookie.getValue();
                // 2.2 Controller call method cua Model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO result = dao.checkLogin(username, password);
                // 2.3 Process
                if (result != null) {
                    url = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFOR", result);
                }//user exist
            }//cookies have existed but no first time.
            
        } catch (SQLException ex) {
            log("SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("Class Not Found:" + ex.getMessage());
        } finally {
            // Chuyen trang dung gi cung duoc. Tai luu tru trong session
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
