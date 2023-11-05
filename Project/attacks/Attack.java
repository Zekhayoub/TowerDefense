package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.effects.OnHitEffect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class Attack {
    protected int damage, x, y, radius, cost, level, attackNumber;
    protected Color radiusColor = Color.BLANCHEDALMOND;
    protected GameInfo gameInfo;
    protected OnHitEffect onHitEffect;
    protected String name;

    public Attack(int x, int y, int radius, int damage, int cost, GameInfo gameInfo, int attackNumber){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.damage = damage;
        this.cost = cost;
        this.gameInfo = gameInfo;
        this.attackNumber = attackNumber;
        this.level = gameInfo.getAttackLevel(attackNumber);
        scale();
    }

    public Shape drawRadius(int x, int y){
        Circle circle = new Circle(radius,Color.BLANCHEDALMOND);
        circle.relocate(x,y);
        return circle;
    }

    protected  void scale(){
        radius *= 1 + (level - 1)/10;
        damage *= 1 + (level - 1)/2;
        cost *= 1 + (level - 1)/2;
    }

    public int getRadius(){
        return radius;
    }

    public Color getRadiusColor() {
        return radiusColor;
    }

    public int getCost(){
        return cost;
    }
    public abstract void launchAttack();
    public abstract void attackAnimation(Pane layer);

    public void relocate(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected boolean inRange(Enemy enemy){
        double diffX = Math.abs(x - enemy.getX());
        double diffY = Math.abs(y - enemy.getY());
        double distance = Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
        return distance < radius;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public abstract void stop();

    public int getDamage() { return damage; }

    public void update(){
        gameInfo.upgradeAttack(attackNumber);
    }

    public int getAttackNumber(){
        return attackNumber;
    }

    public String getName() { return this.name; }

    public int getLevel(){ return this.level; }

    public void refresh(){
        this.level = gameInfo.getAttackLevel(attackNumber);
        scale();
    }
}
