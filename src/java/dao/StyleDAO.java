/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.interfaceStyle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Album;
import models.Style;

/**
 *
 * @author Pablo
 */
public class StyleDAO implements interfaceStyle {
    
    public StyleDAO() {
    }
    
    @Override
    public boolean set(Style style) {
        boolean isSet = false;
        
        Connection connection = null;
        Statement statement = null;
        
        String sql = "INSERT INTO styles (id,name,active) VALUES (NULL,'" + style.getName() + "',1)";
        
        try {
           connection = Conexion.conectar();
           statement = connection.createStatement();
           int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
           if (affectedRows == 0) {
               throw new SQLException("Fallo al insertar estilo");
           } 
           
           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                   style.setId(generatedKeys.getInt(1));
                   isSet = true;
               } else {
                    throw new SQLException("Fallo al insertar estilo, no se obtuvo el ID generado.");
               }
           }
           
           statement.close();
           connection.close();
        } catch (SQLException ex) {
           Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StyleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSet;
    }

    @Override
    public List<Style> get() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resulset = null;

        List<Style> listStyles = new ArrayList<>();

        String sql = "SELECT * FROM styles WHERE active=1";

        try {
            connection = Conexion.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StyleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);

                while (resulset.next()) {
                    Style style = new Style();
                    style.setId(resulset.getInt(1));
                    style.setName(resulset.getString(2));
                    style.setImage(this.getRandomImageForStyle(style.getId()));
                    listStyles.add(style);
                }
                
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listStyles;
    }

    @Override
    public boolean update(Style style) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Style style) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Style getStyleByName(String styleName) throws ClassNotFoundException {
        Style style = null;
        String sql;

        Connection connection;
        Statement statement;
        ResultSet resulset;

        sql = "SELECT * FROM styles WHERE name = '" + styleName + "'";
        
        connection = Conexion.conectar();
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);
                if (resulset.first()) {
                    style = new Style();
                    style.setId(resulset.getInt(1));
                    style.setName(resulset.getString(2));
                    boolean active = (resulset.getInt(3) != 0) ? true : false;
                    style.setActive(active);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return style;
    }
    
    public List<Style> getStyleByAlbumId(int idAlbum) throws ClassNotFoundException {
        ArrayList<Style> styles = new ArrayList();
        Style style = null;
        
        Connection connection;
        Statement statement;
        ResultSet resulset;
        
        String sql = "SELECT s.id,s.name FROM styles AS s\n" +
        "INNER JOIN album_styles AS ast\n" +
        "ON s.id = ast.id_style\n" +
        "WHERE ast.id_album = '" + idAlbum + "' AND s.active = '1' AND ast.active = '1'";
        
        connection = Conexion.conectar();
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);

                while (resulset.next()) {
                    style = new Style();
                    style.setId(resulset.getInt(1));
                    style.setName(resulset.getString(2));
                    styles.add(style);
                }
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return styles;
    }
    
    public String getRandomImageForStyle(int idStyle) {
        String imageName = null;
        ArrayList<Album> listAlbumsByStyle = new ArrayList();
        int maxValue = 0;
        int minValue = 0;
        int randomInt = 0;
        
        listAlbumsByStyle = (ArrayList<Album>) this.getAlbumsByStyle(idStyle);
        maxValue = listAlbumsByStyle.size() - 1;
        randomInt = (int)(Math.random() * (maxValue - minValue + 1) + minValue);
        
        imageName = listAlbumsByStyle.get(randomInt).getImageCover();
        
        return imageName;
    }
    
    public List<Album> getAlbumsByStyle(int idStyle) {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resulset = null;
        
        ArrayList<Album> albumsByStyle = new ArrayList();
        
        String sql = "SELECT a.id,ar.name,a.title,a.image_cover,a.year,a.active "
                + " FROM albums AS a "
                + " INNER JOIN artists AS ar "
                + " ON a.id_artist = ar.id "
                + " INNER JOIN album_styles AS ast "
                + " ON a.id = ast.id_album "
                + " WHERE "
                + " ("
                + " a.active = 1 "
                + " AND ar.active = 1 "
                + " AND ast.active = 1 "
                + " AND ast.id_style = " + idStyle + " "  
                + " )"
                + " ORDER BY ar.name ";
        
        try {
            connection = Conexion.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            try {
                statement = connection.createStatement();
                resulset = statement.executeQuery(sql);

                while (resulset.next()) {
                    Album album = new Album();
                    album.setId(resulset.getInt(1));
                    album.setArtist(resulset.getString(2));
                    album.setTitle(resulset.getString(3));
                    album.setImageCover(resulset.getString(4));
                    album.setYear(resulset.getInt(5));
                    boolean active = (resulset.getInt(6) != 0) ? true : false;
                    album.setActive(active);
                    albumsByStyle.add(album);
                }
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return albumsByStyle;
    }
}
