package TowerDefense.settings;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsButton {
    private VBox button = new VBox();
    private ImageView settings = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/gear.png")));
    private Pane interfaceLayer;
    private GameInfo gameInfo;

    public SettingsButton(Pane interfaceLayer, GameInfo gameInfo) {
        this.interfaceLayer = interfaceLayer;
        this.gameInfo = gameInfo;
    }

    public VBox generateSettingsButton() {
        //button.setStyle("-fx-background-color: green;"); // permet de situer et voir la VBox

        settings.setFitHeight(35);
        settings.setPreserveRatio(true);
        button.getChildren().add(settings);
        button.setAlignment(Pos.CENTER);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(new ClickSettings(interfaceLayer, gameInfo));

        return button;
    }

    public void addToLayer(Pane layer) { layer.getChildren().add(button); }
}
