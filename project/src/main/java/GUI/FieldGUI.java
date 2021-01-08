package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * class which defines field gui
 * @author dim
 *
 */
public class FieldGUI extends JPanel {
    
    Color currentColor;
    
    
    public FieldGUI() {
        
    	
    	setBackground(Color.WHITE);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void changeColor(Color color) {
    	
    	currentColor = color;
    	repaint();
    }
    public void paintComponent(Graphics g) {
    	
    	
        g.setColor(currentColor);
    	g.fillOval(0,0,this.getWidth(),this.getHeight());
    }
    
    
}