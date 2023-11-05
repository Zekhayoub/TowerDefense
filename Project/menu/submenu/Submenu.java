package TowerDefense.menu.submenu;

import TowerDefense.GameInfo;
import TowerDefense.Main;
import TowerDefense.Starter;
import TowerDefense.logbook.ClickLogbook;
import TowerDefense.menu.Menu;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Submenu {
    private ArrayList<ImageView> screenshots = new ArrayList<>();

    public GridPane initSubMenu(Menu menu, Starter starter, GameInfo gameInfo) {
        GridPane submenuLayer = new GridPane();
        submenuLayer.paddingProperty().bind(Bindings.createObjectBinding(() -> new Insets(
                        gameInfo.getRealWindowWidth().doubleValue()*40/1200),
                gameInfo.getRealWindowWidth().multiply(40).divide(1200)));
        submenuLayer.prefHeightProperty().bind(gameInfo.getRealWindowHeight());
        submenuLayer.prefWidthProperty().bind(gameInfo.getRealWindowWidth());
        submenuLayer.setHgap(70);
        submenuLayer.setVgap(45);
        submenuLayer.setStyle("-fx-background-color: black;");

        ArrayList<Shape> maps= new ArrayList<>();
        ArrayList<ImageView> mapsScreenshots = new ArrayList<>();
        for (int i =0; i < 9; i++){
            Rectangle mapSlot = new Rectangle();
            mapSlot.widthProperty().bind(gameInfo.getRealWindowWidth().multiply(320).divide(1200));
            mapSlot.heightProperty().bind(gameInfo.getRealWindowHeight().multiply(200).divide(800));
            mapSlot.setFill(Color.TRANSPARENT);
            mapSlot.arcHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(10).divide(800));
            mapSlot.arcWidthProperty().bind(gameInfo.getRealWindowWidth().multiply(10).divide(1200));
            mapSlot.setStyle("-fx-stroke: black; -fx-stroke-width: 3;");
            mapSlot.setCursor(Cursor.HAND);

            ImageView mapScreenshot = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/" +
                    "mapScreenshots/map" + i + ".png")));
            mapScreenshot.setFitHeight(200);
            mapScreenshot.setPreserveRatio(true);
            mapScreenshot.setMouseTransparent(true);
            mapsScreenshots.add(mapScreenshot);
            GridPane.setHalignment(mapScreenshot, HPos.CENTER);
            GridPane.setValignment(mapScreenshot, VPos.CENTER);
            screenshots.add(mapScreenshot);

            if (gameInfo.getProgress() <= i){
                mapScreenshot.setOpacity(0.4);
            }

            mapSlot.setOnMouseClicked(new ClickSelector(menu, starter, gameInfo, i+1));

            ClickEnter clickEnter = new ClickEnter(submenuLayer, maps, mapSlot, gameInfo);
            mapSlot.setOnMouseEntered(clickEnter);
            mapSlot.setOnMouseExited(new ClickExit(submenuLayer, mapSlot, clickEnter));
            maps.add(mapSlot);
        }

        submenuLayer.add(mapsScreenshots.get(0), 0, 0);
        submenuLayer.add(mapsScreenshots.get(1), 1, 0);
        submenuLayer.add(mapsScreenshots.get(2), 2, 0);
        submenuLayer.add(mapsScreenshots.get(3), 0, 1);
        submenuLayer.add(mapsScreenshots.get(4), 1, 1);
        submenuLayer.add(mapsScreenshots.get(5), 2, 1);
        submenuLayer.add(mapsScreenshots.get(6), 0, 2);
        submenuLayer.add(mapsScreenshots.get(7), 1, 2);
        submenuLayer.add(mapsScreenshots.get(8), 2, 2);

        submenuLayer.add(maps.get(0), 0, 0);
        submenuLayer.add(maps.get(1), 1, 0);
        submenuLayer.add(maps.get(2), 2, 0);
        submenuLayer.add(maps.get(3), 0, 1);
        submenuLayer.add(maps.get(4), 1, 1);
        submenuLayer.add(maps.get(5), 2, 1);
        submenuLayer.add(maps.get(6), 0, 2);
        submenuLayer.add(maps.get(7), 1, 2);
        submenuLayer.add(maps.get(8), 2, 2);

        addBackButton(menu, submenuLayer, gameInfo);
        addLogbookButton(menu, submenuLayer, gameInfo);  // NEW

        return submenuLayer;
    }

    private void addBackButton(Menu menu, GridPane submenuLayer, GameInfo gameInfo){
        VBox vBox = new VBox();
        //vBox.setStyle("-fx-background-color: red;"); // permet de situer et voir la VBox
        vBox.maxWidthProperty().bind(gameInfo.getRealWindowWidth().multiply(60).divide(1200));
        vBox.maxHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(60).divide(800));
        vBox.translateXProperty().bind(gameInfo.getRealWindowWidth().multiply(-40).divide(1200)); //1100
        vBox.translateYProperty().bind(gameInfo.getRealWindowHeight().multiply(625).divide(800)); //625
        ImageView backArrow = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/arrowLeft.png")));
        backArrow.setFitHeight(50);
        backArrow.setPreserveRatio(true);
        backArrow.setCursor(Cursor.HAND);
        vBox.getChildren().add(backArrow);
        vBox.setAlignment(Pos.CENTER);
        vBox.setOnMouseClicked(new ClickBack(menu));

        submenuLayer.getChildren().add(vBox);
    }

    private void addLogbookButton(Menu menu, GridPane submenuLayer, GameInfo gameInfo){            // NEW
        VBox vBox = new VBox();
        //vBox.setStyle("-fx-background-color: red;");
        vBox.maxWidthProperty().bind(gameInfo.getRealWindowWidth().multiply(60).divide(1200));
        vBox.maxHeightProperty().bind(gameInfo.getRealWindowHeight().multiply(60).divide(800));
        vBox.translateXProperty().bind(gameInfo.getRealWindowWidth().multiply(1100).divide(1200)); //-40
        vBox.translateYProperty().bind(gameInfo.getRealWindowHeight().multiply(625).divide(800)); //625
        ImageView backArrow = new ImageView(new Image(Main.class.getResourceAsStream("resources/image/logbook1.png")));
        backArrow.setFitHeight(50);
        backArrow.setPreserveRatio(true);
        vBox.setCursor(Cursor.HAND);
        vBox.getChildren().add(backArrow);
        vBox.setAlignment(Pos.CENTER);
        vBox.setOnMouseClicked(new ClickLogbook(menu, gameInfo));

        submenuLayer.getChildren().add(vBox);
    }

    public void refreshOpacity(GameInfo gameInfo){
        for (int i =0; i < 9; i++){
            if (gameInfo.getProgress() <= i){
                screenshots.get(i).setOpacity(0.4);
            }
            else {
                screenshots.get(i).setOpacity(1);
            }
        }
        System.out.println(gameInfo.getProgress());
    }
}
