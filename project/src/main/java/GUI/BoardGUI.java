package GUI;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

public class BoardGUI {

	public JFrame frame;
	public JLabel messageLabel;
	HashMap<Coordinates,FieldGUI> hashMap;
	public int counter;

	public JButton skipButton;


	public BoardGUI() {
//		messageLabel.setBackground(Color.lightGray);
//		frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);
		counter = 0;

		messageLabel = new JLabel("...");
		skipButton =  new JButton("skip");
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

	public void setMessageLabel(String text) {
		messageLabel.setText(text);
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}

