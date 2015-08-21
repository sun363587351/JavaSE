package xmlOperaing.dom4JTest;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Created with XMLOperaing.DOM4JTest.
 * User: IFT8
 * Date: 2014/11/11 11:18
 */
public class Test {
    private static final String XML_PATH = "jse\\src\\XMLOperaing\\book.xml";
    private static final String CLASS_XML_PATH = "jse\\src\\XMLOperaing\\class.xml";

    public static void main(String[] args) {
        Test t = new Test();
        Document document = t.loadXML(XML_PATH);
        t.inset(document, "书", 0, "ws", "sadsa");
        t.del(document, "书", 0, "ws", 0);
        t.writeXML(document, XML_PATH);
        t.readXML(document);
        t.readXPath(document, "//作者");

        //模拟servlet
        try {
            Node node = t.loadXML(CLASS_XML_PATH).selectSingleNode("//servlet-class");
            Class<?> clazz = t.getClass().forName(node.getText());
            Object o = clazz.newInstance();
            Method m = clazz.getMethod("servers");
            m.invoke(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Document loadXML(String path) {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    private void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    private void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                //标签
                System.out.print(node.getName() + " ");
                treeWalk((Element) node);
            } else {
                //内容
                System.out.print(node.getText());
            }
        }
    }

    public void readXML(Document document) {
        treeWalk(document);
    }

    private void writeXML(Document document, String path) {
        XMLWriter xmlWriter = null;
        try {
            //可以选择格式化 //压缩格式createCompactFormat
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            //字节流比字符流好在编码问题
            xmlWriter = new XMLWriter(new FileOutputStream(path), outputFormat);
            xmlWriter.write(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void inset(Document document, String fatherTable, int fatherPosition, String table, String content) {
//        document.getRootElement().element(fatherTable).addElement(table).addText(content);
        Element root = document.getRootElement();
        List list = root.elements(fatherTable);
        Element element = (Element) list.get(fatherPosition);
        element.addElement(table).addText(content);
    }

    public void del(Document document, String fatherTable, int fatherPosition, String table, int index) {
        Element root = document.getRootElement();
        List list = root.elements(fatherTable);
        Element fatherElement = (Element) list.get(fatherPosition);
        Element element = (Element) fatherElement.elements(table).get(index);
        fatherElement.remove(element);
    }

    public void readXPath(Document document, String XPath) {
        //需要DOM4J的扩展包jaxen
        List list = document.selectNodes(XPath);
        for (Object element : list) {
            System.out.println("作者:" + ((Element) element).getText());
        }
    }
}
