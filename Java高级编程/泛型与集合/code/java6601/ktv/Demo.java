package java6601.ktv;

import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("World");
		list.add("wowo");
		
		int index = list.indexOf("wowo");
		System.out.println(list.indexOf("wowo"));
		list.remove(list.indexOf("wowo"));
		list.add(index - 1, "wowo");
		System.out.println(list);
	}

}
