package TowerDefense;

import TowerDefense.UI.UserInterface;
import TowerDefense.bullets.Projectile;
import TowerDefense.clearScreen.ClearScreen;
import TowerDefense.enemy.Boss;
import TowerDefense.gameOver.GameOver;
import TowerDefense.menu.Menu;
import TowerDefense.threads.CollisionDetector;
import TowerDefense.threads.FireTurret;
import TowerDefense.threads.HealthChecker;
import TowerDefense.threads.TurretSpawner;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameLoop {
    private final TimeLoop enemyInterval;
    private final TimeLoop waveInterval;
    private final TimeLoop stageInterval;
    private final GameInfo gameInfo;
    private final Pane playLayer;
    private final Pane interfaceLayer;
    private final ArrayList<Enemy> enemyViewerList = new ArrayList<>();
    private final ArrayList<Projectile> projectiles = new ArrayList<>();
    private final ArrayList<Turrets> turretList;
    private final ArrayList<Turrets> onMapTurrets;
    private final UserInterface userInterface;
    private final BorderControl borderControl;
    private final EnemyGenerator enemyGenerator;
    private final TextViewer textViewer;
    private final ArrayList<ArrayList<Integer>> moveCoordinates;
    private final Menu menu;
    private final ArrayList<Boss> bossList = new ArrayList<>();
    private AnimationTimer animationTimer;
    private Map map;
    private EnemyAnimator enemyAnimator;

    public GameLoop(GameInfo gameInfo, Pane playLayer, Pane interfaceLayer,
                    ArrayList<Turrets> turretList, ArrayList<Turrets> onMapTurrets, UserInterface userInterface,
                    ArrayList<ArrayList<Integer>> moveCoordinates, TextViewer textViewer,
                    Menu menu, Map map){
        this.gameInfo = gameInfo;
        this.playLayer = playLayer;
        this.interfaceLayer = interfaceLayer;
        this.turretList = turretList;
        this.onMapTurrets = onMapTurrets;
        this.userInterface  = userInterface;
        this.borderControl = new BorderControl(projectiles, enemyViewerList,gameInfo);
        this.textViewer = textViewer;
        this.moveCoordinates = moveCoordinates;
        this.menu = menu;
        this.map = map;
        this.enemyAnimator = new EnemyAnimator();
        this.enemyInterval = new TimeLoop(gameInfo);
        this.waveInterval = new TimeLoop(gameInfo);
        this.stageInterval = new TimeLoop(gameInfo);
        this.enemyGenerator = new EnemyGenerator(gameInfo, playLayer, interfaceLayer, waveInterval);
        gameInfo.setEnemyViewerList(enemyViewerList);

    }

    public void gameLoop(){
        animationTimer = new AnimationTimer() {
            public void handle(long l) {
                Time.update();
                if (Time.getStatus()){
                    spawnEnemy();
                    launchAnimations();
                    refreshText();
                    moveViewers();
                    clearLevel(this);
                    gameOver(this);
                }
            }
        };
        animationTimer.start();
    }

    private void spawnEnemy(){
        if (stageInterval.stageLoop(15, waveInterval) && bossList.size() == 0){
            textViewer.get(3).setText("Wave in progress");
            if(waveInterval.waveLoop(3.5,6)){
                if(enemyInterval.enemyInterval(1.2)){
                    Enemy enemy = enemyGenerator.generateEnemy(map, moveCoordinates);
                    enemyViewerList.add(enemy);
                    if (enemy instanceof Boss){
                        bossList.add((Boss) enemy);
                    }
                }
            }
        }
        else{ textViewer.get(3).setText("Next Stage in " + stageInterval.getTimeLeft() + " seconds"); //NEW
        }
    }

    public void stop(){
        animationTimer.stop();
    }

    private void gameOver(AnimationTimer animationTimer){
        if (gameInfo.getLife() <= 0){
            animationTimer.stop();
            GameOver gameOver = new GameOver(gameInfo);
            Pane gameOverLayer = gameOver.initGameOver(menu);
            interfaceLayer.getChildren().clear();
            playLayer.getChildren().add(gameOverLayer);
            playLayer.requestFocus();
        }
    }

    private void clearLevel(AnimationTimer animationTimer) {
        if (bossList.size() == 1) {
            if (!bossList.get(0).isAlive()) {
                animationTimer.stop();
                ClearScreen clearScreen = new ClearScreen(gameInfo);
                Pane clearScreenLayer = clearScreen.initClearScreen(menu);
                interfaceLayer.getChildren().clear();
                playLayer.getChildren().clear();
                playLayer.getChildren().add(clearScreenLayer);
                playLayer.requestFocus();
                gameInfo.nextLevel();
            }
        }
    }

    private void launchAnimations(){
        new TurretSpawner(playLayer, turretList, onMapTurrets, userInterface).run();
        new CollisionDetector(enemyViewerList, projectiles).run();
        new HealthChecker(enemyViewerList, gameInfo).run();
        new FireTurret(onMapTurrets, enemyViewerList, playLayer, projectiles).run();
        borderControl.run();
        for (Turrets i : onMapTurrets){
            i.rotateTurret();
        }
        userInterface.getUpdateButton().refreshUpBut();
    }

    private void refreshText(){
        textViewer.get(0).setText(gameInfo.getStageNumber() + " - " + gameInfo.getWaveNumber());  // NEW
        textViewer.get(1).setText("" + gameInfo.getGold());  // NEW
        textViewer.get(2).setText("" + gameInfo.getLife());  // NEW
    }

    private void moveViewers(){
        //Move the object in the database and on the scene
        for( Enemy i: enemyViewerList) {
            i.move();
            i.animate();
            i.relocateAll();
            i.changeTrajectory(this.moveCoordinates);
        }
        for( Viewer i: projectiles) {
            i.move();
            i.getNode().relocate(i.getX(),i.getY());
        }
    }
}
