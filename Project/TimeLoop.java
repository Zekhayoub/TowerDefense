package TowerDefense;

public class TimeLoop {
    private boolean first = true;
    private boolean fire = true;
    private boolean waiting;
    private double  deltaTime = 0;
    //private double totalTime = 0;
    private boolean start = false;
    private double elapsedTime = 0;
    private long timeLeft;
    private GameInfo gameInfo;

    public TimeLoop(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    public boolean loopInterval(double interval){
        if (fire && !first){
            fire = false;
            deltaTime = 0;
        }
        else{
            if (deltaTime >= interval){
                fire = true;
            }
            else {
                deltaTime += Time.delta();
            }
        }
        if (first){
            first = false;
        }
        return fire;
    }

    public boolean enemyInterval(double interval){
        if (fire){
            fire = false;
            deltaTime = 0;
        }
        else{
            if (deltaTime >= interval/gameInfo.getGameDifficulty()){
                fire = true;
            }
            else {
                deltaTime += Time.delta();
            }
        }
        return fire;
    }

    public boolean waveLoop(double min, double max){
        if(deltaTime >= min/gameInfo.getGameDifficulty() && !fire){
            gameInfo.updateWaveNumber();
            gameInfo.updateGameDifficulty();
        }

        if (deltaTime < min/gameInfo.getGameDifficulty()){
            fire = false;
            deltaTime += Time.delta();
        }
        else if (deltaTime >= max * gameInfo.getGameDifficulty()){
            deltaTime = 0;
            if (gameInfo.getWaveNumber() == 4 && !waiting){
                this.waiting = true;
            }
        }
        else {
            deltaTime += Time.delta();
            fire = true;
        }
        return fire;
    }

    public boolean stageLoop(double waitTime, TimeLoop waveInterval){
        if (waveInterval.getWaiting()){
            start = true;
        }
        else{
            fire = true;
        }
        if (start){
            if (elapsedTime <= waitTime/gameInfo.getGameDifficulty()){
                fire = false;
                elapsedTime += Time.delta();
                timeLeft = (long) (waitTime - elapsedTime);
                //System.out.println("Time left before next wave: " + timeLeft);
            }
            else{
                waveInterval.setWaiting(false);
                gameInfo.updateWaveNumber();
                elapsedTime = 0;
                start = false;
                fire = true;
            }
        }
        return fire;
    }

    public void setWaiting(boolean waiting){
        this.waiting = waiting;
    }
    public boolean getWaiting(){
        return this.waiting;
    }
    public long getTimeLeft(){
        return timeLeft;
    }
}

