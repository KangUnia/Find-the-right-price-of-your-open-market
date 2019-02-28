
package rkrurwhtk1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class test {
	public static void main(String[] args) throws Exception{
		pagefine_1();
	}
	
	static String args="http://search.auction.co.kr/search/search.aspx?keyword=e%B6%F3%C7%C7%B5%E5";
	static String wnthspdla="div.more_wrap div.page";
	static String tag="div";
	static String tagrjfmf="class";
	static String tagrjfmf_sodyd="item_title";
	public static void pagefine_1() throws Exception {
		
		Document doc = Jsoup.connect(args).timeout(60*1000).userAgent("Mozilla").get();
		Elements pagenum= doc.select(wnthspdla);
		String nums=pagenum.text().replace("1page/", "");
		System.out.println("총 몇페이지 갯수="+nums);
		System.out.println("tag="+tag);
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.waitForBackgroundJavaScript(50000);
		//ESPN 페이지 접속
		HtmlPage page = (HtmlPage)webClient.getPage(args);
		Map<String, String> listlink=new LinkedHashMap<String, String>();
		List<DomElement> lilist = page.getElementsByTagName(tag);
		System.out.println("사이즈"+lilist.size());
		int k=0;
		for(DomElement e : lilist){
			System.out.println("123"+e.getElementsByTagName("a").get(0));
			//if(e.getAttribute(tagrjfmf).equals(tagrjfmf_sodyd)){
				System.out.println(e.getElementsByTagName("a").get(0).getAttribute("href"));
			//	listlink.put(e.getElementsByTagName("a").get(0).getAttribute("href"),k+"");
				k++;
			//}
		}
	//	return listlink;
	}
	
}
