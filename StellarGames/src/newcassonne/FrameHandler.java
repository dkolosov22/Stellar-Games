package newcassonne;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Creates the main frame for the game.
 * @author Noricia
 *
 */

public class FrameHandler extends JFrame {
	private static final long serialVersionUID = 1L;

	FrameHandler(){

		this.add(new PanelHandler(), BorderLayout.CENTER);
		this.setTitle("Carcassonne");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}