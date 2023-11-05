package TowerDefense;

import TowerDefense.UI.ClickTab;
import TowerDefense.UI.UpdateButton;
import TowerDefense.UI.UpdateTab;
import TowerDefense.UI.UserInterface;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;


public class ClickUpdator implements EventHandler<MouseEvent> {
    private final Turrets turret;
    private final Pane layer;
    private final ArrayList<Text> textList;
    private final UpdateButton updateButton;
    private final GameInfo gameInfo;
    private boolean firstClick = true;
    private Circle range = null;
    private final Node turretImage;
    private final ArrayList<Node> shapes;
    private final ArrayList<ImageView> tabIcons;
    private final ArrayList<Group> tabs;
    private final ArrayList<Integer> tabTypeList;
    private final Group updateGroup;
    private final UserInterface userInterface;
    private final int tabType;
    private final UpdateTab updateTab;
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 18);
    private final Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 18);
    private final DecimalFormat df = new DecimalFormat("0.00");
    private Text damageValue;
    private Text rangeValue;
    private Text speedValue;
    private Text accuracyValue;
    private Text updateCostText;
    private final int removeRangeDelay;
    private double delta;

    public ClickUpdator(Turrets turret, Pane layer, ArrayList<Text> textList, UpdateButton updateButton, GameInfo gameInfo, Node turretImage,
                        ArrayList<Node> shapes, ArrayList<ImageView> tabIcons, ArrayList<Group> tabs, ArrayList<Integer> tabTypeList,
                        Group updateGroup, UserInterface userInterface, int tabType, UpdateTab updateTab){
        this.turret = turret;
        this.layer = layer;
        this.textList = textList;
        this.updateButton = updateButton;
        this.gameInfo = gameInfo;
        this.turretImage = turretImage;
        this.shapes = shapes;
        this.tabIcons = tabIcons;
        this.tabs = tabs;
        this.tabTypeList = tabTypeList;
        this.updateGroup = updateGroup;
        this.userInterface = userInterface;
        this.tabType = tabType;
        this.updateTab = updateTab;
        this.removeRangeDelay = 10;
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
    }


    @Override
    public void handle(MouseEvent mouseEvent) { // questions
        userInterface.setSelectedTurret(turret);
        generateText();
        generateRange();
        refreshUpdateButton();
        updateButton.getButton().setOnMouseClicked(mouseEvent1 -> {
            if (range == null){
                generateRange();
            }
            updateRange();
            updateButton.update(mouseEvent1);
            updateText();
            delta = 0;
        });

        layer.setOnMouseClicked(this::removeRange);

        new ClickTab(shapes, tabIcons, tabs, tabTypeList, updateGroup, userInterface, tabType).handle(mouseEvent); // layer

        turretImage.setOnMouseClicked(mouseEvent12 -> {
            delta = 0;
            new ClickTab(shapes, tabIcons, tabs, tabTypeList, updateGroup, userInterface, tabType).handle(mouseEvent12);
            updateButton.setClickable(true);
            userInterface.setSelectedTurret(turret);
            refreshUpdateButton();
            if (range == null){
                firstClick = true;
                generateRange();
            }
        });

        removeRangeTime(mouseEvent);

        turretImage.setFocusTraversable(true);
    }


    private void refreshUpdateButton(){
        if (gameInfo.canBuy(turret.updateCost)) { //verifier cout de la tourelle + gameinfo.getMoney
            updateButton.setTurret(userInterface.getSelectedTurret());
            updateButton.setClickable(true);
        }
    }

    private void updateRange(){
        layer.getChildren().remove(range);
        range.setRadius(userInterface.getSelectedTurret().getRange());
        layer.getChildren().add(range);
    }

    private void generateRange(){
        range = turret.drawRangeWithoutTimeline();
        range.setMouseTransparent(true);
        range.setFill(Color.RED);
        if(firstClick){
            layer.getChildren().add(range);
        }
    }


    public void generateText(){ //Turret Description
        VBox vbox = updateTab.getVbox();
        vbox.getChildren().clear();
        textList.clear();

        Text turretName = createText(turret.getName(), VerlagBold);
        Text damageText = createText("Damage: ", VerlagCondensedBold);
        damageValue = createText(df.format(userInterface.getSelectedTurret().getDamage()), VerlagCondensedBold);
        Text rangeText = createText("Range: ", VerlagCondensedBold);
        rangeValue = createText(df.format(userInterface.getSelectedTurret().getRange()), VerlagCondensedBold);
        Text speedText = createText("Speed: ", VerlagCondensedBold);
        speedValue = createText(df.format(userInterface.getSelectedTurret().getSpeed()), VerlagCondensedBold);
        Text accuracyText = createText("Accuracy: ", VerlagCondensedBold);
        accuracyValue = createText(df.format(userInterface.getSelectedTurret().getAccuracy()), VerlagCondensedBold);
        updateCostText = createText("Update Cost: " + df.format(turret.getUpdateCost()) + "     "
                + "Level: " + turret.getLevel(), VerlagCondensedBold);

        ImageView damage = createImage("damagewhite");
        ImageView range = createImage("range");
        ImageView speed = createImage("speedwhite");
        ImageView accuracy = createImage("accuracywhite");


        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(200, 140);

        VBox.setMargin(stackPane, new Insets(0, 10, 90, 10));
        StackPane.setAlignment(turretName, Pos.TOP_CENTER);
        StackPane.setMargin(turretName, new Insets(10, 0, 0, 0));

        VBox damageRangeImageBox = new VBox(damage, range);
        damageRangeImageBox.setSpacing(10); //

        VBox damageRangeTextBox = new VBox(damageText, rangeText);
        damageRangeTextBox.setSpacing(15); //
        damageRangeTextBox.setPadding(new Insets(5, 0, 0, 0));

        VBox damageRangeValueBox = new VBox(damageValue, rangeValue);
        damageRangeValueBox.setSpacing(15);
        damageRangeValueBox.setPadding(new Insets(5, 0, 0, 0));
        damageRangeValueBox.setAlignment(Pos.TOP_RIGHT);

        VBox speedAccuracyImageBox = new VBox(speed, accuracy);
        speedAccuracyImageBox.setSpacing(10); //
        //speedAccuracyImageBox.setStyle("-fx-background-color: red;");
        VBox speedAccuracyTextBox = new VBox(speedText, accuracyText);
        speedAccuracyTextBox.setSpacing(15); //
        speedAccuracyTextBox.setPadding(new Insets(5, 0, 0, 0));

        VBox speedAccuracyValueBox = new VBox(speedValue, accuracyValue);
        speedAccuracyValueBox.setSpacing(15);
        speedAccuracyValueBox.setPadding(new Insets(5, 0, 0, 0));
        speedAccuracyValueBox.setAlignment(Pos.TOP_RIGHT);

        HBox infoTurret = new HBox(damageRangeImageBox, damageRangeTextBox, damageRangeValueBox, speedAccuracyImageBox,
                speedAccuracyTextBox, speedAccuracyValueBox);
        infoTurret.setPadding(new Insets(40, 0, 0, 0));
        HBox.setMargin(damageRangeImageBox, new Insets(0, 0, 0, 3));
        HBox.setMargin(damageRangeTextBox, new Insets(0, 0, 0, 5));
        HBox.setMargin(speedAccuracyImageBox, new Insets(0, 0, 0, 10));
        HBox.setMargin(speedAccuracyTextBox, new Insets(0, 0, 0, 5));
        stackPane.getChildren().addAll(turretName, infoTurret);

        vbox.setSpacing(-100);
        StackPane button = updateButton.generateUpdateButton();
        VBox updateBox = new VBox();
        updateBox.setAlignment(Pos.CENTER);
        updateBox.setSpacing(10);
        updateBox.setPadding(new Insets(-10, 0, 0, 0));
        //updateBox.setStyle("-fx-background-color: blue;");
        updateBox.getChildren().addAll(updateCostText, button);

        vbox.getChildren().addAll(stackPane, updateBox);
    }

    public void updateText(){
        damageValue.setText(df.format(userInterface.getSelectedTurret().getDamage()));
        rangeValue.setText(df.format(userInterface.getSelectedTurret().getRange()));
        speedValue.setText(df.format(userInterface.getSelectedTurret().getSpeed()));
        accuracyValue.setText(df.format(userInterface.getSelectedTurret().getAccuracy()));
        updateCostText.setText("Update Cost: " + df.format(turret.getUpdateCost()) + "     "       // NEW
                + "Level: " + turret.getLevel());
    }

    private void removeRange(MouseEvent e){
        System.out.println(firstClick);
        if (!firstClick){
            if (range != null){
                layer.getChildren().remove(range);
                range = null;
            }
        }
        else {
            firstClick = false;
        }
    }

    private Text createText(String word, Font font) {
        Text text = new Text(word);
        text.setFill(Color.WHITE);
        text.setFont(font);
        textList.add(text);
        return text;
    }

    private ImageView createImage(String name) {
        ImageView image = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/info/" + name + ".png")));
        image.setFitHeight(25);
        image.setPreserveRatio(true);
        return image;
    }

    private void removeRangeTime(MouseEvent mouseEvent){
        delta = 0;
        Timeline remover = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> {
            if (delta < removeRangeDelay) {
                delta += Time.delta() * 60;
            } else {
                removeRange(mouseEvent);
            }
        }));
        remover.setCycleCount(Animation.INDEFINITE);
        remover.play();
    }
}
