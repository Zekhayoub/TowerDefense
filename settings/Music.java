package TowerDefense.settings;

import TowerDefense.Main;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;

public class Music {
    private static MediaPlayer mediaPlayer;

    public static MediaView generateMediaView() {
        URL file = Main.class.getResource("resources/sound/rainSound.mp3");
        Media media = new Media(file.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //tourne à l'infini
        mediaPlayer.play();
        return mediaView;
    }

    public static void stop(){
        mediaPlayer.stop();
    }

    public static MediaPlayer getMediaPlayer() { return mediaPlayer; }
}
