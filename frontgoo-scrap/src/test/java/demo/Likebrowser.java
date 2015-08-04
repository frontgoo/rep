package demo;

import java.io.IOException;

import org.frontgoo.scrap.util.ScrapUtil;
import org.frontgoo.util.FileUtil;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Likebrowser {

	public static void main(String[] args) throws IOException, Exception {
		long starttime = System.currentTimeMillis();
		String baseurl = "http://shop1409676194110.1688.com/page/offerlist.htm?pageNum=";
		int pageCount = 0;
		int run = 1;
		StringBuilder allInfos = new StringBuilder();
		String firstUrl = baseurl + run;
		Connection conn = Jsoup.connect(firstUrl);
		ScrapUtil.setHead(conn,15000);
		Response response = conn.ignoreContentType(true).method(Method.GET).execute();
		String content = response.body();
		HtmlCleaner hc = new HtmlCleaner();
		TagNode tn = hc.clean(content);
		String xpathPageCount = "//em[@class='page-count']/text()";
		String xpathItemUrl = "//div[@class='image']/a/@href";
		String xpathItemtitle = "//div[@class='image']/a/@title";
		String xpathItemImgUrl = "//div[@class='image']/a/img/@data-lazy-load-src";
		Object[] itemPageCount = tn.evaluateXPath(xpathPageCount);
		pageCount = Integer.parseInt((String) itemPageCount[0].toString());
		System.out.println("总页数：" + pageCount);
		Object[] itemUrls = tn.evaluateXPath(xpathItemUrl);
		Object[] itemtitles = tn.evaluateXPath(xpathItemtitle);
		Object[] itemImgUrls = tn.evaluateXPath(xpathItemImgUrl);
		System.out.println("########## 第" + run + "页商品信息：");
		allInfos.append("########## 第" + run + "页商品信息：\n");
		for (int i = 0; i < itemUrls.length; i++) {
			 System.out.println("-----------------商品"+(i+1)+"信息-----------------");
			 System.out.println("商品链接："+itemUrls[i]);
			 System.out.println("商品链接："+itemtitles[i]);
			 System.out.println("商品链接："+itemImgUrls[i]);
			 System.out.println("-----------------商品"+(i+1)+"信息-----------------");
			allInfos.append("-----------------商品" + (i + 1)
					+ "信息-----------------\n");
			allInfos.append("商品链接：" + itemUrls[i] + "\n");
			allInfos.append("商品标题：" + itemtitles[i] + "\n");
			allInfos.append("商品图片链接：" + itemImgUrls[i] + "\n");
			allInfos.append("-----------------商品" + (i + 1)
					+ "信息-----------------\n");
		}
		while (run < pageCount) {
			run++;
			Thread.sleep((long) ((Math.random() * 10)*1500));
			String doUrl = baseurl + run;
			Connection doconn = Jsoup.connect(doUrl);
			ScrapUtil.setHead(doconn,15000);
			Response doresponse = doconn.ignoreContentType(true).method(Method.GET).execute();
			String doContent = doresponse.body();
			HtmlCleaner doHc = new HtmlCleaner();
			TagNode dotn = doHc.clean(doContent);
			Object[] doitemUrls = dotn.evaluateXPath(xpathItemUrl);
			Object[] doitemtitles = dotn.evaluateXPath(xpathItemtitle);
			Object[] doitemImgUrls = dotn.evaluateXPath(xpathItemImgUrl);
			System.out.println("########## 第" + run + "页商品信息：");
			allInfos.append("########## 第" + run + "页商品信息：\n");
			for (int i = 0; i < doitemUrls.length; i++) {
				 System.out.println("-----------------商品"+(i+1)+"信息-----------------");
				 System.out.println("商品链接："+doitemUrls[i]);
				 System.out.println("商品链接："+doitemtitles[i]);
				 System.out.println("商品链接："+doitemImgUrls[i]);
				 System.out.println("-----------------商品"+(i+1)+"信息-----------------");
				allInfos.append("-----------------商品" + (i + 1)
						+ "信息-----------------\n");
				allInfos.append("商品链接：" + doitemUrls[i] + "\n");
				allInfos.append("商品标题：" + doitemtitles[i] + "\n");
				allInfos.append("商品图片链接：" + doitemImgUrls[i] + "\n");
				allInfos.append("-----------------商品" + (i + 1)
						+ "信息-----------------\n");
			}
		}
		FileUtil.createFileInPathAndWrite("H:\\workSpace\\download",
				"Itemstest1.txt", allInfos.toString());
		System.out.println("共耗时"+(System.currentTimeMillis()-starttime));
		System.out.println("complete!");
	}
}
