package TowerDefense.maps;

import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map8 extends Map implements MultiPath {
    public Map8(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }
        for (int i = windowHeight/tileSize * 8 + 11; i < windowHeight/tileSize * 21 + 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H1
        }
        for (int i = windowHeight/tileSize * 8 + 6; i < windowHeight/tileSize * 21 ; i += windowHeight/tileSize) {
            setTile(i, 2);//H3
        }
        for (int i = windowHeight/tileSize * 4 + 2; i < windowHeight/tileSize * 21 ; i += windowHeight/tileSize) {
            setTile(i, 2);//H4
        }
        for (int i = windowHeight/tileSize *0+ 9; i < windowHeight/tileSize * 4 ; i += windowHeight/tileSize) {
            setTile(i, 2);//H5
        }

        for (int i = windowHeight/tileSize * 8 + 6; i < windowHeight/tileSize * 8 +16; i++) {
            setTile(i, 2); //V1
        }
        for (int i = windowHeight/tileSize * 21 + 11; i < windowHeight/tileSize * 21 + 16; i++) {
            setTile(i, 2); //V2
        }
        for (int i = windowHeight/tileSize * 21 + 2; i < windowHeight/tileSize * 21 +7 ; i++) {
            setTile(i, 2); //V3
        }
        for (int i = windowHeight/tileSize * 4 + 2; i < windowHeight/tileSize * 4 +10; i++) {
            setTile(i, 2); //V4
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 11+13; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 12+8; i < windowHeight/tileSize * 20; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 1+12; i < windowHeight/tileSize * 6; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        printMap(mapLayer);

    }

    @Override
    public ArrayList<ArrayList<Integer>> getProblematicPoints() {
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>());
        points.get(0).add(8);
        points.get(0).add(11);
        points.get(0).add(0);
        points.get(0).add(-1);


        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {

    }

    @Override
    public int[] getStartX() {
        int[] x = {8,21};
        return x;
    }

    @Override
    public int[] getStartY() {
        int[] y = {15,15};
        return y;
    }
}
