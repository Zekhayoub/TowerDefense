package TowerDefense.logbook;

import TowerDefense.GameInfo;
import TowerDefense.logbook.LogbookScreen;
import TowerDefense.menu.Menu;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ClickLogbook implements EventHandler<MouseEvent> {
    private final Menu menu;
    private final LogbookScreen logbookScreen;

    public ClickLogbook(Menu menu, GameInfo gameInfo){
        this.menu = menu;
        logbookScreen = new LogbookScreen(menu, gameInfo);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        menu.getWholeMenuLayer().setMouseTransparent(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300), menu.getWholeMenuLayer());
        fadeTransition.setFromValue(1);
        Pane logbookLayer = logbookScreen.initLogbookScreen();
        menu.getInterfaceLayer().getChildren().add(logbookLayer);
        logbookLayer.setOpacity(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(actionEvent -> {
            menu.hideAll();
            logbookLayer.requestFocus();
            menu.getWholeMenuLayer().setMouseTransparent(false);
        });
    }
}
