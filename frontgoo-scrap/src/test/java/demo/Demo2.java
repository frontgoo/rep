package demo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Demo2 {

	public static void main(String[] args) throws IOException, XPatherException, ParserConfigurationException, XPathExpressionException {
		  String url = "http://zhidao.baidu.com/daily";
		  String exp = "//h2/a[contains(@href,'daily')]/@href";

		  String html = null;
		  try {
		    Connection connect = Jsoup.connect(url);
		    html = connect.get().body().html();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		  HtmlCleaner hc = new HtmlCleaner();
		  TagNode tn = hc.clean(html);
		  Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
		  XPath xPath = XPathFactory.newInstance().newXPath();
		  Object result;
		  result = xPath.evaluate(exp, dom, XPathConstants.NODESET);
		  if (result instanceof NodeList) {
		    NodeList nodeList = (NodeList) result;
		    System.out.println(nodeList.getLength());
		    for (int i = 0; i < nodeList.getLength(); i++) {
		      Node node = nodeList.item(i);
//		      System.out.println(node.getNodeValue() == null ? node.getTextContent() : node.getNodeValue());
		    }
		  }
		}
}
