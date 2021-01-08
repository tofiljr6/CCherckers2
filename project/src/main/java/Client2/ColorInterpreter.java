package Client2;

import java.awt.Color;

public class ColorInterpreter {

	
	  public Color interprateColors(String c) {
	    	
		  	  
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
	  }
}
