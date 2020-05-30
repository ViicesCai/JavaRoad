/**
 * 
 */
package java6601.udp.comm;

/**
 * 双向通信的接收端
 * 
 * @author CAI
 *
 */
public class CommByReceive {
	
	public static void main(String[] args) {
		System.out.println("启动 JF");
		int port = 8888; // 当前进程的 端口号
		String toAddr = "localhost"; // 接收端的地址：发送用
		int toPort = 9033; // 接收端的端口：发送用
		
		int targetPort = 9011; // 发送端的端口号：接收用
		 
		new Thread(new Send(port, toAddr, toPort)).start();
		new Thread(new Receive(targetPort, "CAI")).start();
	}
}
