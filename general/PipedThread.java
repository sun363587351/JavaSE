package general;

import java.io.*;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/4 17:27
 */
class PipedReader implements Runnable {
    private PipedInputStream pipIn;

    PipedReader(PipedInputStream pipIn) {
        this.pipIn = pipIn;
    }

    @Override
    public void run() {
        byte[] buf = new byte[5];
//      //这里测试用数据编码为UTF-8 中文占3字节 原本开辟缓冲区大小应该为3的倍数
        //为避免编码问题引入字节流缓存
//        BufferedOutputStream bos=new BufferedOutputStream(System.out);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//字符缓存流 没有使用系统资源不需要关闭
        try {
            while (true) {
                int len = pipIn.read(buf);
                if (len == -1) {
                    byteArrayOutputStream.writeTo(System.out);//测试用 输出查看
//                    bos.close();
                    pipIn.close();
                    break;
                }
//                    bos.write(buf,0,len);
                byteArrayOutputStream.write(buf, 0, len);//写入缓存流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PipedWriter implements Runnable {
    private PipedOutputStream pipOut;
    private String info;

    PipedWriter(PipedOutputStream pipOut, String info) {
        this.pipOut = pipOut;
        this.info = info;
    }

    @Override
    public void run() {
        try {
            pipOut.write(info.getBytes());
            pipOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class PipedThread {
    public static void main(String[] args) {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        String testStr = "测试用数据\n的时间疯狂了但是桑德菲杰克里斯丁你的是看见了的减肥了数据来看你疯了的你饥渴你几十" +
                "块能否将快女开发贷款快递费女看见的妇女看见的内裤的可能发能看得见空间的奶粉看见的疯狂加能看得见看见的放空间" +
                "打开极度恐惧看到放空间女剑客女看见放得开快递费空军的疯狂军的空间放牛班艰苦奋斗年把框架内的疯狂金杯你看";
        try {
            in.connect(out);
            PipedWriter pipedWriter = new PipedWriter(out, testStr);
            PipedReader pipedReader = new PipedReader(in);
            new Thread(pipedWriter).start();
            new Thread(pipedReader).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

