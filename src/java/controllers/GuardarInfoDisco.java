/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import dao.AlbumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Album;
import utils.Image;
import webscrapping.SearchDataAlbum;
import models.Message;
import config.MessagesUsers;
/**
 *
 * @author Pablo
 */
@WebServlet(name = "GuardarInfoDisco", urlPatterns = {"/GuardarInfoDisco"})
public class GuardarInfoDisco extends HttpServlet {

    private Gson gson = new Gson();
    private Album album;
    private SearchDataAlbum search;
    private Image image;
    private AlbumDAO albumDao;
    private Message message;

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
            boolean isSavedImage = false;
            boolean isSavedAlbum = false;
            String json;
            String fileName;
            String messageJsonString;
                    
            this.search = new SearchDataAlbum();
            this.image = new Image();
            
            json = request.getParameter("album");
            this.album = this.gson.fromJson(json, Album.class);
            
            fileName = search.createNameDiscArtist(this.album.getTitle(), this.album.getArtist()).toLowerCase();
            if (image.saveImage(album.getImageCover(), fileName)) {
                isSavedImage = true;
                this.album.setImageCover(fileName);
            }
            
            this.albumDao = new AlbumDAO();
            if(this.albumDao.set(this.album)) {
               isSavedAlbum = true;
            }
            
            if (isSavedAlbum && isSavedImage) {
                this.message = new Message(MessagesUsers.SUCCES_OK,MessagesUsers.OK_SAVED_IMAGE_ALBUM);
            } else {
                this.message = new Message(MessagesUsers.SUCCES_OK,MessagesUsers.KO_SAVED_IMAGE_ALBUM);
            }
            
            messageJsonString = this.gson.toJson(this.message);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(messageJsonString);
            out.flush();
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
