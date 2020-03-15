/**
 * 
 */
package java6601.lesson05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author CAI
 *
 */
public class PointDemo6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Point[] points = {
				new Point(1, 2),
				new Point(21, 31),
				new Point(11, 15),
				new Point(1, 2),
				new Point(23, 17)
		};
		
		demo2(points);
		demo3(points);
		
		points[4] = new Point(21, 17);
		
		demo4(points);
	}

	/**
	 * ArrayList 存储数据
	 * 
	 * @param points - 对象数组
	 */
	public static void demo2(Point[] points) {
		Collection<Point> list = new ArrayList<Point>();
		System.out.println("demo2");
		for (Point point : points) {
			list.add(point);
		}
		
		System.out.println(list.contains(new Point(11, 15)));
		System.out.println();

	}

	/**
	 * HashSet存储数据
	 * 
	 * @param points - 对象数组
	 */
	public static void demo3(Point[] points) {
		Collection<Point> list = new HashSet<Point>();
		System.out.println("demo3");

		for (Point point : points) {
			list.add(point);
		}
		
		System.out.println(list);
		System.out.println();
	}

	/**
	 * TreeSet存储数组
	 * 
	 * @param points - 对象数组
	 */
	public static void demo4(Point[] points) {
		Collection<Point> list = new TreeSet<Point>();
		System.out.println("demo4");

		for (Point point : points) {
			list.add(point);
		}
		
		System.out.println(list);
		System.out.println();
	}
}
