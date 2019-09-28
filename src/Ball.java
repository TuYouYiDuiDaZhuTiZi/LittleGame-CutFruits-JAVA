import java.awt.Color;


public class Ball 
{
	int x;
	int y;
	int speedX;
	int speedY;

	//
	
	Ball()
	{
		init();
		
	}
	void update()
	{
		speedY += 10;
		x = x + speedX ;
		y = y + speedY ;
		

	}
	void init()
	{
		x = (int)(Math.random()* 600);
		y = (int)(Math.random()*(80)+400);
		if(x<300)
		{
			speedX = (int)(Math.random()*5);
		}
		else
		{
			speedX = (int)(Math.random()*(-5));
		}
		speedY = (int)(Math.random()*(-100));
	}
}
