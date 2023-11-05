package TowerDefense.gameOver;

import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickBacker implements EventHandler<MouseEvent> {
    private Menu menu;
    public ClickBacker(Menu menu){
        this.menu = menu;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        menu.showAll();
    }
}
