package tiger_zone;

/**
 * The <code>Position</code> class represents a position on the <code>Board</code>. This is a "value object", which
 * means it is completely immutable and equality between Positions is determined solely by their (x, y) values.
 */
public class Position {
	private final int x;
	private final int y;

	/**
	 * Creates a new instance of <code>Position</code>.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate of this position.
	 *
	 * @return the x coordinate
	 */
	public final int getX() {
		return this.x;
	}

	/**
	 * Returns the y coordinate of this position.
	 *
	 * @return the y coordinate
	 */
	public final int getY() {
		return this.y;
	}
	
	/**
	 * Returns the Position that is just north of this Position.
	 * 
	 * @return north position
	 */
	public final Position north() {
		return new Position(this.x, this.y + 1);
	}
	
	/**
	 * Returns the Position that is just east of this Position.
	 * 
	 * @return east position
	 */
	public final Position east() {
		return new Position(this.x + 1, this.y);
	}
	
	/**
	 * Returns the Position that is just south of this Position.
	 * 
	 * @return south position
	 */
	public final Position south() {
		return new Position(this.x, this.y - 1);
	}
	
	/**
	 * Returns the Position that is just west of this Position.
	 * 
	 * @return west position
	 */
	public final Position west() {
		return new Position(this.x - 1, this.y);
	}
	
	/**
	 * Auto-generated by Eclipse; this function performs a one-to-one mapping from an (x, y) pair (which Position is) to
	 * a unique value. This is absolutely necessary for storing Positions in HashMaps and HashSets. Without it, two
	 * Positions with the same x and y value may generate different hashCodes (and thus, appear as different things in
	 * the Map or Set).
	 * 
	 * @see java.lang.Object#hashCode()
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
	 * Returns true if P is a Position with equal (x, y) values to this Position. Otherwise, returns false.
	 * 
	 * @param p Object to compare.
	 * @return true if equal, false otherwise
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object p) {
		return p instanceof Position && this.x == ((Position)p).getX() && this.y == ((Position)p).getY();
	}
}
