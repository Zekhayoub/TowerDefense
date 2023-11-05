package TowerDefense;

import TowerDefense.bullets.Projectile;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Turrets {
    protected int x, y, turretSize, range ,damage, cost, updateCost, height, width;
    protected double speed, accuracy;
    protected Enemy nearestEnemy = null;
    protected ImageView turretImage;
    protected GameInfo gameInfo;
    protected TimeLoop bulletInterval;
    protected String name;
    protected int level;

    public Turrets(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        this.level = 1;
        bulletInterval = new TimeLoop(gameInfo);
    }

    public abstract void updateTurret();

    public abstract Node drawTurret();

    public int getXTurret() {
        return x;
    }

    public int getYTurret() {
        return y;
    }

    public void setXTurret(int x) {
        this.x = x;
    }

    public void setYTurret(int y) {
        this.y = y;
    }

    public boolean nearEnemy(ArrayList<Enemy> enemyList){
        boolean fire = false;
        int tempRange = range;
        for (Enemy i : enemyList){
            int enemyX = i.getX();
            int enemyY = i.getY();
            double diffX = Math.abs(x - enemyX);
            double diffY = Math.abs(y- enemyY);
            double distance = Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
            if (distance < tempRange ) {
                nearestEnemy = i;
                tempRange = (int)distance;
                fire = true;
                break;
            }
            else {
                nearestEnemy = null;
            }
        }
        return fire;
    }

    public Circle drawRange() {
        Circle c = new Circle(range, Color.LIGHTGOLDENRODYELLOW);
        c.setMouseTransparent(true);
        c.relocate(getXTurret() - getRange() + (double)getWidth()/2, getYTurret()-getRange()+ (double)getHeight()*3/4);
        c.setOpacity(0.5);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(2000), new KeyValue(c.opacityProperty(), 0))
        );
        timeline.play();
        timeline.setOnFinished(actionEvent -> c.setRadius(0));

        return c;
    }

    public Circle drawRangeWithoutTimeline() {
        Circle c = new Circle(range, Color.TRANSPARENT); //LIGHTGOLDENRODYELLOW
        c.relocate(getXTurret() - getRange() + (double)getWidth()/2, getYTurret()-getRange()+ (double)getHeight()*3/4);
        c.setOpacity(0.5);
        return c;
    }

    public int getDamage(){
        return this.damage;
    }
    public int getEnemyX(){
        return nearestEnemy.getX();
    }
    public int getEnemyY(){
        return nearestEnemy.getY();
    }
    public int getEnemyDx(){
        return nearestEnemy.getDx();
    }
    public int getEnemyDy(){
        return nearestEnemy.getDy();
    }
    public TimeLoop getTimeLoop(){
        return this.bulletInterval;
    }
    public double getSpeed() {
        return (double) 10/speed;
    }
    public int getSize(){ return  this.turretSize;}
    public int getRange(){return this.range;}
    public int getCost(){ return this.cost; }
    public double getAccuracy(){
        return this.accuracy;
    }
    public int getUpdateCost(){ return this.updateCost;}
    public String getName() { return this.name; }                         // NEW
    public int getLevel(){ return this.level; }                           // NEW

    public void rotate(double angle){
        turretImage.setRotate(angle);
    }

    public void rotateTurret(){
        double angle;
        if (nearestEnemy != null){
            angle = Math.atan((double)-(getEnemyY()-getYTurret())/(double)(getEnemyX()-getXTurret()));
            angle = Math.toDegrees(angle);
            if (getEnemyX()-getXTurret() >= 0){
                angle = 90 - angle;
            }
            else if (getEnemyX()-getXTurret() < 0){
                angle = - 90 - angle;
            }
        }
        else {
            angle = 0;
        }

        rotate(angle);
    }

    public abstract void fire(ArrayList<Projectile> projectiles, Pane playLayer);

    public int getWidth(){
        return turretSize*3/2;
    }

    public int getHeight(){
        return turretSize*2;
    }

    public double getRealSpeed(){
        return speed;
    }

}
