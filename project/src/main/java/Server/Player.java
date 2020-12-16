package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Player implements Runnable {

    Game game;
    char mark;
    int playerId;
    Player opponent;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public Player(Game game, char mark, int playerId, Socket socket) {
        this.game = game;
        this.mark = mark;
        this.playerId = playerId;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (opponent != null && opponent.output != null) {
                opponent.output.println("OTHER_PLAYER_LEFT");
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + mark);
        if (mark == 'X') {
            game.currentPlayer = this; // player 1 identified with X
            output.println("MESSAGE Waiting for opponent to connect");
        } else {
            opponent = game.currentPlayer;
            opponent.opponent = this;
            opponent.output.println("MESSAGE your move");
        }
    }

    private void processCommands() {
        while (input.hasNextLine()) {
            var command = input.nextLine();
            if (command.startsWith("QUIT")) {
                return;
            } else if (command.startsWith("MOVE")) {
                processMoveCommand(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("JUMP")) {
                String cmd[] = command.split(" ");
                int fromLocation = Integer.parseInt(cmd[1]);
                int toLocation = Integer.parseInt(cmd[2]);

                processJumpCommand(fromLocation, toLocation);
            }
        }
    }

    private void processMoveCommand(int location) {
        try {
            game.move(location, this);
            output.println("VALID_MOVE");
            opponent.output.println("OPPONENT_MOVED " + location);
            if(game.hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            } // tie is not accepted

            // printing board on console nc - server site only
            // for visualization
            output.println("BOARD" + Arrays.toString(game.getBoard()));

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    private void processJumpCommand(int fromLocation, int toLocation) {
        try {
            game.jump(fromLocation, toLocation, this);
            output.println("VALID_JUMP");
            opponent.output.println("OPPONENT_JUMPED " + fromLocation + " " + toLocation);
            if (game.hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            }

            // printing board on console nc - server site only
            // for visualization
            output.println("BOARD" + Arrays.toString(game.getBoard()));

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

}
