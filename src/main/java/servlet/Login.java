/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Panier;
import forms.LoginForm;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Client;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author Tom
 */
public class Login extends HttpServlet {
    
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASS = "admin";
    public static final String ATT_ADMIN_SESSION ="adminSession";
    
    public static final String ATT_USER = "user";
    public static final String ATT_FORM = "form";
    public static final String ATT_USER_SESSION = "userSession";
    public static final String ATT_PANIER = "panier";
    public static final String ATT_COMMANDES = "commandes";
    
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    

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
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        if (actionIs(request, "connect")) {
            
            String username = getFieldValue(request, FIELD_USERNAME);
            String password = getFieldValue(request, FIELD_PASSWORD);
            
            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASS))
                newAdminSession(request, response);
            else
                newSession(request, response, username, password);
        } else {
                showView("login.jsp", request, response);
        }
    }
    
    private boolean actionIs(HttpServletRequest request, String action) {
        return action.equals(request.getParameter("action"));
    }
    
    private void showView(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/pages/" + jsp).forward(request, response);
    }
    
    private void newAdminSession(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        
        request.setAttribute(ATT_USER, "admin");
        session.setAttribute(ATT_ADMIN_SESSION, "admin");
        
        response.sendRedirect("home");
    } 
    
    private void newSession(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ServletException, IOException {
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession session = request.getSession();
        
        LoginForm form = new LoginForm();
        Client user = form.connectUser(request,username,password);
        Panier panier = new Panier();
        
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, user);
        
        if (form.getErrors().isEmpty()){
            session.setAttribute(ATT_USER_SESSION, user);
            session.setAttribute(ATT_PANIER, panier);
            try{
               session.setAttribute(ATT_COMMANDES, dao.getCommandes(user.getCode())); 
            } catch (SQLException e){
                System.out.println(e.getMessage());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            
            response.sendRedirect("home");
        }
        else{
            session.invalidate();
            showView("login.jsp",request,response);
        }


    }
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }   
    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("username");
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
