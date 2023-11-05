package TowerDefense.turret;

import TowerDefense.bullets.Bullet;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.bullets.Projectile;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class SniperTurret extends Turrets {
    private final SimpleIntegerProperty enemyX = new SimpleIntegerProperty();
    private final SimpleIntegerProperty enemyY = new SimpleIntegerProperty();
    private final SimpleIntegerProperty startX = new SimpleIntegerProperty();
    private final SimpleIntegerProperty startY = new SimpleIntegerProperty();
    private final SimpleBooleanProperty show = new SimpleBooleanProperty();
    private final Pane playLayer;

    public SniperTurret(GameInfo gameInfo, Pane playLayer){
        super(gameInfo);
        this.x = 0;
        this.y = 0;
        this.turretSize = 25;
        this.height = 2*turretSize;
        this.width = height*3/4;
        this.playLayer = playLayer;
        this.name = "Sniper";

        this.speed = 2; //Speed = 10
        this.range = 300;
        this.damage = 150;
        this.accuracy = 12;
        this.cost = 400;
        this.updateCost = 200;
    }

    @Override
    public void updateTurret() {
        this.speed -= speed/7;
        this.damage += 50;
        this.updateCost *= 1.55;
        this.level += 1;
    }

    @Override
    public Node drawTurret() {
        turretImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/4.png")));
        turretImage.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(height).divide(1200));
        turretImage.setPreserveRatio(true);
        System.out.println(width);
        turretImage.relocate(x + (double)width/2 - (double)turretSize/2, y + (double)height*3/4- (double)turretSize*3/4);
        return turretImage;
    }

    public void drawLine(){
        Line line = new Line();
        line.setStroke(Color.RED);
        line.endXProperty().bind(enemyX);
        line.endYProperty().bind(enemyY);
        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.visibleProperty().bind(show);
        line.setOpacity(0.7);

        //Instantiating the Glow class
        Glow glow = new Glow();
        glow.setLevel(0.9);

        line.setEffect(glow);
        playLayer.getChildren().add(line);
    }

    @Override
    public void fire(ArrayList<Projectile> projectiles, Pane playLayer) {
        Bullet bullet = new Bullet(playLayer, firingX1(), firingY1(),
                (int) ((getEnemyX()- getXTurret())/getAccuracy() + getEnemyDx()),
                (int) ((getEnemyY()- getYTurret())/getAccuracy() + getEnemyDy()), gameInfo, this);
        projectiles.add(bullet);
    }


    private int firingX1() {
        startX.set(x+width/2);
        return (x+width/2);
    }
    private int firingY1() {
        startY.set(y+height*3/4);
        return (y+height*3/4);
    }

    public void rotate(double angle){
        turretImage.setRotate(angle);
        if (nearestEnemy != null){
            enemyX.set(nearestEnemy.getX() + nearestEnemy.getSize());
            enemyY.set(nearestEnemy.getY() + nearestEnemy.getSize());
            if (startX.intValue() != 0){
                show.set(true);
            }
        }
        else {
            show.set(false);
        }

    }
}
