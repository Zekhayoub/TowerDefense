package TowerDefense.logbook;

public class EnemyDescription {
    private final int enemyType;

    public EnemyDescription(int enemyType) {
        this.enemyType = enemyType;
    }

    public int[] getValues() {
        // damage, health, reward
        int[] values;
        if (enemyType == 0){ // Classic
            values = new int[]{10, 100, 20};
        }
        else if (enemyType == 1){ // Fast
            values = new int[]{5, 50, 30};
        }
        else if (enemyType == 2){ // RealZombie
            values = new int[]{15, 200, 40};
        }
        else if (enemyType == 3){ // BigEnemy
            values = new int[]{10, 200, 150};
        }
        else if (enemyType == 4){ // EatingEnemy
            values = new int[]{10, 100, 100};
        }
        else if (enemyType == 5){ // HealingEnemy
            values = new int[]{10, 250, 75};
        }
        else if (enemyType == 6){ // Spooky
            values = new int[]{0, 100, 300};  // damage
        }
        else if (enemyType == 7){ // Shortcut
            values = new int[]{10, 75, 75};
        }
        else { // Boss
            values = new int[]{25, 2000, 1250};
        }

        return values;
    }

    public String[] getTexts() {
        // name, speed, infection, description
        String[] texts;
        if (enemyType == 0){ // Classic
            texts = new String[]{"Classic", "Slow", "Tuberculosis", "Beware of its normality"};
        }
        else if (enemyType == 1){ // Fast
            texts = new String[]{"Fast", "Very fast", "Cholera", "Faster than average (but less tough)"}; //
        }
        else if (enemyType == 2){ // RealZombie
            texts = new String[]{"Real Zombie", "Slow", "Leprosy", "He might come back to life"}; //
        }
        else if (enemyType == 3){ // BigEnemy
            texts = new String[]{"Big", "Fast", "H1N1 Influenza", "He's got it all, speed and toughness"}; //
        }
        else if (enemyType == 4){ // EatingEnemy
            texts = new String[]{"Eating", "Variable", "Ebola", "Eats some of his friends,"  + "\n" + "which makes him stronger but slower"}; //
        }
        else if (enemyType == 5){ // HealingEnemy
            texts = new String[]{"Healing", "Slow", "Diphtheria", "Heals the zombies that are around him," + "\n" + "but cannot heal himself"}; //
        }
        else if (enemyType == 6){ // Spooky
            texts = new String[]{"Spooky", "Very fast", "Plague", "BEWARE"+ "\n" + "If this zombie reaches the end, you'll get" + "\n" +  "a jumpscare and lose all of your HP"}; //
        }
        else if (enemyType == 7){ // Shortcut
            texts = new String[]{"Shortcut", "Slow", "Smallpox", "A way? What way? " + "\n" +
                    "This enemy don't know this word!"};
        }
        else { // Boss
            texts = new String[]{"Boss", "Very Slow", "Coronavirus", "Highly infectious." + "\n"
                    + "Stop him as soon as possible"};
        }
        return texts;
    }

}
