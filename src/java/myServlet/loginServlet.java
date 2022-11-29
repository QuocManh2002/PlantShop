/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import DBAccess.AccountDao;
import DBAccess.OrderDao;
import DTO.Account;
import DTO.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            String save = request.getParameter("savelogin");
            Account acc = null;
            
            try {
                if (email == null || email.equals("") || password == null || password.equals("")) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie acCookie : c) {
                            if (acCookie.getName().equals("selector")) {
                                token = acCookie.getValue();
                            }
                        }
                    }
                    if (!token.equals("")) {
                        
                        response.sendRedirect("personalPage.jsp");
                    } else {
                        response.sendRedirect("errorpage.html");
                    }
                } else {
                    acc = AccountDao.getAccount(email, password);
                    if (acc != null) {
                        if (acc.getRole() == 1) {
                            HttpSession session = request.getSession();
                            if(session!=null){
                                session.setAttribute("name", acc.getFullname());
                                session.setAttribute("email", email);
                                if (save != null) {
                                    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                                            + "lmnopqrstuvwxyz!@#$%&";
                                    Random rnd = new Random();
                                    StringBuilder sb = new StringBuilder(10);
                                    for (int i = 0; i < 10 ;i++) {
                                        sb.append(chars.charAt(rnd.nextInt(chars.length())));
                                    }
                                   
                                    String token = sb.toString();
                                    AccountDao.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2);
                                    response.addCookie(cookie);
                                }
                                session.setAttribute("acc", acc);
                                response.sendRedirect("AdminIndex.jsp");
                            }
                        } else {
                            HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("name", acc.getFullname());
                                session.setAttribute("email", email);
                                session.setAttribute("acc", acc);
                                if (save != null) {
                                    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                                            + "lmnopqrstuvwxyz!@#$%&";
                                    Random rnd = new Random();
                                    StringBuilder sb = new StringBuilder(10);
                                    for (int i = 0; i < 10 ;i++) {
                                        sb.append(chars.charAt(rnd.nextInt(chars.length())));
                                    }
                                   
                                    String token = sb.toString();
                                    AccountDao.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 2);
                                    response.addCookie(cookie);
                                }
                                ArrayList<Order> list = OrderDao.getOrders(email);
                                session.setAttribute("list", list);
                                response.sendRedirect("personalPage.jsp");
                            }
                        }
                    } else {
                        response.sendRedirect("invalid.html");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
