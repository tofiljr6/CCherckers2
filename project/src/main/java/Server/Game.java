package Server;

public class Game {
    private Player[] board = new Player[25];

    Player currentPlayer;

    public boolean hasWinner() { // todo - make good conditions yto this method
        return (board[21] != null && board[21].playerId == 2 && board[22] != null && board[22].playerId == 2 && board[23] != null && board[23].playerId == 2) ||
                (board[9] != null && board[9].playerId == 1 && board[14] != null && board[14].playerId == 1 && board[19] != null && board[19].playerId == 1);
    }

    // todo - boardFillerUp? I suppose that we don't need it because we don't accept tie in game

    public synchronized void move(int location, Player player) {
//        if (player != currentPlayer) {
//            throw new IllegalStateException("Not your turn");
//        } else if (player.opponent == null) {
//            throw new IllegalStateException("You don't have an opponent yet");
//        } else if (board[location] != null) {
//            throw new IllegalStateException("Cell already occupied");
//        }
        board[location] = currentPlayer;
        currentPlayer = currentPlayer.opponent;
    }


    public synchronized void jump(int fromLocation, int toLocation, Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("Not your turn");
        } else if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (board[fromLocation] == null) {
            throw new IllegalStateException("This cell is not occupied by you");
        } else if (board[fromLocation].mark == player.opponent.mark) {
            throw new IllegalStateException("This cell is occupied by opponent");
        } else if (board[toLocation] != null && board[toLocation].mark == player.opponent.mark) {
            throw new IllegalStateException("Occupied");
        }

        board[toLocation] = currentPlayer;
        board[fromLocation] = null;
        currentPlayer = currentPlayer.opponent;
    }

    public Player[] getBoard() {
        return board;
    }
}
