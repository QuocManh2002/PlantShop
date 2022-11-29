/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import DBAccess.AccountDao;
import DTO.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class registerServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtemail");
            String fullname = request.getParameter("txtfullname");
            String password = request.getParameter("txtpassword");
            String phone = request.getParameter("txtphone");
            Account acc = AccountDao.getAccountByEmail(email);
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3}$")) {
                request.setAttribute("ERROR", "Email invalid!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
            else if(phone.matches("[a-zA-Z]")){
                request.setAttribute("ERROR", "the phone is invalid");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }else if(acc!=null){
                request.setAttribute("ERROR", "the email is duplicated");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
            else{
            int status=1;
            int role=0;
            if(AccountDao.insertAccount(email,password,fullname,phone,status,role)!=0){
                  request.setAttribute("email_newAccount", email);
                  RequestDispatcher rd = request.getRequestDispatcher("sendOTP.jsp");
                  rd.forward(request, response);
            }else{
                response.sendRedirect("errorpage.html");
            }
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
        } catch (Exception ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
