package TowerDefense.effects;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Rain {
    private final Pane rainLayer = new Pane();
    private final Pane layer;
    private final GameInfo gameInfo;
    private ImageView[] rain = {new ImageView(new Image(Main.class.getResourceAsStream("resources/image/rain/rain0.png"))),
            new ImageView(new Image(Main.class.getResourceAsStream("resources/image/rain/rain1.png"))),
            new ImageView(new Image(Main.class.getResourceAsStream("resources/image/rain/rain2.png")))};

    public Rain(Pane layer, GameInfo gameInfo){
        this.layer = layer;
        this.gameInfo = gameInfo;
    }

    public void start(){
        generateImages();
        setup();
        changeRainImages();
    }

    private void generateImages(){
        for (ImageView i : rain){
            i.fitHeightProperty().bind(gameInfo.getRealWindowHeight());
            i.fitWidthProperty().bind(gameInfo.getRealWindowWidth());
            i.setSmooth(true);
        }
    }

    private void changeRainImages(){
        Timeline cycle = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (changeImage() == 0){
                    rainLayer.getChildren().clear();
                    rainLayer.getChildren().add(rain[0]);
                }
                else if(changeImage() == 1){
                    rainLayer.getChildren().clear();
                    rainLayer.getChildren().add(rain[1]);
                }
                else{
                    rainLayer.getChildren().clear();
                    rainLayer.getChildren().add(rain[2]);
                }
            }
        }));
        cycle.setCycleCount(Timeline.INDEFINITE);
        cycle.play();
    }

    private int changeImage(){
        if (rainLayer.getChildren().contains(rain[0])){
            return 1;
        }
        else if(rainLayer.getChildren().contains(rain[1]))
            return 2;
        else{
            return 0;
        }
    }

    private void setup(){
        rainLayer.getChildren().add(rain[0]);
        rainLayer.setMouseTransparent(true);
        rainLayer.setFocusTraversable(false);
        rainLayer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        rainLayer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());
        rainLayer.setOpacity(0.6);

        layer.getChildren().add(rainLayer);
    }
}
