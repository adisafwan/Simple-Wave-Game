package Game;

import java.awt.*;

public class HUD {

    public static int HEALTH = 100;

    private int score = 0, level = 1;

    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0, 100);

        score++;
    }

    public void render(Graphics g){
        // Set the color of the health bar
        g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
        g.fillRect(15, 15, HEALTH * 2, 16);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 16);

        g.drawString("Score : " + score, 500, 25);
        g.drawString("Level : " + level, 500, 40);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}
