package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EatingEnemy extends Enemy {
    public EatingEnemy(Pane layer, int x, int y, GameInfo gameInfo) {
        super(layer, x, y, 5, 0, 100, 10, 20, gameInfo, Color.HOTPINK, 100); //Ouille HOT PINK

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }

    private void eat(){
        for (Enemy i : gameInfo.getEnemyViewerList()){
            if (i != this && inRange(i)){
                maxHealth += i.getHealth();
                health += i.getHealth();
                size += 2;
                animator.changeSize(size);
                x -= 2;
                y -= 2;
                differator.setRadius((double)size/2);
                if (speed * 3/4 != 0){
                    speed *= (double) 3/4;
                    if (dx != 0 && dx != 1){
                        dx *= (double) 3/4;
                    }
                    else if (dy != 0 && dy != 1){
                        dy *= (double) 3/4;
                    }
                }

                i.dealDamage(i.getHealth());
            }
        }
    }

    public void animate(){
        if (size <= 26){ //Can eat max 3 ennemis
            eat();
        }
        if (animator.getChange() && !frozen){
            layer.getChildren().remove(node);
            animator.animateEnemy(this);
            layer.getChildren().add(node);
        }
    }

    private boolean inRange(Enemy enemy){
        double diffX = Math.abs(x - enemy.getX());
        double diffY = Math.abs(y - enemy.getY());
        double distance = Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
        return distance < size;
    }
}
