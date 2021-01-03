package GUI;

import java.awt.Color;
import java.util.HashMap;

import model.ColorsFor2Players;
import model.ColorsFor4Players;
import model.FieldModel;

public class SixArmBoardGUIPreparer {

	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor2Players(HashMap<Coordinates, FieldGUI> hashMap) {
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


	// easy test for jump through opponent - todo DELETE after
	hashMap.get(new Coordinates(6,6 )).changeColor(Color.BLUE);
	hashMap.get(new Coordinates(16,6 )).changeColor(Color.BLUE);
	hashMap.get(new Coordinates(20,6 )).changeColor(Color.BLUE);
	hashMap.get(new Coordinates(3,9 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(9,9 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(21,5 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(18,8 )).changeColor(Color.GREEN);

	hashMap.get(new Coordinates(8,6 )).changeColor(Color.GREEN);
//	hashMap.get(new Coordinates(10,6 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(7,7 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(5,7 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(4,6 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(5,5 )).changeColor(Color.GREEN);
	hashMap.get(new Coordinates(7,5 )).changeColor(Color.GREEN);

	hashMap.get(new Coordinates(14,6 )).changeColor(Color.GREEN);



	hashMap.get(new Coordinates(16,4 )).changeColor(Color.GREEN);
}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor4Players(HashMap<Coordinates, FieldGUI> hashMap) {
		
		
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
			hashMap.get(new Coordinates(19,5)).changeColor(Color.red);
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
			
			
			//delete this after
			hashMap.get(new Coordinates(8,12)).changeColor(Color.magenta);
			//cyan player
			
//			hashMap.get(new Coordinates(3,9)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(4,10)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(5,11)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(6,12)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(2,10)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(3,11)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(4,12)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(1,11)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(2,12)).changeColor(Color.cyan);
//			hashMap.get(new Coordinates(0,12)).changeColor(Color.cyan);
			hashMap.get(new Coordinates(3,9)).changeColor(Color.RED);
			hashMap.get(new Coordinates(4,10)).changeColor(Color.RED);
			hashMap.get(new Coordinates(5,11)).changeColor(Color.RED);
			hashMap.get(new Coordinates(10,12)).changeColor(Color.RED);
			hashMap.get(new Coordinates(2,10)).changeColor(Color.RED);
			hashMap.get(new Coordinates(3,11)).changeColor(Color.RED);
			hashMap.get(new Coordinates(4,12)).changeColor(Color.RED);
			hashMap.get(new Coordinates(1,11)).changeColor(Color.RED);
			hashMap.get(new Coordinates(2,12)).changeColor(Color.RED);
			hashMap.get(new Coordinates(0,12)).changeColor(Color.RED);
		}
	/**
	 * setting up given fields
	 * @param hashMap hashmap on which operations are performed
	 */
	public void setUpBoardFor6Players(HashMap<Coordinates, FieldGUI> hashMap) {
		setUpBoardFor2Players(hashMap);
		setUpBoardFor4Players(hashMap);
	}
}
