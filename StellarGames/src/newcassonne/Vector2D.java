package newcassonne;

/**
 * Saves two values for the mouse position and the map position.
 * @author Lisa
 * @param x The x-coordinates of the mouse.
 * @param y The y-coordinates of the mouse.
 */

public class Vector2D {
	public int x;
	public int y;
	
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
