package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;

public class BoardGUI {

	public JFrame frame;
	HashMap<Coordinates,FieldGUI> hashMap;
	
	public BoardGUI() {
		
		
		frame = new JFrame("Chinese Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 700);
	    frame.setVisible(true);
	    frame.setResizable(true);
	}
	
	
	public void re() {
		frame.repaint();
	}

	public void setColorRe(int x, int y, Color colorRe) {
		hashMap.get(new Coordinates(x, y)).changeColor(colorRe);
	}

}

