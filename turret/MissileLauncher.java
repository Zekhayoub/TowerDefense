package TowerDefense.turret;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.Missile;
import TowerDefense.bullets.Projectile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MissileLauncher extends Turrets implements AOE {
    private int radius;
    private int blastDamage;
    public MissileLauncher(GameInfo gameInfo){
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "Missile Launcher";

        this.speed = 0.8; //Speed = 10
        this.range = 125;
        this.damage = 200;
        this.accuracy = 8;
        this.cost = 550;
        this.updateCost = 300;
        this.radius = 80;
        this.blastDamage = 150;
    }

    @Override
    public void updateTurret() {
        this.range += 10;
        this.speed -= speed/15;
        this.damage += 100;
        this.radius += 5;
        this.blastDamage += 75;
        this.accuracy -= accuracy/25;
        this.updateCost *= 1.55;
        this.level += 1;
    }

    @Override
    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/6.png")));
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        System.out.println(width);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        Missile missile = new Missile(playLayer, firingX1(), firingY1(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);
        projectiles.add(missile);
    }

    private int firingX1() {
        return (int)Math.round((x+turretSize) + (7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }
    private int firingY1() {
        return (int)Math.round((y+turretSize) + (7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getBlastDamage() {
        return blastDamage;
    }
}
