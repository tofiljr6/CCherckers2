package Server2;

import GUI.Coordinates;
import model.ColorsFor2Players;
import model.FieldModel;
import model.SixArmBoardModel;
import settings.SixArmBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CCPlayer implements Runnable {
    public SixArmBoardModel sixArmBoardModel;
    public ColorsFor2Players color;
    public CCPlayer opponent;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public CCPlayer(SixArmBoardModel sixArmBoardModel, ColorsFor2Players color, Socket socket) {
        this.sixArmBoardModel = sixArmBoardModel;
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
            sixArmBoardModel.currentPlayer = this;
            output.println("MESSAGE Waiting for opponent to connect");
        } else {
            opponent = sixArmBoardModel.currentPlayer;
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
            	System.out.print("A");
                String cmd[] = command.split(" ");
                int xLoc = Integer.parseInt(cmd[1]);
                int yLoc = Integer.parseInt(cmd[2]);
                processMoveCommand(xLoc, yLoc);
            }
        }
    }

    private void processMoveCommand(int xLoc, int yLoc) {
        try {
            sixArmBoardModel.move(xLoc, yLoc, this);
            output.println("VALID_MOVE " + xLoc + " " + yLoc);
            opponent.output.println("OPPONENT_MOVED " + xLoc + " " + yLoc);

            if (sixArmBoardModel.hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            }

//            output.println("BOARD" + Arrays.deepToString(sixArmBoardModel.states) + "\n"
//            + Arrays.deepToString(sixArmBoardModel.colors)); // todo finish to check

            HashMap<Coordinates, FieldModel> board = sixArmBoardModel.getHashMap();
            String btest = board.get(new Coordinates(xLoc, yLoc)).getColor().toString();

            output.println("BOARD " + btest);


        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }


}
