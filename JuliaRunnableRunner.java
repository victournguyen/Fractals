import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class JuliaRunnableRunner extends JFrame {
	
	private static final long serialVersionUID = -1313598947673000176L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	
	public int gradient = 2;

	public JuliaRunnableRunner() {
		super("Julia Set - Dynamic");
		setBackground(Color.BLACK);
		setSize(WIDTH + 15, HEIGHT + 39);
		JuliaRunnable julia = new JuliaRunnable(WIDTH, HEIGHT, -.79, .15, 50);
		add(julia);
		KeyAdapter key = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					dispose();
				if (e.getKeyCode() == KeyEvent.VK_F)
					julia.freeze();
				if (e.getKeyCode() == KeyEvent.VK_1) {
					julia.start = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
					if (gradient == 2)
						julia.calculateColors();
					else if (gradient == 3)
						julia.calculateColors(0.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_2) {
					julia.mid = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
					if (gradient == 2)
						julia.calculateColors();
					else if (gradient == 3)
						julia.calculateColors(0.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_3) {
					julia.end = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
					if (gradient == 2)
						julia.calculateColors();
					else if (gradient == 3)
						julia.calculateColors(0.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_4) {
					if (gradient == 2) {
						gradient = 3;
						julia.calculateColors(0.5);
					}
					else if (gradient == 3) {
						gradient = 2;
						julia.calculateColors();
					}
				}
			}
		};
		addKeyListener(key);
		validate();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JuliaRunnableRunner();
	}
	
}
