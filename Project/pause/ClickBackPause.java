package TowerDefense.pause;

import TowerDefense.Starter;
import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickBackPause implements EventHandler<MouseEvent> {
    private Menu menu;
    private Starter starter;
    public ClickBackPause(Menu menu){
        this.menu = menu;
        this.starter = menu.getStarter();
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        menu.showAll();
        starter.stop();
    }
}
