package cps.platformer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cps.platformer.PlatformerMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "The Strange-Former";
        config.width = 256;
        config.height = 224;
		new LwjglApplication(new PlatformerMain(), config);
	}
}
