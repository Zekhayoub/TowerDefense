package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map1 extends Map implements SinglePath{


    public Map1(GameInfo gameInfo){
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

        for (int i = windowHeight/tileSize * 3 + 6; i < windowHeight/tileSize * 20 + 6; i += windowHeight/tileSize) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 11 +6; i < windowHeight/tileSize * 12 - 3 ; i++) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 3 +6; i < windowHeight/tileSize * 4 - 3 ; i++) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 3 + 13; i < windowHeight/tileSize * 21 + 11; i += windowHeight/tileSize) {
            setTile(i, 2);
        }
        for (int i = windowHeight/tileSize * 21 + 13; i < windowHeight/tileSize * 22 ; i++) {  // chemin vertical
            setTile(i, 2);
        }

        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 14+8; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 14+9; i < windowHeight/tileSize * 19; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }


        //Ajout des tiles sur le Pane
        printMap(mapLayer);
    }

    public ArrayList<ArrayList<Integer>> getProblematicPoints(){
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>());
        points.get(0).add(11);
        points.get(0).add(6);
        points.get(0).add(-1); //Mettre en premier la direction du chemin le + grand
        points.get(0).add(0);
        points.get(0).add(0); // Ici mettre la direction du raccourci
        points.get(0).add(1);

        points.add(new ArrayList<>());
        points.get(1).add(11);
        points.get(1).add(13);
        points.get(1).add(1);
        points.get(1).add(0);

        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        //Rock rock = new Rock(450,500, mapLayer, gameInfo);
        Tree tree = new Tree(250,350,mapLayer, gameInfo);
        //decor.add(rock);
        decor.add(tree);
    }

    @Override
    public int getStartX() {
        return 0;
    }

    @Override
    public int getStartY() {
        return 2;
    }
}
