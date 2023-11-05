package TowerDefense.menu;

import TowerDefense.GameInfo;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends StackPane {
    public MenuItem(String name, GameInfo gameInfo){
        LinearGradient gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITESMOKE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
                new Stop(1, Color.WHITESMOKE));

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(gameInfo.getRealWindowWidth().multiply(250).divide(1200));
        rect.heightProperty().bind(gameInfo.getRealWindowHeight().multiply(40).divide(800));
        rect.setOpacity(0.4);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Impact", FontWeight.BLACK,20));


        setAlignment(Pos.CENTER);
        getChildren().addAll(rect , text);

        setOnMouseEntered(event -> {
            rect.setFill(gradient);
            text.setFill(Color.WHITE);
        });

        setOnMouseExited(event -> {
            rect.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });
        setOnMousePressed(event -> {
            rect.setOpacity(0.05);
            rect.setFill(Color.DARKRED);
        });

        setOnMouseReleased(event -> {
            rect.setOpacity(0.4);
            rect.setFill(gradient);
        });
    }
}
