package TowerDefense.settings;

import TowerDefense.GameInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ClickSettings implements EventHandler<MouseEvent> {
    private final Pane interfaceLayer;
    private final GameInfo gameInfo;

    public ClickSettings(Pane interfaceLayer, GameInfo gameInfo) {
        this.interfaceLayer = interfaceLayer;
        this.gameInfo = gameInfo;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        SettingsScreen settingsScreen = new SettingsScreen(gameInfo, interfaceLayer, 1);
        settingsScreen.initSettingsScreen();
        settingsScreen.settingsGame();
        //settingsScreen.initQuitEvent();        // NEW
    }
}

