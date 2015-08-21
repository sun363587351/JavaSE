package socket.udp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class UDPChat extends JDialog {
    private JPanel contentPane;
    private JButton btnSend;
    private JTextField textSend;
    private JTextArea textRece;

    public UDPChat() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnSend);
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        setBounds(300, 300, 0, 0);
        //接受线程
        new Thread(new Rece(0)).start();
    }

    private void onOK() {
        try {
            new Thread(new Send(textSend.getText(), InetAddress.getByName("192.168.17.110"), 0)).start();
            textSend.setText("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPChat dialog = new UDPChat();
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }


    class Send implements Runnable {
        private static final int PORT = 1211;
        private InetAddress inetAddress;
        private int port;
        private String sendData;

        Send(String sendData, InetAddress inetAddress, int port) {
            this.sendData = sendData;
            this.inetAddress = inetAddress;
            this.port = port < 1024 ? PORT : port;
        }

        public void send(String sendData, InetAddress inetAddress, int port) {
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket();
                byte[] buf = sendData.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (datagramSocket != null)
                    datagramSocket.close();
            }
        }

        @Override
        public void run() {
            if (!"".equals(this.sendData)) {
                send(this.sendData, this.inetAddress, this.port);
            } else {
                textSend.setText("信息不能为空");
            }
        }
    }

    class Rece implements Runnable {
        private static final int PORT = 1211;
        private int port;

        Rece(int port) {
            this.port = port < 1024 ? PORT : port;
        }

        public void rece(int port) {
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket(port);
                byte[] buf = new byte[1024];
                //单线程而已
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                    datagramSocket.receive(datagramPacket);
                    String stringData = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                    if ("over".equals(stringData)) {
                        break;
                    }
                    stringBuilder.append(datagramPacket.getAddress().getHostAddress()).append(": ").append(stringData).append('\n');
                    textRece.setText(stringBuilder.toString());
//                    System.out.println(stringData);
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
            }
        }

        @Override
        public void run() {
            rece(this.port);
        }

    }
}



