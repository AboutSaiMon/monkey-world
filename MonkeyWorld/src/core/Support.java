package core;

public class Support{
		
	private int monkeyPosition;
	private int bananasPosition;
	private int boxPosition;
	
	public Support() {
		monkeyPosition = -1;
		bananasPosition = 0;
		boxPosition = 1;
	}

	public int getMonkeyPosition() {
		return monkeyPosition;
	}

	public void setMonkeyPosition(int monkeyPosition) {
		this.monkeyPosition = monkeyPosition;
	}

	public int getBananasPosition() {
		return bananasPosition;
	}

	public void setBananasPosition(int bananasPosition) {
		this.bananasPosition = bananasPosition;
	}

	public int getBoxPosition() {
		return boxPosition;
	}

	public void setBoxPosition(int boxPosition) {
		this.boxPosition = boxPosition;
	}
	
}