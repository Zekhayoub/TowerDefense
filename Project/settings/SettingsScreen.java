package TowerDefense.settings;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Time;
import TowerDefense.pause.ClickResolution;
import TowerDefense.settings.Resolution.ResolutionScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Slider;
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

public class SettingsScreen {
    private final StackPane layer;
    private final GameInfo gameInfo;
    private final Pane interfaceLayer;
    private Pane resolutionLayer;
    private final int settingsType;  // 0 from Pause, 1 from SettingsButton
    private final Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 70);
    private final Font VerlagBook =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Book.otf"), 30);
    private final Font VerlagBookSmall =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Book.otf"), 27);

    public SettingsScreen(GameInfo gameInfo, Pane interfaceLayer, int settingsType) {
        this.layer = new StackPane();
        this.gameInfo = gameInfo;
        this.interfaceLayer = interfaceLayer;
        this.settingsType = settingsType;
    }

    public void initSettingsScreen() {
        ResolutionScreen resolutionScreen = new ResolutionScreen(gameInfo, layer, interfaceLayer, settingsType);
        resolutionLayer = resolutionScreen.getResolutionScreen();

        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.heightProperty().bind(gameInfo.getRealWindowHeight());
        background.widthProperty().bind(gameInfo.getRealWindowWidth());

        if (settingsType == 0){ // from Pause
            background.setOpacity(1);
        } else { // from SettingsButton
            background.setOpacity(0.55);
        }

        layer.setPrefSize(1200, 800);
        layer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        layer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());

        // TOP
        HBox hbox = new HBox();
        hbox.setMaxSize(1200, 400);
        hbox.setPadding(new Insets(35, 0, 0, 30));
        hbox.setSpacing(20);

        //TOP LEFT

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
        ImageView bannergeneral = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_general1.png")));
        bannergeneral.setFitHeight(55);
        bannergeneral.setPreserveRatio(true);
        HBox bannergeneralBox = new HBox(bannergeneral);
        HBox.setMargin(bannergeneralBox, new Insets(10, 0, 345, 500));
        ImageView bannergraphics = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_graphics1.png")));
        bannergraphics.setFitHeight(59);
        bannergraphics.setPreserveRatio(true);
        HBox bannergraphicsBox = new HBox(bannergraphics);
        HBox.setMargin(bannergraphicsBox, new Insets(5, 0, 345, -20));
        bannergraphicsBox.setCursor(Cursor.HAND);
        bannergraphicsBox.setOnMouseClicked(new ClickResolution(this));

        hbox.getChildren().addAll(bannergeneralBox, bannergraphicsBox);

        // BOTTOM LEFT
        VBox banner = new VBox();
        banner.setMaxSize(220, 40);
        ImageView bannerback = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/banner_back.png")));
        bannerback.setFitHeight(40);
        bannerback.setPreserveRatio(true);
        banner.getChildren().add(bannerback);
        banner.setOnMouseClicked(mouseEvent -> { settingsGame(); });
        StackPane.setMargin(banner, new Insets(0, 0, 30, -20));
        StackPane.setAlignment(banner, Pos.BOTTOM_LEFT);

        // CENTER
        VBox box = new VBox();
        box.setMaxSize(400, 300);
        box.setAlignment(Pos.TOP_CENTER);
        StackPane.setMargin(box, new Insets(120, 0, 0, 0));
        StackPane.setAlignment(box, Pos.TOP_CENTER);

        Text audio = new Text("AUDIO");
        audio.setFont(VerlagBook);
        audio.setFill(Color.WHITE);
        audio.setTextAlignment(TextAlignment.CENTER);
        VBox.setMargin(audio, new Insets(0, 0, 20, 0));

        HBox music = new HBox();
        music.setSpacing(30);
        music.setMaxSize(325, 50);

        Text musicText = new Text("Music");
        musicText.setFont(VerlagBookSmall);
        musicText.setFill(Color.WHITE);
        musicText.setTextAlignment(TextAlignment.CENTER);

        // VOLUME MUSIC SLIDER

        HBox musicSliderBox = new HBox();
        musicSliderBox.setAlignment(Pos.CENTER);
        musicSliderBox.setMinWidth(160);

        Slider volumeMusic = new Slider();
        volumeMusic.setMinWidth(160);
        volumeMusic.getStylesheets().add(getClass().getResource("stylesheet2.css").toExternalForm());
        volumeMusic.setValue(25);
        //volumeMusic.setValue(Music.getMediaPlayer().getVolume() * 250); // à modifier si nécessaire
        volumeMusic.valueProperty().addListener(observable -> Music.getMediaPlayer().setVolume(volumeMusic.getValue()/250));

        volumeMusic.valueProperty().addListener((ov, old_val, new_val) -> {
            StackPane trackPane = (StackPane) volumeMusic.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #ffffff %d%%, #969696 %d%%);",
                    new_val.intValue(), new_val.intValue());
            trackPane.setStyle(style);
        });

        musicSliderBox.getChildren().add(volumeMusic);

        Text valueMusic = new Text("" + (int)volumeMusic.getValue());
        valueMusic.setFont(VerlagBookSmall);
        valueMusic.setFill(Color.WHITE);
        volumeMusic.valueProperty().addListener((observable, oldValue, newValue) -> valueMusic.setText("" + newValue.intValue()));

        interfaceLayer.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ESCAPE){
                    quit();
                }
            }
        });

        music.getChildren().addAll(musicText, musicSliderBox, valueMusic);

        box.getChildren().addAll(audio, music);

        // ADD TO LAYER
        layer.getChildren().addAll(background, hbox, banner, box);
    }

    public Pane getSettingScreen(){ return layer; }

    public void showRes(){
        if (settingsType == 0)
        layer.getChildren().add(resolutionLayer);
        else {
            interfaceLayer.getChildren().add(resolutionLayer);
            interfaceLayer.getChildren().remove(layer);
        }
    }

    public void removeRes(){
        layer.getChildren().remove(resolutionLayer);
    }

    private void quit(){
        if (settingsType == 0) {
            interfaceLayer.getChildren().remove(layer);
        } else {
            if (interfaceLayer.getChildren().contains(layer)){
                interfaceLayer.getChildren().remove(layer);
                Time.unpause();
            }
        }
    }

    protected void settingsGame() {
        if (settingsType == 0) {
            interfaceLayer.getChildren().remove(layer);
        } else {
            if (Time.getStatus()) {
                interfaceLayer.getChildren().add(layer);
                Time.pause();
            } else {
                interfaceLayer.getChildren().remove(layer);
                Time.unpause();
            }
        }

    }
}

