package cps.platformer;

import java.time.Year;

import javax.lang.model.util.ElementScanner6;
import javax.print.attribute.standard.MediaSize.Other;
import javax.xml.transform.Templates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class PlatformerMain extends Game {
	public SpriteBatch batch;
	Texture img;
	Texture bg;
	boolean grounded = false;
	Array<Block> blockarray = new Array<Block>();
	Playerobj player;
	private float gravity = 10;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 256, 224);
		player = new Playerobj();
		player.width = 16;
		player.height = 32;
		player.y = 32;
		batch = new SpriteBatch();
		for (int i = 0; i < 10; i++) {
			Block tempblocks = new Block();
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
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//
		batch.draw(bg, 0, 0);

		



		for (Block iter : blockarray) {
			batch.draw(iter.blockimage, iter.x, iter.y);
		}
		batch.draw(img, player.x, player.y);
		batch.end();



		// BLOCK CREATION FOR DEBUG TESTING AND MAP CREATION
		if (Gdx.input.justTouched()) {
			Vector3 touch = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Block testblock = new Block(16*((int)(touch.x /16)), 16*((int)(touch.y /16)));
			blockarray.add(testblock);
			
		}


		//INPUT
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
		player.y += player.velocity.y;
		player.x += player.velocity.x * Gdx.graphics.getDeltaTime() *50;
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && grounded) {
			player.velocity.y =3;
		}
		else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			System.out.println(grounded);
		}



		if (grounded && player.velocity.y <0) 
		player.velocity.y = 0;

		boolean tempgrounded = false;
		for (Block iter : blockarray) {
			if (iter.overlaps(player)) {
				if (player.y < iter.y -16 && (player.x + 16 > iter.x && player.x < iter.x + 16)) {
					player.velocity.y =0;
					player.y = iter.y - player.height;
				}
				if (player.y > iter.y && (player.x + 16 > iter.x && player.x < iter.x + 16)) {
					tempgrounded = true;
					player.y = iter.y + iter.height;
				}
				if (player.y < iter.y -16 && !(player.x + 16 > iter.x && player.x < iter.x + 16)) {
					
				}
				if (player.y > iter.y && !(player.x + 16 > iter.x && player.x < iter.x + 16)) {

				}
				if (player.x < iter.x && !(player.y < iter.y -16 || player.y > iter.y)) {
					player.velocity.x =0;
					player.x = iter.x-16;
				}
				if (player.x > iter.x && !(player.y < iter.y -16|| player.y > iter.y)) {
					player.velocity.y =0;
					player.x = iter.x + 16;
				}

			}
		}
		grounded = tempgrounded;


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

