package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Loader;
import TowerDefense.effects.OnHitEffect;
import javafx.scene.layout.Pane;

public class Explosion extends Attack{
    public Explosion(int x, int y, GameInfo gameInfo){
        super(x, y,100, 150, 100, gameInfo, 0);
        this.name = "Explosion";
        attackNumber = 0;
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
        onHitEffect = new OnHitEffect(x, y, 100, Loader.getExpl01(), layer);
        onHitEffect.start(layer);
    }

    @Override
    public void stop() {
        onHitEffect.stop();
    }


}
