package cps.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Blocks extends Rectangle {

    Texture blockimage;

    Blocks () {
        blockimage = new Texture("block.png");
        this.width = 16;
        this.height = 16;
    }
}
