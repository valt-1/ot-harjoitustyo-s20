package spaceinvaders.domain;

public class Game {
    
    private double sizeX;
    private double sizeY;
    private LaserGun laserGun;
    
    public Game(double sizeX, double sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.laserGun = new LaserGun(sizeX / 2, sizeY - 10);
    }

    public double getSizeX() {
        return this.sizeX;
    }

    public double getSizeY() {
        return this.sizeY;
    }
    
    public LaserGun getLaserGun() {
        return this.laserGun;
    }

}
