package TowerDefense.pause;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ClickSettingsFromPause implements EventHandler<MouseEvent> {
    private Pane whole;
    private Pane settingScreenLayer;

    public ClickSettingsFromPause(Pane whole, Pane settingScreenLayer) {
        this.settingScreenLayer = settingScreenLayer;
        this.whole = whole;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        settingScreenLayer.requestFocus();
        if (!whole.getChildren().contains(settingScreenLayer)) {
            whole.getChildren().add(settingScreenLayer);
        }
    }
}