package TowerDefense.menu.cinematic;

import TowerDefense.Main;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;

public class Cinematic {
    private static MediaPlayer mediaPlayer;
    private static Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 26);

    public static StackPane generateMediaView(Pane layer, Runnable runnable) {
        URL file = Main.class.getResource("resources/cinematic/cin.mp4");
        Media media = new Media(file.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setVolume(0.1);
        StackPane cinematicLayer = new StackPane();
        Rectangle background = new Rectangle(1200, 800, Color.BLACK);
        cinematicLayer.setStyle("-fx-background-color: black;");
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(675);
        StackPane.setAlignment(mediaView, Pos.CENTER);

        StackPane skipButton = generateSkipButton(mediaPlayer, cinematicLayer, layer, runnable);
        StackPane.setMargin(skipButton, new Insets(600, -450, 0, 1000));

        mediaPlayer.setOnEndOfMedia(new Runnable(){
            public void run() {
                stop(layer, cinematicLayer, runnable);
            }
        });

        mediaView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("ola");
            }
        });

        cinematicLayer.getChildren().addAll(background, mediaView, skipButton);
        mediaPlayer.play();
        return cinematicLayer;
    }

    public static void stop(Pane layer, Pane cinematicLayer, Runnable runnable){
        runnable.run();
        mediaPlayer.stop();
        layer.getChildren().remove(cinematicLayer);
    }

    private static StackPane generateSkipButton(MediaPlayer mediaPlayer, StackPane cinematicLayer, Pane layer, Runnable runnable) {
        StackPane skipButton = new StackPane();

        Rectangle background = new Rectangle(200, 50, Color.LIGHTGREY);
        background.setOpacity(0.9);
        background.setArcHeight(10);
        background.setArcWidth(10);

        background.setCursor(Cursor.HAND);

        background.setOnMouseClicked(mouseEvent -> {
            stop(layer, cinematicLayer, runnable);
        });

        background.setOnMouseEntered(mouseEvent -> {
            background.setScaleX(1.1);
            background.setScaleY(1.1);
        });

        background.setOnMouseEntered(mouseEvent -> {
            background.setScaleX(1);
            background.setScaleY(1);
        });

        Text skipText = new Text("SKIP");
        skipText.setFont(VerlagCondensedBold);
        skipText.setFill(Color.BLACK);
        skipText.setMouseTransparent(true);
        skipText.scaleXProperty().bind(background.scaleXProperty());
        skipText.scaleYProperty().bind(background.scaleYProperty());

        skipButton.getChildren().addAll(background, skipText);

        Interpolator easeOutQuint = interpolator();
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(new Duration(2000), new KeyValue(skipButton.translateXProperty(), 0, easeOutQuint)),
                new KeyFrame(new Duration(4000), new KeyValue(skipButton.translateXProperty(), -250, easeOutQuint)),
                new KeyFrame(new Duration(8000), new KeyValue(skipButton.translateXProperty(), -250, easeOutQuint)),
                new KeyFrame(new Duration(12000), new KeyValue(skipButton.translateXProperty(), 0, easeOutQuint))
        );
        timeline.setRate(2);
        timeline.play();
        timeline.setOnFinished(actionEvent -> {
            cinematicLayer.getChildren().remove(skipButton);
        });
        return skipButton;
    }

    private static Interpolator interpolator() {
        return new Interpolator() {
            @Override
            protected double curve(double t) {
                return 1 - Math.pow(1 - t, 5);
            }
        };
    }


}
