package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HealingEnemy extends Enemy {
    private final int radius;
    private final int heal;
    public HealingEnemy(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 2, 0, 250, 10, 20, gameInfo, Color.LIGHTSEAGREEN, 75);
        this.radius =100 + (int) (1- gameInfo.getGameDifficulty())/2;
        this.heal = (int) (2 * gameInfo.getGameDifficulty());

        node = new Circle(size, Color.MEDIUMVIOLETRED);

        generateRange();
        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }

    private boolean inRange(Enemy enemy){
        double diffX = Math.abs(x - enemy.getX());
        double diffY = Math.abs(y - enemy.getY());
        double distance = Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
        return distance < radius;
    }

    private void heal(){
        for (Enemy i : gameInfo.getEnemyViewerList()){
            if (inRange(i) && i != this){
                i.heal(heal);
            }
        }
    }

    public void animate(){
        if (animator.getChange()){
            layer.getChildren().remove(node);
            animator.animateEnemy(this);
            layer.getChildren().add(node);
            heal();
        }
    }

    private void generateRange(){
        differator.setRadius(radius);
        differator.setFill(Color.LIGHTSEAGREEN);
        differator.setOpacity(0.7);
        differator.setMouseTransparent(true);
        differator.setFocusTraversable(true);
    }

    protected void relocateAll(){
        healthBar.relocate(x,y-10);
        node.relocate(x,y);
        differator.relocate(x - radius + size ,y - radius + size);
        if (!layer.getChildren().contains(healthBar)){
            layer.getChildren().add(healthBar);
            layer.getChildren().add(node);
            layer.getChildren().add(differator);
        }
    }
}
