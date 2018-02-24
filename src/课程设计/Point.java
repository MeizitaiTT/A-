package ¿Î³ÌÉè¼Æ;

public class Point implements Comparable<Point> {
	int x;
	int y;
	Point parent;
	int F,G,H;
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return this.F-o.F;
	}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		F = 0;
		G = 0;
		H = 0;
	}
	@Override
	public boolean equals(Object obj){
		Point point = (Point) obj;
		if(point.x == this.x && point.y == this.y )
			return true;
		return false;
	}
	
	public static int getDis(Point p1, Point p2){
		int dis = Math.abs(p1.x-p2.x)*10 + Math.abs(p1.y-p2.y)*10;
		return dis;
	}
	

}
