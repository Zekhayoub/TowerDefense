package TowerDefense.menu;

import TowerDefense.GameInfo;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CheatFuntion implements EventHandler<KeyEvent> {
    private GameInfo gameInfo;
    public CheatFuntion(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.F1){
            gameInfo.setGold(100000);
            gameInfo.setProgress(10);
        }
    }
}
