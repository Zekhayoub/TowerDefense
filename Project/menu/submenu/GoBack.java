package TowerDefense.menu.submenu;

import TowerDefense.menu.Menu;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class GoBack implements EventHandler<KeyEvent> {
    private final Menu menu;

    public GoBack(Menu menu){
        this.menu = menu;
    }

    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ESCAPE) {

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300), menu.getSubmenuLayer());
            fadeTransition.setFromValue(1);
            menu.getMenuLayer().setOpacity(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();

            fadeTransition.setOnFinished(actionEvent -> {
                menu.getMenuLayer().toFront();
                menu.getMenuLayer().requestFocus();
            });
        }
    }
}