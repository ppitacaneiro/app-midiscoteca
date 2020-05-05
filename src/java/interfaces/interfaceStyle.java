/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Style;

/**
 *
 * @author Pablo
 */
public interface interfaceStyle {
    public boolean set(Style style);
    public List<Style> get();
    public boolean update(Style style);
    public boolean delete(Style style);
}
