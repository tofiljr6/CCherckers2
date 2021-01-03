package Client2;

import GUI.SixArmBoardGUI;
import settings.SixArmBoard;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class represnt what client sends to server
 */
public class CCLient {
    // connection variables
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    // current color
    private ColorInterpreter colorInterpreter;
    // the same board for each players
    public SixArmBoard board = new SixArmBoard(); // signleton pattern ?? xd
    // players gui
    public SixArmBoardGUI g;
    // hints fields coords
    private ArrayList<Integer> xList = new ArrayList<>();
    private ArrayList<Integer> yList = new ArrayList<>();
    // number of players ake place in game
    private int numberOfPlayers;

    /**
     * Constructor
     * @param serverAddress what server address is (localhost)
     * @param numberOfPlayers how many players take part in the game
     * @throws Exception connection variable is close or not exist
     */
    public CCLient(String serverAddress, int numberOfPlayers) throws Exception {
        // init connection var
        socket = new Socket(serverAddress, 59001);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        // init players number
        this.numberOfPlayers = numberOfPlayers;
//      board = new SixArmBoard();
        // set gui , depends how many players take part in game
        g = new SixArmBoardGUI(board,this.numberOfPlayers,out);
        // set current color
        colorInterpreter = new ColorInterpreter(this.numberOfPlayers);
    }

    /**
     * the method is responsible to what c linten what server sends to client
     * @throws Exception input exception - input is close / server is not run
     */
    public void play() throws Exception {
        try {
            // listen on response what server sends
            var response = in.nextLine();
//          var color = response.substring(6);
            String c = response.substring(8);

            // current player color
            Color currentPlayerColor;
            Color playerColor;

            playerColor = colorInterpreter.interprateColors(c);
            currentPlayerColor = Color.magenta;

            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE") ) {
                	// we make good move/jump so repaint all necessary graphics to be up-to-date board

                    // VALID_MOVE command sends with params
                    // we convert it to integer and set to graphics
                    String cmd[] = response.split(" ");
                    int xStart = Integer.parseInt(cmd[1]);
                    int yStart = Integer.parseInt(cmd[2]);
                    int xEnd = Integer.parseInt(cmd[3]);
                    int yEnd = Integer.parseInt(cmd[4]);
                    
                    // clean a hints
                    // so gray fields comes to black again
                    for (int i = 0; i < xList.size(); i++) {
                        g.setColorRe(xList.get(i), yList.get(i), Color.BLACK);
                    }

                    g.setColorRe(xEnd , yEnd, playerColor);
                    g.setColorRe(xStart, yStart, Color.BLACK);
                    g.re();

                    // coordinates system - checking
                    System.out.println(xList);
                    System.out.println(yList);

                    // clear coords hints
                    // to next hits we must sure we have emoty list
                    xList.clear();
                    yList.clear();

                    // depend on what jump we did, the label is set
                    if (cmd[5].equals("oneMoreMove")) {
                        // one more move
                        g.setMessageLabel("you have one more move");
                    } else {
                        // you have one move
                        g.setMessageLabel("Valid move, please wait");
                    }
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    // opponents make good move/jump so repaint all necessary graphics to be up-to-date board
                    String cmd[] = response.split(" ");
                   
                    int xStart = Integer.parseInt(cmd[1]);
                    int yStart = Integer.parseInt(cmd[2]);
                    int xEnd = Integer.parseInt(cmd[3]);
                    int yEnd = Integer.parseInt(cmd[4]);
                    currentPlayerColor = colorInterpreter.interprateColors(cmd[5]);

                    // OPPONENT_MOVE command sends with params
                    // we convert it to integer and set to graphics
                    g.setColorRe(xEnd , yEnd, currentPlayerColor);
                    g.setColorRe(xStart, yStart, Color.BLACK);
                    g.re();

                    // set good label
                    g.setMessageLabel("Opponent moved, your turn");
                } else if (response.startsWith("MESSAGE")) {
                    // this coomand handle all warings. start from 8 char
                    out.println(response.substring(8));
                } else if (response.startsWith("VICTORY")) {
                    // victory case command
                    out.println( "Winner Winner");
                    g.setMessageLabel("YOU ARE WINNER");
                    return;
                } else if (response.startsWith("DEFEAT")) {
                    // loser case command
                    out.println("Sorry you lost");
                    g.setMessageLabel("YOU ARE LOSER");
                    return;
                } else if (response.startsWith("TIE")) {
                    // tie case command
                    out.println("Tie");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    // other player left case
                    out.append("Other player left");
                    g.setMessageLabel("Other player left");
                    break;
                } else if (response.startsWith("CONFIRM_MOVE")) { // changing info games process label
                    // player has typied the good field which is his.
                    String cmd[] = response.split(" ");
                    // and your field you have chosen is your so next click on field is field where your will jump
                    g.setMessageLabel("You choose field with coords " + cmd[1] + " " + cmd[2]);
                    // next clicked is sends jump to the server
                    g.counter = 1;
                } else if (response.startsWith("BOARD")) { // for visualization only
                    System.out.println(response.substring(5));
                    out.println(response.substring(5));
//                } else if (response.startsWith("WELCOME")) { // greeting label // it is not work
//                    g.setMessageLabel(response);
                } else if (response.startsWith("YOU_SKIP")) {
                    // skip command current player
//                    System.out.println(response);
                    out.println("you skipped, please wait");
                    g.setMessageLabel("you skipped");
                } else if (response.startsWith("OPPONENT_SKIP")) {
                    // skip command opponents
                    out.println("opponent skipped");
                    g.setMessageLabel("opponent skipped, your tern");
                } else if (response.startsWith("HINT_TO")) {
                    // hints command
                    // colorize the field where you can jump
                    // this field is correct with game rules
                    String cmd[] = response.split(" ");

                    // this is coords correct fields
                    int xS = Integer.parseInt(cmd[1]);
                    int yS = Integer.parseInt(cmd[2]);
                    int col = Integer.parseInt(cmd[3]);

                    // @see what hint return
                    // the field is balck color so it is not taken
                    if (col == 3) {
                        // append to list
                        xList.add(xS);
                        yList.add(yS);

                        // set hints in gui
                        g.setColorRe(xS, yS, Color.GRAY);
                    }
                } else if (response.startsWith("CLEAR_HINTS")) {
                    // clean a hints
                    // so gray fields comes to black again
                    for (int i = 0; i < xList.size(); i++) {
                        g.setColorRe(xList.get(i), yList.get(i), Color.BLACK);
                    }
                } else if (response.startsWith("ONE_MORE_MOVE")) {
                    // we jump over pawn so we have one more move
                    out.println("you have one more move");
                    g.setMessageLabel("You have one more move");
                } else if (response.startsWith("MOVE_AGAIN")) {
                    // we jump over pawn so we have one more move and next clicked is where we jump
                    // so counter equals 2
                    // @see  sixArmBoardGui and mouseListener
                    g.setMessageLabel("you have extra move");
                    g.counter = 2;
                } else if (response.startsWith("NO_MOVE_AGAIN")) {
                    // we dont jump over pawn so we dont have one more move
                    // so counter now equals 0
                    g.setMessageLabel("dont have extra move");
                    g.counter = 0;
                } else if (response.startsWith("CLEAN_LISTS")) {
                    // clear all neighborhood coords
                    xList.clear();
                    yList.clear();
                }
                else if (response.startsWith("CONGRATULATION")) {
                    // you are winner - case
                	g.setMessageLabel(response);
//                	break; // HOW I GET WRITE THIS !!
                }
                else if(response.startsWith("BEGIN")) {
                	g.setMessageLabel("You start");
                }
//                else if (response.startsWith("CLEAR_FIN")) {
//                    String cmd[] = response.split(" ");
//
//                    // this is coords correct fields
//                    int xS = Integer.parseInt(cmd[1]);
//                    int yS = Integer.parseInt(cmd[2]);
//
//                    g.setColorRe(xS, yS, Color.BLACK);
//
//                }
//                else if (response.startsWith("CLEAN_XY")) {
//                    String cmd[] = response.split(" ");
//
//                    // this is coords correct fields
//                    int xS = Integer.parseInt(cmd[1]);
//                    int yS = Integer.parseInt(cmd[2]);
//
//                    g.setColorRe(xS, yS, Color.BLACK);
//                    g.re();
//                } else if (response.startsWith("REPAINT_ME")) {
//                    g.re();
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    /**
     * main method - start client
     * @param args on what port we listen (localhost)
     * @throws Exception the socket server is not run
     */
    public static void main(String[] args)  throws Exception {
        if (args.length != 2) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }

        int numberOfPlayers = Integer.parseInt(args[1]);

        CCLient ccLient = new CCLient(args[0],numberOfPlayers);
        ccLient.play();

    }
}
