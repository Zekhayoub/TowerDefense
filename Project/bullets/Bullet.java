package TowerDefense.bullets;

import TowerDefense.GameInfo;
import TowerDefense.Turrets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends Projectile {
    private Turrets turret;

    public Bullet(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo, Turrets turret){
        super(layer, x, y, dx, dy, gameInfo);
        this.turret = turret;
    }

    public int getDamage(){
        return turret.getDamage();
    }

    @Override
    public void onHitEffect() {
    }

    @Override
    protected Node generateNode() {
        return new Circle(2, Color.YELLOW);
    }
}
