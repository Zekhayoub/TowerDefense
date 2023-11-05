package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map9 extends Map implements MultiPath{

    public Map9(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }
        for (int i = windowHeight/tileSize * 0 + 2; i < windowHeight/tileSize * 21 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);//H1
        }
        for (int i = windowHeight/tileSize * 3 + 6; i < windowHeight/tileSize * 21 + 6; i += windowHeight/tileSize) {
            setTile(i, 2);//H2
        }
        for (int i = windowHeight/tileSize * 3 + 10; i < windowHeight/tileSize * 8 + 10; i += windowHeight/tileSize) {
            setTile(i, 2);//H3
        }
        for (int i = windowHeight/tileSize * 8 + 14; i < windowHeight/tileSize * 25 + 14; i += windowHeight/tileSize) {
            setTile(i, 2);//H4
        }
        for (int i = windowHeight/tileSize * 10 + 11; i < windowHeight/tileSize * 25 + 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H5
        }

        for (int i = windowHeight/tileSize * 21 + 2; i < windowHeight/tileSize * 21 +7; i++) {
            setTile(i, 2); //V1
        }
        for (int i = windowHeight/tileSize * 3 + 6; i < windowHeight/tileSize * 3 +10 ; i++) {
            setTile(i, 2); //V2
        }
        for (int i = windowHeight/tileSize * 8 + 10; i < windowHeight/tileSize * 8 +14 ; i++) {
            setTile(i, 2); //V3
        }
        for (int i = windowHeight/tileSize * 10 + 6; i < windowHeight/tileSize * 10 +11 ; i++) {
            setTile(i, 2); //V4
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 14+8; i < windowHeight/tileSize * 20; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 9+3; i < windowHeight/tileSize * 15; i += windowHeight/tileSize) {
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
        points.get(0).add(10);
        points.get(0).add(6);
        points.get(0).add(1);
        points.get(0).add(0);
        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(600,600,mapLayer, gameInfo);
        Tree tree1 = new Tree(900,600,mapLayer, gameInfo);
        decor.add(tree);
        decor.add(tree1);
    }

    @Override
    public int[] getStartX() {
        int[] x = {24,24};
        return x;
    }

    @Override
    public int[] getStartY() {
        int[] y = {11,14};
        return y;
    }
}
