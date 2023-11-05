package TowerDefense.UI;

import TowerDefense.Main;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UpdateTab {
    private VBox vbox = new VBox();
    private Text message = new Text();
    private Group group = new Group();
    private final Font VerlagLightItalic =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-LightItalic.otf"), 26);

    public UpdateTab() { }

    public Group generateUpdateTab() {
        //Group group = new Group();

        ImageView updateTab = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/tab3.png")));
        updateTab.relocate(30, 800); // 0 - 570

        message.setText("Click on a turret" + "\n" + "to know its stats" + "\n" + "and to upgrade it");
        message.setTextAlignment(TextAlignment.CENTER);
        message.setFont(VerlagLightItalic);
        message.setFill(Color.WHITE);

        vbox.getChildren().add(message);

        vbox.setAlignment(Pos.CENTER);
        vbox.relocate(30, 800);
        vbox.setPrefSize(288, 220);
        //vbox.setStyle("-fx-background-color: yellow;");

        group.getChildren().addAll(updateTab, vbox);

        return group;
    }

    public VBox getVbox() { return vbox; }

}
