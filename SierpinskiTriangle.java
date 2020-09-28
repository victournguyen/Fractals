import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class SierpinskiTriangle extends JPanel {

	private static final long serialVersionUID = -4471645226757797410L;
	
	Point a, b, c;
	Color color;
	
	public SierpinskiTriangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = Color.BLACK;
	}
	
	public SierpinskiTriangle(int ax, int ay, int bx, int by, int cx, int cy) {
		a = new Point(ax, ay);
		b = new Point(bx, by);
		c = new Point(cx, cy);
		this.color = Color.BLACK;
	}
	
	public SierpinskiTriangle(Point a, Point b, Point c, Color color) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;
	}
	
	public SierpinskiTriangle(int ax, int ay, int bx, int by, int cx, int cy, Color color) {
		a = new Point(ax, ay);
		b = new Point(bx, by);
		c = new Point(cx, cy);
		this.color = color;
	}
	
	public double dist(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
	}
	
	public Point mid(Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
	
	public void drawSierpinski(Graphics window, Point a, Point b, Point c) {
		if (dist(a, b) <= 4) {
			window.setColor(color);
			window.drawLine(a.x, a.y, b.x, b.y);
			window.drawLine(a.x, a.y, c.x, c.y);
			window.drawLine(b.x, b.y, c.x, c.y);
		}
		else {
			drawSierpinski(window, a, mid(a, b), mid(a, c));
			drawSierpinski(window, mid(a, b), b, mid(b, c));
			drawSierpinski(window, mid(a, c), mid(b, c), c);
		}
	}
	
	public void paint(Graphics window) {
		drawSierpinski(window, a, b, c);
	}

}
