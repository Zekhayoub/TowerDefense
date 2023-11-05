package TowerDefense;

import TowerDefense.turret.DoubleGunTurret;
import TowerDefense.turret.MissileLauncher;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class DragDetector implements EventHandler<MouseEvent> {
    private final ArrayList<Turrets> turretWaitingList;
    private final ArrayList<Turrets> turretList;
    private final GameInfo gameInfo;
    private final int turretType;
    private final ArrayList<Node> movingNode = new ArrayList<>();
    private final Pane layer;
    private final PositionControl positionControl;


    public DragDetector(Pane layer, ArrayList<Turrets> turretWaitingList, ArrayList<Turrets> turretList, GameInfo gameInfo, int turretType,
                        Map map, ArrayList<Turrets> onMapTurrets) {
        this.turretWaitingList = turretWaitingList;
        this.turretList = turretList;
        this.gameInfo = gameInfo;
        this.turretType = turretType;
        this.layer = layer;
        positionControl = new PositionControl(map, onMapTurrets, gameInfo.getWindowWidth(),
                gameInfo.getWindowHeight(), gameInfo.getTileSize());
    }

    public void handle(MouseEvent event) {
        Turrets turret;
        if (turretType == 0){
            turret = new DoubleGunTurret(gameInfo);
        }
        else if (turretType == 1){
            turret = new MissileLauncher(gameInfo);
        }
        else{
            turret = new MissileLauncher(gameInfo);
        }

        if (gameInfo.canBuy(turret.getCost())){
            if (!(movingNode.size() == 2)){
                Node turretImage = turret.drawTurret();
                Circle range = turret.drawRangeWithoutTimeline();

                movingNode.add(turretImage);
                movingNode.add(range);

                movingNode.get(0).relocate(-1000,-1000);

                turretWaitingList.add(turret);
                layer.getChildren().addAll(movingNode);
            }

            //layer.setOnMouseDragged(mouseEvent -> moveNodes(mouseEvent, turret, (Circle) movingNode.get(1)));
            //layer.setOnMouseReleased(mouseEvent -> spawnTurret(mouseEvent, turret));
        }
    }

    private void moveNodes(MouseEvent mouseEvent, Turrets turret, Circle range){
        if (turretWaitingList.size() == 1) {
            movingNode.get(0).relocate(mouseEvent.getX()-turret.getSize(), mouseEvent.getY() - turret.getSize());
            movingNode.get(1).relocate(mouseEvent.getX() - turret.getRange(), mouseEvent.getY() - turret.getRange());
            if (positionControl.posVerif((int) mouseEvent.getX(), (int) mouseEvent.getY(), turret.getSize())){
                range.setFill(Color.FORESTGREEN);
            }
            else {
                if ((int) mouseEvent.getY() >= gameInfo.getWindowHeight() - 30) {
                    range.setFill(Color.TRANSPARENT);
                } else {
                    range.setFill(Color.RED);
                } }
        }
        else {
            movingNode.clear();
        }
    }

    private void relocateTurret(MouseEvent mouseEvent, Turrets turret){
        turret.setXTurret((int)mouseEvent.getX() - turret.getSize());
        turret.setYTurret((int)mouseEvent.getY() - turret.getSize());
    }

    private void spawnTurret(MouseEvent mouseEvent, Turrets turret){
        if (turretWaitingList.size() == 1){
            if (positionControl.posVerif((int)mouseEvent.getX(), (int)mouseEvent.getY(), turret.getSize())){
                relocateTurret(mouseEvent, turret);
                turretList.add(turret);
            }
            else {
                System.out.println("can't put a turret here my friend");
            }
            turretWaitingList.remove(turret);
            layer.getChildren().removeAll(movingNode);
            movingNode.clear();
            System.out.println("done");
        }
    }

}
