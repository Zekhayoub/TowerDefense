package TowerDefense.threads;

import TowerDefense.Enemy;
import TowerDefense.Turrets;
import TowerDefense.bullets.Projectile;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class FireTurret implements Runnable{
    private final ArrayList<Turrets> onMapTurrets;
    private final ArrayList<Enemy> enemyViewerList;
    private final Pane playLayer;
    private final ArrayList<Projectile> projectiles;

    public FireTurret(ArrayList<Turrets> onMapTurrets, ArrayList<Enemy> enemyViewerList, Pane playLayer, ArrayList<Projectile> projectiles){
        this.onMapTurrets = onMapTurrets;
        this.enemyViewerList = enemyViewerList;
        this.playLayer = playLayer;
        this.projectiles = projectiles;
    }

    @Override
    public void run() {
        for (Turrets i : onMapTurrets){
            if(i.nearEnemy(enemyViewerList) && i.getTimeLoop().loopInterval(i.getRealSpeed())){
                i.fire(projectiles, playLayer);
            }
        }
    }
}
