package TowerDefense.UI;

import TowerDefense.GameInfo;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickUpdate implements EventHandler<MouseEvent> {
    private UpdateButton updateButton;
    private GameInfo gameInfo;

    public ClickUpdate(UpdateButton updateButton, GameInfo gameInfo){
        this.updateButton = updateButton;
        this.gameInfo = gameInfo;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (updateButton.getClickable()){
            gameInfo.buyTurret(updateButton.getTurret().getUpdateCost());
            updateButton.getTurret().updateTurret();
        }
    }
}
