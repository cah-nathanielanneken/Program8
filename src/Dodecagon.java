import java.awt.Color;

/**
 * A class that represents a twelve sided dodecagon, its location, and its color
 * 
 * @author Nathan Anneken
 * 
 */
public class Dodecagon extends Shape {
	/**
	 * Constructs a Dodecagon with a given bounding box and color.
	 * 
	 * @param x
	 *            x-coordinate of the bounding box' upper-left corner
	 * @param y
	 *            y-coordinate of the bounding box' upper-left corner
	 * @param width
	 *            width of the bounding box
	 * @param height
	 *            height of the bounding box
	 * @param color
	 *            color of the shape
	 */
	public Dodecagon(int xPos, int yPos, int newWidth, int newHeight,
			Color newColor) {
		super(xPos, yPos, newWidth, newHeight, newColor);
	}

	/**
	 * Creates a dodecagon that is a copy of this dodecagon, copying its color
	 * and all the details of its bounding box.
	 * 
	 * @param p
	 *            the dodecagon that is to be copied
	 */
	public Dodecagon(Dodecagon p) {
		super(p);
	}

	/**
	 * Returns a String representation of this shape, by putting the shape type
	 * in front of the inherited bounding box and color information
	 * 
	 * @see Shape#toString()
	 */
	public String toString() {
		return "Dodecagon " + super.toString();
	}

	/**
	 * Returns true if the parameter is the same type of shape, and everything
	 * else matches from the parent class check
	 * 
	 * @see Shape#equals(java.lang.Object)
	 */
	public boolean equals(Object that) {
		if (that instanceof Dodecagon) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Gets an array of points representing the vertices of this shape.
	 * 
	 * @return an array of points representing the vertices of this shape.
	 */
	public Point[] getVertices() {
		Point[] result = new Point[12];

		int x = getX();
		int y = getY();
		int h = getHeight();
		int w = getWidth();

		result[0] = new Point(x + w / 2, y);
		result[1] = new Point(x + w * 9 / 12, y + h * 1 / 12);
		result[2] = new Point(x + w * 11 / 12, y + h * 3 / 12);
		result[3] = new Point(x + w, y + h / 2);
		result[4] = new Point(x + w * 11 / 12, y + h * 9 / 12);
		result[5] = new Point(x + w * 9 / 12, y + h * 11 / 12);
		result[6] = new Point(x + w / 2, y + h);
		result[7] = new Point(x + w * 3 / 12, y + h * 11 / 12);
		result[8] = new Point(x + w * 1 / 12, y + h * 9 / 12);
		result[9] = new Point(x, y + h / 2);
		result[10] = new Point(x + w * 1 / 12, y + h * 3 / 12);
		result[11] = new Point(x + w * 3 / 12, y + h * 1 / 12);

		return result;
	}

}
