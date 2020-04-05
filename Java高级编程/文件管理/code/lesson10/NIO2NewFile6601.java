/**
 * 
 */
package java6601.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * NIO.2 创建文件
 * 
 * @author CAI
 *
 */
public class NIO2NewFile6601 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入要创建的文件：");
		String uri = in.next();
		Path path = Paths.get(uri); // 将输入的文件路径字符串转换为 Path 对象
		
		// 先判断该文件是否存在
		if (Files.notExists(path)) {
			Path parent = path.getParent(); // 获取文件的 父路径
			// 如果此路径存在父路径 且 该路径不存在 
			if (parent != null && Files.notExists(parent)) {
				Files.createDirectories(parent); // 按照 父路径 创建目录
				System.out.println("创建目录：" + parent);
			}
			Files.createFile(path); // 按照 文件路径 创建文件
			System.out.println("创建新文件：" + path.toAbsolutePath());
		} else {
			System.out.println("该文件已存在");
		}
		
		in.close();
	}
}
