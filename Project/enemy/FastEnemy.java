package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FastEnemy extends Enemy {
    public FastEnemy(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 4, 0, 50, 5, 20, gameInfo, Color.ORANGE, 30);

        node = new Circle(size, Color.PALEVIOLETRED);

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }
}
