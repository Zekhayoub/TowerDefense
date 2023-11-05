package TowerDefense.maps;

import TowerDefense.DecorObject.Tree;
import TowerDefense.GameInfo;
import TowerDefense.Map;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Map6 extends Map implements SinglePath{

    public Map6(GameInfo gameInfo) {
        super(gameInfo);
    }

    @Override
    public void createNewMap(Pane mapLayer) {
        for (int i =0; i <= windowHeight/tileSize; i ++ ) {
            for (int j = 0; j <= windowWidth/tileSize; j ++) {
                map.add(1);
            }
        }
        for (int i = windowHeight/tileSize * 6 + 2; i < windowHeight/tileSize * 21 + 2; i += windowHeight/tileSize) {
            setTile(i, 2);//H1
        }
        for (int i = windowHeight/tileSize * 15 + 8; i < windowHeight/tileSize * 21 + 8; i += windowHeight/tileSize) {
            setTile(i, 2);//H2
        }
        for (int i = windowHeight/tileSize * 3 + 5; i < windowHeight/tileSize * 15 + 5; i += windowHeight/tileSize) {
            setTile(i, 2);//H3
        }
        for (int i = windowHeight/tileSize * 3 + 8; i < windowHeight/tileSize * 12+ 8; i += windowHeight/tileSize) {
            setTile(i, 2);//H4
        }
        for (int i = windowHeight/tileSize * 2 + 11; i < windowHeight/tileSize * 12+ 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H5
        }
        for (int i = windowHeight/tileSize * 2 + 13; i < windowHeight/tileSize * 15+ 13; i += windowHeight/tileSize) {
            setTile(i, 2);//H6
        }
        for (int i = windowHeight/tileSize * 15 + 11; i < windowHeight/tileSize * 21+ 11; i += windowHeight/tileSize) {
            setTile(i, 2);//H7
        }
        for (int i = windowHeight/tileSize * 21 + 13; i < windowHeight/tileSize * 23+ 13; i += windowHeight/tileSize) {
            setTile(i, 2);//H8
        }

        for (int i = windowHeight/tileSize * 6 + 0; i < windowHeight/tileSize * 6 +2 ; i++) {
            setTile(i, 2); //V1
        }
        for (int i = windowHeight/tileSize * 21+ 2; i < windowHeight/tileSize * 21 +9 ; i++) {
            setTile(i, 2); //V2
        }
        for (int i = windowHeight/tileSize * 15 + 5; i < windowHeight/tileSize * 15 +8 ; i++) {
            setTile(i, 2); //V3
        }
        for (int i = windowHeight/tileSize * 3 + 5; i < windowHeight/tileSize * 3 +8; i++) {
            setTile(i, 2); //V4
        }
        for (int i = windowHeight/tileSize * 12 + 8; i < windowHeight/tileSize * 12 +12; i++) {
            setTile(i, 2); //V5
        }
        for (int i = windowHeight/tileSize * 2 + 11; i < windowHeight/tileSize * 2 +13 ; i++) {
            setTile(i, 2); //V6
        }
        for (int i = windowHeight/tileSize * 15 + 11; i < windowHeight/tileSize * 15 +14; i++) {
            setTile(i, 2); //V7
        }
        for (int i = windowHeight/tileSize * 18 + 8; i < windowHeight/tileSize * 18 + 11; i++) {
            setTile(i, 2); //V7
        }


        for (int i = windowHeight/tileSize * 21 + 11; i < windowHeight/tileSize * 21 +13 ; i++) {
            setTile(i, 2); //V9
        }
        for (int i = windowHeight/tileSize * 22 + 13; i < windowHeight/tileSize * 22 +16 ; i++) {
            setTile(i, 2); //V10
        }
        //Water
        for (int j = 0; j < 2 ; j++) {
            for (int i = windowHeight/tileSize * 1+3; i < windowHeight/tileSize * 6; i += windowHeight/tileSize) {
                setTile(i+j, 3);
            }
        }
        printMap(mapLayer);
    }


    @Override
    public ArrayList<ArrayList<Integer>> getProblematicPoints() {
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>());
        points.get(0).add(18);
        points.get(0).add(8);
        points.get(0).add(-1);
        points.get(0).add(0);
        points.get(0).add(0);
        points.get(0).add(1);

        points.add(new ArrayList<>());
        points.get(1).add(18);
        points.get(1).add(11);
        points.get(1).add(1);
        points.get(1).add(0);

        return points;
    }

    @Override
    public void generateObjects(Pane mapLayer) {
        Tree tree = new Tree(870,200,mapLayer, gameInfo);
        Tree tree1 = new Tree(870,260,mapLayer, gameInfo);
        Tree tree2= new Tree(860,720,mapLayer, gameInfo);
        Tree tree3 = new Tree(850,660,mapLayer, gameInfo);



        decor.add(tree);
        decor.add(tree1);
        decor.add(tree2);
        decor.add(tree3);


    }

    @Override
    public int getStartX() {
        return 6;
    }

    @Override
    public int getStartY() {
        return 0;
    }
}
