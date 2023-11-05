package TowerDefense.pause;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Time;
import TowerDefense.gameOver.GameOverBox;
import TowerDefense.gameOver.GameOverItem;
import TowerDefense.menu.ClickQuit;
import TowerDefense.menu.Menu;
import TowerDefense.settings.SettingsScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Pause {
    private final Pane interfaceLayer;
    private final Menu menu;
    private final StackPane pauseLayer = new StackPane();
    private final Pane wholeLayer = new Pane();
    private Pane settingsScreenLayer;
    private final GameInfo gameInfo;
    private final boolean first = true;
    private final Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 80); //90
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 24);

    public Pause(Pane interfaceLayer, Menu menu, GameInfo gameInfo){
        this.interfaceLayer = interfaceLayer;
        this.menu = menu;
        this.gameInfo = gameInfo;
    }

    public void pauseFuntion(){
        generatePauseLayer();
        interfaceLayer.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ESCAPE){
                    pauseGame();
                }
            }
        });
    }

    protected void generatePauseLayer(){
        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.heightProperty().bind(gameInfo.getRealWindowHeight());
        background.widthProperty().bind(gameInfo.getRealWindowWidth());
        background.setOpacity(0.1);

        pauseLayer.setPrefSize(1200, 800);
        pauseLayer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        pauseLayer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());

        VBox box = new VBox();
        box.setPadding(new Insets(50, 0, 0, 0));
        box.setMaxSize(500, 700);
        box.setAlignment(Pos.TOP_CENTER);

        ImageView biohazard = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/biohazard_3.png")));
        biohazard.setFitHeight(100); //100
        biohazard.setPreserveRatio(true);

        ImageView exit = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/exitRight.png")));
        exit.setFitHeight(35); //35
        exit.setPreserveRatio(true);

        VBox.setMargin(exit, new Insets(155, 0, 0, 0));

        Text title = new Text("PAUSED");
        title.setFont(BebasNeue);
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(title, new Insets(20, 0, 0, 0));

        VBox quitBox = new VBox();
        quitBox.setMaxSize(50,50);
        Text quit = new Text("QUIT");
        quit.setFont(VerlagBold);
        quit.setFill(Color.WHITE);
        quit.setTextAlignment(TextAlignment.CENTER);
        quitBox.getChildren().add(quit);
        quitBox.setAlignment(Pos.CENTER);
        quitBox.setOnMouseClicked(new ClickQuit());
        VBox.setMargin(quit, new Insets(10, 5, 5, 5));

        Line line = new Line();
        line.setEndX(210);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(2);

        GameOverItem resumeButton = new GameOverItem("RESUME", 1);
        resumeButton.setOnMouseClicked(mouseEvent -> {
            pauseGame();
        });
        GameOverItem restartButton = new GameOverItem("RESTART", 1);
        restartButton.setOnMouseClicked(new ClickRestartPause(menu));

        SettingsScreen settingsScreen = new SettingsScreen(gameInfo, wholeLayer, 0);
        settingsScreen.initSettingsScreen();
        settingsScreenLayer = settingsScreen.getSettingScreen();
        GameOverItem optionsBoutton = new GameOverItem("OPTIONS", 1);
        optionsBoutton.setOnMouseClicked(new ClickSettingsFromPause(wholeLayer, settingsScreenLayer));

        GameOverItem backButton = new GameOverItem("BACK", 1);
        backButton.setOnMouseClicked(new ClickBackPause(menu));

        GameOverBox vbox = new GameOverBox(resumeButton, optionsBoutton, restartButton, backButton);
        vbox.setAlignment(Pos.CENTER);
        VBox.setMargin(vbox, new Insets(10, 190, 0, 190));

        box.getChildren().addAll(biohazard, title, line, vbox, exit, quitBox);
        StackPane.setAlignment(box, Pos.CENTER);

        pauseLayer.getChildren().addAll(background, box);
        wholeLayer.getChildren().add(pauseLayer);
    }



    public void pauseGame(){
        if (Time.getStatus()){
            wholeLayer.getChildren().remove(settingsScreenLayer);
            interfaceLayer.getChildren().add(wholeLayer);
            Time.pause();
        }
        else{
            if (interfaceLayer.getChildren().contains(wholeLayer)){
                if(wholeLayer.getChildren().contains(settingsScreenLayer)){
                    wholeLayer.getChildren().remove(settingsScreenLayer);
                }
                else{
                    interfaceLayer.getChildren().remove(wholeLayer);
                    Time.unpause();
                }

            }
            else {
                System.out.println("You can't pause right now!");
            }

        }
        interfaceLayer.requestFocus();
    }
}
