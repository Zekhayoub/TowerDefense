package TowerDefense.logbook;

import TowerDefense.menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickBackFromLogbook implements EventHandler<MouseEvent> {
    private final Menu menu;
    private final LogbookScreen logbookScreen;

    public ClickBackFromLogbook(Menu menu, LogbookScreen logbookScreen) {
        this.menu = menu;
        this.logbookScreen = logbookScreen;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        logbookScreen.getLogbookLayer().setMouseTransparent(true);
        menu.getWholeMenuLayer().setOpacity(1);
        menu.showAll();
        menu.getInterfaceLayer().getChildren().remove(logbookScreen.getLogbookLayer());
        logbookScreen.getLogbookLayer().getChildren().clear();
        logbookScreen.getLogbookLayer().setMouseTransparent(false);
    }
}
