package servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created with Servers.
 * User: IFT8
 * Date: 2014/11/3 20:41
 */
public class MyServers implements Runnable {
    private static final int BYTE_MAX_SIZE = 1024 * 8; //数据最大限制为8KB
    private static final String ROOT_PATH = "D:\\web";//根目录
    private static final String DOMAIN = "http://127.0.0.1";//域名

    private int port;//监听端口号
    private boolean state = true;

    public MyServers(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (state) {
            init();
        }
    }

    public void stop() {
        state = false;
    }

    private void init() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //开启服务器
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            //获取客户端数据
            InputStream in = socket.getInputStream();
            //获取响应头
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //获取响应头GET部分
            String headGet = br.readLine();
            //判断是不是响应头GET部分
            if (headGet == null) {
                return;
            }
            if ( !"GET".equals(headGet.substring(0, 3))) {
                System.out.println("--Head--不是GET--" + headGet);
                return;
            }
            //截取GET
            String headGets[] = headGet.split(" ");
            //获取请求路径
            String requestPath = headGets[1];
            OutputStream out = socket.getOutputStream();
            //调用方法处理请求
            dealRequest(out, requestPath);
            br.close();
        } catch (IOException e) {
            System.out.println("连接中断");
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("服务器关闭失败");
                }
        }
    }

    private void dealRequest(OutputStream out, String path) {
        System.out.println("--GET--:" + path);
        FileInputStream fileInputStream = null;
        try {
            //是否需要重定向
            String redirectPath = findRedirect(path);
            if (!path.equals(redirectPath)) {
                //发送响应头
                out.write("HTTP/1.1 302 Found\n".getBytes());
                //地址
                out.write(("Location:" + DOMAIN + redirectPath + "\n\n").getBytes());
                return;
            } else {
                //发送响应头
                out.write("HTTP/1.1 200 OK\n".getBytes());
            }
            //如果以/结尾则查找映射页面
            if (path.endsWith("/")) {
                path = findMappingPath(path);
            }
            //打开发送页面文件
            fileInputStream = new FileInputStream(ROOT_PATH + path);
            byte[] buf = new byte[BYTE_MAX_SIZE];
            int len;
            //文件后缀转MIME
            int position = path.lastIndexOf(".");
            String fileType = "html";
            if (position != -1) {
                fileType = path.substring(position, path.length());
            }
            //文件类型
            out.write(("Content-type:" + findMIME(fileType) + "\n").getBytes());
            //地址
            out.write(("Location:" + DOMAIN + path + "\n\n").getBytes());
            //发送数据
            while ((len = fileInputStream.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            System.out.println("--POST--" + path);
        } catch (FileNotFoundException e) {
            try {
                //文件类型
                out.write("Content-type:text/html\n\n".getBytes());
                FileInputStream fin404 = new FileInputStream(ROOT_PATH + "\\404.html");
                byte[] buf = new byte[BYTE_MAX_SIZE];
                int len;
                //发送数据
                while ((len = fin404.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                fin404.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String findMappingPath(String path) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(ROOT_PATH + "\\mapping.properties");
            properties.load(fileInputStream);
            String tmpString = properties.getProperty(path);
            if (tmpString != null) {
                path = tmpString;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    private String findRedirect(String path) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(ROOT_PATH + "\\redirect.properties");
            properties.load(fileInputStream);
            String tmpString = properties.getProperty(path);
            if (tmpString != null) {
                path = tmpString;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    private String findMIME(String fileType) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(ROOT_PATH + "\\MIME.properties");
            properties.load(fileInputStream);
            String tmpString = properties.getProperty(fileType);
            if (tmpString != null) {
                fileType = tmpString;
            } else {
                fileType = "other/other";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("--POST--" + fileType);
        return fileType;
    }
}
