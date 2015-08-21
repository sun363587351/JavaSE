package socket.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with Socket.TCP.
 * User: IFT8
 * Date: 2014/10/10 21:15
 */


class TCPClient {
    private InetAddress inetAddress;
    private int port;
    private byte[] byteData;

    TCPClient(byte[] byteData, InetAddress inetAddress, int port) {
        this.byteData = byteData;
        this.inetAddress = inetAddress;
        this.port = port;
        init();
    }

    public void init() {
        Socket socket = null;
        try {
            socket = new Socket(this.inetAddress, this.port);
            OutputStream out = socket.getOutputStream();
            System.out.println("Client Sending");
            out.write(this.byteData);
            System.out.println("Client Sended");
            socket.shutdownOutput();
            //回访
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
//                System.out.println("Client Will Read");
                len = in.read(buf);
//                System.out.println("Client Reading len==" + len);
                if (len == -1) {
                    byteArrayOutputStream.writeTo(System.out);
//                    System.out.println("\nClient Readed");
                    socket.shutdownInput();
                    in.close();
                    break;
                } else {
                    byteArrayOutputStream.write(buf, 0, len);
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
