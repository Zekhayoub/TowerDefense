package TowerDefense.maps;


import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map4 extends Map implements SinglePath {


    public Map4 (GameInfo gameInfo){
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }
        for (int i = 8; i < windowHeight/tileSize * 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H1
        }
        for (int i = windowHeight/tileSize * 22+3; i < windowHeight/tileSize * 25 + 3; i += windowHeight/tileSize) {
            setTile(i, 2);//H2
        }
        for (int i = windowHeight/tileSize * 11 + 14; i < windowHeight/tileSize * 22 + 14; i += windowHeight/tileSize) {
            setTile(i, 2);//H3
        }
        for (int i = windowHeight/tileSize * 11 + 8; i < windowHeight/tileSize * 11 +14; i++) {
            setTile(i, 2); //V2
        }
        for (int i = windowHeight/tileSize * 21 + 3; i < windowHeight/tileSize * 21 +14; i++) {
            setTile(i, 2); //V2
        }

        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight / tileSize +4; i < windowHeight/tileSize * 10; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight / tileSize +2; i < windowHeight/tileSize * 10; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight / tileSize +11; i < windowHeight/tileSize * 9; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 14+10; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 14+6; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        printMap(mapLayer);
    }


    @Override
    public ArrayList<ArrayList<Integer>> getProblematicPoints() {
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>());
        points.get(0).add(11);
        points.get(0).add(8);
        points.get(0).add(0);
        points.get(0).add(1);
        points.get(0).add(0);
        points.get(0).add(-1);


        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(250,300,mapLayer, gameInfo);
        Tree tree1 = new Tree(800,400,mapLayer, gameInfo);
        decor.add(tree);
        decor.add(tree1);
    }

    @Override
    public int getStartX() {
        return 0;
    }

    @Override
    public int getStartY() {
        return 8;
    }
}
