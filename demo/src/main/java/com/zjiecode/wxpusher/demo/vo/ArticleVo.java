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
		s.append("商品标题: "+this.articleTitle+" <br />");
		s.append("<span style='color:red;'>商品价格: "+this.articlePrice+"</span> <br />");
		s.append("所属平台: "+this.articleMall+" <br />");
		s.append("<a href=\""+this.articleLink+"\"\"> 商品链接:  </a>"+this.articleLink+ "<br />");
		s.append("<a href=\""+this.articleUrl+"\"\"> 文章地址:  </a>"+this.articleUrl+ "<br />");
		return s.toString();
	}
	
	public String getNormalValue(){
		StringBuilder s = new StringBuilder();
		s.append("商品标题: "+this.articleTitle+" <br />");
		s.append("<span style='color:red;'>商品价格: "+this.articlePrice+"</span> <br />");
		return s.toString();
	}
}
