package Server2;

import GUI.Coordinates;
import model.*;

import settings.StartingFieldsPosition;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Class represent single player in server site
 * You can use nc to play game without gui
 * The single player represents by thread
 */
public class CCPlayer implements Runnable {
    // reference to game model
    public SixArmBoardModel sixArmBoardModel;
    // current player color
    public Colors color;
    //position on board
    private StartingFieldsPosition startingFieldsPosition;
    // opponents of the current player
    public ArrayList<CCPlayer> opponents;
    // next player after the current one
    public CCPlayer nextPlayer;
    // reference to connection between client server architecture
    Socket socket;
    Scanner input;
     PrintWriter output;
    // hints fields coords
    private ArrayList<Integer> xList = new ArrayList<>();
    private ArrayList<Integer> yList = new ArrayList<>();
    // variable storing info whether the player has on emore move
    private boolean oneMoreMove = false;
    // last clicked x and y coords
    private int xRemember;
    private int yRemember;

    /**
     * Constructor, create the player in server side
     * @param sixArmBoardModel model of board where the game is played
     * @param color player color
     * @param socket in which socker the game is played
     */
    public CCPlayer(SixArmBoardModel sixArmBoardModel, StartingFieldsPosition startingFieldPosition, Socket socket) {
        // initialized variables
    	
    	this.startingFieldsPosition = startingFieldPosition;
        this.sixArmBoardModel = sixArmBoardModel;
        this.color = sixArmBoardModel.getBoard().getColorFromStartingPosition(startingFieldPosition);
        this.socket = socket;
    }

    /**
     * Run threads
     */
    @Override
    public void run() {
        try {
            // setting up game and listen player command
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
       } 
        // todo - remember about other player left case
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

    /**
     * method setting game for player
     * @throws IOException if the server socket is not open
     */
    private void setup() throws IOException {
        // assign the value in client server architecture
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        // greeting player
        output.println("WELCOME " + color.toString());

        // how many player take part in the game
        int numberOfPlayers = sixArmBoardModel.getNumberOfPlayers();

        // depends on number of player take part in game
        // set the opponents for each player
        switch(numberOfPlayers) {
        	case 2:
        	    // the blue "player" has first move
        	    if (startingFieldsPosition == StartingFieldsPosition.TOP) {
        	    	sixArmBoardModel.players.add(this);
                    sixArmBoardModel.setCurrentPlayer(this);
                    output.println("MESSAGE Waiting for opponent to connect");
                } else {
        	        // assign the opponents
                	sixArmBoardModel.players.add(this);
                    sixArmBoardModel.getCurrentPlayer().nextPlayer=this;
                    sixArmBoardModel.getCurrentPlayer().opponents = new ArrayList<CCPlayer>();
                    sixArmBoardModel.getCurrentPlayer().opponents.add(this);

                    this.opponents = new ArrayList<CCPlayer>();
                    this.opponents.add(sixArmBoardModel.getCurrentPlayer());
                    this.nextPlayer = sixArmBoardModel.getCurrentPlayer();
                  
                    int random = new Random().nextInt(2);
                    sixArmBoardModel.setCurrentPlayer(sixArmBoardModel.players.get(random));
                    sixArmBoardModel.getCurrentPlayer().output.println("BEGIN");
                    
                }
        	    break;
        	case 3:
        		
        		if (startingFieldsPosition == StartingFieldsPosition.TOP) {
        	    	sixArmBoardModel.players.add(this);
                    sixArmBoardModel.setCurrentPlayer(this);
                    this.opponents = new ArrayList<CCPlayer>();
                    output.println("MESSAGE Waiting for opponent to connect");
        		}else if (startingFieldsPosition == StartingFieldsPosition.BOTTOM_RIGHT){
        			sixArmBoardModel.players.add(this);
        			this.opponents = new ArrayList<CCPlayer>();
                    sixArmBoardModel.setCurrentPlayer(this);
                } else if(startingFieldsPosition == StartingFieldsPosition.BOTTOM_LEFT){
        	        // assign the opponents
                	sixArmBoardModel.players.add(this);
     	        	this.opponents = new ArrayList<CCPlayer>();

     	        	this.setNextPlayer(sixArmBoardModel.players.get(0));
     	        	this.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(1));
     	        	
     	        	this.nextPlayer.nextPlayer.setNextPlayer(this);

     	        	CCPlayer player = this;
     	        	//adding players to array List of opponents
     	        	for(int j =0; j<3; j++) {
	     	        	
	     	        	CCPlayer playerToAdd = player.nextPlayer;
	     	        	for(int i =0; i < 2 ;i ++) {
	     	        	
	     	        		
	     	        		player.opponents.add(playerToAdd);
	     	        		playerToAdd = playerToAdd.nextPlayer;
	     	        	}
	     	        	player = player.nextPlayer;
     	        	}

                    int random = new Random().nextInt(3);
                    sixArmBoardModel.setCurrentPlayer(sixArmBoardModel.players.get(random));
                    sixArmBoardModel.getCurrentPlayer().output.println("BEGIN");
                    
                }
        	    break;
        		
        		
        	case 4:
                if (startingFieldsPosition == StartingFieldsPosition.BOTTOM_RIGHT) {
                    sixArmBoardModel.setCurrentPlayer(this);
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                    output.println("MESSAGE Waiting for opponent to connect");
     	        } else if (startingFieldsPosition == StartingFieldsPosition.BOTTOM_LEFT){
     	        	sixArmBoardModel.players.add(this);
     	        	this.opponents = new ArrayList<CCPlayer>();
     	        } else if( startingFieldsPosition == StartingFieldsPosition.UPPER_LEFT) {
     	        	sixArmBoardModel.players.add(this);
     	        	this.opponents = new ArrayList<CCPlayer>();
        		}else if(startingFieldsPosition == StartingFieldsPosition.UPPER_RIGHT) {
        			sixArmBoardModel.players.add(this);
     	        	this.opponents = new ArrayList<CCPlayer>();

     	        	this.setNextPlayer(sixArmBoardModel.players.get(0));
     	        	this.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(1));
     	        	this.nextPlayer.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(2));
     	        	this.nextPlayer.nextPlayer.nextPlayer.setNextPlayer(this);

     	        	CCPlayer player = this;
     	        	//adding players to array List of opponents
     	        	for(int j =0; j<4; j++) {
	     	        	
	     	        	CCPlayer playerToAdd = player.nextPlayer;
	     	        	for(int i =0; i < 3 ;i ++) {
	     	        	
	     	        		
	     	        		player.opponents.add(playerToAdd);
	     	        		playerToAdd = playerToAdd.nextPlayer;
	     	        	}
	     	        	player = player.nextPlayer;
     	        	}

                    int random = new Random().nextInt(4);
                    sixArmBoardModel.setCurrentPlayer(sixArmBoardModel.players.get(random));
                    sixArmBoardModel.getCurrentPlayer().output.println("BEGIN");
     	        }
                break;
            case 6:
                if (startingFieldsPosition == StartingFieldsPosition.TOP) {
                    sixArmBoardModel.setCurrentPlayer(this);
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                    output.println("MESSAGE waiting for opponent to connect");
                } else if (startingFieldsPosition == StartingFieldsPosition.UPPER_RIGHT) {
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                } else if (startingFieldsPosition == StartingFieldsPosition.BOTTOM_RIGHT) {
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                } else if (startingFieldsPosition == StartingFieldsPosition.BOTTOM) {
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                } else if (startingFieldsPosition == StartingFieldsPosition.BOTTOM_LEFT) {
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();
                } else if (startingFieldsPosition == StartingFieldsPosition.UPPER_LEFT) {
                    sixArmBoardModel.players.add(this);
                    this.opponents = new ArrayList<CCPlayer>();

                    this.setNextPlayer(sixArmBoardModel.players.get(0));
                    this.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(1));
                    this.nextPlayer.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(2));
                    this.nextPlayer.nextPlayer.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(3));
                    this.nextPlayer.nextPlayer.nextPlayer.nextPlayer.setNextPlayer(sixArmBoardModel.players.get(4));
                    this.nextPlayer.nextPlayer.nextPlayer.nextPlayer.nextPlayer.setNextPlayer(this);

                    CCPlayer player = this;
                    for (int j = 0; j < 6; j++) {
                        CCPlayer playerToAdd = player.nextPlayer;
                        for (int i = 0; i < 5; i++) {
                            player.opponents.add(playerToAdd);
                            playerToAdd = playerToAdd.nextPlayer;
                        }
                        player = player.nextPlayer;
                    }

                    int random = new Random().nextInt(6);
                    sixArmBoardModel.setCurrentPlayer(sixArmBoardModel.players.get(random));
                    sixArmBoardModel.getCurrentPlayer().output.println("BEGIN");

                    
                }

        }
    }

    /**
     * Method listener what client choose in input (what GUI sends to us)
     * Depends what client sends we invoke the method
     */
    private void processCommands() {
        while (input.hasNextLine()) {
        	
            var command = input.nextLine();
            // quit -> the game has ended
            if (command.startsWith("QUIT")) {
                return;
            } else if (command.startsWith("MOVE")) {
                // move -> it is jump 1.0 version, yoou can jump to any destinantion even if it is not correct rules
                // @deprecated
                // only for the beginning of game
                // move command has more params
                String cmd[] = command.split(" ");
                int xLoc = Integer.parseInt(cmd[1]);
                int yLoc = Integer.parseInt(cmd[2]);
                // invoke move command
                processMoveCommand(xLoc, yLoc);
            } else if (command.startsWith("JUMP")) {
                // jump -> jump from one field to another one
                // this jump is common in our game
                // it is correct with the game rules
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                int xEnd = Integer.parseInt(cmd[3]);
                int yEnd = Integer.parseInt(cmd[4]);
                // invoke jump command
                processJumpCommand(xStart, yStart,xEnd, yEnd);
            } else if (command.startsWith("CHOOSE")) {
                // sends to model coords and decided is field is your
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
                // clear the neighborhood coods becuse we choose another field so we have new one neighborhood
                xList.clear();
                yList.clear();
                // clear hints on gui
                output.println("CLEAR_HINTS");
                // invokde info command
                processInfoCommand(xStart, yStart);
            } else if (command.startsWith("SKIP")) {
                // player skip move so invoke command which set next player
                processSkipCommand();
            } else if (command.startsWith("CLICKED")) {
                // which fields player clicked
                // x and y coords
                String cmd[] = command.split(" ");
                int xStart = Integer.parseInt(cmd[1]);
                int yStart = Integer.parseInt(cmd[2]);
//                xClicked = xStart;
//                yClicked = yStart;
            } else if (command.startsWith("AGAIN")) {
                // you have one more move,
                // you previous makcase that decide to hint in to make another one
                String cmd[] = command.split(" ");
                int xEnd = Integer.parseInt(cmd[1]);
                int yEnd = Integer.parseInt(cmd[2]);
                // invoke jump command with special start coords
                // this in all diff between jump method
                processJumpCommand(xRemember, yRemember, xEnd, yEnd);
//                output.println("NO_MOVE_AGAIN"); // unless
            }
        }
    }

    /**
     * Method represent move on board
     * @deprecated we use it only in game 1.0 version
     * this is not neccesary in current game
     * @param xLoc x coords where are we moving
     * @param yLoc y coords where are we moving
     */
    private void processMoveCommand(int xLoc, int yLoc) {
        try {
            // sends tcase that decide to hint insixArmBoardModel.move(xLoc, yLoc, this);
            // we make valid move so sends to the client info
            output.println("VALID_MOVE " + xLoc + " " + yLoc);

            // sends to the oppcase that decide to hint innt player has moved
            for(CCPlayer ccplayer: opponents) {
            	ccplayer.output.println("OPPONENT_MOVED " + xLoc + " " + yLoc);
            }
            // for visualization only - it is unless
//            HashMap<Coordinates, FieldModel> board = sixArmBoardModel.getHashMap();
//            String btest = board.get(new Coordinates(xLoc, yLoc)).getColor().toString();
//
//            output.println("BOARD " + btest);
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    /**
     * the method responsible for the jumping if we have one more move
     * the single jumping method (processJumpCommand) evoke this method
     * @param xStart x coords where we jump from
     * @param yStart y coords where we jump from
     * @param xEnd x coords where we jump to
     * @param yEnd y coords where we jump to
     */
    private void jumpingAllTheTime(int xStart, int yStart, int xEnd, int yEnd) {
        // neighborhood coordinates
        int[] xNeighborhood = {-1, 1,  1, -1, 2, -2};
        int[] yNeighborhood = { 1, 1, -1, -1, 0,  0};

        // the same player has more move
        output.println("ONE_MORE_MOVE");
        // sends communication to client - player
        output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " oneMoreMove");
        processInfoCommand(xEnd, yEnd);

        // choose and print new fields hints
        xList.clear();
        yList.clear();
        output.println("CLEAR_HINTS");

        //case that decide to hint inp have we neighborhood
        // because we should have one more move
        // this is also allow to back move
        boolean surroundings = false;

        // checking all neighborhood pawn
        for (int q = 0; q < xNeighborhood.length; q++) {
            // @see what hints returns
            // 2 means we dont have neighborhood
            if (2 != sixArmBoardModel.hints(xEnd, xNeighborhood[q], yEnd, yNeighborhood[q], this)) {
                surroundings = true;
                // next available fields to jump
                int xm = xEnd + (xNeighborhood[q] * 2);
                int ym = yEnd + (yNeighborhood[q] * 2);
                // sends hints to player and add coords to lists
                output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
                xList.add(xm);
                yList.add(ym);
            }
        }

        if (surroundings) {
            output.println("MOVE_AGAIN");
        } else {
            // set that we dont have one more move, clear previous hints and set next player
            output.println("NO_MOVE_AGAIN");
            output.println("CLEAR_HINTS");
            sixArmBoardModel.setCurrentPlayer(nextPlayer);
        }

        // remember x and y coords to next move
        xRemember = xEnd;
        yRemember = yEnd;

        // sends communication to opponents about player movement
        for (CCPlayer ccplayer : opponents) {
            ccplayer.output.println("OPPONENT_MOVED " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " " +this.color+ " "+ this.nextPlayer.color);
        }
    }

    /**
     * the method is responsible to the single jumping
     * @param xStart x coords where we jump from
     * @param yStart y coords where we jump from
     * @param xEnd x coords where we jump to
     * @param yEnd y coords where we jump to
     */
    private void processJumpCommand(int xStart, int yStart, int xEnd, int yEnd) {
        try {
            // we must check all surrounding field,
            // xList and yList are a coordinates correct fields
            for (int i = 0; i < xList.size(); i++) {
                // if this move (jump) is correct with game rules
                if (xEnd == xList.get(i) && yEnd == yList.get(i)) {
                    // jump to destination
                    sixArmBoardModel.jump(xStart, yStart, xEnd, yEnd, this);

                    // set next player according the movement
                    // if player jump over pawn, he has one more move
                    // else we set next player
                    if (yStart == yEnd && xEnd - xStart == 4) { // right jump now only
                        System.out.println("right jump");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else if (yEnd - yStart == 2 && xEnd - xStart == 2) { // bottom right jump
                        System.out.println("bottom right");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else if (yStart == yEnd && xEnd - xStart == -4) { // left jump
                        System.out.println("left jump");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else if (yEnd - yStart == -2 && xEnd - xStart == -2) { // upper left jump
                        System.out.println("upper left");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else if (xStart - xEnd == 2 && yStart - yEnd == -2) { // bottom left jump
                        System.out.println("Bottom left");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else if (xStart - xEnd == -2 && yStart - yEnd == 2) { // upper right
                        System.out.println("upper right");
                        jumpingAllTheTime(xStart, yStart, xEnd, yEnd);
                    } else {
                        // you not jump in this move so you dont have one more move
                        output.println("NO_MORE_AGAIN");
                        // set new player, current player dont have more move so it is next player time
                        sixArmBoardModel.setCurrentPlayer(nextPlayer);

                        // sends communication to client - player
                        output.println("VALID_MOVE " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " not");
                      
                        // sends communication to client - opponents
                        for (CCPlayer ccplayer : opponents) {
                            ccplayer.output.println("OPPONENT_MOVED " + xStart + " " + yStart + " " + xEnd + " " + yEnd + " " +this.color + " "+ this.nextPlayer.color);
                            System.out.print(ccplayer.color);
                        }
                    }
                }
            }

            
            // winner case
            if (sixArmBoardModel.playerFinished(this, sixArmBoardModel.getHashMap())) {
            	
                output.println("CONGRATULATION you've finished "+ " " +sixArmBoardModel.getPlaceOfFinishedPlayer());

                // winner after move dont have any extra move, it is time for next player
                output.println("CLEAR_HINTS");
                xList.clear();
                yList.clear();
                output.println("CLEAN_LISTS");
                //output.println("NO_MOVE_AGAIN");
             
                sixArmBoardModel.skip(this);

                //winner is not longer in game,
                //his next opponent is set as next player of player before winner
                for (CCPlayer ccplayer : opponents) {
                    if(ccplayer.nextPlayer.equals(this)) {
                    	ccplayer.setNextPlayer(this.nextPlayer);
                    }
                }

                //for every opponent delete winner in opponents arraylists of opponents
                for (CCPlayer ccplayer : opponents) {
                	ccplayer.opponents.remove(this);
                }
            }

        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
      
    }

    /**
     * Method is responsible tocase that decide to hint inield is our field
     * and sends to the model info and wait where we can move
     * hints to the current field
     * @param xStart x coords what we choose
     * @param yStart y coords what we choose
     */
    private void processInfoCommand(int xStart, int yStart) {
        try {
//            sixArmBoardModel.hints(xStart, yStart, this);
            // clear all neighborhood coords
            xList.clear();
            yList.clear();

            // aks mocase that decide to hint in have chosen is your
            if (sixArmBoardModel.choose(xStart, yStart, this)) {
                // yes, chosen field is your so confirm move
                output.println("CONFIRM_MOVE " + xStart + " " + yStart);

                // hints logic starts here
                int[] xNeighborhood = {-1, 1,  1, -1, 2, -2};
                int[] yNeighborhood = { 1, 1, -1, -1, 0,  0};

                // for each direction we sends hints
                for (int i = 0; i < xNeighborhood.length; i++) {
                    // see what hints method returns
                    int decision = sixArmBoardModel.hints(xStart, xNeighborhood[i], yStart, yNeighborhood[i], this);
                    // init var and assign imposbile value (-1 is never reach beacuse the minimimu value is 0)
                    int xm = -1;
                    int ym = -1;

                    // depends what direction we now decide we go to the right case
                    // 2 -> return true info - > normal move - not jump over opponent
                    // 3 -> case that decide to hint in right R
                    // 4 -> case that decide to hint in left L
                    // 5 -> case that decide to hint in upper right UR
                    // 6 -> case that decide to hint in bottom right BR
                    // 7 -> case that decide to hint in bottom left BL
                    // 8 -> case that decide to hint in upper left UL
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
                            output.println("HINT_TO " + xm + " " + ym + " " + sixArmBoardModel.getHashMapCordColor(xm ,ym));
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

    /**
     * Method is responsible to skip move if the palyer what to skip
     */
    private void processSkipCommand() {
        try {
            // sends to model that the current player skip
            sixArmBoardModel.skip(this);

            // sends communication to player that he skipped
            output.println("YOU_SKIPPED");

            // clear its neighborhood coords and sends to gui that repaint is to black again
            xList.clear();
            yList.clear();
            output.println("CLEAR_HINTS");
            output.println("CLEAN_LISTS");
            output.println("NO_MOVE_AGAIN");

            // sends to other player communication that he skipped
            for(CCPlayer ccplayer: opponents) {
            	ccplayer.output.println("OPPONENT_SKIP " + this.nextPlayer.color);
            }
            
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }

    /**
     * set next player
     * @param player what next player is
     */
    private void setNextPlayer(CCPlayer player) {
    	this.nextPlayer = player;
    }
    public StartingFieldsPosition getStartingFieldPosition(){
    	
    	return startingFieldsPosition;
    }
}
