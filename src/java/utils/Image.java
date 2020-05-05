/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author Pablo
 */
public class Image {

    public Image() {
    }
    
    public boolean saveImage(String url,String filename) throws MalformedURLException, IOException, AccessDeniedException {
        boolean isFileSavedSuccess = false;
        
        try(InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(Config.PATH_SAVEIMAGES + filename + ".jpg"),REPLACE_EXISTING);
            isFileSavedSuccess = true;
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        } 
        
        return isFileSavedSuccess;
    }
    
}
