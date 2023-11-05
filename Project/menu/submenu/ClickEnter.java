package TowerDefense.menu.submenu;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ClickEnter implements EventHandler<MouseEvent> {
    private final GridPane submenuLayer;
    private final ArrayList<Shape> maps;
    private final Rectangle mapSlot;
    private final GameInfo gameInfo;
    private final Group group = new Group();
    private final Text text = new Text();
    private final Font VerlagBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-Bold.otf"), 24);  // NEW

    public ClickEnter(GridPane submenuLayer, ArrayList<Shape> maps, Rectangle mapSlot, GameInfo gameInfo) {
        this.submenuLayer = submenuLayer;
        this.maps = maps;
        this.mapSlot = mapSlot;
        this.gameInfo = gameInfo;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        group.getChildren().clear();

        mapSlot.setStyle("-fx-stroke: green; -fx-stroke-width: 3;");

        Region region = new Region();
        region.setPrefSize(300, 50);
        region.prefHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(40).divide(800));
        region.prefWidthProperty().bind(gameInfo.getRealWindowWidth().multiply(320).divide(1200));
        region.setStyle("-fx-background-color: green; -fx-background-radius: 0 0 10 10");
        group.getChildren().add(region);

        text.setFont(VerlagBold);

        text.setMouseTransparent(true);
        text.setFill(Color.WHITE);
        GridPane.setMargin(text, new Insets(10));

        for (int i = 0; i < 9; i++){
            if (maps.indexOf(mapSlot) == i){
                text.setText("Map n°" + (i + 1));
                submenuLayer.add(group, i - (i /3) * 3, i /3);
                submenuLayer.add(text, i - (i /3) * 3, i /3);
            }
        }

        group.setMouseTransparent(true);
        GridPane.setValignment(group, VPos.BOTTOM);
        GridPane.setHalignment(group, HPos.CENTER);

        GridPane.setValignment(text, VPos.BOTTOM);
        GridPane.setHalignment(text, HPos.CENTER);

    }

    public Group getGroup() { return group; }

    public Text getText() { return text; }

}
