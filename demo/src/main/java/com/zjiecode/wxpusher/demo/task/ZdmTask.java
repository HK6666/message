package com.zjiecode.wxpusher.demo.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.bean.Message;
import com.zjiecode.wxpusher.client.bean.MessageResult;
import com.zjiecode.wxpusher.client.bean.Result;
import com.zjiecode.wxpusher.demo.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author : huangkai @date : 2022/1/18 : 3:54 下午
 */
@EnableScheduling
@Component
public class ZdmTask {
	
	@Value("${wxpusher.biz.apptoken}")
	private String appToken;
	
	
	private String uid = "UID_Se2FFn9o8QwuvvztdK2eA2HnkYtZ";
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void getResult() throws IOException {
		JsoupTest jsoupTest = new JsoupTest();
		//获取信息
		String result  = getRequest();
		List<ArticleVo> articleVos = new ArrayList<>();
		Result<List<MessageResult>> message = new Result<>();
		
		if (result!=null){
			
			articleVos = JSON.parseArray(result, ArticleVo.class);
			System.out.println("List<ArticleVo>:  "+articleVos);
		}
		
		//发送信息
		if (!articleVos.isEmpty()){
			
			//三小时最火信息 数码类历史低价
			message =  sendWechat(articleVos, jsoupTest.getDigitalHtml());
		}
		
		System.out.println("message: " + message);
	}
	
	
	//发送微信提醒
	public Result<List<MessageResult>> sendWechat(List<ArticleVo> articleVos,  List<ArticleVo> digitalVos){
		Message message = new Message();
		message.setContentType(Message.CONTENT_TYPE_HTML);
		message.setUid(uid);
		message.setAppToken(appToken);
		message.setContent(getHtml(articleVos, digitalVos));
		return WxPusher.send(message);
	}
	
	//生成3小时最热的商品html
	public String getHtml(List<ArticleVo> articleVos, List<ArticleVo> digitalVos){
		StringBuilder sb = new StringBuilder("" +
				"<br /><span style='color:red;'>hk的三小时内全网商品低价监控：</span><br />状态：<span style='color:green;'>成功</span>\"\n" +
				"<br /><br /><br />");
		
		articleVos.forEach(articleVo -> {
			sb.append("<span style='color:green;'>");
			sb.append(articleVo.getValue());
			sb.append("</span> <br /><br /><br />");
		});
		
		sb.append("<br /><span style='color:red;'>数码好物精选：</span><br />状态：<span style='color:green;'>成功</span>\"\n" +
				"<br /><br /><br />");
		
		digitalVos.forEach(digitalVo -> {
			sb.append("<span style='color:green;'>");
			sb.append(digitalVo.getNormalValue());
			sb.append("</span> <br /><br /><br />");
		});
		
		return sb.toString();
	}
	
	//请求地址
	public String getRequest(){
		//获取三小时的时间长
		Date date = new Date();
		Long time = date.getTime()/1000;
		
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = "https://faxian.smzdm.com/json_more?filter=h1s0t104979f0c0&type=a&timesort="+time.toString();
			System.out.println("请求查询三小时最热接口: "+urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
//			System.out.println("result:    "+ result);
			return result;
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	
}
