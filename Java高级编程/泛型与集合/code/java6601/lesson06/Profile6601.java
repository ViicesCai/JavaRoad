/**
 * 
 */
package java6601.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author CAI
 *
 */
public class Profile6601 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("myProFile6601.properties"));
		System.out.println(properties.getProperty("id"));
		System.out.println(properties.getProperty("name"));
		System.out.println(properties.getProperty("age"));

	}

}
