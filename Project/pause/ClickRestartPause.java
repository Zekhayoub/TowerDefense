package TowerDefense.pause;

import TowerDefense.Starter;
import TowerDefense.Time;
import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickRestartPause implements EventHandler<MouseEvent> {
    private Menu menu;
    private Starter starter;

    public ClickRestartPause(Menu menu){
        this.menu = menu;
        this.starter = menu.getStarter();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        starter.stop();
        starter.reset();
        starter.start(menu, starter.getMapNumber());
        Time.unpause();
    }
}
