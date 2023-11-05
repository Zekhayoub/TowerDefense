package TowerDefense.tiles;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GrassTile extends Tile{

    public GrassTile(int x, int y, GameInfo gameInfo) {
        super(x, y, gameInfo);
    }

    @Override
    public ImageView getTile() {
            return new ImageView(new Image(Main.class.getResourceAsStream("resources/image/new1.png")));
    }
}
