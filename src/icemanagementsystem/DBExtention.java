/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

/**
 *
 * @author onyeka
 */
public class DBExtention {

    final String DB_URL = "jdbc:mysql://localhost:3306/ice_interns";
    final String user_name = "root";
    final String password = "";

    public Connection openDBExtention() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, user_name, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBExtention.class.getName()).log(Level.SEVERE, null, ex);
            ThemePainter tp = new ThemePainter();
            Preferences prefs = Preferences.userRoot().node(tp.getClass().getName());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            if ("Light".equals(prefs.get("manager", "Light"))) {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            } else {
                dialogPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            }
            dialogPane.getStyleClass().add("myDialog");

            alert.setTitle("Xampp connectivity");
            alert.setHeaderText("Server not working!");
            alert.setContentText(ex.getMessage());
            alert.initStyle(StageStyle.DECORATED);
            alert.showAndWait();
        }
        return conn;
    }
}
