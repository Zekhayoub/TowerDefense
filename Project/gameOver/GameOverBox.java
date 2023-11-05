package TowerDefense.gameOver;

import javafx.scene.layout.VBox;

public class GameOverBox extends VBox {

    public GameOverBox(GameOverItem...items) {

        for(GameOverItem item : items){
            getChildren().addAll(item);
        }
    }
}