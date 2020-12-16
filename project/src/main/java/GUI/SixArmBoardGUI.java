package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

import settings.SixArmBoard;

public class SixArmBoardGUI extends BoardGUI {

	
	JFrame frame;
	HashMap<Coordinates,FieldGUI> hashMap;
	private PrintWriter out;
	
	
	
	public SixArmBoardGUI(SixArmBoard board) {
		
		frame = new JFrame("Chinese Checkers");
		frame.getContentPane().setLayout(new GridLayout(board.getYSize(),1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 700);
	    frame.setVisible(true);
	    frame.setResizable(true);
		
		hashMap = new HashMap<Coordinates,FieldGUI>();
		
		for(int i=0;i<board.getYSize(); i++) {
			JPanel container = new JPanel();
			
			for(int j=0; j<board.getXSize(); j++) {
				if(board.getDimensions()[i][j] == 1) {
					
					final FieldGUI field = new FieldGUI();
					field.setPreferredSize(new Dimension(30,30));
					
					Coordinates coordinates = new Coordinates(i,j);
					hashMap.put(coordinates, field);
					
					
					field.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {
	
						 //TO DO define out
						 //out.println("MOVE " + getKeyByValue(hashMap, field).toString());
						System.out.println("MOVE " + getKeyByValue(hashMap, field).toString());
						out.println("MOVE " + 5);
						}

						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						public void mousePressed(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}});
					container.add(field);
				}
			}
			frame.getContentPane().add(container);
		
		}
		setUpBoardFor4Players();
	}

	public void setOut(PrintWriter out) {
		
		this.out = out;
	}
	
	public void setUpBoardFor2Players() {
		
		//blue player
		hashMap.get(new Coordinates(3,9)).changeColor(Color.blue);
		hashMap.get(new Coordinates(3,11)).changeColor(Color.blue);
		hashMap.get(new Coordinates(3,13)).changeColor(Color.blue);
		hashMap.get(new Coordinates(3,15)).changeColor(Color.blue);
		hashMap.get(new Coordinates(2,14)).changeColor(Color.blue);
		hashMap.get(new Coordinates(2,12)).changeColor(Color.blue);
		hashMap.get(new Coordinates(2,10)).changeColor(Color.blue);
		hashMap.get(new Coordinates(1,13)).changeColor(Color.blue);
		hashMap.get(new Coordinates(1,11)).changeColor(Color.blue);
		hashMap.get(new Coordinates(0,12)).changeColor(Color.blue);
		
		//Green player
		hashMap.get(new Coordinates(13,9)).changeColor(Color.green);
		hashMap.get(new Coordinates(13,11)).changeColor(Color.green);
		hashMap.get(new Coordinates(13,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(13,15)).changeColor(Color.green);
		hashMap.get(new Coordinates(14,14)).changeColor(Color.green);
		hashMap.get(new Coordinates(14,10)).changeColor(Color.green);
		hashMap.get(new Coordinates(14,12)).changeColor(Color.green);
		hashMap.get(new Coordinates(15,11)).changeColor(Color.green);
		hashMap.get(new Coordinates(15,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(16,12)).changeColor(Color.green);
	}
	
	public void setUpBoardFor4Players(){
		
		//yellow player
		hashMap.get(new Coordinates(4,0)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,2)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,6)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,5)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,1)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,3)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(6,2)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(6,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(7,3)).changeColor(Color.yellow);
		
		//red Player
		hashMap.get(new Coordinates(4,18)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,20)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,22)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,24)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,19)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,21)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,23)).changeColor(Color.red);
		hashMap.get(new Coordinates(6,20)).changeColor(Color.red);
		hashMap.get(new Coordinates(6,22)).changeColor(Color.red);
		hashMap.get(new Coordinates(7,21)).changeColor(Color.red);
		
		//magenta player
		hashMap.get(new Coordinates(12,18)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,20)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,22)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,24)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,19)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,21)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,23)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(10,20)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(10,22)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(9,21)).changeColor(Color.magenta);
		
		//cyan player
		
		hashMap.get(new Coordinates(9,3)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(10,4)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,5)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,6)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(10,2)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,3)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,4)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,1)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,2)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,0)).changeColor(Color.cyan);
	}
	
	public void setUpBoardFor6Players(){
		
		setUpBoardFor4Players();
		setUpBoardFor2Players();
	}
	
	//where should be this class located
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	
}