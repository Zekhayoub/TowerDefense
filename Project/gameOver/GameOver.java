package TowerDefense.gameOver;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.menu.ClickQuit;
import TowerDefense.menu.Menu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameOver {
    private final StackPane gameOverLayer;
    private final GameInfo gameInfo;
    private Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 120);

    public GameOver(GameInfo gameInfo){
        this.gameOverLayer = new StackPane();
        this.gameInfo = gameInfo;

    }

    public Pane initGameOver(Menu menu){
        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.setOpacity(0.7);

        gameOverLayer.setPrefSize(1200,800);

        VBox box = new VBox();
        box.setMaxSize(500, 500);
        box.setAlignment(Pos.CENTER);

        Text title = new Text("GAME OVER");
        title.setFont(BebasNeue);
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);

        Line line = new Line();
        line.setEndX(430);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(2);

        GameOverItem restartButton = new GameOverItem("RESTART", 1); restartButton.setOnMouseClicked(new ClickRestarter(menu));
        GameOverItem backButton = new GameOverItem("BACK", 1);  backButton.setOnMouseClicked(new ClickBacker(menu));
        GameOverItem quitButton = new GameOverItem("QUIT", 1);  quitButton.setOnMouseClicked(new ClickQuit());
        GameOverBox vbox = new GameOverBox(restartButton, backButton, quitButton);
        VBox.setMargin(vbox, new Insets(0, 190, 0, 190)); //
        vbox.setAlignment(Pos.CENTER);

        box.getChildren().addAll(title, vbox);
        StackPane.setAlignment(box, Pos.CENTER);

        gameOverLayer.getChildren().addAll(background, line, box);
        return gameOverLayer;
    }
}
