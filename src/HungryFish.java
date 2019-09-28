import java.awt.Graphics;

import javax.swing.ImageIcon;

public class HungryFish
{
	
	int direction;
	boolean if_again;
	
	int x;
	int y;
	int width;
	int height;
	
	ImageIcon appear;
	ImageIcon image1;
	ImageIcon image2;
	ImageIcon image3;
	ImageIcon image4;
	
	HungryFish()
	{
		image1 = new ImageIcon(getClass().getResource("images/hungryfish1.gif"));
		image2 = new ImageIcon(getClass().getResource("images/hungryfish2.gif"));
		image3 = new ImageIcon(getClass().getResource("images/hungryfish3.gif"));
		image4 = new ImageIcon(getClass().getResource("images/hungryfish4.gif"));
		
		init();
	}
	void draw(Graphics g)
	{
		g.drawImage(appear.getImage(),x,y,width,height,null);
	}
	void init()
	{
		if_again = false;
		//鳄鱼来的方向，左来1，右来2，上来3，下来4
		direction = (int)Math.random()*4+1;
		if(direction == 1)
		{
			width = 200;
			height = 50;
			x = -160;
			y = (int)Math.random()*400;
			appear = image1;
		}
		else if(direction == 2)
		{
			width = 200;
			height = 50;
			x = 600+160;
			y = (int)Math.random()*400;
			appear = image2;
		}
		else if(direction == 3)
		{
			width = 50;
			height = 200;
			x = (int)Math.random()*550;
			y = -30;
			appear = image3;
		}
		else if(direction == 4)
		{
			width = 50;
			height = 200;
			x = (int)Math.random()*550;
			y = 450+30;
			appear = image4;
		}
	}
	
	void update()
	{
		if(direction == 1)
		{
			x += 3;
			if(x > 650)
			{
				if_again = true;
			}
		}
		else if(direction == 2)
		{
			x -= 3;
			if(x <-50)
			{
				if_again = true;
			}
		}
		else if(direction == 3)
		{
			y += 3;
			if(y > 500)
			{
				if_again = true;
			}
		}
		else if(direction == 4)
		{
			y-=3;
			if(y < -50)
			{
				if_again = true;
			}
		}
	}
}
