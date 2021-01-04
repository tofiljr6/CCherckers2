package settings;

import java.util.ArrayList;
import java.util.HashMap;

import GUI.Coordinates;
import Server2.CCPlayer;
import model.Colors;

import model.PawnColors;
import model.FieldModel;
/**
 * class for winning conditions for 'normal' six arm board
 * @author dim
 *
 */
public class SixArmBoardWinningCondition extends WinningConditions{

	
	
	
	public SixArmBoardWinningCondition(int numberOfPlayers) {
		super(numberOfPlayers);
	}
	/**
	 * condition for green to finish
	 * @return
	 */
	private boolean bottomPlayerFinished(HashMap<Coordinates,FieldModel> hashMap,SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getTopStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * condition for blue to finish
	 * @return
	 */
	private boolean topPlayerFinished(HashMap<Coordinates,FieldModel> hashMap, SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getBottomStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * condition for cyan to finish
	 * @return
	 */
	private boolean bottomLeftPlayerFinished(HashMap<Coordinates,FieldModel> hashMap, SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getUpperRightStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * condition for yellow to finish
	 * @return
	 */
	private boolean upperLeftPlayerFinished(HashMap<Coordinates,FieldModel> hashMap, SixArmBoard sixArmBoard, CCPlayer player) {
	
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getBottomRightStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * condition for red to finish
	 * @return
	 */
	private boolean upperRightPlayerFinished(HashMap<Coordinates,FieldModel> hashMap, SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getBottomLeftStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * condition for magenta to finish
	 * @return
	 */
	private boolean bottomRightPlayerFinished(HashMap<Coordinates,FieldModel> hashMap, SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		ArrayList<Coordinates> finalFields = sixArmBoard.getUpperLeftStartingFields();
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isGameFinished() {
		
		
		return (numberOfPlayers -1 == numberOfFinishedPlayers);
	}

	public int getNumberOfFinishedPlayers() {
		return numberOfFinishedPlayers;
	}
	
	/**
	 * method which checks if given player finished 
	 * @param player player to be checked
	 * @param hashMap hashmap
	 * @return boolean vlaue
	 */
	public boolean playerFinished(HashMap<Coordinates,FieldModel> hashMap,SixArmBoard sixArmBoard, CCPlayer player) {
		
		
		StartingFieldsPosition playersStartingPositions = player.getStartingFieldPosition();
		
		switch(playersStartingPositions) {
			case TOP:
				if(topPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				} else
					return false;
			case UPPER_LEFT:
				if(upperLeftPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case BOTTOM:
				if(bottomPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case BOTTOM_LEFT:
				if(bottomLeftPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case UPPER_RIGHT:
				if(upperRightPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case BOTTOM_RIGHT:
				if(bottomRightPlayerFinished(hashMap,sixArmBoard,player)) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			default:
				return false;
		}
	}
	
}
