package java6601.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JOptionPane;

public class MoreChatServer6601 {
  // ���������
  private static final String QUIT = "**QUIT**";
  private ServerSocket server;
  // �洢���пͻ���ͨ�ŵ�Socket����
  private Set<Socket> allSocket = new HashSet<Socket>();
  // �洢�ӿͻ��˽��յ���������Ϣ
  private BlockingQueue<String> msgQueue6601 = new LinkedBlockingDeque<String>();

  public MoreChatServer6601() {

    try {
      server = new ServerSocket(18888);
      // ������ͻ��˷�����Ϣ���߳�
      new Thread(this::sendToAllClient6601).start();
      System.out.println("������������...");
      acceptClients6601();
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "��������������!");
    }
  }

  // ���տͻ��˵���������
  private void acceptClients6601() {
    while (true) {
      try {
        Socket socket = server.accept();
        allSocket.add(socket);
        // ������˿ͻ���ͨ�ŵ��߳�
        new Receiver6601(socket).start();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "��Ч�Ŀͻ�������!");
        // e.printStackTrace();
      }
    }
  }

  // �ӿͻ��˽���������Ϣ���߳�������
  class Receiver6601 extends Thread {
    Socket socket;

    public Receiver6601(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        Scanner reader = new Scanner(socket.getInputStream());
        
        boolean stop = false;
        String strDate, strMsg;
        while (!stop) {
          // ���տͻ��˵���Ϣ
          strMsg = reader.nextLine();
          strDate = LocalDateTime.now().toString();
          
          if (strMsg.indexOf(QUIT) == 0) {
            stop = true;
            strMsg = strDate + "===" + strMsg.substring(QUIT.length()) + "�����ߣ�===";
            allSocket.remove(socket);
            socket.close();
            reader.close();
          } else {
            strMsg = strDate + "\n" + strMsg;
          }
          // ���յ���Ϣ��ӵ������У��ɷ����߳�ת�������пͻ���
          msgQueue6601.put(strMsg);
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // �����пͻ�����������Ϣ���߳���
  public void sendToAllClient6601() {
    String msg = "";
    while (true) {
      try {
        // ����Ϣ������ȡ����Ϣ�����͸����пͻ���
        msg = msgQueue6601.take();
        for (Socket socket : allSocket) {
          try {
            PrintWriter printer = new PrintWriter(socket.getOutputStream());
            printer.println(msg);
            printer.flush();
          } catch (IOException e) {
            System.out.println("�޷�������Ϣ\n" + socket + "\n" + e.getMessage());
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String args[]) {
    new MoreChatServer6601();
  }
}
