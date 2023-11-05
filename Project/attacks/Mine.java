package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Loader;
import TowerDefense.Main;
import TowerDefense.effects.OnHitEffect;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class Mine extends Attack {
    private final ImageView mineOn = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/mineOn.png")));
    private final ImageView mineOff = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/mineOff.png")));
    private final int outerRadius;
    private final int explosionDelay;
    private Timeline attackAnimation, attackDamage;
    public Mine(int x, int y, GameInfo gameInfo) {
        super(x, y, 100, 100, 75, gameInfo, 1);
        outerRadius = radius * 2;
        explosionDelay = 750;
        setupImages();
        this.name = "Mine";
    }

    @Override
    public void launchAttack() {
        attackDamage = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            if (activateMine()){
                attackDamage.stop();
                Timeline explosion = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        dealDamage(damage);
                    }
                }));
                explosion.setDelay(Duration.millis(explosionDelay));
                explosion.play();
            }
        }));
        attackDamage.setOnFinished(actionEvent -> gameInfo.removeAttack(this));
        attackDamage.setCycleCount(Animation.INDEFINITE);
        attackDamage.play();
    }

    @Override
    public void attackAnimation(Pane layer) {
        mineOn.relocate(x-mineOn.getFitHeight()/2,y-mineOn.getFitHeight()/2);
        mineOff.relocate(x-mineOn.getFitHeight()/2,y-mineOn.getFitHeight()/2);
        layer.getChildren().add(mineOff);
        attackAnimation = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (inOuterRange() && !activateMine()){
                    if (layer.getChildren().contains(mineOff)){
                        layer.getChildren().remove(mineOff);
                        layer.getChildren().add(mineOn);
                    }
                    else{
                        layer.getChildren().remove(mineOn);
                        layer.getChildren().add(mineOff);
                    }
                }
                else if (activateMine()){
                    if (layer.getChildren().contains(mineOff)){
                        layer.getChildren().remove(mineOff);
                        layer.getChildren().add(mineOn);
                    }
                    Timeline explosion = new Timeline(new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            OnHitEffect onHitEffect = new OnHitEffect(x,y,radius, Loader.getExpl01(), layer);
                            onHitEffect.start(layer);
                        }
                    }));
                    explosion.setDelay(Duration.millis(explosionDelay-250));
                    explosion.setOnFinished(actionEvent1 -> layer.getChildren().remove(mineOn));
                    explosion.play();
                    attackAnimation.stop();
                }
            }
        }));
        attackAnimation.setCycleCount(Animation.INDEFINITE);
        attackAnimation.setOnFinished(actionEvent -> gameInfo.removeAttack(this));
        attackAnimation.play();
    }

    @Override
    public void stop() {

    }


    private void dealDamage(int damage){
        for (Enemy i : gameInfo.getEnemyViewerList()){
            if (inRange(i)){
                i.dealDamage(damage);
            }
        }
    }

    private void setupImages(){
        mineOff.setFitHeight(25);
        mineOff.setPreserveRatio(true);
        mineOff.toFront();
        mineOff.setMouseTransparent(true);
        mineOff.setPickOnBounds(true);

        mineOn.setFitHeight(25);
        mineOn.setPreserveRatio(true);
        mineOn.toFront();
        mineOn.setMouseTransparent(true);
        mineOn.setPickOnBounds(true);
    }

    private boolean inOuterRange(){
        boolean bool = false;
        for (Enemy enemy : gameInfo.getEnemyViewerList()){
            double diffX = Math.abs(x - enemy.getX());
            double diffY = Math.abs(y - enemy.getY());
            double distance = Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
            if (distance < outerRadius){
                bool = true;
                break;
            }
        }
        return bool;
    }

    private boolean activateMine(){
        boolean activate = false;
        for (Enemy enemy : gameInfo.getEnemyViewerList()){
            if (inRange(enemy)){
                activate = true;
                break;
            }
        }
        return activate;
    }
}
