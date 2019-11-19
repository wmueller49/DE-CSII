import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *      @author Mrs. Kelly
 */
/**
 * Defines a generic shape for others to extend. Will eventually be re-written
 * as an interface
 */


public class LongFile {

	/**
	 * height and width for the shape
	 */

	private Dimension dimensions;

	/**
	 * top left corner of the shape footprint
	 */
	private Point location;

	/**
	 * line color for the shape
	 */
	private Color color;

	/**
	 * creates a yellow shape, of unit size in the top left corner of the
	 * window
	 */

	public Shape() {
		defaultPoint();
		defaultDimensions();
		defaultColor();
	}

	/**
	 * creates a yellow shape, of unit size in the specified location
	 * 
	 * @param p top left corner for shape
	 */
	Shape(Point p) {
		location = p;
		defaultDimensions();
		defaultColor();
	}

	/**
	 * creates a yellow shape of the specified size in the specified height and width
	 * 
	 * @param height height of the shape
	 * @param width width of the shape 
	 */
	Shape(int height, int width) {
		defaultPoint();
		setDimensions(height, width);
		defaultColor();
	}

	/**
	 * creates a yellow shape of the specified size in the specified location
	 * 
	 * @param x starting point, x coordinate
	 * @param y starting point, y coordinate
	 * @param height height of the shape
	 * @param width width of the shape
	 */
	Shape(int x, int y, int height, int width) {
		setLocation(x, y);
		setDimensions(height, width);
		defaultColor();
	}

	/**
	 * creates a yellow shape of the specified size in the specified location
	 * 
	 * @param loc top left corner of the shape
	 * @param height height of the shape
	 * @param width width of the shape
 	 */
	Shape(Point loc, int height, int width) {
		location = loc;
		setDimensions(height, width);
		defaultColor();
	}

	/**
	 * creates a yellow shape of the specified size in the specified location
	 * 
	 * @param loc top left corner of the shape
	 * @param d height and width of the shape
	 */
	Shape(Point loc, Dimension d) {
		location = loc;
		dimensions = d;
	}

	// Additional Constructors to handle color

	/**

	 * creates a unit-sized shape of the specified color in the specified location
	 * @param p top left corner of the shape
	 * @param c color of the shape
	 * 	 */
	Shape(Point p, Color c) {
		location = p;
		defaultDimensions();
		color = c;
	}

	/**
	 * creates a shape of the specified shape and color in the top left corner
	 * 
	 * @param height height of the shape
	 * @param width width of the shape
	 * @param color color of the shape
	 */
	Shape(int height, int width, Color color) {
		defaultPoint();
		setDimensions(height, width);
		this.color = color;
	}

	/**
	 * creates a shape of the specified shape and color in the specified location
	 * 
	 * @param x x coordinate of the top left corner of the shape
	 * @param y y coordinate of the top left corner of the shape
	 * @param height height of the shape
	 * @param width width of the shape
	 * @param c color of the shape
	 */
	Shape(int x, int y, int height, int width, Color c) {
		setLocation(x, y);
		setDimensions(height, width);
		color = c;
	}

	/**
	 * creates a shape of the specified color in the specified location of the specified height and width
	 * 
	 * @param loc top left corner of the shape
	 * @param height height of the shape
	 * @param width width of the shape
	 * @param c color of the shape
	 */
	Shape(Point loc, int height, int width, Color c) {
		location = loc;
		setDimensions(height, width);
		color = c;
	}

	/**
	 * 	creates a shape of the specified color and height and width in the top left corner
	 * 
	 * @param d height and width of the shape
	 * @param c color of the shape
	 */
	Shape(Dimension d, Color c) {
		defaultPoint();
		dimensions = d;
		color = c;
	}

	/**
	 * creates a shape of the specified color in the specified location of the specified height and width
	 * 
	 * @param loc top left corner of the shape
	 * @param d height and width of the shape
	 * @param c color of the shape
	 */
	Shape(Point loc, Dimension d, Color c) {
		location = loc;
		dimensions = d;
		color = c;
	}

	/**
	 * returns the height and width for the shape
	 * 
	 * @return the dimensions of the shape
	 */
	public Dimension getDimensions() {
		return dimensions;
	}

	/**
	 * changes the height and width of the shape
	 * 
	 * @param dimensions the new dimensions of the shape to set
	 */
	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}

	/**
	 * changes the height and width of the shape
	 * 
	 * @param height new height for the shape
	 * @param width new weight for the shape
	 */
	public void setDimensions(int height, int width) {
		dimensions = new Dimension(height, width);
	}

	/**
	 * returns the top left corner of the shape
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * sets the top left corner of the shape
	 * 
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * sets the top left corner of the shape
	 * 
	 * @param x the x location of the top left corner of the shape to set
	 * @param y the y location of the top left corner of the shape to set
	 */
	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}

	/**
	 * returns the color of the shape
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * sets the color of the shape
	 * 
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * sets the top left corner of the shape to (0, 0)
	 */
	private void defaultPoint() {
		setLocation(new Point(0, 0));
	}

	/**
	 * sets the height and width of the shape to 1
	 */
	private void defaultDimensions() {
		setDimensions(new Dimension(1, 1));
	}

	/**
	 * sets the default color of the shape to yellow
	 */
	private void defaultColor() {
		setColor(Color.YELLOW);
	}

	/**
	 * draws the shape in the graphics component
	 * 
	 * @param g Graphics component to draw the shape in
	 */
	public void drawMe(Graphics g) {
//		System.out.println("If this is properly overriden, the proper shape will be drawn in the Graphics Window");
		Color saveColor = g.getColor();
		g.setColor(color);
		g.drawRect((int) location.getX(), (int) location.getY(), (int) dimensions.getHeight(),
				(int) dimensions.getWidth());
		g.setColor(saveColor);
	}

	/**
	 * calculates the area for the shape
	 * 
	 * @return calculated area
	 */
	public int getArea() {
		return (int) dimensions.getHeight() * (int) dimensions.getWidth();
	}

	/**
	 * calculates the perimeter for the shape
	 * 
	 * @return calculated perimeter
	 */
	public int getPerimeter() {
		int height = (int) dimensions.getHeight();
		int width = (int) dimensions.getWidth();
		return 2 * height + 2 * width;
	}

	/**
	 * @return String Location, Dimensions, and Color of Shape
	 */
	public String toString() {
		String result = "(" + location.toString() + ")";
		result += (int) dimensions.getHeight() + "x" + (int) dimensions.getWidth();
		result += " " + color.toString();
		return result;
	}

}
