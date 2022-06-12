package tran;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadClient {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println("客户端运行...");

        try {

//向非本机的8080客户端发出请求

            Socket socket = new Socket("10.69.61.195",8090);

//由Socket获得输出流，并创建缓冲输出流

            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

//创建文件输入流

            FileInputStream fin = new FileInputStream("C://Users//ASUS//Desktop//xn");

//由文件输入流创建缓冲输出流

            BufferedInputStream in = new BufferedInputStream(fin);

//准备一个缓冲区

            byte[] buffer = new byte[1024];

//首次读取文件

            int len = in.read(buffer);

            while(len != -1) {

//数据写入Socket

                out.write(buffer,0,len);

//再次读取文件

                len = in.read(buffer);

            }

            System.out.println("上传成功！");

        } catch(ConnectException e) {

            System.out.println("服务器未启动！");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}