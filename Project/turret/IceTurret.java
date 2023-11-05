package TowerDefense.turret;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.IceCube;
import TowerDefense.bullets.Projectile;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class IceTurret extends Turrets {
    public IceTurret(GameInfo gameInfo) {
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.name = "Ice Machine";

        this.speed = 1; //Speed = 10
        this.range = 80;
        this.damage = 50;
        this.accuracy = 8;
        this.cost = 750;
        this.updateCost = 300;
    }

    @Override
    public void updateTurret() {
        this.range += 25;
        this.speed -= speed/15;
        this.damage += 25;
        this.accuracy -= accuracy/25;
        this.updateCost *= 1.55;
        this.level += 1;
    }

    @Override
    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/7.png")));
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        IceCube iceCubes = new IceCube(playLayer, firingX(), firingY(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);

        projectiles.add(iceCubes);
    }

    private int firingX() {
        return (int)Math.round((x+turretSize) + (7)*Math.cos(turretImage.getRotate()) - (-5)*Math.sin(turretImage.getRotate()));
    }
    private int firingY() {
        return (int)Math.round((y+turretSize) + (7)*Math.sin(turretImage.getRotate()) + (-5)*Math.cos(turretImage.getRotate()));
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
            if (distance < tempRange && !i.isFrozen() ) {
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
}
