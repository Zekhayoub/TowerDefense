package TowerDefense.logbook;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import TowerDefense.turret.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class LogbookTurret {
    private GameInfo gameInfo;
    private LogbookScreen logbookScreen;
    private ArrayList<Shape> waitingShapes;
    private Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 22);
    private Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 20);
    private DecimalFormat df = new DecimalFormat("0.00");
    private Font VerlagLightItalic =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-LightItalic.otf"), 20);

    public LogbookTurret(GameInfo gameInfo, LogbookScreen logbookScreen) {
        this.gameInfo = gameInfo;
        this.logbookScreen = logbookScreen;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateLogbookTurret() {
        Group group = new Group();

        Rectangle turretTab = new Rectangle(700, 480, Color.BLACK); // RED
        //turretTab.setStroke(Color.WHITE);
        //turretTab.setStrokeWidth(1);
        turretTab.relocate(70, 210);
        turretTab.setArcWidth(10);
        turretTab.setArcHeight(10);

        GridPane gridPane = new GridPane();
        gridPane.relocate(70, 210);
        gridPane.setPadding(new Insets(19, 52, 19, 52));
        gridPane.setHgap(20); //8 - 15
        gridPane.setVgap(20); //8 - 15
        //gridPane.setStyle("-fx-background-color: blue;");

        ArrayList<ImageView> turretPreviews = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            ImageView turretPreview = new ImageView(new Image(Main.class.getResourceAsStream(
                    "resources/image/turretsPreview/preview" + i + ".png")));
            turretPreview.setFitHeight(130);
            turretPreview.setPreserveRatio(true);
            turretPreview.setMouseTransparent(true);
            turretPreviews.add(turretPreview);
        }

        ArrayList<Shape> shapes = new ArrayList<>();
        waitingShapes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Rectangle turretSlot = new Rectangle(130, 130, Color.WHITE);
            turretSlot.setArcWidth(10);
            turretSlot.setArcHeight(10);
            turretSlot.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            turretSlot.setCursor(Cursor.HAND);
            shapes.add(turretSlot);
            turretSlot.setOnMouseClicked(mouseEvent -> {
                if (waitingShapes.size() == 1) {
                    waitingShapes.get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
                    waitingShapes.clear();
                    logbookScreen.getInformationTab().getChildren().clear();
                }
                turretSlot.setStyle("-fx-stroke: green; -fx-stroke-width: 2;");
                waitingShapes.add(turretSlot);
                StackPane stackpane = generateInfoTurret(shapes, turretPreviews, turretSlot);
                logbookScreen.getInformationTab().getChildren().add(stackpane);
                //System.out.println(waitingShapes);
                //System.out.println(logbookScreen.getInformationTab().getChildren());
            });
            turretSlot.setOnMouseEntered(mouseEvent -> {
                turretSlot.setScaleX(1.04);
                turretSlot.setScaleY(1.04);
            });

            turretSlot.setOnMouseExited(mouseEvent -> {
                turretSlot.setScaleX(1);
                turretSlot.setScaleY(1);
                turretSlot.setFill(Color.WHITE);
            });
        }

        ArrayList<ImageView> turretImages = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            ImageView turretImage = new ImageView(new Image(Main.class.getResourceAsStream(
                    "resources/image/turrets/" + i + ".png")));
            turretImage.setFitHeight(70); //75 - 80
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
        shapes.get(9).setMouseTransparent(true);
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

    private StackPane generateInfoTurret(ArrayList<Shape> shapes, ArrayList<ImageView> turretPreviews,
                                         Rectangle turretSlot) { // void
        Turrets turret;
        Pane layer = new Pane();
        Text turretDescription;
        ImageView turretPreview; // temporaire
        if (shapes.indexOf(turretSlot) == 0){
            turret = new GunTurret(gameInfo);
            turretDescription = createText("The basic turret necessary" + "\n" +
                    "for any good arsenal", VerlagLightItalic);
            turretPreview = turretPreviews.get(0);
        }
        else if (shapes.indexOf(turretSlot) == 1){
            turret = new DoubleGunTurret(gameInfo);
            turretDescription = createText("Two guns for twice as much damage", VerlagLightItalic);
            turretPreview = turretPreviews.get(1);
        }
        else if (shapes.indexOf(turretSlot) == 2){
            turret = new MachineGun(gameInfo);
            turretDescription = createText("Quick as a flash", VerlagLightItalic);
            turretPreview = turretPreviews.get(2);
        }
        else if (shapes.indexOf(turretSlot) == 3){
            turret = new ShotgunTurret(gameInfo);
            turretDescription = createText("Drowns enemies under a wave of bullets", VerlagLightItalic);
            turretPreview = turretPreviews.get(3);
        }
        else if (shapes.indexOf(turretSlot) == 4){
            turret = new SniperTurret(gameInfo, layer);
            turretDescription = createText("When precision meets efficiency", VerlagLightItalic);
            turretPreview = turretPreviews.get(4);
        }
        else if (shapes.indexOf(turretSlot) == 5){
            turret = new CrossbowTurret(gameInfo);
            turretDescription = createText("Medieval, but no less efficient", VerlagLightItalic);
            turretPreview = turretPreviews.get(5);
        }
        else if (shapes.indexOf(turretSlot) == 6){
            turret = new MissileLauncher(gameInfo);
            turretDescription = createText("Perfect for cleaning the area", VerlagLightItalic);
            turretPreview = turretPreviews.get(6);
        }
        else if (shapes.indexOf(turretSlot) == 7) {
            turret = new IceTurret(gameInfo);
            turretDescription = createText("Freezes the zombies forever", VerlagLightItalic);
            turretPreview = turretPreviews.get(7);
        }
        else if (shapes.indexOf(turretSlot) == 8) {
            turret = new ElectricTurret(gameInfo, layer);
            turretDescription = createText("Seeing this turret," + "\n" +
                    "enemies will be shocked!", VerlagLightItalic);
            turretPreview = turretPreviews.get(8);
        }
        else {
            turret = new ElectricTurret(gameInfo, layer);
            turretDescription = createText("Seeing this turret," + "\n" +
                    "enemies will be shocked!", VerlagLightItalic);
            turretPreview = turretPreviews.get(8);
        }
        turretDescription.setTextAlignment(TextAlignment.CENTER);
        StackPane.setMargin(turretDescription, new Insets(80, 0, 0, 0));
        StackPane.setAlignment(turretDescription, Pos.CENTER);

        StackPane turretInfo = new StackPane();
        turretInfo.relocate(800, 220);
        turretInfo.setPrefSize(320, 460);
        turretInfo.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 5;");

        Text turretName = createText(turret.getName(), VerlagBold);
        StackPane.setAlignment(turretName, Pos.TOP_CENTER);
        StackPane.setMargin(turretName, new Insets(25, 0, 0, 0));

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 170);
        vboxImage.setSpacing(5);
        vboxImage.setPadding(new Insets(0, -10, 0, 50));

        ImageView damage = createImage("damage");
        ImageView range = createImage("range");
        ImageView speed = createImage("speed");
        ImageView accuracy = createImage("accuracy");
        ImageView cost =createImage("cost");

        vboxImage.getChildren().addAll(damage, range, speed, accuracy, cost);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(8, -10, 0, 0));
        vboxText.setSpacing(15);
        vboxText.setMaxHeight(170);
        Text damageText = createText("Damage: ", VerlagCondensedBold);
        Text rangeText = createText("Range: ", VerlagCondensedBold);
        Text speedText = createText("Speed: ", VerlagCondensedBold);
        Text accuracyText = createText("Accuracy: ", VerlagCondensedBold);
        Text costText = createText("Cost: ", VerlagCondensedBold);

        vboxText.getChildren().addAll(damageText, rangeText, speedText, accuracyText, costText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(8, 10, 0, 0));
        vboxValue.setSpacing(15);
        vboxValue.setMaxHeight(170);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(turret.getDamage()), VerlagCondensedBold);
        Text rangeValue = createText(df.format(turret.getRange()), VerlagCondensedBold);
        Text speedValue = createText(df.format(turret.getSpeed()), VerlagCondensedBold);
        Text accuracyValue = createText(df.format(turret.getAccuracy()), VerlagCondensedBold);
        Text costValue = createText(df.format(turret.getCost()), VerlagCondensedBold);

        vboxValue.getChildren().addAll(damageValue, rangeValue, speedValue, accuracyValue, costValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(60, 0, 0, 10));
        hboxInfo.setSpacing(30);

        //ImageView turretPreview = turretPreviews.get(shapes.indexOf(turretSlot)); // définitif
        StackPane.setAlignment(turretPreview, Pos.BOTTOM_CENTER);
        StackPane.setMargin(turretPreview, new Insets(0, 0, 20, 0));

        turretInfo.getChildren().addAll(turretName, hboxInfo, turretDescription, turretPreview);

        return turretInfo;

    }

    private Text createText(String word, Font font){
        Text text = new Text(word);
        text.setFont(font);
        text.setFill(Color.BLACK);
        return text;
    }

    private ImageView createImage(String name) {
        ImageView image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/info/" + name + ".png")));
        image.setFitHeight(30);
        image.setPreserveRatio(true);
        return image;
    }

    public ArrayList<Shape> getWaitingShapes() { return waitingShapes; }
}
