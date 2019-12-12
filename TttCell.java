package ticTacToeAssignment;

/**
 * Cell class represents a letter on a Tic-Tac-Toe grid. Cell can 
 * only have up to one letter and if it does not have a letter, Cell
 * is set as empty.
 * @author zionchilagan
 *
 */

public class TttCell {
	/** Character of cell */
	private char character;
	/** boolean value determining if a cell is empty */
	private boolean isEmpty;
	
	/**
	 * Constructs a Cell with a character and value to 
	 * determine if it is empty.
	 * @param character - Character for the Cell
	 * @param isEmpty - Value to determine if a Cell is empty
	 */
	public TttCell(char character, boolean isEmpty) {
		this.character = character;
		this.isEmpty = isEmpty;
		
	}
	
	/**
	 * Creates no-arg Cell that contains no character
	 * and is set to emtpy.
	 */
	public TttCell() {
		this(' ',true);
	}
	
	/**
	 * Get the character for the cell
	 * @return - character for the cell
	 */
	public char getCharacter() {
		return character;
	}
	
	/**
	 * Set the character for the Cell, if the character
	 * is not a space then the Cell is marked as not empty.
	 * @param character - character to set for the Cell
	 */
	public void setCharacter(char character) {
		this.character = character;
		if(character != ' ') {
			isEmpty = false;
		}
	}
	
	/**
	 * Get whether or not a Cell is empty
	 * @return
	 */
	public boolean getIsEmpty() {
		return isEmpty;
	}
	
	/**
	 * Set the value of whether or not a Cell is empty explicitly. 
	 * @param isEmpty - value to determine if a Cell is empty
	 */
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	/**
	 * Overridden method that evaluates Object and this Class for equality
	 */
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
	
	/**
	 * Overridden method that returns a String representation of Cell object
	 */
	@Override
	public String toString() {
		return "Cell: " + character + " isEmpty: " + isEmpty;
	}
	

}
