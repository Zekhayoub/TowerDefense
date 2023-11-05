package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Boss extends Enemy {
    private boolean alive = true;
    public Boss(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 1, 0, 2000, 25, 25, gameInfo, Color.RED, 1250);

        node = new Circle(size, Color.DARKCYAN);


        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }

    public boolean isAlive(){
        return alive;
    }

    public void isDead(){
        alive = false;
    }
}