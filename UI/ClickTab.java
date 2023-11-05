package TowerDefense.UI;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class ClickTab implements EventHandler<MouseEvent> {
    private final ArrayList<Node> shapes;
    private final ArrayList<ImageView> tabIcons;
    private final ArrayList<Group> tabs;
    private final ArrayList<Integer> tabTypeList;
    private final Group tabGroup;
    private final UserInterface userInterface;
    private final int tabType; // 0 normal, 1 small

    public ClickTab(ArrayList<Node> shapes, ArrayList<ImageView> tabIcons, ArrayList<Group> tabs, ArrayList<Integer> tabTypeList,
                    Group tabGroup, UserInterface userInterface, int tabType) {
        this.shapes = shapes;
        this.tabIcons = tabIcons;
        this.tabs = tabs;
        this.tabTypeList = tabTypeList;
        this.tabGroup = tabGroup;
        this.userInterface = userInterface;
        this.tabType = tabType;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        Interpolator easeOutQuint = interpolator();

        Timeline timeline = new Timeline();

        if (!userInterface.getOpen() && tabs.size() == 0) { // false, montée
            if (tabType == 0) {
                animateTab(timeline, tabGroup, easeOutQuint, new Duration(300), new Duration(2300), 0, -240);
            } else {
                animateTab(timeline, tabGroup, easeOutQuint, new Duration(300), new Duration(2300), 0, -70);
            }
            for (int i = 0; i < 5; i++) {
                animateEnterButton(shapes, shapes.get(i), tabType);
                tabIcons.get(i).translateXProperty().bind(shapes.get(i).translateXProperty());
                tabIcons.get(i).translateYProperty().bind(shapes.get(i).translateYProperty());
            }
            tabs.add(tabGroup);
            tabTypeList.add(tabType);
            userInterface.setOpen(true);
        } else { // true
            if (tabs.get(0) == tabGroup){ // descente
                if (tabType == 0) {
                    animateTab(timeline, tabGroup, easeOutQuint, Duration.ZERO, new Duration(2000), -240, 0);
                } else {
                    animateTab(timeline, tabGroup, easeOutQuint, Duration.ZERO, new Duration(1000), -70, 0);
                }
                for (int i = 0; i < 5; i++) {
                    animateExitButton(shapes, shapes.get(i), tabType);
                }
                tabs.clear();
                tabTypeList.clear();
                userInterface.setOpen(false);
            } else { // changement
                if (tabType == 0 && tabTypeList.get(0) == 0) { // normal -> normal
                    animateTab(timeline, tabs.get(0), easeOutQuint, Duration.ZERO, new Duration(2000), -240, 0);
                    animateTab(timeline, tabGroup, easeOutQuint, Duration.ZERO, new Duration(2000), 0, -240);
                } else if (tabType == 0 && tabTypeList.get(0) == 1) { // small -> normal
                    animateTab(timeline, tabs.get(0), easeOutQuint, Duration.ZERO, new Duration(1000), -70, 0);
                    animateTab(timeline, tabGroup, easeOutQuint, new Duration(300), new Duration(2300), 0, -240);
                    for (int i = 0; i < 5; i++) {
                        animateChangerNormalButton(shapes, shapes.get(i));
                    }
                } else { // normal -> small
                    animateTab(timeline, tabs.get(0), easeOutQuint, Duration.ZERO, new Duration(2000), -240, 0);
                    animateTab(timeline, tabGroup, easeOutQuint, new Duration(300), new Duration(2300), 0, -70);
                    for (int i = 0; i < 5; i++) {
                        animateChangerSmallButton(shapes, shapes.get(i));
                    }
                }
                tabs.clear();
                tabTypeList.clear();
                tabs.add(tabGroup);
                tabTypeList.add(tabType);
                userInterface.setOpen(true);
            }
        }
        timeline.setRate(2); //1, 1.5, -2-, 2.5, 3
        tabGroup.requestFocus();
        timeline.playFromStart();
    }

    public void animateTab(Timeline timeline, Group tabGroup, Interpolator easeOutQuint, Duration firstDuration,
                            Duration secondDuration, int y1, int y2){
        timeline.getKeyFrames().addAll(
                new KeyFrame(firstDuration, new KeyValue(tabGroup.translateYProperty(), y1, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tabGroup.translateYProperty(), y2, easeOutQuint))
        );
    }

    public void animateEnterButton(ArrayList<Node> shapes, Node tab, int tabType) {
        Interpolator easeOutQuint = interpolator();
        Timeline timeline = new Timeline();
        if (tabType == 0) { // normal
            if (shapes.indexOf(tab) == 4) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),295, -172, 90);
            } else if (shapes.indexOf(tab) == 3) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),250, -129, 90);
            } else if (shapes.indexOf(tab) == 2) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),205, -86, 90);
            } else if (shapes.indexOf(tab) == 1) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),160, -43, 90);
            } else {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),115, 0, 90);
            }
            timeline.setRate(3.5); // 3
        } else { // small
            if (shapes.indexOf(tab) == 4) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),0, -60, 0);
            } else if (shapes.indexOf(tab) == 3) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),0, -60, 0);
            } else if (shapes.indexOf(tab) == 2) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),0, -60, 0);
            } else if (shapes.indexOf(tab) == 1) {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),0, -60, 0);
            } else {
                enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),0, -60, 0);
            }
            timeline.setRate(4);
        }
        timeline.playFromStart();

    }

    public void animateExitButton(ArrayList<Node> shapes, Node tab, int tabType) {
        Interpolator easeOutQuint = interpolator();
        Timeline timeline = new Timeline();
        if (tabType == 0) {
            if (shapes.indexOf(tab) == 4) {
                exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),295, -172, 90, 0);
            } else if (shapes.indexOf(tab) == 3) {
                exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),250, -129, 90, 0);
            } else if (shapes.indexOf(tab) == 2) {
                exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),205, -86, 90, 0);
            } else if (shapes.indexOf(tab) == 1) {
                exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),160, -43, 90, 0);
            } else {
                exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),115, 0, 90, 0);
            }
            timeline.setRate(3); // 3
        } else {
            if (shapes.indexOf(tab) == 4) {
                exit(timeline, tab, easeOutQuint, new Duration(500), new Duration(2500),0, -60, 0, 0);
            } else if (shapes.indexOf(tab) == 3) {
                exit(timeline, tab, easeOutQuint, new Duration(500), new Duration(2500),0, -60, 0, 0);
            } else if (shapes.indexOf(tab) == 2) {
                exit(timeline, tab, easeOutQuint, new Duration(500), new Duration(2500),0, -60, 0, 0);
            } else if (shapes.indexOf(tab) == 1) {
                exit(timeline, tab, easeOutQuint, new Duration(500), new Duration(2500),0, -60, 0, 0);
            } else {
                exit(timeline, tab, easeOutQuint, new Duration(500), new Duration(2500),0, -60, 0, 0);
            }
            timeline.setRate(4);
        }
        timeline.playFromStart();
    }

    public void animateChangerNormalButton(ArrayList<Node> shapes, Node tab){
        Interpolator easeOutQuint = interpolator();
        Timeline timeline = new Timeline();

        if (shapes.indexOf(tab) == 4) {
            enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),295, -172, 90);
        } else if (shapes.indexOf(tab) == 3) {
            enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),250, -129, 90);
        } else if (shapes.indexOf(tab) == 2) {
            enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),205, -86, 90);
        } else if (shapes.indexOf(tab) == 1) {
            enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),160, -43, 90);
        } else {
            enter(timeline, tab, easeOutQuint, Duration.ZERO, new Duration(2000),115, 0, 90);
        }

        timeline.setRate(4);
        timeline.playFromStart();
    }

    public void animateChangerSmallButton(ArrayList<Node> shapes, Node tab){
        Interpolator easeOutQuint = interpolator();
        Timeline timeline = new Timeline();

        if (shapes.indexOf(tab) == 4) {
            exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),295, -172, 90,  -60);
        } else if (shapes.indexOf(tab) == 3) {
            exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),250, -129, 90, -60);
        } else if (shapes.indexOf(tab) == 2) {
            exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),205, -86, 90, -60);
        } else if (shapes.indexOf(tab) == 1) {
            exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),160, -43, 90, -60);
        } else {
            exit(timeline, tab, easeOutQuint, new Duration(1100), new Duration(3100),115, 0, 90, -60);
        }

        timeline.setRate(4);
        timeline.playFromStart();
    }

    public void enter(Timeline timeline, Node tab, Interpolator easeOutQuint, Duration firstDuration,
                       Duration secondDuration, int x, int y, int rotation){
        timeline.getKeyFrames().addAll(
                new KeyFrame(firstDuration, new KeyValue(tab.rotateProperty(), 0, easeOutQuint)),
                new KeyFrame(firstDuration, new KeyValue(tab.translateXProperty(), 0, easeOutQuint)),
                new KeyFrame(firstDuration, new KeyValue(tab.translateYProperty(), 0, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.rotateProperty(), rotation, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.translateXProperty(), x, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.translateYProperty(), y, easeOutQuint))
        );
    }

    public void exit(Timeline timeline, Node tab, Interpolator easeOutQuint, Duration firstDuration,
                      Duration secondDuration, int x, int y, int rotation, int finalY){
        timeline.getKeyFrames().addAll(
                new KeyFrame(firstDuration, new KeyValue(tab.rotateProperty(), rotation, easeOutQuint)),
                new KeyFrame(firstDuration, new KeyValue(tab.translateXProperty(), x, easeOutQuint)),
                new KeyFrame(firstDuration, new KeyValue(tab.translateYProperty(), y, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.rotateProperty(), 0, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.translateXProperty(), 0, easeOutQuint)),
                new KeyFrame(secondDuration, new KeyValue(tab.translateYProperty(), finalY, easeOutQuint))
        );
    }

    public Interpolator interpolator() {

        return new Interpolator() {
            @Override
            protected double curve(double t) {
                return 1 - Math.pow(1 - t, 5); //3, 4, 5
            }
        };
    }

}

