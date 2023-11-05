package TowerDefense.logbook;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ClickTabLogbook implements EventHandler<MouseEvent> {
    private Group group;
    private VBox informationTab;
    private LogbookAttack logbookAttack;
    private LogbookTurret logbookTurret;
    private LogbookEnemy logbookEnemy;

    public ClickTabLogbook(Group group, VBox informationTab, LogbookTurret logbookTurret, LogbookAttack logbookAttack,
                           LogbookEnemy logbookEnemy) {
        this.group = group;
        this.informationTab = informationTab;
        this.logbookTurret = logbookTurret;
        this.logbookAttack = logbookAttack;
        this.logbookEnemy = logbookEnemy;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.group.toFront();
        informationTab.getChildren().clear();
        if (logbookTurret.getWaitingShapes().size() == 1) {
            logbookTurret.getWaitingShapes().get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            logbookTurret.getWaitingShapes().clear();
        } else if (logbookAttack.getWaitingShapes().size() == 1) {
            logbookAttack.getWaitingShapes().get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            logbookAttack.getWaitingShapes().clear();
        } else if (logbookEnemy.getWaitingShapes().size() == 1){  // NEW
            logbookEnemy.getWaitingShapes().get(0).setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            logbookEnemy.getWaitingShapes().clear();
        }
    }
}
