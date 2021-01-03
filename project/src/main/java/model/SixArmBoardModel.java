package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;

import GUI.Coordinates;

import Server2.CCPlayer;
import settings.SixArmBoard;
import settings.SixArmBoardWinningCondition;
/**
 * class which defines model of six arm board
 * @author dim
 *
 */
public class SixArmBoardModel extends BoardModel {

	/**
	 
	 */
	private CCPlayer currentPlayer;
	HashMap<Coordinates,FieldModel> hashMap = new HashMap<Coordinates, FieldModel>();
	private  int numberOfPlayers;
	private SixArmBoard board;
	public ArrayList<CCPlayer> players = new ArrayList<>();
	
	private SixArmBoardPreparer preparer;
	private SixArmBoardWinningCondition winningConditions;
	
	/**
	 * class constructor
	 * 
	 * @param board board which is base to built a instance of class
	 * @param numberOfPlayers - numberOfPlayersInGame
	 */
	
	public SixArmBoardModel(SixArmBoard board, int numberOfPlayers) {
		//class responsible for setting up board
		preparer = new SixArmBoardPreparer();
		//class resposible for checking for winning condidtions
		winningConditions = new SixArmBoardWinningCondition(numberOfPlayers);
		this.board = board;

		this.numberOfPlayers = numberOfPlayers;
		
		//itterating over board and creating model fields
		for(int i=0;i<board.getYSize(); i++) {
			for(int j=0; j< board.getXSize(); j++) {
				if(board.getFields()[i][j] == 1) {
					
					FieldModel field = new FieldModel();
					Coordinates coordinates = new Coordinates(j,i);

					hashMap.put(coordinates, field);
				}
			}
		}
		
		//setting board up
		preparer.setUpBoard(numberOfPlayers, hashMap);
	}
	
	/**
	 * method which allows to two way communication between hashmap and key
	 * @param <T>
	 * @param <E>
	 * @param map
	 * @param value
	 * @return
	 */
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	/**
	 * 
	 * @return returns value if game is already finished
	 */
	public boolean gameFinished() {
		
		return winningConditions.isGameFinished();
	}

	/**
	 * 
	 * @param player
	 * @param hashMap
	 * @return boolean value if player already finished
	 */
	public boolean playerFinished(CCPlayer player, HashMap<Coordinates,FieldModel> hashMap) {
		
		return winningConditions.playerFinished(player, hashMap);
	}
	
	/**
	 * 
	 * 
	 * @return return place of player that finished
	 */
	public int getPlaceOfFinishedPlayer() {
		return winningConditions.getNumberOfFinishedPlayers();
	}

	/**
	 * @deprecated
	 * @param xLoc
	 * @param yLoc
	 * @param ccPlayer
	 */
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
	
	/**
	 * method which performs jump on model
	 * @param xStart- x starting argument
	 * @param yStart - y starting argument
	 * @param xEnd - x ending argument
	 * @param yEnd - y ending argument
	 * @param ccPlayer - player which performs jump
	 */
	
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
	}

	/**
	 * method which returns bool value defing if player can start move on selected coordinates (field must be taken by pawn with same color as player) 
	 * @param xStart x coordinate
	 * @param yStart y coordinate
	 * @param ccPlayer player who performe a move
	 * @return
	 */
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

	/**
	 * 
	 * method which allows game to skip turn
	 * @param ccPlayer player which skips turn
	 */
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
		// 1 -> return false info
		// 2 -> return true info - > normal move - not jump over opponent
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
 		} else if (hashMap.get(new Coordinates(xStart + xDestination, yStart + yDestination)).getState() == State.TAKEN ) {
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

		// no neighborhood
		return 2;
	}
/**
 * getter for hashmap
 * @return
 */
	public HashMap<Coordinates, FieldModel> getHashMap() {
		return hashMap;
	}

	/**
	 * 
	 * method which returns if field is taken
	 * @param x coordinate
	 * @param y coordinate
	 * @return 1- taken, 3-free
	 */
	public synchronized int getHashMapCordColor(int x, int y) {

		try {

			if (hashMap.get(new Coordinates(x, y)).getState()== State.TAKEN) {
				return 1; //taken
		
			} else {
				return 3; // free
			}

		} catch (NullPointerException nullPtrExce) {

		}
		return 0;
	}
	
	public synchronized void oneMore(CCPlayer ccPlayer){
		currentPlayer = currentPlayer.nextPlayer;
	}
}
