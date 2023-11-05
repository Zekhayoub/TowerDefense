package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ClassicEnemy extends Enemy {
    public ClassicEnemy(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 2, 0, 100, 10, 20, gameInfo, Color.STEELBLUE, 20);

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }
}
