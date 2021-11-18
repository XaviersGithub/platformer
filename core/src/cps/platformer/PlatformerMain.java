package cps.platformer;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.Templates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PlatformerMain extends Game {
	public SpriteBatch batch;
	Texture img;
	Texture bg;
	boolean grounded = false;
	Array<Blocks> blockarray = new Array<Blocks>();
	Playerobj player;
	private float gravity = 10;
	
	@Override
	public void create () {
		player = new Playerobj();
		player.width = 16;
		player.height = 32;
		batch = new SpriteBatch();
		for (int i = 0; i < 10; i++) {
			Blocks tempblocks = new Blocks();
			tempblocks.x = 0;
			tempblocks.y = 0;
			tempblocks.x = i*16;
			blockarray.add(tempblocks);
		}
		img = new Texture("player.png");
		bg = new Texture("bg.png");
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(bg, 0, 0);

		for (Blocks iter : blockarray) {
			batch.draw(iter.blockimage, iter.x, iter.y);
		}
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

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && grounded) {
			player.velocity.y =3;
		}
		else if (player.y <= 0) {
			player.velocity.y = 0;
			player.y = 0;
			grounded = true;
		}
		else {
			grounded = false;
		}

		for (Blocks iter : blockarray) {
			if (iter.overlaps(player)) {

				if (player.x < iter.x) {
					player.velocity.x =0;
				}
				else if (player.x > iter.x) {
					player.velocity.y =0;
					player.x = iter.x + 16;
				}
				if (player.y < iter.y) {
					player.velocity.y =0;
				}
				else if (player.y > iter.y) {
					player.velocity.y = 0;
					player.y = iter.y + iter.height;
				}
			}
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

