package tran;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {

    public static void main(String[] args) {

        System.out.println("服务器端运行...");

        try {

//创建一个ServerSocket监听8080端口的客户端请求

            ServerSocket serverSocket = new ServerSocket(8090);

//使用accept()阻塞当前线程，等待客户端请求

            Socket socket = serverSocket.accept();
//由Socket获得输入流，并创建缓冲输入流

            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());

//由文件输出流创建缓冲输出流

            FileOutputStream out = new FileOutputStream("C://Users//ASUS//Desktop//xna//ccc.txt");

//创建一个缓冲区

            byte[] buffer = new byte[1024];

//首次向Socket读取数据

            int len = in.read(buffer);

            while (len != -1) {

//写入数据到文件

                out.write(buffer, 0, len);

//再次从Socket读取数据

                len = in.read(buffer);

            }

            System.out.println("接收完成！");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
