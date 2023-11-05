package TowerDefense.threads;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.enemy.Boss;
import TowerDefense.enemy.RealZombie;

import java.util.ArrayList;
import java.util.Iterator;

public class HealthChecker implements Runnable {
    private final ArrayList<Enemy> enemyViewerList;
    private final GameInfo gameInfo;

    public HealthChecker(ArrayList<Enemy> enemyViewerList, GameInfo gameInfo){
        this.enemyViewerList = enemyViewerList;
        this.gameInfo = gameInfo;
    }

    @Override
    public void run() {
        Iterator<Enemy> iter = enemyViewerList.iterator();
        while (iter.hasNext()){
            Enemy enemy = iter.next();
            if (enemy.getHealth() <= 0){
                iter.remove();
                enemy.removeHealthBar();
                enemy.deathAnimation();
                gameInfo.addGold(enemy.getReward());
                if (enemy instanceof RealZombie){
                    ((RealZombie) enemy).respawn();
                }
                else if(enemy instanceof Boss){
                    ((Boss) enemy).isDead();
                }
            }
        }
    }
}
