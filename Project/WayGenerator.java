package TowerDefense;

import java.util.ArrayList;

public class WayGenerator {
    private final Map map;
    private final int windowWidth;
    private final int windowHeight;
    private final int tileSize;
    private final ArrayList<ArrayList<Integer>> theWay = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> criticalPoints = new ArrayList<>();
    private boolean first = true;

    public WayGenerator(Map map, GameInfo gameInfo){
        this.map = map;
        this.windowHeight = gameInfo.getWindowHeight();
        this.windowWidth = gameInfo.getWindowWidth();
        this.tileSize = gameInfo.getTileSize();

    }

    private void start(int x, int y){
        ArrayList<Integer> startingPoint = new ArrayList<>();
        if (duplicatePoint(x, y)){
            theWay.add(createPoint(x,y));
            if (x - 1 >= 0 && getTile((x-1),y) == 2){
                    startingPoint = createSpeed(x , y ,-1,0);
            }
            if (x + 1 <= windowWidth/tileSize && getTile((x+1),y) == 2){
                    startingPoint = createSpeed(x, y, 1,0);
            }
            if (y - 1 >= 0 && getTile(x, y - 1) == 2){
                    startingPoint = createSpeed(x,y, 0,-1);
            }
            if (y + 1 <= windowWidth/tileSize && getTile(x, y + 1) == 2){
                    startingPoint = createSpeed(x, y,0,1);
            }
            criticalPoints.add(startingPoint);
        }
    }

    public void autoGen(int x, int y){ // x et y ici ne represent pas le nombre de pixels mais le numero de la Tile
        ArrayList<String> direction = new ArrayList<>();
        ArrayList<Integer> criticalPoint = new ArrayList<>();
        if (first){
            start(x,y);
        }
        first = false;

        if (getTile(x,y) == 2){
            if (x - 1 >= 0 && getTile((x-1),y) == 2 && duplicatePoint(x - 1, y)){
                if (straightY()){
                    criticalPoint = createSpeed(x , y ,-1,0);
                }
                direction.add("W");
            }
            if (x + 1 <= windowWidth/tileSize && getTile((x+1),y) == 2 && duplicatePoint(x + 1, y)){
                if (straightY()){
                    criticalPoint = createSpeed(x , y, 1,0);
                }
                direction.add("E");
            }
            if (y - 1 >= 0 && getTile(x, y - 1) == 2 && duplicatePoint(x, y - 1)){
                if (straightX()){
                    criticalPoint = createSpeed(x,y, 0,-1);
                }
                direction.add("N");
            }
            if (y + 1 <= windowWidth/tileSize && getTile(x, y + 1) == 2 && duplicatePoint(x, y + 1)){
                if (straightX()){
                    criticalPoint = createSpeed(x, y,0,1);
                }
                direction.add("S");
            }
            if(criticalPoint.size() != 0 && direction.size() == 1){
                criticalPoints.add(criticalPoint);
            }
            nextTile(x,y, direction);
        }
    }

    public void printWay(){
        System.out.println(theWay);
    }

    public ArrayList<ArrayList<Integer>> getCriticalPoints(){
        return criticalPoints;
    }

    private ArrayList<Integer> createSpeed(int x, int y, int dx, int dy){
        ArrayList<Integer> criticalPoint = new ArrayList<>();
        criticalPoint.add(x*tileSize + tileSize/2);
        criticalPoint.add(y*tileSize + tileSize/2);
        criticalPoint.add(dx);
        criticalPoint.add(dy);
        return criticalPoint;
    }
    private ArrayList<Integer> createDoubleSpeed(int x, int y, int dx, int dy, int dx2, int dy2){
        ArrayList<Integer> criticalPoint = new ArrayList<>();
        criticalPoint.add(x*tileSize + tileSize/2);
        criticalPoint.add(y*tileSize + tileSize/2);
        criticalPoint.add(dx);
        criticalPoint.add(dy);
        criticalPoint.add(dx2);
        criticalPoint.add(dy2);
        return criticalPoint;
    }

    private boolean straightY(){
        boolean change = false;
        if (theWay.size() >= 2 && theWay.get(theWay.size()-1).get(0) - theWay.get(theWay.size()-2).get(0) == 0){
            change = true;
        }
        return change;
    }
    private boolean straightX(){
        boolean change = false;
        if (theWay.size() >= 2 && theWay.get(theWay.size()-1).get(1) - theWay.get(theWay.size()-2).get(1) == 0){
            change = true;
        }
        return change;
    }

    private void nextTile(int x, int y, ArrayList<String> direction){
        if (direction.size() == 1){
            switch (direction.get(0)) {
                case "W":
                    x --;
                    break;
                case "E":
                    x ++;
                    break;
                case "N":
                    y --;
                    break;
                case "S":
                    y ++;
                    break;
            }
            ArrayList<Integer> point = createPoint(x, y);
            theWay.add(point);
            autoGen(x , y);
        }
        else if (direction.size() == 2){
            for(ArrayList<Integer> i : map.getProblematicPoints()){
                if (x == i.get(0) && y == i.get(1)){
                    int realX = x;
                    int realY = y;
                    if (i.get(2) == 1){
                        x ++;
                    }
                    else if (i.get(2) == - 1){
                        x --;
                    }
                    else if (i.get(3) == 1){
                        y ++;
                    }
                    else if (i.get(3) == -1){
                        y --;
                    }
                    if (i.size() == 6){
                        criticalPoints.add(createDoubleSpeed(realX,realY,i.get(2), i.get(3),i.get(4), i.get(5)));
                    }
                    else {
                        criticalPoints.add(createSpeed(realX,realY,i.get(2), i.get(3)));
                    }
                    ArrayList<Integer> point = createPoint(x, y);
                    theWay.add(point);
                    autoGen(x , y);
                    break;
                }
            }
        }
        else {
            first = true;
        }
    }

    private boolean duplicatePoint(int XTile, int YTile){
        ArrayList<Integer> point = createPoint( XTile, YTile);
        return !theWay.contains(point);
    }

    private ArrayList<Integer> createPoint(int startX, int startY){
        ArrayList<Integer> point = new ArrayList<>();
        point.add(startX);
        point.add(startY);
        return point;
    }

    private int getTile(int x, int y){
        return map.getTile(x*(windowHeight/tileSize) + y);
    }

}
