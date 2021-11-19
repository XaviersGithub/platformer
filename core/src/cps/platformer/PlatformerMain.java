package cps.platformer;

import java.time.Year;
import java.time.chrono.IsoEra;

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
import com.badlogic.gdx.math.Matrix4;
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
		player.y = 64;
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

		
		//camera.position.set(new Vector3(player.x, 224/2,0));
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

		player.velocity.y -= gravity * Gdx.graphics.getDeltaTime();
		player.y += player.velocity.y;
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
				if (player.y > iter.y) {

					player.y = iter.y + iter.height;
					tempgrounded = true;
				}
			}
		}


		for (Block iter : blockarray) {
			if (iter.overlaps(player)) {
				if (player.y < iter.y) {
					player.y = iter.y - player.height;
					player.velocity.y = 0;
				}
			}
		}	

		grounded = tempgrounded;
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x -= 50* Gdx.graphics.getDeltaTime();
			for (Block iter : blockarray) {
				if (iter.overlaps(player)) {
					if (player.x  < iter.x + iter.width) {
						player.x = iter.x + player.width;
					}
				}
			}

		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			
			player.x += 50* Gdx.graphics.getDeltaTime();
			for (Block iter : blockarray) {
				if (iter.overlaps(player)) {
					if (player.x + player.width > iter.x) {
						
						player.x = iter.x - player.width;
					}
				}
			}
		}


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

