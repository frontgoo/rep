package org.frontgoo.scrap.worker;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.frontgoo.scrap.ali.dto.ColorSizeDto;
import org.frontgoo.scrap.ali.dto.SkuDto;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;

public class UrlParseWorker implements Callable<SkuDto>{
		
		public UrlParseWorker(String url) {
			this.url = url;
		}

		private String url;

		public SkuDto parseUrl(String url) throws Exception {
//			String url = "http://detail.1688.com/offer/520281948908.html?spm=a2615.7691456.0.0.t3aqPX";
			String content = Jsoup.connect(url).post().html();
			HtmlCleaner hc = new HtmlCleaner();
			TagNode tn = hc.clean(content);
			String xpath = "//script";
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
			JSONObject skuMap = skuJsonObject.getJSONObject("skuMap");
			Map<String, ColorSizeDto> map = new HashedMap();
			Set<?> set = skuMap.keySet();
			for (Object object : set) {
				String	key = (String)object;
				String value = skuMap.getString(key);
				ColorSizeDto colorSizeDto = (ColorSizeDto) JSONObject.toBean(JSONObject.fromObject(value), ColorSizeDto.class);
				map.put(key, colorSizeDto);
			}
			skuDto.setSkuMap(map);
			System.out.println(skuMap);
			return skuDto;
		}
		
		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		public SkuDto call() throws Exception {
			return parseUrl(url);
		}
	}
