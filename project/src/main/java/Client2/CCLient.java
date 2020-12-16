package Client2;

import GUI.SixArmBoardGUI;
import settings.SixArmBoard;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CCLient {
    // connection variables
    private Socket socket;
    private Scanner in;
    private PrintWriter out;


    public CCLient(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, 59001);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception {
        try {
            var response = in.nextLine();


            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")) {

                } else if (response.startsWith("OPPONENT_MOVED")) {

                } else if (response.startsWith("MESSAGE")) {
                    out.println(response.substring(8));
                } else if (response.startsWith("VICTORY")) {
                    out.println( "Winner Winner");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    out.println("Sorry you lost");
                    break;
                } else if (response.startsWith("TIE")) {
                    out.println("Tie");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    out.append("Other player left");
                    break;
                } else if (response.startsWith("BOARD")) { // for visualization only
                    System.out.println(response.substring(6));
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
