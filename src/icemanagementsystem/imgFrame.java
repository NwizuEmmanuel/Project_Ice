/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author starfriut
 */
public class imgFrame {
    
    public VBox frame(){
        Label label = new Label("Intern with us");
        HBox hbox = new HBox(label);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(30));
        VBox box = new VBox(hbox);
        box.setAlignment(Pos.BOTTOM_LEFT);
        box.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/imgFramer.css").toExternalForm());
        hbox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/imgFramer.css").toExternalForm());
        box.getStyleClass().add("myFrame");
        hbox.getStyleClass().add("mySubFrame");
        return box;
    }
}
