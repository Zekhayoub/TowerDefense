package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RealZombie extends Enemy {
    private final int respawnDelay;
    public RealZombie(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 2, 0, 200, 15, 20, gameInfo, Color.VIOLET, 40);

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
        respawnDelay = 1500;
    }

    public void respawn(){
        Timeline delay = new Timeline(new KeyFrame(Duration.millis(respawnDelay), actionEvent -> {
            ClassicEnemy classicEnemy = new ClassicEnemy(layer, x+size, y+size, gameInfo);
            gameInfo.addEnemy(classicEnemy);
            classicEnemy.setDx(dx);
            classicEnemy.setDy(dy);
        }));
        delay.setCycleCount(1);
        delay.play();
    }


}
