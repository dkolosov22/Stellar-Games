package oldcassonne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class KeyHandler implements KeyListener, MouseInputListener, ActionListener {

	@Override
	public void keyTyped(KeyEvent e) {
		//

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Move map with WASD
		VariableContainer.offsetX += (VariableContainer.moveMod * (e.getKeyCode() == 'A' ? 1 : 0)) - (VariableContainer.moveMod * (e.getKeyCode() == 'D' ? 1 : 0));
		VariableContainer.offsetY += (VariableContainer.moveMod * (e.getKeyCode() == 'W' ? 1 : 0)) - (VariableContainer.moveMod * (e.getKeyCode() == 'S' ? 1 : 0));

		// Rotate tile with R
		VariableContainer.tileID = ((VariableContainer.tileID / 10) + ((VariableContainer.tileID % 10) * 1000)) * (e.getKeyCode() == 'R' ? 1 : 0)
				+ (VariableContainer.tileID * (e.getKeyCode() == 'R' ? 0 : 1));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Place tile LMB
		if (e.getButton() == MouseEvent.BUTTON1) {

			int x = ((VariableContainer.cursorX - VariableContainer.offsetX) + (VariableContainer.offsetX % VariableContainer.tileSize)) / VariableContainer.tileSize;
			int y = ((VariableContainer.cursorY - VariableContainer.offsetY) + (VariableContainer.offsetY % VariableContainer.tileSize)) / VariableContainer.tileSize;

			if (x >= 0 && x < VariableContainer.mapSize && y >= 0 && y < VariableContainer.mapSize) {
				// Place Tile
				int placed = (VariableContainer.map[x][y] < 1 && (
						VariableContainer.map[x-1][y] != 0 || 
						VariableContainer.map[x][y-1] != 0 || 
						VariableContainer.map[x][y+1] != 0 ||
						VariableContainer.map[x+1][y] != 0)) ? 1 : 0;
				VariableContainer.map[x][y] = placed == 1 ? VariableContainer.tileID : VariableContainer.map[x][y];

				// Check for neighbours here NESW
				if (placed == 1) {
					VariableContainer.score += ((y > 0 ? ((VariableContainer.map[x][y] / 1000) == ((VariableContainer.map[x][y - 1] / 10) % 10) ? 1 : 0) : 0)
							+ (x < VariableContainer.mapSize - 1 ? (((VariableContainer.map[x][y] / 100) % 10) == (VariableContainer.map[x + 1][y] % 10) ? 1 : 0)
									: 0)
							+ (y < VariableContainer.mapSize - 1 ? (((VariableContainer.map[x][y] / 10) % 10) == (VariableContainer.map[x][y + 1] / 1000) ? 1 : 0)
									: 0)
							+ (x > 0 ? ((VariableContainer.map[x][y] % 10) == ((VariableContainer.map[x - 1][y] / 100) % 10) ? 1 : 0) : 0))
							* VariableContainer.scoreModifier;
				}

				// Assign new tile to mouse
				if (placed == 1) {
					VariableContainer.tileID = ((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 1000) + // North
							((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 100) + // East
							((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 10) + // South
							(VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1); // West
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//

	}

}