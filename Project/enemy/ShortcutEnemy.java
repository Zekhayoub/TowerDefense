package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class ShortcutEnemy extends Enemy {
    private final int endX;
    private final int endY;
    private boolean followWay = false;

    public ShortcutEnemy(Pane layer, int x, int y, GameInfo gameInfo, int endX, int endY) {
        super(layer, x, y, 2, 0, 75, 10, 20, gameInfo, Color.BROWN, 75);
        this.endX = endX - gameInfo.getTileSize()/2;
        this.endY = endY - gameInfo.getTileSize()/2;

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }

    private boolean goStraight() {
        int distance = (int) Math.round(Math.sqrt(Math.pow(endX - x,2)+Math.pow(endY - y,2)));
        boolean straight = false;

        if ((double) (endX - x) / distance != 1 && (double) (endX - x) / distance != -1 && !followWay) {
            setDx((int) Math.round((double)(endX - x)*2/distance));
            setDy((int) Math.round((double)(endY - y)*2/distance));
            straight = true;
        }
        return straight;
    }

    public void changeTrajectory(ArrayList<ArrayList<Integer>> moveCoordinates) {
        if (!followWay){
            if (!goStraight()){
                followWay = true;
            }
        }
        if (followWay) {
            double security = size;
            int realX = this.getX() + this.getSize() - gameInfo.getMargin();
            int realY = this.getY() + this.getSize();
            for (ArrayList<Integer> i : moveCoordinates) {
                if (i.size() == 6) {
                    if (i.get(0) - security <= realX && i.get(0) + security >= realX &&
                            i.get(1) - security <= realY && i.get(1) + security >= realY){
                        if (new Random().nextInt(25/speed)== 1) {
                            this.setAutoSpeed(i, 4, 5);
                        }
                        else {
                            this.setAutoSpeed(i, 2 , 3);
                        }
                    }
                } else {
                    if (i.get(0) - security <= realX && i.get(0) + security >= realX &&
                            i.get(1) - security <= realY && i.get(1) + security >= realY) {
                        this.setAutoSpeed(i, 2 , 3);
                    }
                }
            }
        }


    }

    public int enemyAngle(){
        return (int) Math.round(Math.toDegrees(Math.atan((double) dy/dx)));
    }
}
