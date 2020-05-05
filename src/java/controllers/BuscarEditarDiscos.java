/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "BuscarEditarDiscos", urlPatterns = {"/BuscarEditarDiscos"})
public class BuscarEditarDiscos extends HttpServlet {

    private String artista;
    private String disco;
    private String genero;
    private String estilo;
    private Map<String,String> searchParams;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            this.artista = request.getParameter("artista").trim();
            this.disco = request.getParameter("album").trim();
            this.estilo = request.getParameter("estilo");
            this.genero = request.getParameter("genero");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarEditarDiscos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarEditarDiscos at " + request.getContextPath() + "</h1>");
            out.println("<p>" + this.artista + "</p>");
            out.println("<p>" + this.disco + "</p>");
            out.println("<p>" + this.estilo + "</p>");
            out.println("<p>" + this.genero + "</p>");
            
            if (!this.artista.isEmpty() || !this.disco.isEmpty() || !this.estilo.isEmpty() || !this.genero.isEmpty()) {
                this.searchParams = new HashMap<String,String>();
                
                if (!this.artista.isEmpty()) {
                    this.searchParams.put("artista", this.artista);
                }
                
                if (!this.disco.isEmpty()) {
                    this.searchParams.put("disco", this.disco);
                }
                
                if (!this.estilo.isEmpty()) {
                    this.searchParams.put("estilo", this.estilo);
                }
                
                if (!this.genero.isEmpty()) {
                    this.searchParams.put("genero", this.genero);
                }
                
                for (Map.Entry<String, String> entry : this.searchParams.entrySet()) {
                    out.println("<p>Key = " + entry.getKey() + ", Value = " + entry.getValue() + "</p>");
                }
            }
            
            out.println("</body>");
            out.println("</html>");
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
