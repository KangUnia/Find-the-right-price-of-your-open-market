package rkrurwhtk1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class datawkfy {
	String data[][]=new String[11][];
	ArrayList<String [][]> AllList=new ArrayList();
	
}


class optionfind{
	
}


public class ation4 extends datawkfy{
	public static void onetkdvna(String args){
		
	}
	
	public static void pagefine_1(String args) throws Exception {	
		WebClient webClient = new WebClient();
		 
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.waitForBackgroundJavaScript(50000);
		//ESPN 페이지 접속
		HtmlPage page = (HtmlPage)webClient.getPage(args);
		int pagenum=0;
		List<DomElement> divlist1 = page.getElementsByTagName("div");
		for(DomElement e : divlist1){
			if(e.getAttribute("class").equals("more_wrap")){
				System.out.println("끝페이지 : "+e.getElementsByTagName("div").get(0).asText().replace("1page/", ""));
				pagenum=Integer.parseInt(e.getElementsByTagName("div").get(0).asText().replace("1page/", ""));
			 
			}
		}
		int finalnums=1;
		for(int nums=1;nums<=pagenum;nums++){//페이지 돌리기
			
			List<DomElement> lilist = page.getElementsByTagName("div"); 
			int num=1;
			for(DomElement e : lilist){
				if(e.getAttribute("class").equals("item_title")){
			
					try{
						System.out.println(finalnums+":"+num+" = " + e.getElementsByTagName("a").get(0).asText());	
						System.out.println(finalnums+":"+num+" = " + e.getElementsByTagName("a").get(0).getAttribute("href"));
						String http=e.getElementsByTagName("a").get(0).getAttribute("href");
			//			qhwhvkdlf(http);
						num++;
						finalnums++;
					}catch(Exception e1){
					}
					
					//
				}
			}
			
			if(nums!=pagenum){//마지막에는 작동 취소하기
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
			}//마지막에는 작동 취소하기
		}//for문 페이지 넘기기
		
	}
}