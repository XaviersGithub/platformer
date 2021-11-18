package cps.platformer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Playerobj extends Rectangle {
    public Vector2 velocity;

    Playerobj () {
        velocity = new Vector2();
        this.height= 32;
        this.width = 16;
    }
}
