import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

public class SierpinskiRunner extends JFrame {
	
	private static final long serialVersionUID = -394121021325971737L;

	public SierpinskiRunner() {
		super("Sierpinski Triangle");
		setBackground(Color.BLACK);
		setSize(1920 + 15, 1080 + 39);
		add(new SierpinskiTriangle(new Point(800, 250), new Point(250, 683), new Point(750, 683), Color.WHITE));
		add(new SierpinskiTriangle(new Point(800, 250), new Point(750, 683), new Point(1100, 600), Color.WHITE));
		validate();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SierpinskiRunner();
	}
	
}
