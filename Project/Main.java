package TowerDefense;

import TowerDefense.menu.Menu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Pane mapLayer = new Pane();
    Pane playLayer = new Pane();
    Pane interfaceLayer = new Pane();
    GameInfo gameInfo = new GameInfo();
    Starter starter = new Starter(gameInfo, mapLayer, playLayer, interfaceLayer);
    Menu menu = new Menu(interfaceLayer, gameInfo);

    public void start(Stage Stage){
        //Creation of main node + scene
        Stage.setTitle("Ultimate Tower Defense Game");
        Group root = new Group();
        Scene scene = new Scene(root, Color.WHITE);
        Pane menuLayer = menu.initMenu(starter);
        Stage stage = new Stage();
        gameInfo.setStage(stage);

        //Add layers to scene
        root.getChildren().addAll(mapLayer, playLayer, interfaceLayer, menuLayer);

        //Load animations
        Loader.loadAnimations();

        //Show scene
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}