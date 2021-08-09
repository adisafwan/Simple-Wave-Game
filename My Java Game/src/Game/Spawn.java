package Game;

import java.util.Random;

public class Spawn {

    private  Handler handler;
    private HUD hud;
    private Game game;
    private Random r = new Random();

    private int scoreDefault = 0;

    public Spawn(Handler handler, HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick(){
        scoreDefault++;

        if(game.difficulty == 0){
            if(scoreDefault >= 250){
                scoreDefault = 0;
                hud.setLevel(hud.getLevel() + 1);
                handler.addObject(new EasyEnemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.Enemy, handler));

                if(hud.getLevel() >= 5){
                    handler.addObject(new FastEasyEnemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.FastEnemy, handler));
                }
            }
        }else if(game.difficulty == 1){
            if(scoreDefault >= 250){
                scoreDefault = 0;
                hud.setLevel(hud.getLevel() + 1);
                handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.Enemy, handler));

                if(hud.getLevel() >= 5){
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.FastEnemy, handler));
                }
            }
        }else if(game.difficulty == 2){
            if(scoreDefault >= 250){
                scoreDefault = 0;
                hud.setLevel(hud.getLevel() + 1);
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.Enemy, handler));

                if(hud.getLevel() >= 5){
                    handler.addObject(new FastHardEnemy(r.nextInt(Game.WIDTH - 10), r.nextInt(Game.HEIGHT - 10), ID.FastEnemy, handler));
                }
            }
        }



    }

}
