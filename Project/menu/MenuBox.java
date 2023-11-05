package TowerDefense.menu;

import TowerDefense.GameInfo;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MenuBox extends VBox {
    private GameInfo gameInfo;
    public MenuBox(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    public void createBox(MenuItem...items){
        getChildren().addAll(createSeperator());

        for(MenuItem item : items){
            getChildren().addAll(item,createSeperator());
        }
    }

    private Line createSeperator(){
        Line seperator = new Line();
        seperator.endXProperty().bind(gameInfo.getRealWindowWidth().multiply(250).divide(1200));
        seperator.setStroke(Color.DARKGREY);
        return seperator;
    }
}