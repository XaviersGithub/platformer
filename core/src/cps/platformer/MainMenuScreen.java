package cps.platformer;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	final PlatformerMain game;

	OrthographicCamera camera;

	public MainMenuScreen(final PlatformerMain game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 256, 224);
	}

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);
		game.batch.begin();
		game.batch.draw(game.img, 0, 0);
		game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

}
