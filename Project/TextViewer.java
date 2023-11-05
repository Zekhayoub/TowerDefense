package TowerDefense;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TextViewer {
    private int windowHeight, waveNumber, stageNumber, goldNumber, lifeNumber;
    private ArrayList<Text> textList = new ArrayList<>();
    private Font VerlagCondensedBold =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Book.otf"), 30);
    private Font VerlagCondensedLight =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/VerlagCondensed-Light.otf"), 30);
    private Font VerlagLightItalic =
            Font.loadFont(Main.class.getResourceAsStream("resources/fonts/Verlag-LightItalic.otf"), 20);

    public TextViewer(int windowHeight, int waveNumber, int stageNumber, int lifeNumber) {
        this.windowHeight = windowHeight;
        this.waveNumber = waveNumber;
        this.stageNumber = stageNumber;
        this.lifeNumber = lifeNumber;
    }

    public void generateText(){
        textList.add(generateWaveText());
        textList.add(generateGoldText());
        textList.add(generateLifeText());
        textList.add(generateTimerText());
    }

    public ArrayList<Text> getTextList(){
        return textList;
    }

    public Text get(int i){
        return textList.get(i);
    }

    public void setTextList(int i, Text text){
        textList.set(i, text);
    }

    private Text generateWaveText(){ // Mettre aussi le Stage Number
        Text text = new Text(50,(int) (windowHeight/25),stageNumber + " - " + waveNumber);
        text.setFont(VerlagCondensedBold);
        text.setFill(Color.WHITE);
        return text;
    }

    private Text generateTimerText(){
        Text text = new Text(120,(int) (windowHeight/25),"Wave in progress");
        text.setFont(VerlagLightItalic);
        text.setFill(Color.WHITE);
        return text;
    }

    private Text generateLifeText(){
        Text text = new Text(980,(int) (windowHeight/25), "" + lifeNumber); // lifeNumber + " LP"
        text.setFont(VerlagCondensedBold);
        text.setFill(Color.WHITE);
        return text;
    }

    private Text generateGoldText(){
        Text text = new Text(1095,(int) (windowHeight/25),"" + goldNumber); // "$$" + goldNumber
        text.setFont(VerlagCondensedBold);
        text.setFill(Color.WHITE);
        return text;
    }

}
