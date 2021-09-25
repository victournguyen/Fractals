import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.swing.JPanel;

public class Julia extends JPanel implements MouseListener, MouseWheelListener {

	private static final long serialVersionUID = 4327569184045273143L;

	int WIDTH, HEIGHT, max_i, left_plot_padding, top_plot_padding;
	Color[] thing;
	double zoom = 1, zoom_factor = 1.5, zoom_factor_scroll = 1.075, left_padding, top_padding, c_re, c_im;

	public Julia(int wd, int ht, double c_re, double c_im, int max) {
		WIDTH = wd;
		HEIGHT = ht;
		if (WIDTH > HEIGHT)
			left_plot_padding = (WIDTH - HEIGHT) / 2;
		else if (HEIGHT > WIDTH)
			top_plot_padding = (HEIGHT - WIDTH) / 2;
		WIDTH = Math.min(WIDTH, HEIGHT);
		HEIGHT = Math.min(WIDTH, HEIGHT);
		max_i = max;
		this.c_re = c_re;
		this.c_im = c_im;
		/*left_padding = wd / 2.0;
		top_padding = ht / 2.0;*/
		left_padding = 0;
		top_padding = 0;
		thing = new Color[max_i];
		Color start = new Color(0, 255, 100);
		Color mid = new Color(255, 255, 255);
		Color end = new Color(255, 0, 0);
		
		//two-color gradient
		double dist_red = (end.getRed() - start.getRed()) / (double) max_i;
		double dist_green = (end.getGreen() - start.getGreen()) / (double) max_i;
		double dist_blue = (end.getBlue() - start.getBlue()) / (double) max_i;
		for (int i = 0; i < max_i; i++) {
			//System.out.printf("%d: %d %d %d\n", i, (int) (start.getRed() + i * dist_red), (int) (start.getGreen() + i * dist_green), (int) (start.getBlue() + i * dist_blue));
			//thing[i] = new Color((int) (start.getRed() + i * dist_red), (int) (start.getGreen() + i * dist_green), (int) (start.getBlue() + i * dist_blue));
			thing[i] = new Color(Color.HSBtoRGB(i / 256f - i / 400f, 1, i / (i + 8f)));
		}
		
		//three-color gradient
		/*double percent_first = 0.5;
		double dist_red = (mid.getRed() - start.getRed()) / (double) (percent_first * max_i);
		double dist_green = (mid.getGreen() - start.getGreen()) / (double) (percent_first * max_i);
		double dist_blue = (mid.getBlue() - start.getBlue()) / (double) (percent_first * max_i);
		for (int i = 0; i < percent_first * max_i; i++) {
			thing[i] = new Color((int) (start.getRed() + i * dist_red), (int) (start.getGreen() + i * dist_green), (int) (start.getBlue() + i * dist_blue));
		}
		double dist_red2 = (end.getRed() - mid.getRed()) / (double) ((1 - percent_first) * max_i);
		double dist_green2 = (end.getGreen() - mid.getGreen()) / (double) ((1 - percent_first) * max_i);
		double dist_blue2 = (end.getBlue() - mid.getBlue()) / (double) ((1 - percent_first) * max_i);
		for (int i = 0; i < (1 - percent_first) * max_i; i++) {
			thing[(int) (percent_first * max_i) + 1] = new Color((int) (mid.getRed() + i * dist_red2), (int) (mid.getGreen() + i * dist_green2), (int) (mid.getBlue() + i * dist_blue2));
		}*/
		addMouseListener(this);
		addMouseWheelListener(this);
	}
	
	public int iterations_2(double re, double im) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = re * re - im * im + c_re;
			im = 2 * re * im + c_im;
			re = x_new;
		}
		return i;
	}
	
	public int iterations_3(double re, double im) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = re * re * re - 3 * re * im * im + c_re;
			im = 3 * re * re * im - im * im * im + c_im;
			re = x_new;
		}
		return i;
	}

	public int iterations_4(double re, double im) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = re * re * re * re - 6 * re * re * im * im + im * im * im * im + c_re;
			im = 4 * re * re * re * im - 4 * re * im * im * im + c_im;
			re = x_new;
		}
		return i;
	}
	
	public int iterations_5(double re, double im) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = re * re * re * re * re - 10 * re * re * re * im * im + 5 * re * im * im * im * im + c_re;
			im = 5 * re * re * re * re * im - 10 * re * re * im * im * im + im * im * im * im * im + c_im;
			re = x_new;
		}
		return i;
	}
	
	public int iterations_6(double re, double im) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = re * re * re * re * re * re - 15 * re * re * re * re * im * im + 15 * re * re * im * im * im * im - im * im * im * im * im * im + c_re;
			im = 6 * re * re * re * re * re * im - 20 * re * re * re * im * im * im + 6 * re * im * im * im * im * im + c_im;
			re = x_new;
		}
		return i;
	}
	
	public int iterations_n(double re, double im, int n) {
		int i = 0;
		for (i = 0; re * re + im * im <= 4 && i < max_i; i++) {
			double x_new = Math.pow(re * re + im * im, n / 2.0) * Math.cos(n * Math.atan2(im, re)) + c_re;
			im = Math.pow(re * re + im * im, n / 2.0) * Math.sin(n * Math.atan2(im, re)) + c_im;
			re = x_new;
		}
		return i;
	}

	public void drawJulia(Graphics window) throws IOException {
		//System.out.println(Arrays.toString(thing));
		//BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//Graphics2D imgdraw = image.createGraphics();
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				double x_reg = x - WIDTH / 2.0;
				double y_reg = HEIGHT / 2.0 - y;
				double re = (x_reg) * 4.0 / WIDTH / zoom - left_padding;
				double im = (y_reg) * 4.0 / HEIGHT / zoom + top_padding;
				int iter = iterations_6(re, im);
				if (iter == max_i) {
					window.setColor(Color.BLACK);
					//imgdraw.setColor(Color.BLACK);
				}
				else {
					window.setColor(thing[iter]);
					//imgdraw.setColor(thing[iter - 1]);
				}
				window.fillRect(x + left_plot_padding, y + top_plot_padding, 1, 1);
				//imgdraw.fillRect(x, y, 1, 1);
			}
		}
		//ImageIO.write(image, "png", new File("julia.png"));
	}

	public void paint(Graphics window) {
		try {
			drawJulia(window);
			window.setColor(Color.BLACK);
			window.fillRect(0, 0, 200, 40);
			window.setColor(Color.WHITE);
			window.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
			window.drawString(String.format("c = %.3f + %.3fi", c_re, c_im), 10, 25);
		} catch (IOException e) { }
	}

	public void new_zoom(int x, int y, boolean in, boolean click) {
		if (in) {
			double left_dis = (x - WIDTH / 2.0) * 4.0 / WIDTH;
			double top_dis = (HEIGHT / 2.0 - y) * 4.0 / HEIGHT;
			left_padding = -left_dis / zoom;
			top_padding = top_dis / zoom;
			if (click)
				zoom *= zoom_factor;
			else
				zoom *= zoom_factor_scroll;
			repaint();
		}
		else {
			double left_dis = (x - WIDTH / 2.0) * 4.0 / WIDTH;
			double top_dis = (HEIGHT / 2.0 - y) * 4.0 / HEIGHT;
			left_padding -= left_dis / zoom;
			top_padding += top_dis / zoom;
			if (click)
				zoom /= zoom_factor;
			else
				zoom /= zoom_factor_scroll;
			repaint();
		}
	}
	
	public void zoom(int x, int y, boolean in, boolean click) {
		if (in) {
			double left_dis = (x - WIDTH / 2.0) * 4.0 / WIDTH;
			double top_dis = (HEIGHT / 2.0 - y) * 4.0 / HEIGHT;
			left_padding -= left_dis / zoom;
			top_padding += top_dis / zoom;
			if (click)
				zoom *= zoom_factor;
			else
				zoom *= zoom_factor_scroll;
			repaint();
		}
		else {
			double left_dis = (x - WIDTH / 2.0) * 4.0 / WIDTH;
			double top_dis = (HEIGHT / 2.0 - y) * 4.0 / HEIGHT;
			left_padding -= left_dis / zoom;
			top_padding += top_dis / zoom;
			if (click)
				zoom /= zoom_factor;
			else
				zoom /= zoom_factor_scroll;
			repaint();
		}
	}
	
	public void move(int key) {
		switch (key) {
		case 0:
			top_padding += 0.5 / zoom; break;
		case 1:
			left_padding += 0.5 / zoom; break;
		case 2:
			top_padding -= 0.5 / zoom; break;
		case 3:
			left_padding -= 0.5 / zoom; break;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			zoom(arg0.getX(), arg0.getY(), true, true);
		}
		else if (arg0.getButton() == MouseEvent.BUTTON3) {
			zoom(arg0.getX(), arg0.getY(), false, true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		double rot = arg0.getPreciseWheelRotation();
		zoom(arg0.getX(), arg0.getY(), rot < 0, false);
	}

}
