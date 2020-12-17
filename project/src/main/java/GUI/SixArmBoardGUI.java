package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import settings.SixArmBoard;

public class SixArmBoardGUI extends BoardGUI {

	
	//connection variable
	private PrintWriter out;
	String msg;
	int counter =0;
	public SixArmBoardGUI(SixArmBoard board, int numberOfPlayers, PrintWriter out) throws Exception {
		// information about games process
		messageLabel.setForeground(Color.BLACK);
		frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);


		frame.getContentPane().setLayout(new GridLayout(board.getYSize() + 1,1));
		hashMap = new HashMap<Coordinates,FieldGUI>();
		msg="";
		this.out = out;
		for(int i=0;i<board.getYSize(); i++) {
			JPanel container = new JPanel();
			
			for(int j=0; j<board.getXSize(); j++) {
				if(board.getDimensions()[i][j] == 1) {
					
					final FieldGUI field = new FieldGUI();
					field.setPreferredSize(new Dimension(30,30));
					
					Coordinates coordinates = new Coordinates(j,i);
					hashMap.put(coordinates, field);

					//not needed
//					final int xcor = i;
//					final int ycor = j;
				
					
					field.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {

							//out.println("MOVE " + getKeyByValue(hashMap, field).toString());
							//System.out.println("MOVE " + getKeyByValue(hashMap, field).toString());
//							out.println("MOVE " + xcor + " "+ ycor);
							
							//out.println("MOVE " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY());
							System.out.println("CLICKED " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY());
							
							
						
							if(counter == 0) {
								msg = "JUMP " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY();
								counter++;
							}
							else if(counter ==1) {
								
								msg = msg +" "+ getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY();
								out.println(msg);
								counter = 0;
							}
							
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
			frame.getContentPane().add(container, BorderLayout.CENTER);
		}
		
		switch (numberOfPlayers) {
		
		case 2:{
			
			setUpBoardFor2Players();
			break;
		}
		case 4:{
			
			setUpBoardFor4Players();
			break;
		}
		
		case 6:{
			
			setUpBoardFor6Players();
			break;
		}
		
		default: {
			
			System.out.println("Invalid input...");
            throw new IllegalArgumentException();
		}
		}
	}
	
	public void setUpBoardFor2Players() {

		//blue player
		hashMap.get(new Coordinates(9,3)).changeColor(Color.blue);
		hashMap.get(new Coordinates(11,3)).changeColor(Color.blue);
		hashMap.get(new Coordinates(13,3)).changeColor(Color.blue);
		hashMap.get(new Coordinates(15,3)).changeColor(Color.blue);
		hashMap.get(new Coordinates(14,2)).changeColor(Color.blue);
		hashMap.get(new Coordinates(12,2)).changeColor(Color.blue);
		hashMap.get(new Coordinates(10,2)).changeColor(Color.blue);
		hashMap.get(new Coordinates(13,1)).changeColor(Color.blue);
		hashMap.get(new Coordinates(11,1)).changeColor(Color.blue);
		hashMap.get(new Coordinates(12,0)).changeColor(Color.blue);

		//Green player
		hashMap.get(new Coordinates(9,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(11,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(13,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(15,13)).changeColor(Color.green);
		hashMap.get(new Coordinates(14,14)).changeColor(Color.green);
		hashMap.get(new Coordinates(10,14)).changeColor(Color.green);
		hashMap.get(new Coordinates(12,14)).changeColor(Color.green);
		hashMap.get(new Coordinates(11,15)).changeColor(Color.green);
		hashMap.get(new Coordinates(13,15)).changeColor(Color.green);
		hashMap.get(new Coordinates(12,16)).changeColor(Color.green);
	}

	public void setUpBoardFor4Players(){
		
		//yellow player
		hashMap.get(new Coordinates(0,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(2,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(6,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,5)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(1,5)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(3,5)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(2,6)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,6)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(3,7)).changeColor(Color.yellow);
		
		//red Player
		hashMap.get(new Coordinates(18,4)).changeColor(Color.red);
		hashMap.get(new Coordinates(20,4)).changeColor(Color.red);
		hashMap.get(new Coordinates(22,4)).changeColor(Color.red);
		hashMap.get(new Coordinates(24,4)).changeColor(Color.red);
		hashMap.get(new Coordinates(19,20)).changeColor(Color.red);
		hashMap.get(new Coordinates(21,5)).changeColor(Color.red);
		hashMap.get(new Coordinates(23,5)).changeColor(Color.red);
		hashMap.get(new Coordinates(20,6)).changeColor(Color.red);
		hashMap.get(new Coordinates(22,6)).changeColor(Color.red);
		hashMap.get(new Coordinates(21,7)).changeColor(Color.red);
		
		//magenta player
		hashMap.get(new Coordinates(18,12)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(20,12)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(22,12)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(24,12)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(19,11)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(21,11)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(23,11)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(20,10)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(22,10)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(21,9)).changeColor(Color.magenta);
		
		//cyan player
		
		hashMap.get(new Coordinates(3,9)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(4,10)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(5,11)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(6,12)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(2,10)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(3,11)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(4,11)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(1,11)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(2,12)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(0,12)).changeColor(Color.cyan);
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