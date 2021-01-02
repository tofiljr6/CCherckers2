package Server2;

import GUI.Coordinates;
import model.Colors;
import model.ColorsFor2Players;
import model.ColorsFor4Players;
import model.FieldModel;
import model.SixArmBoardModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CCPlayer implements Runnable {
    public SixArmBoardModel sixArmBoardModel;
    public Colors color;
    public ArrayList<CCPlayer> opponents;
    public CCPlayer nextPlayer;
    Socket socket;
    Scanner input;
    PrintWriter output;
    // hints fields coords
    private ArrayList<Integer> xList = new ArrayList<>();
    private ArrayList<Integer> yList = new ArrayList<>();
    private boolean oneMoreMove = false;
    private int xClicked;
    private int yClicked;

    public CCPlayer(SixArmBoardModel sixArmBoardModel, Colors color, Socket socket) {
        this.sixArmBoardModel = sixArmBoardModel;
        this.color = color;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
       } 
        //TO-DO
			finally {
//				if (opponents.get(0) != null && opponents.get(0).output != null) {
//	            opponents.get(0).output.println("OTHER_PLAYER_LEFT");
//           }

           try {
               socket.close();
           } catch (IOException e) {

           }
        }
    }


    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + color.toString());
        
        int numberOfPlayers = sixArmBoardModel.getNumberOfPlayers();
        
        
        switch(numberOfPlayers) {
        	case 2:
        		
        		 if (color == ColorsFor2Players.BLUE) {
        	            sixArmBoardModel.setCurrentPlayer(this);
        	            output.println("MESSAGE Waiting for opponent to connect");
        	        } else {
        	        	sixArmBoardModel.getCurrentPlayer().nextPlayer=this;
        	        	sixArmBoardModel.getCurrentPlayer().opponents = new ArrayList<CCPlayer>();
        	        	sixArmBoardModel.getCurrentPlayer().opponents.add(this);
        	        	
        	        	
        	            this.opponents = new ArrayList<CCPlayer>();
        	            this.opponents.add(sixArmBoardModel.getCurrentPlayer());
        	            this.nextPlayer = sixArmBoardModel.getCurrentPlayer();
        	         
        	            this.nextPlayer.output.println("MESSAGE your move");
        	        }
        		 break;
        		 
        	case 4:
        		 if (color == ColorsFor4Players.CYAN) {
     	            sixArmBoardModel.setCurrentPlayer(this);
     	            this.opponents = new ArrayList<CCPlayer>();
     	            output.println("MESSAGE Waiting for opponent to connect");
     	        } else if (color == ColorsFor4Players.RED){
     	        	
     	        	
     	        	sixArmBoardModel.getCurrentPlayer().nextPlayer=this;
     	        	sixArmBoardModel.setCurrentPlayer(this);
     	        	
     	        	this.opponents = new ArrayList<CCPlayer>();
     	        	 
     	        } else if( color == ColorsFor4Players.YELLOW) {
     	        	
     	        	sixArmBoardModel.getCurrentPlayer().nextPlayer=this;
     	        	sixArmBoardModel.setCurrentPlayer(this);
     	        	
     	        	this.opponents = new ArrayList<CCPlayer>();
     	        	
        		}else if(color == ColorsFor4Players.MAGENTA) {
        			
        			sixArmBoardModel.getCurrentPlayer().nextPlayer=this;
     	        	sixArmBoardModel.setCurrentPlayer(this);
        			
     	        	sixArmBoardModel.getCurrentPlayer().nextPlayer.nextPlayer.nextPlayer.nextPlayer=this;
     	        	
     	        	this.opponents = new ArrayList<CCPlayer>();
     	        	this.opponents.add(this.nextPlayer);
     	        	this.opponents.add(this.nextPlayer.nextPlayer);
     	        	this.opponents.add(this.nextPlayer.nextPlayer.nextPlayer);
     	        	
     	        	CCPlayer player = this.nextPlayer;
     	        	//adding players to array List of opponents
     	        	for(int j =0; j<4; j++) {
	     	        	
	     	        	CCPlayer playerToAdd = player.nextPlayer;
	     	        	for(int i =0; i < 4 ;i ++) {
	     	        	
	     	        		
	     	        		player.opponents.add(playerToAdd);
	     	        		playerToAdd = playerToAdd.nextPlayer;
	     	        	}
	     	        	player = player.nextPlayer;
     	        	}
     	        	
     	            this.nextPlayer.output.println("MESSAGE your move");
     	        }
     		 break;
        } 	
        
      
        
    }

    private void processCommands() {
        while (input.hasNextLine()) {
        	
            var command = input.nextLine();
            if (command.startsWith("QUIT")) {
                return;
            } else if (command.startsWith("MOVE")) {

                String cmd[] = command.split(" ");
                int xLoc = Integer.parseInt(cmd[1]);
                int yLoc = Integer.parseInt(cmd[2]);
                processMoveCommand(xLoc, yLoc);
            } else if (command.startsWith("JUMP")) {

                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                int xEnd = Integer.parseInt(cmd[3]);
                int yEnd = Integer.parseInt(cmd[4]);

                processJumpCommand(xStart, yStart,xEnd, yEnd);
            } else if (command.startsWith("CHOOSE")) {
                // sends to model coords and decided is field is your
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                xList.clear();
                yList.clear();
                output.println("CLEAR_HINTS");
                processInfoCommand(xStart, yStart);
            } else if (command.startsWith("SKIP")) {
                processSkipCommand();
            } else if (command.startsWith("CLICKED")) {
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                xClicked = xStart;
                yClicked = yStart;
            }
        }
    }

    private void processMoveCommand(int xLoc, int yLoc) {
        try {
            sixArmBoardModel.move(xLoc, yLoc, this);
            output.println("VALID_MOVE " + xLoc + " " + yLoc);
            
            for(CCPlayer ccplayer: opponents) {
            
            	ccplayer.output.println("OPPONENT_MOVED " + xLoc + " " + yLoc);
            }
            
            if (sixArmBoardModel.hasWinner()) {
                output.println("VICTORY");
                
                for(CCPlayer ccplayer: opponents) {
                    
                	ccplayer.output.println("DEFEAT");
                }
               
            }

//            output.println("BOARD" + Arrays.deepToString(sixArmBoardModel.states) + "\n"
//            + Arrays.deepToString(sixArmBoardModel.colors)); // todo finish to check

            HashMap<Coordinates, FieldModel> board = sixArmBoardModel.getHashMap();
            String btest = board.get(new Coordinates(xLoc, yLoc)).getColor().toString();

            output.println("BOARD " + btest);


        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    private void processJumpCommand(int xStart, int yStart, int xEnd, int yEnd) {
        try {
            // we must check all surrounding field,
            // xList and yList are a coordinates correct fields
            for (int i = 0; i < xList.size(); i++) {
                // if this move (jump) is correct with game rules
                if (xEnd == xList.get(i) && yEnd == yList.get(i)) {
                    // jump to destination
                    sixArmBoardModel.jump(xStart, yStart, xEnd, yEnd, this);


                    // set next player
                    if (yStart == yEnd && xEnd - xStart == 4) { // right jump now only
                        // the same player has more move
                        output.println("ONE_MORE_MOVE");
                        // sends communication to client - player
                        output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " oneMoreMove");
//                        // new hints - where you can now jump
//                        processInfoCommand(xEnd, yEnd);

                        // choose and print new fields hints
                        xList.clear();
                        yList.clear();
                        output.println("CLEAR_HINTS");
                        processInfoCommand(xEnd, yEnd);


//                        output.println("CONFIRM_MOVE " + xEnd + " " + yEnd);
//
//                        for (int j = 0; j < xList.size(); j++) {
//                            if (xList.get(j) == xClicked && yList.get(j) == yClicked) {
//                                processJumpCommand(xEnd, yEnd, xClicked, yClicked);
//                            }
//                        }

//                        var command = input.nextLine();
//                        if (command.startsWith("CHOOSE")) {
//                            String cmd[] = command.split(" ");
//                            int xStart2 = Integer.parseInt(cmd[1]);
//                            int yStart2 = Integer.parseInt(cmd[2]);
//                            processInfoCommand(xStart2, yStart2);
//                        }

                    } else {
                        // set new player, current player dont have more move so it is next player time
                        sixArmBoardModel.setCurrentPlayer(nextPlayer);
                        // sends communication to client - player
                        output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " not");
                    }

//                    // sends communication to client - player
//                    output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd + " " + yEnd);

                    // sends communication to client - opponents
                    for (CCPlayer ccplayer : opponents) {
                        ccplayer.output.println("OPPONENT_MOVED " + xStart + " " + yStart + " " + xEnd + " " + yEnd);
                    }

                    // winner case
                    if (sixArmBoardModel.hasWinner()) {
                        output.println("VICTORY");

                        for (CCPlayer ccplayer : opponents) {
                            ccplayer.output.println("DEFEAT");
                        }
                    }
                }
            }

//            output.println("BOARD" + Arrays.deepToString(sixArmBoardModel.states) + "\n"
//            + Arrays.deepToString(sixArmBoardModel.colors)); // todo finish to check

//            HashMap<Coordinates, FieldModel> board = sixArmBoardModel.getHashMap();
//            String btest1 = board.get(new Coordinates(xStart, yStart)).getColor().toString();
//            String btest2 = board.get(new Coordinates(xEnd, yEnd)).getColor().toString();
//            output.println("BOARD " + btest1+ " " +btest2);

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    private void processInfoCommand(int xStart, int yStart) {
        try {

//            sixArmBoardModel.hints(xStart, yStart, this);

            xList.clear();
            yList.clear();

            // aks model is that field you have chosen is your
            if (sixArmBoardModel.choose(xStart, yStart, this)) {
                // yes, chosen field is your so confirm move
            	
                output.println("CONFIRM_MOVE " + xStart + " " + yStart);

                // hints logic starts here
                int[] xNeighborhood = {-1, 1,  1, -1, 2, -2};
                int[] yNeighborhood = { 1, 1, -1, -1, 0,  0};

                for (int i = 0; i < xNeighborhood.length; i++) {
                    // see what hints method returns
                    int decision = sixArmBoardModel.hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], this);
                    int xm = -1;
                    int ym = -1;

                    switch (decision) {
                        case 2:
                            xm = xStart + xNeighborhood[i];
                            ym = yStart + yNeighborhood[i];
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 3:
                            xm = xStart + xNeighborhood[i] + 2;
                            ym = yStart + yNeighborhood[i];
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));

//                            xm = xStart + xNeighborhood[i] + 2;
////                            ym = yStart + yNeighborhood[i];
//                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
//                            processInfoCommand(xm, ym);

//                            output.println("HINT_TO " + 0 + " " + 12 + " " + sixArmBoardModel.getHashMapCordColor(0 ,12));

                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 4:
                            xm = xStart + xNeighborhood[i] - 2;
                            ym = yStart + yNeighborhood[i];
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 5:
                            xm = xStart + xNeighborhood[i] - 1;
                            ym = yStart + yNeighborhood[i] + 1;
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 6:
                            xm = xStart + xNeighborhood[i] + 1;
                            ym = yStart + yNeighborhood[i] + 1;
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 7:
                            xm = xStart + xNeighborhood[i] + 1;
                            ym = yStart + yNeighborhood[i] - 1;
//                            if (3 != sixArmBoardModel.getHashMapCordColor(xm, ym)) { // why it works?
                                output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
//                            }
                            xList.add(xm);
                            yList.add(ym);
                            break;
                        case 8:
                            xm = xStart + xNeighborhood[i] - 1;
                            ym = yStart + yNeighborhood[i] - 1;
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                            xList.add(xm);
                            yList.add(ym);
                            break;
                    }

                }
            }
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }


    private void processSkipCommand() {
        try {
            sixArmBoardModel.skip(this);

            output.println("YOU_SKIPPED");
      

            for(CCPlayer ccplayer: opponents) {
                
            	ccplayer.output.println("OPPONENT_SKIP");
            }
            
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }
}
