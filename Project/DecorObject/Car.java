package TowerDefense.DecorObject;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Car extends Object {
public Car(int x, int y, Pane mapLayer, GameInfo gameInfo) {
        super(x, y, mapLayer, gameInfo);
        image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/objects/car1.png")));
        this.height = 30;
        this.width = 100;
        add();
        }
}
