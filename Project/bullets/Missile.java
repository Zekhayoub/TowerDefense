package TowerDefense.bullets;

import TowerDefense.GameInfo;
import TowerDefense.Loader;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.attacks.Explosion;
import TowerDefense.effects.OnHitEffect;
import TowerDefense.turret.AOE;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Missile extends Projectile {
    private Turrets turret;
    private OnHitEffect onHitEffect;
    private Explosion explosion;
    public Missile(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo, Turrets turret) {
        super(layer, x, y, dx, dy, gameInfo);
        this.turret = turret;
        onHitEffect = new OnHitEffect(0, 0, ((AOE)turret).getRadius(), Loader.getExpl06(), layer);
        explosion = new Explosion(x, y, gameInfo);
    }

    @Override
    protected Node generateNode() {
        ImageView node = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/turrets/spr_missile.png")));
        node.setFitHeight(15);
        node.setPreserveRatio(true);
        node.setRotate(Math.toDegrees(Math.atan((double)dy/(double)dx)));
        return node;
    }

    public int getDamage(){
        return turret.getDamage();
    }

    @Override
    public void onHitEffect() {
        onHitEffect.relocate(x, y);
        onHitEffect.start(layer);
        explosion.launchAttack();
    }

}
