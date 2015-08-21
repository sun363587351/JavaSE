package xmlOperaing.saxTest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created with XMLOperaing.SAXTest.
 * User: IFT8
 * Date: 2014/11/10 21:17
 * 只能解析XML不能修改
 */
public class Test {
    private static final String XML_PATH = "jse\\src\\XMLOperaing\\book.xml";

    public static void main(String[] args) {
        Test t = new Test();
        t.loadSAX(XML_PATH);
    }

    private void loadSAX(String path) {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(path, new SAXHandler());
        } catch (ParserConfigurationException e) {
            System.out.println(e);
        } catch (SAXException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("系统找不到指定的文件");
        }
    }

    class SAXHandler extends DefaultHandler {

        private boolean flag = false;
        private int index = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if ("作者".equals(qName)) {
                flag = true;
                index++;
            }
            // System.out.print(qName + " ");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

            if (flag == true && index == 2) {
                System.out.println("找到第二个" + new String(ch, start, length));
                flag = false;
            }
            //System.out.print(new String(ch, start, length));
        }

    }
}
