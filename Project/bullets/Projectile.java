package TowerDefense.bullets;

import TowerDefense.GameInfo;
import TowerDefense.Viewer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Projectile extends Viewer {
    public Projectile(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo) {
        super(layer, x, y, dx, dy, gameInfo);
        node = generateNode();
        layer.getChildren().add(node);
        node.relocate(x,y);
    }

    protected abstract Node generateNode();

    public abstract int  getDamage();

    public abstract void onHitEffect();
}
