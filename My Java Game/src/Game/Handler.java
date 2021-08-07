package Game;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    // Linked List for object in game
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    // Tick method
    public void tick(){
        for (GameObject tempObject : object) {
            tempObject.tick();
        }
    }

    // Render method
    public void render(Graphics g){
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }

    // Clear Enemies method
    public void clearEnemies(){
        for (GameObject tempObject : object) {
            if(tempObject.getId() != ID.Player){
                object.clear();
            }
        }
    }

    // Add object method
    public void addObject(GameObject object){
        this.object.add(object);
    }

    // Remove object method
    public void removeObject(GameObject tempObject){
        this.object.remove(object);
    }

}
