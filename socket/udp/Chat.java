package socket.udp;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

/**
 * Created with GUI.
 * User: IFT8
 * Date: 2014/10/5 21:06
 */

public class Chat {
    public static void main(String[] args) {
        new CFrame();
    }
}

class CFrame extends Frame {
    private TextArea textSend = new TextArea();
    private TextArea textRece = new TextArea();
    private InetAddress clientInetAddress;
    StringBuffer stringBuffer = new StringBuffer();
    CFrame() {
        init();
    }


    public void init() {
        //设置位置以及大小
        setBounds(500, 300, 500, 420);
        //添加关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //设置布局
        setLayout(new FlowLayout());
        //添加按钮
        Button btn = new Button("Button");
        add(textRece);
        add(btn);
        add(textSend);
        final Rece rece=new Rece(0);
        try {
            clientInetAddress=InetAddress.getByName("192.168.17.110");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new Thread(new Send(textSend.getText(),clientInetAddress , 0)).start();
                    textSend.setText("");
            }
        });
        //接受线程
        new Thread(rece).start();
        //显示
        setVisible(true);
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
                System.out.println(datagramPacket.getAddress());
                System.out.println(datagramPacket.getPort());
                datagramSocket.send(datagramPacket);
                stringBuffer.append(InetAddress.getLocalHost().getHostAddress()).append(sendData);
                textRece.setText(stringBuffer.toString());
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

                while (true) {
                    DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                    datagramSocket.receive(datagramPacket);
                    String stringData = new String(datagramPacket.getData(), 0, datagramPacket.getLength(),"GBK");
                    if ("over".equals(stringData)) {
                        break;
                    }
                    if (InetAddress.getLocalHost() != datagramPacket.getAddress()) {
                        clientInetAddress = datagramPacket.getAddress();
                    }
                    stringBuffer.append(clientInetAddress.getHostAddress()).append(": ").append(stringData).append('\n');
                    textRece.setText(stringBuffer.toString());
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

