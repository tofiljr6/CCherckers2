package Server2;

import model.Color;
import model.ColorsFor2Players;
import model.SixArmBoardModel;
import settings.SixArmBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class CCPlayer implements Runnable {
    public SixArmBoardModel ccGame;
    public ColorsFor2Players color;
    public CCPlayer opponent;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public CCPlayer(SixArmBoardModel ccGame, ColorsFor2Players color, Socket socket) {
        this.ccGame = ccGame;
        this.color = color;
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
        output.println("WELCOME " + color.toString());
        if (color == ColorsFor2Players.BLUE) {
            ccGame.currentPlayer = this;
            output.println("MESSAGE Waiting for opponent to connect");
        } else {
            opponent = ccGame.currentPlayer;
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
                String cmd[] = command.split(" ");
                int xLoc = Integer.parseInt(cmd[1]);
                int yLoc = Integer.parseInt(cmd[2]);
                processMoveCommand(xLoc, yLoc);
            }
        }
    }

    private void processMoveCommand(int xLoc, int yLoc) {
        try {
            ccGame.move(xLoc, yLoc, this);
            output.println("VALID_MOVE");
            opponent.output.println("OPPONENT_MOVED " + xLoc + "" + yLoc);

            if (ccGame.hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            }

//            output.println("BOARD" + Arrays.deepToString(ccGame.states) + "\n" + Arrays.deepToString(ccGame.colors)); // todo finish to check

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }


}
