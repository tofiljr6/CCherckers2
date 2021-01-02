package Client2;

import java.awt.Color;

public class ColorInterpreter {

	
	private int numberOfPlayers;
	
	public ColorInterpreter(int numberOfPlayers) {
		
		 
		this.numberOfPlayers = numberOfPlayers;
	}
	
	
	  public Color interprateColors(String c) {
	    	
		  
		  switch(numberOfPlayers) {
		  
		  	case 2:
			  
			  switch(c) {
		    	
	    		case "GREEN":
	    			return Color.green;
	    		case "BLUE":
	    			return Color.blue;
	    		default:
	    			return null;
	    	}
			  
		  	case 4:
		  		switch(c) {
	    		case "RED":
	    			return Color.red;
	    		case "CYAN":
	    			return Color.cyan;
	    		case "YELLOW":
	    			return Color.yellow;
	    		case "MAGENTA":
	    			return Color.magenta;
	    		default:
	    			return null;
		  	}
		  
		  case 6:
			  switch(c) {
		    	
	  		case "GREEN":
	  			return Color.green;
	  		case "BLUE":
	  			return Color.blue;
	  		case "RED":
	  			return Color.red;
	  		case "CYAN":
	  			return Color.cyan;
	  		case "YELLOW":
	  			return Color.yellow;
	  		case "MAGENTA":
	  			return Color.magenta;
	  		default:
	  			return null;
			  }
		  default:
			  return null;
	    }
	  }
}
