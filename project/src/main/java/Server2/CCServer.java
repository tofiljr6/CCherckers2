package Server2;

import model.ColorsFor2Players;
import model.ColorsFor4Players;
import model.SixArmBoardModel;

import settings.SixArmBoard;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

/**
 * Class represetns the server
 */
public class CCServer {
    /**
     * run server on port 59001
     * @param args localhost
     */
    public static void main(String[] args) {
        try (var listener = new ServerSocket(59001)) {
            System.out.println("CCServer 2.0 is running...");
            SixArmBoardModel sixArmBoardModel;
            int numberOfPlayers = Integer.parseInt(args[1]);
            // upper limit of player is 20 (threads)
            // we will never handle 20 players but it save maybe not optimization
            var pool = Executors.newFixedThreadPool(20);
//            System.out.print(ColorsFor4Players.MAGENTA.toString());
            while (true) {
                SixArmBoard sixArmBoard = new SixArmBoard();

                switch (numberOfPlayers) {
                    case 2:
                        // game to two players
                        sixArmBoardModel = new SixArmBoardModel(sixArmBoard,2);

                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.BLUE, listener.accept()));
                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.GREEN, listener.accept()));
                        break;
                    case 3:
                        break;
                    case 4:
                        // game to four players
                        sixArmBoardModel = new SixArmBoardModel(sixArmBoard,4);

                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.MAGENTA, listener.accept()));
                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.CYAN, listener.accept()));
                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.YELLOW, listener.accept()));
                        pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.RED, listener.accept()));


                }
//                 game to two players
//               SixArmBoardModel sixArmBoardModel = new SixArmBoardModel(sixArmBoard,2);
//
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.BLUE, listener.accept()));
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.GREEN, listener.accept()));

                // game to four players
//               SixArmBoardModel sixArmBoardModel = new SixArmBoardModel(sixArmBoard,4);
//
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.MAGENTA, listener.accept()));
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.CYAN, listener.accept()));
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.YELLOW, listener.accept()));
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.RED, listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
