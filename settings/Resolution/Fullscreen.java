package TowerDefense.settings.Resolution;

import TowerDefense.GameInfo;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.MouseEvent;

public class Fullscreen implements EventHandler<MouseEvent> {
    private GameInfo gameInfo;
    public Fullscreen(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        gameInfo.getStage().setFullScreenExitKeyCombination(new KeyCharacterCombination("alt+f4"));
        gameInfo.getStage().setFullScreenExitHint("Fullscreen On");
        gameInfo.getStage().setFullScreen(true);


        gameInfo.setWindowWidth((int)gameInfo.getStage().getWidth());
        gameInfo.setWindowHeight((int)gameInfo.getStage().getHeight());
    }
}
