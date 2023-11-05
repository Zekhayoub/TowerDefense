package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map7 extends Map implements MultiPath {
    public Map7(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
        for (int j = 0; j <= windowWidth/tileSize; j ++) {
            map.add(1);
        }
    }

        for (int i = windowHeight/tileSize * 2 + 2; i < windowHeight/tileSize * 6 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);//H1
        }
        for (int i = windowHeight/tileSize * 2 + 5; i < windowHeight/tileSize * 9 + 5; i += windowHeight/tileSize) {
            setTile(i, 2);//H2
        }
        for (int i = windowHeight/tileSize * 9 + 2; i < windowHeight/tileSize * 14 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);//H3
        }
        for (int i = windowHeight/tileSize * 14 + 5; i < windowHeight/tileSize * 17 + 5; i += windowHeight/tileSize) {
            setTile(i, 2);//H4
        }
        for (int i = windowHeight/tileSize * 17 + 2; i < windowHeight/tileSize * 22+ 2; i += windowHeight/tileSize) {
            setTile(i, 2);//H5
        }
        for (int i = windowHeight/tileSize * 17 + 9; i < windowHeight/tileSize * 21 + 9; i += windowHeight/tileSize) {
            setTile(i, 2);//H6
        }
        for (int i = windowHeight/tileSize * 12 + 8; i < windowHeight/tileSize * 17 + 8; i += windowHeight/tileSize) {
            setTile(i, 2);//H7
        }
        for (int i = windowHeight/tileSize * 12 + 11; i < windowHeight/tileSize * 21 + 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H8
        }
        for (int i = windowHeight/tileSize * 8 + 14; i < windowHeight/tileSize * 21 + 14; i += windowHeight/tileSize) {
            setTile(i, 2);//H9
        }
        for (int i = windowHeight/tileSize * 4 + 8; i < windowHeight/tileSize * 8 + 8; i += windowHeight/tileSize) {
            setTile(i, 2);//H10
        }
        for (int i = windowHeight/tileSize * 0+ 10; i < windowHeight/tileSize * 4 + 10; i += windowHeight/tileSize) {
            setTile(i, 2);//H11
        }


        for (int i = windowHeight/tileSize * 21+ 4; i < windowHeight/tileSize * 25 ; i += windowHeight/tileSize) {
            setTile(i, 2);//H12  CHEMIN DOUBLE
        }



        for (int i = windowHeight/tileSize * 6 + 0; i < windowHeight/tileSize * 6 +3 ; i++) {
            setTile(i, 2); //V1
        }
        for (int i = windowHeight/tileSize * 2 + 2; i < windowHeight/tileSize * 2 +5 ; i++) {
            setTile(i, 2); //V2
        }
        for (int i = windowHeight/tileSize * 9 + 2; i < windowHeight/tileSize * 9 +6; i++) {
            setTile(i, 2); //V3
        }
        for (int i = windowHeight/tileSize * 14 + 2; i < windowHeight/tileSize * 14 +5 ; i++) {
            setTile(i, 2); //V4
        }
        for (int i = windowHeight/tileSize * 17 + 2; i < windowHeight/tileSize * 17 +6 ; i++) {
            setTile(i, 2); //V5
        }
        for (int i = windowHeight/tileSize * 21 + 2; i < windowHeight/tileSize * 21 +10 ; i++) {
            setTile(i, 2); //V6
        }
        for (int i = windowHeight/tileSize * 17 + 8; i < windowHeight/tileSize * 17 +9 ; i++) {
            setTile(i, 2); //V7
        }
        for (int i = windowHeight/tileSize * 12 + 8; i < windowHeight/tileSize * 12 +12 ; i++) {
            setTile(i, 2); //V8
        }
        for (int i = windowHeight/tileSize * 21 + 11; i < windowHeight/tileSize * 21 +15 ; i++) {
            setTile(i, 2); //V9
        }
        for (int i = windowHeight/tileSize * 8 + 8; i < windowHeight/tileSize * 8 +14 ; i++) {
            setTile(i, 2); //V10
        }
        for (int i = windowHeight/tileSize * 4 + 8; i < windowHeight/tileSize * 4 +11 ; i++) {
            setTile(i, 2); //V11
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
        points.get(0).add(21);
        points.get(0).add(4);
        points.get(0).add(0);
        points.get(0).add(1);


        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(470,300,mapLayer, gameInfo);
        Tree tree1 = new Tree(510,300,mapLayer, gameInfo);




        decor.add(tree);
        decor.add(tree1);


    }

    @Override
    public int[] getStartX() {
        int[] x = {6,24};
        return x;
    }

    @Override
    public int[] getStartY() {
        int[] y = {0,4};
        return y;
    }
}
