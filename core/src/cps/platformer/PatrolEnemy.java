package cps.platformer;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PatrolEnemy extends Rectangle{
    public Vector2 velocity = new Vector2();
    public int dir = 1;
    PatrolEnemy () {
        this.x = 0;
        this.y = 0;
        velocity = new Vector2(1, 0);
        this.width = 16;
        this.height = 16;
    }

    PatrolEnemy (int xpos, int ypos) {
        this.x = xpos;
        this.y = ypos;
        this.width = 16;
        this.height = 16;
        velocity = new Vector2(1, 0);
    }



}