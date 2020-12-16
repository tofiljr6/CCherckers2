package Server;

import model.Color;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class ChineseCheckersServer {

    public static void main(String[] args) {
        try(var listener = new ServerSocket(59001)) {
            System.out.println("Chinese Checkers Server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                Game game = new Game();

                pool.execute(new Player(game, 'X', 1, listener.accept()));
                pool.execute(new Player(game, 'O', 2, listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}