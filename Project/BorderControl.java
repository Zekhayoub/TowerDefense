package TowerDefense;

import TowerDefense.bullets.Projectile;
import TowerDefense.enemy.SpookyEnemy;

import java.util.ArrayList;
import java.util.Iterator;

public class BorderControl implements Runnable{
    private final ArrayList<Projectile> projectiles;
    private final ArrayList<Enemy> enemyViewerList;
    private final int windowWidth;
    private final int windowHeight;
    private int tileSize;
    private final GameInfo gameInfo;

    public BorderControl(ArrayList<Projectile> projectiles, ArrayList<Enemy> enemyViewerList, GameInfo gameInfo){
        this.projectiles = projectiles;
        this.enemyViewerList = enemyViewerList;
        this.windowHeight = gameInfo.getWindowHeight();
        this.windowWidth = gameInfo.getWindowWidth();
        this.gameInfo = gameInfo;
        this.tileSize = gameInfo.getTileSize();
    }

    public void cleanWindowEnemy(){
        Iterator iter = enemyViewerList.iterator();

        while( iter.hasNext()) {
            Enemy enemy = (Enemy) iter.next();
            boolean remove = false;
            int size = enemy.getSize();
            if( enemy.getX() + size > windowWidth + tileSize + enemy.getSize()) {
                System.out.println("here it is");
                remove = true;
            }
            else if( enemy.getX() + size < - enemy.getSize() - tileSize) {
                remove = true;
            }
            else if( enemy.getY() + size > windowHeight + tileSize + enemy.getSize()) {
                remove = true;
            }
            else if( enemy.getY() + size < - enemy.getSize() - tileSize) {
                remove = true;
            }

            if (remove){
                if (enemy instanceof SpookyEnemy){
                    ((SpookyEnemy) enemy).launchSpookyTime();
                }
                iter.remove();
                enemy.removeAll();
                gameInfo.loseLife(enemy.getDamage());
            }
        }
    }

    public void cleanWindowBullet(){
        Iterator iter = projectiles.iterator();
        while( iter.hasNext()) {
            Viewer nextBulletViewer = (Viewer) iter.next();
            if( nextBulletViewer.getX() > windowWidth) {
                iter.remove();
                nextBulletViewer.removeFromLayer();
                break;
            }
            if( nextBulletViewer.getX() < 0) {
                iter.remove();
                nextBulletViewer.removeFromLayer();
                break;
            }
            if( nextBulletViewer.getY() > windowHeight) {
                iter.remove();
                nextBulletViewer.removeFromLayer();
                break;
            }
            if( nextBulletViewer.getY() < 0) {
                iter.remove();
                nextBulletViewer.removeFromLayer();
                break;
            }
            if(nextBulletViewer.getDx() == 0 && nextBulletViewer.getDy() == 0){
                iter.remove();
                nextBulletViewer.removeFromLayer();
                break;
            }
        }
    }

    @Override
    public void run() {
        cleanWindowBullet();
        cleanWindowEnemy();
    }
}