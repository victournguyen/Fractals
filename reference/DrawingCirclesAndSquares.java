import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.MouseInfo;

public class DrawingCirclesAndSquares extends Canvas
{

	public DrawingCirclesAndSquares ()
	{
		setBackground(Color.WHITE);		
	}

	public void paint( Graphics window )
	{
		window.setColor(Color.BLACK);
		window.drawString("Drawing Circles ", 50, 40);
		
		window.drawString("Mouse  coordinates " + "(" + MouseInfo.getPointerInfo().getLocation().x + "   " + MouseInfo.getPointerInfo().getLocation().y + ")", 250, 30 );
		
		/*OVALS */
		window.setColor(Color.ORANGE);
		//drawOval(int x1, int y1, int width, int height)
		window.drawOval(50,50,40,40);
		
		window.setColor(Color.GREEN);
		window.drawOval(150,50,100,50);
		
		window.setColor(Color.YELLOW);
		window.fillOval(350,50,70,70);
		
		
		window.setColor(Color.BLACK);
		window.drawString("Drawing  Squares ", 50, 140);
		
		/* RECTANGLES */
		window.setColor(Color.BLUE);
		//fillRect(int x1, int y1, int width, int height)
		window.fillRect(50, 150, 100, 20 );
		
		window.setColor(Color.GRAY);
		window.drawRect(200,150,50,50);
		
		
		window.setColor(Color.BLACK);
		window.drawString("Drawing Arcs ", 50, 290);
		
		/* MAKING A COLOR */
							//Color( int red, int green, int blue )
		Color newColor = new Color(40,			60,			80);
		
		window.setColor(newColor);
		window.drawArc(50,300,50,50,0,-180);
		
		newColor = new Color(140,			160,			180);
		window.setColor(newColor);
		window.drawArc(150,300,50,50,0,180);
		
		window.setColor(Color.BLACK);
		window.drawString("Drawing Lines ", 50, 440);
		
		window.setColor( Color.GREEN);
		window.drawLine(50,475,150,450);
		
		window.setColor( Color.RED);
		window.drawLine(250,475,450,500);
	}
}
