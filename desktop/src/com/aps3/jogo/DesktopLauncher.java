package com.aps3.jogo;
import com.aps3.jogo.Telas.Menu;
import com.aps3.jogo.Telas.Play;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static DesktopLauncher desktop;;
	public static Lwjgl3ApplicationConfiguration config;
	public DesktopLauncher(){
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(600, 600);
		config.setResizable(true);
		config.setTitle("APS3-Jogo-LibGdx");
		new Lwjgl3Application(Jogo.getInstance(), config);
	}
	public static void main (String[] arg) {
		desktop = new DesktopLauncher();
	}
	public static void alterarDimensao(int largura,int altura){
		config.setWindowedMode(largura, altura);
	}
	public static DesktopLauncher getDesktop(){
		return desktop;
	}
}
