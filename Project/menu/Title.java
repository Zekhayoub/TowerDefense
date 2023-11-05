package TowerDefense.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Title extends StackPane {
    public Title(String name){
        Rectangle rect = new Rectangle(375,60);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(2);
        rect.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        String x = "Lucida Console";
        text.setFont(Font.font(x, FontWeight.SEMI_BOLD,40));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rect , text);
    }
}