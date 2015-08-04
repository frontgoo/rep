package demo;

import java.io.IOException;

import org.jsoup.Jsoup;

public class LoginWithCookie {
	
	public static void main(String[] args) throws IOException {
		String url = "http://detail.1688.com/offer/520321798994.html?spm=a2615.7691456.0.0.eVu7nR";
		String content = Jsoup.connect(url).post().html();
	}
}
