/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.User;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author Tom
 */
public class UpdateClient extends HttpServlet {
    
    private static final String FIELD_SOCIETE = "societe";
    private static final String FIELD_CONTACT = "contact";
    private static final String FIELD_FONCTION = "fonction";
    private static final String FIELD_ADRESSE = "adresse";
    private static final String FIELD_VILLE = "ville";
    private static final String FIELD_CODE_POSTAL = "code_postal";
    private static final String FIELD_PAYS = "pays";
    private static final String FIELD_REGION = "region";
    private static final String FIELD_TELEPHONE = "telephone";
    private static final String FIELD_FAX = "password";

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
        
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        String code = null;
        String societe = null;
        String contact = null;
        String fonction = null;
        String adresse = null;
        String ville = null;
        String code_postal = null;
        String pays = null;
        String region = null;
        String telephone = null;
        String fax = null;

        String errors = new String();
        
        try {
            HttpSession session = request.getSession(false);
        
            if (session != null){

                code = ((User) session.getAttribute("userSession")).getPassword();

                if (code != null) {
                        contact = request.getParameter(FIELD_CONTACT);
                        if (contact == null || contact.trim().length() == 0 ) throw new Exception("Veuillez renseigner un nom.");
                        societe = request.getParameter(FIELD_SOCIETE);
                        if (societe == null || societe.trim().length() == 0 ) throw new Exception("Veuillez renseigner une société valide.");
                        fonction = request.getParameter(FIELD_FONCTION);
                        adresse = request.getParameter(FIELD_ADRESSE);
                        ville = request.getParameter(FIELD_VILLE);
                        code_postal = request.getParameter(FIELD_CODE_POSTAL);
                        pays = request.getParameter(FIELD_PAYS);
                        region = request.getParameter(FIELD_REGION);
                        telephone = request.getParameter(FIELD_TELEPHONE);
                        fax = request.getParameter(FIELD_FAX); 
                }
            }
            
            dao.updateClientInfos(code, societe, contact, fonction, adresse, ville, code_postal, pays, region, telephone, fax);
 
        } catch (Exception e) {
            errors = e.getMessage();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        if (errors.isEmpty()){
            response.sendRedirect("profil");
        }else{
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request,response);
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
