/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DataSourceFactory;

/**
 *
 * @author Tom
 */
public class EditProduit extends HttpServlet {
    
    private static final String FIELD_NOM = "nom";
    private static final String FIELD_FOURNISSEUR = "fournisseur";
    private static final String FIELD_CATEGORIE = "categorie";
    private static final String FIELD_QUANTITE_UNITE = "quantite_par_unite";
    private static final String FIELD_PRIX_UNITAIRE = "prix_unitaire";
    private static final String FIELD_UNITES_EN_STOCK = "unites_en_stock";
    private static final String FIELD_NIVEAU_REAPPRO = "niveau_de_reapprovi";
    
    private DAO dao;
    
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
        
        dao = new DAO(DataSourceFactory.getDataSource());
        
        String action = request.getParameter("action");
        
        if (action != null){
            switch (action){
                case "add":
                    ajouterProduit(request,response);
                    break;
                default:
                    break;
            }
                   
        }
        
    }
    
    private void ajouterProduit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nom = null;
        int fournisseur = -1;
        int categorie = -1;
        String quantite_par_unite = null;
        float prix_unitaire = -1f;
        int unites_en_stock = -1;
        int niveau_de_reapprovi = -1;
        
        int indisponible = 0;
        
        try {
            
            nom = request.getParameter(FIELD_NOM);
            
            String fourniString = request.getParameter(FIELD_FOURNISSEUR);
            if (fourniString != null) fournisseur = Integer.valueOf(fourniString);
            
            String catString = request.getParameter(FIELD_CATEGORIE);
            if (catString != null) categorie = Integer.valueOf(catString);
            
            quantite_par_unite = request.getParameter(FIELD_QUANTITE_UNITE);
            
            String prixString = request.getParameter(FIELD_PRIX_UNITAIRE);
            if (prixString != null) prix_unitaire = Float.valueOf(prixString);
            
            String unitString = request.getParameter(FIELD_UNITES_EN_STOCK);
            if (unitString != null) unites_en_stock = Integer.valueOf(unitString);
            
            String reappString = request.getParameter(FIELD_NIVEAU_REAPPRO);
            if (reappString != null) niveau_de_reapprovi = Integer.valueOf(reappString);
            
            try {
                if (unites_en_stock == 0) indisponible = 1;
                dao.addProduit(nom, fournisseur, categorie, quantite_par_unite, prix_unitaire, unites_en_stock, 1, niveau_de_reapprovi, indisponible);
            } catch (SQLException e){
                System.out.println(e.getMessage());
                throw e;
            }
            
        }catch (Exception e){
            request.setAttribute("errors", e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        response.sendRedirect("home");
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
