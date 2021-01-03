package GUI;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
/**
 * 
 * base class for board gui contains basic elements for board gui
 * @author dim
 *
 */
public class BoardGUI {

	public JFrame frame;
	public JLabel messageLabel;
	HashMap<Coordinates,FieldGUI> hashMap;
	//variable to know which operation is now performed
	public int counter;

	public JButton skipButton;

	/**
	 * constructor
	 */
	public BoardGUI() {

		counter = 0;

		messageLabel = new JLabel("...");
		skipButton =  new JButton("skip");
		frame = new JFrame("Chinese Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 700);
	    frame.setVisible(true);
	    frame.setResizable(true);
	}
	
	/**
	 * repaint frame
	 */
	public void re() {
		frame.repaint();
	}
	/**
	 * setting color of field
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param colorRe color to which field should change color
	 */
	public void setColorRe(int x, int y, Color colorRe) {
		hashMap.get(new Coordinates(x, y)).changeColor(colorRe);
	}
	
	/**
	 * setting label message
	 * @param text
	 */
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

