package cps.platformer;

import java.sql.Time;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import org.w3c.dom.Text;

public class Playerobj extends Rectangle {
    public Vector2 velocity;
    boolean dead = false;

    public Array <Texture> textures;
    public Texture currenttext;

    Playerobj () {
        textures  = new Array <Texture>();
        textures.add(new Texture("newidle.png"));
        textures.add(new Texture("armwalk1.png"));
        textures.add(new Texture("armwalk2.png"));
        textures.add(new Texture("fallanim.png"));

        currenttext = textures.get(0);
        velocity = new Vector2();
        this.height= 32;
        this.width = 16;
    }

    void walk () {
        //currenttext =  textures.get(Math.abs(((int) (TimeUtils.timeSinceMillis(0) /200) % 3)));
        currenttext =  textures.get(Math.abs(((int) (TimeUtils.timeSinceMillis(0) /((int) (200*((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) ? 0.80f : 1f)))) % 3)));
    }

    void idling() {
        currenttext = textures.get(0);
    }

    void falling () {
        currenttext = textures.get(3);
    }
}
