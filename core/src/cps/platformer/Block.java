package cps.platformer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Block extends Rectangle {

    Texture blockimage;
    int curnum = 0;
    int textureindex = 0;
    boolean consumnable = false;


    Block () {
        //blockimage = new Texture("block.png");
        blockimage = new Texture("structurebeam.png");
        this.width = 16;
        this.height = 16;
    }


    Block (int xpos, int ypos) {
        //blockimage = new Texture("block.png");
        blockimage = new Texture("structurebeam.png");
        this.width = 16;
        this.height = 16;
        this.x = xpos;
        this.y = ypos;
    }

    Block(int xpos, int ypos, Texture temptex) {
        blockimage = temptex;
        this.width = 16;
        this.height = 16;
        this.x = xpos;
        this.y = ypos;
    }
}
