package TowerDefense.tiles;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WayTile extends Tile{
    public WayTile(int x, int y, GameInfo gameInfo) {
        super(x, y, gameInfo);
    }
    @Override
    public ImageView getTile() {
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new2.png")));
    }
    public ImageView getCornerTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new2corner.png")));
    }
    public ImageView getTTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new2T.png")));
    }
    public ImageView getQuadTile(){
        return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new2quad.png")));
    }


    public ImageView addCornerTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getCornerTile();
        setupImage(imageTile);
        return imageTile;
    }

    public ImageView addTTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getTTile();
        setupImage(imageTile);
        return imageTile;
    }

    public ImageView addQuadTile() {
        ImageView imageTile = new ImageView();
        imageTile = this.getQuadTile();
        setupImage(imageTile);
        return imageTile;
    }
}
