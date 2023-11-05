package TowerDefense;

import TowerDefense.DecorObject.Object;

import java.util.ArrayList;

public class PositionControl {
    private final ArrayList<Integer> map;
    private int turretSize;
    private final int windowWidth;
    private final int windowHeight;
    private final int tileSize;
    private final ArrayList<Turrets> onMapTurrets;
    private final Map realMap;
    private final ArrayList<Object> decor;

    public PositionControl(Map realMap, ArrayList<Turrets> onMapTurrets, int windowWidth, int windowHeight, int tileSize) {
        this.realMap = realMap;
        this.onMapTurrets = onMapTurrets;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.tileSize = tileSize;
        this.map = realMap.getMap();
        this.decor = realMap.getDecor();
    }

    public Boolean posVerif(int x, int y, int turretSize) {
        boolean bool = false;
        int security = 2;
        int margin = realMap.margin();
        int i = (y + turretSize) / tileSize ;
        int j = (x + turretSize-margin) / tileSize ;
        int l = (y - turretSize) / tileSize ;
        int m = (x - turretSize-margin) / tileSize ;
        int o = j * (windowHeight/tileSize) + i;
        int p = m * (windowHeight/tileSize) + l;
        int q = j * (windowHeight/tileSize) + l;
        int r = m * (windowHeight/tileSize) + i;
        if (map.get(o) == 1 && map.get(p) == 1 && map.get(q) == 1 && map.get(r) == 1 &&
                x >= 0 && x + turretSize*2 <= windowWidth &&
                y >= 0 && y + turretSize*2 <= windowHeight) {
            bool = true;
            for (Turrets k : onMapTurrets) {
                int otherTurretX = k.getXTurret();
                int otherTurretY = k.getYTurret();
                if (x + turretSize + security >= otherTurretX && x - turretSize - security  <= otherTurretX + k.getSize() * 2 &&
                        y + turretSize + security >= otherTurretY && y - turretSize - security <= otherTurretY + k.getSize() * 2) {
                    bool = false;
                    break;
                }
            }
            for (Object s : decor){
                if (x + turretSize >= s.getX() && x - turretSize <= s.getX() + s.getWidth() && y + turretSize >= s.getY() && y - turretSize <= s.getY() + s.getHeight()){
                    bool = false;
                    break;
                }
            }
        } return bool;
    }
}



