import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class Tree extends JPanel {

	private static final long serialVersionUID = -3051225370744615177L;
	
	int width, height, angle_change;
	Color color;
	
	public Tree(int wd, int ht, int angle_change) {
		width = wd;
		height = ht;
		this.angle_change = angle_change;
		color = Color.BLACK;
	}
	
	public Tree(int wd, int ht, int angle_change, Color color) {
		width = wd;
		height = ht;
		this.angle_change = angle_change;
		this.color = color;
	}
	
	public void drawTree(Graphics window, double length, Point curr, int angle) {
		if (length <= 0)
			return;
		double x = length * Math.cos(Math.toRadians(angle));
		double y = -(length * Math.sin(Math.toRadians(angle)));
		Graphics2D draw = (Graphics2D) window;
		draw.setStroke(new BasicStroke((int) (length / 15)));
		draw.setColor(color);
		draw.drawLine(curr.x, curr.y, curr.x + (int) x, curr.y + (int) y);
		drawTree(window, length - 10, new Point(curr.x + (int) x, curr.y + (int) y), angle + angle_change);
		drawTree(window, length - 10, new Point(curr.x + (int) x, curr.y + (int) y), angle - angle_change);
	}
	
	public void paint(Graphics window) {
		drawTree(window, 100, new Point(500, 750), 90);
	}
	
}
