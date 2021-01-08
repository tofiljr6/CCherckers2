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
	public boolean playerFinished(HashMap<Coordinates,FieldModel> hashMap,SixArmBoard sixArmBoard, CCPlayer player) {
		
		Colors color = player.color;
		StartingFieldsPosition playerStartingPosition = player.getStartingFieldPosition();
		StartingFieldsPosition playerFinishPosition = sixArmBoard.getOpponentPosition(playerStartingPosition);
		ArrayList<Coordinates> finalFields = sixArmBoard.getStartingFields(playerFinishPosition);
		
		for(int i=0; i < finalFields.size(); i++ ) {
			if(!(hashMap.get(finalFields.get(i)).getColor().equals(color))) {
				return false;
			}
		}
		numberOfFinishedPlayers++;
		return true;
	}
	
	public boolean isGameFinished() {
		
		
		return (numberOfPlayers -1 == numberOfFinishedPlayers);
	}

	public int getNumberOfFinishedPlayers() {
		return numberOfFinishedPlayers;
	}

}
