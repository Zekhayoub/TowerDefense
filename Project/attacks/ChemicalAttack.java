package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Loader;
import TowerDefense.Time;
import TowerDefense.effects.OnHitEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ChemicalAttack extends Attack {
    private final int duration;
    private Timeline cycle;
    public ChemicalAttack(int x, int y, GameInfo gameInfo) {
        super(x, y, 100, 50, 100, gameInfo, 3);
        this.duration = 10;
        this.name = "Chemical Attack";
    }

    @Override
    public void launchAttack() {
        cycle = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Time.getStatus()){
                    dealDamage(damage/10);
                }
                else {
                    cycle.setCycleCount(cycle.getCycleCount()+1);
                }
            }
        }));
        cycle.setCycleCount(duration*10);
        cycle.setOnFinished(actionEvent -> gameInfo.removeAttack(this));
        cycle.play();
    }

    @Override
    public void attackAnimation(Pane layer) {
        onHitEffect = new OnHitEffect(x, y, 100, Loader.getSmoke(), layer, 20);
        onHitEffect.start(layer);
    }

    @Override
    public void stop() {
        onHitEffect.stop();
        cycle.stop();
    }

    private void dealDamage(int damage){
        for (Enemy i : gameInfo.getEnemyViewerList()){
            if (inRange(i)){
                i.dealDamage(damage);
            }
        }
    }
}
