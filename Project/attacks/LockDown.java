package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

public class LockDown extends  Attack implements InstantEffect{
    public LockDown(int x, int y, GameInfo gameInfo) {
        super(x, y, 0, 0, 1250, gameInfo, 5);
        this.name = "LockDown";
    }


    @Override
    public void launchAttack() {
        if (level >= 3){
            for (Enemy i : gameInfo.getEnemyViewerList()){
                i.dealDamage(i.getHealth());
            }
        }
        if (level >= 5){
            gameInfo.loseLife(-50);
        }
        if (level >= 10){
            gameInfo.addGold(gameInfo.getGold());
        }
    }

    @Override
    public void attackAnimation(Pane layer) {
        if (level < 3){
            new Alert(Alert.AlertType.INFORMATION, "It's not very effective... You should try and upgrade it").showAndWait();
        }
        if (level >= 3 && level < 5){
            new Alert(Alert.AlertType.INFORMATION, "It's super effective! But can it do more?").showAndWait();
        }
        if (level >= 5 && level < 10){
            new Alert(Alert.AlertType.INFORMATION, "It's ultra effective! But can it do more?").showAndWait();
        }
        if (level >= 10){
            new Alert(Alert.AlertType.INFORMATION, "Stop cheating").showAndWait();
        }
    }

    @Override
    public void stop() {
    }

}
