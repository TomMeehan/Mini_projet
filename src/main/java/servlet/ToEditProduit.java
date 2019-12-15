/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DataSourceFactory;
import model.Produit;

/**
 *
 * @author Tom
 */
public class ToEditProduit extends HttpServlet {
    
    private static final String FIELD_NOM = "nom";
    private static final String FIELD_FOURNISSEUR = "fournisseur";
    private static final String FIELD_CATEGORIE = "categorie";
    private static final String FIELD_QUANTITE_UNITE = "quantite_par_unite";
    private static final String FIELD_PRIX_UNITAIRE = "prix_unitaire";
    private static final String FIELD_UNITES_EN_STOCK = "unites_en_stock";
    private static final String FIELD_NIVEAU_REAPPRO = "niveau_de_reapprovi";
    
    DAO dao;
    
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
        String action = request.getParameter("action");
        
        if (action != null){
            switch (action){
                case "add":
                    request.getRequestDispatcher("WEB-INF/pages/admin/ajoutProduit.jsp").forward(request,response);
                    break;
                case "edit":
                    sendToEdit(request,response);
                    break;
                default:
                    break;
            }
                   
        }
    }
    
    private void sendToEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        dao = new DAO(DataSourceFactory.getDataSource());
        
        int reference=-1;
        
        try {
            String refString = request.getParameter("reference"); 
            if (refString != null) reference = Integer.valueOf(refString);
            
            Produit produit = dao.getProduit(reference);
            
            request.setAttribute("produit",produit); 
            
            request.getRequestDispatcher("WEB-INF/pages/admin/editProduit.jsp").forward(request,response);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("errors", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
