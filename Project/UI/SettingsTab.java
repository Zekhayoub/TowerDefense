package TowerDefense.UI;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.menu.ClickQuit;
import TowerDefense.menu.Menu;
import TowerDefense.pause.ClickBackPause;
import TowerDefense.pause.ClickRestartPause;
import TowerDefense.settings.SettingsButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SettingsTab {
    private Pane interfaceLayer;
    private Menu menu;
    private GameInfo gameInfo;

    public SettingsTab(Pane interfaceLayer, Menu menu, GameInfo gameInfo) {
        this.interfaceLayer = interfaceLayer;
        this.menu = menu;
        this.gameInfo = gameInfo;
    }

    public Group generateSettingsTab() {
        Group group = new Group();

        ImageView settingsTab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/tab4.png"))); // NEW
        settingsTab.relocate(30, 800); // 0 - 570

        SettingsButton settingsButton = new SettingsButton(interfaceLayer, gameInfo);
        VBox settingsBox = settingsButton.generateSettingsButton();

        ImageView back = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/home.png")));
        back.setFitHeight(35);
        back.setPreserveRatio(true);
        VBox backBox = new VBox(back);
        backBox.setAlignment(Pos.CENTER);
        backBox.setCursor(Cursor.HAND);
        backBox.setOnMouseClicked(new ClickBackPause(menu));

        ImageView restart = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/restart.png")));
        restart.setFitHeight(35);
        restart.setPreserveRatio(true);
        VBox restartBox = new VBox(restart);
        restartBox.setAlignment(Pos.CENTER);
        restartBox.setCursor(Cursor.HAND);
        restartBox.setOnMouseClicked(new ClickRestartPause(menu));
        //restartBox.setStyle("-fx-background-color: blue;");

        ImageView quit = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/exitRight.png")));
        quit.setFitHeight(35);
        quit.setPreserveRatio(true);
        VBox quitBox = new VBox(quit);
        quitBox.setAlignment(Pos.CENTER);
        quitBox.setCursor(Cursor.HAND);
        quitBox.setOnMouseClicked(new ClickQuit());
        //quitBox.setStyle("-fx-background-color: yellow;");

        HBox hbox = new HBox(backBox, settingsBox, restartBox, quitBox);
        //hbox.setStyle("-fx-background-color: white;");
        hbox.relocate(30, 800);
        hbox.setPadding(new Insets(7.5, 25, 7.5, 25));
        hbox.setSpacing(15);

        group.getChildren().addAll(settingsTab, hbox);

        return group;
    }
}
