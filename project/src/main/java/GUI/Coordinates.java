package GUI;
/**
 * class which defines coordinates on board
 * @author dim
 *
 */
public class Coordinates {
	
	private int x;
	private int y;
	
	
	/**
	 * 
	 * constructor
	 * @param x
	 * @param y
	 */
	public Coordinates(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * overriding hashcode to be able to put it in hashmap
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	/**
	 *overrinding equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	public String toString() {
		
		return x+ " " +y;
	}
	
}
