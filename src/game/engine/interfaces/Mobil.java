package game.engine.interfaces;

// Interface containing the methods available to all objects that has mobility (i.e. can move)
//within the game
public interface Mobil {
    int getDistance();
    void setDistance(int distance);
    int getSpeed();
    void setSpeed(int speed);
}
