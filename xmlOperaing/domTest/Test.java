package xmlOperaing.domTest;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with XMLOperaing.DOMTest.
 * User: IFT8
 * Date: 2014/11/4 09:48
 */
public class Test {
    private static final String XML_PATH = "jse\\src\\XMLOperaing\\book.xml";

    public static void main(String[] args) {
        Test t = new Test();

        //读XML指定标签内容
        ArrayList<String> contentList = t.readXMLContent(XML_PATH, "书");
        if(contentList!=null) {
            for (String content : contentList) {
                System.out.println(content);
            }
        }
        //插入XML
//        t.insetXML(XML_PATH, "书", 0, "备注", "啦啦啦啦啦");

    }

    private Document getDocument(String xmlPath) {
        Document document = null;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(xmlPath);
        } catch (ParserConfigurationException e) {
            System.out.println("创建Document管理器失败");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("打开XML文件失败");
        }
        return document;
    }

    public ArrayList<String> readXMLContent(String xmlPath, String table) {
        Document document = getDocument(xmlPath);
        if (document == null) {
            return null;
        }
        NodeList nodeList = document.getElementsByTagName(table);
        int len = nodeList.getLength();
        ArrayList<String> contentList = new ArrayList<String>();
        for (int i = 0; i < len; i++) {
            contentList.add(nodeList.item(i).getTextContent());
        }
        return contentList;
    }

    public boolean insetXML(String xmlPath, String fatherTable, int fatherPosition, String table, String content) {
        Document docunment = getDocument(xmlPath);
        if (docunment == null) {
            return false;
        }
        //创建节点
        Element element = docunment.createElement(table);
        //设置节点内容
        element.setTextContent(content);
        //找到插入父节点
        Node node = docunment.getElementsByTagName(fatherTable).item(fatherPosition);
        //插入节点
        node.appendChild(element);
        writeXML(docunment,xmlPath);
        return true;
    }

    private void writeXML(Document docunment, String xmlPath) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //创建DOM输入源
            DOMSource domSource = new DOMSource(docunment);
            //创建DOM输出源
            StreamResult streamResult = new StreamResult(xmlPath);
            //把内存中的DOM的树形结构转换写出到XML
            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException e) {
            System.out.println("创建Transformer失败");
        } catch (TransformerException e) {
            System.out.println("写XML失败");
        }
    }
}
