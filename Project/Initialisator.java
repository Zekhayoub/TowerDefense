package TowerDefense;

import TowerDefense.UI.UpdateButton;
import TowerDefense.UI.UserInterface;
import TowerDefense.effects.Rain;
import TowerDefense.menu.Menu;
import TowerDefense.pause.Pause;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Initialisator {

    public UserInterface initInterface(Pane interfaceLayer, Pane playLayer, Pane mapLayer, ArrayList<Turrets> turretList,
                              ArrayList<Turrets> onMapTurrets, Map map , GameInfo gameInfo, ArrayList<Text> textList,
                              UpdateButton updateButton, TextViewer textViewer, Menu menu){
        //Initialisation of interface
        ArrayList<Turrets> turretWaitingList = new ArrayList<>();

        Rain rain = new Rain(interfaceLayer, gameInfo);
        rain.start();
        UserInterface userInterface = new UserInterface(interfaceLayer, playLayer, turretWaitingList, turretList, onMapTurrets, gameInfo,
                menu, map, textList, updateButton);
        userInterface.generateUserInterface();

        interfaceLayer.getChildren().addAll(textViewer.get(0), textViewer.get(1), textViewer.get(2), textViewer.get(3));

        interfaceLayer.setPickOnBounds(false);

        playLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> updateButton.setClickable(false));
        mapLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> updateButton.setClickable(false));

        interfaceLayer.requestFocus();

        return userInterface;
    }

    public void initPause(Pane interfaceLayer, Menu menu, GameInfo gameInfo){
        Pause pause = new Pause(interfaceLayer, menu, gameInfo);
        pause.pauseFuntion();
    }
}
