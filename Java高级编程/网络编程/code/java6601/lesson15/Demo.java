/**
 * 
 */
package java6601.lesson15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author CAI
 *
 */
public class Demo {

	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.bilibili.com");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0");
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String msg = null;
		
		while (null != (msg = reader.readLine())) {
			System.out.println(msg);
		}
		
		reader.close();
	}

}
