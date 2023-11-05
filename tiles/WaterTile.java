package TowerDefense.tiles;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WaterTile extends Tile {
    public WaterTile(int x, int y, GameInfo gameInfo) {
        super(x, y, gameInfo);
    }

    @Override
    public ImageView getTile() {
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new3center.png")));
    }
    public ImageView getCornerTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new3corner.png")));
    }
    public ImageView getCenterTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new3center.png")));
    }
    public ImageView getSideTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new3side.png")));
    }


    public ImageView addCornerTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getCornerTile();
        setupImage(imageTile);
        return imageTile;
    }
    public ImageView addCenterTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getCenterTile();
        setupImage(imageTile);
        return imageTile;
    }
    public ImageView addSideTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getSideTile();
        setupImage(imageTile);
        return imageTile;
    }


}