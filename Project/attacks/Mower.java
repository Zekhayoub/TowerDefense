package TowerDefense.attacks;

import TowerDefense.Enemy;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Viewer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Mower extends Attack {
    private Viewer mower;
    private Timeline animation, killer;
    public Mower(int x, int y,GameInfo gameInfo) {
        super(x, y, 0, 0, 1000, gameInfo, 4);
        this.name = "Mower";
    }

    public Shape drawRadius(int x,int y){
        Rectangle rectangle = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getTileSize());
        rectangle.relocate(0, y);
        rectangle.setFill(Color.BLANCHEDALMOND);
        rectangle.setFocusTraversable(true);
        rectangle.setMouseTransparent(true);
        rectangle.toBack();
        return rectangle;
    }

    @Override
    public void launchAttack() {
        killer = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mowEnemy();
                if (mower.getX() > gameInfo.getWindowWidth()){
                    killer.stop();
                }
            }
        }));
        killer.setCycleCount(Animation.INDEFINITE);
        killer.play();
    }

    @Override
    public void attackAnimation(Pane layer) {
        setupViewer(layer);
        animation = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mower.move();
                mower.relocate();
                if (mower.getX() > gameInfo.getWindowWidth()){
                    mower.removeFromLayer();
                    animation.stop();
                }
            }
        }));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void setupViewer(Pane layer){
        mower = new Viewer(layer, -30, y - gameInfo.getTileSize()/2, 5, 0, gameInfo);
        ImageView mowerImage = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/mower.png")));
        mowerImage.setFitHeight(gameInfo.getTileSize());
        mowerImage.setPreserveRatio(true);
        mower.setNode(mowerImage);
        mower.add();
    }

    @Override
    public void stop() {
    }

    private void mowEnemy(){
        double tileSize2 = (double)gameInfo.getTileSize()/2;
        int y = mower.getY();
        int x = mower.getX();
        for(Enemy i : gameInfo.getEnemyViewerList()){
            if (i.getY() > y - tileSize2  && i.getY() < y + tileSize2*2 && i.getX() > x - tileSize2 && i.getX() < x + tileSize2*2){
                i.dealDamage(i.getHealth());
            }
        }
    }
}
