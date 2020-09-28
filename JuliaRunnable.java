import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;

public class JuliaRunnable extends JPanel implements Runnable {

	private static final long serialVersionUID = 4327569184045273143L;

	int WIDTH, HEIGHT, max_i, left_plot_padding, top_plot_padding;
	Color start, mid, end;
	Color[] thing;
	double c_re, c_im;
	
	boolean freeze;

	public JuliaRunnable(int wd, int ht, double c_re, double c_im, int max) {
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
		
		thing = new Color[max_i];
		start = new Color(40, 40, 40);
		mid = new Color(255, 255, 255);
		end = new Color(0, 255, 128);
		
		double dist_red = (end.getRed() - start.getRed()) / (double) max_i;
		double dist_green = (end.getGreen() - start.getGreen()) / (double) max_i;
		double dist_blue = (end.getBlue() - start.getBlue()) / (double) max_i;
		for (int i = 0; i < max_i; i++) {
			thing[i] = new Color(Color.HSBtoRGB(i / 256f - i / 400f, 1, i / (i + 8f)));
			//thing[i] = new Color((int) (start.getRed() + i * dist_red), (int) (start.getGreen() + i * dist_green), (int) (start.getBlue() + i * dist_blue));
		}
		
		//calculateColors();
		
		freeze = false;
		new Thread(this).start();
	}
	
	//two-color gradient
	public void calculateColors() {
		double dist_red = (end.getRed() - start.getRed()) / (double) max_i;
		double dist_green = (end.getGreen() - start.getGreen()) / (double) max_i;
		double dist_blue = (end.getBlue() - start.getBlue()) / (double) max_i;
		for (int i = 0; i < max_i; i++) {
			thing[i] = new Color((int) (start.getRed() + i * dist_red), (int) (start.getGreen() + i * dist_green), (int) (start.getBlue() + i * dist_blue));
		}
	}
	
	//three-color gradient
	public void calculateColors(double percent_first) {
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
		}
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
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				double x_reg = x - WIDTH / 2.0;
				double y_reg = HEIGHT / 2.0 - y;
				double re = (x_reg) * 4.0 / WIDTH;
				double im = (y_reg) * 4.0 / HEIGHT;
				int iter = iterations_6(re, im);
				if (iter == max_i) {
					window.setColor(Color.BLACK);
				}
				else {
					window.setColor(thing[iter]);
				}
				window.fillRect(x + left_plot_padding, y + top_plot_padding, 1, 1);
			}
		}
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
	
	public void freeze() {
		freeze = freeze ^ true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(5);
				if (freeze)
					continue;
				Point p = getMousePosition(); //display this somewhere
				if (p == null)
					continue;
				int x = p.x - left_plot_padding, y = p.y - top_plot_padding;
				//System.out.println(x + " " + y);
				c_re = (x - WIDTH / 2.0) * 4.0 / WIDTH;
				c_im = (HEIGHT / 2.0 - y) * 4.0 / HEIGHT;
				repaint();
			}
		} catch (Exception e) {
			
		}
	}

}
