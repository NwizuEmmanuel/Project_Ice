/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author starfriut
 */
public class internStats {
    
    public Node stats(VBox vb, Label statInfo, HBox statsBox){
        int iconNum;
        MaterialDesignIconView webIconView = new MaterialDesignIconView(MaterialDesignIcon.WEB, "40");
        MaterialDesignIconView softwareIconView = new MaterialDesignIconView(MaterialDesignIcon.APPLICATION, "40");
        MaterialDesignIconView mobileIconView  = new MaterialDesignIconView(MaterialDesignIcon.PHONE, "40");
        MaterialDesignIconView graphics = new MaterialDesignIconView(MaterialDesignIcon.PENCIL,"40");
        MaterialDesignIconView seoIconView = new MaterialDesignIconView(MaterialDesignIcon.CREATION, "40");
        for (int i = 1; i < 6; i++) {
            iconNum = 1;
            switch (iconNum) {
                case 1:
                    statsBox = new HBox(webIconView);
                    break;
                case 2:
                    statsBox = new HBox(softwareIconView);
                    break;
                case 3:
                    statsBox = new HBox(mobileIconView);
                    break;
                case 4:
                    statsBox = new HBox(graphics);
                case 5:
                    statsBox = new HBox(seoIconView);
                default:
                    break;
            }
            ++iconNum;
            vb.getChildren().add(statsBox);
        }
        return vb;
    }
}
