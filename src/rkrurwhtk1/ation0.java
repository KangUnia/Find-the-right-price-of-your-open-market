package rkrurwhtk1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ation0 {
	static String data[][]=new String[10][];
	public static void option_pic(String wnth, String title, int j){//사진
		
	}
	public static int option_cho(String wnth, int mainprice, int j) throws IOException{//조합형
		int count=0;
		Document doc = Jsoup.connect(wnth).timeout(60*1000).userAgent("Mozilla").get();	
		
		ArrayList <Element> stand1 = new ArrayList(doc.select("#hdivStandAloneContainer div[id]"));
		try{
			dataoutput1=new String[1];
			dataoutput1[0]="";
			dataoutputpride1=new String[1];
			dataoutputpride1[0]="";
			 for(int kd=0;kd<stand1.size();kd++){
				String option_list1[]=null;
				System.out.println(stand1.get(kd).attr("id"));
				ArrayList <Element> stand = new ArrayList(doc.select("#hddlStandAloneOrderText_"+kd+" option"));
				option_list1=new String[stand.size()-1];
				for(int k=0;k<stand.size();k++){
					if(!stand.get(k).text().equals("(필수) 선택하세요")){
						
						
						option_list1[k-1]=stand.get(k).text();
					}	
				//	System.out.println(stand.get(k).text());
				}
				for(int k=0;k<option_list1.length;k++){
					System.out.println("데이터"+k+option_list1[k]);
					
				}
				ftitle(option_list1,dataoutput1);
				stand.clear();
			}
			
			count=dataoutput1.length;
		}catch(Exception e){
			System.out.println("옥션 개수 없음");
			count=0;
		}
		return count;
	}
	
	public static int option_full(String wnth, int mainprice, int j){//독립 전체폼있는
		WebDriver driver = new FirefoxDriver();
		driver.get(wnth);
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		WebElement full_option=driver.findElement(By.xpath("//*[@id=\"hrefOptionSearch\"]"));
		full_option.click();
		
	//	for(int k=0;;k++){	
	//		WebElement check1 =driver.findElement(By.xpath("//*[@id=\"htxtOptionName\"]"));
	//		if(check1.getText().equals("htxtOptionName")==true){
	//			break;
	//		}else{
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
	//		}
	//	}
		String list="";
		WebElement full_option_list= driver.findElement(By.xpath("//*[@id=\"hdivResult\"]"));
		list=full_option_list.getText();
		System.out.println("list="+list);
		list=list.replaceAll("[\\n]", ":");
		System.out.println("");
		System.out.println("list1="+list);
		String[] array=null;
		array = list.split(":");
		dataoutput1=new String[array.length];
		dataoutputpride1=new String[array.length];
		
		for(int k=0;k<array.length;k++){
			
			String matchResult3="";//m가격+추가가격
			Pattern p3 = Pattern.compile("\\([+|-].+\\)");
			Matcher m3 = p3.matcher(array[k]);
			int intmatchResult3=0;
			if(m3.find()) {
				matchResult3 = m3.group().replace("(", "").replace(")", "").replace(",", "").replace("원", "");
				intmatchResult3=mainprice+Integer.parseInt(matchResult3);
			}else{
				System.out.println("추가 가격 없음");
			}	
			System.out.println(matchResult3);
			dataoutput1[j+k]=array[k];
			dataoutputpride1[j+k]=intmatchResult3+"";
		}

		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		driver.quit();
		
		return array.length;
	}
	
	static String dataoutput[]=null;
	static String dataoutput1[]=null;
	static String dataoutputpride[]=null;
	static String dataoutputpride1[]=null;
	
	public static void ftitle(String[] arg,String[] arg1) {
		dataoutput=new String[arg.length*arg1.length];
		dataoutputpride=new String[arg.length*arg1.length];
		System.out.println("데이터갯수"+dataoutput.length);
		for(int k=0;k<arg.length*arg1.length;k++){
			dataoutput[k]="";
			dataoutputpride[k]="0";
		}
		
		int nums=0;
		for(int i=0;i<arg.length;i++){
			String matchResult="";
			int intmatchResult=0;
			Pattern p1 = Pattern.compile("\\([+|-].+\\)");
			Matcher m1 = p1.matcher(arg[i]);
			if(m1.find()) {
				matchResult = m1.group().replace("(", "").replace(")", "").replace(",", "").replace("원", "");	
				intmatchResult=Integer.parseInt(matchResult);	
			}
			
			//System.out.println("i"+matchResult);
			
			for(int j=0;j<arg1.length;j++){
				dataoutput[nums]=arg[i]+"|"+arg1[j];
				int jintmatchResult=0;
				if(!dataoutputpride1[j].equals("")){
					jintmatchResult=Integer.parseInt(dataoutputpride1[j]);	
				}
				dataoutputpride[nums]=jintmatchResult+intmatchResult+"";
				//System.out.println("dataoutputpride-"+nums+" "+dataoutputpride[nums]);
				nums++;
			}
		}
		dataoutput1=dataoutput;
		dataoutputpride1=dataoutputpride;
		for(int i=0;i<dataoutput1.length;i++){
			System.out.println("데이터"+i+" "+dataoutput1[i]+" 가격 "+i+"="+dataoutputpride1[i]);
			
		}
	}
	static String datasub[]=null;
	static String datasubpride[]=null;
	
	public static int onelist(String wnth, String title, int j) throws IOException{
		int count=0;
		data[0][j]=wnth; data[1][j]=title;
		
		Document doc = Jsoup.connect("http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=A796564423&scoredtype=0").timeout(60*1000).userAgent("Mozilla").get();	
		Elements main_Price= doc.select("tr td.discount div.dis.mprice span");
		Iterator<Element> suboption = doc.select("td option").iterator();
		int price=Integer.parseInt(main_Price.text().replace(",", ""));
		System.out.println("main 가격 ="+price);
		data[2][j]=main_Price.text().replace(",", "");
		
		try{
			suboption.next();
			System.out.println("추가구성");
			int sub_option_count=0;
			while(suboption.hasNext()){//추가구성
				String sub_option = suboption.next().text().replaceAll("^[\\d]\\.", "");
				System.out.println(sub_option);
				
				String matchResult="";//m가격+추가가격
				Pattern p1 = Pattern.compile("\\([+|-].+\\)");
				Matcher m1 = p1.matcher(sub_option);
				int matchResult1=0;
				if(m1.find()) {
					matchResult = m1.group().replace("(", "").replace(")", "").replace(",", "").replace("원", "");
				}
				matchResult1=price+Integer.parseInt(matchResult);
				
				System.out.println(matchResult);
			
				if(!sub_option.equals("선택안함")){
					datasubpride[j+sub_option_count]=matchResult1+""; datasub[j+sub_option_count]=sub_option;
					sub_option_count++;	
				}
			}
			System.out.println("추가옵션 갯수="+sub_option_count);
			count=sub_option_count;
		}catch(Exception e){
			System.out.println("추가구성 없음");
		}
		//////////////////////////////
		
		
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		
		WebClient webClient = new WebClient();
		 
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.waitForBackgroundJavaScript(50000);
		//ESPN 페이지 접속
		HtmlPage page = (HtmlPage)webClient.getPage("http://search.auction.co.kr/search/search.aspx?keyword=e%B6%F3%C7%C7%B5%E5&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=e%B6%F3%C7%C7%B5%E5&acode=SRP_SU_0100&encKeyword=e%25EB%259D%25BC%25ED%2594%25BC%25EB%2593%259C");
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
		//System.out.println(page.asXml());
		
		data[0][0]="주소"; data[1][0]="제목"; data[2][0]="main 가격"; data[3][0]="m가격+옵션가격"; data[4][0]="m가격+추가가격"; data[5][0]="m가격+옵션+추가가격"; data[6][0]="옵션제목"; data[7][0]="옵션가격"; data[8][0]="추가제목"; data[9][0]="추가가격";
		
		
		
	}
}
