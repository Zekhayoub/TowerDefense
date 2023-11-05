package TowerDefense.turret;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.Bullet;
import TowerDefense.bullets.Projectile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MachineGun extends Turrets {

    public MachineGun(GameInfo gameInfo) {
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "Machine S. Gun";

        this.speed = 0.4; //Speed = 10
        this.range = 100;
        this.damage = 30;
        this.accuracy = 8;
        this.cost = 300;
        this.updateCost = 125;
    }

    @Override
    public void updateTurret() {
        this.range += 20;
        this.speed -= speed/15;
        this.damage += 40;
        this.accuracy -= accuracy/25;
        this.updateCost *= 1.55;
        this.level += 1;
    }

    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/2.png"))); //NEW
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        Bullet bulletVieuw1 = new Bullet(playLayer, firingX(), firingY(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);

        projectiles.add(bulletVieuw1);
    }

    private int firingX() {
        return (int)Math.round((x+turretSize) + (7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }
    private int firingY() {
        return (int)Math.round((y+turretSize) + (7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
    }

}
