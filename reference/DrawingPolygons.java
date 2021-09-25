import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Polygon;
import java.awt.MouseInfo;

public class DrawingPolygons extends Canvas
{

	public DrawingPolygons ()
	{
		setBackground(Color.WHITE);		
	}

	public void paint( Graphics window )
	{
		window.setColor(Color.BLACK);
		window.drawString("Drawing Polygons ", 50, 40);
		
		int[] xPoints = {200, 300, 100};
		int[] yPoints = {100, 200, 200};
		
		Polygon pol = new Polygon( xPoints, yPoints, 3);
		

		window.setColor(Color.GREEN);
		
		window.drawPolygon( pol );
		
		//window.fillPolygon( pol );
	}
}
