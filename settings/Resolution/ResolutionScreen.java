package TowerDefense.settings.Resolution;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Time;
import TowerDefense.gameOver.GameOverBox;
import TowerDefense.gameOver.GameOverItem;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ResolutionScreen {
    private StackPane layer;
    private final Pane parentLayer, interfaceLayer;
    private final GameInfo gameInfo;
    private int settingsType;  // 0 from Pause, 1 from SettingsButton
    private final Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 70);
    private final Font VerlagBook =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Book.otf"), 30);

    public ResolutionScreen(GameInfo gameInfo, Pane parentLayer, Pane interfaceLayer, int settingsType){
        this.gameInfo =gameInfo;
        this.parentLayer = parentLayer;
        this.interfaceLayer = interfaceLayer;
        this.settingsType = settingsType;
        this.layer = new StackPane();
        generateResolutionScreen();
    }

    private void generateResolutionScreen(){
        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.heightProperty().bind(gameInfo.getRealWindowHeight());
        background.widthProperty().bind(gameInfo.getRealWindowWidth());

        if (settingsType == 0) {
            background.setOpacity(1);
        } else {
            background.setOpacity(0.55);
        }

        layer.setPrefSize(1200, 800);
        layer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        layer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());
        //layer.setStyle("-fx-background-color: purple;");

        // TOP LEFT
        HBox hbox = new HBox();
        //hbox.setStyle("-fx-background-color: red;");
        hbox.setMaxSize(1000, 400);
        hbox.setPadding(new Insets(35, 0, 0, 30));
        hbox.setSpacing(20);

        ImageView biohazard = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/biohazard_3.png")));
        biohazard.setFitHeight(75);
        biohazard.setPreserveRatio(true);
        HBox.setMargin(biohazard, new Insets(0, 0, 0, 0));

        Text title = new Text("SETTINGS");
        title.setFont(BebasNeue);
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);

        hbox.getChildren().addAll(biohazard, title);
        StackPane.setAlignment(hbox, Pos.TOP_LEFT);

        //TOP RIGHT

        ImageView bannergeneral = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_general2.png")));
        bannergeneral.setFitHeight(59);
        bannergeneral.setPreserveRatio(true);
        HBox bannergeneralBox = new HBox(bannergeneral);
        HBox.setMargin(bannergeneralBox, new Insets(10, 0, 350, 510));
        bannergeneralBox.setCursor(Cursor.HAND);
        bannergeneralBox.setOnMouseClicked(mouseEvent -> { settingsGame(); });
        //bannergeneralBox.setStyle("-fx-background-color: darkblue;");
        ImageView bannergraphics = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_graphics2.png")));
        bannergraphics.setFitHeight(55);
        bannergraphics.setPreserveRatio(true);
        HBox bannergraphicsBox = new HBox(bannergraphics);
        HBox.setMargin(bannergraphicsBox, new Insets(10, 0, 338, -10));
        //bannergraphicsBox.setStyle("-fx-background-color: black;");

        hbox.getChildren().addAll(bannergeneralBox, bannergraphicsBox);

        // BOTTOM LEFT
        VBox banner = new VBox();
        //banner.setStyle("-fx-background-color: green;");
        banner.setMaxSize(220, 40);
        ImageView bannerback = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_back.png")));
        bannerback.setFitHeight(40);
        bannerback.setPreserveRatio(true);
        banner.getChildren().add(bannerback);
        banner.setOnMouseClicked(mouseEvent -> { quit(); });
        StackPane.setMargin(banner, new Insets(0, 0, 30, -20));
        StackPane.setAlignment(banner, Pos.BOTTOM_LEFT);

        // CENTER

        // RESOLUTION

        VBox box = new VBox();
        //box.setStyle("-fx-background-color: yellow;");
        box.setMaxSize(400, 300);
        box.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(box, new Insets(120, 0, 0, 0));
        StackPane.setAlignment(box, Pos.TOP_CENTER);

        Text resolution = new Text("RESOLUTION");
        resolution.setFont(VerlagBook);
        resolution.setFill(Color.WHITE);
        resolution.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(resolution, new Insets(0, 0, 10, 0));

        GameOverItem res1600x1200 = new GameOverItem("1600x1200", 2);
        res1600x1200.setOnMouseClicked(new ResolutionChanger(1600,1200,gameInfo));

        GameOverItem res1200x800 = new GameOverItem("1200x800", 2);
        res1200x800.setOnMouseClicked(new ResolutionChanger(1200,800,gameInfo));

        GameOverItem res1680x1050 = new GameOverItem("1680x1050", 2);
        res1680x1050.setOnMouseClicked(new ResolutionChanger(1680,1050,gameInfo));

        GameOverItem res600x400 = new GameOverItem("600x400", 2);
        res600x400.setOnMouseClicked(new ResolutionChanger(600,400,gameInfo));

        GameOverItem fullscreen = new GameOverItem("Fullscreen", 2);
        fullscreen.setOnMouseClicked(new Fullscreen(gameInfo));

        GameOverBox buttons = new GameOverBox(res1600x1200, res1200x800, res1680x1050, res600x400, fullscreen);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        VBox.setMargin(buttons, new Insets(10, 190, 0, 190));
        //buttons.setStyle("-fx-background-color: green;");

        if (settingsType == 0) {
            parentLayer.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        quit();
                    }
                }
            });
        } else {
            interfaceLayer.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        quit();
                    }
                }
            });
        }


        box.getChildren().addAll(resolution, buttons);

        layer.getChildren().addAll(background, hbox, banner, box);
    }

    public Pane getResolutionScreen(){
        return layer;
    }

    private void quit(){
        if (settingsType == 0) {
            interfaceLayer.getChildren().remove(parentLayer);
        } else {
            if (interfaceLayer.getChildren().contains(layer)){
                interfaceLayer.getChildren().remove(layer);
                Time.unpause();
            }
        }

    }

    protected void settingsGame() {
        if (settingsType == 0) {
            parentLayer.getChildren().remove(layer);
        } else {
            interfaceLayer.getChildren().remove(layer);
            interfaceLayer.getChildren().add(parentLayer);
        }
    }
}
