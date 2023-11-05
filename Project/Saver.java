package TowerDefense;

import javafx.scene.control.Alert;

import java.io.*;

public class Saver {
    private final GameInfo gameInfo;

    public Saver(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    public void startNewSave(){
        BufferedWriter save;
        try {
            save = new BufferedWriter(new FileWriter("save"));
            save.write(generateSaveData());
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readSaveFile(){
        try {
            BufferedReader save = new BufferedReader(new FileReader("save"));
            saveDecoder(save);
        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Save not found, creating a new game").showAndWait();
            startNewSave();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Corrupted files, creating a new game").showAndWait();
            startNewSave();
            e.printStackTrace();
        }
    }

    public void saveGame(){
        startNewSave();
    }

    private String generateSaveData(){
        gameInfo.reset();
        StringBuilder save = new StringBuilder(gameInfo.getGold() + "\n" + gameInfo.getProgress() + "\n");
        for (int i : gameInfo.getAttackLevelList()){
            save.append(i).append("\n");
        }
        return save.toString();
    }

    private void saveDecoder(BufferedReader save) throws IOException {

        gameInfo.setGold(Integer.parseInt(save.readLine()));
        gameInfo.setProgress(Integer.parseInt(save.readLine()));
        for (int i = 0; i < gameInfo.getAttackLevelList().length; i++){
            gameInfo.setAttackLevel(i , Integer.parseInt(save.readLine()));
        }
    }
}
