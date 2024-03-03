package game.engine.titans;

public abstract class titan {
    final int baseHealth;
    public int getBaseHealth() {
        return baseHealth;
    }
    int currentHealth ;
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }


    final int baseDamage;
    public int getBaseDamage() {
        return baseDamage;
    }



    public int getHeightInMeters() {
        return heightInMeters;
    }

    final int heightInMeters;

    public int getDistanceFromBase() {
        return distanceFromBase;
    }

    public void setDistanceFromBase(int distanceFromBase) {
        this.distanceFromBase = distanceFromBase;
    }

    int distanceFromBase;
    int speed;
    int resourcesValue;
    final int dangerLevel;



    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    protected titan(int baseHealth, int currentHealth, int baseDamage, int heightInMeters, int dangerLevel) {
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.dangerLevel = dangerLevel;
    }
}
