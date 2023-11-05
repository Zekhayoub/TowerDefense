package TowerDefense.DecorObject;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tree extends Object {
    public Tree(int x, int y, Pane mapLayer, GameInfo gameInfo) {
        super(x, y, mapLayer, gameInfo);
        image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/objects/deadtree1.png")));
        this.height = 75;
        this.width = 75;
        add();
    }
}
