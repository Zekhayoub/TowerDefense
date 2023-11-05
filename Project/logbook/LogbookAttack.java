package TowerDefense.logbook;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.attacks.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class LogbookAttack {
    private GameInfo gameInfo;
    private LogbookScreen logbookScreen;
    private ArrayList<Shape> waitingShapes;
    private Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 22);
    private Font VerlagCondensedBoldBig =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 36);
    private Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 20);
    private DecimalFormat df = new DecimalFormat("0.00");
    private Font VerlagLightItalic =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-LightItalic.otf"), 20);

    public LogbookAttack(GameInfo gameInfo, LogbookScreen logbookScreen) {
        this.gameInfo = gameInfo;
        this.logbookScreen = logbookScreen;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateLogbookAttack() {
        Group group = new Group();

        Rectangle attackTab = new Rectangle(700, 480, Color.BLACK); //GREEN
        attackTab.relocate(70, 210);
        attackTab.setArcWidth(10);
        attackTab.setArcHeight(10);

        ArrayList<ImageView> attackPreviews = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            ImageView attackPreview = new ImageView(new Image(Main.class.getResourceAsStream(
                    "resources/image/attacksPreview/preview" + i + ".png")));
            attackPreview.setFitHeight(130);
            attackPreview.setPreserveRatio(true);
            attackPreview.setMouseTransparent(true);
            attackPreviews.add(attackPreview);
        }

        VBox attackNamesBox = new VBox();
        ArrayList<Shape> shapes = new ArrayList<>();
        waitingShapes = new ArrayList<>();
        for (int i = 0; i <=6 ; i++) {
            Rectangle attackSlot = new Rectangle(200, 35, Color.TRANSPARENT);
            attackSlot.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            attackSlot.setCursor(Cursor.HAND);
            shapes.add(attackSlot);

            Attack attack;
            Text attackDescription;
            ImageView attackPreview;
            if (shapes.indexOf(attackSlot) == 0){
                attack = new Explosion(-1000,-1000, gameInfo);
                attackDescription = createText("What a magnificent explosion!", VerlagLightItalic);
            }
            else if (shapes.indexOf(attackSlot) == 1) {
                attack = new Mine(-1000, -1000, gameInfo);
                attackDescription = createText("Better not to get close to it!", VerlagLightItalic);
            }
            else if (shapes.indexOf(attackSlot) == 2) {
                attack = new Lightning(-1000, -1000, gameInfo);
                attackDescription = createText("In front of this brilliant attack, " + "\n" +
                        "enemies should bolt", VerlagLightItalic);
            }
            else if (shapes.indexOf(attackSlot) == 3) {
                attack = new Potion(-1000, -1000, gameInfo);
                attackDescription = createText("Gives you some HP", VerlagLightItalic);
            }
            else if (shapes.indexOf(attackSlot) == 4) {
                attack = new ChemicalAttack(-1000, -1000, gameInfo);
                attackDescription = createText("Produces a toxic gas, " + "\n" +
                        "which gradually poisons enemies", VerlagLightItalic);
            }
            else if (shapes.indexOf(attackSlot) == 5) {
                attack = new Mower(-1000, -1000, gameInfo);
                attackDescription = createText("Do you recognize it?", VerlagLightItalic);
            }
            else {
                attack = new LockDown(-1000, -1000, gameInfo);
                attackDescription = createText("Upgrade it to see its effects," + "\n" +
                        "you won't regret it", VerlagLightItalic);
            }
            attackPreview = attackPreviews.get(i);

            Text attackNameText = new Text(attack.getName());
            attackNameText.setFont(VerlagCondensedBoldBig);
            attackNameText.setFill(Color.WHITE);
            attackNameText.setMouseTransparent(true);
            attackNameText.scaleXProperty().bind(attackSlot.scaleXProperty());
            attackNameText.scaleYProperty().bind(attackSlot.scaleYProperty());

            attackSlot.setOnMouseClicked(mouseEvent -> {
                if (waitingShapes.size() == 1) {
                    waitingShapes.get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
                    waitingShapes.clear();
                    logbookScreen.getInformationTab().getChildren().clear();
                }
                attackSlot.setStyle("-fx-stroke: green; -fx-stroke-width: 2;");
                waitingShapes.add(attackSlot);
                StackPane stackpane = generateInfoAttack(attack, attackDescription, attackPreview);
                logbookScreen.getInformationTab().getChildren().add(stackpane);
                //System.out.println(waitingShapes);
                //System.out.println(logbookScreen.getInformationTab().getChildren());
            });

            attackSlot.setOnMouseEntered(mouseEvent -> {
                attackSlot.setScaleX(1.1);
                attackSlot.setScaleY(1.1);
            });

            attackSlot.setOnMouseExited(mouseEvent -> {
                attackSlot.setScaleX(1);
                attackSlot.setScaleY(1);
            });

            StackPane cell = new StackPane(attackSlot, attackNameText);
            StackPane.setAlignment(attackNameText, Pos.CENTER);
            cell.setPadding(new Insets(0, 0, 15, 0));
            //cell.setStyle("-fx-background-color: blue;");

            attackNamesBox.getChildren().add(cell);
        }


        HBox hbox = new HBox(attackNamesBox);
        hbox.relocate(70, 210);
        hbox.setPadding(new Insets(60, 12, -15, 250));
        //hbox.setStyle("-fx-background-color: yellow;");

        group.getChildren().addAll(attackTab, hbox);

        return group;
    }

    private StackPane generateInfoAttack(Attack attack, Text attackDescription, ImageView attackPreview) {

        StackPane attackInfo = new StackPane();
        attackInfo.relocate(800, 220);
        attackInfo.setPrefSize(320, 460);
        attackInfo.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 5;");

        attackDescription.setTextAlignment(TextAlignment.CENTER);
        StackPane.setMargin(attackDescription, new Insets(0, 0, 0, 0));
        StackPane.setAlignment(attackDescription, Pos.CENTER);

        Text attackName = createText(attack.getName(), VerlagBold);
        StackPane.setAlignment(attackName, Pos.TOP_CENTER);
        StackPane.setMargin(attackName, new Insets(45, 0, 0, 0));

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 170);
        vboxImage.setSpacing(5);
        vboxImage.setPadding(new Insets(0, -10, 0, 50));

        ImageView damage = createImage("damage");
        ImageView radius = createImage("range");
        ImageView cost = createImage("cost");

        vboxImage.getChildren().addAll(damage, radius, cost);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(8, -10, 0, 0));
        vboxText.setSpacing(15);
        vboxText.setMaxHeight(170);
        Text damageText = createText("Damage: ", VerlagCondensedBold);
        Text radiusText = createText("Radius: ", VerlagCondensedBold);
        Text costText = createText("Cost: ", VerlagCondensedBold);

        vboxText.getChildren().addAll(damageText, radiusText, costText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(8, 10, 0, 0));
        vboxValue.setSpacing(15);
        vboxValue.setMaxHeight(170);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(attack.getDamage()), VerlagCondensedBold);
        Text radiusValue = createText(df.format(attack.getRadius()), VerlagCondensedBold);
        Text costValue = createText(df.format(attack.getCost()), VerlagCondensedBold);

        vboxValue.getChildren().addAll(damageValue, radiusValue, costValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(80, 0, 0, 10));
        hboxInfo.setSpacing(30);

        StackPane.setAlignment(attackPreview, Pos.BOTTOM_CENTER);
        StackPane.setMargin(attackPreview, new Insets(0, 0, 50, 0));

        attackInfo.getChildren().addAll(attackName, hboxInfo, attackDescription, attackPreview);

        return attackInfo;
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
