package Game;

import java.awt.*;

public abstract class GameObject {

    // protected variables
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    // GameObject constructor
    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // Abstract method
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract  Rectangle getBounds();

    // Setter for x
    public void setX(int x){
        this.x = x;
    }

    // Setter for y
    public void setY(int y) {
        this.y = y;
    }

    // Getter for x
    public int getX(){
        return x;
    }

    // Getter for y
    public int getY() {
        return y;
    }

    // Setter for ID
    public void setId(ID id) {
        this.id = id;
    }

    // Getter for ID
    public ID getId() {
        return id;
    }

    // Setter for velX
    public void setVelX(int velX) {
        this.velX = velX;
    }

    // Setter for velY
    public void setVelY(int velY) {
        this.velY = velY;
    }

    // Getter for velX
    public int getVelX() {
        return velX;
    }

    // Getter for velY
    public int getVelY() {
        return velY;
    }



}
