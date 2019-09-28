import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class GamePanel extends JPanel implements MouseListener
{
	Timer timer;
	
	ImageIcon imag[] = new ImageIcon[8];
	ImageIcon imag_home;
	ImageIcon background;
	ImageIcon imag_surprise;
	ImageIcon imag_help;
	ImageIcon appear_imag[]= new ImageIcon[5];
	ImageIcon imag_die;
	ImageIcon imag_full;
	
	public Ball ball[] = new Ball[5];
	int state = 1;
	int flag = 1;
	int score = 40;
	boolean pressed[]= new boolean[5];
	
	Font myfont;
	
	//声明鳄鱼类
	HungryFish hungryfish;
	
	GamePanel()
	{
		imag_surprise = new ImageIcon(getClass().getResource("images/surprise.png"));
		background = new ImageIcon(getClass().getResource("images/background.jpg"));
		imag_home = new ImageIcon(getClass().getResource("images/main.jpg"));
		imag_help = new ImageIcon(getClass().getResource("images/help.jpg"));
		imag_die = new ImageIcon(getClass().getResource("images/die.jpg"));
		imag_full = new ImageIcon(getClass().getResource("images/full.jpg"));
		
		imag[0] = new ImageIcon(getClass().getResource("images/cake1.png"));
		imag[1] = new ImageIcon(getClass().getResource("images/cake2.png"));
		imag[2] = new ImageIcon(getClass().getResource("images/cake3.png"));
		imag[3] = new ImageIcon(getClass().getResource("images/cake4.png"));
		imag[4] = new ImageIcon(getClass().getResource("images/cake5.png"));
		imag[5] = new ImageIcon(getClass().getResource("images/cake6.png"));
		imag[6] = new ImageIcon(getClass().getResource("images/cake7.png"));
		imag[7] = new ImageIcon(getClass().getResource("images/cake8.png"));
		
		for(int i = 0 ; i<5 ; i++)
		{
			int num = (int)(Math.random()*8);
			appear_imag[i] = imag[num];
		}
		
		hungryfish = new HungryFish();
		

		this.addMouseListener(this);
		timer = new Timer();
		
		
		myfont = new Font("新蒂小丸子小学版",Font.BOLD,47/2);

		

			for(int i = 0; i<5; i++)
			{
				
				ball[i] = new Ball();
				
			}
		
			timer.schedule(new MyTask(), 0 , 100);
			timer.schedule(new HungerTask(), 0 , 450);
	}
	

	
	//

	public void paint(Graphics g)
	{
		super.paint(g);
		//
		if(state == 1)
		{
			g.drawImage(imag_home.getImage(), 0, 0, 1200/2, 900/2, null);
		}
		
		else if(state == 2)
		{
			g.drawImage(background.getImage(), 0, 0, 1200/2, 900/2, null);
			for(int i = 0; i<5 ;i++)
			{
				g.drawImage(appear_imag[i].getImage(),ball[i].x,ball[i].y,90,90,null);		
			}
			hungryfish.draw(g);
			
			g.setFont(myfont);
			g.setColor(new Color(255,255,255));
			g.drawString("饥饿值：" + score, 70/2 ,128/2);
			
		}
		else if(state == 3)
		{
			g.drawImage(imag_help.getImage(), 0, 0, 1200/2, 900/2, null);
		}
		else if(state == 4)
		{
			g.drawImage(imag_die.getImage(), 0, 0, 1200/2, 900/2, null);

			g.setFont(myfont);
			g.setColor(new Color(255,255,255));
			g.drawString("饥饿值：" + score, 70/2 ,128/2);
		}
		else if(state == 5)
		{
			g.drawImage(imag_full.getImage(), 0, 0, 1200/2, 900/2, null);

			g.setFont(myfont);
			g.setColor(new Color(255,255,255));
			g.drawString("饥饿值：0" , 70/2 ,128/2);
		}
	}
	
	
	class HungerTask extends TimerTask
	{
		public void run() 
		{
			if(state == 2)
			{
				score += 5;
			}
			repaint();
		}
		
	}
	
	class MyTask extends TimerTask
	{

		public void run()
		{

			if(state == 2)
				for(int i = 0; i<5 ; i++)
				{
					ball[i].update();
					hungryfish.update();
					
					//判断是否要开始新的一轮【待封装】
					if(ball[i].y>1000/2)
					{
						int num = (int)(Math.random()*8);
						appear_imag[i] = imag[num];
						this.again(i);
					}					
				}
			
			if(score == 100)
			{
				state = 4;
			}
			else if(score < 0)
			{
				state = 5;
			}
			repaint();
		}
		
		//【待封装】
		void again(int i)
		{
			
			ball[i].init();
			pressed[i] = false;

		}
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		
		if(state == 1)
		{
			if(e.getX()>=760/2 && e.getX()<= 1042.5 /2&& e.getY()>=500/2 && e.getY()<=573.5/2)
			{
				state = 2;
			}
			
			else if(e.getX()>=760/2 && e.getX()<= 1042.5 /2&& e.getY()>=630/2&& e.getY()<=705/2)
			{
				state = 3;
			}
		}
		
		//
		
		else if(state == 2)
		{
			boolean result[] = new boolean[4];
			for(int i = 0 ; i<5 ; i++)
			{
				result[i] = true;
				
				if(e.getX() < ball[i].x||e.getX()>ball[i].x + 200/2)
					result[i] = false;
				else if(e.getY() < ball[i].y||e.getY()>ball[i].y + 200/2)
				{
					result[i]=false;
					hungryfish.init();
				}
				
				if(result[i]==true&&pressed[i]==false)
				{
					appear_imag[i] = imag_surprise;
					ball[i].speedY = 0; 
					score -= 10;
					pressed[i]=true;
				}
			}
			if(e.getX()>=866/2 && e.getX()<= 1144/2 && e.getY()>=75/2 && e.getY()<=160/2)		//界面二（游戏界面）转至界面一（主界面）坐标右上角
			{
				state = 1;
					
			}
		}
		
		else if(state == 3)
		{
			if(e.getX()>=895/2 && e.getX()<= 1080/2 && e.getY()>=763.5/2 && e.getY()<=826/2 )	//界面三（帮助界面）转至界面一（主界面）坐标右下角
			{
				state = 1;
			}
		}
		else if(state ==4||state == 5)
		{
			
			if(e.getX()>=866/2 && e.getX()<= 1144/2 && e.getY()>=75/2 && e.getY()<=160/2)		////界面四、五（评分界面）转至界面一（主界面）坐标右上角
			{
				state = 1;
				score = 40;
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{	
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e)
	{
		
	}

	
	
	//
	
}





