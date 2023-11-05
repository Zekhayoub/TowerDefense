package TowerDefense.menu.submenu;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class ClickExit implements EventHandler<MouseEvent> {
    private GridPane submenuLayer;
    private Rectangle mapSlot;
    private ClickEnter clickEnter;

    public ClickExit(GridPane submenuLayer, Rectangle mapSlot, ClickEnter clickEnter) {
        this.submenuLayer = submenuLayer;
        this.mapSlot = mapSlot;
        this.clickEnter = clickEnter;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        mapSlot.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
        submenuLayer.getChildren().removeAll(clickEnter.getGroup(), clickEnter.getText());
    }
}
