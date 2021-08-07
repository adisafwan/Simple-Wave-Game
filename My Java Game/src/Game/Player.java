package Game;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler= handler;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0 , Game.HEIGHT - 70);

        collision();

    }

    // Collision method = collide the player with the enemy causing HP drop
    private void collision(){
        for (int i = 0; i < handler.object.size() ; i++) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.FastEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    // Collision happen
                    HUD.HEALTH -= 1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        g.setColor(Color.black);
        graphics2D.drawOval(x, y, 32, 32);

        if(id == ID.Player)
            g.setColor(Color.blue);
        else if(id == ID.Player2)
            g.setColor(Color.white);

        g.fillOval(x, y, 32, 32);

    }



}
