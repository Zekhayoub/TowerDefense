package TowerDefense;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Viewer {
    protected Pane layer;
    protected Node node;
    protected int x, y, dx,dy;
    protected GameInfo gameInfo;

    public Viewer(Pane layer, int x, int y, int dx, int dy, GameInfo gameInfo){
        this.layer = layer;
        this.x = x;
        this.y = y;
        this.dx = (int)Math.round(dx* gameInfo.getScaling());
        this.dy= (int)Math.round(dy* gameInfo.getScaling());

    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public Node getNode() {
        return node;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return this.dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void move() {
        x += dx;
        y += dy;
    }
    public void removeFromLayer(){
        layer.getChildren().remove(node);
    }

    public void setNode(Node node){
        this.node = node;
    }

    public void relocate(){
        if (node != null){
            node.relocate(x,y);
        }
    }

    public void add(){
        layer.getChildren().add(node);
        node.relocate(x,y);
    }
}
