package TowerDefense.UI;

import TowerDefense.ClickDropAttack;
import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.attacks.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class AttackTab {
    private final Pane interfaceLayer;
    private final GameInfo gameInfo;
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 18);
    private final Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 18);
    private final Font VerlagCondensedBoldSmall =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 16);
    private final DecimalFormat df = new DecimalFormat("0.00");

    public AttackTab(Pane interfaceLayer, GameInfo gameInfo) {
        this.interfaceLayer = interfaceLayer;
        this.gameInfo = gameInfo;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateAttackTab() {
        Group group = new Group();

        ImageView attackTab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/tab1.png")));
        attackTab.relocate(30, 800); // 0 - 570

        VBox attackNamesBox = new VBox();
        VBox levelCostBox = new VBox();
        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i <=6; i++) {
            Rectangle attackSlot = new Rectangle(100, 20, Color.TRANSPARENT);
            attackSlot.setStyle("-fx-stroke: transparent; -fx-stroke-width: 2;");
            attackSlot.setOnMouseClicked(new ClickDropAttack(attackSlot, interfaceLayer, gameInfo, i));
            attackSlot.setCursor(Cursor.HAND);
            shapes.add(attackSlot);

            Attack attack;
            if (shapes.indexOf(attackSlot) == 0){
                attack = new Explosion(-1000,-1000, gameInfo);
            }
            else if (shapes.indexOf(attackSlot) == 1){
                attack = new Mine(-1000,-1000, gameInfo);
            }
            else if (shapes.indexOf(attackSlot) == 2){
                attack = new Lightning(-1000,-1000, gameInfo);
            }
            else if (shapes.indexOf(attackSlot) == 3){
                attack = new Potion(-1000,-1000, gameInfo);
            }
            else if (shapes.indexOf(attackSlot) == 4){
                attack = new ChemicalAttack(-1000,-1000, gameInfo);
            }
            else if (shapes.indexOf(attackSlot) == 5){
                attack = new Mower(-1000,-1000, gameInfo);
            }
            else {
                attack = new LockDown(-1000, -1000,  gameInfo);
            }

            Text attackNameText = new Text(attack.getName());
            attackNameText.setFont(VerlagCondensedBold);
            attackNameText.setFill(Color.WHITE);
            attackNameText.setMouseTransparent(true);
            attackNameText.scaleXProperty().bind(attackSlot.scaleXProperty());
            attackNameText.scaleYProperty().bind(attackSlot.scaleYProperty());

            attackSlot.setOnMouseEntered(mouseEvent -> {
                attackSlot.setScaleX(1.1);
                attackSlot.setScaleY(1.1);
            });
            attackSlot.setOnMouseExited(mouseEvent -> {
                attackSlot.setScaleX(1);
                attackSlot.setScaleY(1);
            });

            StackPane cell = new StackPane(attackSlot, attackNameText);
            StackPane.setAlignment(attackNameText, Pos.CENTER_LEFT);

            attackNamesBox.getChildren().add(cell);

            generateTooltip(attackSlot, attack);

            ImageView plus = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/plus.png")));
            plus.setFitHeight(10);
            plus.setPreserveRatio(true);
            plus.setMouseTransparent(true);
            VBox plusIcon = new VBox(plus);
            plusIcon.setMaxSize(12, 12);
            plusIcon.setAlignment(Pos.CENTER);
            plusIcon.setStyle("-fx-background-color: blue; -fx-background-radius: 2;");
            plusIcon.setCursor(Cursor.HAND);

            Text levelText = new Text("Level: " + attack.getLevel());
            levelText.setFont(VerlagCondensedBoldSmall);
            levelText.setFill(Color.WHITE);

            Text costText = new Text("Cost: " + df.format(attack.getCost()));
            costText.setFont(VerlagCondensedBoldSmall);
            costText.setFill(Color.WHITE);

            plusIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new ClickUpdateAttack(gameInfo, attack).handle(mouseEvent);
                    attack.refresh();
                    updateText(attack, levelText, costText);
                    System.out.println(attack.getLevel());
                    System.out.println(attack.getCost());
                }
            });

            HBox levelCostCell = new HBox();
            levelCostCell.setMinSize(120, 22);
            //levelCostCell.setStyle("-fx-background-color: green;");
            levelCostCell.getChildren().addAll(levelText, plusIcon, costText);
            HBox.setMargin(plusIcon, new Insets(1.5, 0, 0, -5));
            levelCostCell.setSpacing(15);

            levelCostBox.setPadding(new Insets(3.5, 0, 0, 0));
            levelCostBox.getChildren().add(levelCostCell);
        }


        HBox hbox = new HBox(attackNamesBox, levelCostBox);
        hbox.relocate(30, 800);
        hbox.setPadding(new Insets(34, 12, 12, 20));
        hbox.setSpacing(10);

        group.getChildren().addAll(attackTab, hbox);

        return group;
    }

    private void generateTooltip(Rectangle attackSlot, Attack attack) {

        StackPane attackInfo = new StackPane();
        attackInfo.setPrefSize(150, 110);
        attackInfo.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        generateInfo(attack, attackInfo);

        Tooltip tooltip = new Tooltip(attack.getName());
        Tooltip.install(attackSlot, tooltip);
        tooltip.setGraphic(attackInfo);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);

        tooltip.setShowDelay(new Duration(50));
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setHideOnEscape(false);

        tooltip.setStyle("-fx-background-color: rgba(30,30,30,0.8);");
        tooltip.setFont(VerlagBold);
    }

    private void updateText(Attack attack, Text levelText, Text costText){
        levelText.setText("Level: " + attack.getLevel());
        costText.setText("Cost: " + df.format(attack.getCost()));
    }

    private void generateInfo(Attack attack, StackPane attackInfo) {

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 110);
        vboxImage.setSpacing(5);

        ImageView damage = createImage("damage");
        ImageView radius = createImage("range");
        ImageView cost = createImage("cost");

        vboxImage.getChildren().addAll(damage, radius, cost);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(5, -10, 0, 0));
        vboxText.setSpacing(12);
        Text damageText = createText("Damage: ");
        Text radiusText = createText("Radius: ");
        Text costText = createText("Cost: ");

        vboxText.getChildren().addAll(damageText, radiusText, costText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(5, 10, 0, 0));
        vboxValue.setSpacing(12);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(attack.getDamage()));
        Text radiusValue = createText(df.format(attack.getRadius()));
        Text costValue = createText(df.format(attack.getCost()));

        vboxValue.getChildren().addAll(damageValue, radiusValue, costValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(10, 0, 0, 10));
        hboxInfo.setSpacing(10);

        attackInfo.getChildren().addAll(hboxInfo);
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




