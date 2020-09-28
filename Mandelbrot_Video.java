import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mandelbrot_Video {

	int width, height, max_i;
	Color[] thing;
	double zoom = 1, zoom_factor, left_padding, top_padding;
	
	int frame_number = 0;

	public Mandelbrot_Video(int wd, int ht, int max, double zf) {
		width = wd;
		height = ht;
		max_i = max;
		zoom_factor = zf;
		/*left_padding = wd / 2.0;
		top_padding = ht / 2.0;*/
		left_padding = 0;
		top_padding = 0;
		thing = new Color[max_i];
		Color start = new Color(0, 255, 0);
		Color end = new Color(0, 0, 255);
		double dist_red = Math.abs(end.getRed() - start.getRed()) / (double) max_i;
		double dist_green = Math.abs(end.getGreen() - start.getGreen()) / (double) max_i;
		double dist_blue = Math.abs(end.getBlue() - start.getBlue()) / (double) max_i;
		for (int i = 0; i < max_i; i++) {
			thing[i] = new Color((int) (Math.min(start.getRed(), end.getRed()) + i * dist_red), (int) (Math.min(start.getGreen(), end.getGreen()) + i * dist_green), (int) (Math.min(start.getBlue(), end.getBlue()) + i * dist_blue));
		}
	}

	public int iterations_2(double re, double im) {
		double x = 0, y = 0;
		int i = 0;
		for (i = 0; x * x + y * y <= 4 && i < max_i; i++) {
			double x_new = x * x - y * y + re;
			y = 2 * x * y + im;
			x = x_new;
		}
		return i;
	}

	public int iterations_3(double re, double im) {
		double x = 0, y = 0;
		int i = 0;
		for (i = 0; x * x + y * y <= 4 && i < max_i; i++) {
			double x_new = x * x * x - 3 * x * y * y + re;
			y = 3 * x * x * y - y * y * y + im;
			x = x_new;
		}
		return i;
	}

	public int iterations_4(double re, double im) {
		double x = 0, y = 0;
		int i = 0;
		for (i = 0; x * x + y * y <= 4 && i < max_i; i++) {
			double x_new = x * x * x * x - 6 * x * x * y * y + y * y * y * y + re;
			y = 4 * x * x * x * y - 4 * x * y * y * y + im;
			x = x_new;
		}
		return i;
	}
	
	public int iterations_5(double re, double im) {
		double x = 0, y = 0;
		int i = 0;
		for (i = 0; x * x + y * y <= 4 && i < max_i; i++) {
			double x_new = x * x * x * x * x - 10 * x * x * x * y * y + 5 * x * y * y * y * y + re;
			y = 5 * x * x * x * x * y - 10 * x * x * y * y * y + y * y * y * y * y + im;
			x = x_new;
		}
		return i;
	}
	
	public int iterations_6(double re, double im) {
		double x = 0, y = 0;
		int i = 0;
		for (i = 0; x * x + y * y <= 4 && i < max_i; i++) {
			double x_new = x * x * x * x * x * x - 15 * x * x * x * x * y * y + 15 * x * x * y * y * y * y - y * y * y * y * y * y + re;
			y = 6 * x * x * x * x * x * y - 20 * x * x * x * y * y * y + 6 * x * y * y * y * y * y + im;
			x = x_new;
		}
		return i;
	}

	public void drawMandelbrot() throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D imgdraw = image.createGraphics();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double x_reg = x - width / 2.0;
				double y_reg = height / 2.0 - y;
				double re = (x_reg) * 4.0 / width / zoom - left_padding;
				double im = (y_reg) * 4.0 / height / zoom + top_padding;
				int iter = iterations_2(re, im);
				if (iter == max_i) {
					imgdraw.setColor(Color.BLACK);
				}
				else {
					imgdraw.setColor(thing[iter - 1]);
				}
				imgdraw.fillRect(x, y, 1, 1);
			}
		}
		ImageIO.write(image, "png", new File(String.format(".\\mandelbrot_video\\mandelbrot%d.png", frame_number++)));
	}
	
	public void makeVideo(int center_x, int center_y, int fps, int num_frames) throws IOException {
		double left_dis = (width / 2.0 - center_x) * 4.0 / width;
		double top_dis = (height / 2.0 - center_y) * 4.0 / height;
		left_padding = left_dis;
		top_padding = top_dis;
		drawMandelbrot();
		for (int i = 0; i < num_frames - 1; i++) {
			zoom(true);
			drawMandelbrot();
		}
		String command = String.format("C:\\ffmpeg\\bin\\ffmpeg -start_number 0 -i .\\mandelbrot_video\\mandelbrot%%d.png -codec mpeg4 -r %d .\\mandelbrot_video2.mp4", fps);
		Runtime.getRuntime().exec(command);
	}

	public void zoom(boolean in) {
		if (in) {
			//double left_dis = (x - width / 2.0) * 4.0 / width;
			//double top_dis = (height / 2.0 - y) * 4.0 / height;
			//left_padding -= left_dis / zoom;
			//top_padding += top_dis / zoom;
			zoom *= zoom_factor;
			//repaint();
		}
		else {
			//double left_dis = (x - width / 2.0) * 4.0 / width;
			//double top_dis = (height / 2.0 - y) * 4.0 / height;
			//left_padding -= left_dis / zoom;
			//top_padding += top_dis / zoom;
			zoom /= zoom_factor;
			//repaint();
		}
	}

}
