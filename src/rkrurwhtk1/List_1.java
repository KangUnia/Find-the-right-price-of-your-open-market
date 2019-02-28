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

public class List_1 {
	String args;
	String wnthspdla;
	String tag;
	String tagrjfmf;
	String tagrjfmf_sodyd;
	String ekdma;
	ArrayList pagefine_1() throws Exception {
		
		Document doc = Jsoup.connect(args).timeout(60*1000).userAgent("Mozilla").get();
		Elements pagenum= doc.select(wnthspdla);
		int num=Integer.parseInt(pagenum.text().replace("1page/", ""));
		System.out.println("총 몇페이지 갯수="+num);
		System.out.println("args="+args);
	//	System.out.println("1");
		System.out.println("tag="+tag);
	//	System.out.println("2");
		System.out.println("tagrjfmf="+tagrjfmf);
	//	System.out.println("3");
		System.out.println("tagrjfmf_sodyd="+tagrjfmf_sodyd);
		//System.out.println("4");
		System.out.println("wnthspdla="+wnthspdla);
		
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.waitForBackgroundJavaScript(50000);
		//ESPN 페이지 접속
		HtmlPage page = (HtmlPage)webClient.getPage(args);
		//System.out.println("사이즈"+page.asText());
		ArrayList<String> newlist=new ArrayList();
		
		for(int nums=1;nums<=num;nums++){
			List<DomElement> lilist = page.getElementsByTagName(tag);
		
			int k=0;
			for(DomElement e : lilist){
				//System.out.println("123"+e.getElementsByTagName("a").get(0));
				if(e.getAttribute(tagrjfmf).equals(tagrjfmf_sodyd)){
					try{
						//System.out.println("dkfjid1="+e.getElementsByTagName("a").get(0).getAttribute("href"));
						newlist.add(e.getElementsByTagName("a").get(0).getAttribute("href"));
						k++;	
					}catch(Exception e1){
					}
				}
			}
			if(nums!=num){//마지막에는 작동 취소하기
				List<DomElement> divlist = page.getElementsByTagName("span");
				for(DomElement e : divlist){
					if(e.getAttribute("class").equals("nxt")){
						System.out.println("===next===1");
						page = e.getElementsByTagName("a").get(0).click();
						break;
					}else if(e.getAttribute("class").equals("num")){
						System.out.println("===next===21");
						List<DomElement> divlist2 = page.getElementsByTagName("a");
						int i=0;
						for(DomElement e2 : divlist2){
							if(e.getAttribute("class").equals("num")&&e2.getAttribute("class").equals("on")&&e2.getAttribute("href").equals("javascript:void(0)")){
								System.out.println("===next===2");
								System.out.println(i+1);
								page = e.getElementsByTagName("a").get(i+1).click();
								break;
							}else if(e.getAttribute("class").equals("num")&&e2.getAttribute("class").equals("")&&e2.getAttribute("onclick").equals("javascript:void(0);")){
								i++;
							}
						}
						break;
					}
				}
			}
		
		}
		return newlist;
	}
	
}
