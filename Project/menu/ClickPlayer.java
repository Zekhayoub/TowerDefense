package TowerDefense.menu;

import TowerDefense.Saver;
import TowerDefense.menu.cinematic.Cinematic;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ClickPlayer implements EventHandler<MouseEvent>, Runnable{
    private final Menu menu;
    private boolean newGame;
    private Saver saver;

    public ClickPlayer(Menu menu, Saver saver, boolean newGame){
        this.menu = menu;
        this.newGame = newGame;
        this.saver = saver;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (newGame){
            Runnable runnable = this;
            saver.startNewSave();
            StackPane cinematicLayer = Cinematic.generateMediaView(menu.getMenuLayer(), this);
            menu.getMenuLayer().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.ESCAPE){
                        Cinematic.stop(menu.getMenuLayer(), cinematicLayer, runnable);
                    }
                }
            });
            menu.getMenuLayer().getChildren().add(cinematicLayer);

        }
        else{
            saver.readSaveFile();
            run();
        }
    }

    @Override
    public void run() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300), menu.getMenuLayer());
        fadeTransition.setFromValue(1);
        menu.getSubmenuLayer().setOpacity(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        menu.getWholeMenuLayer().setMouseTransparent(true);

        fadeTransition.setOnFinished(actionEvent -> {
            menu.hideMenuLayer();
            menu.getSubmenuLayer().requestFocus();
            menu.getWholeMenuLayer().setMouseTransparent(false);
        });
    }
}
