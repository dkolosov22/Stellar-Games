package oldcassonne;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.Random;

public class VariableContainer {
	// Other
	static Random rand = new Random();
	static int score = 0;
	static int scoreModifier = 5;

	// JFrame
	static JFrame frame;
	static int winWidth = 1280;
	static int winHeight = 720;

	// Refresh Rate
	static Timer timer;
	static int fps = 60;

	// Tile Informations
	static int tileID = 1111;
	static int tileTypes = 4;
	static int tileSize = 64;

	// Map stuff
	static int mapSize = 50;
	static int moveMod = 8;
	static int[][] map = new int[mapSize][mapSize];

	// Player Position on Map
	static int viewX = winWidth / 2;
	static int viewY = winHeight / 2;

	static int offsetX = (winWidth / 2) - (mapSize * (tileSize / 2));
	static int offsetY = (winHeight / 2) - (mapSize * (tileSize / 2));

	static int cursorX = 0;
	static int cursorY = 0;

}