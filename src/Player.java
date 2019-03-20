/**
 * Player <br>
 * A generic player of the konane game
 * 
 * @author James Gettinger
 * 
 */
public abstract class Player {

	protected boolean colour;
	/**
	 * Default constructor
	 * 
	 * @param colour
	 *            the player's colour
	 */
	public Player(boolean colour) {
		this.colour = colour;
	}


	/**
	 * Function to prompt the player to make a move after the first move has
	 * already been made
	 * 
	 * @param b
	 *            the board to parse
	 * @return the selected move
	 */
	public Move getNextMove(Board b) {
		return null;
	}

	public boolean getColour() {
		return colour;
	}

	public void setColour(boolean colour) {
		this.colour = colour;
	}
}
