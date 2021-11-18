package cps.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlatformerMain extends Game {
	public SpriteBatch batch;
	Texture img;
	Texture bg;
	Playerobj player;
	private float gravity = 10;
	
	@Override
	public void create () {
		player = new Playerobj();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		bg = new Texture("bg.png");
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.draw(img, player.x, player.y);
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.velocity.x = -2;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.velocity.x = 2;
		}
		else {
			player.velocity.x = 0;
		}


		player.velocity.y -= gravity * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.velocity.y =3;
		}
		else if (player.y <= 0) {
			player.velocity.y = 0;
		}
		player.x += player.velocity.x;
		player.y += player.velocity.y;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

