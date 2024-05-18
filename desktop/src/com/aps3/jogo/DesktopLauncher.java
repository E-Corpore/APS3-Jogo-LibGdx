package com.aps3.jogo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(1024, 1024);
		config.setResizable(true);
		config.setTitle("APS3-Jogo-LibGdx");
		new Lwjgl3Application(new Jogo(), config);
	}
	public void entrouMenu(){

	}
}
