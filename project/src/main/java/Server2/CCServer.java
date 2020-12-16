package Server2;

import Server.Player;
import model.State;
import model.Color;

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
                CCGame ccGame = new CCGame();

                pool.execute(new CCPlayer(ccGame, Color.BLUE, listener.accept()));
                pool.execute(new CCPlayer(ccGame, Color.RED, listener.accept()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
