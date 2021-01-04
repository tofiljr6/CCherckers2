package model;



import java.util.ArrayList;
import java.util.HashMap;

import GUI.Coordinates;
import settings.SixArmBoard;

/**
 * 
 * class which set ups six arm board
 * @author dim
 *
 */
public class SixArmBoardPreparer {

	
	
	public void setUpBoard(int numberOfPlayers,HashMap<Coordinates, FieldModel> hashMap, SixArmBoard board) {
		
		switch (numberOfPlayers) {
		
		case 2:
			setUpBoardFor2Players(hashMap, board);
			break;
		
		case 4:
			setUpBoardFor4Players(hashMap, board);
			break;
		
		case 6:
			setUpBoardFor6Players(hashMap, board);
			break;
		}
	}
	
	
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor2Players(HashMap<Coordinates, FieldModel> hashMap, SixArmBoard board) {
		//blue player
		
		ArrayList<Coordinates> topStartingFields = board.getTopStartingFields();
		for(int i =0 ;i< topStartingFields.size();i++) {
		hashMap.get(topStartingFields.get(i)).setFieldColor(PawnColors.BLUE);
		}
		
		//Green player
		ArrayList<Coordinates> bottomStartingFields = board.getBottomStartingFields();
		for(int i =0 ;i< bottomStartingFields.size();i++) {
		hashMap.get(bottomStartingFields.get(i)).setFieldColor(PawnColors.GREEN);
		}
	
	
		// easy test for jump through opponent - todo DELETE after
		hashMap.get(new Coordinates(6,6 )).setFieldColor(PawnColors.BLUE);
		hashMap.get(new Coordinates(16,6 )).setFieldColor(PawnColors.BLUE);
		hashMap.get(new Coordinates(3,9 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(9,9 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(21,5 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(18,8 )).setFieldColor(PawnColors.GREEN);
	
		hashMap.get(new Coordinates(20,6 )).setFieldColor(PawnColors.BLUE);
	
	
		hashMap.get(new Coordinates(8,6 )).setFieldColor(PawnColors.GREEN);
		//hashMap.get(new Coordinates(10,6 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(7,7 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(5,7 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(4,6 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(5,5 )).setFieldColor(PawnColors.GREEN);
		hashMap.get(new Coordinates(7,5 )).setFieldColor(PawnColors.GREEN);
	
		hashMap.get(new Coordinates(14,6 )).setFieldColor(PawnColors.GREEN);
	
		hashMap.get(new Coordinates(16,4 )).setFieldColor(PawnColors.GREEN);
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor4Players(HashMap<Coordinates, FieldModel> hashMap, SixArmBoard board) {
		
		//yellow player
		ArrayList<Coordinates> upperLeftStartingFields = board.getUpperLeftStartingFields();
		for(int i =0 ;i<upperLeftStartingFields.size();i++) {
			hashMap.get(upperLeftStartingFields.get(i)).setFieldColor(PawnColors.YELLOW);
		}
		
		//red Player
		ArrayList<Coordinates> upperRightStartingFields = board.getUpperRightStartingFields();
		for(int i =0 ;i< upperRightStartingFields.size();i++) {
			hashMap.get(upperRightStartingFields.get(i)).setFieldColor(PawnColors.RED);
		}
		
		//magenta player
		ArrayList<Coordinates> bottomRightStartingFields = board.getBottomRightStartingFields();
		for(int i =0 ;i< bottomRightStartingFields.size();i++) {
			hashMap.get(bottomRightStartingFields.get(i)).setFieldColor(PawnColors.MAGENTA);
		}
		
		
		
		
		
		//cyan player
		
		//uncomment that!!
//		ArrayList<Coordinates> bottomLeftStartingFields = board.getBottomLeftStartingFields();
//		for(int i =0 ;i< bottomLeftStartingFields.size();i++) {
//		hashMap.get(bottomLeftStartingFields.get(i)).setFieldColor(ColorsFor4Players.CYAN);
//		}
		
		
		hashMap.get(new Coordinates(3,9)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(4,10)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(5,11)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(10,12)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(2,10)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(3,11)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(4,12)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(1,11)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(2,12)).setFieldColor(PawnColors.RED);
		hashMap.get(new Coordinates(0,12)).setFieldColor(PawnColors.RED);
		
		//delete this after
		hashMap.get(new Coordinates(8,12)).setFieldColor(PawnColors.MAGENTA);
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor6Players(HashMap<Coordinates, FieldModel> hashMap,SixArmBoard board) {
		setUpBoardFor2Players(hashMap, board);
		setUpBoardFor4Players(hashMap, board);
	}
}
