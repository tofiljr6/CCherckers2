package settings;

import java.util.HashMap;

import GUI.Coordinates;
import Server2.CCPlayer;
import model.Colors;
import model.ColorsFor2Players;
import model.ColorsFor4Players;
import model.FieldModel;

public class SixArmBoardWinningCondition extends WinningConditions{

	private HashMap<Coordinates,FieldModel> hashMap = new HashMap<>();
	
	public SixArmBoardWinningCondition(int numberOfPlayers) {
		super(numberOfPlayers);
	}

	private boolean greenFinished() {
		if(
		(hashMap.get(new Coordinates(9,3)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(11,3)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(13,3)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(15,3)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(14,2)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(12,2)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(10,2)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(13,1)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(11,1)).getColor()==ColorsFor2Players.BLUE) &&
		(hashMap.get(new Coordinates(12,0)).getColor()==ColorsFor2Players.BLUE) 
		)
			return true;
		else 
			return false;
	}
	
	private boolean blueFinished() {
		if(
		(hashMap.get(new Coordinates(9,13)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(11,13)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(13,13)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(15,13)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(14,14)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(10,14)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(12,14)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(11,15)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(13,15)).getColor()==ColorsFor2Players.GREEN) &&
		(hashMap.get(new Coordinates(12,16)).getColor()==ColorsFor2Players.GREEN) 
		)
			return true;
		else 
			return false;
	}
	
	private boolean redFinished() {
		if(
		(hashMap.get(new Coordinates(3,9)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(4,10)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(15,11)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(6,12)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(2,10)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(3,11)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(4,12)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(1,11)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(2,12)).getColor()==ColorsFor4Players.CYAN) &&
		(hashMap.get(new Coordinates(0,12)).getColor()==ColorsFor4Players.CYAN) 
		)
			return true;
		else 
			return false;
	}
	
	private boolean magentaFinished() {
		if(
		(hashMap.get(new Coordinates(18,12)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(20,12)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(22,12)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(24,12)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(19,11)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(21,11)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(23,11)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(20,10)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(22,10)).getColor()==ColorsFor4Players.YELLOW) &&
		(hashMap.get(new Coordinates(21,9)).getColor()==ColorsFor4Players.YELLOW) 
		)
			return true;
		else 
			return false;
	}
	
	
	
	private boolean cyanFinished() {
		if(
		(hashMap.get(new Coordinates(18,4)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(20,4)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(22,4)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(24,4)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(19,5)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(21,5)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(23,5)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(20,6)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(22,6)).getColor()==ColorsFor4Players.RED) &&
		(hashMap.get(new Coordinates(21,7)).getColor()==ColorsFor4Players.RED) 
		)
			return true;
		else 
			return false;
	}
	

	private boolean yellowFinished() {
		if(
		(hashMap.get(new Coordinates(18,12)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(20,12)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(22,12)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(24,12)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(19,11)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(21,11)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(23,11)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(20,10)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(22,10)).getColor()==ColorsFor4Players.MAGENTA) &&
		(hashMap.get(new Coordinates(21,9)).getColor()==ColorsFor4Players.MAGENTA) 
		)
			return true;
		else 
			return false;
	}
	
	public boolean isGameFinished() {
		
		
		return (numberOfPlayers -1 == numberOfFinishedPlayers);
	}
	
	public int getNumberOfFinishedPlayers() {
		return numberOfFinishedPlayers;
	}
	public boolean playerFinished(CCPlayer player,HashMap<Coordinates,FieldModel> hashMap) {
		
		this.hashMap = hashMap;
		String playersColor = player.color.toString();
		
		switch(playersColor) {
			case "BLUE":
				if(blueFinished()) {
					numberOfFinishedPlayers++;
					return true;
				} else
					return false;
			case "YELLOW":
				if(yellowFinished()) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case "GREEN":
				if(greenFinished()) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case "CYAN":
				if(cyanFinished()) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case "RED":
				if(redFinished()) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			case "MAGENTA":
				if(magentaFinished()) {
					numberOfFinishedPlayers++;
					return true;
				}else
					return false;
			default:
				return false;
		}
	}
	
}
