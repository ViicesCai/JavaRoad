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
  // 聊天结束语
  private static final String QUIT = "**QUIT**";
  private ServerSocket server;
  // 存储所有客户端通信的Socket对象
  private Set<Socket> allSocket = new HashSet<Socket>();
  // 存储从客户端接收到的聊天消息
  private BlockingQueue<String> msgQueue6601 = new LinkedBlockingDeque<String>();

  public MoreChatServer6601() {

    try {
      server = new ServerSocket(18888);
      // 启动向客户端发送信息的线程
      new Thread(this::sendToAllClient6601).start();
      System.out.println("服务器已启动...");
      acceptClients6601();
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "服务器不能启动!");
    }
  }

  // 接收客户端的连接请求
  private void acceptClients6601() {
    while (true) {
      try {
        Socket socket = server.accept();
        allSocket.add(socket);
        // 启动与此客户端通信的线程
        new Receiver6601(socket).start();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "无效的客户端连接!");
        // e.printStackTrace();
      }
    }
  }

  // 从客户端接收聊天信息的线程任务类
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
          // 接收客户端的信息
          strMsg = reader.nextLine();
          strDate = LocalDateTime.now().toString();
          
          if (strMsg.indexOf(QUIT) == 0) {
            stop = true;
            strMsg = strDate + "===" + strMsg.substring(QUIT.length()) + "已下线！===";
            allSocket.remove(socket);
            socket.close();
            reader.close();
          } else {
            strMsg = strDate + "\n" + strMsg;
          }
          // 接收的信息添加到集合中，由发送线程转发给所有客户端
          msgQueue6601.put(strMsg);
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // 给所有客户发送聊天消息的线程体
  public void sendToAllClient6601() {
    String msg = "";
    while (true) {
      try {
        // 从消息队列中取出消息，发送给所有客户端
        msg = msgQueue6601.take();
        for (Socket socket : allSocket) {
          try {
            PrintWriter printer = new PrintWriter(socket.getOutputStream());
            printer.println(msg);
            printer.flush();
          } catch (IOException e) {
            System.out.println("无法发送消息\n" + socket + "\n" + e.getMessage());
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
