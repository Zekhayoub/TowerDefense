package TowerDefense.UI;

import TowerDefense.*;
import TowerDefense.menu.Menu;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class UserInterface {
    private final Pane interfaceLayer;
    private final Pane playLayer;
    private final int windowHeight;
    private final int interfaceHeight;
    private final ArrayList<Group> tabs = new ArrayList<>();
    private final ArrayList<Integer> tabTypeList = new ArrayList<>();
    private final ArrayList<Turrets> turretWaitingList;
    private final ArrayList<Turrets> turretList;
    private final ArrayList<Turrets> onMapTurrets;
    private final GameInfo gameInfo;
    private final Map map;
    private final Menu menu;
    private final ArrayList<Text> textList;
    private final UpdateButton updateButton;
    private boolean openInterface;
    private UpdateTab updateTab;
    private Group updateGroup;
    private ArrayList<Node> shapes;
    private ArrayList<ImageView> tabIcons;
    private Turrets selectedTurret;

    public UserInterface(Pane interfaceLayer, Pane playLayer, ArrayList<Turrets> turretWaitingList, ArrayList<Turrets> turretList,
                         ArrayList<Turrets> onMapTurrets, GameInfo gameInfo, Menu menu, Map map, ArrayList<Text> textList,
                         UpdateButton updateButton) {
        this.interfaceLayer = interfaceLayer;
        this.turretWaitingList = turretWaitingList;
        this.turretList = turretList;
        this.onMapTurrets = onMapTurrets;
        this.windowHeight = gameInfo.getWindowHeight();
        this.gameInfo = gameInfo;
        this.interfaceHeight = 50;
        this.menu = menu;
        this.map = map;
        this.textList = textList;
        this.playLayer = playLayer;
        this.updateButton = updateButton;
        this.openInterface = false;
    }

    public void generateUserInterface() {
        generateLifeGoldImages();

        GridPane gridPane = new GridPane();
        gridPane.relocate(0,windowHeight-interfaceHeight);
        gridPane.setPadding(new Insets(-5, 8, 8, 40)); //40 ou 50
        gridPane.setHgap(10);
        //gridPane.setStyle("-fx-background-color: yellow;");

        shapes = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            ImageView tab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/button" + (4-i) + ".png")));
            tab.setFitHeight(35);
            tab.setPreserveRatio(true);
            tab.setCursor(Cursor.HAND);
            shapes.add(tab);
            gridPane.add(shapes.get(i), 4-i, 0);

            int finalI = i;
            shapes.get(i).setOnMouseEntered(mouseEvent -> {
                shapes.get(finalI).setScaleX(1.1);
                shapes.get(finalI).setScaleY(1.1);
            });
            shapes.get(i).setOnMouseExited(mouseEvent -> {
                shapes.get(finalI).setScaleX(1);
                shapes.get(finalI).setScaleY(1);
            });
        }

        tabIcons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView tabIcon = new ImageView(new Image(Main.class.getResourceAsStream(
                    "resources/image/tabIcons/tabIcon" + i + ".png")));
            tabIcon.setFitHeight(20);
            tabIcon.scaleXProperty().bind(shapes.get(i).scaleXProperty());
            tabIcon.scaleYProperty().bind(shapes.get(i).scaleYProperty());
            tabIcon.setPreserveRatio(true);
            tabIcon.setMouseTransparent(true);
            tabIcons.add(tabIcon);
            gridPane.add(tabIcons.get(i), 4-i, 0);
            GridPane.setHalignment(tabIcon, HPos.CENTER);
            GridPane.setValignment(tabIcon, VPos.CENTER);
        }

        tabIcons.get(0).setFitHeight(25);

        TurretTab turretTab = new TurretTab(interfaceLayer, playLayer,  turretWaitingList, turretList, onMapTurrets, gameInfo, map, textList);
        Group turretGroup = turretTab.generateTurretTap();
        ClickTab clickBigTab = new ClickTab(shapes, tabIcons, tabs, tabTypeList, turretGroup, this, 0);
        shapes.get(4).setOnMouseClicked(clickBigTab);

        AttackTab attackTab = new AttackTab(playLayer, gameInfo);
        Group attackGroup = attackTab.generateAttackTab();
        shapes.get(3).setOnMouseClicked(new ClickTab(shapes, tabIcons, tabs, tabTypeList, attackGroup, this, 0));

        EnemyTab enemyTab = new EnemyTab();
        Group enemyGroup = enemyTab.generateEnemyTab();
        shapes.get(2).setOnMouseClicked(new ClickTab(shapes, tabIcons, tabs, tabTypeList, enemyGroup, this, 0));

        updateTab = new UpdateTab();
        updateGroup = updateTab.generateUpdateTab();
        shapes.get(1).setOnMouseClicked(new ClickTab(shapes, tabIcons, tabs, tabTypeList, updateGroup, this, 0));

        SettingsTab settingsTab = new SettingsTab(interfaceLayer, menu, gameInfo);
        Group settingsGroup = settingsTab.generateSettingsTab();
        shapes.get(0).setOnMouseClicked(new ClickTab(shapes, tabIcons, tabs, tabTypeList, settingsGroup, this, 1));

        interfaceLayer.getChildren().addAll(gridPane, settingsGroup, updateGroup, enemyGroup, attackGroup, turretGroup);
    }

    private void generateLifeGoldImages() {
        ImageView money = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/moneywhite.png")));
        money.setFitHeight(30);
        money.setPreserveRatio(true);
        money.setX(1060);
        money.setY(8);

        ImageView life = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/lifewhite.png")));
        life.setFitHeight(35);
        life.setPreserveRatio(true);
        life.setX(935);
        life.setY(5);

        interfaceLayer.getChildren().addAll(life, money);
    }

    public boolean getOpen(){
        return openInterface;
    }

    public void setOpen(boolean open){
        openInterface = open;
    }

    public  void enableUpdate(Turrets turret, Node node){
        node.setOnMouseClicked(new ClickUpdator(turret, playLayer, textList, updateButton, gameInfo, node, shapes, tabIcons, tabs,
                tabTypeList, updateGroup, this, 0, updateTab));
    }

    public UpdateButton getUpdateButton() { return updateButton; }

    public void setSelectedTurret(Turrets turret){
        selectedTurret = turret;
    }

    public Turrets getSelectedTurret(){
        return selectedTurret;
    }
}
