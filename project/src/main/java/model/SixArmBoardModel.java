package model;

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


		// easy test for jump through opponent - todo DELETE after
		hashMap.get(new Coordinates(6,6 )).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(16,6 )).setFieldColor(ColorsFor2Players.BLUE);

		hashMap.get(new Coordinates(20,6 )).setFieldColor(ColorsFor2Players.BLUE);


		hashMap.get(new Coordinates(8,6 )).setFieldColor(ColorsFor2Players.GREEN);
//		hashMap.get(new Coordinates(10,6 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(7,7 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(5,7 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(4,6 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(5,5 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(7,5 )).setFieldColor(ColorsFor2Players.GREEN);

		hashMap.get(new Coordinates(14,6 )).setFieldColor(ColorsFor2Players.GREEN);

		hashMap.get(new Coordinates(16,4 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(15,5 )).setFieldColor(ColorsFor2Players.GREEN);
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
		} else if (hashMap.get(new Coordinates(xStart, yStart)).getState() != State.TAKEN  ) {
			throw new IllegalStateException("This cell is free");
		}

		// this is a coords from selected fields
		int[] xNeighborhood = {-1, 1,  1, -1, 2, -2};
		int[] yNeighborhood = {1 , 1, -1, -1, 0,  0};

		// init - incredible value to start
		// this value is never minus
		int xm = -1;
		int ym = -1;

		// check all surrounding fields
		for (int i = 0; i < xNeighborhood.length; i++) {
			if (2 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i];
				ym = yStart + yNeighborhood[i];
			} else if (3 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] + 2;
				ym = yStart + yNeighborhood[i];
			} else if (4 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] - 2;
				ym = yStart + yNeighborhood[i];
			} else if (5 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] - 1;
				ym = yStart + yNeighborhood[i] + 1;
			} else if (6 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] + 1;
				ym = yStart + yNeighborhood[i] + 1;
			} else if (7 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] + 1;
				ym = yStart + yNeighborhood[i] - 1;
			} else if (8 == hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], ccPlayer)) {
				xm = xStart + xNeighborhood[i] - 1;
				ym = yStart + yNeighborhood[i] - 1;
			}

			// this movement is accepted according rules
			if (xm == xEnd && ym == yEnd && hashMap.get(new Coordinates(xEnd, yEnd)).getColor() != ccPlayer.color) {
				// setting new color
				hashMap.get(new Coordinates(xEnd, yEnd)).setFieldColor(ccPlayer.color);
				hashMap.get((new Coordinates(xStart, yStart))).setFieldFree();
			}


		}

//		// setting new color
//		hashMap.get(new Coordinates(xEnd, yEnd)).setFieldColor(ccPlayer.color);
//		hashMap.get((new Coordinates(xStart, yStart))).setFieldFree();
		
		// setting to next opponent
		currentPlayer = currentPlayer.nextPlayer;
	}

	public synchronized boolean choose(int xStart, int yStart, CCPlayer ccPlayer) {
		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		} else if (hashMap.get(new Coordinates(xStart, yStart)).getState() != State.TAKEN) {
			return false;
		} else if (hashMap.get(new Coordinates(xStart, yStart)).getColor() != ccPlayer.color) {
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

	public synchronized int hints(int xStart, int xDestination, int yStart, int yDestination, CCPlayer ccPlayer) {
		// return false -> 1;
		// return true -> 2;
		// 1 -> return false info
		// 2 -> return true info
		// 3 -> return that recursion right R
		// 4 -> return that recursion left L
		// 5 -> return that recursion upper right UR
		// 6 -> return that recursion bottom right BR
		// 7 -> return that recursion bottom left BL
		// 8 -> return that recursion upper left UL

		if (ccPlayer != currentPlayer) {
			throw new IllegalStateException("NOT your turn");
		} else if (hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)) == null) {
			return 1;
 		} else if (hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)).getState() == State.TAKEN &&
				   hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)).getColor() != ccPlayer.color) {
			if (xDestination == 2 && yDestination == 0) {
				return 3;
			} else if (xDestination == -2 && yDestination == 0) {
				return 4;
			} else if (xDestination == -1 && yDestination == 1) {
				return 5;
			} else if (xDestination == 1 && yDestination == 1) {
				return 6;
			} else if (xDestination == 1 && yDestination == -1) {
				return 7;
			} else if (xDestination == -1 && yDestination == -1) {
				return 8;
			}
		}
		else if (hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)).getState() == State.TAKEN) {
			return 1;
		}

		hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)).setFieldColorHint(ColorsFor2Players.GREEN);

		return 2;
	}

	public HashMap<Coordinates, FieldModel> getHashMap() {
		return hashMap;
	}

	public int getHashMapCordColor(int x, int y) {
//		try {
			if (hashMap.get(new Coordinates(x, y)).getColor() == ColorsFor2Players.GREEN) {
				return 1; // green
			} else if (hashMap.get(new Coordinates(x, y)).getColor() == ColorsFor2Players.BLUE) {
				return 2; // blue
			} else {
				return 3;
			}
//		} catch (NullPointerException nullPtrExce) {
//			System.out.println("nullptr" + nullPtrExce);
//		}
//		return 0;
	}
}
