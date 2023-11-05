package TowerDefense.clearScreen;

import TowerDefense.Starter;
import TowerDefense.Time;
import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class ClickNext implements EventHandler<MouseEvent> {
    private Menu menu;
    private Starter starter;

    public ClickNext(Menu menu){
        this.menu = menu;
        this.starter = menu.getStarter();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        starter.reset();
        if (starter.getMapNumber() == 8){
            new Alert(Alert.AlertType.WARNING, "You finished the game").showAndWait();
        }
        else {
            starter.start(menu, starter.getMapNumber()+1);
        }
        Time.unpause();
    }
}
