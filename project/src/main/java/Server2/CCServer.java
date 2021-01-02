package Server2;

import Server.Player;
import model.ColorsFor2Players;
import model.ColorsFor4Players;
import model.SixArmBoardModel;
import model.State;
import settings.Board;
import settings.SixArmBoard;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class CCServer {
    public static void main(String[] args) {
        try (var listener = new ServerSocket(59001)) {
            System.out.println("CCServer 2.0 is running...");
            var pool = Executors.newFixedThreadPool(20);

            while (true) {
//                CCGame ccGame = new CCGame();
                SixArmBoard sixArmBoard = new SixArmBoard();

                // game to two players
//               SixArmBoardModel sixArmBoardModel = new SixArmBoardModel(sixArmBoard,2);
//
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.BLUE, listener.accept()));
//                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor2Players.GREEN, listener.accept()));

                // game to four players
               SixArmBoardModel sixArmBoardModel = new SixArmBoardModel(sixArmBoard,4);

                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.MAGENTA, listener.accept()));
                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.CYAN, listener.accept()));
                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.YELLOW, listener.accept()));
                pool.execute(new CCPlayer(sixArmBoardModel, ColorsFor4Players.RED, listener.accept()));


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
