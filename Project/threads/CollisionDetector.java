package TowerDefense.threads;

import TowerDefense.Enemy;
import TowerDefense.bullets.IceCube;
import TowerDefense.bullets.Projectile;

import java.util.ArrayList;
import java.util.Iterator;

public class CollisionDetector implements Runnable {
    private ArrayList<Enemy> enemyViewerList;
    private ArrayList<Projectile> projectiles;

    public CollisionDetector(ArrayList<Enemy> enemyViewerList, ArrayList<Projectile> projectiles){
        this.enemyViewerList = enemyViewerList;
        this.projectiles = projectiles;
    }

    @Override
    public void run() {
        for (Enemy j : enemyViewerList){
            Iterator<Projectile> iter = projectiles.iterator();
            while (iter.hasNext()){
                Projectile i = iter.next();
                int security = 2;
                if ( i.getX() <= j.getX() + 2 * j.getSize() + security && i.getY()<= j.getY() + 2 * j.getSize() + security
                        && i.getX() >= j.getX() -security && i.getY() >= j.getY() - security){
                    i.onHitEffect();
                    if (i instanceof IceCube){
                        j.freeze();
                    }
                    iter.remove();
                    i.removeFromLayer();
                    j.dealDamage(i.getDamage());
                }
            }
        }
    }
}
