package model;

public class FieldModel {

	private State state;
	private Colors color;
	
	public FieldModel() {
		
		state= State.FREE;
	}
	
	
	public boolean isTaken(){
		
		return this.state==State.TAKEN;
	}
	
	public void setFieldColor(Colors color) {
		this.state = State.TAKEN;
		this.color =color;
	}
	
	public void setFieldFree() {
		this.state = State.FREE;
		synchronizeEnums();
	}
	
	//TO DO make it easy to change(diffrent number of players)
	public void synchronizeEnums() {
		
		if(state == State.FREE) {
			color = ColorsFor2Players.NULL;
		}
	}
}
