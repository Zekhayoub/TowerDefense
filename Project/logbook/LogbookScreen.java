package TowerDefense.logbook;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.gameOver.GameOverItem;
import TowerDefense.menu.Menu;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class LogbookScreen {
    private final Pane logbookLayer;
    private final VBox informationTab;
    private final GameInfo gameInfo;
    private final Menu menu;
    private final Font BebasNeue =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/BebasNeue-Regular.ttf"), 70);
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 24);

    public LogbookScreen(Menu menu, GameInfo gameInfo) {
        this.logbookLayer = new Pane();
        this.informationTab = new VBox();
        this.menu = menu;
        this.gameInfo = gameInfo;
    }

    public Pane initLogbookScreen(){

        Rectangle background = new Rectangle(gameInfo.getWindowWidth(), gameInfo.getWindowHeight(), Color.BLACK);
        background.heightProperty().bind(gameInfo.getRealWindowHeight());
        background.widthProperty().bind(gameInfo.getRealWindowWidth());

        //logbookLayer.setPrefSize(1200, 800);
        logbookLayer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        logbookLayer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());

        Text logbook = new Text("LOGBOOK");
        logbook.setFont(BebasNeue);
        logbook.setFill(Color.WHITE);
        logbook.relocate(70, 30);

        GridPane tabtitles = new GridPane();
        tabtitles.relocate(70, 120);
        tabtitles.setHgap(20);
        ArrayList<ImageView> titleBackgrounds = new ArrayList<>();
        ArrayList<Text> titleTexts = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            ImageView titleBackground = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/logbookTab" + i + ".png")));
            titleBackground.setCursor(Cursor.HAND);
            titleBackgrounds.add(titleBackground);
            tabtitles.add(titleBackground, i, 0);

            titleBackground.setOnMouseEntered(mouseEvent -> {
                titleBackground.setScaleX(1.04);
                titleBackground.setScaleY(1.04);
            });

            titleBackground.setOnMouseExited(mouseEvent -> {
                titleBackground.setScaleX(1);
                titleBackground.setScaleY(1);
            });

            Text title = new Text("");
            title.setFont(VerlagBold);
            title.setFill(Color.WHITE);
            title.setTextAlignment(TextAlignment.CENTER);
            title.scaleXProperty().bind(titleBackground.scaleXProperty());
            title.scaleYProperty().bind(titleBackground.scaleYProperty());
            title.setMouseTransparent(true);
            titleTexts.add(title);
            tabtitles.add(title, i, 0);
            GridPane.setHalignment(title, HPos.CENTER);
            GridPane.setValignment(title, VPos.CENTER);
        }

        titleTexts.get(0).setText("Turrets");
        titleTexts.get(1).setText("Attacks");
        titleTexts.get(2).setText("Enemies");

        LogbookTurret logbookTurret = new LogbookTurret(gameInfo,this);                     // NEW
        Group logbookTurretGroup = logbookTurret.generateLogbookTurret();

        LogbookAttack logbookAttack = new LogbookAttack(gameInfo, this);
        Group logbookAttackGroup = logbookAttack.generateLogbookAttack();

        LogbookEnemy logbookEnemy = new LogbookEnemy(this);
        Group logbookEnemyGroup = logbookEnemy.generateLogbookEnemy();

        titleBackgrounds.get(0).setOnMouseClicked(new ClickTabLogbook(logbookTurretGroup, informationTab, logbookTurret,
                logbookAttack, logbookEnemy));
        titleBackgrounds.get(1).setOnMouseClicked(new ClickTabLogbook(logbookAttackGroup, informationTab, logbookTurret,
                logbookAttack, logbookEnemy));
        titleBackgrounds.get(2).setOnMouseClicked(new ClickTabLogbook(logbookEnemyGroup, informationTab, logbookTurret,
                logbookAttack, logbookEnemy));

        informationTab.relocate(790, 210);
        informationTab.setPrefSize(340, 480);
        informationTab.setStyle("-fx-border-color: white; -fx-border-width: 1;");
        informationTab.setPadding(new Insets(10, 10, 10, 10));

        GameOverItem backButton = new GameOverItem("BACK", 1);
        backButton.relocate(30, 730);
        backButton.setOnMouseClicked(new ClickBackFromLogbook(menu, this));
        logbookLayer.setOnKeyPressed(new GoBackFromLogbook(menu, gameInfo, this));

        //System.out.println("0" + logbookLayer.getChildren());
        logbookLayer.getChildren().addAll(background, logbook, backButton, tabtitles, informationTab,
                logbookEnemyGroup, logbookAttackGroup, logbookTurretGroup);
        //System.out.println("1" + logbookLayer.getChildren());

        return logbookLayer;
    }

    public Pane getLogbookLayer() { return logbookLayer; }

    public VBox getInformationTab() { return informationTab; }

}