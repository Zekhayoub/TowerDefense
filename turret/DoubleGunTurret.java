package TowerDefense.turret;

import TowerDefense.bullets.Bullet;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.Projectile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class DoubleGunTurret extends Turrets{

    public DoubleGunTurret(GameInfo gameInfo) {
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "Double S. Gun";

        this.speed = 0.8; //Speed = 10
        this.range = 100;
        this.damage = 50;
        this.accuracy = 8;
        this.cost = 250;
        this.updateCost = 100;
    }

    @Override
    public void updateTurret() {
        this.range += 10;
        this.speed -= speed/15;
        this.damage += 40;
        this.accuracy -= accuracy/25;
        this.updateCost *= 1.55;
        this.level += 1;
    }


    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/1.png"))); // NEW
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    private int firingX1() {
        return (int)Math.round((x+turretSize) + (7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }
    private int firingX2() {
        return (int)Math.round((x+turretSize) + (-7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }


    private int firingY1() {
        return (int)Math.round((y+turretSize) + (7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
    }
    private int firingY2() {
        return (int)Math.round((y+turretSize) + (-7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        Bullet bulletVieuw1 = new Bullet(playLayer, firingX1(), firingY1(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);
        Bullet bulletVieuw2 = new Bullet(playLayer, firingX2(), firingY2(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);

        projectiles.add(bulletVieuw1);
        projectiles.add(bulletVieuw2);
    }
}