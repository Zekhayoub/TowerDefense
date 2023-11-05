package TowerDefense.enemy;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class SpookyEnemy extends Enemy {
    private ImageView freddy;
    private AudioClip sound;
    private final Pane interfaceLayer;
    public SpookyEnemy(Pane layer, int x, int y, GameInfo gameInfo, Pane interfaceLayer) {
        super(layer, x, y, 4, 0, 100, 0, 20, gameInfo, Color.BLACK, 300); //Spooky
        this.interfaceLayer = interfaceLayer;

        healthBar = generateHealthBar();
        scale(gameInfo);
        relocateAll();
        launchAnimator();
    }

    private void generateSpookyLayer(){
        freddy = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/jumpScare.gif")));
        freddy.setFitWidth(gameInfo.getWindowWidth());
        freddy.setPreserveRatio(true);
        interfaceLayer.getChildren().add(freddy);
    }

    private void generateSpookySound(){
        sound = new AudioClip(Main.class.getResource("resources/sound/scream.mp3").toString());
        sound.setVolume(0.3);
        sound.play();
    }

    public void launchSpookyTime(){
        generateSpookyLayer();
        generateSpookySound();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), actionEvent -> {
        }));
        timeline.setCycleCount(1);
        timeline.setOnFinished(actionEvent -> {
            sound.stop();
            interfaceLayer.getChildren().remove(freddy);
            gameInfo.loseLife(gameInfo.getLife());
        });
        timeline.play();
    }
}
