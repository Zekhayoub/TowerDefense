package TowerDefense;

import TowerDefense.attacks.*;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class ClickDropAttack implements EventHandler<MouseEvent> {
    private final Rectangle rectangle;
    private final Pane layer;
    private final int windowHeight;
    private final GameInfo gameInfo;
    private final int attackNumber;
    private ArrayList<Attack> attacks = new ArrayList<>();

    public ClickDropAttack(Rectangle rectangle, Pane layer, GameInfo gameInfo, int attackNumber) {
        this.rectangle = rectangle;
        this.layer = layer;
        this.windowHeight = gameInfo.getWindowHeight();
        this.gameInfo = gameInfo;
        this.attackNumber = attackNumber;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (rectangle.getStyle().equals("-fx-stroke: transparent; -fx-stroke-width: 2;")) {
            rectangle.setStyle("-fx-stroke: green; -fx-stroke-width: 2;");
            Attack attack;
            if (attackNumber == 0){
                attack = new Explosion(-1000,-1000, gameInfo);
            }
            else if (attackNumber == 1){
                attack = new Mine(-1000,-1000, gameInfo);
            }
            else if (attackNumber == 2){
                attack = new Lightning(-1000,-1000, gameInfo);
            }
            else if (attackNumber == 3){
                attack = new Potion(-1000,-1000, gameInfo);
            }
            else if (attackNumber == 4){
                attack = new ChemicalAttack(-1000,-1000, gameInfo);
            }
            else if (attackNumber == 5){
                attack = new Mower(-1000,-1000, gameInfo);
            }
            else {
                attack = new LockDown(-1000, -1000,  gameInfo);
            }
            attacks.clear();
            attacks.add(attack);

            Shape radius = attack.drawRadius(attack.getX(), attack.getY());
            radius.setOpacity(0.5);

            layer.getChildren().add(radius);

            if (attack instanceof InstantEffect){
                instantAttack(mouseEvent, attacks, radius);
            }
            else {
                layer.setOnMouseMoved(e -> moveCircles(e, attack, radius));
                layer.setOnMouseClicked(e -> launchAttack(e, attacks, radius));
            }
        }
    }
    private void moveCircles(MouseEvent e, Attack attack, Shape radius){
        if (radius instanceof Circle){
            radius.relocate(e.getX()-attack.getRadius(), e.getY()-attack.getRadius());
        }
        else if (radius instanceof Rectangle){
            ((Rectangle) radius).setLayoutY(e.getY()-(double)gameInfo.getTileSize()/2);
        }

        if ((int) e.getY() >= windowHeight - 30) {
            radius.setFill(Color.TRANSPARENT);
        } else {
            radius.setFill(attack.getRadiusColor());
        }
    }

    private void instantAttack(MouseEvent e, ArrayList<Attack> attacks, Shape radius){
        if (e.getButton() == MouseButton.PRIMARY && attacks.size() == 1 && gameInfo.canBuy(attacks.get(0).getCost())){
            attacks.get(0).relocate((int)e.getX(),(int) e.getY());
            attacks.get(0).launchAttack();
            attacks.get(0).attackAnimation(layer);
            rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
            layer.getChildren().removeAll(radius);
            gameInfo.addAttack(attacks.get(0));
            attacks.clear();
        }
    }

    private void launchAttack(MouseEvent e, ArrayList<Attack> attacks, Shape radius){
        if (e.getButton() == MouseButton.PRIMARY && attacks.size() == 1){
            if (e.getTarget() != rectangle && gameInfo.canBuy(attacks.get(0).getCost()) && e.getY() < windowHeight - 30) {
                attacks.get(0).relocate((int)e.getX(),(int) e.getY());
                attacks.get(0).launchAttack();
                attacks.get(0).attackAnimation(layer);
                gameInfo.buyTurret(attacks.get(0).getCost());
                rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
                layer.getChildren().removeAll(radius);
                gameInfo.addAttack(attacks.get(0));
                attacks.clear();
            }
            else if (e.getTarget() != rectangle && !gameInfo.canBuy(attacks.get(0).getCost())){
                new Alert(Alert.AlertType.INFORMATION, "You don't have enough money").showAndWait();
                rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
                layer.getChildren().removeAll(radius);
                attacks.clear();
            }
        }
        else {
            rectangle.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
            layer.getChildren().removeAll(radius);
            attacks.clear();
        }

    }
}
