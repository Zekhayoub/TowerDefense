package TowerDefense;

import TowerDefense.DecorObject.Object;
import TowerDefense.tiles.GrassTile;
import TowerDefense.tiles.OrientationDetection;
import TowerDefense.tiles.WaterTile;
import TowerDefense.tiles.WayTile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Map extends Main{
    protected ArrayList<Integer> map;
    protected ArrayList<ArrayList<Integer>> moveCoordinates;
    protected int windowWidth, windowHeight, tileSize;
    protected int customWindowWidth, customWindowHeight;
    protected GameInfo gameInfo;
    protected ArrayList<Object> decor;


    public Map(GameInfo gameInfo){
        this.windowHeight = gameInfo.getWindowHeight();
        this.windowWidth = gameInfo.getWindowWidth();
        this.tileSize = gameInfo.getTileSize();
        this.gameInfo =gameInfo;
        this.map = new ArrayList<>();
        this.decor = new ArrayList<>();
        this.moveCoordinates = new ArrayList<>();
    }

    public abstract void createNewMap(Pane mapLayer);

    private void customRes(){
        customWindowHeight = tileSize*16;
        customWindowWidth = tileSize*24;
    }

    public int margin(){
        return (windowWidth-customWindowWidth)/2;
    }

    private void addBackground(Pane mapLayer){
        Rectangle rectangle = new Rectangle(windowWidth, windowHeight, Color.BLACK);
        mapLayer.getChildren().add(rectangle);
    }

    public void printMap(Pane mapLayer){
        int o = 0;
        customRes();
        addBackground(mapLayer);
        OrientationDetection oD = new OrientationDetection(this, customWindowWidth, customWindowHeight, tileSize);
        for (int i = margin(); i < customWindowWidth+margin(); i += gameInfo.getTileSize()){
            for (int j = 0; j < customWindowHeight; j += gameInfo.getTileSize()){
                ImageView imageView = new ImageView();
                if (map.get(o) == 1){
                    GrassTile tile = new GrassTile(i,j, gameInfo);
                    imageView = tile.addTile();
                }
                else if ( map.get(o) == 2){
                    WayTile tile = new WayTile(i,j, gameInfo);
                    imageView = oD.printWayTileImage(tile);
                }
                else if ( map.get(o) == 3){
                    WaterTile tile = new WaterTile(i,j, gameInfo);
                    imageView = oD.printWaterTileImage(tile);
                }
                o ++;
                if (o == 385){
                    break;
                }
                mapLayer.getChildren().add(imageView);
            }
            if (o == 385) {
                break;
            }
        }
    }

    public ArrayList<Integer> getMap() {
        return map;
    }

    public void setTile(int position, int value){
        map.set(position,value);
    }

    public int getRealTile(int x, int y){
        return getTile(x*(windowHeight/tileSize) + y);
    }

    public int getTile(int position){
        return map.get(position);
    }

    public  abstract ArrayList<ArrayList<Integer>> getProblematicPoints();

    public abstract void generateObjects(Pane mapLayer);

    public ArrayList<Object> getDecor(){
        return decor;
    }
}
