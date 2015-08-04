package org.frontgoo.scrap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import net.sf.json.JSONObject;

import org.frontgoo.scrap.ali.dto.ColorSizeDto;
import org.frontgoo.scrap.ali.dto.SkuDto;
import org.frontgoo.scrap.worker.UrlParseWorker;
import org.frontgoo.util.FileUtil;
import org.frontgoo.util.RegUtil;

public class Ali88Util {

	public static ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
	
	public static void parseItems(List<String> urls) {
		StringBuilder sall = new StringBuilder();
		for (String url : urls) {
			Callable<SkuDto> worker = new UrlParseWorker(url);
			Future<SkuDto> future = pool.submit(worker);
			SkuDto skuDto = null;
			try {
				skuDto = future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			if (null!=skuDto) {
				StringBuilder sBuilder = new StringBuilder();
				String reg = "\\d{11}";
				String id = RegUtil.getRegStr(reg, url);
				sBuilder.append(id+"|");
				Map<String, ColorSizeDto> map = skuDto.getSkuMap();
				sBuilder.append(JSONObject.fromObject(map));
				sall.append(sBuilder.toString());
			}
		}
		FileUtil.createFileInPathAndWrite("H:\\workSpace\\download",
				"ItemsInfos.txt", sall.toString());
	}
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("http://detail.1688.com/offer/520282168538.html");
		urls.add("http://detail.1688.com/offer/44651671191.html");
		parseItems(urls);
	}
}
