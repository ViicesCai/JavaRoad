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
 * �ļ�����
 * 
 * @author CAI
 *
 */
public class Lambda6601 {
	/**
	 * �ù���������ɸѡ�����Ŀ¼�������������ļ�
	 * 
	 * @param dir    Ŀ¼����
	 * @param filter �ļ�������
	 */
	public static void list6601(String dir, FilenameFilter filter) {
		File file = new File(dir); // ������ dir Ϊ·���� File ����
		File[] files = file.listFiles(filter); // �� file �µ������ļ���Ŀ¼

		try {
			for (File f : files) {
				System.out.println(f.getName());
			}
			
		} catch (Exception e) {
			System.out.println("Ŀ¼Ϊ�ջ������·������");
		}
	}

	/**
	 * �ֱ����Ŀ¼�к�׺��Ϊdoc���ļ�������1M���ļ�����������ʵ�֣�
	 * 
	 * @param dir Ŀ¼����
	 */
	public static void useInner6601(String dir) {
		System.out.println("------��׺��Ϊdoc���ļ�------");
		
		list6601(dir, new FilenameFilter() {
			/**
			 * ���˳�Ŀ¼�к�׺Ϊ doc ���ļ�
			 */
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".doc");
			}
		});
		
		System.out.println("\n------�ļ���С���� 1M ���ļ�------");
		list6601(dir, new FilenameFilter() {
			
			/**
			 * ���˳�Ŀ¼���ļ���С���� 1M ���ļ�
			 */
			@Override
			public boolean accept(File dir, String name) {
				File file = new File(dir, name); // ���� File ����
				
				// �ļ���С�ǰ����ֽڼ���ģ���Ҫ����ת��
				Double byteSize = (double) file.length(); // �ļ��Ĵ�С
				// 1MB = 1024KB 1KB = 1024Byte
				Double mbSize = byteSize / (1024*1024);
				return mbSize > 1;
			}
		});
	}

	/**
	 * �ֱ����Ŀ¼�к�׺��Ϊdoc���ļ�������1M���ļ���2019��4���Ժ��޸Ĺ����ļ�����Lambdaʵ�֣�
	 * 
	 * @param dir Ŀ¼����
	 */
	public static void useLambda6601(String dir) {
		System.out.println("------��׺��Ϊdoc���ļ�------");
		/**
		 * ���˳�Ŀ¼�к�׺Ϊ doc ���ļ�
		 */
		list6601(dir, (d, n) -> n.endsWith(".doc"));
		
		System.out.println("\n------�ļ���С���� 1M ���ļ�------");
		/**
		 * ���˳�Ŀ¼���ļ���С���� 1M ���ļ�
		 */
		list6601(dir, (d, n) -> new File(d, n).length() / (1024*1024) > 1);
		
		System.out.println("\n------2019��4���Ժ��޸ĵ��ļ�------");
		/**
		 * ���˳�Ŀ¼��2019������֮���޸ĵ��ļ�
		 */
		list6601(dir, Lambda6601::isLastModifiedDate);
	}
	
	public static boolean isLastModifiedDate(File dir, String name) {
		File file = new File(dir,name); // ���� File ����
		
		// ��ȡ�ļ�����޸ĵ�����
		// �� long������ ת��Ϊʱ���
		Instant timeStamp = Instant.ofEpochMilli(file.lastModified());
		
		// ��ʱ�������ϵͳĬ��ʱ����ת���� LocalDate ����
		LocalDate lastModifiedDate = LocalDateTime.ofInstant(timeStamp, ZoneId.systemDefault()).toLocalDate();
		
		// ���ļ�����޸ĵ�ʱ�䲻��2019��4��1��֮ǰ��2019������֮��)
		return !lastModifiedDate.isBefore(LocalDate.of(2019, 4, 1));
	}
	
	public static void main(String[] args) {
		// ��ʾ��Ϣ
		System.out.println("*���Ŀ¼�к�׺��Ϊdoc���ļ��ʹ���1M���ļ�*");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("����������Ŀ¼·����");
		String dir = scanner.next(); // �û������·��
		
		System.out.println("useInner6601����");
		useInner6601(dir); // �������˷���
		
		System.out.println();
		System.out.println("useLambda6601����");
		useLambda6601(dir); // Lambda ʵ�ֵĹ��˷���
		
		scanner.close();
	}
}
