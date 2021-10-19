/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 *
 * @author starfriut
 */
public class DBSearch {

    Connection connection;

    public void search(JFXTextField textField, BorderPane borderPane, VBox vBox, HBox hb, Label label, MaterialDesignIconView iconView, JFXButton button) {
        button.setDisable(true);
        try {
            int emptyness = 0;
            vBox = new VBox();
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vBox);
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            vBox.setSpacing(30);
            vBox.setPadding(new Insets(30));
            borderPane.setCenter(scrollPane);
            DBExtention extention = new DBExtention();
            connection = extention.openDBExtention();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ice_interns WHERE Full_name LIKE '%" + textField.getText() + "%' OR WhatsappNo LIKE '%" + textField.getText() + "%'";
            String query2 = "SELECT COUNT(ID) FROM ice_interns WHERE Full_name LIKE '%" + textField.getText() + "%' OR WhatsappNo LIKE '%" + textField.getText() + "%'";
            ResultSet rsEmpty = statement.executeQuery(query2);
            while (rsEmpty.next()) {
                emptyness = rsEmpty.getInt("COUNT(ID)");
            }
            if (emptyness == 0) {
                iconView = new MaterialDesignIconView(MaterialDesignIcon.EMOTICON_SAD, "100");
                iconView.setFill(Paint.valueOf("green"));
                Label info = new Label("Sorry", iconView);
                vBox.getChildren().add(info);
                vBox.setAlignment(Pos.CENTER);
            } else {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    hb = new HBox();
                    hb.setPadding(new Insets(30));
                    if ("Male".equals(resultSet.getString("Gender"))) {
                        iconView = new MaterialDesignIconView(MaterialDesignIcon.HUMAN_MALE, "40");
                    } else {
                        iconView = new MaterialDesignIconView(MaterialDesignIcon.HUMAN_FEMALE, "40");
                    }
                    iconView.setFill(Paint.valueOf("green"));
                    label = new Label(resultSet.getString("Full_name"), iconView);
                    HBox cover1 = new HBox(label);
                    HBox.setHgrow(cover1, Priority.ALWAYS);
                    hb.getChildren().add(cover1);
                    iconView = new MaterialDesignIconView(MaterialDesignIcon.PACKAGE, "40");
                    iconView.setFill(Paint.valueOf("green"));
                    label = new Label(resultSet.getString("Course"), iconView);
                    HBox cover2 = new HBox(label);
                    HBox.setHgrow(cover2, Priority.ALWAYS);
                    hb.getChildren().add(cover2);
                    iconView = new MaterialDesignIconView(MaterialDesignIcon.PHONE, "40");
                    iconView.setFill(Paint.valueOf("green"));
                    label = new Label(resultSet.getString("WhatsappNo"), iconView);
                    HBox cover3 = new HBox(label);
                    HBox.setHgrow(cover3, Priority.ALWAYS);
                    hb.getChildren().add(cover3);
                    vBox.getChildren().add(hb);
                }
            }
            ThemePainter tp = new ThemePainter();
            Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());
            if ("Light".equals(prefs.get("manager", "Light"))) {
                vBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                vBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            } else {
                vBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            }
            vBox.getStyleClass().add("internHome");
        } catch (SQLException ex) {
            Logger.getLogger(DBSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
