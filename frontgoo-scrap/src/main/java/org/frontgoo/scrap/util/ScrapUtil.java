package org.frontgoo.scrap.util;

import org.jsoup.Connection;

public class ScrapUtil {

	public static void setHead(Connection conn,int timeout) {
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
		conn.timeout(timeout);
	}
}
