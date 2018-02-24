package 课程设计;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements Runnable {
	static Car Tcar;
	static Point endPoint;
	static Point currentPoint;
	static Point lastPoint;
	static Point[] bPoints;
	static int[] xP;
	static int[] yP;
	static ArrayList<barrier> barriers ;
	/**
	 * Create the panel.
	 */
	int info = 0;  
    boolean isLive = true;  
  
    public void paint(Graphics g) {  
        super.paint(g);
        /*try {
			Image carIm = ImageIO.read(new File("src/car.jpg"));
			g.drawImage(carIm, Tcar.x, Tcar.y, 20, 20, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        g.setColor(Color.white); 
                        
        g.setColor(Color.gray);             
        g.fill3DRect(250, 20, 500, 150, true);
        g.fill3DRect(280,250,500,150,true);
        /*g.fill3DRect(300, 70, 20, 300, true)*/;
        g.fill3DRect(80,35,80,90,true);
        g.fill3DRect(450,400,60,150,true);
        g.fill3DRect(1, 100, 160, 300, true);
        g.fill3DRect(130, 400, 60, 260, true);
        g.fill3DRect(190, 550, 160, 110, true);
        g.setColor(Color.red);
        g.drawPolyline(xP, yP, bPoints.length);
        ImageIcon forbin = new ImageIcon("src/课程设计/forbin.jpg");
        g.drawImage(forbin.getImage(), 1, 30, 70, 70, null);
        g.drawImage(forbin.getImage(), 40, 400, 80, 80, null);
        g.drawImage(forbin.getImage(), 60, 580, 80, 80, null);
        g.drawImage(forbin.getImage(), 50, 500, 80, 80, null);
       /* barrier barrier1 = new barrier(1, 20, 200, 200);
		 barrier barrier2 = new barrier(40, 400, 200, 200);
		 barrier barrier3 = new barrier(60, 580, 20, 10);
		 barrier barrier4 = new barrier(50, 500, 20, 10);*/
		 
        g.setColor(Color.blue);	
        ImageIcon end_p = new ImageIcon("src/课程设计/end.jpg");
        g.drawImage(end_p.getImage(), 600,600,40,40, null);
      	ImageIcon car = new ImageIcon("src/课程设计/car.jpg");
        g.drawImage(car.getImage(), Tcar.x, Tcar.y,20,20, null);
       
    }
	public StartPanel() {
			new Thread(this).start();
	}

	@Override
	public void run() {
		
		Tcar = new Car(1,1,1,Color.orange);
		currentPoint = new Point(1, 1);
		new Thread(Tcar).start();		
		endPoint = new Point(600,600);
		       		
	}

}
