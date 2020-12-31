package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;

import GUI.Coordinates;

import Server2.CCPlayer;
import settings.SixArmBoard;

public class SixArmBoardModel extends BoardModel {


	private CCPlayer currentPlayer;
	HashMap<Coordinates,FieldModel> hashMap = new HashMap<Coordinates, FieldModel>();
	private  int numberOfPlayers;
	
	//add number of players in constructor->solution for problem in fieldModel
	public SixArmBoardModel(SixArmBoard board, int numberOfPlayers) {
		
		this.numberOfPlayers = numberOfPlayers;
		for(int i=0;i<board.getYSize(); i++) {
			for(int j=0; j< board.getXSize(); j++) {
				if(board.getFields()[i][j] == 1) {
					
					FieldModel field = new FieldModel();
					Coordinates coordinates = new Coordinates(j,i);

					hashMap.put(coordinates, field);
				}
			}
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
		hashMap.get(new Coordinates(9,3)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(11,3)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(13,3)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(15,3)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(14,2)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(12,2)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(10,2)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(13,1)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(11,1)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(12,0)).setFieldColor(ColorsFor2Players.BLUE);
		
		//Green player
		hashMap.get(new Coordinates(9,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(11,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(13,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(15,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(14,14)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(10,14)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(12,14)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(11,15)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(13,15)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(12,16)).setFieldColor(ColorsFor2Players.GREEN);
	}
	
	
	public void setUpBoardFor4Players(){
		
		//yellow player
		hashMap.get(new Coordinates(0,4)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(2,4)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(4,4)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(6,4)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(5,5)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(1,5)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(3,5)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(2,6)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(4,6)).setFieldColor(ColorsFor4Players.YELLOW);
		hashMap.get(new Coordinates(3,7)).setFieldColor(ColorsFor4Players.YELLOW);
		
		//red Player
		hashMap.get(new Coordinates(18,4)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(20,4)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(22,4)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(24,4)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(19,5)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(21,5)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(23,5)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(20,6)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(22,6)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(21,7)).setFieldColor(ColorsFor4Players.RED);
		
		//magenta player
		hashMap.get(new Coordinates(18,12)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(20,12)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(22,12)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(24,12)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(19,11)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(21,11)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(23,11)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(20,10)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(22,10)).setFieldColor(ColorsFor4Players.MAGENTA);
		hashMap.get(new Coordinates(21,9)).setFieldColor(ColorsFor4Players.MAGENTA);
		
		//cyan player
		
		hashMap.get(new Coordinates(3,9)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(4,10)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(5,11)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(6,12)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(2,10)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(3,11)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(4,12)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(1,11)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(2,12)).setFieldColor(ColorsFor4Players.CYAN);
		hashMap.get(new Coordinates(0,12)).setFieldColor(ColorsFor4Players.CYAN);
	}
	
	public void setUpBoardFor6Players(){
		
		setUpBoardFor2Players();
		setUpBoardFor4Players();
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

	public boolean hasWinner() {
		// todo winner cases
		return (hashMap.get(new Coordinates(14, 8)).getColor() == ColorsFor2Players.GREEN ||
				hashMap.get(new Coordinates(14, 8)).getColor() == ColorsFor2Players.BLUE);
	}

	public synchronized void move(int xLoc, int yLoc, CCPlayer ccPlayer) {
		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		}
		else if (ccPlayer.opponents == null) {
			throw new IllegalStateException("You don't have an opponent yet");
		} else if (hashMap.get(new Coordinates(xLoc, yLoc)).getState() != State.FREE) {
			throw new IllegalStateException("This cell is occupied by other players");
		}
//		else if (states[xLoc][yLoc] != State.FREE) {
//			throw new IllegalStateException("This cell is ocpcupied by opponent");
//		} else if (colors[xLoc][yLoc] != Color.NULL) {
//			throw new IllegalStateException("Cell already occupied");
//		}

		// setting new color
		hashMap.get(new Coordinates(xLoc, yLoc)).setFieldColor(ccPlayer.color);

		// setting to next opponent
		currentPlayer = currentPlayer.nextPlayer;
	}
	
	public synchronized void jump(int xStart, int yStart, int xEnd, int yEnd, CCPlayer ccPlayer) {
		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		} else if (ccPlayer.opponents == null) {
			throw new IllegalStateException("You don't have an opponent yet");
		} else if (hashMap.get(new Coordinates(xEnd, yEnd)).getState() != State.FREE) {
			throw new IllegalStateException("This cell is occupied by other players");
		}else if (hashMap.get(new Coordinates(xStart, yStart)).getState() != State.TAKEN  ) {
			throw new IllegalStateException("This cell is free");
		}
//		else if (states[xLoc][yLoc] != State.FREE) {
//			throw new IllegalStateException("This cell is ocpcupied by opponent");
//		} else if (colors[xLoc][yLoc] != Color.NULL) {
//			throw new IllegalStateException("Cell already occupied");
//		}

		// setting new color
		hashMap.get(new Coordinates(xEnd, yEnd)).setFieldColor(ccPlayer.color);
		hashMap.get((new Coordinates(xStart, yStart))).setFieldFree();
		
		// setting to next opponent
		currentPlayer = currentPlayer.nextPlayer;
	}

	public synchronized boolean choose(int xStart, int yStart, CCPlayer ccPlayer) {
		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		} else if (hashMap.get(new Coordinates(xStart, yStart)).getColor() != currentPlayer.color) {
//			throw new IllegalStateException("Not your field");
			return false;
		}

		// increment counter if pass tests
		return true;

	}

	public synchronized void skip(CCPlayer ccPlayer) {
		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		}

		// setting to next opponent
		currentPlayer = currentPlayer.nextPlayer;
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public CCPlayer getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(CCPlayer ccplayer) {
		this.currentPlayer = ccplayer;
	}

	public HashMap<Coordinates, FieldModel> getHashMap() {
		return hashMap;
	}
}
