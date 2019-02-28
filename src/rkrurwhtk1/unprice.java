package rkrurwhtk1;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class unprice{ 
	
	public static void main(String[] args) throws Exception{
		String rjatordj="e라피드";
		System.out.println(URLEncoder.encode(rjatordj));
		String ationwnth="http://search.auction.co.kr/search/search.aspx?keyword="+URLEncoder.encode(rjatordj);
		String gmarketwnth="http://search.gmarket.co.kr/search.aspx?keyword="+URLEncoder.encode(rjatordj);
		String qjsrk11wnth="http://search.11st.co.kr/SearchPrdAction.tmall?method=getTotalSearchSeller&kwd="+URLEncoder.encode(rjatordj);
		String interparkwnth="http://nsearch.interpark.com/dsearch/total.jsp?tq="+URLEncoder.encode(rjatordj);
		  
	//	List_1 ation=new List_1();
		ArrayList<String> newlist=new ArrayList();
	//	ation.args=ationwnth; 
	//	ation.tag="div";
	//	ation.tagrjfmf="class";
	//	ation.tagrjfmf_sodyd="item_title";
	//	ation.wnthspdla="div.more_wrap div.page"; 
		
	//	newlist=ation.pagefine_1();
		
		List_1 qjsrk11=new List_1();
		qjsrk11.args=qjsrk11wnth;
		qjsrk11.wnthspdla="div.total_area em";
		qjsrk11.tag="div";
		qjsrk11.tagrjfmf="class";
		qjsrk11.tagrjfmf_sodyd="pup_title";
		newlist=qjsrk11.pagefine_1();
		
		for(int k=0;k<newlist.size();k++){
			System.out.println("링크="+k);
			System.out.println("링크="+newlist.get(k));	
		}
		
				
	
	}
	
}
