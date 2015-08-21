package general;

import java.io.UnsupportedEncodingException;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/5 13:56
 */
public class EncodeStreamDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String srcStr = "撒娇昆德\n拉是";
        byte[] bytesStr = sendEncode(srcStr, "GBK");
        String desStr=receiveEncode(bytesStr, "GBK");
        System.out.println(desStr);
    }

    public static byte[] sendEncode(String srcStr, String encode) {
        byte[] strBytes = null;
        try {
            //按照设置源String编码为encode转化成字节集
            strBytes = srcStr.getBytes(encode);
        } catch (UnsupportedEncodingException e) {
            System.out.println("未识别的编码名称");
        }
        return strBytes;
    }

    public static String receiveEncode(byte[] srcStrBytes, String encode) {
        String desData = null;
        try {
            //按照源String编码解码
            desData = new String(srcStrBytes, encode);
        } catch (UnsupportedEncodingException e) {
            System.out.println("未识别的编码名称");
        }
        return desData;
    }
}
