/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.interfaceGenre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Album;
import models.Genre;
import models.Style;

/**
 *
 * @author Pablo
 */
public class GenreDAO implements interfaceGenre {

    @Override
    public boolean set(Genre genre) {
        boolean isSet = false;
        
        Connection connection = null;
        Statement statement = null;
        
        String sql = "INSERT INTO genres (id,name,active) VALUES (NULL,'" + genre.getName() + "',1)";
        
        try {
           connection = Conexion.conectar();
           statement = connection.createStatement();
           int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
           if (affectedRows == 0) {
               throw new SQLException("Fallo al insertar genero");
           } 
           
           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                   genre.setId(generatedKeys.getInt(1));
                   isSet = true;
               } else {
                    throw new SQLException("Fallo al insertar genero, no se obtuvo el ID generado.");
               }
           }
           
           statement.close();
           connection.close();
        } catch (SQLException ex) {
           Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSet;
    }

    @Override
    public List<Genre> get() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resulset = null;

        List<Genre> listGenres = new ArrayList<>();

        String sql = "SELECT * FROM genres WHERE active=1";

        try {
            connection = Conexion.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);

                while (resulset.next()) {
                    Genre genre = new Genre();
                    genre.setId(resulset.getInt(1));
                    genre.setName(resulset.getString(2));
                    listGenres.add(genre);
                }
                
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listGenres;
    }

    @Override
    public boolean update(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public Genre getGenreByName(String genreName) throws ClassNotFoundException {
        Genre genre = null;
        String sql;

        Connection connection;
        Statement statement;
        ResultSet resulset;

        sql = "SELECT * FROM genres WHERE name = '" + genreName + "'";

        connection = Conexion.conectar();
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);
                if (resulset.first()) {
                    genre = new Genre();
                    genre.setId(resulset.getInt(1));
                    genre.setName(resulset.getString(2));
                    boolean active = (resulset.getInt(3) != 0) ? true : false;
                    genre.setActive(active);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return genre;
    }
     
    public List<Genre> getGenresAlbumId(int idAlbum) throws ClassNotFoundException {
        ArrayList<Genre> genres = new ArrayList();
        Genre genre = null;
        
        Connection connection;
        Statement statement;
        ResultSet resulset;
        
        String sql = "SELECT g.id,g.name FROM genres AS g\n" +
        "INNER JOIN album_genres AS ag\n" +
        "ON g.id = ag.id_genre\n" +
        "WHERE ag.id_album = '" + idAlbum + "' AND g.active = '1' AND ag.active = '1'";
        
        connection = Conexion.conectar();
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);

                while (resulset.next()) {
                    genre = new Genre();
                    genre.setId(resulset.getInt(1));
                    genre.setName(resulset.getString(2));
                    genres.add(genre);
                }
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return genres;
    }
    
}
