/**
 * 
 */
package java6601.udp.comm;

/**
 * 双向通信的发送端
 * 
 * @author CAI
 *
 */
public class CommBySend {
	
	public static void main(String[] args) {
		System.out.println("启动 CAI");
		
		int port = 9999; // 当前进程的 端口号：仅作为该进程的标识
		String toAddr = "localhost"; // 接收端的地址：发送用
		int toPort = 9011; // 接收端的端口：发送用
		
		int targetPort = 9033; // 发送端的端口号：接收用
		
		new Thread(new Receive(targetPort, "JF")).start();
		new Thread(new Send(port, toAddr, toPort)).start();
	}
}
