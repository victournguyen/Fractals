import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.awt.Canvas;

public class UsingFontsAndColors extends Canvas
{
  public UsingFontsAndColors ()
	{
		setBackground(Color.WHITE);
	}

	public void paint( Graphics window )
	{
		window.setColor(Color.BLACK);
		
		Font nFont = new Font("Arial", Font.BOLD, 18);
		
		window.setFont( nFont );
		window.drawString("Using Fonts and Colors ", 50, 40);
		
		/* MAKING A COLOR */
							//Color( int red, int green, int blue )
		Color newColor = new Color(40,			60,			80);
		
		window.setColor(newColor);
		window.drawArc(50,100,50,50,0,-180);
		
		newColor = new Color(140,			160,			180);
		window.setColor(newColor);
		window.drawArc(150,100,50,50,0,180);
		
		window.setColor(Color.BLACK);
		
		nFont = new Font("Tahoma", Font.BOLD, 14);
		window.setFont( nFont );
		
		window.drawString("Making Random Colors ", 50, 240);
		
		/* MAKING A RANDOM COLOR */
		int red = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		newColor = new Color(red, green, blue);
		
		window.setColor(newColor);
		window.fillRect(50,250,50,50);
		
		//the not so simple approach
		newColor = new Color(((int)(Math.random()*256)),
																	((int)(Math.random()*256)),
																	((int)(Math.random()*256)));
		window.setColor(newColor);
		window.fillOval(150,250,50,50);
		
		newColor = new Color(((int)(Math.random()*256)),
																	((int)(Math.random()*256)),
																	((int)(Math.random()*256)));
		
		window.setColor(newColor);
		window.fillOval(250,250,10,50);
		
		red = (int)(Math.random()*256);
		green = (int)(Math.random()*256);
		blue = (int)(Math.random()*256);
		newColor = new Color(red, green, blue);
		window.setColor(newColor);
		window.fillRect(350,250,50,50);
	}
}