/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author carambola
 */
public class AddAccount {
    
    public JFXTextField fullNameField;
    public JFXButton nextButton;
    public JFXTextField telephone_number;
    public VBox container;
    public ComboBox courseBox;
    public JFXRadioButton gender_male;
    public JFXRadioButton gender_female;
    public JFXDatePicker datePicker;
    
    VBox creatAccount(Stage stage){
        LocalDate ld = LocalDate.now();
        JFXTextField emailField = new JFXTextField();
        emailField.setPromptText("Email");
        HBox emailBox = new HBox(emailField);
        HBox.setHgrow(emailField, Priority.ALWAYS);
        datePicker = new JFXDatePicker(ld);
        gender_male = new JFXRadioButton("Male");
        gender_female = new JFXRadioButton("Female");
        ToggleGroup gender_Group = new ToggleGroup();
        gender_Group.getToggles().addAll(gender_female,gender_male);
        nextButton = new JFXButton("Done");
        nextButton.getStyleClass().add("next-btn");
        courseBox = new ComboBox();
        courseBox.setPromptText("Choose course");
        courseBox.setPrefWidth(300);
        fullNameField = new JFXTextField();
        fullNameField.setPromptText("Full name");
        fullNameField.setLabelFloat(true);
        telephone_number = new JFXTextField();
        telephone_number.setPromptText("Whatsapp number");
        telephone_number.setLabelFloat(true);
        HBox coverFN = new HBox(fullNameField);
        HBox coverTN = new HBox(telephone_number);
        HBox coverCB = new HBox(courseBox);
        HBox coverNB = new HBox(nextButton);
        HBox coverG = new HBox(gender_female,gender_male);
        HBox coverDate = new HBox(datePicker);
        coverG.setSpacing(10);
        coverNB.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(emailBox, Priority.ALWAYS);
        VBox.setVgrow(coverNB, Priority.ALWAYS);
        VBox.setVgrow(coverCB, Priority.ALWAYS);
        VBox.setVgrow(coverFN, Priority.ALWAYS);
        VBox.setVgrow(coverTN, Priority.ALWAYS);
        VBox.setVgrow(coverG, Priority.ALWAYS);
        container = new VBox(coverFN,coverTN,coverG,coverCB,emailBox,coverDate,coverNB);
        container.setPadding(new Insets(30));
        container.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        HBox.setHgrow(fullNameField, Priority.ALWAYS);
        HBox.setHgrow(telephone_number, Priority.ALWAYS);
        HBox.setHgrow(courseBox, Priority.ALWAYS);
        courseBox.getItems().addAll("Web development and Hosting","Software development","Mobile development",
                "Graphics design","SEO/Content creation");
        nextButton.setOnAction(e->{
            try {
                NewIntern newIntern = new NewIntern();
                newIntern.addIntern(fullNameField, telephone_number, gender_male, gender_female, courseBox, datePicker,stage);
            } catch (SQLException ex) {
                Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        return container;
    }
}
