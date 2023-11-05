package TowerDefense.effects;

import TowerDefense.Loader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Transform;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Electricity {
    private final ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private Timeline cycle;
    private boolean running = false;
    private boolean first = true;
    private ImageView currentImage;
    private final DecimalFormat df = new DecimalFormat("00");
    private final int startX, startY, endX, endY;
    private final SimpleDoubleProperty diffX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty diffY = new SimpleDoubleProperty();
    private final SimpleIntegerProperty length = new SimpleIntegerProperty();
    private final SimpleDoubleProperty angle = new SimpleDoubleProperty();
    private final Glow glow = new Glow();
    private Pane layer;

    public Electricity(int startX, int startY, int endX, int endY, Pane layer){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.layer = layer;
        glow.setLevel(0.9);
        this.diffX.set(Math.abs(startX - endX));
        this.diffY.set(Math.abs(startY - endY));
        this.length.set((int) Math.round((Math.sqrt(Math.pow(diffX.intValue(),2)+Math.pow(diffY.intValue(),2)))));
        this.angle.set(Math.atan(diffY.doubleValue()/diffX.doubleValue()));
    }

    public void first(){
        loadImages();
        setupImages();
        currentImage = imageViewArrayList.get(0);
        timeChanger();
        first = false;
        running = true;
    }

    public void start(){
        if(first){
            first();
        }
        else{
            cycle.playFromStart();
        }
        running = true;
    }
    public void stop(){
        if (running){
            cycle.stop();
        }
        running = false;
        layer.getChildren().remove(currentImage);
    }

    public void refresh(int startX, int startY, int endX, int endY){
        double diffX = Math.abs(startX - endX);
        double diffY = Math.abs(startY - endY);
        this.length.set((int) Math.round((Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2)))));
        this.angle.set(Math.toDegrees(Math.atan(diffY/diffX)));
        if (endX-startX >= 0 && endY - startY < 0){
            angle.set(-angle.doubleValue());
        }
        else if (endX-startX < 0 && endY - startY <= 0){
            angle.set(angle.doubleValue()+180);
        }
        else if(endX-startX < 0 && endY - startY > 0){
            angle.set(180 -angle.doubleValue());
        }
        if (currentImage != null){
            currentImage.getTransforms().clear();
            currentImage.getTransforms().add(Transform.rotate(angle.doubleValue(),-0,length.doubleValue()/4));
            currentImage.setLayoutX(startX);
            currentImage.setEffect(glow);
            currentImage.setLayoutY(startY - (double)length.getValue()/4);
        }

    }

    private void timeChanger(){
        cycle = new Timeline(new KeyFrame(Duration.millis(25), event -> changeImage(layer)));
        cycle.setCycleCount(25);
        cycle.play();
        cycle.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layer.getChildren().remove(currentImage);
                running = false;
            }
        });
    }

    private void loadImages() {
        for (Image i : Loader.getElectricity()) {
            ImageView image = new ImageView(i);
            this.imageViewArrayList.add(image);
        }
    }
    private void setupImages() {
        for (ImageView i : imageViewArrayList) {
            i.fitWidthProperty().bind(length);
            i.setLayoutX(startX);
            i.toBack();
            i.setLayoutY(startY- (double)length.getValue()/4);
            i.setPreserveRatio(true);
            i.setMouseTransparent(true);
            i.setPickOnBounds(true);
        }
    }

    private void changeImage(Pane layer){
        if (currentImage == null){
            currentImage = imageViewArrayList.get(0);
            layer.getChildren().add(currentImage);
        }
        else if (currentImage == imageViewArrayList.get(imageViewArrayList.size()-1)){
            layer.getChildren().remove(currentImage);
            currentImage = null;
        }
        else {
            for (int i = 0; i < imageViewArrayList.size()-1; i ++){
                if (currentImage == imageViewArrayList.get(i)){
                    layer.getChildren().remove(currentImage);
                    currentImage = imageViewArrayList.get(i+1);
                    layer.getChildren().add(currentImage);
                    currentImage.toBack();
                    break;
                }
            }
        }
    }

    public boolean isRunning(){
        return running;
    }

    public ImageView getCurrentImage(){
        return currentImage;
    }
}
