package TowerDefense.turret;

import TowerDefense.*;
import TowerDefense.bullets.Bullet;
import TowerDefense.bullets.Projectile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ShotgunTurret extends Turrets {
    private int numberOfShots = 3;

    public ShotgunTurret(GameInfo gameInfo){
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "Shotgun";

        this.speed = 1; //Speed = 10
        this.range = 75;
        this.damage = 50;
        this.accuracy = 8;
        this.cost = 350;
        this.updateCost = 200;
    }

    @Override
    public void updateTurret() {
        this.range += 5;
        this.speed -= speed/15;
        this.damage += 40;
        this.accuracy -= accuracy/25;
        this.updateCost *= 1.55;
        this.level += 1;
        this.numberOfShots += 2;
    }
    @Override
    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/3.png")));
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        System.out.println(width);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(playLayer, firingX1(), firingY1(), //center
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this));
        for (int i = 1; i <= numberOfShots-1; i +=2){
            bullets.add(new Bullet(playLayer, firingX1(), firingY1(),
                            (int) Math.round((getEnemyX()- getXTurret()+ getSize())/getAccuracy() + getEnemyDx() + Math.sin(turretImage.getRotate())*i),
                            (int) Math.round((getEnemyY()- getYTurret()+ getSize())/getAccuracy() + getEnemyDy()+ Math.cos(turretImage.getRotate())*i),
                            gameInfo, this));
            bullets.add(new Bullet(playLayer, firingX1(), firingY1(),
                    (int) Math.round((getEnemyX()- getXTurret()+ getSize())/getAccuracy() + getEnemyDx() - Math.sin(turretImage.getRotate())*i),
                    (int) Math.round((getEnemyY()- getYTurret()+ getSize())/getAccuracy() + getEnemyDy()- Math.cos(turretImage.getRotate())*i),
                    gameInfo, this));
        }
        projectiles.addAll(bullets);
    }

    private int firingX1() {
        return (int)Math.round((x+turretSize) + (7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }
    private int firingY1() {
        return (int)Math.round((y+turretSize) + (7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
    }
}
