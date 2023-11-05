package TowerDefense;

import TowerDefense.UI.UpdateButton;
import TowerDefense.UI.UserInterface;
import TowerDefense.maps.*;
import TowerDefense.menu.Menu;
import TowerDefense.settings.Music;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Starter {
    private final GameInfo gameInfo;
    private final Pane mapLayer;
    private final Pane playLayer;
    private final Pane interfaceLayer;
    private UpdateButton updateButton;
    private ArrayList<Turrets> turretList;
    private ArrayList<Turrets> onMapTurrets;
    private ArrayList<Text> textList;
    private TextViewer textViewer;
    private Initialisator initialisator;
    private boolean first = true;
    private int mapNumber;
    private GameLoop gameLoop;
    private Saver saver;
    private MediaView music;
    public Starter(GameInfo gameInfo, Pane mapLayer, Pane playLayer, Pane interfaceLayer){
        this.gameInfo =gameInfo;
        this.mapLayer = mapLayer;
        this.playLayer = playLayer;
        this.interfaceLayer = interfaceLayer;
        updateButton = new UpdateButton(gameInfo);
        turretList = new ArrayList<>();
        onMapTurrets = new ArrayList<>();
        textList = new ArrayList<>();
        textViewer = new TextViewer(gameInfo.getWindowHeight(), gameInfo.getWaveNumber(), gameInfo.getStageNumber(), gameInfo.getLife());
        initialisator = new Initialisator();
        saver = new Saver(gameInfo);
    }

    public void start(Menu menu, int mapNumber){
        this.mapNumber = mapNumber;
        gameInfo.setMapNumber(mapNumber);

        music = Music.generateMediaView();


        //Create map
        Map map;
        map = mapSelector(mapNumber);
        map.createNewMap(mapLayer);
        WayGenerator wayGenerator = new WayGenerator(map, gameInfo);
        if (map instanceof SinglePath){
            wayGenerator.autoGen(((SinglePath)map).getStartX(),((SinglePath)map).getStartY());
        }
        else {
            wayGenerator.autoGen(((MultiPath)map).getStartX()[0],((MultiPath)map).getStartY()[0]);
            wayGenerator.autoGen(((MultiPath)map).getStartX()[1],((MultiPath)map).getStartY()[1]);
        }
        map.generateObjects(mapLayer);

        wayGenerator.printWay();

        //Generate interface
        textViewer.generateText();
        UserInterface userInterface = initialisator.initInterface(interfaceLayer, playLayer, mapLayer, turretList,onMapTurrets, map, gameInfo,
                textList, updateButton, textViewer, menu);
        if (first){
            initialisator.initPause(interfaceLayer, menu, gameInfo);
        }
        first = false;

        //Start game loop
        gameLoop = new GameLoop(gameInfo, playLayer, interfaceLayer, turretList, onMapTurrets,
             userInterface, wayGenerator.getCriticalPoints(), textViewer, menu, map);
        gameLoop.gameLoop();
    }

    public boolean getFirst(){
        return first;
    }

    public void stop(){
        gameLoop.stop();
        playLayer.getChildren().remove(music);
        Music.stop();
    }

    public void reset(){
        saver.saveGame();
        Time.pause();
        playLayer.getChildren().clear();
        mapLayer.getChildren().clear();
        interfaceLayer.getChildren().clear();
        gameInfo.reset();

        updateButton = new UpdateButton(gameInfo);
        turretList = new ArrayList<>();
        onMapTurrets = new ArrayList<>();
        textList = new ArrayList<>();
        textViewer = new TextViewer(gameInfo.getWindowHeight(), gameInfo.getWaveNumber(), gameInfo.getStageNumber(),
                gameInfo.getLife());
    }

    private Map mapSelector(int i){
        Map map;
        if (i == 1){
            map = new Map1(gameInfo);
        }
        else if (i == 2){
            map = new Map2(gameInfo);
        }
        else if (i == 3){
            map = new Map3(gameInfo);
        }
        else if (i == 4){
            map = new Map4(gameInfo);
        }
        else if (i == 5){
            map = new Map5(gameInfo);
        }
        else if (i == 6){
            map = new Map6(gameInfo);
        }
        else if (i == 7){
            map = new Map7(gameInfo);
        }
        else if (i == 8){
            map = new Map8(gameInfo);
        }
        else if (i == 9){
            map = new Map9(gameInfo);
        }
        else{
            map = new Map2(gameInfo);
        }
        return map;
    }

    public int getMapNumber(){
        return mapNumber;
    }
}
