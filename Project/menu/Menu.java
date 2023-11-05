package TowerDefense.menu;

import TowerDefense.*;
import TowerDefense.menu.submenu.GoBack;
import TowerDefense.menu.submenu.Submenu;
import TowerDefense.pause.ClickSettingsFromPause;
import TowerDefense.settings.SettingsScreen;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Menu<obs> {
    private final Pane menuLayer;
    private final Pane submenuLayer;
    private final Pane wholeMenuLayer;
    private final Pane interfaceLayer;
    private Starter starter;
    private GameInfo gameInfo;
    private SimpleIntegerProperty windowWidth = new SimpleIntegerProperty();
    private SimpleIntegerProperty windowHeight = new SimpleIntegerProperty();
    private Pane settingsLayer;
    private Saver saver;
    private Submenu submenu;

    public Menu(Pane interfaceLayer, GameInfo gameInfo){
        this.menuLayer = new Pane();
        this.submenuLayer = new Pane();
        this.wholeMenuLayer = new Pane();
        this.settingsLayer = new Pane();
        this.gameInfo = gameInfo;
        this.windowWidth = gameInfo.getRealWindowWidth();
        this.windowHeight = gameInfo.getRealWindowHeight();
        this.interfaceLayer = interfaceLayer;
        this.saver = new Saver(gameInfo);
    }

    public Pane initMenu(Starter starter){
        this.starter = starter;

        //Menu
        menuLayer.setPrefSize(windowWidth.intValue(),windowHeight.intValue());
        ImageView img = new ImageView(new Image(Initialisator.class.getResourceAsStream("menu/mask-4934337_1920.jpg")));
        img.fitWidthProperty().bind(windowWidth);
        img.fitHeightProperty().bind(windowHeight);

        ImageView title = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/HumanDefenseTrans2.png")));
        title.layoutXProperty().bind(windowWidth.multiply(610).divide(1200));
        title.layoutYProperty().bind(windowHeight.multiply(450).divide(800));
        title.fitHeightProperty().bind(windowHeight.multiply(135).divide(800));
        title.setPreserveRatio(true);

        MenuItem newGameButton = new MenuItem("NEW GAME", gameInfo);  newGameButton.setOnMouseClicked(new ClickPlayer(this, saver, true));
        MenuItem continueButton = new MenuItem("CONTINUE", gameInfo);  continueButton.setOnMouseClicked(new ClickPlayer(this, saver, false));

        SettingsScreen settingsScreen = new SettingsScreen(gameInfo, wholeMenuLayer, 0);
        settingsScreen.initSettingsScreen();
        settingsLayer = settingsScreen.getSettingScreen();

        MenuItem settingsButton = new MenuItem("SETTINGS", gameInfo);  settingsButton.setOnMouseClicked(new ClickSettingsFromPause(wholeMenuLayer, settingsLayer));
        MenuItem quitButton = new MenuItem("QUIT", gameInfo);  quitButton.setOnMouseClicked(new ClickQuit());

        MenuBox vbox = new MenuBox(gameInfo);
        vbox.createBox(newGameButton, continueButton,settingsButton,  quitButton);

        vbox.layoutXProperty().bind(gameInfo.getRealWindowWidth().multiply(750).divide(1200));
        vbox.layoutYProperty().bind(gameInfo.getRealWindowHeight().multiply(600).divide(800));

        menuLayer.getChildren().addAll(img, title,vbox);
        menuLayer.setFocusTraversable(true);

        //Submenu
        submenu = new Submenu();
        submenuLayer.getChildren().add(submenu.initSubMenu(this, starter, gameInfo));
        submenuLayer.setOnKeyPressed(new GoBack(this));

        wholeMenuLayer.getChildren().addAll(submenuLayer, menuLayer);
        wholeMenuLayer.setOnKeyPressed(new CheatFuntion(gameInfo));
        return wholeMenuLayer;
    }

    public Pane getMenuLayer(){
        return menuLayer;
    }

    public void hideMenuLayer(){
        menuLayer.toBack();
    }

    public Pane getSubmenuLayer(){
        submenu.refreshOpacity(gameInfo);
        submenuLayer.setMouseTransparent(false);
        return submenuLayer;
    }

    public void hideSubmenuLayer(){
        submenuLayer.toBack();
    }

    public void hideAll(){
        wholeMenuLayer.toBack();
    }

    public void restartMenu(){
        wholeMenuLayer.getChildren().add(menuLayer);
    }

    public Pane getWholeMenuLayer(){
        return wholeMenuLayer;
    }

    public Pane getInterfaceLayer(){
        return this.interfaceLayer;
    }

    public void showAll(){
        submenuLayer.toFront();
        submenuLayer.setOpacity(1);
        wholeMenuLayer.toFront();
        submenuLayer.requestFocus();
        submenu.refreshOpacity(gameInfo);
    }
    public Starter getStarter(){
        return starter;
    }
}
