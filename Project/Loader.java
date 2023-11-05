package TowerDefense;

import javafx.scene.image.Image;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Loader {
    private static final DecimalFormat df = new DecimalFormat("00");
    private static int numberOfFrames;
    private static String folder;
    private static final ArrayList<Image> expl01 = new ArrayList<>();
    private static final ArrayList<Image> expl06 = new ArrayList<>();
    private static final ArrayList<Image> electricity = new ArrayList<>();
    private static final ArrayList<Image> smoke = new ArrayList<>();
    private static final ArrayList<Image> walkingZombie = new ArrayList<>();
    private static final ArrayList<Image> runningZombie = new ArrayList<>();
    private static final ArrayList<Image> electrocutedZombie = new ArrayList<>();
    private static final ArrayList<Image> death01 = new ArrayList<>();
    private static final ArrayList<Image> death02 = new ArrayList<>();
    private static final ArrayList<Image> lightning = new ArrayList<>();

    public static void loadAnimations(){
        loadExpl01();
        loadExpl06();
        loadElectricity();
        loadSmoke();
        loadWalking();
        loadRunning();
        loadElectrocuted();
        loadDeath01();
        loadDeath02();
        loadLightning();
    }

    private static void loadExpl01(){
        expl01.clear();
        numberOfFrames = 24;
        folder = "expl01";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image = new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            expl01.add(image);
        }
    }
    private static void loadExpl06(){
        expl06.clear();
        numberOfFrames = 32;
        folder = "expl06";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image = new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            expl06.add(image);
        }
    }
    private static void loadElectricity(){
        electricity.clear();
        numberOfFrames = 11;
        folder = "electricity";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            electricity.add(image);
        }
    }
    private static void loadSmoke(){
        smoke.clear();
        numberOfFrames = 90;
        folder = "smoke";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            smoke.add(image);
        }
    }
    private static void loadWalking(){
        walkingZombie.clear();
        numberOfFrames = 32;
        folder = "zombie/walk";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            walkingZombie.add(image);
        }
    }
    private static void loadRunning(){
        runningZombie.clear();
        numberOfFrames = 32;
        folder = "zombie/run";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            runningZombie.add(image);
        }
    }

    private static void loadElectrocuted(){
        electrocutedZombie.clear();
        numberOfFrames = 5;
        folder = "zombie/electrocuted";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            electrocutedZombie.add(image);
        }
    }
    private static void loadDeath01(){
        death01.clear();
        numberOfFrames = 17;
        folder = "zombie/death01";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            death01.add(image);
        }
    }

    private static void loadDeath02(){
        death02.clear();
        numberOfFrames = 17;
        folder = "zombie/death02";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            death02.add(image);
        }
    }
    private static void loadLightning(){
        lightning.clear();
        numberOfFrames = 10;
        folder = "lightning";
        for (int i = 1; i <= numberOfFrames; i ++){
            Image image =new Image(Main.class.getResourceAsStream(
                    "resources/image/" + folder +"/" + df.format(i) + ".png"));
            lightning.add(image);
        }
    }

    public static ArrayList<Image> getExpl01(){
        return expl01;
    }
    public static ArrayList<Image> getExpl06(){
        return expl06;
    }
    public static ArrayList<Image> getElectricity(){
        return electricity;
    }
    public static ArrayList<Image> getSmoke(){
        return smoke;
    }
    public static ArrayList<Image> getWalkingZombie(){
        return walkingZombie;
    }
    public static ArrayList<Image> getRunningZombie(){
        return runningZombie;
    }
    public static ArrayList<Image> getElectrocutedZombie(){
        return electrocutedZombie;
    }
    public static ArrayList<Image> getDeath01(){
        return death01;
    }
    public static ArrayList<Image> getDeath02(){ return death02; }
    public static ArrayList<Image> getLightning(){
        return lightning;
    }
}
