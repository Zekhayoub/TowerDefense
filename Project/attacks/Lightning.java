package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Loader;
import TowerDefense.effects.OnHitEffect;
import javafx.scene.layout.Pane;

public class Lightning extends Attack {
    public Lightning(int x, int y, GameInfo gameInfo){
        super(x, y,50, 250, 200, gameInfo, 2);
        this.name = "Lightning";
    }

    @Override
    public void launchAttack() {
        for (Enemy i : gameInfo.getEnemyViewerList()){
            if (inRange(i)){
                i.dealDamage(damage);
            }
        }
    }

    @Override
    public void attackAnimation(Pane layer) {
        onHitEffect = new OnHitEffect(x, y, radius, Loader.getLightning(), layer);
        onHitEffect.start(layer);
    }

    @Override
    public void stop() {
        onHitEffect.stop();
    }
}
