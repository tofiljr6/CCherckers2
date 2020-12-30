package Server2;

import GUI.Coordinates;
import model.Colors;
import model.ColorsFor2Players;
import model.FieldModel;
import model.SixArmBoardModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class CCPlayer implements Runnable {
    public SixArmBoardModel sixArmBoardModel;
    public Colors color;
    public CCPlayer opponent;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public CCPlayer(SixArmBoardModel sixArmBoardModel, Colors color, Socket socket) {
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

                String cmd[] = command.split(" ");
                int xLoc = Integer.parseInt(cmd[1]);
                int yLoc = Integer.parseInt(cmd[2]);
                processMoveCommand(xLoc, yLoc);
            } else if (command.startsWith("JUMP")) {

                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                int xEnd = Integer.parseInt(cmd[3]);
                int yEnd = Integer.parseInt(cmd[4]);

                processJumpCommand(xStart, yStart,xEnd, yEnd);
            } else if (command.startsWith("CHOOSE")) {
                // sends to model coords and decided is field is your
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                processInfoCommand(xStart, yStart);
            } else if (command.startsWith("SKIP")) {
                processSkipCommand();
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

    private void processJumpCommand(int xStart, int yStart, int xEnd, int yEnd) {
        try {
            sixArmBoardModel.jump(xStart, yStart, xEnd, yEnd, this);
            output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd +" " + yEnd);
            opponent.output.println("OPPONENT_MOVED " + xStart + " " + yStart + " " +xEnd +" " +yEnd);

            if (sixArmBoardModel.hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            }

//            output.println("BOARD" + Arrays.deepToString(sixArmBoardModel.states) + "\n"
//            + Arrays.deepToString(sixArmBoardModel.colors)); // todo finish to check

            HashMap<Coordinates, FieldModel> board = sixArmBoardModel.getHashMap();
            String btest1 = board.get(new Coordinates(xStart, yStart)).getColor().toString();
            String btest2 = board.get(new Coordinates(xEnd, yEnd)).getColor().toString();
            output.println("BOARD " + btest1+ " " +btest2);

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    private void processInfoCommand(int xStart, int yStart) {
        try {
            // aks model is that field you have chosen is your
            if (sixArmBoardModel.choose(xStart, yStart, this)) {
                // yes, chosen field is your so confirm move
                output.println("CONFIRM_MOVE " + xStart + " " + yStart);
            }
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    private void processSkipCommand() {
        try {
            sixArmBoardModel.skip(this);

            output.println("YOU_SKIPPED");
            opponent.output.println("OPPONENT_SKIP");

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }
}
