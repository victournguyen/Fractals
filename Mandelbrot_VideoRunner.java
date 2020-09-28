import java.io.IOException;

public class Mandelbrot_VideoRunner {
	
	public static final int WIDTH = 2160;
	public static final int HEIGHT = 2160;

	public Mandelbrot_VideoRunner() throws IOException {
		new Mandelbrot_Video(WIDTH, HEIGHT, 50, 1.01).makeVideo(460, 898, 60, 2000);
	}
	
	public static void main(String[] args) throws IOException {
		new Mandelbrot_VideoRunner();
	}
	
}
