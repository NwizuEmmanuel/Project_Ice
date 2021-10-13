/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToolbar;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author carambola
 */
public class IceManagementSystem extends Application {

    public JFXButton addPersonBtn;
    public JFXButton settingBtn;
    public JFXTextField searchBox;
    public JFXToolbar toolbar;
    public MaterialDesignIconView xbox_icon;
    public MaterialDesignIconView coinIcon;
    public MaterialDesignIconView searchIcon;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        GuiKit guiKit = new GuiKit();
        Scene scene = guiKit.scene(guiKit.borderPane());
        primaryStage.setTitle("Ice MS");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(900);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        DBExtention extention = new DBExtention();
        extention.openDBExtention();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
