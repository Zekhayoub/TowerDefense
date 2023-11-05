package TowerDefense.menu;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ClickQuit implements EventHandler<MouseEvent> {

    public ClickQuit() {
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
