package TowerDefense.effects;

import TowerDefense.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class OnHitEffect {
    private final ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private ImageView currentImage;
    private int x;
    private int y;
    private final int size;
    private int duration;
    private int frameNumber;
    private Timeline cycle;
    private Pane layer;

    public OnHitEffect(int x, int y, int radius, ArrayList<Image> imageViewArrayList, Pane layer){
        this.x = x;
        this.y = y;
        this.size = radius*2;
        this.layer = layer;
        for(Image i : imageViewArrayList){
            ImageView image = new ImageView(i);
            this.imageViewArrayList.add(image);
        }
    }

    public OnHitEffect(int x, int y, int radius, ArrayList<Image> imageViewArrayList, Pane layer, int duration){
        this.x = x;
        this.y = y;
        this.size = radius*2;
        this.duration = duration;
        this.layer = layer;
        for(Image i : imageViewArrayList){
            ImageView image = new ImageView(i);
            this.imageViewArrayList.add(image);
        }
    }

    public void start(Pane layer){
        setupImages();
        currentImage = imageViewArrayList.get(0);
        layer.getChildren().add(currentImage);
        frameNumber = 0;
        timeChanger(layer);
    }

    private void timeChanger(Pane layer){
        cycle = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Time.getStatus()){
                    changeImage(layer);
                }
                else {
                    cycle.setCycleCount(cycle.getCycleCount()+1);
                }
            }
        }));
        if (duration == 0){
            cycle.setCycleCount(imageViewArrayList.size());
        }
        else{
            cycle.setCycleCount(duration*1000/20);
        }

        cycle.play();
        cycle.setOnFinished(actionEvent -> layer.getChildren().remove(currentImage));
    }

    private void changeImage(Pane layer){
        if (frameNumber < imageViewArrayList.size() - 1){
            frameNumber ++;
        }
        else {
            frameNumber = 0;
        }
        layer.getChildren().remove(currentImage);
        currentImage = imageViewArrayList.get(frameNumber);
        layer.getChildren().add(currentImage);
    }

    public void relocate(int x, int y){
        this.x = x;
        this.y = y;
        for (ImageView i : imageViewArrayList){
            i.relocate(x,y);
        }
    }

    private void setupImages(){
        Glow glow = new Glow();
        glow.setLevel(0.8);
        for (ImageView i : imageViewArrayList){
            i.setFitHeight(size);
            i.relocate(x - (double) size/2,y - (double) size/2);
            i.setPreserveRatio(true);
            i.toFront();
            i.setMouseTransparent(true);
            i.setPickOnBounds(true);
            i.setEffect(glow);
        }
    }

    public void stop(){
        cycle.stop();
        layer.getChildren().remove(currentImage);
    }
}
