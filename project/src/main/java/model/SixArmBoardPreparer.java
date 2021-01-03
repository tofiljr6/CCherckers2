package model;

import java.util.HashMap;

import GUI.Coordinates;

/**
 * 
 * class which set ups six arm board
 * @author dim
 *
 */
public class SixArmBoardPreparer {

	
	
	public void setUpBoard(int numberOfPlayers, HashMap<Coordinates, FieldModel> hashMap) {
		
		switch (numberOfPlayers) {
		
		case 2:
			setUpBoardFor2Players(hashMap);
			break;
		
		case 4:
			setUpBoardFor4Players(hashMap);
			break;
		
		case 6:
			setUpBoardFor6Players(hashMap);
			break;
		}
	}
	
	
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor2Players(HashMap<Coordinates, FieldModel> hashMap) {
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
		hashMap.get(new Coordinates(3,9 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(9,9 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(21,5 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(18,8 )).setFieldColor(ColorsFor2Players.GREEN);
	
		hashMap.get(new Coordinates(20,6 )).setFieldColor(ColorsFor2Players.BLUE);
	
	
		hashMap.get(new Coordinates(8,6 )).setFieldColor(ColorsFor2Players.GREEN);
		//hashMap.get(new Coordinates(10,6 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(7,7 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(5,7 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(4,6 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(5,5 )).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(7,5 )).setFieldColor(ColorsFor2Players.GREEN);
	
		hashMap.get(new Coordinates(14,6 )).setFieldColor(ColorsFor2Players.GREEN);
	
		hashMap.get(new Coordinates(16,4 )).setFieldColor(ColorsFor2Players.GREEN);
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor4Players(HashMap<Coordinates, FieldModel> hashMap) {
		
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
		
		//uncomment that!!
//		hashMap.get(new Coordinates(3,9)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(4,10)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(5,11)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(6,12)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(2,10)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(3,11)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(4,12)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(1,11)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(2,12)).setFieldColor(ColorsFor4Players.CYAN);
//		hashMap.get(new Coordinates(0,12)).setFieldColor(ColorsFor4Players.CYAN);
		
		hashMap.get(new Coordinates(3,9)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(4,10)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(5,11)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(10,12)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(2,10)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(3,11)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(4,12)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(1,11)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(2,12)).setFieldColor(ColorsFor4Players.RED);
		hashMap.get(new Coordinates(0,12)).setFieldColor(ColorsFor4Players.RED);
		
		//delete this after
		hashMap.get(new Coordinates(8,12)).setFieldColor(ColorsFor4Players.MAGENTA);
	}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor6Players(HashMap<Coordinates, FieldModel> hashMap) {
		setUpBoardFor2Players(hashMap);
		setUpBoardFor4Players(hashMap);
	}
}
