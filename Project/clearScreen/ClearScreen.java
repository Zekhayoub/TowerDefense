package TowerDefense.clearScreen;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.gameOver.ClickBacker;
import TowerDefense.gameOver.GameOverBox;
import TowerDefense.gameOver.GameOverItem;
import TowerDefense.menu.ClickQuit;
import TowerDefense.menu.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ClearScreen {
    private final StackPane layer;
    private final GameInfo gameInfo;
    private Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 80);
    private Font BebasNeueSmall =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 40);

    public ClearScreen(GameInfo gameInfo){
        this.layer = new StackPane();
        this.gameInfo = gameInfo;
    }

    public Pane initClearScreen(Menu menu){
        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.setOpacity(0.7);

        layer.setPrefSize(1200,800);

        VBox box = new VBox();
        box.setPadding(new Insets(50, 0, 0, 0)); //50 // NEW
        box.setMaxSize(500, 750);
        box.setAlignment(Pos.TOP_CENTER);

        ImageView logo = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/Logo2.png")));
        logo.setFitHeight(250); // NEW
        logo.setPreserveRatio(true);

        Text title = new Text("HUMANITY IS SAFE");
        title.setFont(BebasNeue);
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(title, new Insets(40, 0, 0, 0)); //40 // NEW

        Text subtitle = new Text("for now..."); //
        subtitle.setFont(BebasNeueSmall);
        subtitle.setFill(Color.WHITE);
        subtitle.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(subtitle, new Insets(-15,0,10,0)); // NEW

        Line line = new Line();
        line.setEndX(460); // NEW
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(2);

        GameOverItem restartButton = new GameOverItem("CONTINUE", 2); restartButton.setOnMouseClicked(new ClickNext(menu));
        GameOverItem backButton = new GameOverItem("BACK", 1);  backButton.setOnMouseClicked(new ClickBacker(menu));
        GameOverItem quitButton = new GameOverItem("QUIT", 1);  quitButton.setOnMouseClicked(new ClickQuit());

        GameOverBox vbox = new GameOverBox(restartButton, backButton, quitButton);
        VBox.setMargin(vbox, new Insets(10, 190, 0, 190)); // NEW
        vbox.setAlignment(Pos.CENTER);

        box.getChildren().addAll(logo, title, subtitle, line, vbox);
        StackPane.setAlignment(box, Pos.CENTER);

        layer.getChildren().addAll(background, box);
        return layer;
    }
}
