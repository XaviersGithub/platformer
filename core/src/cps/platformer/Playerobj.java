package cps.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Playerobj extends Rectangle {
    public Vector2 velocity;

    public Array <Texture> textures;

    Playerobj () {
        textures  = new Array <Texture>();
        textures.add(new Texture("idle.png"));
        textures.add(new Texture("newplayer.png"));
        textures.add(new Texture("walk2.png"));
        velocity = new Vector2();
        this.height= 32;
        this.width = 16;
    }
}
