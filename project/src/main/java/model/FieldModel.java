package model;

public class FieldModel {

	private State state;
	private Colors color;
	
	public FieldModel() {
		
		state= State.FREE;
		color =null;
	}
	
	
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

	//TO DO make it easy to change(diffrent number of players)
	public void synchronizeEnums() {
		
		if(state == State.FREE) {
			color = null;
		} else if (color == null) {
			state = State.FREE;
		}
	}
}
