package settings;

public class WinningConditions {

	
	
	protected int numberOfPlayers;
	protected int numberOfFinishedPlayers;
	
	
	public WinningConditions(int numberOfPlayers) {
		
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfFinishedPlayers = 0;

	}
	
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public int getNumberOfFinishedPlayers() {
		return numberOfFinishedPlayers;
	}

}
