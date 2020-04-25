/**
 * 
 */
package java6601.lesson14.synchronized6601;

/**
 * 启动线程
 * 
 * @author CAI
 *
 */
public class StudyPutAndGet6601 {
	public static void main(String[] args) {
		Product6601 item = new Product6601();

		new Thread(new 生产者6601(item)).start();
		new Thread(new 消费者6601(item)).start();
	}
}
