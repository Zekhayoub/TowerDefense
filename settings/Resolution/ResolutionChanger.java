package TowerDefense.settings.Resolution;

import TowerDefense.GameInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ResolutionChanger implements EventHandler<MouseEvent> {
    private final int width;
    private final int height;
    private final GameInfo gameInfo;
    public ResolutionChanger(int width, int height, GameInfo gameInfo){
        this.width = width;
        this.height = height;
        this.gameInfo = gameInfo;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(gameInfo.getStage().isFullScreen()){
            gameInfo.getStage().setFullScreen(false);
        }
        gameInfo.setWindowWidth(width);
        gameInfo.setWindowHeight(height);
    }
}
