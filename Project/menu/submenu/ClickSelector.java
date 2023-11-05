package TowerDefense.menu.submenu;

import TowerDefense.GameInfo;
import TowerDefense.Starter;
import TowerDefense.Time;
import TowerDefense.menu.Menu;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ClickSelector implements EventHandler<MouseEvent> {
    private final Menu menu;
    private final Starter starter;
    private final int mapNumber;
    private GameInfo gameInfo;

    public ClickSelector(Menu menu, Starter starter, GameInfo gameInfo, int mapNumber){
        this.menu = menu;
        this.starter = starter;
        this.mapNumber = mapNumber;
        this.gameInfo = gameInfo;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (!starter.getFirst()) {
            starter.reset();
        }
        if (mapNumber <= gameInfo.getProgress()){
            startNewGame();
        }
        else {
            new Alert(Alert.AlertType.INFORMATION, "You can't do that, yet...").showAndWait();
        }

    }


    private void startNewGame(){
        starter.start(menu, mapNumber); //START GAME HERE
        menu.getWholeMenuLayer().setMouseTransparent(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300), menu.getSubmenuLayer());
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                menu.hideAll();
                menu.getInterfaceLayer().requestFocus();
                menu.getWholeMenuLayer().setMouseTransparent(false);
                Time.unpause();
            }
        });
    }
}

