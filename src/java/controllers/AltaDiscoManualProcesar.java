/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AlbumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Album;
import models.Genre;
import models.Song;
import models.Style;
import config.MessagesUsers;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "AltaDiscoManualProcesar", urlPatterns = {"/AltaDiscoManualProcesar"})
public class AltaDiscoManualProcesar extends HttpServlet {

    private AlbumDAO albumDao;
    private ArrayList<Song> songs;
    private String artist;
    private String titulo;
    private String[] checkedStyles;
    private String[] checkedGenres;
    private int numSongs;
    private ArrayList<Genre> genres;
    private ArrayList<Style> styles;
    private Album album;
    private Style style;
    private Genre genre;
    private Song song;
    private String messageUser;
    private int year;
    
    
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
            
            this.songs = new ArrayList();
            this.genres = new ArrayList();
            this.styles = new ArrayList();
            
            this.albumDao = new AlbumDAO();
            this.album = new Album();
            
            this.artist = request.getParameter("artista");
            this.titulo = request.getParameter("titulo");
            this.checkedStyles = request.getParameterValues("check-estilos");
            this.checkedGenres = request.getParameterValues("check-generos");
            this.numSongs = Integer.parseInt(request.getParameter("num-songs"));
            this.year = Integer.parseInt(request.getParameter("yearAlbum"));
            
            this.addSongs(request);
            this.addStyles(this.checkedStyles);
            this.addGenres(this.checkedGenres);
            
            this.album.setArtist(this.artist);
            this.album.setTitle(this.titulo);
            this.album.setGenres(this.genres);
            this.album.setStyles(this.styles);
            this.album.setSongs(this.songs);
            this.album.setYear(this.year);
            
            if (this.albumDao.set(this.album)) {
                this.messageUser = MessagesUsers.OK_SAVED_IMAGE_ALBUM;
            } else {
                this.messageUser = MessagesUsers.KO_SAVED_IMAGE_ALBUM;
            }
            
            request.setAttribute("message",this.messageUser);
            request.setAttribute("id",Integer.toString(this.album.getId()));
            request.getRequestDispatcher("/altadiscomanual_addfile.jsp").forward(request, response);
        }
    }
    
    private void addSongs(HttpServletRequest request) {
        for (int i = 1;i <= this.numSongs; i++) {
            String song = request.getParameter("title-song-" + i);
            this.song = new Song();
            this.song.setTitle(song);
            this.songs.add(this.song);
        }
    }
    
    private void addGenres(String[] genres) {
        for (int i = 0; i < genres.length; i++) {
            this.genre = new Genre();
            this.genre.setName(genres[i]);
            this.genres.add(genre);
        }
    }
    
    private void addStyles(String[] styles) {
        for (int i = 0;i < styles.length; i++) {
            this.style = new Style();
            this.style.setName(styles[i]);
            this.styles.add(style);
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
