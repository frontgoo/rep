package demo;

import java.awt.print.Printable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.frontgoo.scrap.ali.dto.SkuDto;
import org.frontgoo.util.FileUtil;
import org.htmlcleaner.BaseToken;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class Demo {

	// public static void main(String[] args) throws IOException,
	// XPatherException {
	// String url = "http://zhidao.baidu.com/daily";
	// String contents = Jsoup.connect(url).post().html();
	//
	// HtmlCleaner hc = new HtmlCleaner();
	// TagNode tn = hc.clean(contents);
	// String xpath = "//h2/a/@href";
	// Object[] objects = tn.evaluateXPath(xpath);
	// System.out.println(objects.length);
	//
	// }

	public static void parseSignlePage(String[] args) throws IOException,
			XPatherException {
		String url = "http://detail.1688.com/offer/520321798994.html?spm=a2615.7691456.0.0.eVu7nR";
		String content = Jsoup.connect(url).post().html();
		HtmlCleaner hc = new HtmlCleaner();
		TagNode tn = hc.clean(content);
		String xpath = "//script";
		// String xpath = "//h2/a/@href";
		Object[] objects = tn.evaluateXPath(xpath);
		TagNode tagNode = (TagNode) objects[10];
		ContentNode contentNode = (ContentNode) tagNode.getAllChildren().get(0);
		String con = contentNode.getContent();
		Pattern pattern = Pattern
				.compile("(?:iDetailData = )(\\{[\\s\\S]*\\})");
		Matcher matcher = pattern.matcher(con);
		String skuString = null;
		if (matcher.find()) {
			skuString = matcher.group(1);
		}
		JSONObject jsonObject = JSONObject.fromObject(skuString);
		JSONObject skuJsonObject = jsonObject.getJSONObject("sku");
		SkuDto skuDto = (SkuDto) JSONObject.toBean(skuJsonObject, SkuDto.class);
		System.out.println(skuDto);
		System.out.println("该商品可订购总数：" + skuDto.getCanBookCount());
		System.out.println("该商品可订购颜色和尺寸：" + skuDto.getSkuProps());
	}

	public static void main(String[] args) throws IOException, XPatherException {
		String baseurl = "http://shop1409676194110.1688.com/page/offerlist.htm?pageNum=1";
		Connection conn = Jsoup.connect(baseurl);
		conn.header("(Request-Line)", "POST /cgi-bin/login?lang=zh_CN HTTP/1.1");
		conn.header("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.header("Accept-Encoding", "gzip, deflate, sdch");
		conn.header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		conn.header("Cache-Control", "no-cache");// max-age=0
		conn.header("Connection", "Keep-Alive");
		conn.header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		conn.header("Host", "shop1409676194110.1688.com");
		conn.header("Referer", "https://mp.weixin.qq.com/");
		conn.header(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		Response response = conn.ignoreContentType(true).method(Method.POST).execute();
//		Response response = conn.ignoreContentType(true).method(Method.POST)
//				.data(map).execute();
		int pageCount = 0;
		int run = 1;
		StringBuilder allInfos = new StringBuilder();
		String firstUrl = baseurl + run;
		String content = Jsoup.connect(firstUrl).post().html();
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
			// System.out.println("-----------------商品"+(i+1)+"信息-----------------");
			// System.out.println("商品链接："+itemUrls[i]);
			// System.out.println("商品链接："+itemtitles[i]);
			// System.out.println("商品链接："+itemImgUrls[i]);
			// System.out.println("-----------------商品"+(i+1)+"信息-----------------");
			allInfos.append("-----------------商品" + (i + 1)
					+ "信息-----------------\n");
			allInfos.append("商品链接：" + itemUrls[i] + "\n");
			allInfos.append("商品标题：" + itemtitles[i] + "\n");
			allInfos.append("商品图片链接：" + itemImgUrls[i] + "\n");
			allInfos.append("-----------------商品" + (i + 1)
					+ "信息-----------------\n");
		}
		while (run <= pageCount) {
			String doUrl = baseurl + run;
			String doContent = Jsoup.connect(doUrl).post().html();
			HtmlCleaner doHc = new HtmlCleaner();
			TagNode dotn = doHc.clean(doContent);
			Object[] doitemUrls = dotn.evaluateXPath(xpathItemUrl);
			Object[] doitemtitles = dotn.evaluateXPath(xpathItemtitle);
			Object[] doitemImgUrls = dotn.evaluateXPath(xpathItemImgUrl);
			System.out.println("########## 第" + run + "页商品信息：");
			allInfos.append("########## 第" + run + "页商品信息：\n");
			for (int i = 0; i < doitemUrls.length; i++) {
				// System.out.println("-----------------商品"+(i+1)+"信息-----------------");
				// System.out.println("商品链接："+doitemUrls[i]);
				// System.out.println("商品链接："+doitemtitles[i]);
				// System.out.println("商品链接："+doitemImgUrls[i]);
				// System.out.println("-----------------商品"+(i+1)+"信息-----------------");
				allInfos.append("-----------------商品" + (i + 1)
						+ "信息-----------------\n");
				allInfos.append("商品链接：" + doitemUrls[i] + "\n");
				allInfos.append("商品标题：" + doitemtitles[i] + "\n");
				allInfos.append("商品图片链接：" + doitemImgUrls[i] + "\n");
				allInfos.append("-----------------商品" + (i + 1)
						+ "信息-----------------\n");
			}
			run++;
		}
		FileUtil.createFileInPathAndWrite("H:\\workSpace\\download",
				"ItemList1.txt", allInfos.toString());
	}
}
