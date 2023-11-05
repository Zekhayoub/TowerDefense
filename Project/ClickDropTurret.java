package TowerDefense;

import TowerDefense.turret.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ClickDropTurret implements EventHandler<MouseEvent> {
    private final ArrayList<Turrets> turretList;
    private final ArrayList<Turrets> turretWaitingList;
    private final ArrayList<Turrets> onMapTurrets;
    private final Map map;
    private final int windowHeight;
    private final int windowWidth;
    private final int tileSize;
    private final int turretType;
    private final Rectangle rectangle;
    private final Pane layer;
    private final GameInfo gameInfo;
    private final ArrayList<Text> textList;
    private final Font BebasNeue =
            Font.loadFont(getClass()
                    .getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 24);
    private final DecimalFormat df = new DecimalFormat("0.00");

    public ClickDropTurret(ArrayList<Turrets> turretWaitingList, ArrayList<Turrets> turretList, Map map,
                           ArrayList<Turrets> onMapTurrets, Rectangle rectangle, Pane layer, GameInfo gameInfo, int turretType, ArrayList<Text> textList) {
        this.map = map;
        this.turretList = turretList;
        this.turretWaitingList = turretWaitingList;
        this.onMapTurrets = onMapTurrets;
        this.windowWidth= gameInfo.getWindowWidth();
        this.windowHeight = gameInfo.getWindowHeight();
        this.tileSize = gameInfo.getTileSize();
        this.rectangle = rectangle;
        this.layer = layer;
        this.gameInfo = gameInfo;
        this.turretType = turretType;
        this.textList = textList;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        turretWaitingList.clear();
        layer.getChildren().removeAll(textList);
        textList.clear();

        if (rectangle.getStyle().equals("-fx-stroke: transparent; -fx-stroke-width: 2;")) {
            rectangle.setStyle("-fx-stroke: green; -fx-stroke-width: 2;");

            Turrets turret;
            if (turretType == 0){
                turret = new GunTurret(gameInfo);
            }
            else if (turretType == 1){
                turret = new DoubleGunTurret(gameInfo);
            }
            else if (turretType == 2){
                turret = new MachineGun(gameInfo);
            }
            else if (turretType == 3){
                turret = new ShotgunTurret(gameInfo);
            }
            else if (turretType == 4){
                turret = new SniperTurret(gameInfo, layer);
            }
            else if (turretType == 5){
                turret = new CrossbowTurret(gameInfo);
            }
            else if (turretType == 6){
                turret = new MissileLauncher(gameInfo);
            }
            else if (turretType == 7){
                turret = new IceTurret(gameInfo);
            }
            else if (turretType == 8){
                turret = new ElectricTurret(gameInfo, layer);
            }
            else {
                turret = new ElectricTurret(gameInfo, layer);
            }


            turretWaitingList.add(turret);
            Circle range = turret.drawRangeWithoutTimeline();
            Node turretC = turret.drawTurret();
            range.relocate(-100, -100);
            turretC.relocate(-100,-100);
            layer.getChildren().addAll(range, turretC);

            PositionControl positionControl = new PositionControl(map, onMapTurrets, windowWidth, windowHeight, tileSize);

            layer.setOnMouseMoved(mouseEvent1 -> moveCircles(mouseEvent1, turret, range, turretC, positionControl));

            layer.setOnMouseClicked(mouseEvent1 -> spawnTurret(mouseEvent1, turret, positionControl, range, turretC));
        }
    }


    private void moveCircles(MouseEvent mouseEvent1, Turrets turret, Circle range, Node turretC, PositionControl positionControl){
        range.relocate(mouseEvent1.getX()-turret.getRange(), mouseEvent1.getY()-turret.getRange());
        turretC.relocate(mouseEvent1.getX()-(int)(turret.getSize()/2), mouseEvent1.getY()-(int) (turret.getSize()*3/4));
            if (positionControl.posVerif((int) mouseEvent1.getX(), (int) mouseEvent1.getY(), turret.getSize())){
                range.setFill(Color.FORESTGREEN);
                }
                else {
                    if ((int) mouseEvent1.getY() >= windowHeight - 30) {
                        range.setFill(Color.TRANSPARENT);
                    } else {
                        range.setFill(Color.RED);
                    } }
    }

    private void spawnTurret(MouseEvent mouseEvent1, Turrets turret, PositionControl positionControl, Circle range, Node turretC){
        if (mouseEvent1.getButton() == MouseButton.PRIMARY){
            if (mouseEvent1.getTarget() != rectangle && gameInfo.canBuy(turret.getCost()) && turretWaitingList.size() == 1) {
                Turrets turret1 = turretWaitingList.get(0);
                verifSurrounding(positionControl, mouseEvent1, turret1, turret.getSize()/2);
                rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
                layer.getChildren().removeAll(textList);
                layer.getChildren().removeAll(turretC, range);
                turretWaitingList.clear();
                textList.clear();
            }
            else if (mouseEvent1.getTarget() != rectangle && !gameInfo.canBuy(turret.getCost()) && turretWaitingList.size() == 1){
                new Alert(Alert.AlertType.INFORMATION, "You don't have enough money").showAndWait();
                rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
                layer.getChildren().removeAll(textList);
                layer.getChildren().removeAll(turretC, range);
                turretWaitingList.clear();
                textList.clear();
            }
        }
        else {
            rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
            layer.getChildren().removeAll(turretC, range);
            turretWaitingList.clear();
        }
    }

    private void generateText(Turrets turret){
        //Turret Description
        Text text = new Text(310,705, "Damage: " + df.format(turret.getDamage()) +
                "\n" + "Range: " + df.format(turret.getRange()) + "\n" +
                "Speed: " + df.format(turret.getSpeed()) + "\n" + "Accuracy: " + df.format(turret.getAccuracy()));
        Text cost = new Text(800, 705, "Cost: "+ df.format(turret.getCost()) + "$\n" + "Update Cost: " + df.format(turret.getUpdateCost()) + "$");
        text.setFont(BebasNeue); text.setFill(Color.WHITE);
        cost.setFont(BebasNeue); cost.setFill(Color.WHITE);
        textList.add(text); textList.add(cost);
        layer.getChildren().addAll(textList);
    }

    private void verifSurrounding(PositionControl positionControl, MouseEvent mouseEvent1, Turrets turret1, int security){
        if (positionControl.posVerif((int) mouseEvent1.getX(), (int) mouseEvent1.getY(), turret1.getSize())) {
            placeTurret(mouseEvent1, turret1 , 0,0);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX()-security, (int) mouseEvent1.getY(), turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , -security,0);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX()+security, (int) mouseEvent1.getY(), turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , +security,0);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX(), (int) mouseEvent1.getY()-security, turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , 0,-security);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX(), (int) mouseEvent1.getY()+security, turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , 0,+security);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX()-security, (int) mouseEvent1.getY()-security, turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , -security,-security);
        }
        else if (positionControl.posVerif((int) mouseEvent1.getX()+security, (int) mouseEvent1.getY()+security, turret1.getSize())){
            placeTurret(mouseEvent1, turret1 , +security,+security);
        }
        else {
            System.out.println("Can't add turret here");
        }
    }

    private void placeTurret(MouseEvent mouseEvent1, Turrets turret1, int dx, int dy){
        turret1.setXTurret((int) mouseEvent1.getX() - turret1.getWidth()/2+ dx);
        turret1.setYTurret((int) mouseEvent1.getY() - turret1.getHeight()*3/4 + dy);
        turretList.add(turret1);
        if (turret1 instanceof SniperTurret){
            ((SniperTurret) turret1).drawLine();
        }
        gameInfo.buyTurret(turret1.getCost());
    }
}


