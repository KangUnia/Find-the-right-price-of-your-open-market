package rkrurwhtk1;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ation5 {
	public static void main(String[] args) throws IOException{
		Document doc = Jsoup.connect("http://search.11st.co.kr/SearchPrdAction.tmall?method=getTotalSearchSeller&targetTab=T&isGnb=Y&prdType=&category=&cmd=&pageSize=&lCtgrNo=&mCtgrNo=&sCtgrNo=&dCtgrNo=&fromACK=&semanticFromGNB=&gnbTag=TO&schFrom=&kwd=%BE%C6%C0%CC%C6%D0%B5%E5&adUrl=&adKwdTrcNo=1201405153031707564&adPrdNo=577452271#pageNum%%1").timeout(60*1000).userAgent("Mozilla").get();	
	//	ArrayList <Elements> main_Price1=new ArrayList(doc.select("tr div.info a[href]"));
	//Áö¸¶ÄÏ	Elements main_Price=doc.select("tr div.info a[href]");
//		ArrayList <Elements> main_Price1=new ArrayList(doc.select(""));
		Elements main_Price=doc.select("div.total_area em");
		
		int k=0;
		//System.out.println(doc.html());
		
		for(Element de: main_Price ){
			String d=de.text();
			System.out.println("d="+k+"+"+d);
			k++;
		}
		
	//	System.out.println("°¹¼ö"+main_Price1.size());
	//	for(int i=0;i<main_Price1.size();i++){
	//		String dkd=main_Price1.get(i).attr("href");
	//		System.out.println("e="+i+"+"+dkd);
	//	}
		
	}
}
