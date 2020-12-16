package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChineseCheckersClient {

    // frame variables
    private JFrame frame = new JFrame("Chinese Checkers");
    private JLabel messageLabel = new JLabel("...");

    // board variables
    private Square[] board = new Square[25];
    private Square currentSquare;

    // connection variables
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public ChineseCheckersClient(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, 59001);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        var boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);
        boardPanel.setLayout(new GridLayout(5,5,2,2));

        for (int i = 0; i < board.length; i++) {
            final int j = i;
            board[i] = new Square();

            if (i != 0 && i != 4 && i != 20 && i != 24) { // not narrows so squares with listener
                board[i].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        currentSquare = board[j];
                        out.println("MOVE " + j);
                    }
                });
            } else { // narrows
                board[i].setBackground(Color.BLACK);
            }


            boardPanel.add(board[i]);
        }
        frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
    }


    // ---
    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var mark = response.charAt(8);
            var opponentMark = mark == 'X' ? 'O' : 'X';
            frame.setTitle("Chinese Checkers: Player " + mark);

            // label info about game per client
            messageLabel.setBackground(Color.lightGray);
            frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);

            while (in.hasNextLine()) {
                response = in.nextLine();
                if (response.startsWith("VALID_MOVE")) {
                    messageLabel.setText("Valid move, please wait");
                    currentSquare.setText(mark);
                    currentSquare.repaint();
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    var loc = Integer.parseInt(response.substring(15));
                    board[loc].setText(opponentMark);
                    board[loc].repaint();
                    messageLabel.setText("Opponent moved, your turn");
                } else if (response.startsWith("MESSAGE")) {
                    messageLabel.setText(response.substring(8));
                } else if (response.startsWith("VICTORY")) {
                    JOptionPane.showMessageDialog(frame, "Winner Winner");
                    break;
                } else if (response.startsWith("DEFEAT")) {
                    JOptionPane.showMessageDialog(frame, "Sorry you lost");
                    break;
                } else if (response.startsWith("TIE")) {
                    JOptionPane.showMessageDialog(frame, "Tie");
                    break;
                } else if (response.startsWith("OTHER_PLAYER_LEFT")) {
                    JOptionPane.showMessageDialog(frame, "Other player left");
                    break;
                } else if (response.startsWith("BOARD")) { // for visualization only
                    System.out.println(response);
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
            frame.dispose();
        }
    }
    // ---



    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }

        ChineseCheckersClient client = new ChineseCheckersClient(args[0]);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(480, 480);
        client.frame.setVisible(true);
        client.frame.setResizable(false);

        client.play();
    }
}
