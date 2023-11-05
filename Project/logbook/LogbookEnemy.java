package TowerDefense.logbook;

import TowerDefense.Main;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class LogbookEnemy {
    private LogbookScreen logbookScreen;
    private ArrayList<Shape> waitingShapes;
    private Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 22);
    private Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 20);
    private DecimalFormat df = new DecimalFormat("0.00");
    private Font VerlagLightItalic =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-LightItalic.otf"), 20);

    public LogbookEnemy(LogbookScreen logbookScreen) {
        this.logbookScreen = logbookScreen;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateLogbookEnemy() {
        Group group = new Group();

        Rectangle enemyTab = new Rectangle(700, 480, Color.BLACK); //BLUE
        enemyTab.relocate(70, 210);
        enemyTab.setArcWidth(10);
        enemyTab.setArcHeight(10);

        GridPane gridPane = new GridPane();
        gridPane.relocate(70, 210);
        gridPane.setPadding(new Insets(8, 52, 5.5, 52));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        ArrayList<Shape> enemies = new ArrayList<>();
        ArrayList<ImageView> enemiesImage = new ArrayList<>();
        waitingShapes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Rectangle enemyShape = new Rectangle(181, 139, Color.TRANSPARENT);
            enemyShape.setArcWidth(10);
            enemyShape.setArcHeight(10);
            enemyShape.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            enemyShape.setCursor(Cursor.HAND);
            enemies.add(enemyShape);

            enemyShape.setOnMouseClicked(mouseEvent -> {
                if (waitingShapes.size() == 1) {
                    waitingShapes.get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
                    waitingShapes.clear();
                    logbookScreen.getInformationTab().getChildren().clear();
                }
                enemyShape.setStyle("-fx-stroke: green; -fx-stroke-width: 2;");
                waitingShapes.add(enemyShape);
                StackPane stackpane = generateInfoEnemy(enemies, enemyShape);
                logbookScreen.getInformationTab().getChildren().add(stackpane);
            });

            enemyShape.setOnMouseEntered(mouseEvent -> {
                enemyShape.setScaleX(1.04);
                enemyShape.setScaleY(1.04);
            });

            enemyShape.setOnMouseExited(mouseEvent -> {
                enemyShape.setScaleX(1);
                enemyShape.setScaleY(1);
            });

            ImageView enemySlot = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/" +
                    "enemiesPreview/preview" + i + ".png")));
            enemySlot.setFitHeight(140);
            enemySlot.setPreserveRatio(true);
            enemySlot.setMouseTransparent(true);
            enemiesImage.add(enemySlot);
            GridPane.setHalignment(enemySlot, HPos.CENTER);
            GridPane.setValignment(enemySlot, VPos.CENTER);
            enemySlot.scaleXProperty().bind(enemyShape.scaleXProperty());
            enemySlot.scaleYProperty().bind(enemyShape.scaleYProperty());
        }

        gridPane.add(enemiesImage.get(0), 0, 0);
        gridPane.add(enemiesImage.get(1), 1, 0);
        gridPane.add(enemiesImage.get(2), 2, 0);
        gridPane.add(enemiesImage.get(3), 0, 1);
        gridPane.add(enemiesImage.get(4), 1, 1);
        gridPane.add(enemiesImage.get(5), 2, 1);
        gridPane.add(enemiesImage.get(6), 0, 2);
        gridPane.add(enemiesImage.get(7), 1, 2);
        gridPane.add(enemiesImage.get(8), 2, 2);

        gridPane.add(enemies.get(0), 0, 0);
        gridPane.add(enemies.get(1), 1, 0);
        gridPane.add(enemies.get(2), 2, 0);
        gridPane.add(enemies.get(3), 0, 1);
        gridPane.add(enemies.get(4), 1, 1);
        gridPane.add(enemies.get(5), 2, 1);
        gridPane.add(enemies.get(6), 0, 2);
        gridPane.add(enemies.get(7), 1, 2);
        gridPane.add(enemies.get(8), 2, 2);

        group.getChildren().addAll(enemyTab, gridPane);

        return group;
    }

    private StackPane generateInfoEnemy(ArrayList<Shape> enemies, Rectangle enemyShape) {
        EnemyDescription enemy;
        if (enemies.indexOf(enemyShape) == 0){
            enemy = new EnemyDescription(0);
        }
        else if (enemies.indexOf(enemyShape) == 1){
            enemy =  new EnemyDescription(1);
        }
        else if (enemies.indexOf(enemyShape) == 2){
            enemy =  new EnemyDescription(2);
        }
        else if (enemies.indexOf(enemyShape) == 3){
            enemy = new EnemyDescription(3);
        }
        else if (enemies.indexOf(enemyShape) == 4){
            enemy =  new EnemyDescription(4);
        }
        else if (enemies.indexOf(enemyShape) == 5){
            enemy = new EnemyDescription(5);
        }
        else if (enemies.indexOf(enemyShape) == 6){
            enemy = new EnemyDescription(6);

        }
        else if (enemies.indexOf(enemyShape) == 7){
            enemy = new EnemyDescription(7);
        }
        else {
            enemy = new EnemyDescription(8);
        }

        Text enemyDescription = createText(enemy.getTexts()[3], VerlagLightItalic);
        enemyDescription.setTextAlignment(TextAlignment.CENTER);
        StackPane.setMargin(enemyDescription, new Insets(180, 0, 0, 0));
        StackPane.setAlignment(enemyDescription, Pos.CENTER);

        StackPane enemyInfo = new StackPane();
        enemyInfo.relocate(800, 220);
        enemyInfo.setPrefSize(320, 460);
        enemyInfo.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 5;");

        Text enemyName = createText(enemy.getTexts()[0], VerlagBold);
        StackPane.setAlignment(enemyName, Pos.TOP_CENTER);
        StackPane.setMargin(enemyName, new Insets(65, 0, 0, 0));

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 170);
        vboxImage.setSpacing(5);
        vboxImage.setPadding(new Insets(0, -10, 0, 50));

        ImageView damage = createImage("damageenemy");
        ImageView health = createImage("healthenemy");
        ImageView reward = createImage("rewardenemy");
        ImageView speed = createImage("speed");
        ImageView infection = createImage("infectionenemy");

        vboxImage.getChildren().addAll(damage, health, reward, speed, infection);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(8, -10, 0, 0));
        vboxText.setSpacing(15);
        vboxText.setMaxHeight(170);
        Text damageText = createText("Damage: ", VerlagCondensedBold);
        Text healthText = createText("Health: ", VerlagCondensedBold);
        Text rewardText = createText("Reward: ", VerlagCondensedBold);
        Text speedText = createText("Speed: ", VerlagCondensedBold);
        Text infectionText = createText("Infection: ", VerlagCondensedBold);

        vboxText.getChildren().addAll(damageText, healthText, rewardText, speedText, infectionText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(8, 0, 0, -10));
        vboxValue.setSpacing(15);
        vboxValue.setMaxHeight(170);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(enemy.getValues()[0]), VerlagCondensedBold);
        Text healthValue = createText(df.format(enemy.getValues()[1]), VerlagCondensedBold);
        Text reawardValue = createText(df.format(enemy.getValues()[2]), VerlagCondensedBold);
        Text speedValue = createText(enemy.getTexts()[1], VerlagCondensedBold);
        Text infectionValue = createText(enemy.getTexts()[2], VerlagCondensedBold);

        vboxValue.getChildren().addAll(damageValue, healthValue, reawardValue, speedValue, infectionValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(100, 0, 0, 10));
        hboxInfo.setSpacing(30);

        enemyInfo.getChildren().addAll(enemyName, hboxInfo, enemyDescription);

        return enemyInfo;
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
