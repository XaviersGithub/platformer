package cps.platformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Year;
import java.time.chrono.IsoEra;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;
import javax.naming.directory.DirContext;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.plaf.metal.MetalBorders.PaletteBorder;
import javax.xml.transform.Templates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.SpawnInfluencer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class PlatformerMain extends Game {
	public SpriteBatch batch;
	Texture img;
	Texture currenttex;
	Texture bg;
	private boolean playerdir =false;
	int regnum =1 ;
	boolean grounded = false;
	Array<Block> blockarray = new Array<Block>();
	Playerobj player;
	private float gravity = 10;
	Sound nom1;
	Sound nom2;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		nom1 = Gdx.audio.newSound(Gdx.files.internal("consume1.wav"));
		nom2 = Gdx.audio.newSound(Gdx.files.internal("consume2.wav"));
		camera = new OrthographicCamera();
		currenttex = new Texture("structurebeam.png");
		camera.setToOrtho(false, 256, 224);
		player = new Playerobj();
		player.width = 16;
		player.height = 32;
		player.y = 64;
		batch = new SpriteBatch();
		for (int i = 0; i < 100d; i++) {
			Block tempblocks = new Block();
			tempblocks.x = 0;
			tempblocks.y = 0;
			tempblocks.x = i*16;
			blockarray.add(tempblocks);
		}
		img = new Texture("newplayer.png");
		bg = new Texture("bg.png");
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		if (player.velocity.y < 0) {
			if (!grounded) {
				player.falling();
			}
		}
		float SprintModifier = (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) ? 1.5f : 1f;
		if (player.x < camera.position.x - camera.viewportWidth/2) {
			player.x = camera.position.x - camera.viewportWidth/2;
		}
		if (player.x > camera.position.x ) {
			camera.position.set(new Vector3(player.x, 224/2,0));
		}
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//
		batch.draw(bg, camera.position.x - camera.viewportWidth/2, 0);

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			if (regnum > 10) {
				regnum = 1;
			}
			if (regnum <= 0) {
				regnum = 10;
			}
			else {
				regnum++;
			}
		}

		switch (regnum) {
			case 1:
				currenttex = new Texture("block.png");
				break;
			case 2:
				currenttex = new Texture("cementtop.png");
				break;
			case 3:
				currenttex = new Texture("chicken.png");
				break;
			default:
				currenttex = new Texture("structurebeam.png");
				break;
		}



		for (Block iter : blockarray) {
			batch.draw(iter.blockimage, iter.x, iter.y);
		}

		TextureRegion playertextureregion = new TextureRegion();
		playertextureregion.setRegion(player.currenttext);
		playertextureregion.flip(playerdir, false);
		batch.draw(playertextureregion, player.x, player.y);
		batch.end();



		// BLOCK CREATION FOR DEBUG TESTING AND MAP CREATION
		if (Gdx.input.justTouched()) {
			Vector3 touch = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Block testblock = new Block(16*((int)(touch.x /16)), 16*((int)(touch.y /16)), currenttex);
			testblock.consumnable = false;
			blockarray.add(testblock);
			
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
			Vector3 touch = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Block testblock = new Block(16*((int)(touch.x /16)), 16*((int)(touch.y /16)), new Texture("chicken.png"));
			testblock.consumnable = true;
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
			if (iter.overlaps(player) && !iter.consumnable) {
				if (player.y > iter.y) {

					player.y = iter.y + iter.height;
					tempgrounded = true;
				}
			}
			else if (iter.overlaps(player) && iter.consumnable) {
				blockarray.removeValue(iter, true);
				consumenoise();

			}
		}


		for (Block iter : blockarray) {
			if (iter.overlaps(player) && !iter.consumnable) {
				if (player.y < iter.y) {
					player.y = iter.y - player.height;
					player.velocity.y = 0;
				}
			}
			else if (iter.overlaps(player) && iter.consumnable) {
				blockarray.removeValue(iter, true);
				consumenoise();
			}
		}	

		grounded = tempgrounded;
		
		if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.idling();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			playerdir = true;
			player.walk();
			player.x -= 50* Gdx.graphics.getDeltaTime() * SprintModifier;
			for (Block iter : blockarray) {
				if (iter.overlaps(player) && !iter.consumnable) {
					if (player.x  < iter.x + iter.width) {
						player.x = iter.x + player.width;
					}
				}
				else if (iter.overlaps(player) && iter.consumnable) {
					blockarray.removeValue(iter, true);
					consumenoise();
				}
			}

		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {

			playerdir = false;
			player.walk();
			player.x += 50* Gdx.graphics.getDeltaTime() * SprintModifier;
			for (Block iter : blockarray) {
				if (iter.overlaps(player) && !iter.consumnable) {
					if (player.x + player.width > iter.x) {
						
						player.x = iter.x - player.width;
					}
					
				}
				else if (iter.overlaps(player) && iter.consumnable) {
					blockarray.removeValue(iter, true);
					consumenoise();
				}
			}
		}


	}
	

	void consumenoise () {
		Random random = new Random();
		if (random.nextInt(2) == 1) {
			nom1.play();
		}
		else {
			nom2.play();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		nom1.dispose();
		nom2.dispose();
	}
}

