package cps.platformer;


import java.io.File;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PatrolEnemy extends Rectangle{
    public Vector2 velocity = new Vector2();
    Vector2 spawnloc = new Vector2();
    public int dir = 1;
    TextureRegion thistext = new TextureRegion(new Texture("PIGGOOMBA.png"));
    PatrolEnemy () {
        this.x = 0;
        this.y = 0;
        velocity = new Vector2(1, 0);
        this.width = 16;
        this.height = 16;
    }

    PatrolEnemy (int xpos, int ypos) {
        spawnloc = new Vector2(xpos, ypos);
        this.x = xpos;
        this.y = ypos;
        this.width = 16;
        this.height = 16;
        velocity = new Vector2(1, 0);
    }



}