package rkrurwhtk1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ation1 {
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
	
	public static void main(String[] arth) throws IOException{
		Document doc = Jsoup.connect("http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=A850023148&keyword=%be%c6%c0%cc%c6%d0%b5%e5&scoredtype=2").timeout(60*1000).userAgent("Mozilla").get();	
		
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
			
		}catch(Exception e){
			System.out.println("옥션 개수 없음");
		}
		
		
		
		
		
		
	}
}
