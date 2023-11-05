package TowerDefense.pause;

import TowerDefense.settings.SettingsScreen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickResolution implements EventHandler<MouseEvent> {
    private SettingsScreen settingsScreen;

    public ClickResolution(SettingsScreen settingsScreen){
        this.settingsScreen = settingsScreen;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        settingsScreen.showRes();
    }
}
