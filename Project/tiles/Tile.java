package TowerDefense.tiles;

import TowerDefense.GameInfo;
import javafx.scene.image.ImageView;

public abstract class Tile{
    protected int x,y;
    protected GameInfo gameInfo;

    public Tile(int x, int y, GameInfo gameInfo){
        this.x = x;
        this.y = y;
        this.gameInfo = gameInfo;
    }

    public abstract ImageView getTile();

    public ImageView addTile(){
        ImageView imageTile = new ImageView();
        imageTile = this.getTile();
        imageTile.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(50).divide(800));
        imageTile.setPreserveRatio(true);
        imageTile.relocate(x,y);
    return  imageTile;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    protected void setupImage(ImageView imageTile){
        imageTile.fitHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(50).divide(800));
        imageTile.setPreserveRatio(true);
        imageTile.relocate(x, y);
    }


}