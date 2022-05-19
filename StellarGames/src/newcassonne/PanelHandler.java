package newcassonne;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Processes the panel and does calculations for the game.
 * 
 * @author Lisa
 *
 */

public class PanelHandler extends JPanel {
	private static final long serialVersionUID = 1L;
	GameDisplay gDisplay = new GameDisplay();
	JLabel score = new JLabel("Score: 0");

	/**
	 * Sets the Windows dimensions. The Window is not resizable.
	 */
	static final int SCREEN_WIDTH = 1240;
	static final int SCREEN_HEIGHT = 720;

	static List<TileSet> tileMap = new ArrayList<TileSet>();

	static Vector2D positionOnMap = new Vector2D();
	static Vector2D positionOfCursor = new Vector2D();

	static int cursorTileID;
	static int scoreValue;

	Random random;

	/**
	 * Creates the frame.
	 */
	PanelHandler() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		showMenu();
	}

	public void showMenu() {
		JLabel header = new JLabel("Carcassonne");
		JButton play = new JButton("PLAY GAME");
		JButton help = new JButton("HELP");
		JButton exit = new JButton("EXIT GAME");

		this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		this.setLayout(new GridLayout(0, 1));

		header.setHorizontalAlignment(JLabel.CENTER);
		header.setFont(new Font("Germania One", Font.BOLD, 64));
		header.setForeground(Color.WHITE);

		this.add(header);
		this.add(play);
		this.add(help);
		this.add(exit);

		play.addActionListener(e -> {
			this.removeAll();
			this.invalidate();
			this.validate();
			startGame();
		});
		help.addActionListener(e -> {
			this.removeAll();
			this.invalidate();
			this.validate();
			showHelp();
		});
		exit.addActionListener(e -> {
			System.exit(0);
		});
	}

	/**
	 * This is for showing the player the rules of the game.
	 */
	public void showHelp() {
		JLabel header = new JLabel("Carcassonne");
		JLabel info = new JLabel("Filler Buster");
		JButton back = new JButton("GO BACK");

		this.setBorder(BorderFactory.createEmptyBorder(20, 100, 100, 100));
		this.setLayout(new GridLayout(0, 1));

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("You start off with a single grass tile.");
		sb.append("<br>");
		sb.append("Each time you place a tile, another random tile will appear in its place.");
		sb.append("<br>");
		sb.append("Over time, you will create a unique world, spaning over hundreds of tiles");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("Controls:");
		sb.append("<br>");
		sb.append("LMB to place tiles.");
		sb.append("<br>");
		sb.append("R to rotate tiles.");
		sb.append("<br>");
		sb.append("WASD to move around the map.");
		sb.append("<br>");
		sb.append("<br>");
		sb.append("The rules are as follows:");
		sb.append("<br>");
		sb.append("1. Tiles must be placed next to each other");
		sb.append("<br>");
		sb.append("2. Place tiles with similar terrain to gain points");
		sb.append("<br>");
		sb.append("3. Have fun!");
		sb.append("<br>");
		sb.append("</html>");

		header.setFont(new Font("Germania One", Font.BOLD, 64));
		header.setForeground(Color.WHITE);

		info.setText(sb.toString());
		info.setForeground(Color.WHITE);

		this.add(header);
		this.add(info);
		this.add(back);

		back.addActionListener(e -> {
			this.removeAll();
			this.invalidate();
			this.validate();
			showMenu();
		});
	}

	/**
	 * Method for starting the Game.
	 * 
	 * @param back Creates the button "Go Back" so the player can return to the main
	 *             menu.
	 */
	public void startGame() {
		JButton back = new JButton("GO BACK");

		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setLayout(null);

		score.setFont(new Font("Germania One", Font.BOLD, 24));
		score.setForeground(Color.WHITE);
		score.setBounds(20, 20, 500, 20);

		back.setBounds(1100, 20, 120, 40);

		gDisplay.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		gDisplay.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		gDisplay.setVisible(true);

		this.add(score, BorderLayout.WEST);
		this.add(back, BorderLayout.EAST);
		this.add(gDisplay);

		gDisplay.repaint();

		scoreValue = 0;
		cursorTileID = (random.nextInt(4) * 1000) + // North
				(random.nextInt(4) * 100) + // East
				(random.nextInt(4) * 10) + // South
				random.nextInt(4); // West
		tileMap.add(new TileSet(0, 0, 0));
		positionOnMap = new Vector2D((SCREEN_WIDTH - 64) / 2, (SCREEN_HEIGHT - 64) / 2);

		gDisplay.setCursorTile(cursorTileID);
		gDisplay.setTileMap(tileMap);
		gDisplay.setPositionOnMap(positionOnMap);

		this.addKeyListener(new EventHandler());
		this.addMouseListener(new MouseEventHandler());
		this.addMouseMotionListener(new MouseEventHandler());
		back.addActionListener(e -> {
			this.removeAll();
			tileMap.clear();
			this.invalidate();
			this.validate();
			showMenu();
		});

	}

	/**
	 * This is for moving the map if the space for the placed tiles is getting very
	 * small.
	 * 
	 * @author Lisa
	 *
	 */
	public class EventHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT: {
				// Move left
				positionOnMap.x += 8;
				break;
			}
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN: {
				// Move down
				positionOnMap.y -= 8;
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT: {
				// Move right
				positionOnMap.x -= 8;
				break;
			}
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP: {
				// Move up
				positionOnMap.y += 8;
				break;
			}
			case KeyEvent.VK_R: {
				// Rotate
				cursorTileID = ((cursorTileID / 10) + ((cursorTileID % 10) * 1000));
				gDisplay.setCursorTile(cursorTileID);
				break;
			}
			}
			repaint();
		}
	}

	/**
	 * This is for placing the tiles on the map via mouse click.
	 * 
	 * @author Lisa
	 *
	 */
	public class MouseEventHandler implements MouseListener, MouseMotionListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int x = ((positionOfCursor.x - positionOnMap.x) + (positionOnMap.x % 64)) / 64;
				int y = ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64)) / 64;

				if ((positionOfCursor.x - positionOnMap.x) + (positionOnMap.x % 64) < 0) {
					x--;
				}
				if ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64) < 0) {
					y--;
				}
				// Place Tile
				boolean free = true;
				boolean adjacent = false;
				for (int i = 0; i < tileMap.size(); i++) {
					if (x == tileMap.get(i).getX() && y == tileMap.get(i).getY()) {
						free = false;
						break;
					}
					if (x == tileMap.get(i).getX() && y == tileMap.get(i).getY() + 1
							|| x == tileMap.get(i).getX() && y == tileMap.get(i).getY() - 1
							|| x == tileMap.get(i).getX() + 1 && y == tileMap.get(i).getY()
							|| x == tileMap.get(i).getX() - 1 && y == tileMap.get(i).getY()) {

						if (x == tileMap.get(i).getX() && y == tileMap.get(i).getY() + 1
								&& cursorTileID / 1000 == (tileMap.get(i).getId() / 10) % 10) {
							scoreValue += 5;
						}
						if (x == tileMap.get(i).getX() && y == tileMap.get(i).getY() - 1
								&& (cursorTileID / 10) % 10 == tileMap.get(i).getId() / 1000) {
							scoreValue += 5;
						}
						if (x == tileMap.get(i).getX() + 1 && y == tileMap.get(i).getY()
								&& cursorTileID % 10 == (tileMap.get(i).getId() / 100) % 10) {
							scoreValue += 5;
						}
						if (x == tileMap.get(i).getX() - 1 && y == tileMap.get(i).getY()
								&& (cursorTileID / 100) % 10 == tileMap.get(i).getId() % 10) {
							scoreValue += 5;

						}
						adjacent = true;
					}
				}

				if (free == true && adjacent == true) {
					tileMap.add(new TileSet(cursorTileID, x, y));

					cursorTileID = (random.nextInt(4) * 1000) + // North
							(random.nextInt(4) * 100) + // East
							(random.nextInt(4) * 10) + // South
							random.nextInt(4); // West

					score.setText("Score: " + scoreValue);
					gDisplay.setCursorTile(cursorTileID);
					gDisplay.setTileMap(tileMap);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		/**
		 * Method to refresh the Frame whenever the mouse is hovering over a new tile.
		 */

		@Override
		public void mouseMoved(MouseEvent e) {
			positionOfCursor.x = e.getPoint().x - (positionOnMap.x % 64);
			positionOfCursor.y = e.getPoint().y - (positionOnMap.y % 64);

			Vector2D oldPosition = new Vector2D(0, 0);
			gDisplay.setPositionOfCursor(positionOfCursor);

			// if mouse is on new tile -> refresh
			if ((((positionOfCursor.x - positionOnMap.x) + (positionOnMap.x % 64)) / 64)
					- ((positionOfCursor.x - positionOnMap.x) + (positionOnMap.x % 64) < 0 ? 1 : 0) != oldPosition.x
					|| (((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64)) / 64)
							- ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64) < 0 ? 1
									: 0) != oldPosition.y
					|| ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64)) / 64 == 0
							&& ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64)) / 64 == 0) {
				oldPosition.x = ((positionOfCursor.x - positionOnMap.x) + (positionOnMap.x % 64)) / 64;
				oldPosition.y = ((positionOfCursor.y - positionOnMap.y) + (positionOnMap.y % 64)) / 64;
				repaint();
			}
		}
	}
}
