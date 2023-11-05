package TowerDefense;

public class Time {
    private static boolean status = false;
    private static boolean first = true;
    private static long lastTime, totalTime;
    private static float delta = 0,speed =1;

    public static  long getTime(){
        return System.currentTimeMillis();
    }

    public static float getDeltaTime(){ //Source chaine Youtube Indie Programmer
        long currentTime = getTime();
        int deltaTime = (int) (currentTime - lastTime);
        lastTime = getTime();
        return deltaTime * 0.001f;
    }
    public static float delta(){
        if (status){
            if (!first){
                return delta*speed;
            }
            else{
                first = false;
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    public static float getSpeed(){
        return speed;
    }

    public static void update(){
        delta = getDeltaTime();
        totalTime += delta();
    }

    public static void setSpeed(float i){
        speed = i;
    }

    public static void pause(){
        status = false;
    }

    public static void unpause(){
        status = true;
    }

    public static boolean getStatus(){ return  status;}
}
