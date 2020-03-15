/**
 * 
 */
package java6601.lesson06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author CAI
 *
 */
public class DemoListSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point(1, 2));
		list.add(new Point(1, 31));
		list.add(new Point(11, 15));
		list.add(new Point(1, 2));
		list.add(new Point(23, 17));
		list.add(new Point(1, 21));
		
		Collections.sort(list);
		Collections.sort(list, new SortByY());
		list.sort(new Sortor());
		System.out.println(list);
	}
}

class SortByY implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		return p1.getY() - p2.getY();
	}
}

class Sortor implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		int xy = p1.getX() - p2.getX();
		return xy == 0 ? p1.getY() - p2.getY() : xy;
	}
}
