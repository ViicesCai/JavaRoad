/**
 * 
 */
package java6601.lesson10;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;


/**
 * 文件过滤
 * 
 * @author CAI
 *
 */
public class Lambda6601 {
	/**
	 * 用过滤器进行筛选，输出目录中满足条件的文件
	 * 
	 * @param dir    目录名称
	 * @param filter 文件过滤器
	 */
	public static void list6601(String dir, FilenameFilter filter) {
		File file = new File(dir); // 创建以 dir 为路径的 File 对象
		File[] files = file.listFiles(filter); // 此 file 下的所有文件及目录

		try {
			for (File f : files) {
				System.out.println(f.getName());
			}
			
		} catch (Exception e) {
			System.out.println("目录为空或输入的路径错误！");
		}
	}

	/**
	 * 分别输出目录中后缀名为doc的文件、大于1M的文件（用匿名类实现）
	 * 
	 * @param dir 目录名称
	 */
	public static void useInner6601(String dir) {
		System.out.println("------后缀名为doc的文件------");
		
		list6601(dir, new FilenameFilter() {
			/**
			 * 过滤出目录中后缀为 doc 的文件
			 */
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".doc");
			}
		});
		
		System.out.println("\n------文件大小大于 1M 的文件------");
		list6601(dir, new FilenameFilter() {
			
			/**
			 * 过滤出目录中文件大小大于 1M 的文件
			 */
			@Override
			public boolean accept(File dir, String name) {
				File file = new File(dir, name); // 创建 File 对象
				
				// 文件大小是按照字节计算的，需要进行转换
				Double byteSize = (double) file.length(); // 文件的大小
				// 1MB = 1024KB 1KB = 1024Byte
				Double mbSize = byteSize / (1024*1024);
				return mbSize > 1;
			}
		});
	}

	/**
	 * 分别输出目录中后缀名为doc的文件、大于1M的文件、2019年4月以后修改过的文件（用Lambda实现）
	 * 
	 * @param dir 目录名称
	 */
	public static void useLambda6601(String dir) {
		System.out.println("------后缀名为doc的文件------");
		/**
		 * 过滤出目录中后缀为 doc 的文件
		 */
		list6601(dir, (d, n) -> n.endsWith(".doc"));
		
		System.out.println("\n------文件大小大于 1M 的文件------");
		/**
		 * 过滤出目录中文件大小大于 1M 的文件
		 */
		list6601(dir, (d, n) -> new File(d, n).length() / (1024*1024) > 1);
		
		System.out.println("\n------2019年4月以后修改的文件------");
		/**
		 * 过滤出目录中2019年四月之后修改的文件
		 */
		list6601(dir, Lambda6601::isLastModifiedDate);
	}
	
	public static boolean isLastModifiedDate(File dir, String name) {
		File file = new File(dir,name); // 创建 File 对象
		
		// 获取文件最后修改的日期
		// 将 long型数据 转换为时间戳
		Instant timeStamp = Instant.ofEpochMilli(file.lastModified());
		
		// 将时间戳按照系统默认时区，转换成 LocalDate 对象
		LocalDate lastModifiedDate = LocalDateTime.ofInstant(timeStamp, ZoneId.systemDefault()).toLocalDate();
		
		// 该文件最后修改的时间不是2019年4月1日之前（2019年四月之后)
		return !lastModifiedDate.isBefore(LocalDate.of(2019, 4, 1));
	}
	
	public static void main(String[] args) {
		// 提示信息
		System.out.println("*输出目录中后缀名为doc的文件和大于1M的文件*");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入您的目录路径：");
		String dir = scanner.next(); // 用户输入的路径
		
		System.out.println("useInner6601方法");
		useInner6601(dir); // 匿名过滤方法
		
		System.out.println();
		System.out.println("useLambda6601方法");
		useLambda6601(dir); // Lambda 实现的过滤方法
		
		scanner.close();
	}
}
