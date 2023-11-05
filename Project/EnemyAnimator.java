package TowerDefense;

import TowerDefense.enemy.FastEnemy;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class EnemyAnimator {
    private final ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private final ArrayList<ImageView> electrocutedList = new ArrayList<>();
    private final ArrayList<ImageView> deathAnimation = new ArrayList<>();
    private ImageView currentImage;
    private int size;
    private final DecimalFormat df = new DecimalFormat("0000");
    private boolean change = true;
    private boolean dead = false;
    private final int delayMillis;
    private Timeline cycle;

    public EnemyAnimator(){
        this.delayMillis = 25;
    }

    public ImageView getFirst(){
        return currentImage;
    }

    private void loadImages(Enemy enemy){
        if (enemy instanceof FastEnemy){
            for(Image i : Loader.getRunningZombie()){
                ImageView image = new ImageView(i);
                this.imageViewArrayList.add(image);
            }
        }
        else{
            for(Image i : Loader.getWalkingZombie()){
                ImageView image = new ImageView(i);
                this.imageViewArrayList.add(image);
            }
        }
        for(Image i : Loader.getElectrocutedZombie()){
            ImageView image = new ImageView(i);
            this.electrocutedList.add(image);
        }
        if (new Random().nextBoolean()){
            for (Image i : Loader.getDeath01()){
                ImageView image = new ImageView(i);
                this.deathAnimation.add(image);
            }
        }
        else {
            for (Image i : Loader.getDeath02()){
                ImageView image = new ImageView(i);
                this.deathAnimation.add(image);
            }
        }
    }

    public void start(Enemy enemy){
        size = enemy.getSize();
        loadImages(enemy);
        setupImages();
        changeImage(enemy);
        timer();
    }


    public void animateEnemy(Enemy enemy){
            changeImage(enemy);
            change = false;
    }

    private void setupImages(){
        for (ImageView i : imageViewArrayList){
            i.setFitHeight(size*8);
            i.setTranslateY(-size*3);
            i.setTranslateX(-size*3);
            i.setPreserveRatio(true);
            i.toFront();
            i.setMouseTransparent(true);
            i.setPickOnBounds(true);
        }
        for (ImageView i : electrocutedList){
            i.setFitHeight(size*8);
            i.setTranslateY(-size*3);
            i.setTranslateX(-size*3);
            i.setPreserveRatio(true);
            i.toFront();
            i.setMouseTransparent(true);
            i.setPickOnBounds(true);
        }
        for (ImageView i : deathAnimation){
            i.setFitHeight(size*8);
            i.setTranslateY(-size*3);
            i.setTranslateX(-size*3);
            i.setPreserveRatio(true);
            i.toFront();
            i.setMouseTransparent(true);
            i.setPickOnBounds(true);
        }
    }

    private void timer(){
        cycle = new Timeline(new KeyFrame(Duration.millis(delayMillis), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                change = true;
                if (dead){
                    cycle.setCycleCount(0);
                }
            }
        }));
        cycle.setCycleCount(Animation.INDEFINITE);
        cycle.play();
    }

    private void changeImage(Enemy enemy){
        if (!dead){
            if (enemy.getNode() == null || enemy.getNode() instanceof Circle || currentImage == imageViewArrayList.get(imageViewArrayList.size()-1)){
                currentImage = imageViewArrayList.get(0);
            }
            else {
                for (int i = 0; i < electrocutedList.size()-1; i ++){
                    if (currentImage == electrocutedList.get(i)){
                        currentImage = imageViewArrayList.get(0);
                        break;
                    }
                }
                for (int i = 0; i < imageViewArrayList.size()-1; i ++){
                    if (currentImage == imageViewArrayList.get(i)){
                        currentImage = imageViewArrayList.get(i+1);
                        break;
                    }
                }

            }
            if (enemy.isElectrocuted()){
                currentImage = electrocutedList.get(new Random().nextInt(4));
            }
        }
        else{
            for (int i = 0; i < imageViewArrayList.size()-1; i ++){
                if (currentImage == imageViewArrayList.get(i)){
                    currentImage = imageViewArrayList.get(i+1);
                    break;
                }
            }

        }

        currentImage.setRotate(enemy.enemyAngle());
        enemy.setNode(currentImage);
    }


    public boolean getChange(){
        return change;
    }

    public void deathAnimation(Enemy enemy, int frame){
        changeDeathAnimation(enemy, frame);
    }

    private void changeDeathAnimation(Enemy enemy, int frame){
        if (frame == 0){
            currentImage = deathAnimation.get(0);
        }
        else if (frame < deathAnimation.size() - 1){
            currentImage = deathAnimation.get(frame);
        }

        currentImage.relocate(enemy.getX(), enemy.getY());
        currentImage.setRotate(enemy.enemyAngle());
        enemy.setNode(currentImage);
    }

    public void changeSize(int size){
        this.size = size;
        setupImages();
    }

}
