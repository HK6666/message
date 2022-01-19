package com.zjiecode.wxpusher.demo.task;

import com.zjiecode.wxpusher.demo.vo.ArticleVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : huangkai @date : 2022/1/19 : 2:53 下午
 */
@Service
public class JsoupTest {
	
	public List<ArticleVo> getDigitalHtml() throws IOException {
		Document doc = Jsoup.connect("https://www.smzdm.com/jingxuan/xuan/s0f163t0b0d0r0p1/").get();
		String title = doc.title();
		Elements elements = doc.select("div.z-feed-content");
		elements.text();
		
		List<ArticleVo> articleVos = new ArrayList<>();
		for (Element element :elements){
			String goodsTitle = element.text();
			String price = element.select("a.z-highlight").text();
			String goodsSmallTitle = element.select("h5.feed-block-title").text();
			
			ArticleVo articleVo = new ArticleVo();
			articleVo.setArticlePrice(price);
			articleVo.setArticleTitle(goodsSmallTitle);
			
			articleVos.add(articleVo);
		}
		
		return articleVos;
	}
	

}
