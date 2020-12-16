package Server2;
import model.Color;
import model.State;

public class CCGame {
    public Color[][] colors = {{Color.NULL, Color.BLUE, Color.NULL},
            {Color.RED, Color.NULL, Color.NULL},
            {Color.NULL, Color.NULL, Color.NULL}};

    public State[][] states = {{State.FREE, State.TAKEN, State.FREE},
                        {State.TAKEN, State.FREE, State.FREE},
                        {State.FREE, State.FREE, State.FREE}};

    CCPlayer currentPlayer;

    public boolean hasWinner() {
        // todo winner cases
        return (colors[0][2] == Color.BLUE || colors[1][2] == Color.RED);
    }

    public synchronized void move(int xLoc, int yLoc, CCPlayer ccPlayer) {
        if (ccPlayer != currentPlayer) {
            throw new IllegalStateException("NOT your turn");
        } else if (ccPlayer.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (states[xLoc][yLoc] != State.FREE) {
            throw new IllegalStateException("This cell is ocpcupied by opponent");
        } else if (colors[xLoc][yLoc] != Color.NULL) {
            throw new IllegalStateException("Cell already occupied");
        }

        colors[xLoc][yLoc] = currentPlayer.color;
        states[xLoc][yLoc] = State.TAKEN;
        currentPlayer = currentPlayer.opponent;
    }
}
