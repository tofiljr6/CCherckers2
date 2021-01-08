package model;

public class FieldModel {
	// state of field
	private State state;
	//field color
	private Colors color;
	
	/**
	 * construcotr
	 */
	public FieldModel() {
		
		state= State.FREE;
		color =PawnColors.NULL;
	}
	
	/**
	 * method which tells if field is taken
	 * @return field state
	 */
	public boolean isTaken(){
		
		return this.state==State.TAKEN;
	}
	
	public void setFieldColor(Colors color) {
		this.state = State.TAKEN;
		this.color =color;
		synchronizeEnums();
	}

	public void setFieldColorHint(Colors color) {
		this.color = color;
		synchronizeEnums();
	}
	
	public void setFieldFree() {
		this.state = State.FREE;
		synchronizeEnums();
	}

	public State getState() {
		return state;
	}

	public Colors getColor() {
		return color;
	}

	/**
	 * 
	 * method which symnchrnize state and color
	 * allows avoiding situtations as:
	 * state=Free
	 * color= red
	 */
	public void synchronizeEnums() {
		
		if(state == State.FREE) {
			color = PawnColors.NULL;
		} else if (color == PawnColors.NULL) {
			state = State.FREE;
		}
	}
}
