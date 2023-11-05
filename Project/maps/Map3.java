package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map3 extends Map implements MultiPath{


    public Map3(GameInfo gameInfo){
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
        for (int i = 2; i < windowHeight/tileSize * 20 + 2; i += windowHeight/tileSize) { //H
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 20 +2; i < windowHeight/tileSize * 21 - 9 ; i++) { //v
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 8 + 6; i < windowHeight/tileSize * 20 + 6; i += windowHeight/tileSize) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 7 + 6; i < windowHeight/tileSize * 8 - 7 ; i++) {
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 7 + 9; i < windowHeight/tileSize * 24 + 11; i += windowHeight/tileSize) {    // le 9 jauge la hauteur
            setTile(i, 2);
        }

        // CHEMIN N2
        for (int i = 6; i < windowHeight/tileSize * 5 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 4 + 7; i < windowHeight/tileSize * 5 - 4 ; i++) {  // chemin vertical
            setTile(i, 2);
        }

        for (int i = windowHeight/tileSize * 6 -20; i < windowHeight/tileSize * 25 +2;  i += windowHeight/tileSize) { // H 4
            setTile(i, 2); //H
        }

        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 12+3; i < windowHeight/tileSize * 17; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 8+14; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }

        printMap(mapLayer);
    }

    @Override
    public int[] getStartX() {
        int[] x = {0,0};
        return x;
    }

    @Override
    public int[] getStartY() {
        int[] y = {2,6};
        return y;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getProblematicPoints() {
        return new ArrayList<>();
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(250,200,mapLayer, gameInfo);
        Tree tree1 = new Tree(800,350,mapLayer, gameInfo);
        Tree tree2 = new Tree(500,350,mapLayer, gameInfo);
        Tree tree3 = new Tree(700,500,mapLayer, gameInfo);
        Tree tree4 = new Tree(400,500,mapLayer, gameInfo);
        Tree tree5 = new Tree(1000,500,mapLayer, gameInfo);
        Tree tree6 = new Tree(50,450,mapLayer, gameInfo);



        decor.add(tree);
        decor.add(tree1);
        decor.add(tree2);
        decor.add(tree3);
        decor.add(tree4);
        decor.add(tree5);
        decor.add(tree6);

    }
}
