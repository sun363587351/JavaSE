package socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with Socket.TCP.
 * User: IFT8
 * Date: 2014/10/10 22:04
 */
class TCPServers {
    private int port;
    private int backlog;
//    private byte[] byteSend;

    TCPServers(int port, int backlog) {
//        this.byteSend = byteSend;
        this.port = port;
        this.backlog = backlog;
        init();
    }


    class ServerThread implements Runnable {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            int total = 1;
            try {
                InputStream in = this.socket.getInputStream();
                byte[] buf = new byte[1024 * 1024];
                int len;

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
//                System.out.println("Servers Will Read");
                    len = in.read(buf);
                    System.out.println("Servers Reading len==" + len);

                    if (len == -1) {
                        File file = new File("D:\\Servers" + total++);
                        while (file.exists()) {
                            file = new File("D:\\Servers" + total++);
                        }
                        byteArrayOutputStream.writeTo(new FileOutputStream(file));
                        System.out.println("\nServers Readed");
                        this.socket.shutdownInput();
                        //回访
                        OutputStream out = socket.getOutputStream();
//                    System.out.println("Servers Sending");
                        out.write("Upload complete".getBytes());
//                    System.out.println("Servers Sended");
                        this.socket.shutdownOutput();
                        out.close();
                        in.close();
                        break;
                    } else {
                        byteArrayOutputStream.write(buf, 0, len);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void init() {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, backlog);
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
