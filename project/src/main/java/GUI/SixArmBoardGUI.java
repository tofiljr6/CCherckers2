package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.*;

import settings.SixArmBoard;
/**
 * 
 * 
 * @author dim
 *
 */
public class SixArmBoardGUI extends BoardGUI {

	
	//connection variable
	private PrintWriter out;
	//message to be sent
	String msg;
	//class which prepare gui board
	private SixArmBoardGUIPreparer preparer;
	
	public SixArmBoardGUI(SixArmBoard board, int numberOfPlayers, PrintWriter out) throws Exception {
		
		preparer = new SixArmBoardGUIPreparer();
		// information about games process
		messageLabel.setForeground(Color.BLACK);
		frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);

		//action listener to skip button
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("SKIP");
				out.println("SKIP kurde no");
			}
		});
		frame.getContentPane().add(skipButton, BorderLayout.NORTH);

		//setting up and organizing gui
		frame.getContentPane().setLayout(new GridLayout(board.getYSize() + 2,1));
		hashMap = new HashMap<Coordinates,FieldGUI>();
		msg="";
		this.out = out;
		for(int i=0;i<board.getYSize(); i++) {
			JPanel container = new JPanel();
			
			for(int j=0; j<board.getXSize(); j++) {
				if(board.getFields()[i][j] == 1) {
					
					final FieldGUI field = new FieldGUI();
					field.setPreferredSize(new Dimension(30,30));
					//creating coordiantes and putting them in hashmap
					Coordinates coordinates = new Coordinates(j,i);
					hashMap.put(coordinates, field);


					//adding mouse listener
					field.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent arg0) {


							System.out.println("CLICKED " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY());
							out.println("CLICKED " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY());

							if(counter == 0) {
								msg = "JUMP " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY();

								// connection to the server and sends coords to checking yur fields
								out.println("CHOOSE " + getKeyByValue(hashMap, field).getX() + " " + getKeyByValue(hashMap, field).getY());

							}
							else if(counter ==1) {
								// continue build response with confirmed move
								msg = msg +" "+ getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY();
								out.println(msg);
								// resent state
								counter = 0;
							}
							else if (counter == 2) {
								System.out.println("you should have one more move");
								out.println("AGAIN " + getKeyByValue(hashMap,field).getX() + " "+ getKeyByValue(hashMap,field).getY());
//								counter = 0;
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
		//switching over number of players
		preparer.setUpGUIBoard(numberOfPlayers, hashMap);
	}

	
	
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	
}