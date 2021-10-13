/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icemanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToolbar;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.util.prefs.Preferences;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author onyeka
 */
public class GuiKit {

    private ThemePainter tp;
    private Preferences prefs;

    private Scene rootscene;
    private BorderPane rootBorderPane;
    private JFXToolbar rootToolbar;
    private MaterialDesignIconView addIcon;
    private MaterialDesignIconView settingIcon;
    private MaterialDesignIconView searchIcon;
    private JFXButton addIconBtn;
    private JFXButton settingIconBtn;
    private JFXTextField searchBox;
    private HBox searchBoxCover;
    private TabPane settingTabPane;
    private Tab theme_tab;
    private JFXRadioButton lightThemeBtn;
    private JFXRadioButton darkThemeBtn;
    private Scene settingScene;
    private VBox container_theme;
    private VBox addAccountBox;

    public Scene scene(Parent p) {
        tp = new ThemePainter();
        prefs = Preferences.userRoot().node(tp.getClass().getName());
        rootscene = new Scene(p);
        if ("Light".equals(prefs.get("manager", "Light"))) {
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
        } else {
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        }
        return rootscene;
    }

    public BorderPane borderPane() {
        ViewInterns interns = new ViewInterns();
        rootBorderPane = new BorderPane();
        rootBorderPane.getStyleClass().add("body");
        rootBorderPane.setTop(toolbar());
        rootBorderPane.setCenter(interns.internsBox());
        return rootBorderPane;
    }

    public JFXToolbar toolbar() {
        MaterialDesignIconView searchBtnIcon = new MaterialDesignIconView(MaterialDesignIcon.BOOK_OPEN_PAGE_VARIANT,"30");
        searchBtnIcon.getStyleClass().add("icons");
        JFXButton searchBtn = new JFXButton(null);
        searchBtn.setGraphic(searchBtnIcon);
        addIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_PLUS, "30");
        addIcon.getStyleClass().add("icons");
        settingIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS, "30");
        settingIcon.getStyleClass().add("icons");
        searchIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_SEARCH, "30");
        addIconBtn = new JFXButton();
        addIconBtn.setFocusTraversable(false);
        addIconBtn.setGraphic(addIcon);
        addIconBtn.setTooltip(new Tooltip("Add account"));
        addIconBtn.getStyleClass().add("icon-buttons");
        addIconBtn.setOnAction((e) -> {
            AddAccount addAccountEvent = new AddAccount();
            Stage stage = new Stage();
            addAccountBox = addAccountEvent.creatAccount(stage);
            Scene addAccountScene = new Scene(addAccountBox, 400, 400);
            tp = new ThemePainter();
            prefs = Preferences.userRoot().node(tp.getClass().getName());
            if ("Light".equals(prefs.get("manager", "Light"))) {
                addAccountBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                addAccountBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            } else {
                addAccountBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(false);
            stage.setTitle("Intern form");
            stage.setResizable(false);
            stage.setScene(addAccountScene);
            stage.show();
        });

        settingIconBtn = new JFXButton();
        settingIconBtn.setFocusTraversable(false);
        settingIconBtn.setGraphic(settingIcon);
        settingIconBtn.setTooltip(new Tooltip("Setting"));
        settingIconBtn.getStyleClass().add("icon-buttons");
        settingIconBtn.setOnAction(e -> {
            Stage stage = new Stage();
            settingScene = new Scene(tabPane(), 400, 400);
            tp = new ThemePainter();
            prefs = Preferences.userRoot().node(tp.getClass().getName());
            if ("Light".equals(prefs.get("manager", "Light"))) {
                settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
                settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            } else {
                settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(false);
            stage.setTitle("Settings");
            stage.setResizable(false);
            stage.setScene(settingScene);
            stage.show();
        });

        searchBox = new JFXTextField();
        searchBox.setFocusTraversable(false);
        searchBox.setPromptText("Search");
        searchBox.getStyleClass().addAll("text-field-AS", "jfx-text-field-AS");
        HBox leftToolsBox = new HBox(addIconBtn, settingIconBtn);
        searchBoxCover = new HBox();
        HBox.setHgrow(searchBox, Priority.ALWAYS);
        searchBoxCover.setPadding(new Insets(3));
        searchBoxCover.setSpacing(3);
        searchBoxCover.getChildren().addAll(searchIcon, searchBox);
        searchBoxCover.getStyleClass().add("search-box-cover");
        searchBoxCover.setMaxWidth(300);
        searchBoxCover.setMaxHeight(30);
        searchBoxCover.setAlignment(Pos.BOTTOM_LEFT);

        leftToolsBox.setPadding(new Insets(10));
        leftToolsBox.setSpacing(5);
        rootToolbar = new JFXToolbar();
        rootToolbar.setLeft(leftToolsBox);
        rootToolbar.setCenter(searchBoxCover);
        rootToolbar.setMinWidth(900);
        return rootToolbar;
    }

    public TabPane tabPane() {
        lightThemeBtn = new JFXRadioButton("Light theme");
        lightThemeBtn.setFocusTraversable(false);
        darkThemeBtn = new JFXRadioButton("Dark theme");
        darkThemeBtn.setFocusTraversable(false);
        darkThemeBtn.setFont(Font.font(15));
        lightThemeBtn.setFont(Font.font(15));
        theme_tab = new Tab("Theme");
        ToggleGroup themeGroup = new ToggleGroup();
        themeGroup.getToggles().addAll(lightThemeBtn, darkThemeBtn);
        settingTabPane = new TabPane(theme_tab);
        settingTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        container_theme = new VBox(lightThemeBtn, darkThemeBtn);

        tp = new ThemePainter();
        prefs = Preferences.userRoot().node(tp.getClass().getName());
        if ("Light".equals(prefs.get("manager", "Light"))) {
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        } else if ("Dark".equals(prefs.get("manager", "Dark"))) {
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
        } else {
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        }
        container_theme.setPadding(new Insets(20));
        container_theme.setSpacing(50);

        lightThemeBtn.setOnAction(e -> {
            ThemePainter painter = new ThemePainter();
            painter.paintLight();
            rootscene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            settingScene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            // for children in tab for theme(container_theme)
            container_theme.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
        });

        darkThemeBtn.setOnAction(e -> {
            ThemePainter painter = new ThemePainter();
            painter.paintDark();
            rootscene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            settingScene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            // for children in tab for theme(container_theme)
            container_theme.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
        });

        theme_tab.setContent(container_theme);
        return settingTabPane;
    }
}
