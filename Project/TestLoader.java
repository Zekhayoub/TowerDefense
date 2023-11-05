package TowerDefense;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestLoader {
    private static final DecimalFormat df = new DecimalFormat("00");

    public TestLoader(){
        Loader.loadAnimations();
    }

    @Test
    public void testWalking(){
        ArrayList<Image> walkingZombie = new ArrayList<>();
        int numberOfFrames = 32;
        String folder = "zombie/walk";
        for (int i = 1; i <= numberOfFrames; i ++){
            try{
                Image image =new Image(Main.class.getResourceAsStream(
                        "resources/image/" + folder +"/" + df.format(i) + ".png"));
                walkingZombie.add(image);
            }
            catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Some files are missing, please verify your installation folder").showAndWait();
            }
        }
        assert (walkingZombie.size() == Loader.getWalkingZombie().size() && Loader.getWalkingZombie().size() == numberOfFrames);
    }

    @Test
    public void testExpl01(){
        ArrayList<Image> expl01 = new ArrayList<>();
        int numberOfFrames = 24;
        String folder = "expl01";
        for (int i = 1; i <= numberOfFrames; i ++){
            try{
                Image image =new Image(Main.class.getResourceAsStream(
                        "resources/image/" + folder +"/" + df.format(i) + ".png"));
                expl01.add(image);
            }
            catch (Exception e){
                System.out.println("Veuillez vous assurez que les images du jeu sont présentes dans /resources/images/"+folder);
            }
        }
        assert (expl01.size() == Loader.getExpl01().size() && Loader.getExpl01().size() == numberOfFrames);
    }
}