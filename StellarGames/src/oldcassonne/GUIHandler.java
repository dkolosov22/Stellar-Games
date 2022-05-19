package oldcassonne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GUIHandler {

	public GUIHandler() {
		VariableContainer.frame = new JFrame();
		VariableContainer.frame.setSize(VariableContainer.winWidth, VariableContainer.winHeight);
		VariableContainer.frame.setTitle("Carcassonne");
		VariableContainer.frame.setLayout(null);
		VariableContainer.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VariableContainer.frame.setResizable(false);
		VariableContainer.frame.setVisible(true);
		VariableContainer.frame.setLocationRelativeTo(null);
		VariableContainer.frame.addKeyListener(new KeyHandler());
		VariableContainer.frame.addMouseListener(new KeyHandler());
		VariableContainer.frame.requestFocus();
		
		Draw draw = new Draw();
		draw.setBounds(0, 0, VariableContainer.winWidth, VariableContainer.winHeight);
		draw.setVisible(true);
		VariableContainer.frame.add(draw);

		VariableContainer.timer = new Timer(1000/VariableContainer.fps, new ActionListener() {
		    public void actionPerformed(ActionEvent event) 
		    {
		      draw.repaint();
		    }
		});
	}
}