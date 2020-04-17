/**
 * 
 */
package java6601.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class TextCharset6601 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("UTF8Text.txt"), "UTF-8");
		
		while (in.hasNextLine()) {
			System.out.println(in.nextLine());
		}
		
		in.close();
	}
}
