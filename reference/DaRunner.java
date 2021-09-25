import javax.swing.JFrame;

public class DaRunner extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public DaRunner()
	{
		super("Da Runner");

		setSize(WIDTH,HEIGHT);

		//add whatever you want to demo
		//getContentPane().add(new DrawingPolygons() );
		//getContentPane().add(new DrawingCirclesAndSquares() );
		getContentPane().add(new UsingFontsAndColors() );

		setVisible(true);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		DaRunner run = new DaRunner();
	}
}
