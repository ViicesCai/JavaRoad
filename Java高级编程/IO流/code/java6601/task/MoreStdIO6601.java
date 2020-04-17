/**
 * 
 */
package java6601.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author CAI
 *
 */
public class MoreStdIO6601 {

	public static void main(String[] args) throws IOException {
		PrintStream outstd = System.out;
		System.out.println("�����룺");
		
		// �ض����׼�����
		PrintStream outFile = new PrintStream("out.txt");
		System.setOut(outFile);
		
		// ��װ��׼������
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input;
		
		while ((input = reader.readLine()) != null && !input.equals("end")) {
			System.out.println(input);
		}
		
		System.out.println(outstd);
	}

}
