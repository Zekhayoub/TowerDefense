package TowerDefense.UI;

import TowerDefense.GameInfo;
import TowerDefense.attacks.Attack;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


public class ClickUpdateAttack implements EventHandler<MouseEvent> {
    private GameInfo gameInfo;
    private Attack attack;

    public ClickUpdateAttack(GameInfo gameInfo, Attack attack){
        this.gameInfo = gameInfo;
        this.attack = attack;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (gameInfo.canBuy(attack.getCost())){
            gameInfo.buyTurret(attack.getCost());
            gameInfo.upgradeAttack(attack.getAttackNumber());
        }
        else {
            new Alert(Alert.AlertType.INFORMATION, "You don't have enough money.").showAndWait();
        }
    }
}
