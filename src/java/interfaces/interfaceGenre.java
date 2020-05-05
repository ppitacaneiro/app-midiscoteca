/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Genre;

/**
 *
 * @author Pablo
 */
public interface interfaceGenre {
    public boolean set(Genre genre);
    public List<Genre> get();
    public boolean update(Genre genre);
    public boolean delete(Genre genre);
}
