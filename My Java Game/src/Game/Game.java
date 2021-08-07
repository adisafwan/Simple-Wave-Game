package Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    // SerialVersionUID for Game
    private static final long serialVersionUID = 155069097823471818L;

    // Variable Declaration
    public static final int WIDTH = 640, HEIGHT = 480;
    private Thread thread;
    private boolean running = true;
    private Handler handler;
    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE{
        Menu,
        Help,
        Game,
        End
    };

    public STATE gameState = STATE.Menu;

    // Game constructor
    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler , hud);

        // get key from user
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Windows(WIDTH, HEIGHT, "Mathematics Game", this);

        spawner = new Spawn(handler, hud);
        r = new Random();

        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
//            handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2))
            handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler));

        }else{
            for(int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }

    }

    // Start method to start the game
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    // Stop method to stop the game
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Run method to run the game
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime(); // get current time to the nanosecond
        double amountOfTicks = 60.0; // set the number of ticks
        double ns = 1000000000 / amountOfTicks; // this determines how many times we can devide 60 into 1e9 of nano seconds or about 1 second
        double delta = 0; // change in time (delta always means a change like delta v is change in velocity)
        long timer = System.currentTimeMillis(); // get current time
        int frames = 0; // set frame variable
        while(running){
            long now = System.nanoTime(); // get current time in nonoseconds durring current loop
            delta += (now - lastTime) / ns; // add the amount of change since the last loop
            lastTime = now;  // set lastTime to now to prepare for next loop
            while(delta >= 1){
                // one tick of time has passed in the game this
                //ensures that we have a steady pause in our game loop
                //so we don't have a game that runs way too fast
                //basically we are waiting for  enough time to pass so we
                // have about 60 frames per one second interval determined to the nanosecond (accuracy)
                // once this pause is done we render the next frame
                tick();
                delta--;  // lower our delta back to 0 to start our next frame wait
            }
            if(running){
                render(); // render the visuals of the game
            }
            frames++; // note that a frame has passed
            if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
                timer+= 1000; // add a thousand to our timer for next time
//                System.out.println("FPS: " + frames); // print out how many frames have happend in the last second
                frames = 0; // reset the frame count for the next second
            }
        }
        stop(); // no longer running stop the thread
    }

    // Tick method
    private void tick(){
        handler.tick();

        if (gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                HUD.HEALTH = 100;
                handler.clearEnemies();
                gameState = STATE.End;
            }

        }else if (gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        }

    }

    // Render method - to render the game layout
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.PINK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.Game){
            hud.render(g);
        }else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static int clamp(int val, int min, int max){
        if(val >= max)
            return val = max;

        else if (val <= min)
            return val = min;
        else
            return val;
    }

    // Main method
    public static void main(String[] args) {
        new Game();
    }
}
