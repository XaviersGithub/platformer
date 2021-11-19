package cps.platformer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Block extends Rectangle {

    Texture blockimage;


    Block () {
        blockimage = new Texture("block.png");
        this.width = 16;
        this.height = 16;
    }


    Block (int xpos, int ypos) {
        blockimage = new Texture("block.png");
        this.width = 16;
        this.height = 16;
        this.x = xpos;
        this.y = ypos;
    }
}
