package TowerDefense.UI;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Turrets;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class UpdateButton {
    private Rectangle upgrade = new Rectangle(100, 40, Color.DIMGREY);
    private Text upgradeText = new Text("UPGRADE");
    private boolean clickable;
    private Turrets turret;
    private GameInfo gameInfo;
    private final Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 20);

    public UpdateButton(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    public StackPane generateUpdateButton() {
        //Update Button
        StackPane button = new StackPane();
        upgrade.setArcWidth(20);
        upgrade.setArcHeight(20);
        //upgrade.setStroke(Color.WHITE);
        //upgrade.setStrokeType(StrokeType.INSIDE);
        //upgrade.setStrokeWidth(3);

        upgradeText.setFill(Color.GRAY);
        upgradeText.setFont(VerlagCondensedBold);
        upgradeText.setMouseTransparent(true);
        upgrade.setOnMouseClicked(new ClickUpdate(this, gameInfo));

        button.getChildren().addAll(upgrade, upgradeText);
        button.setCursor(Cursor.HAND);

        button.setOnMouseEntered(mouseEvent -> {
            button.setScaleX(1.07);
            button.setScaleY(1.07);
        });

        button.setOnMouseExited(mouseEvent -> {
            button.setScaleX(1);
            button.setScaleY(1);
        });

        return button;
    }

    public void setClickable(boolean clickable){ this.clickable = clickable; }

    public boolean getClickable(){ return this.clickable; }

    public void refreshUpBut(){
        if (turret != null && clickable){
            this.clickable = gameInfo.canBuy(turret.getUpdateCost());
        }
        if (clickable){
            upgrade.setFill(Color.SILVER);
            upgradeText.setFill(Color.BLACK);
        }
        else {
            upgrade.setFill(Color.DIMGREY);
            upgradeText.setFill(Color.GRAY);
        }
    }

    public void setTurret(Turrets turret){
        this.turret = turret;
    }

    public Turrets getTurret(){
        return turret;
    }

    public Rectangle getButton(){
        return upgrade;
    }

    public void update(MouseEvent mouseEvent){
        new ClickUpdate(this, gameInfo).handle(mouseEvent);
    }

    public Text getText(){
        return upgradeText;
    }
}