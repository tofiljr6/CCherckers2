//package Server2;
//
//import Client2.CCLient;
//import Server2.CCServer;
//import model.ColorsFor2Players;
//import model.SixArmBoardModel;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import settings.SixArmBoard;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.util.concurrent.Executors;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//
//public class CCPlayerTEST {
//
//    public static CCPlayer ccPlayerGREEN;
//    public static CCPlayer ccPlayerBLUE;
//    public static ServerSocket listener;
//    public static SixArmBoard sixArmBoard;
//    public static SixArmBoardModel sixArmBoardModel;
//    public static CCLient ccLient1;
//    public static CCLient ccLient2;
//    public static CCServer server;
//
//
////        try {
////            listener = new ServerSocket(59001);
////            System.out.println("CCServer 2.0 is running...");
////            var pool = Executors.newFixedThreadPool(20);
////            while (true) {
////                sixArmBoard = new SixArmBoard();
////                sixArmBoardModel = new SixArmBoardModel(sixArmBoard, 2);
////                ccPlayerGREEN = new CCPlayer(sixArmBoardModel, ColorsFor2Players.GREEN, listener.accept());
////                ccPlayerBLUE = new CCPlayer(sixArmBoardModel, ColorsFor2Players.BLUE, listener.accept());
////
////                pool.execute(ccPlayerGREEN);
////                pool.execute(ccPlayerBLUE);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        CCServer server = new CCServer();
////    }
//
////    @BeforeClass
////    public static void initAll() throws IOException {
////        listener = new ServerSocket(59001);
////        sixArmBoard = new SixArmBoard();
////        sixArmBoardModel = new SixArmBoardModel(sixArmBoard, 2);
////    }
////
//    @BeforeClass
//    public static void client2Start() throws Exception {
//        System.out.println("Client2 is running");
//        ccLient1 = new CCLient("localhost", 2);
//    }
//
//    @BeforeClass
//    public static void client1Start() throws Exception {
//        System.out.println("Client1 is running");
//        ccLient1 = new CCLient("localhost", 2);
//    }
//
//    @BeforeClass
//    public static void serverStart() throws IOException {
//        System.out.println("Server is running");
//        server = new CCServer();
//        server.start();
//    }
//
//    @Test
//    public void testGreenColor() {
////        assertEquals(sixArmBoard, ccLient1.board);
//        assertEquals("G","G");
//        assertNotNull(server);
////        assertNotNull(server.getSixArmBoardModel());
////        assertEquals(server.getSixArmBoardModel(), sixArmBoardModel);
////        assertEquals(ccLient1.board.toString(), server.sixArmBoard.toString());
////        assertNotNull(ccLient1.g);
////        assertEquals("GREEN" ,ccPlayerGREEN.color.toString());
//    }
//}
