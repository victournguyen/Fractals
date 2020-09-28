import java.awt.Color;

import javax.swing.JFrame;

public class TreeRunner extends JFrame {
	
	private static final long serialVersionUID = -1985134633522537923L;
	
	public static final int WIDTH = 1000, HEIGHT = 1000;
	
	public TreeRunner() {
		super("Tree Fractal");
		setSize(WIDTH + 15, HEIGHT + 39);
		setBackground(Color.BLACK);
		add(new Tree(WIDTH, HEIGHT, 30, Color.WHITE));
		validate();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TreeRunner();
	}
	
}
