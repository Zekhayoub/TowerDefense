package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map5 extends Map implements MultiPath {


    public Map5 (GameInfo gameInfo){
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }

        //"This is the way"
        for (int i = 2; i < windowHeight/tileSize * 5 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);
        }


        for (int i = windowHeight/tileSize * 5 +2; i < windowHeight/tileSize * 6 - 6 ; i++) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 5 + 9; i < windowHeight/tileSize * 12+ 9; i += windowHeight/tileSize) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 12 +2; i < windowHeight/tileSize * 13 - 6 ; i++) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 12+ 2; i < windowHeight/tileSize * 25+ 2; i += windowHeight/tileSize) {
            setTile(i, 2);
        }



        // CHEMIN N2
        for (int i = 6; i < windowHeight/tileSize * 3 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 2 + 7; i < windowHeight/tileSize * 3 - 3 ; i++) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 2 + 12; i < windowHeight/tileSize * 15+ 12; i += windowHeight/tileSize) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 15 + 5; i < windowHeight/tileSize * 16 - 3 ; i++) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 16+ 5; i < windowHeight/tileSize * 21+ 5; i += windowHeight/tileSize) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 21 + 5; i < windowHeight/tileSize * 21 +16; i++) {
            setTile(i, 2); //V4
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 7+5; i < windowHeight/tileSize * 11; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 7+4; i < windowHeight/tileSize * 11; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 7+2; i < windowHeight/tileSize * 11; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 8+14; i < windowHeight/tileSize * 18; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }

        printMap(mapLayer);

    }


    public ArrayList<ArrayList<Integer>> getProblematicPoints(){


        return new ArrayList<>();
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(870,400,mapLayer, gameInfo);
        Tree tree1 = new Tree(870,500,mapLayer, gameInfo);
        Tree tree2 = new Tree(870,600,mapLayer, gameInfo);


        decor.add(tree);
        decor.add(tree1);
        decor.add(tree2);


    }

    @Override
    public int[] getStartX() {
        return new int[]{24,0};
    }

    @Override
    public int[] getStartY() {
        return new int[]{2,6};
    }
}