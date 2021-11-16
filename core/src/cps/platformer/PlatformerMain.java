package cps.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlatformerMain extends Game {
	public SpriteBatch batch;
	Texture img;
	Rectangle mario;
	@Override
	public void create () {
		mario = new Rectangle();
		batch = new SpriteBatch();
		img = new Texture("bg.png");
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
