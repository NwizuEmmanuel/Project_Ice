/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import java.util.prefs.Preferences;

/**
 *
 * @author carambola
 */
public class ThemePainter {
    
    private Preferences prefs;
    public String theme;
    
    public void paintDark(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        theme = "manager";
        prefs.remove(theme);
        prefs.put(theme, "Dark");
    }
    
    public void paintLight(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        theme = "manager";
        prefs.remove(theme);
        prefs.put(theme, "Light");
    }
}
