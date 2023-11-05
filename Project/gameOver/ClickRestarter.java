package TowerDefense.gameOver;

import TowerDefense.Starter;
import TowerDefense.Time;
import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickRestarter implements EventHandler<MouseEvent> {
    private Menu menu;
    private Starter starter;

    public ClickRestarter(Menu menu){
        this.menu = menu;
        this.starter = menu.getStarter();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        starter.reset();
        starter.start(menu, starter.getMapNumber());
        Time.unpause();
    }
}
