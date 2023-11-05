package TowerDefense.bullets;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Arrow extends Projectile {
    private Turrets turret;

    public Arrow(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo, Turrets turret) {
        super(layer, x, y, dx, dy, gameInfo);
        this.turret = turret;
        node.setRotate(Math.toDegrees(Math.atan((double)dy/(double)dx)));
    }

    @Override
    protected Node generateNode() {
        ImageView node = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/arrowcrossbow.png")));
        node.setFitHeight(5);
        node.setPreserveRatio(true);
        return node;
    }

    @Override
    public int getDamage() { return turret.getDamage(); }

    @Override
    public void onHitEffect() {

    }
}
