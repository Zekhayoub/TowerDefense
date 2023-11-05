package TowerDefense.UI;

import TowerDefense.*;
import TowerDefense.turret.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class TurretTab {
    private final Pane interfaceLayer;
    private final Pane playLayer;
    private final ArrayList<Turrets> turretWaitingList;
    private final ArrayList<Turrets> turretList;
    private final ArrayList<Turrets> onMapTurrets;
    private final GameInfo gameInfo;
    private final Map map;
    private final ArrayList<Text> textList;
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 18);
    private final Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 18);
    private final DecimalFormat df = new DecimalFormat("0.00");

    public TurretTab(Pane interfaceLayer, Pane playLayer, ArrayList<Turrets> turretWaitingList, ArrayList<Turrets> turretList,
                     ArrayList<Turrets> onMapTurrets, GameInfo gameInfo, Map map, ArrayList<Text> textList) {
        this.interfaceLayer = interfaceLayer;
        this.turretWaitingList = turretWaitingList;
        this.turretList = turretList;
        this.onMapTurrets = onMapTurrets;
        this.gameInfo = gameInfo;
        this.map = map;
        this.textList = textList;
        this.playLayer = playLayer;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateTurretTap() {
        Group group = new Group();

        ImageView turretTab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/tab0.png")));
        turretTab.relocate(30, 800); // 0 - 570

        GridPane gridPane = new GridPane();
        gridPane.relocate(30, 800);
        gridPane.setPadding(new Insets(12, 12, 12, 12)); //18
        gridPane.setHgap(8); //8
        gridPane.setVgap(8); //8
        //gridPane.setStyle("-fx-background-color: blue;");

        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Rectangle turretSlot = new Rectangle(58, 58, Color.WHITE);
            turretSlot.setArcWidth(10);
            turretSlot.setArcHeight(10);
            turretSlot.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
            turretSlot.setOnMouseClicked(new ClickDropTurret(turretWaitingList, turretList, map, onMapTurrets,
                    turretSlot, interfaceLayer, gameInfo, i, textList));
            turretSlot.setCursor(Cursor.HAND);
            shapes.add(turretSlot);
            generateTooltip(shapes, turretSlot);

            turretSlot.setOnMouseEntered(mouseEvent -> {
                turretSlot.setScaleX(1.1);
                turretSlot.setScaleY(1.1);
            });

            turretSlot.setOnMouseExited(mouseEvent -> {
                turretSlot.setScaleX(1);
                turretSlot.setScaleY(1);
            });
        }

        ArrayList<ImageView> turretImages = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            ImageView turretImage = new ImageView(new Image(Main.class.getResourceAsStream(
                    "resources/image/turrets/" + i + ".png")));
            turretImage.setFitHeight(35);
            turretImage.setPreserveRatio(true);
            turretImage.setMouseTransparent(true);
            turretImages.add(turretImage);
            GridPane.setHalignment(turretImage, HPos.CENTER);
            GridPane.setValignment(turretImage, VPos.CENTER);
            turretImage.scaleXProperty().bind(shapes.get(i).scaleXProperty());
            turretImage.scaleYProperty().bind(shapes.get(i).scaleYProperty());
        }

        gridPane.add(shapes.get(0), 0, 0);
        gridPane.add(shapes.get(1), 1, 0);
        gridPane.add(shapes.get(2), 2, 0);
        gridPane.add(shapes.get(3), 3, 0);
        gridPane.add(shapes.get(4), 0, 1);
        gridPane.add(shapes.get(5), 1, 1);
        gridPane.add(shapes.get(6), 2, 1);
        gridPane.add(shapes.get(7), 3, 1);
        gridPane.add(shapes.get(8), 0, 2);
        gridPane.add(shapes.get(9), 1, 2);
        gridPane.add(shapes.get(10), 2, 2);
        gridPane.add(shapes.get(11), 3, 2);
        shapes.get(9).setMouseTransparent(true);                              // NEW
        shapes.get(10).setMouseTransparent(true);
        shapes.get(11).setMouseTransparent(true);

        gridPane.add(turretImages.get(0), 0, 0);
        gridPane.add(turretImages.get(1), 1, 0);
        gridPane.add(turretImages.get(2), 2, 0);
        gridPane.add(turretImages.get(3), 3, 0);
        gridPane.add(turretImages.get(4), 0, 1);
        gridPane.add(turretImages.get(5), 1, 1);
        gridPane.add(turretImages.get(6), 2, 1);
        gridPane.add(turretImages.get(7), 3, 1);
        gridPane.add(turretImages.get(8), 0, 2);

        group.getChildren().addAll(turretTab, gridPane);

        return group;
    }

    private void generateTooltip(ArrayList<Shape> shapes, Rectangle turretSlot) {
        Turrets turret;
        if (shapes.indexOf(turretSlot) == 0){
            turret = new GunTurret(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 1){
            turret = new DoubleGunTurret(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 2){
            turret = new MachineGun(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 3){
            turret = new ShotgunTurret(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 4){
            turret = new SniperTurret(gameInfo, playLayer);
        }
        else if (shapes.indexOf(turretSlot) == 5){
            turret = new CrossbowTurret(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 6){
            turret = new MissileLauncher(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 7){
            turret = new IceTurret(gameInfo);
        }
        else if (shapes.indexOf(turretSlot) == 8){
            turret = new ElectricTurret(gameInfo, playLayer);
        }
        else {
            turret = new ElectricTurret(gameInfo, playLayer);                             // NEW
        }

        StackPane turretInfo = new StackPane();
        turretInfo.setPrefSize(150, 170);
        turretInfo.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        generateInfo(turret, turretInfo);

        Tooltip tooltip = new Tooltip(turret.getName());
        Tooltip.install(turretSlot, tooltip);
        tooltip.setGraphic(turretInfo);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);

        tooltip.setShowDelay(new Duration(50));
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setHideOnEscape(false);

        tooltip.setStyle("-fx-background-color: rgba(30,30,30,0.8);");
        tooltip.setFont(VerlagBold);
    }

    private void generateInfo(Turrets turret, StackPane turretInfo) {

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 170);
        vboxImage.setSpacing(5);

        ImageView damage = createImage("damage");
        ImageView range = createImage("range");
        ImageView speed = createImage("speed");
        ImageView accuracy = createImage("accuracy");
        ImageView cost =createImage("cost");

        vboxImage.getChildren().addAll(damage, range, speed, accuracy, cost);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(5, -10, 0, 0));
        vboxText.setSpacing(12);
        Text damageText = createText("Damage: ");
        Text rangeText = createText("Range: ");
        Text speedText = createText("Speed: ");
        Text accuracyText = createText("Accuracy: ");
        Text costText = createText("Cost: ");

        vboxText.getChildren().addAll(damageText, rangeText, speedText, accuracyText, costText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(5, 10, 0, 0));
        vboxValue.setSpacing(12);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(turret.getDamage()));
        Text rangeValue = createText(df.format(turret.getRange()));
        Text speedValue = createText(df.format(turret.getSpeed()));
        Text accuracyValue = createText(df.format(turret.getAccuracy()));
        Text costValue = createText(df.format(turret.getCost()));

        vboxValue.getChildren().addAll(damageValue, rangeValue, speedValue, accuracyValue, costValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(10, 0, 0, 10));
        hboxInfo.setSpacing(10);

        turretInfo.getChildren().addAll(hboxInfo);

    }

    private Text createText(String word){
        Text text = new Text(word);
        text.setFont(VerlagCondensedBold);
        text.setFill(Color.BLACK);
        return text;
    }

    private ImageView createImage(String name) {
        ImageView image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/info/" + name + ".png")));
        image.setFitHeight(25);
        image.setPreserveRatio(true);
        return image;
    }
}
