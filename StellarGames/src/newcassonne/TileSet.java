package newcassonne;

/**
 * Saves the relevant values for a Tile field like its position and ID.
 * @author Lisa
 * @param id To differentiate between the different tiles.
 * @param x The x-coordinates of the Tile.
 * @param y The y-coordinates of the Tile.
 */

public class TileSet {
	private int id;
	private int x;
	private int y;
	
	public TileSet(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
}
