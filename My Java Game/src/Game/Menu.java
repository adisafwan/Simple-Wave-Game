package Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static Game.Game.HEIGHT;
import static Game.Game.WIDTH;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu){
            // This is for Play button
            if(mouseOver(mx, my, 240, 100, 150, 64) ){
                game.gameState = Game.STATE.Game;
                handler.clearEnemies();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
//            handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2))
                handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler));
            }

//            // This is for Exit Button
//            if(game.gameState == Game.STATE.Game){
//                if(mouseOver(mx, my, 50, 200, 90, 48)){
//                    game.gameState = Game.STATE.Menu;
//                    return;
//                }
//            }

            // This is for Help Button
            if(mouseOver(mx, my, 240, 200, 150, 64) ){
                game.gameState = Game.STATE.Help;
            }

            // This is for Quit button
            if(mouseOver(mx, my, 240, 300, 150, 64) ){
                System.exit(1);
            }
        }

        // This is for  Back Button in Help
        if(game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my, 50, 380, 90, 48)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        // This is for Retry button
        if(game.gameState == Game.STATE.End){
            if(mouseOver(mx, my, 205, 345, 90, 48 )){
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.clearEnemies();
                handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
//            handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2))
                handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler));
            }

            if(mouseOver(mx, my, 335, 345, 130, 48)){
                game.gameState = Game.STATE.Menu;
                for(int i = 0; i < 10; i++){
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                }
                return;
            }
        }

        // For Quit button in End
        if(game.gameState == Game.STATE.End){

        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        // to check mouse not over the border of the box of the button
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font font = new Font("inlanders", Font.BOLD, 25);
            g.setFont(font);

            g.setColor(Color.BLUE);
            g.drawString("Mathematics Game!", 200, 75);

            Font font2 = new Font("Verdana", Font.BOLD, 13);
            g.setFont(font2);

            g.setColor(Color.BLACK);
            g.drawRect(240, 100, 150, 64);
            g.drawString("Play", 300, 135);

            g.setColor(Color.BLACK);
            g.drawRect(240, 200, 150, 64);
            g.drawString("Help", 300, 235);

            g.setColor(Color.BLACK);
            g.drawRect(240, 300, 150, 64);
            g.drawString("Quit", 300, 335);
        } else if (game.gameState == Game.STATE.Game) {

//            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
////            handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2))
//            handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler));

            Font font4 = new Font("Verdana", Font.PLAIN, 15);
            g.setFont(font4);
            g.setColor(Color.BLACK);
            g.drawRect(50, 200, 90, 48);
            g.drawString("Exit", 80, 230);

        } else if(game.gameState == Game.STATE.Help){
            Font font3 = new Font("Verdana", Font.BOLD, 25);
            g.setFont(font3);
            g.setColor(Color.BLACK);
            g.drawString("Help", 300, 75);

            Font font4 = new Font("Verdana", Font.BOLD, 15);
            g.setFont(font4);
            g.drawString("Use WASD button to move player and dodge enemy", 150, 125);

            Font font5 = new Font("Verdana", Font.PLAIN, 15);
            g.setFont(font5);
            g.drawRect(50, 380, 90, 48);
            g.drawString("Back", 80, 410);
        } else if(game.gameState == Game.STATE.End){
            Font font3 = new Font("Verdana", Font.BOLD, 35);
            g.setFont(font3);
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 180, 75);

            Font font4 = new Font("Verdana", Font.BOLD, 15);
            g.setFont(font4);
            g.drawString("Try Again Next Time", 195, 175);
            g.drawString("Your score : " + hud.getScore(), 195, 225);
            g.drawString("Level : " + hud.getLevel(), 195, 275);

            g.setColor(Color.BLACK);
            g.drawRect(205, 345, 90, 48);
            g.drawRect(335, 345, 130, 48);
            g.setColor(Color.RED);
            g.drawString("Retry", 225, 375);
            g.drawString("Back to Menu", 345, 375);
        }


    }

}
