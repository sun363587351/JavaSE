package servers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created with Servers.
 * User: IFT8
 * Date: 2014/11/3 21:35
 */
public class Test {
    public static void main(String[] args) {
        MyServers myServers = new MyServers(80);
        Thread thread = new Thread(myServers);
        Scanner scanner = new Scanner(System.in);
        thread.start();
        if ("over".equals(scanner.next())) {
            myServers.stop();
            try {
                Socket socket = new Socket(InetAddress.getLocalHost(), 80);
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
