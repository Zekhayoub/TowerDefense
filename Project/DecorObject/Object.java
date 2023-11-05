package TowerDefense.DecorObject;

import TowerDefense.GameInfo;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Object {
    protected int x, y, width, height;
    protected ImageView image;
    protected Pane mapLayer;
    protected GameInfo gameInfo;

    public Object(int x, int y, Pane mapLayer, GameInfo gameInfo){
        this.x = x;
        this.y = y;
        this.mapLayer = mapLayer;
        this.gameInfo = gameInfo;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    protected void add(){
        image.layoutXProperty().bind(gameInfo.getRealWindowWidth().multiply(x).divide(1200));
        image.layoutYProperty().bind(gameInfo.getRealWindowHeight().multiply(y).divide(800));
        image.setPreserveRatio(true);
        image.setFitHeight((int)Math.round(height* gameInfo.getScaling()));
        image.setSmooth(false);
        mapLayer.getChildren().add(image);
    }
}
