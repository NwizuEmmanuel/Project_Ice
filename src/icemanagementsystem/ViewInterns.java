/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 *
 * @author starfriut
 */
public class ViewInterns {

    Connection connection = null;

    public Node internsBox(HBox internBox, VBox box, ScrollPane pane, Label info, MaterialDesignIconView iconView) {
        box.setPadding(new Insets(30));
        box.setSpacing(30);
        ThemePainter tp = new ThemePainter();
        Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());
        if ("Light".equals(prefs.get("manager", "Light"))) {
            box.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            internBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            pane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            info.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
            box.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            internBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            pane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            info.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
        } else {
            box.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            internBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            pane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            info.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        }
        box.getStyleClass().add("internHome");
        internBox.getStyleClass().add("internBox");

        DBExtention extention = new DBExtention();
        connection = extention.openDBExtention();
        String sql = "SELECT Full_name, WhatsappNo, Gender, Course FROM ice_interns";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if ("Male".equals(resultSet.getString("Gender"))) {
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.PACKAGE);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info = new Label(resultSet.getString("Course"), iconView);
                    HBox coverCourse = new HBox(info);
                    HBox.setHgrow(coverCourse, Priority.ALWAYS);
                    info = new Label(resultSet.getString("Full_name"));
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.HUMAN_MALE);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info.setGraphic(iconView);
                    HBox coverName = new HBox(info);
                    HBox.setHgrow(coverName, Priority.ALWAYS);
                    info = new Label(resultSet.getString("WhatsappNo"));
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.WHATSAPP);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info.setGraphic(iconView);
                    HBox coverTel = new HBox(info);
                    HBox.setHgrow(coverTel, Priority.ALWAYS);
                    internBox = new HBox();
                    internBox.setPadding(new Insets(30));
                    internBox.getChildren().addAll(coverName, coverCourse, coverTel);
                } else {
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.PACKAGE);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info = new Label(resultSet.getString("Course"), iconView);
                    HBox coverCourse = new HBox(info);
                    HBox.setHgrow(coverCourse, Priority.ALWAYS);
                    info = new Label(resultSet.getString("Full_name"));
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.HUMAN_FEMALE);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info.setGraphic(iconView);
                    HBox coverName = new HBox(info);
                    HBox.setHgrow(coverName, Priority.ALWAYS);
                    info = new Label(resultSet.getString("WhatsappNo"));
                    iconView = new MaterialDesignIconView();
                    iconView.setIcon(MaterialDesignIcon.WHATSAPP);
                    iconView.setSize("30");
                    iconView.setFill(Paint.valueOf("green"));
                    info.setGraphic(iconView);
                    HBox coverTel = new HBox(info);
                    HBox.setHgrow(coverTel, Priority.ALWAYS);
                    internBox = new HBox();
                    internBox.setPadding(new Insets(30));
                    internBox.getChildren().addAll(coverName, coverCourse, coverTel);
                }
                box.getChildren().add(internBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewInterns.class.getName()).log(Level.SEVERE, null, ex);
        }
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        pane.setContent(box);

        return pane;
    }
}
