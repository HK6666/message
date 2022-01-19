package com.zjiecode.wxpusher.demo.vo;

import lombok.Data;

/**
 * @author : huangkai @date : 2022/1/18 : 4:23 下午
 */
@Data
public class ArticleVo {
	//标题
	private String articleTitle;
	
	//价格
	private String articlePrice;
	
	//商城
	private String articleMall;
	
	//链接
	private String articleLink;
	
	//文章地址
	private String articleUrl;
	
	public String getValue(){
		StringBuilder s = new StringBuilder();
		s.append("商品标题: "+this.articleTitle+" ");
		s.append("<span style='color:red;'>商品价格: "+this.articlePrice+"</span> ");
		s.append("商城: "+this.articleMall+" ");
		s.append("商品链接: "+this.articleLink+" ");
		s.append("文章地址: "+this.articleUrl+" ");
		return s.toString();
	}
}
