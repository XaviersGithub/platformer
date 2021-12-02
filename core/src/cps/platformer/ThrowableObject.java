package cps.platformer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ThrowableObject extends Rectangle{

    Vector2 Velocity = new Vector2();
    ThrowableObject (int posx, int posy, int widthx, int heighty) {
        this.x = posx;
        this.y = posy;

        this.height = heighty;
        this.width = widthx;
    }
}
