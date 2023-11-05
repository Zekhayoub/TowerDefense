package TowerDefense.logbook;

import TowerDefense.GameInfo;
import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GoBackFromLogbook implements EventHandler<KeyEvent> {
    private final Menu menu;
    private final LogbookScreen logbookScreen;

    public GoBackFromLogbook(Menu menu, GameInfo gameInfo, LogbookScreen logbookScreen) {
        this.menu = menu;
        this.logbookScreen = logbookScreen;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            logbookScreen.getLogbookLayer().setMouseTransparent(true);
            menu.getWholeMenuLayer().setOpacity(1);
            menu.showAll();
            menu.getInterfaceLayer().getChildren().remove(logbookScreen.getLogbookLayer());
            logbookScreen.getLogbookLayer().getChildren().clear();
            logbookScreen.getLogbookLayer().setMouseTransparent(false);
        }
    }
}
