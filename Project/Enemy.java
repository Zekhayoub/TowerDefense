package TowerDefense;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends Viewer{
    protected ProgressBar healthBar;
    protected int health, damage , speed, size, maxHealth, reward;
    protected GameInfo gameInfo;
    protected boolean electrocuted, frozen;
    protected EnemyAnimator animator;
    protected Circle differator = new Circle(10);

    public Enemy(Pane layer, int x, int y, int dx, int dy, int health, int damage, int size, GameInfo gameInfo, Color color, int reward){
        super(layer, x, y, dx, dy, gameInfo);
        this.health = health ;
        this.damage = damage;
        this.size = (int)Math.round(size* gameInfo.getScaling());
        this.speed = (int)Math.round(dx* gameInfo.getScaling());
        this.dx = 0;
        this.gameInfo = gameInfo;
        this.differator.setFill(color);
        setDifferatorStyle();
        animator = new EnemyAnimator();
        this.x = x - this.size;
        this.y = y - this.size;
        this.maxHealth = health;
        this.reward = reward;
        this.node = new Circle(size, Color.MEDIUMVIOLETRED);
    }

    protected void launchAnimator(){
        layer.getChildren().remove(node);
        animator.start(this);
        node = animator.getFirst();
        layer.getChildren().add(node);
    }

    public void animate(){
        if (animator.getChange() && !frozen){
            layer.getChildren().remove(node);
            animator.animateEnemy(this);
            layer.getChildren().add(node);
        }
    }

    protected void scale(GameInfo gameInfo){
        this.health = (int) (this.maxHealth * gameInfo.getGameDifficulty());
        this.maxHealth = (int) (this.maxHealth * gameInfo.getGameDifficulty());
        this.reward = (int) (this.reward * gameInfo.getGameDifficulty());
    }

    public int getSize(){
        return size;
    }

    public void dealDamage(int damage){
        this.health -= damage;
        refreshHealthBar();
    }

    public int getHealth(){
        return health;
    }

    public void changeTrajectory(ArrayList<ArrayList<Integer>> moveCoordinates) {
        double security = (double) speed/2;
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

    public int getDamage(){ return damage; }

    public ProgressBar getHealthBar() { return healthBar; }

    public void removeHealthBar(){
        layer.getChildren().remove(healthBar);
    }

    public void removeAll(){
        layer.getChildren().removeAll(node, healthBar, differator);
    }

    protected void setAutoSpeed(ArrayList<Integer> i, int first, int second){
        if (i.get(first) == 1){
            this.setDx(this.speed);
        }
        else if (i.get(first) == -1){
            this.setDx(-this.speed);
        }
        else{
            this.setDx(0);
        }

        if (i.get(second) == 1){
            this.setDy(this.speed);
        }
        else if (i.get(second) == -1){
            this.setDy(-this.speed);
        }
        else{
            this.setDy(0);
        }
    }


    protected ProgressBar generateHealthBar(){
        ProgressBar healthBar = new ProgressBar();
        healthBar.setProgress(1);
        healthBar.setStyle("-fx-accent: green;");
        healthBar.setPrefSize(size*2, 5);
        healthBar.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        return  healthBar;
    }

    protected void relocateAll(){
        healthBar.relocate(x,y-10);
        node.relocate(x,y);
        differator.relocate(x + (double) size/2 ,y + (double) size/2);
        if (!layer.getChildren().contains(healthBar)){
            layer.getChildren().add(healthBar);
            layer.getChildren().add(node);
            layer.getChildren().add(differator);
        }
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void move() {
        if (!electrocuted && !frozen){
            x += dx;
            y += dy;
        }
    }

    public void setElectrocuted(boolean electrocuted){
        this.electrocuted = electrocuted;
    }

    public boolean isElectrocuted() {
        return electrocuted;
    }

    public void setNode(Node node){
        this.node = node;
    }

    public int enemyAngle(){
        if(dx > 0){
            return 0;
        }
        else if (dx < 0){
            return 180;
        }
        else if(dy > 0){
            return 90;
        }
        else{
            return -90;
        }
    }

    public void heal(int HP){
        if (health + HP <= maxHealth){
            this.health += HP;
        }
        else if (health <= maxHealth){
            health = maxHealth;
        }
        refreshHealthBar();
    }

    private void refreshHealthBar(){
        double percentage = ((double)getHealth())/getMaxHealth();
        getHealthBar().setProgress(percentage);
        if (percentage >= 0.67){
            getHealthBar().setStyle("-fx-accent: green;");
        }
        else if (percentage > 0.34 && percentage < 0.67) {
            getHealthBar().setStyle("-fx-accent: orange;");
        } else if (percentage <= 0.34) {
            getHealthBar().setStyle("-fx-accent: red;");
        }
    }

    public void deathAnimation(){
        if (frozen){
            this.removeAll();
        }
        else {
            final int[] frame = {0};
            Enemy me = this;
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent -> {
                layer.getChildren().remove(node);
                animator.deathAnimation(me, frame[0]);
                frame[0]++;
                layer.getChildren().add(node);
            }));
            timeline.setCycleCount(17); //There are 17 frames
            timeline.setOnFinished(actionEvent -> me.removeAll());
            timeline.play();
        }

    }

    private void setDifferatorStyle(){
        differator.setOpacity(0.95);
        differator.setRadius((double)size/2);
        differator.setMouseTransparent(true);
        differator.setFocusTraversable(true);
    }

    public void freeze(){
        frozen = true;
    }

    public boolean isFrozen(){
        return frozen;
    }

    public int getReward(){
        return reward;
    }
}
