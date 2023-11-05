package TowerDefense.tiles;

import TowerDefense.Map;
import javafx.scene.image.ImageView;

public class OrientationDetection {
    private final Map map;
    private final int windowWidth;
    private final int windowHeight;
    private final int tileSize;
    public OrientationDetection(Map map, int windowWidth, int windowHeight, int tileSize){
        this.map = map;
        this.windowWidth = windowWidth;
        this.windowHeight =windowHeight;
        this.tileSize = tileSize;
    }

    public String wayOrientation(int x, int y){ // x et y ici ne represent pas le nombre de pixels mais le numero de la Tile
        if (x - 1 >= 0 && getRealTile((x-1),y) == 2 && x + 1 <= windowWidth/tileSize && getRealTile((x+1),y) == 2){
            if (y - 1 <= windowHeight/tileSize && map.getRealTile(x,y-1) == 2 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 2)
                return "quad";
            else if(y - 1 <= windowHeight/tileSize && map.getRealTile(x,y-1) == 2){
                return "invT";
            }
            else if (y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 2){
                return "T";
            }
            else{
                return "H";
            }
        }
        else if (y + 1 <= windowHeight/tileSize && map.getRealTile(x,y+1) == 2 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 2){
            if (x - 1 >= 0 && getRealTile((x-1),y) == 2 ){
                return "lT";
            }
            else if(x + 1 >= 0 && getRealTile((x+1),y) == 2){
                return "rT";
            }
            else{
                return "V";
            }
        }
        else if (x - 1 >= 0 && getRealTile((x-1),y) == 2 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 2){
            return "RU";
        }
        else if (x - 1 >= 0 && getRealTile((x-1),y) == 2 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 2){
            return "RD";
        }
        else if((x + 1 >= 0 && getRealTile((x+1),y) == 2 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 2)){
            return "LU";
        }
        else if (x + 1 >= 0 && getRealTile((x+1),y) == 2 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 2){
            return "LD";
        }
        else if (x - 1 >= 0 && getRealTile((x-1),y) == 2){
            return "H";
        }
        else if (x + 1 >= 0 && getRealTile((x+1),y) == 2){
            return "H";
        }
        else return "V";
    }

    public ImageView printWayTileImage(WayTile tile){
        ImageView imageView = new ImageView();
        switch (wayOrientation(tile.getX()/tileSize, tile.getY()/tileSize)) {
            case "H":
                imageView = tile.addTile();
                imageView.setRotate(90);
                break;
            case "V":
                imageView = tile.addTile();
                break;
            case "quad":
                imageView = tile.addQuadTile();
            case "RU":
                imageView = tile.addCornerTile();
                break;
            case "RD":
                imageView = tile.addCornerTile();
                imageView.setRotate(90);
                break;
            case "LD":
                imageView = tile.addCornerTile();
                imageView.setRotate(180);
                break;
            case "LU":
                imageView = tile.addCornerTile();
                imageView.setRotate(270);
                break;
            case "T":
                imageView = tile.addTTile();
                break;
            case "invT":
                imageView = tile.addTTile();
                imageView.setRotate(180);
                break;
            case "rT":
                imageView = tile.addTTile();
                imageView.setRotate(270);
                break;
            case "lT":
                imageView = tile.addTTile();
                imageView.setRotate(90);
                break;
        }
        return imageView;
    }

    public String waterOrientation(int x, int y){ // x et y ici ne represent pas le nombre de pixels mais le numero de la Tile
        if (x - 1 >= 0 && getRealTile((x-1),y) == 3 && x + 1 <= windowWidth/tileSize && getRealTile((x+1),y) == 3){
            if (y - 1 <= windowHeight/tileSize && map.getRealTile(x,y-1) == 3 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 3)
                return "center";
            else if(y - 1 <= windowHeight/tileSize && map.getRealTile(x,y-1) == 3){
                return "lowBorder";
            }
            else if (y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 3){
                return "highBorder";
            }
            else{
                return "center";
            }
        }
        else if (y + 1 <= windowHeight/tileSize && map.getRealTile(x,y+1) == 3 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 3){
            if (x - 1 >= 0 && getRealTile((x-1),y) == 3){
                return "rightBorder";
            }
            else if(x + 1 >= 0 && getRealTile((x+1),y) == 3){
                return "leftBorder";
            }
            else{
                return "center";
            }
        }
        else if (x - 1 >= 0 && getRealTile((x-1),y) == 3 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 3){
            return "RU";
        }
        else if (x - 1 >= 0 && getRealTile((x-1),y) == 3 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 3){
            return "RD";
        }
        else if((x + 1 >= 0 && getRealTile((x+1),y) == 3 && y + 1 <= windowHeight/tileSize && getRealTile(x,y+1) == 3)){
            return "LU";
        }
        else if (x + 1 >= 0 && getRealTile((x+1),y) == 3 && y - 1 <= windowHeight/tileSize && getRealTile(x,y-1) == 3){
            return "LD";
        }
        else return "center";
    }

    public ImageView printWaterTileImage(WaterTile tile){
        ImageView imageView = new ImageView();
        switch (waterOrientation(tile.getX()/tileSize, tile.getY()/tileSize)) {
            case "center":
                imageView = tile.addCenterTile();
                imageView.setRotate(90);
                break;
            case "RU":
                imageView = tile.addCornerTile();
                break;
            case "RD":
                imageView = tile.addCornerTile();
                imageView.setRotate(90);
                break;
            case "LD":
                imageView = tile.addCornerTile();
                imageView.setRotate(180);
                break;
            case "LU":
                imageView = tile.addCornerTile();
                imageView.setRotate(270);
                break;
            case "highBorder":
                imageView = tile.addSideTile();
                break;
            case "lowBorder":
                imageView = tile.addSideTile();
                imageView.setRotate(180);
                break;
            case "rightBorder":
                imageView = tile.addSideTile();
                imageView.setRotate(90);
                break;
            case "leftBorder":
                imageView = tile.addSideTile();
                imageView.setRotate(-90);
                break;
        }
        return imageView;
    }

    private int getRealTile(int x, int y){
        return map.getRealTile(x,y);
    }

}
