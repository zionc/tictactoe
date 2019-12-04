package ticTacToeAssignment;



public class TttCell {
	private char character;
	private boolean isEmpty;
	
	public TttCell(char character, boolean isEmpty) {
		this.character = character;
		this.isEmpty = isEmpty;
		
	}
	
	public TttCell() {
		this(' ',true);
	}
	
	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public boolean getIsEmpty() {
		return isEmpty;
	}
	
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(obj.getClass() == this.getClass()) {
			TttCell cell = (TttCell) obj;
			return cell.getCharacter() == this.getCharacter() && cell.getIsEmpty() == this.getIsEmpty();
		}
		return false;
	}
	
	public String toString() {
		return "Cell: " + character + " isEmpty: " + isEmpty;
	}
	

}
