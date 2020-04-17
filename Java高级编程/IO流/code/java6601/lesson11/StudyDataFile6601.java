/**
 * 
 */
package java6601.lesson11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 数据文件的读写
 * 
 * @author CAI
 *
 */
public class StudyDataFile6601 {

	/**
	 * 字节数组转换为int
	 * 
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt6601(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	/**
	 * int转换成字节数组
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] intToByteArray6601(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}

	public static void main(String[] args) throws Exception {
		writeIntWithByte6601();
		readData6601();
		writeData6601();
		readIntWithByte6601();
	}

	public static void writeIntWithByte6601() throws Exception {
		// 步骤1：确定输出的文件（目的地）
		// File.separator表示跨平台的目录分隔符
		File file = new File("write" + File.separator + "data6601.dat");
		// 输出时文件可以不存在，但目录必须存在
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 步骤2：创建指向文件的输出流
		OutputStream out = new FileOutputStream(file);
		// 步骤3：写数据
		out.write(intToByteArray6601(2048));
		// 步骤4：关闭
		out.close();
	}

	public static void readIntWithByte6601() throws Exception {
		// 步骤1：确定输入的源文件
		// File.separator表示跨平台的目录分隔符
		File file = new File("write" + File.separator + "data6601.dat");
		// 输入时文件必须存在
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return;
		}
		// 步骤2：创建指向文件的输入流
		InputStream in = new FileInputStream(file);

		// 步骤3：读数据
		byte[] data = new byte[4];
		in.read(data);
		int i = byteArrayToInt6601(data);
		System.out.println(i);

		// 步骤4：关闭流
		in.close();

	}

	public static void writeData6601() throws Exception {
		// 步骤1：确定输出的文件（目的地）
		// File.separator表示跨平台的目录分隔符
		File file = new File("write" + File.separator + "data6601.dat");
		// 输出时文件可以不存在，但目录必须存在
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 步骤2：创建指向文件的数据输出流
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		// 步骤3：写数据
		out.writeInt(123);
		// 步骤4：关闭
		out.close();
	}

	public static void readData6601() throws Exception {
		// 步骤1：确定输入的源文件
		// File.separator表示跨平台的目录分隔符
		File file = new File("write" + File.separator + "data6601.dat");
		// 输入时文件必须存在
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return;
		}
		// 步骤2：创建指向文件的数据输入流
		DataInputStream in = new DataInputStream(new FileInputStream(file));

		// 步骤3：读数据
		System.out.println(in.readInt());
		// 步骤4：关闭流
		in.close();

	}

}
