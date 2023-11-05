package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map2 extends Map implements SinglePath{

    public Map2(GameInfo gameInfo){
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }

        // Map is generated transversely
        // windowHeight/tileSize = 16 en y

        for (int i = 2; i < windowHeight/tileSize * 3 + 6; i += windowHeight/tileSize) { // H 1
            setTile(i, 2); // 2 à 54
        }

        for (int i = windowHeight/tileSize * 3 +3; i < windowHeight/tileSize * 4 - 5 ; i++) { // V 2
            setTile(i, 2); // 54 à 59
        }

        for (int i = windowHeight/tileSize * 3 + 7; i < windowHeight/tileSize * 11 +6;  i += windowHeight/tileSize) { // H 3
            setTile(i, 2); // 54 à 182 haut
        }

        for (int i = windowHeight/tileSize * 5 -21; i < windowHeight/tileSize * 11 +2;  i += windowHeight/tileSize) { // H 4
            setTile(i, 2); // 54 à 182 bas
        }

        for (int i = windowHeight/tileSize * 11 +2; i < windowHeight/tileSize * 12 - 4; i++) { // V 5
            setTile(i, 2); // 182 à 187
        }

        for (int i = windowHeight/tileSize * 12+2; i < windowHeight/tileSize * 20 +2;  i += windowHeight/tileSize) { // H 6
            setTile(i, 2); // 187 à 322 haut
        }

        for (int i = windowHeight/tileSize * 12+5; i < windowHeight/tileSize * 20 +2;  i += windowHeight/tileSize) { // H 7
            setTile(i, 2); // 187 à 322 bas
        }

        for (int i = windowHeight/tileSize * 20 +2; i < windowHeight/tileSize * 20 +12; i++) { // V 8
            setTile(i, 2); // 317 à 322
        }

        for (int i = windowHeight/tileSize * 20 + 11; i < windowHeight/tileSize * 24 + 40;  i += windowHeight/tileSize) { // H 9
            setTile(i, 2); // 322 à 395 // 360 à 424
        }

        //Water
        for (int j = 0; j < 4 ; j++) {
            for (int i = windowHeight/tileSize * 14+8; i < windowHeight/tileSize * 18; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }


        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 8+13; i < windowHeight/tileSize * 18; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 6+3; i < windowHeight/tileSize * 9; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }

        //Ajout des tiles sur le Pane
        printMap(mapLayer);
    }


    @Override
    public int getStartX() {
        return 0;
    }

    @Override
    public int getStartY() {
        return 2;
    }

    public ArrayList<ArrayList<Integer>> getProblematicPoints() {
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>());
        points.get(0).add(3);
        points.get(0).add(7);
        points.get(0).add(0); // grand chemin
        points.get(0).add(1);
        points.get(0).add(1); // raccourci
        points.get(0).add(0);

        points.add(new ArrayList<>());
        points.get(1).add(11);
        points.get(1).add(7);
        points.get(1).add(0);
        points.get(1).add(-1);

        points.add(new ArrayList<>());
        points.get(2).add(11);
        points.get(2).add(5);
        points.get(2).add(0); // grand chemin
        points.get(2).add(-1);
        points.get(2).add(1); // raccourci
        points.get(2).add(0);

        points.add(new ArrayList<>());
        points.get(3).add(20);
        points.get(3).add(5);
        points.get(3).add(0);
        points.get(3).add(1);

        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(1050,650,mapLayer, gameInfo);
        Tree tree1 = new Tree(280,450,mapLayer, gameInfo);
        Tree tree2 = new Tree(330,450,mapLayer, gameInfo);
        Tree tree3 = new Tree(600,350,mapLayer, gameInfo);
        decor.add(tree);
        decor.add(tree1);
        decor.add(tree2);
        decor.add(tree3);
    }
}
