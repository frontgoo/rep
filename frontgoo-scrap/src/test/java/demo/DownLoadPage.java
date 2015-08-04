package demo;

import java.io.IOException;

import org.frontgoo.util.FileUtil;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;

public class DownLoadPage {

	public static void main(String[] args) throws IOException, XPatherException {
		  String url = "http://shop1409676194110.1688.com/page/offerlist.htm";
		  String content = Jsoup.connect(url).post().html();
		  FileUtil.createFileInPathAndWrite("H:\\com\\sunny\\core", "ali1688ItemList.html", content);
		}
}
