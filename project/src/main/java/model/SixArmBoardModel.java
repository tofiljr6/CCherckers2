package model;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;

import GUI.Coordinates;

import settings.SixArmBoard;

public class SixArmBoardModel extends BoardModel {

	HashMap<Coordinates,FieldModel> hashMap;
	
	//add number of players in constructor->solution for problem in fieldModel
	public SixArmBoardModel(SixArmBoard board) {
		
		for(int i=0;i<board.getYSize(); i++) {
			for(int j=0; j< board.getXSize(); j++) {
				if(board.getDimensions()[i][j] == 1) {
					
					FieldModel field = new FieldModel();
					Coordinates coordinates = new Coordinates(i,j);
					hashMap.put(coordinates, field);
				}
			}
		}
		setUpBoardFor2Players();
	}
	
	public void setUpBoardFor2Players() {
		
		//blue player
		hashMap.get(new Coordinates(3,9)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(3,11)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(3,13)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(3,15)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(2,14)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(2,12)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(2,10)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(1,13)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(1,11)).setFieldColor(ColorsFor2Players.BLUE);
		hashMap.get(new Coordinates(0,12)).setFieldColor(ColorsFor2Players.BLUE);
		
		//Green player
		hashMap.get(new Coordinates(13,9)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(13,11)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(13,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(13,15)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(14,14)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(14,10)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(14,12)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(15,11)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(15,13)).setFieldColor(ColorsFor2Players.GREEN);
		hashMap.get(new Coordinates(16,12)).setFieldColor(ColorsFor2Players.GREEN);
	}
	
	
	//implement that later......
	/*
	public void setUpBoardFor4Players(){
		
		//yellow player
		hashMap.get(new Coordinates(4,0)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,2)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(4,6)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,5)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,1)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(5,3)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(6,2)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(6,4)).changeColor(Color.yellow);
		hashMap.get(new Coordinates(7,3)).changeColor(Color.yellow);
		
		//red Player
		hashMap.get(new Coordinates(4,18)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,20)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,22)).changeColor(Color.red);
		hashMap.get(new Coordinates(4,24)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,19)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,21)).changeColor(Color.red);
		hashMap.get(new Coordinates(5,23)).changeColor(Color.red);
		hashMap.get(new Coordinates(6,20)).changeColor(Color.red);
		hashMap.get(new Coordinates(6,22)).changeColor(Color.red);
		hashMap.get(new Coordinates(7,21)).changeColor(Color.red);
		
		//magenta player
		hashMap.get(new Coordinates(12,18)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,20)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,22)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(12,24)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,19)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,21)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(11,23)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(10,20)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(10,22)).changeColor(Color.magenta);
		hashMap.get(new Coordinates(9,21)).changeColor(Color.magenta);
		
		//cyan player
		
		hashMap.get(new Coordinates(9,3)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(10,4)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,5)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,6)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(10,2)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,3)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,4)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(11,1)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,2)).changeColor(Color.cyan);
		hashMap.get(new Coordinates(12,0)).changeColor(Color.cyan);
	}
	
	public void setUpBoardFor6Players(){
		
		setUpBoardFor4Players();
		setUpBoardFor2Players();
	}
	*/
	
	//where should be this class located
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	
	
	

}
