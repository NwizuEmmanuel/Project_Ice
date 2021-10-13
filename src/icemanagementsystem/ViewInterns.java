/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialdesignicons.utils.MaterialDesignIconFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author starfriut
 */
public class ViewInterns {
    
    Connection connection = null;
    public VBox internsBox(){
        VBox box = new VBox();
        DBExtention extention = new DBExtention();
        connection = extention.openDBExtention();
        String sql = "SELECT Full_name, WhatsappNo, Gender, Course FROM ice_interns";
        MaterialDesignIconView maleIcon = new MaterialDesignIconView(MaterialDesignIcon.GENDER_MALE);
        MaterialDesignIconView femaleIcon = new MaterialDesignIconView(MaterialDesignIcon.GENDER_FEMALE);
        MaterialDesignIconView telIcon = new MaterialDesignIconView(MaterialDesignIcon.CELLPHONE);
        Label name;
        Label tel;
        HBox internBox = new HBox();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {                
                if ("Male".equals(resultSet.getString("Gender"))) {
                    name = new Label(resultSet.getString("Full_name"));
                    tel = new Label(resultSet.getString("WhatsappNo"));
                    tel.setGraphic(telIcon);
                    internBox.getChildren().addAll(maleIcon,name,tel);
                }else{
                    name = new Label(resultSet.getString("Full_name"));
                    tel = new Label(resultSet.getString("WhatsappNo"));
                    tel.setGraphic(telIcon);
                    internBox.getChildren().addAll(femaleIcon,name,tel);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewInterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(box);
        
        return box;
    }
}
