package TowerDefense.gameOver;


import TowerDefense.Main;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverItem extends StackPane {

    private Font VerlagBook =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Book.otf"), 26);

    public GameOverItem(String name, int numberBanner){

        ImageView banner = selectBanner(numberBanner);

        banner.setFitHeight(38); //45.5-46 pour le 1, 38 pour le 2
        banner.setPreserveRatio(true);
        banner.setOpacity(0);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(VerlagBook);
        text.setFill(Color.WHITE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(banner, text);

        setOnMouseEntered(event -> {
            banner.setOpacity(1);
            //text.setFill(Color.WHITE);
        });

        setOnMouseExited(event -> {
            banner.setOpacity(0);
            //text.setFill(Color.DARKGREY);
        });

        /*setOnMousePressed(event -> {
            rect.setOpacity(0.05);
            rect.setFill(Color.DARKRED);
        });

        setOnMouseReleased(event -> {
            rect.setOpacity(0.4);
            //rect.setFill(gradient);
        });*/
    }

    private ImageView selectBanner(int numberBanner) {
        if (numberBanner == 1) {
            ImageView banner = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_text_white2.png")));
            return banner;
        } else {
            ImageView banner = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_text_white2long.png")));
            return banner;
        }
    }
}

