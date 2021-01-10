package GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


import model.PawnColors;
import settings.SixArmBoard;
import settings.StartingFieldsPosition;


public class SixArmBoardGUIPreparer {

	
	public void setUpGUIBoard(int numberOfPlayers, HashMap<Coordinates, FieldGUI> hashMap, SixArmBoard sixArmBoard) {
		
		switch(numberOfPlayers) {
		
		case 2:
			setUpBoardFor2Players(hashMap, sixArmBoard);
			break;
		case 3:
			setUpBoardFor3Players(hashMap,sixArmBoard);
			break;
		case 4:
			setUpBoardFor4Players(hashMap, sixArmBoard);
			break;
		case 6:
			setUpBoardFor6Players(hashMap, sixArmBoard);
			break;
		}
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	private void setUpBoardFor2Players(HashMap<Coordinates, FieldGUI> hashMap,SixArmBoard sixArmBoard) {
		//top player
		PawnColors topPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.TOP);
		Color topFieldColor = pawnColorToGUIColor(topPawnColor);
		ArrayList<Coordinates> topPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.TOP);
		
		for(int i=0; i< topPlayerStartingFields.size(); i++) {
			hashMap.get(topPlayerStartingFields.get(i)).changeColor(topFieldColor);
		}


	//bottom player
		PawnColors bottomPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.BOTTOM);
		Color bottomFieldColor = pawnColorToGUIColor(bottomPawnColor);
		ArrayList<Coordinates> bottomPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.BOTTOM);
		for(int i=0; i< bottomPlayerStartingFields.size(); i++) {
			hashMap.get(bottomPlayerStartingFields.get(i)).changeColor(bottomFieldColor);
		}


	//	// easy test for jump through opponent - todo DELETE after
	//	hashMap.get(new Coordinates(6,6 )).changeColor(Color.BLUE);
	//	hashMap.get(new Coordinates(16,6 )).changeColor(Color.BLUE);
	//	hashMap.get(new Coordinates(20,6 )).changeColor(Color.BLUE);
	//	hashMap.get(new Coordinates(3,9 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(9,9 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(21,5 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(18,8 )).changeColor(Color.GREEN);
	//
	//	hashMap.get(new Coordinates(8,6 )).changeColor(Color.GREEN);
	////	hashMap.get(new Coordinates(10,6 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(7,7 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(5,7 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(4,6 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(5,5 )).changeColor(Color.GREEN);
	//	hashMap.get(new Coordinates(7,5 )).changeColor(Color.GREEN);
	//
	//	hashMap.get(new Coordinates(14,6 )).changeColor(Color.GREEN);
	//
	//
	//
	//	hashMap.get(new Coordinates(16,4 )).changeColor(Color.GREEN);
	}
	
	private void setUpBoardFor3Players(HashMap<Coordinates, FieldGUI> hashMap,SixArmBoard sixArmBoard) {
		//top player
		PawnColors topPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.TOP);
		Color topFieldColor = pawnColorToGUIColor(topPawnColor);
		ArrayList<Coordinates> topPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.TOP);
		
		for(int i=0; i< topPlayerStartingFields.size(); i++) {
			hashMap.get(topPlayerStartingFields.get(i)).changeColor(topFieldColor);
		}

		//bottom right player
		PawnColors bottomRightPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.BOTTOM_RIGHT);
		Color bottomRightFieldColor = pawnColorToGUIColor(bottomRightPawnColor);
		ArrayList<Coordinates> bottomRightPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.BOTTOM_RIGHT);
		for(int i=0; i< bottomRightPlayerStartingFields.size(); i++) {
			hashMap.get(bottomRightPlayerStartingFields.get(i)).changeColor(bottomRightFieldColor);
		}
	
		//bottom left player
		PawnColors bottomLeftPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.BOTTOM_LEFT);
		Color bottomLeftFieldColor = pawnColorToGUIColor(bottomLeftPawnColor);
		ArrayList<Coordinates> bottomLeftPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.BOTTOM_LEFT);
		for(int i=0; i< bottomLeftPlayerStartingFields.size(); i++) {
			hashMap.get(bottomLeftPlayerStartingFields.get(i)).changeColor(bottomLeftFieldColor);
		}
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	private void setUpBoardFor4Players(HashMap<Coordinates, FieldGUI> hashMap, SixArmBoard sixArmBoard) {
		
		
			//upper left player
			PawnColors upperLeftPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.UPPER_LEFT);
			Color upperLeftFieldColor = pawnColorToGUIColor(upperLeftPawnColor);
			ArrayList<Coordinates> upperLeftPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.UPPER_LEFT);;
			for(int i=0; i< upperLeftPlayerStartingFields.size(); i++) {
				hashMap.get(upperLeftPlayerStartingFields.get(i)).changeColor(upperLeftFieldColor);
			}
				
			//upper right Player
			PawnColors upperRightPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.UPPER_RIGHT);
			Color upperRightFieldColor = pawnColorToGUIColor(upperRightPawnColor);
			ArrayList<Coordinates> upperRightPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.UPPER_RIGHT);
			for(int i=0; i< upperRightPlayerStartingFields.size(); i++) {
				hashMap.get(upperRightPlayerStartingFields.get(i)).changeColor(upperRightFieldColor);
			}
			
			//bottom right player
			PawnColors bottomRightPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.BOTTOM_RIGHT);
			Color bottomRightFieldColor = pawnColorToGUIColor(bottomRightPawnColor);
			ArrayList<Coordinates> bottomRightPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.BOTTOM_RIGHT);
			for(int i=0; i< bottomRightPlayerStartingFields.size(); i++) {
				hashMap.get(bottomRightPlayerStartingFields.get(i)).changeColor(bottomRightFieldColor);
			}
			
			

			
			//bottom left player
			PawnColors bottomLeftPawnColor = sixArmBoard.getColorFromStartingPosition(StartingFieldsPosition.BOTTOM_LEFT);
			Color bottomLeftFieldColor = pawnColorToGUIColor(bottomLeftPawnColor);
			ArrayList<Coordinates> bottomLeftPlayerStartingFields = sixArmBoard.getStartingFields(StartingFieldsPosition.BOTTOM_LEFT);
			for(int i=0; i< bottomLeftPlayerStartingFields.size(); i++) {
				hashMap.get(bottomLeftPlayerStartingFields.get(i)).changeColor(bottomLeftFieldColor);
			}

			//commentted stuff co check win condition
			
//			hashMap.get(new Coordinates(8,12)).changeColor(Color.magenta);
//			hashMap.get(new Coordinates(3,9)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(4,10)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(5,11)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(10,12)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(2,10)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(3,11)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(4,12)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(1,11)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(2,12)).changeColor(Color.RED);
//			hashMap.get(new Coordinates(0,12)).changeColor(Color.RED);
		}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	private void setUpBoardFor6Players(HashMap<Coordinates, FieldGUI> hashMap,SixArmBoard sixArmBoard) {
		setUpBoardFor2Players(hashMap,sixArmBoard);
		setUpBoardFor4Players(hashMap,sixArmBoard);
	}
	
	private Color pawnColorToGUIColor(PawnColors pawnColor) {
		
		switch(pawnColor) {
		
		case RED:
			return Color.red;
		case YELLOW:
			return Color.yellow;
		case GREEN:
			return Color.green;
		case BLUE:
			return Color.blue;
		case MAGENTA:
			return Color.magenta;
		case CYAN:
			return Color.cyan;
		case NULL:
			return Color.black;
		}
		return null;
		
	}
}
