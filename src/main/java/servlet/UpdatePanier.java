/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Panier;
import beans.ProduitPanier;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tom
 */
public class UpdatePanier extends HttpServlet {

    
    Panier panier;
    ProduitPanier produit;
    
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
        
        HttpSession session = request.getSession();
        
        panier = null;
        produit = null;
        
        if (session.getAttribute("userSession") != null)
            panier = ((Panier) session.getAttribute("panier"));
        
        
        /*System.out.println("___");
        System.out.println(request.getParameter("reference"));
        System.out.println(request.getParameter("categorie"));
        System.out.println(request.getParameter("nom"));
        System.out.println(request.getParameter("prix_unitaire"));
        System.out.println(request.getParameter("unites_en_stock"));
        System.out.println(request.getParameter("quantite"));
        System.out.println("___");*/
        
        String action = request.getParameter("action");
        if (action.equals("update")) updatePanier(session, request, response);
        else if (action.equals("remove")) removeFromPanier(session, request, response);
        
        response.sendRedirect("toPanier");
        
        
    }
    
    private void updatePanier(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        int reference = -1;
        String categorie = null;
        String nom = null;
        float prix_unitaire = -1f;

        int quantite = -1;
        int quantiteInitiale = -1;
        int unites_en_stock = -1;
        
        String refString = request.getParameter("reference");
        if (refString != null) reference = Integer.valueOf(refString);

        categorie =  request.getParameter("categorie");

        nom = request.getParameter("nom");

        String prixString = request.getParameter("prix_unitaire");
        if (prixString != null) prix_unitaire = Float.valueOf(prixString);

        String qteString = request.getParameter("quantite");
        if (qteString != null) quantite = Integer.valueOf(qteString);
        
        String qteInitString = request.getParameter("quantite_initiale");
        if (qteInitString != null) quantiteInitiale = Integer.valueOf(qteInitString);

        String stockString = request.getParameter("unites_en_stock");
        if (stockString != null) unites_en_stock = Integer.valueOf(stockString);
        
        
        unites_en_stock += quantiteInitiale - quantite;
        System.out.println("new:"+unites_en_stock);
        if (unites_en_stock < 0) request.setAttribute("errors","quantite en stock insuffisante");
        else {
            produit = new ProduitPanier(reference,categorie,nom,prix_unitaire,quantite,unites_en_stock,unites_en_stock+quantite);
            panier.addProduit(produit,true);
            session.setAttribute("panier",panier);
        }
    }
    
    public void removeFromPanier(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        int reference = -1;
        String refString = request.getParameter("reference");
        if (refString != null) reference = Integer.valueOf(refString);
        
        int quantite = -1;
        String qteString = request.getParameter("quantite");
        if (qteString != null) quantite = Integer.valueOf(qteString);
        
        float prix_unitaire = -1f;
        String prixString = request.getParameter("prix_unitaire");
        if (prixString != null) prix_unitaire = Float.valueOf(prixString);
        
        try {
            panier.removeProduit(reference,quantite,prix_unitaire);
            session.setAttribute("panier",panier);
        } catch (Exception e) {
            System.out.println("aled");
            session.setAttribute("errors", e.getMessage());
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
