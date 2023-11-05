package TowerDefense.bullets;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class IceCube extends Projectile {
    private final Turrets turrets;
    public IceCube(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo, Turrets turrets) {
        super(layer, x, y, dx, dy, gameInfo);
        this.turrets = turrets;
    }

    @Override
    protected Node generateNode() {
        ImageView node = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/iceCube.png")));
        node.setFitHeight(15);
        node.setPreserveRatio(true);
        node.setRotate(Math.toDegrees(Math.atan((double)dy/(double)dx)));
        return node;
    }

    @Override
    public int getDamage() {
        return turrets.getDamage();
    }

    @Override
    public void onHitEffect() {
    }
}
