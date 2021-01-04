package settings;


import java.awt.Color;
import java.util.ArrayList;

import GUI.Coordinates;
import Server2.CCPlayer;
import model.PawnColors;

public class SixArmBoard extends Board {

	public CCPlayer currentPlayer;
	
	//starting fields in six arm board 0th element are starting fields for top triangle,1-st element upper right, 2-nd lower right,
	//3-bottom one, 4- bottom left, 5- upper left
	private ArrayList<ArrayList<Coordinates>> startingFields;
	
	public SixArmBoard() {

		fields = new int[][] {

				{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0},
				{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
				{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
				{0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
				{0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0},
				{0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0},
				{0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0},
				{0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0},
				{0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
				{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
				{0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0}
		};
		
		startingFields = new ArrayList<>();
		
		ArrayList<Coordinates> topStartingField = new ArrayList<>();
		topStartingField.add(new Coordinates(9,3));
		topStartingField.add(new Coordinates(11,3));
		topStartingField.add(new Coordinates(13,3));
		topStartingField.add(new Coordinates(15,3));			
		topStartingField.add(new Coordinates(14,2));
		topStartingField.add(new Coordinates(12,2));
		topStartingField.add(new Coordinates(10,2));
		topStartingField.add(new Coordinates(13,1));
		topStartingField.add(new Coordinates(11,1));
		topStartingField.add(new Coordinates(12,0));
		startingFields.add(topStartingField);
		

		ArrayList<Coordinates> upperRightStartingField = new ArrayList<>();
		upperRightStartingField.add(new Coordinates(18,4));
		upperRightStartingField.add(new Coordinates(20,4));
		upperRightStartingField.add(new Coordinates(22,4));
		upperRightStartingField.add(new Coordinates(24,4));			
		upperRightStartingField.add(new Coordinates(19,5));
		upperRightStartingField.add(new Coordinates(21,5));
		upperRightStartingField.add(new Coordinates(23,5));
		upperRightStartingField.add(new Coordinates(20,6));
		upperRightStartingField.add(new Coordinates(22,6));
		upperRightStartingField.add(new Coordinates(21,7));
		startingFields.add(upperRightStartingField);
		
		
		ArrayList<Coordinates> bottomRightStartingField = new ArrayList<>();
		bottomRightStartingField.add(new Coordinates(18,12));
		bottomRightStartingField.add(new Coordinates(20,12));
		bottomRightStartingField.add(new Coordinates(22,12));
		bottomRightStartingField.add(new Coordinates(24,12));			
		bottomRightStartingField.add(new Coordinates(19,11));
		bottomRightStartingField.add(new Coordinates(21,11));
		bottomRightStartingField.add(new Coordinates(23,11));
		bottomRightStartingField.add(new Coordinates(20,10));
		bottomRightStartingField.add(new Coordinates(22,10));
		bottomRightStartingField.add(new Coordinates(21,9));
		startingFields.add(bottomRightStartingField);
		

		
		ArrayList<Coordinates> bottomStartingField = new ArrayList<>();
		bottomStartingField.add(new Coordinates(9,13));
		bottomStartingField.add(new Coordinates(11,13));
		bottomStartingField.add(new Coordinates(13,13));
		bottomStartingField.add(new Coordinates(15,13));			
		bottomStartingField.add(new Coordinates(14,14));
		bottomStartingField.add(new Coordinates(10,14));
		bottomStartingField.add(new Coordinates(12,14));
		bottomStartingField.add(new Coordinates(11,15));
		bottomStartingField.add(new Coordinates(13,15));
		bottomStartingField.add(new Coordinates(12,16));
		startingFields.add(bottomStartingField);
		
		
		ArrayList<Coordinates> bottomLeftStartingField = new ArrayList<>();
		bottomLeftStartingField.add(new Coordinates(3,9));
		bottomLeftStartingField.add(new Coordinates(4,10));
		bottomLeftStartingField.add(new Coordinates(5,11));
		bottomLeftStartingField.add(new Coordinates(6,12));			
		bottomLeftStartingField.add(new Coordinates(2,10));
		bottomLeftStartingField.add(new Coordinates(3,11));
		bottomLeftStartingField.add(new Coordinates(4,12));
		bottomLeftStartingField.add(new Coordinates(1,11));
		bottomLeftStartingField.add(new Coordinates(2,12));
		bottomLeftStartingField.add(new Coordinates(0,12));
		startingFields.add(bottomLeftStartingField);
		
		ArrayList<Coordinates> upperLeftStartingField = new ArrayList<>();
		upperLeftStartingField.add(new Coordinates(2,4));
		upperLeftStartingField.add(new Coordinates(4,4));
		upperLeftStartingField.add(new Coordinates(6,4));
		upperLeftStartingField.add(new Coordinates(5,5));			
		upperLeftStartingField.add(new Coordinates(1,5));
		upperLeftStartingField.add(new Coordinates(3,5));
		upperLeftStartingField.add(new Coordinates(2,6));
		upperLeftStartingField.add(new Coordinates(4,6));
		upperLeftStartingField.add(new Coordinates(3,7));
		upperLeftStartingField.add(new Coordinates(0,4));
		startingFields.add(upperLeftStartingField);
	}
	
	public ArrayList<Coordinates> getTopStartingFields() {
		
		return startingFields.get(0);
	}
	
	public ArrayList<Coordinates> getUpperRightStartingFields() {
			
			return startingFields.get(1);
		}
	public ArrayList<Coordinates> getBottomRightStartingFields() {
		
		return startingFields.get(2);
	}
	public ArrayList<Coordinates> getBottomStartingFields() {
		
		return startingFields.get(3);
	}
	public ArrayList<Coordinates> getBottomLeftStartingFields() {
		
		return startingFields.get(4);
	}
	public ArrayList<Coordinates> getUpperLeftStartingFields() {
		
		return startingFields.get(5);
	}

	
	//we assume that board is rectangual size
	public int getXSize() {

		return fields[0].length;
	}
	//getting size of array
	public int getYSize() {

		return fields.length;
	}

	
}
