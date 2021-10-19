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
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author starfriut
 */
public class internStats {

    public Node stats(VBox vb, Label statInfo, HBox statsBox) throws SQLException {
        MaterialDesignIconView webIconView = new MaterialDesignIconView(MaterialDesignIcon.WEB, "40");
        webIconView.setFill(Paint.valueOf("green"));
        MaterialDesignIconView softwareIconView = new MaterialDesignIconView(MaterialDesignIcon.APPS, "40");
        softwareIconView.setFill(Paint.valueOf("green"));
        MaterialDesignIconView mobileIconView = new MaterialDesignIconView(MaterialDesignIcon.CELLPHONE, "40");
        mobileIconView.setFill(Paint.valueOf("green"));
        MaterialDesignIconView graphics = new MaterialDesignIconView(MaterialDesignIcon.PENCIL, "40");
        graphics.setFill(Paint.valueOf("green"));
        MaterialDesignIconView seoIconView = new MaterialDesignIconView(MaterialDesignIcon.CREATION, "40");
        seoIconView.setFill(Paint.valueOf("green"));

        String numWeb = "";
        String numSoft = "";
        String numMobile = "";
        String numGraphics = "";
        String numSeo = "";
        LocalDate ld = LocalDate.now();
        DBExtention extention = new DBExtention();
        Connection connection = extention.openDBExtention();
        Statement statement = connection.createStatement();
        String queryWeb = "SELECT COUNT(Course) FROM ice_interns WHERE Date LIKE '%" + ld.getYear() + "%' AND Course = 'Web development and Hosting'";
        String querySoft = "SELECT COUNT(Course) FROM ice_interns WHERE Date LIKE '%" + ld.getYear() + "%' AND Course = 'Software development'";
        String queryMobile = "SELECT COUNT(Course) FROM ice_interns WHERE Date LIKE '%" + ld.getYear() + "%' AND Course = 'Mobile development'";
        String queryGraphics = "SELECT COUNT(Course) FROM ice_interns WHERE Date LIKE '%" + ld.getYear() + "%' AND Course = 'Graphics design'";
        String querySeo = "SELECT COUNT(Course) FROM ice_interns WHERE Date LIKE '%" + ld.getYear() + "%' AND Course = 'SEO/Content creation'";
        ResultSet rsWeb = statement.executeQuery(queryWeb);
        while (rsWeb.next()) {
            String num = rsWeb.getString("COUNT(Course)");
            int num2 = Integer.valueOf(num);
            if (num2 > 1) {
                numWeb = String.valueOf(num2) + " interns";
            } else {
                numWeb = String.valueOf(num2) + " intern";
            }
        }
        ResultSet rsSoft = statement.executeQuery(querySoft);
        while (rsSoft.next()) {
            String num = rsSoft.getString("COUNT(Course)");
            int num2 = Integer.valueOf(num);
            if (num2 > 1) {
                numSoft = String.valueOf(num2) + " interns";
            } else {
                numSoft = String.valueOf(num2) + " intern";
            }
        }
        ResultSet rsMobile = statement.executeQuery(queryMobile);
        while (rsMobile.next()) {
            String num = rsMobile.getString("COUNT(Course)");
            int num2 = Integer.valueOf(num);
            if (num2 > 1) {
                numMobile = String.valueOf(num2) + " interns";
            } else {
                numMobile = String.valueOf(num2) + " intern";
            }
        }
        ResultSet rsGraphics = statement.executeQuery(queryGraphics);
        while (rsGraphics.next()) {
            String num = rsGraphics.getString("COUNT(Course)");
            int num2 = Integer.valueOf(num);
            if (num2 > 1) {
                numGraphics = String.valueOf(num2) + " interns";
            } else {
                numGraphics = String.valueOf(num2) + " intern";
            }
        }
        ResultSet rsSeo = statement.executeQuery(querySeo);
        while (rsSeo.next()) {
            String num = rsSeo.getString("COUNT(Course)");
            int num2 = Integer.valueOf(num);
            if (num2 > 1) {
                numSeo = String.valueOf(num2) + " interns";
            } else {
                numSeo = String.valueOf(num2) + " intern";
            }
        }
        statInfo = new Label("Web Development and Hosting  (" + numWeb + ")", webIconView);
        statInfo.setFont(Font.font(14));
        statsBox = new HBox(statInfo);
        statsBox.setPadding(new Insets(10, 0, 10, 5));
        statsBox.setStyle("-fx-background-color: transparent");
        vb.getChildren().add(statsBox);
        statInfo = new Label("Software development  (" + numSoft + ")", softwareIconView);
        statInfo.setFont(Font.font(14));
        statsBox = new HBox(statInfo);
        statsBox.setPadding(new Insets(10, 0, 10, 5));
        statsBox.setStyle("-fx-background-color: transparent");
        vb.getChildren().add(statsBox);
        statInfo = new Label("Mobile development  (" + numMobile + ")", mobileIconView);
        statInfo.setFont(Font.font(14));
        statsBox = new HBox(statInfo);
        statsBox.setPadding(new Insets(10, 0, 10, 5));
        statsBox.setStyle("-fx-background-color: transparent");
        vb.getChildren().add(statsBox);
        statInfo = new Label("Graphics design  (" + numGraphics + ")", graphics);
        statInfo.setFont(Font.font(14));
        statsBox = new HBox(statInfo);
        statsBox.setPadding(new Insets(10, 0, 10, 5));
        statsBox.setStyle("-fx-background-color: transparent");
        vb.getChildren().add(statsBox);
        statInfo = new Label("SEO/Content creation  (" + numSeo + ")", seoIconView);
        statInfo.setFont(Font.font(14));
        statsBox = new HBox(statInfo);
        statsBox.setPadding(new Insets(10, 0, 10, 5));
        statsBox.setStyle("-fx-background-color: transparent");
        vb.setStyle("-fx-background-color: transparent");
        vb.getChildren().add(statsBox);
        vb.setPrefWidth(400);
        vb.setPadding(new Insets(30, 0, 0, 0));
        vb.setSpacing(30);
        return vb;
    }
}
