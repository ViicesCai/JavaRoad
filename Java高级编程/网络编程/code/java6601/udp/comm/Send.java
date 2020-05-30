/**
 * 
 */
package java6601.udp.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * ���ͷ�
 * 
 * @author CAI
 *
 */
public class Send implements Runnable {
	private DatagramSocket client;
	private BufferedReader reader;
	private String toAddr;
	private int toPort;

	public Send(int port, String toAddr, int toPort) {
		super();
		this.toAddr = toAddr;
		this.toPort = toPort;

		try {
			client = new DatagramSocket(port);
			reader = new BufferedReader(new InputStreamReader(System.in));

		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			String data;

			try {
				data = reader.readLine();
				byte[] datas = data.getBytes();

				// 3. ��װ���ݣ���ָ��Ŀ�ĵ�
				DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
						new InetSocketAddress(this.toAddr, this.toPort));

				// 4. �������ݱ�
				client.send(packet);

				if (data.equals("exit")) {
					System.out.println("���Ͷ˽�����");
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 5. �ͷ���Դ
		client.close();
	}
}
