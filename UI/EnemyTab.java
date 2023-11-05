package TowerDefense.UI;

import TowerDefense.Main;
import TowerDefense.logbook.EnemyDescription;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class EnemyTab {
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 18);
    private final Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 18);
    private final DecimalFormat df = new DecimalFormat("0.00");

    public EnemyTab() {
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }

    public Group generateEnemyTab() {
        Group group = new Group();

        ImageView enemyTab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/tab2.png")));
        enemyTab.relocate(30, 800); // 288 - 220

        GridPane gridPane = new GridPane();
        gridPane.relocate(30, 800);
        gridPane.setPadding(new Insets(15, 12, 12, 19));
        gridPane.setHgap(10);
        gridPane.setVgap(8);

        ArrayList<ImageView> enemies = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ImageView enemySlot = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/" +
                    "enemiesPreview/preview" + i + ".png")));
            enemySlot.setFitHeight(58);
            enemySlot.setPreserveRatio(true);
            enemySlot.setCursor(Cursor.HAND);
            enemies.add(enemySlot);
            generateTooltip(enemies, enemySlot);

            enemySlot.setOnMouseEntered(mouseEvent -> {
                enemySlot.setScaleX(1.1);
                enemySlot.setScaleY(1.1);
            });

            enemySlot.setOnMouseExited(mouseEvent -> {
                enemySlot.setScaleX(1);
                enemySlot.setScaleY(1);
            });

        }

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


    private void generateTooltip(ArrayList<ImageView> enemies, ImageView enemySlot) {
        EnemyDescription enemy;
        if (enemies.indexOf(enemySlot) == 0){
            enemy = new EnemyDescription(0);
        }
        else if (enemies.indexOf(enemySlot) == 1){
            enemy =  new EnemyDescription(1);
        }
        else if (enemies.indexOf(enemySlot) == 2){
            enemy =  new EnemyDescription(2);
        }
        else if (enemies.indexOf(enemySlot) == 3){
            enemy = new EnemyDescription(3);
        }
        else if (enemies.indexOf(enemySlot) == 4){
            enemy =  new EnemyDescription(4);
        }
        else if (enemies.indexOf(enemySlot) == 5){
            enemy = new EnemyDescription(5);
        }
        else if (enemies.indexOf(enemySlot) == 6){
            enemy = new EnemyDescription(6);

        }
        else if (enemies.indexOf(enemySlot) == 7){
            enemy = new EnemyDescription(7);
        }
        else {
            enemy = new EnemyDescription(8);
        }

        StackPane enemyInfo = new StackPane();
        enemyInfo.setPrefSize(150, 170);
        enemyInfo.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        generateInfo(enemy, enemyInfo);

        Tooltip tooltip = new Tooltip(enemy.getTexts()[0]);
        Tooltip.install(enemySlot, tooltip);
        tooltip.setGraphic(enemyInfo);
        tooltip.setContentDisplay(ContentDisplay.BOTTOM);

        tooltip.setShowDelay(new Duration(50));
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setHideOnEscape(false);

        tooltip.setStyle("-fx-background-color: rgba(30,30,30,0.8);");
        tooltip.setFont(VerlagBold);
    }

    private void generateInfo(EnemyDescription enemy, StackPane enemyInfo) {

        VBox vboxImage = new VBox();
        //vboxImage.setStyle("-fx-background-color: red;");
        vboxImage.setMaxSize(20, 170);
        vboxImage.setSpacing(5);

        ImageView damage = createImage("damageenemy");
        ImageView health = createImage("healthenemy");
        ImageView reward = createImage("rewardenemy");
        ImageView speed = createImage("speed");
        ImageView infection = createImage("infectionenemy");

        vboxImage.getChildren().addAll(damage, health, reward, speed, infection);

        VBox vboxText = new VBox();
        //vboxText.setStyle("-fx-background-color: green;");
        vboxText.setPadding(new Insets(5, -10, 0, 0));
        vboxText.setSpacing(12);
        Text damageText = createText("Damage: ");
        Text healthText = createText("Health: ");
        Text rewardText = createText("Reward: ");
        Text speedText = createText("Speed: ");
        Text infectionText = createText("Infection: ");

        vboxText.getChildren().addAll(damageText, healthText, rewardText, speedText, infectionText);

        VBox vboxValue = new VBox();
        //vboxValue.setStyle("-fx-background-color: green;");
        vboxValue.setPadding(new Insets(5, 10, 0, 0));
        vboxValue.setSpacing(12);
        vboxValue.setAlignment(Pos.TOP_RIGHT);
        Text damageValue = createText(df.format(enemy.getValues()[0]));
        Text healthValue = createText(df.format(enemy.getValues()[1]));
        Text reawardValue = createText(df.format(enemy.getValues()[2]));
        Text speedValue = createText(enemy.getTexts()[1]);
        Text infectionValue = createText(enemy.getTexts()[2]);

        vboxValue.getChildren().addAll(damageValue, healthValue, reawardValue, speedValue, infectionValue);

        HBox hboxInfo = new HBox(vboxImage, vboxText, vboxValue);
        hboxInfo.setPadding(new Insets(10, 0, 0, 10));
        hboxInfo.setSpacing(10);

        enemyInfo.getChildren().addAll(hboxInfo);

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



