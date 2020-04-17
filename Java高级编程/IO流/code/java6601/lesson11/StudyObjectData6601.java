/**
 * 
 */
package java6601.lesson11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author CAI
 *
 */
public class StudyObjectData6601 {
	/**
	 * 将对象保存到数据文件中
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeStudent6601() throws FileNotFoundException, IOException {
		// 步骤1：确定输出的文件（目的地）
		File file = new File("write" + File.separator + "student6601.dat");
		
		// 步骤2：创建指向文件的对象流
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		
		// 步骤3：写对象
		out.writeObject(new Student6601("211906601-蔡维恒", 22));
		
		// 步骤4：关闭
		out.close();
	}
	
	public static void readStudent6601() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 步骤1：确定输入的源文件
		File file = new File("write" + File.separator + "student6601.dat");
		
		// 输入时文件必须存在
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return;
		}
		
		// 步骤2：创建指向文件的对象流
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		
		// 步骤3：读数据
		Object obj = in.readObject();
		System.out.println(obj);
		Student6601 stu = (Student6601) obj;
		System.out.println("姓名=" + stu.getName() + ",年龄=" + stu.getAge());
		
		// 关闭资源
		in.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		writeStudent6601();
		readStudent6601();
	}
	
	
}
