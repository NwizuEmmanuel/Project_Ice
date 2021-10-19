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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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
    public BorderPane rootBorderPane;
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
    private HBox internBox;
    private VBox internHome;
    private MaterialDesignIconView iconView;
    private ScrollPane scrollPane;
    private Label info;
    private JFXButton homeBtn;
    private MaterialDesignIconView homeIcon;
    private JFXButton searchTrigger;

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
        VBox statsHome = new VBox();
        Label statsInfo = new Label();
        HBox statsBox = new HBox();
        iconView = new MaterialDesignIconView();
        internHome = new VBox();
        internBox = new HBox();
        info = new Label();
        scrollPane = new ScrollPane();

        internStats iStats = new internStats();
        imgFrame iFrame = new imgFrame();
        rootBorderPane = new BorderPane();
        rootBorderPane.getStyleClass().add("body");
        rootBorderPane.setTop(toolbar());
        rootBorderPane.setCenter(iFrame.frame());
        try {
            rootBorderPane.setRight(iStats.stats(statsHome, statsInfo, statsBox));
        } catch (SQLException ex) {
            Logger.getLogger(GuiKit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rootBorderPane;
    }

    public JFXToolbar toolbar() {
        homeIcon = new MaterialDesignIconView(MaterialDesignIcon.HOME, "30");
        homeIcon.getStyleClass().add("icons");
        addIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_PLUS, "30");
        addIcon.getStyleClass().add("icons");
        settingIcon = new MaterialDesignIconView(MaterialDesignIcon.SETTINGS, "30");
        settingIcon.getStyleClass().add("icons");
        searchIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_SEARCH, "30");
        searchTrigger = new JFXButton("Name or whatsapp", searchIcon);
        searchTrigger.setStyle("-fx-background-color: white; -fx-text-fill: black");
        searchTrigger.setFocusTraversable(false);
        searchTrigger.setPrefWidth(300);
        searchTrigger.setAlignment(Pos.CENTER_LEFT);
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
            stage.showAndWait();
            AutoRefresh autoRefresh = new AutoRefresh();
            autoRefresh.refresh(internBox, internHome, scrollPane, info, iconView, rootBorderPane);
        });

        settingIconBtn = new JFXButton();
        settingIconBtn.setFocusTraversable(false);
        settingIconBtn.setGraphic(settingIcon);
        settingIconBtn.setTooltip(new Tooltip("Setting"));
        settingIconBtn.getStyleClass().add("icon-buttons");
        settingIconBtn.setOnAction(e -> {
            Stage stage = new Stage();
            settingScene = new Scene(tabPane(stage), 400, 400);
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
        homeBtn = new JFXButton();
        homeBtn.setFocusTraversable(false);
        homeBtn.setGraphic(homeIcon);
        homeBtn.setTooltip(new Tooltip("Back to home"));
        homeBtn.getStyleClass().add("icon-buttons");
        homeBtn.setOnAction(e -> {
            imgFrame iFrame = new imgFrame();
            rootBorderPane.setCenter(iFrame.frame());
            rootToolbar.setCenter(searchTrigger);
            settingIconBtn.setDisable(false);
        });

        searchBox = new JFXTextField();
        searchBox.setFocusTraversable(false);
        searchBox.setPromptText("Search");
        searchBox.getStyleClass().addAll("text-field-AS", "jfx-text-field-AS");
        DBSearch search = new DBSearch();
        searchBox.setOnKeyReleased(e -> {
            search.search(searchBox, rootBorderPane, internHome, internBox, info, iconView, settingIconBtn);
        });
        HBox leftToolsBox = new HBox(homeBtn, addIconBtn, settingIconBtn);
        searchBoxCover = new HBox();
        HBox.setHgrow(searchBox, Priority.ALWAYS);
        searchBoxCover.setPadding(new Insets(3));
        searchBoxCover.setSpacing(3);
        searchBoxCover.getChildren().addAll(searchIcon, searchBox);
        searchBoxCover.getStyleClass().add("search-box-cover");
        searchBoxCover.setMaxWidth(300);
        searchBoxCover.setMaxHeight(30);
        searchBoxCover.setAlignment(Pos.BOTTOM_LEFT);
        searchTrigger.setOnAction(e -> {
            iconView = new MaterialDesignIconView();
            internHome = new VBox();
            internBox = new HBox();
            info = new Label();
            scrollPane = new ScrollPane();
            rootToolbar.setCenter(searchBoxCover);
            searchBox.setFocusTraversable(true);
            ViewInterns interns = new ViewInterns();
            rootBorderPane.setCenter(interns.internsBox(internBox, internHome, scrollPane, info, iconView));
        });

        leftToolsBox.setPadding(new Insets(10));
        leftToolsBox.setSpacing(5);
        rootToolbar = new JFXToolbar();
        rootToolbar.setLeft(leftToolsBox);
        rootToolbar.setCenter(searchTrigger);
        rootToolbar.setMinWidth(900);
        return rootToolbar;
    }

    public TabPane tabPane(Stage stage) {
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
            internHome.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            internBox.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            rootscene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            settingScene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            // for children in tab for theme(container_theme)
            container_theme.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            scrollPane.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            info.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            //--------------------------
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            internBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            internHome.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            info.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            scrollPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            stage.close();
        });

        darkThemeBtn.setOnAction(e -> {
            ThemePainter painter = new ThemePainter();
            painter.paintDark();
            internHome.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            internBox.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            rootscene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            settingScene.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            info.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            scrollPane.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            // for children in tab for theme(container_theme)
            container_theme.getStylesheets().remove(getClass().getResource("/icemanagementsystem/Stylers/lightTheme.css").toExternalForm());
            //------------------------------
            rootscene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            settingScene.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            container_theme.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            internBox.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            internHome.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            info.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            scrollPane.getStylesheets().add(getClass().getResource("/icemanagementsystem/Stylers/darkTheme.css").toExternalForm());
            stage.close();
        });

        theme_tab.setContent(container_theme);
        return settingTabPane;
    }
}
