package TowerDefense;

import TowerDefense.enemy.*;
import TowerDefense.maps.MultiPath;
import TowerDefense.maps.SinglePath;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class EnemyGenerator {
    private final GameInfo gameInfo;
    private final Pane playLayer, interfaceLayer;
    private final TimeLoop waveInterval;
    private final int tileSize;

    public EnemyGenerator(GameInfo gameInfo, Pane playLayer, Pane interfaceLayer, TimeLoop waveInterval){
        this.gameInfo = gameInfo;
        this.playLayer = playLayer;
        this.interfaceLayer = interfaceLayer;
        this.waveInterval = waveInterval;
        this.tileSize = gameInfo.getTileSize();
    }

    public Enemy generateEnemy(Map map, ArrayList<ArrayList<Integer>> moveCoordinates){
        Enemy enemy;
        if (map instanceof SinglePath){
            enemy = waveGenerator(((SinglePath) map).getStartX()*tileSize+ tileSize/2, ((SinglePath) map).getStartY()*tileSize+ tileSize/2, moveCoordinates);
        }
        else{
            if(new Random().nextInt(2)== 1){
                enemy = waveGenerator(((MultiPath) map).getStartX()[0]*tileSize + tileSize/2, ((MultiPath) map).getStartY()[0]*tileSize + tileSize/2, moveCoordinates);
            }
            else{
                enemy = waveGenerator(((MultiPath) map).getStartX()[1]*tileSize + tileSize/2, ((MultiPath) map).getStartY()[1]*tileSize+ tileSize/2, moveCoordinates);
            }
        }
        return enemy;
    }

    private Enemy waveGenerator(int x, int y, ArrayList<ArrayList<Integer>> moveCoordinates){
        Enemy enemy;

        if (gameInfo.getWaveNumber() == 1){ //WAVE 1
            if (new Random().nextInt(smallChance())== 0){
                enemy = new EatingEnemy(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(bigChance()) == 0){
                enemy = new FastEnemy(playLayer,x , y, gameInfo);
            }
            else {
                enemy = new ClassicEnemy(playLayer,x , y, gameInfo);
            }
        }

        else if (gameInfo.getWaveNumber() == 2){
            if (new Random().nextInt(smallChance())== 0){
                enemy = new EatingEnemy(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new RealZombie(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new HealingEnemy(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new ShortcutEnemy(playLayer,x , y, gameInfo, moveCoordinates.get(moveCoordinates.size()-1).get(0),moveCoordinates.get(moveCoordinates.size()-1).get(1));
            }
            else if (new Random().nextInt(bigChance()) == 0){
                enemy = new FastEnemy(playLayer,x , y, gameInfo);
            }
            else {
                enemy = new ClassicEnemy(playLayer,x , y, gameInfo);
            }
        }

        else if (gameInfo.getWaveNumber() == 3){
            if (new Random().nextInt(smallChance())== 0){
                enemy = new RealZombie(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new ShortcutEnemy(playLayer,x , y, gameInfo, moveCoordinates.get(moveCoordinates.size()-1).get(0),moveCoordinates.get(moveCoordinates.size()-1).get(1));
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new HealingEnemy(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(smallChance())== 0){
                enemy = new SpookyEnemy(playLayer,x , y, gameInfo, interfaceLayer);
            }
            else if (new Random().nextInt(bigChance()) == 0){
                enemy = new FastEnemy(playLayer,x , y, gameInfo);
            }
            else if (new Random().nextInt(bigChance())== 0){
                enemy = new EatingEnemy(playLayer,x , y, gameInfo);
            }
            else {
                enemy = new ClassicEnemy(playLayer,x , y, gameInfo);
            }
        }
        else{
            if(gameInfo.getStageNumber() == 5){
                enemy = new Boss(playLayer, x, y, gameInfo);
            }
            else {
                enemy = new BigEnemy(playLayer, x, y, gameInfo);
            }
            waveInterval.setWaiting(true);
        }
        return enemy;
    }

    private int bigChance(){
        return (int) Math.round(5/gameInfo.getGameDifficulty());
    }

    private int smallChance(){
        return (int) Math.round(25/gameInfo.getGameDifficulty());
    }
}