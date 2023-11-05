package TowerDefense;

import TowerDefense.attacks.Attack;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameInfo {
    private int waveNumber, stageNumber, gold , life, mapNumber, progress;
    private final SimpleIntegerProperty  tileSize = new SimpleIntegerProperty();
    private final SimpleIntegerProperty windowWidth = new SimpleIntegerProperty();
    private final SimpleIntegerProperty windowHeight = new SimpleIntegerProperty();
    private double gameDifficulty;
    private Stage stage;
    private ArrayList<Enemy> enemyViewerList;
    private final ArrayList<Attack> attackList = new ArrayList<>();
    private int[] attackLevel;

    public GameInfo (){
        this.waveNumber = 0;
        this.stageNumber = 1;
        this.gameDifficulty = 1;
        this.gold = 750;
        this.life = 100;
        this.tileSize.set(50);
        this.windowWidth.set(1200);
        this.windowHeight.set(800);
        this.attackLevel = new int[]{1,1,1,1,1,1,1};
        this.progress = 1;
    }

    public void updateGameDifficulty(){
        int i =  waveNumber + (stageNumber - 1)*2 + (mapNumber - 1)*2;
        this.gameDifficulty = Math.pow(1.1,i);
    }

    public void updateWaveNumber(){
        if (waveNumber == 4){
            this.stageNumber ++;
            this.waveNumber =1;
        }
        else {this.waveNumber ++;}

    }
    public boolean canBuy( int gold){
        return gold <= this.gold;
    }
    public void reset(){
        this.waveNumber = 0;
        this.stageNumber = 1;
        this.gameDifficulty = 1;
        this.life = 100;
        stopAttacks();
    }
    public void buyTurret(int gold){
        this.gold -= gold;
    }
    public int getWaveNumber(){
        return this.waveNumber;
    }
    public void updateStageNumber(){
        stageNumber ++;
    }
    public int getStageNumber(){
        return stageNumber;
    }

    public double getGameDifficulty(){ return gameDifficulty; }
    public void addGold(int gold){
        this.gold += gold;
    }
    public int getGold(){
        return gold;
    }
    public void loseLife(int life){
        this.life -= life;
    }
    public int getLife(){
        return life;
    }
    public int getWindowWidth(){return windowWidth.intValue();}
    public int getWindowHeight(){return  windowHeight.intValue();}
    public int getTileSize(){
        tileSize.set(windowHeight.divide(16).get());
        return  tileSize.getValue();}

    public void resetWaveNumber(){
        this.waveNumber = 0;
    }
    public void setWindowWidth(int windowWidth){
        this.windowWidth.set(windowWidth);
        stage.setWidth(windowWidth);
    }
    public void setGold(int gold){
        this.gold = gold;
    }

    public void setWindowHeight(int windowHeight){
        this.windowHeight.set(windowHeight);
        stage.setHeight(windowHeight);
    }

    public SimpleIntegerProperty getRealWindowWidth(){
        return windowWidth;
    }
    public SimpleIntegerProperty getRealWindowHeight(){
        return windowHeight;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage(){
        return stage;
    }

    public int getMargin(){
        return (windowWidth.intValue()-tileSize.intValue()*24)/2;
    }

    public double getScaling(){
        return windowHeight.doubleValue() / 800.00;
    }

    public void setEnemyViewerList(ArrayList<Enemy> enemyViewerList){
        this.enemyViewerList = enemyViewerList;
    }
    public ArrayList<Enemy> getEnemyViewerList(){
        return enemyViewerList;
    }

    public void addEnemy(Enemy enemy){
        enemyViewerList.add(enemy);
    }

    public void addAttack(Attack attack){
        attackList.add(attack);
    }

    public void removeAttack(Attack attack){
        attackList.remove(attack);
    }

    private void stopAttacks(){
        for (Attack i : attackList){
            i.stop();
        }
    }

    public void setMapNumber(int mapNumber){
        this.mapNumber = mapNumber;
    }

    public int getAttackLevel(int attackNumber){
        return attackLevel[attackNumber];
    }

    public void upgradeAttack(int attackNumber){
        attackLevel[attackNumber] += 1;
    }

    public void nextLevel(){
        progress += 1;
    }

    public int getProgress(){
        return progress;
    }

    public int[] getAttackLevelList(){
        return attackLevel;
    }

    public void setAttackLevel(int attackNumber, int attackLevel){
        this.attackLevel[attackNumber] = attackLevel;
    }

    public void setProgress(int progress){
        this.progress = progress;
    }
}
