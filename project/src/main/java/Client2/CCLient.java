package Client2;

import GUI.SixArmBoardGUI;
import settings.SixArmBoard;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CCLient {
    // connection variables
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public SixArmBoard board = new SixArmBoard(); // signleton pattern ?? xd
    public SixArmBoardGUI g;



    public CCLient(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, 59001);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

//        board = new SixArmBoard();
        g = new SixArmBoardGUI(board);
        g.setOut(out);
    }

    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var color = response.substring(5);
            //Color opponentColor = color.startsWith("BLUE") ? java.awt.Color.green : java.awt.Color.BLUE;
            Color opponentColor;
            if (color.startsWith("BLUE")) {
                opponentColor = Color.green;
            } else {
                opponentColor = Color.blue;
            }

            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")) {
                    g.setColorRe(12 , 8, opponentColor);
                    g.re();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    g.re();

                } else if (response.startsWith("MESSAGE")) {
                    out.println(response.substring(8));
                } else if (response.startsWith("VICTORY")) {
                    out.println( "Winner Winner");
                    return;
                } else if (response.startsWith("DEFEAT")) {
                    out.println("Sorry you lost");
                    return;
                } else if (response.startsWith("TIE")) {
                    out.println("Tie");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    out.append("Other player left");
                    break;
                }
                else if (response.startsWith("BOARD")) { // for visualization only
                    System.out.println(response.substring(5));
                    out.println(response.substring(5));
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
