package TowerDefense.threads;

import TowerDefense.Turrets;
import TowerDefense.UI.UserInterface;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;

public class TurretSpawner implements Runnable {
    private ArrayList<Turrets> onMapTurrets, turretList;
    private Pane playLayer;
    private UserInterface userInterface;

    public TurretSpawner(Pane playLayer, ArrayList<Turrets> turretList, ArrayList<Turrets> onMapTurrets,
                      UserInterface userInterface){
        this.onMapTurrets = onMapTurrets;
        this.turretList =turretList;
        this.playLayer = playLayer;
        this.userInterface = userInterface;
    }

    @Override
    public void run() {
        Iterator<Turrets> iter = turretList.iterator();
        while (iter.hasNext()){
            Turrets turret = iter.next();

            Circle range = turret.drawRange();
            playLayer.getChildren().add(range);

            Node turretImage = turret.drawTurret();

            userInterface.enableUpdate(turret, turretImage);

            playLayer.getChildren().add(turretImage);turretImage.toBack();
            onMapTurrets.add(turret);
            iter.remove();
        }
    }
}
