package socket.tcp;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientDemo {
    public static void main(String[] args) {

        try {
            InputStream in = new FileInputStream("D:\\Client");
            byte[] buf = new byte[1024*1024];
            int len;
            ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
            while (true) {
                len = in.read(buf);
                if (len == -1) {
                    new TCPClient(bufOut.toByteArray(), InetAddress.getLocalHost(), 1211);
                    break;
                } else {
                    bufOut.write(buf, 0, len);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



