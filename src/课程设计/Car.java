package 课程设计;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;

import org.omg.CORBA.Current;

public class Car extends JPanel implements Runnable {
	// 前四个是上下左右，后四个是斜角
	public final static int[] dx = { 0, -1, 0, 1, -1, -1, 1, 1 };
	public final static int[] dy = { -1, 0, 1, 0, 1, -1, -1, 1 };
	
	Color colorCar;
	int CurrentSpeed;
	int MaxSpeed;
	int a;
	int x;
	int y;
	int direct;
	// 是否停车
	boolean stopCar = true;
	// 停止线程
	boolean isStop = true;
	// 暂停线程
	static boolean zanTing = true;
	boolean isLive = true;
	public boolean reach = true; // 是否到达目的地

	@Override
	public void run() {
		 ArrayList<barrier> barrier_ = new ArrayList<barrier>();
		 barrier barrier1 = new barrier(1, 20, 200, 100);
		 barrier barrier2 = new barrier(40, 400, 200, 100);
		 barrier barrier3 = new barrier(150, 580, 200, 100);
		 barrier barrier4 = new barrier(200, 500, 200, 100);
		 barrier_.add(barrier1);
		 barrier_.add(barrier2);
		 barrier_.add(barrier3);
		 barrier_.add(barrier4);
		Point start = new Point(1, 1);
		Point end = new Point(600, 600);
		int[][] map = new int [700][700];
		map = initMap(barrier_);		
		Stack<Point> a = findWay(start, end,map);
		Point[] bPoints = new Point[a.size()];
		a.toArray(bPoints);
		MainFrame.sp.bPoints = bPoints;
		MainFrame.sp.xP = new int[bPoints.length];
		MainFrame.sp.yP = new int[bPoints.length];
		for(int i = 0;i<bPoints.length;i++){
			MainFrame.sp.xP[i] = bPoints[i].x;
			MainFrame.sp.yP[i]= bPoints[i].y;
        }
		/*while(iterator.hasNext()){
			MainFrame.sp.lastPoint = MainFrame.sp.currentPoint;
			MainFrame.sp.currentPoint = iterator.next();		
		}*/
		for(int i = bPoints.length-1;i>=0;i--) {
			
			MainFrame.sp.lastPoint = MainFrame.sp.currentPoint;
			x = MainFrame.sp.currentPoint.x;
			y = MainFrame.sp.currentPoint.y;
			Point currentP = new Point(x, y);
			MainFrame.sp.currentPoint = bPoints[i];
			if(Point.getDis(currentP, end)<100){
				
				try {
					Thread.sleep(CurrentSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				CurrentSpeed=CurrentSpeed+this.a;
			}else if(CurrentSpeed>MaxSpeed){				
				try {
					Thread.sleep(CurrentSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(CurrentSpeed-this.a>0)
					CurrentSpeed-=this.a;
								
			}else{		
				
				try {
					Thread.sleep(CurrentSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 碰到边界，删除
			if (x < -100 || x > 910 || y < -100 || y > 910) {
				this.reach = false;// 如果死亡，退出线程
				this.isStop = false;
				break;
			}
			MainFrame.sp.repaint();
		}

	}

	public void stop() {

	}

	@SuppressWarnings("null")
	public static int[][] initMap(ArrayList<barrier> barriers) {
		int[][] map = new int[700][700];
		for (int i = 0; i < 700; i++) {
			map[0][i] = 1;
			map[i][0] = 1;
			map[i][699] = 1;
			map[699][i] = 1;
		}
		if(barriers!=null||barriers.size()!=0){
			for(int i = 0;i<barriers.size();i++){
				barrier temp = barriers.get(i);
				for(int j = 0;j<temp.height;j++){
					for(int w = 0;w<temp.width;w++){
						map[temp.x+j][temp.y+w] = 1;
					}
				}
			}
		}
		return map;
	}

	public static Stack<Point> findWay(Point start, Point end,int[][] map) {
		ArrayList<Point> openTable = new ArrayList<Point>();
		ArrayList<Point> closeTable = new ArrayList<Point>();
		openTable.clear();
		closeTable.clear();
		Stack<Point> pathStack = new Stack<Point>();
		start.parent = null;
		Point currentPoint = new Point(start.x, start.y);
		boolean flag = true;
		while (flag) {
			for (int i = 0; i < 8; i++) {
				int fx = currentPoint.x + dx[i];
				int fy = currentPoint.y + dy[i];
				Point tempPoint = new Point(fx, fy);
				if (map[fx][fy] == 1) {
					continue;
				} else {
					if (end.equals(tempPoint)) {
						flag = false;
						end.parent = currentPoint;
						break;
					}

					if (i < 4) {
						tempPoint.H = currentPoint.G + 10;
					} else {
						tempPoint.G = currentPoint.G + 14;
					}
					tempPoint.H = Point.getDis(tempPoint, end);
					tempPoint.F = tempPoint.G + tempPoint.H;
					if (openTable.contains(tempPoint)) {
						int pos = openTable.indexOf(tempPoint);
						Point temp = openTable.get(pos);
						if (temp.F > tempPoint.F) {
							openTable.remove(pos);
							openTable.add(tempPoint);
							tempPoint.parent = currentPoint;
						}
					} else if (closeTable.contains(tempPoint)) {
						int pos = closeTable.indexOf(tempPoint);
						Point temp = closeTable.get(pos);
						if (temp.F > tempPoint.F) {
							closeTable.remove(pos);
							openTable.add(tempPoint);
							tempPoint.parent = currentPoint;

						}
					} else {
						openTable.add(tempPoint);
						tempPoint.parent = currentPoint;
					}

				}
			}
			if(openTable.isEmpty()){
				return null;
			}
			if(false == flag){
				break;
			}
			openTable.remove(currentPoint);
			closeTable.add(currentPoint);
			Collections.sort(openTable);
			currentPoint = openTable.get(0);
		}
		Point node = end;
		while (node.parent != null) {
			pathStack.push(node);
			node = node.parent;
		}
		return pathStack;

	}

	public Car(int x, int y, int direct, Color colorCar) {
		this.x = x;
		this.y = y;
		this.direct = direct;
		this.CurrentSpeed = 200;
		this.MaxSpeed = 10;
		this.a = 10;
		this.colorCar = colorCar;
	}
}
