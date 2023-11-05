package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BigEnemy extends Enemy {
    public BigEnemy(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 3, 0, 200, 10, 22, gameInfo,Color.LIMEGREEN, 150);

        node = new Circle(size, Color.RED);

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }
}