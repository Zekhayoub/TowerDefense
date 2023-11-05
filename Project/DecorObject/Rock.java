package TowerDefense.DecorObject;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Rock extends Object {
    public Rock(int x, int y, Pane mapLayer, GameInfo gameInfo) {
        super(x, y, mapLayer, gameInfo);
        image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/objects/rock1.png")));
        this.height = 15;
        this.width = 40;
        add();
    }
}
