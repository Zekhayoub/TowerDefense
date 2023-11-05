package TowerDefense.turret;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.Projectile;
import TowerDefense.effects.Electricity;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ElectricTurret extends Turrets {
    private final SimpleIntegerProperty enemyX = new SimpleIntegerProperty();
    private final SimpleIntegerProperty enemyY = new SimpleIntegerProperty();
    private final SimpleIntegerProperty startX = new SimpleIntegerProperty();
    private final SimpleIntegerProperty startY = new SimpleIntegerProperty();
    private boolean first = true;
    private boolean target;
    private final Electricity electricity;
    public ElectricTurret(GameInfo gameInfo, Pane playLayer) {
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.speed = 1.5; //Speed = 10
        this.range = 150;
        this.damage = 5;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "S. Taser";          // NEW

        this.cost = 1000;
        this.accuracy = 0;
        this.updateCost = 500;
        this.electricity = new Electricity(startX.getValue(), startY.getValue(), enemyX.getValue(), enemyY.getValue(), playLayer);
    }

    @Override
    public void updateTurret() {
        this.range += 25;
        this.speed -= speed/15;
        this.damage += 2;
        this.updateCost *= 1.55;
        this.level += 1;
    }

    public void electricity(){
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                electricity.refresh(startX.getValue(), startY.getValue(), enemyX.getValue(), enemyY.getValue());
                if (!target || electricity.isRunning() || nearestEnemy == null) {
                    if (target && electricity.isRunning() && nearestEnemy != null) {
                        nearestEnemy.setElectrocuted(true);
                        nearestEnemy.dealDamage(damage);
                    } else {
                        electricity.stop();
                    }
                }
                if (nearestEnemy != null && !electricity.isRunning()) {
                    nearestEnemy.setElectrocuted(false);
                }
            }
        };
        animationTimer.start();
    }

    @Override
    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/8.png")));
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        startY.set((int)Math.round((y+(double)height*3/4)));
        startX.set((int)Math.round((x+(double)width/2)));
        if (first){
            electricity();
            first = false;
        }
        if(!electricity.isRunning()){
            electricity.start();
        }

    }

    public void rotate(double angle){
        turretImage.setRotate(angle);

        if (nearestEnemy == null){
            target = false;
        }
        else{
            enemyX.set(nearestEnemy.getX() + nearestEnemy.getSize());
            enemyY.set(nearestEnemy.getY() + nearestEnemy.getSize());
            if (startX.intValue() != 0){
                target = true;
            }
        }
    }
}
