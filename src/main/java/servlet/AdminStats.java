/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
 * @author Gwen
 */
public class AdminStats extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
        List<DAO.Pair<String,Float>> data = new ArrayList();
        Properties result = new Properties();
        String typeStat = request.getParameter("typeStat");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        try {
            if (typeStat != null){
                switch (typeStat){
                    case "Categories":
                        data = dao.chiffAffCat(startDate, endDate); 
                        result.put("type","Categories");
                        request.setAttribute("data",data);
                        break;
                    case "Pays":
                        data = dao.chiffAffPays(startDate, endDate);
                        result.put("type","Pays");
                        request.setAttribute("data",data);
                        break;
                    case "Clients":
                        data = dao.chiffAffClient(startDate, endDate);
                        result.put("type","Clients");
                        request.setAttribute("data", data);
                }
                result.put("data",data);
            }
            
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("data", Collections.EMPTY_LIST);
            request.setAttribute("message", ex.getMessage());
        }
        try (PrintWriter out = response.getWriter()){
            response.setContentType("application/json;charset=UTF-8");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String gsonData = gson.toJson(result);
            out.println(gsonData);
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
