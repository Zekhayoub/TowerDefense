package TowerDefense.menu.submenu;

import TowerDefense.menu.Menu;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ClickBack implements EventHandler<MouseEvent> {
    private Menu menu;

    public ClickBack(Menu menu){
        this.menu = menu;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300), menu.getSubmenuLayer());
        fadeTransition.setFromValue(1);
        menu.getMenuLayer().setOpacity(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        menu.getSubmenuLayer().setMouseTransparent(true);

        fadeTransition.setOnFinished(actionEvent -> {
            menu.getMenuLayer().toFront();
            menu.getMenuLayer().requestFocus();
        });
    }
}
