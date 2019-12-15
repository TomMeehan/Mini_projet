/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Panier;
import beans.ProduitPanier;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ValiderPanier extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        Client client = null;
        Panier panier = null;
        String pass = null;
        

        try {
            if(session.getAttribute("userSession") != null)
                pass = ((Client)session.getAttribute("userSession")).getCode();

           client = dao.getClientInfos(pass);

           if(session.getAttribute("panier") != null)
               panier = ((Panier)session.getAttribute("panier"));

           ArrayList<ProduitPanier> produits = panier.getProduits();

           int[] produitsID = new int[panier.getNbProduits()];
           int[] quantites = new int[panier.getNbProduits()];
           int i = 0;

           for (ProduitPanier p : produits){
               produitsID[i] = p.getReference();
               quantites[i] = p.getQuantite();
               i++;
           }
           try {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(date);
                System.out.println("ajout");
                dao.addCommande(client.getCode(), formattedDate, formattedDate, panier.getPrixTotal()/10, client.getContact(), client.getAdresse(), client.getVille(), client.getRegion(), client.getCode_postal(), client.getPays(), 0, produitsID, quantites);
                panier.viderPanier();
                session.setAttribute("panier",panier);
                session.setAttribute("commandes", dao.getCommandes(client.getCode()));
           } catch (Exception ex){
               System.out.println(ex.getMessage());
               throw ex;
           }
       } catch (Exception ex) { 
           System.out.println(ex.getMessage());
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       }      

       response.sendRedirect("commandes");
       
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
