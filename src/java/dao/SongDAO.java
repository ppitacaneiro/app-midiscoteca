/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.interfaceSong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Album;
import models.Song;

/**
 *
 * @author Pablo
 */
public class SongDAO implements interfaceSong {

    @Override
    public boolean set(Song song, int idAlbum) {
        boolean isSet = false;
        
        Connection connection = null;
        Statement statement = null;
        
        String sql = "INSERT INTO songs (id,title,id_album,active) VALUES (NULL,'" + song.getTitle() + "','" + idAlbum + "',1)";
        
        try {
           connection = Conexion.conectar();
           statement = connection.createStatement();
           int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
           if (affectedRows == 0) {
               throw new SQLException("Fallo al insertar cancion");
           } 
           
           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                   song.setId(generatedKeys.getInt(1));
                   isSet = true;
               } else {
                    throw new SQLException("Fallo al insertar cancion, no se obtuvo el ID generado.");
               }
           }
           
           statement.close();
           connection.close();
        } catch (SQLException ex) {
           Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSet;
    }

    @Override
    public boolean get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Song> getSongsOfAlbumById(int idAlbum)
    {
        ArrayList<Song> songs = new ArrayList();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resulset = null;
        
        String sql = "SELECT * FROM songs WHERE id_album = '" + idAlbum + "' AND active = 1";
        
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
                    Song song = new Song();
                    song.setId(resulset.getInt(1));
                    song.setTitle(resulset.getString(2));
                    songs.add(song);
                }
                statement.close();
                resulset.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return songs;
    }
    
}
