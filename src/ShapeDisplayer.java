import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * A class that draws various shapes into A JFrame
 * 
 * @author Nathan Anneken
 * 
 */
public class ShapeDisplayer extends JFrame {

	private JLabel mouseLocation, areaAndPerimeter;
	private ArrayList<Shape> shapeList;
	private int perimeter, area;

	/**
	 * Constructs the general JFrame of the application by creating the frame,
	 * creating an array list of all the shapes in the frame, setting the
	 * dimensions of the frame, creating the JLabels to display the mouse
	 * coordinates and the total area and perimeter of the shapes, and sets the
	 * frame visible.
	 */
	public ShapeDisplayer() {
		super("Shape Display");

		shapeList = new ArrayList<Shape>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 450));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// Create a scroll pane to contain the components.
		JScrollPane jsp = new JScrollPane(new Board());
		add(jsp, BorderLayout.CENTER);

		// Create a JLabel to display mouse coordinates.
		mouseLocation = new JLabel("  Mouse Location: x=  " + ", y=");
		add(mouseLocation, BorderLayout.SOUTH);

		// Create a JLabel to show the total perimeter and sum of all shapes
		perimeter = 0;
		area = 0;
		areaAndPerimeter = new JLabel(" Total Perimeter = " + perimeter
				+ ", Total Area = " + area);
		add(areaAndPerimeter, BorderLayout.NORTH);

		// Pack and show
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Adds a shape to the ShapeDisplayer. There is a better way to do this, but
	 * we need to cover polymorphism and abstract classes first. Shape must be a
	 * triangle, rectangle, dodecagon, hexagon, pentagon, or star.
	 * 
	 * @param newShape
	 *            The shape to add.
	 */
	public void addShape(Shape newShape) {

		if (newShape.isInsideBox(400, 400)) {
			shapeList.add(newShape);
			perimeter += (int) newShape.findPerimeter();
			area += Math.abs((int) newShape.findArea());
		}

		areaAndPerimeter.setText(" Total Perimeter = " + perimeter
				+ ", Total Area = " + area);
		repaint();
	}

	/**
	 * A board class that extends JComponent, represents the grid area
	 * 
	 * @author DJ Rao, Keith Frikken
	 * 
	 */
	private class Board extends JComponent {

		/**
		 * A default constructor.
		 */
		public Board() {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent me) {
					mouseLocation.setText("  Mouse Location: x=  " + me.getX()
							+ ", y=" + me.getY());
				}
			});
		}

		/**
		 * Sets the preferred size, but it is not explicitly called.
		 */
		public Dimension getPreferredSize() {
			Dimension size = new Dimension(200, 200);
			return size;
		}

		/**
		 * Paints all triangles in the triangle list.
		 */
		public void paintComponent(Graphics g) {
			paintBackground(g);
			for (int i = 0; (i < shapeList.size()); i++) {
				Point[] points = new Point[0];
				Shape current = shapeList.get(i);

				points = current.getVertices();

				Color shapeColor = current.getColor();
				Polygon vertices = new Polygon();
				for (Point p : points) {
					vertices.addPoint(p.getXCoor(), p.getYCoor());
				}
				g.setColor(new Color(shapeColor.getRed(),
						shapeColor.getGreen(), shapeColor.getBlue(), 200));
				g.fillPolygon(vertices);
				g.setColor(shapeColor);
				g.drawPolygon(vertices);
			}

		}

		/**
		 * Creates the background. Makes the grid and sets the background to
		 * black.
		 * 
		 * @param g
		 *            A graphic component.
		 */
		public void paintBackground(Graphics g) {
			final int Width = getWidth();
			final int Height = getHeight();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);
			// Draw horizontal lines and print labels with small font.
			g.setFont(g.getFont().deriveFont(g.getFont().getSize() - 2.0f));
			for (int y = 0; (y < Height); y += 50) {
				g.drawLine(0, y, Width, y);
				g.drawString("" + y, 0, y - 2);
				g.drawString("" + y, Width - 25, y - 2);
			}
			// Draw vertical lines and print labels.
			for (int x = 0; (x < Width); x += 50) {
				g.drawLine(x, 0, x, Height);
				g.drawString("" + x, x + 1, 10);
				g.drawString("" + x, x + 1, Height - 1);
			}
		}
	}

	private static Shape generateShape(int x, int y) {
		Shape shape = null;
				int r = (int) (Math.random() * 256);
				int g = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);
				int shapeChoice = (int) ((Math.random() * 6) + 1);
				switch (shapeChoice) {
				case 1:
					shape = new Dodecagon(x, y, 80, 80, new Color(r, g, b));
					break;
				case 2:
					shape =  new Hexagon(x, y, 80, 80, new Color(r, g, b));
					break;
				case 3:
					shape =  new Pentagon(x, y, 80, 80, new Color(r, g, b));
					break;
				case 4:
					shape =  new Rectangle(x, y, 80, 80, new Color(r, g, b));
					break;
				case 5:
					shape =  new Star(x, y, 80, 80, new Color(r, g, b));
					break;
				case 6:
					shape =  new Triangle(x, y, 80, 80, new Color(r, g, b));
					break;
		}
		return shape;
	}

	/**
	 * Creates a ShapeDisplayer and randomly generates 25 shapes within the
	 * frame of random type and random color that are evenly spaced within the
	 * grid
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ShapeDisplayer disp = new ShapeDisplayer();
		
		for (int y = 0; y < 400; y += 80) {
			for (int x = 0; x < 400; x += 80) {
				disp.addShape(generateShape(x,y));
			}
		}

	}
}
