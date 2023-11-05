package TowerDefense.attacks;

import TowerDefense.GameInfo;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

public class Potion extends Attack implements InstantEffect{
    private int healing;
    public Potion(int x, int y,GameInfo gameInfo) {
        super(x, y, 0, 0, 250, gameInfo, 6);
        healing = 10;
        healing *= level;
        this.name = "Potion";
    }

    @Override
    public void launchAttack() {
        gameInfo.loseLife(-healing);
    }

    @Override
    public void attackAnimation(Pane layer) {
        new Alert(Alert.AlertType.INFORMATION, "You are filled with DETERMINATION").showAndWait();
    }

    @Override
    public void stop() {
    }
}
