package com.company;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

//客户端
public class Client extends Socket{
    private  final String SERVER_IP="10.61.119.42";
    private final int SERVER_PORT=8080;
    private Socket client;
    private FileInputStream fis;
    private DataOutputStream dos;

    //建立客户端，并指定接收的服务端IP和端口号
    public Client() throws IOException{
        this.client=new Socket(SERVER_IP,SERVER_PORT);
        System.out.println("成功链接服务端..."+SERVER_IP);
    }

    //向服务端传输文件
    public void sendFile(String url) throws IOException {
        File file=new File(url);
        try {
            fis = new FileInputStream(file);
            //BufferedInputStream bi=new BufferedInputStream(new InputStreamReader(new FileInputStream(file),"GBK"));
            dos = new DataOutputStream(client.getOutputStream());//client.getOutputStream()返回此套接字的输出流
            //文件名、大小等属性
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();
            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;

            while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                dos.write(bytes, 0, length);
                dos.flush();
            }
            System.out.println("======== 文件传输成功 ========");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("客户端文件传输异常");
        }finally{
            fis.close();
            dos.close();
        }
    }
    public static void main(String[] args) {
        try {
            Client client = new Client(); // 启动客户端链接
            client.sendFile("C:/Users/ASUS/IdeaProjects/Airdrop/client/hello.txt"); // 传输文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}