/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/**
 *
 * @author Star fruit
 */
public class NewIntern {

    Connection cont = null;
    Alert alert;

    public void addIntern(JFXTextField fullName, JFXTextField whatsappNo, JFXRadioButton male, JFXRadioButton female, ComboBox course, JFXDatePicker datePicker) throws SQLException {
        DBExtention extention = new DBExtention();
        cont = extention.openDBExtention();
        String query = "INSERT INTO ice_interns(Full_name, WhatsappNo, Gender, Course, Date) VALUES (?,?,?,?,?)";

        // check for invalid inputs and empty ones
        String fullNameChecker = "[A-Za-Z ]+";
        String numberChecker = "[0-9{11}]+";
        try {
            if ((fullName.getText() != null || fullName.getText().matches(fullNameChecker)) && (whatsappNo.getText().matches(numberChecker) || whatsappNo.getText() != null || whatsappNo.getText().length() <= 11) && (course.getValue() != null) && (datePicker.getValue() != null) && (male.isSelected() || female.isSelected())) {
                PreparedStatement ps = cont.prepareStatement(query);
                ps.setString(1, fullName.getText());
                ps.setString(2, whatsappNo.getText());
                ps.setString(4, course.getValue().toString());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                ps.setString(5, datePicker.getValue().format(dtf));
                if (male.isSelected()) {
                    ps.setString(3, "Male");
                } else if (female.isSelected()) {
                    ps.setString(3, "Female");
                }
                ps.execute();

                ThemePainter tp = new ThemePainter();
                Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());

                alert = new Alert(Alert.AlertType.INFORMATION);
                DialogPane dialogPane = alert.getDialogPane();
                if ("Light".equals(prefs.get("manager", "Light"))) {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
                } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
                } else {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
                }
                dialogPane.getStyleClass().add("myDialog");
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Successfully added.");
                alert.showAndWait();
            } else {
//                alert = new Alert(Alert.AlertType.INFORMATION);
                ThemePainter tp = new ThemePainter();
                Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Full name or whatsapp number is invalid.");
                DialogPane dialogPane = alert.getDialogPane();
                if ("Light".equals(prefs.get("manager", "Light"))) {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
                } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
                } else {
                    dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
                }
                dialogPane.getStyleClass().add("myDialog");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            ThemePainter tp = new ThemePainter();
            Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            DialogPane dialogPane = alert.getDialogPane();
            if ("Light".equals(prefs.get("manager", "Light"))) {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            } else {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            }
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
    }
}
