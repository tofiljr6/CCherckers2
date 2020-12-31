package Client2;

import GUI.SixArmBoardGUI;
import settings.SixArmBoard;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class CCLient {
    // connection variables
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public SixArmBoard board = new SixArmBoard(); // signleton pattern ?? xd
    public SixArmBoardGUI g;
    private ArrayList<Integer> xList = new ArrayList<>();
    private ArrayList<Integer> yList = new ArrayList<>();



    public CCLient(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, 59001);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

//      board = new SixArmBoard();
        g = new SixArmBoardGUI(board,2,out);
      
    }

    public void play() throws Exception {
        try {
        	
        	
            var response = in.nextLine();
//          var color = response.substring(6);
            String c = response.substring(8);


            Color opponentColor;
            Color noopponentColor;

            // center neighborhood
            int xHint = -1;
            int yHint = -1;
            boolean firstHint = true;
            
            if (c.equals("GREEN")) {
                noopponentColor = Color.green;
                opponentColor = Color.blue;
            } else {
                noopponentColor = Color.blue;
                opponentColor = Color.green;
            }

            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE") ) {
                    // rewrite VALID_MODE signal
                	
                    String cmd[] = response.split(" ");
                    int xStart = Integer.parseInt(cmd[1]);
                    int yStart = Integer.parseInt(cmd[2]);
                    int xEnd = Integer.parseInt(cmd[3]);
                    int yEnd = Integer.parseInt(cmd[4]);

                    for (int i = 0; i < xList.size(); i++) {
                        g.setColorRe(xList.get(i), yList.get(i), Color.BLACK);
                    }

                    g.setColorRe(xEnd , yEnd, noopponentColor);
                    g.setColorRe(xStart, yStart, Color.BLACK);
                    g.re();

                    g.setMessageLabel("Valid move, please wait");

                    
                    
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    String cmd[] = response.split(" ");
                    
                    int xStart = Integer.parseInt(cmd[1]);
                    int yStart = Integer.parseInt(cmd[2]);
                    int xEnd = Integer.parseInt(cmd[3]);
                    int yEnd = Integer.parseInt(cmd[4]);
                    
                    g.setColorRe(xEnd , yEnd, opponentColor);
                    g.setColorRe(xStart, yStart, Color.BLACK);
                    g.re();

                    g.setMessageLabel("Opponent moved, your turn");

                } else if (response.startsWith("MESSAGE")) {
                    out.println(response.substring(8));
                } else if (response.startsWith("VICTORY")) {
                    out.println( "Winner Winner");
                    g.setMessageLabel("YOU ARE WINNER");
                    return;
                } else if (response.startsWith("DEFEAT")) {
                    out.println("Sorry you lost");
                    g.setMessageLabel("YOU ARE LOSER");
                    return;
                } else if (response.startsWith("TIE")) {
                    out.println("Tie");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    out.append("Other player left");
                    g.setMessageLabel("Other player left");
                    break;
                } else if (response.startsWith("CONFIRM_MOVE")) { // changing info games process label
                    String cmd[] = response.split(" ");
                    // and your field you have chosen is your so next click on field is field where your will jump
                    g.setMessageLabel("You choose field with coords " + cmd[1] + " " + cmd[2]);
                    g.counter = 1;
                } else if (response.startsWith("BOARD")) { // for visualization only
                    System.out.println(response.substring(5));
                    out.println(response.substring(5));
//                } else if (response.startsWith("WELCOME")) { // greeting label // it is not work
//                    g.setMessageLabel(response);
                } else if (response.startsWith("YOU_SKIP")) {
//                    System.out.println(response);
                    out.println("you skipped, please wait");
                    g.setMessageLabel("you skipped");
                } else if (response.startsWith("OPPONENT_SKIP")) {
                    out.println("opponent skipped");
                    g.setMessageLabel("opponent skipped, your tern");
                } else if (response.startsWith("HINT_TO")) {

//                    for (int i = 0; i < xList.size(); i++) {
//                        g.setColorRe(xList.get(i), yList.get(i), Color.BLACK);
//                    }

                    String cmd[] = response.split(" ");

                    int xS = Integer.parseInt(cmd[1]);
                    int yS = Integer.parseInt(cmd[2]);

                    xList.add(xS);
                    yList.add(yS);

                    g.setColorRe(xS, yS, Color.GRAY);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args)  throws Exception {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }

        CCLient ccLient = new CCLient(args[0]);
        ccLient.play();

    }
}
