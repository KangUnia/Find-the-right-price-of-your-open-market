package rkrurwhtk1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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

 public class gmarket {
	static int chek=0;	 
	static String ex0="";
	static String ex1="";
	static String ex2="";
	static String ex3="";
	static ArrayList <String> ex4=new ArrayList();
	static ArrayList <String> ex5=new ArrayList();
	static ArrayList <String> ex6=new ArrayList();
	static ArrayList <String> ex7=new ArrayList();
	static ArrayList <String> ex8=new ArrayList();
	static ArrayList <String> ex9=new ArrayList();
	static ArrayList <String> ex10=new ArrayList();
	static ArrayList <String[][]> list=new ArrayList();
	static String[][] full_exl=null;
	static String rjatordj="e���ǵ�";
	static String file="C:\\Users\\unia\\Downloads\\ation price.xls";
	public static void main(String[] args) throws Exception{
		String wnth="http://search.auction.co.kr/search/search.aspx?keyword=e%B6%F3%C7%C7%B5%E5&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=e%B6%F3%C7%C7%B5%E5&acode=SRP_SU_0100&encKeyword=e%25EB%259D%25BC%25ED%2594%25BC%25EB%2593%259C";
		//String wnth="http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=A914987406&keyword=%be%c6%c0%cc%c6%d0%b5%e5&scoredtype=2"
		
		
	  	pagefine_1(wnth);
	  	finalfullexl();
		//�˻���
		
		//�������� ���
		
	  	
		
	}
	
	public static void pagefine_1(String args) throws Exception {	
		WebClient webClient = new WebClient();
		 
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getCookieManager().setCookiesEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.waitForBackgroundJavaScript(50000);
		//ESPN ������ ����
		HtmlPage page = (HtmlPage)webClient.getPage(args);
		int pagenum=0;
		List<DomElement> divlist1 = page.getElementsByTagName("div");
		for(DomElement e : divlist1){
			if(e.getAttribute("class").equals("more_wrap")){
				System.out.println("�������� : "+e.getElementsByTagName("div").get(0).asText().replace("1page/", ""));
				pagenum=Integer.parseInt(e.getElementsByTagName("div").get(0).asText().replace("1page/", ""));
			 
			}
		}
		int finalnums=1;
		ArrayList<String> listwnth=new ArrayList();
		ArrayList<String> listtitle=new ArrayList();
		for(int nums=1;nums<=pagenum;nums++){//������ ������
			
			List<DomElement> lilist = page.getElementsByTagName("div"); 
			int num=1;
			for(DomElement e : lilist){
				if(e.getAttribute("class").equals("item_title")){
			
					try{
						System.out.println(finalnums+":"+num+" = " + e.getElementsByTagName("a").get(0).asText());	
						System.out.println(finalnums+":"+num+" = " + e.getElementsByTagName("a").get(0).getAttribute("href"));
						String http=e.getElementsByTagName("a").get(0).getAttribute("href");
						listwnth.add(http);
						listtitle.add(e.getElementsByTagName("a").get(0).asText());
					//	onepage(e.getElementsByTagName("a").get(0).asText(),http);
						
						num++;
						finalnums++;
					}catch(Exception e1){
						
					}
					
					//
				}
			}
			
			if(nums!=pagenum){//���������� �۵� ����ϱ�
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
			}//���������� �۵� ����ϱ�
		}//for�� ������ �ѱ��
		//System.out.println(listwnth.get(1));
		for(int l=72;l<listwnth.size();l++){
			System.out.println("Ƚ��="+l);
			onepage(listtitle.get(l),listwnth.get(l));
		//	onepage(listtitle.get(l),"http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=A665344682&scoredtype=2");
			
		}
		
	}
	public static String stringfind(String arg,String args){
		String matchResult="";//m����+�߰�����
		Pattern p1 = Pattern.compile(args);//���Խ�
		Matcher m1 = p1.matcher(arg);
		if(m1.find()) {
			matchResult = m1.group().replace("(", "").replace(")", "").replace(",", "").replace("��", "");
		}
		System.out.println("���Խİ��="+matchResult);
		return matchResult;
	}
	public static void onepage(String title,String args) throws IOException{//�ּ� �ϳ��� ����
		Document doc = Jsoup.connect(args).timeout(60*1000).userAgent("Mozilla").get();	
		Elements main_Price= doc.select("tr td.discount div.dis.mprice span");
		ArrayList <Element> suboption = new ArrayList(doc.select("td option"));
		
		int price=Integer.parseInt(main_Price.text().replace(",", ""));
		System.out.println("main ���� ="+price);
		System.out.println("�߰����� ã��");
		
		ex1=args;
		ex2=title;
		ex3=price+"";
		if(suboption.size()==0){
			ex5.add(price+"");
			ex9.add("");
			ex10.add("0");
			System.out.println("�߰����� ����");
		}else{//�߰� ���� ����
			
			
			for(int k=0;k<suboption.size();k++){
				if(!suboption.get(k).text().equals("���þ���")){
					ex9.add(suboption.get(k).text().replaceAll("^[\\d]\\.", ""));
					String result1=stringfind(suboption.get(k).text().replaceAll("^[\\d]\\.", ""),"\\([+|-].+\\)");
					if(result1.equals("")){
						result1="0";	
					}
					ex10.add(result1);
					ex5.add((price+Integer.parseInt(result1))+"");
				}
			}
		}
		
		//�ɼ� ã��//
		Elements indp= doc.select("div.info-option a#hrefOptionSearch.toggle-button span.button-text");//����
		Elements chose= doc.select("div #hdivStandAloneContainer div[id]");//����
		Elements pict= doc.select("ul li label[class]");//�׸�
		if(indp.text().equals("�ֹ��ɼ� ��ü����")){//����
			System.out.println("���� Ǯ �ɼ�");
			full_option(args);
		}else if(pict.attr("class").equals("image-option")){//�׸�
			System.out.println("�׸����� ��");
			pic_option(doc);
		}else if(chose.attr("id").contains("tblStandAlone")){//����
			System.out.println("���յ�");
			cho_option(doc);
		}else {//��� ���� ��
			System.out.println("�ɼ� ����");
			ex7.add("");
			ex4.add(ex3);
			ex8.add("0");
		}
		
		/////////data.data0[0][1]ä���
		if(ex7.size()>=ex9.size()){
			ex0=ex7.size()+"";	
		}else{
			ex0=ex9.size()+"";
		}
		/////////m����+�ɼ�+�߰� ����/////
		
		
		for(int k=0;k<Integer.parseInt(ex0);k++){
			int option_price1=0;
			if(k<ex8.size()){
				option_price1=Integer.parseInt(ex8.get(k));	
			}
			int subotion_price1=0;
			if(k<ex10.size()){
				subotion_price1=Integer.parseInt(ex10.get(k));
			}
			ex6.add((price+option_price1+subotion_price1)+"");
		}
		String[][] full_exl=new String[Integer.parseInt(ex0)+1][11];
		for(int i=0;i<full_exl.length;i++){
			for(int j=0;j<full_exl[0].length;j++){
				full_exl[i][j]="";
			}
		}
		//
		full_exl[0][0]=ex0; full_exl[0][1]=ex1; full_exl[0][2]=ex2; full_exl[0][3]=ex3;
		for(int i=0;i<full_exl.length;i++){
			try{
				full_exl[i][4]=ex4.get(i);	
			}catch(Exception e){}
			try{
				full_exl[i][5]=ex5.get(i);		
			}catch(Exception e){}
			try{
				full_exl[i][6]=ex6.get(i);	
			}catch(Exception e){}
			try{
				full_exl[i][7]=ex7.get(i);
			}catch(Exception e){}
			try{
				full_exl[i][8]=ex8.get(i);	
			}catch(Exception e){}
			try{
				full_exl[i][9]=ex9.get(i);	
			}catch(Exception e){}
			try{
				full_exl[i][10]=ex10.get(i);
			}catch(Exception e){}
		}
		
		
		
		for(int i=0;i<full_exl.length;i++){
			for(int j=0;j<full_exl[0].length;j++){
				System.out.print("full_exl["+i+"]["+j+"]"+full_exl[i][j]+"	");
			}
			System.out.println("");
		}
		
		
	
		File f=new File(file);
	//	if(chek==0){
	////		try {
	//			simpleExcelWrite(f ,rjatordj,full_exl);
	//			chek++;
	//		} catch (Exception e) {
				// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			System.out.println("���� ���� ����");
	//		}	
	//	}else{
	//		try {
	//			simpleExcelWrite2(f ,full_exl);
	//			chek++;
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//			System.out.println("���� ���� ����");
	//		}	
	//	}
		list.add(full_exl);
		ex0=null;
		ex1=null;
		ex2=null;
		ex3=null;
		ex4.clear();
		ex5.clear();
		ex6.clear();
		ex7.clear();
		ex8.clear();
		ex9.clear();
		ex10.clear();
		full_exl=null;
		
	}
	public static void full_option(String wnth){
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
		list=list.replaceAll("[\\n]", ":");
		String data7[]=list.split(":");
		for(String e: data7){
			ex7.add(e);	
		}
		for(int k=0;k<ex7.size();k++){
			String result2=stringfind(ex7.get(k),"\\([+|-].+\\)");
			if(result2.equals("")){
				result2="0";
			}
			ex8.add(result2);
			ex4.add((Integer.parseInt(ex3)+Integer.parseInt(result2))+"");
		}
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		driver.quit();

	}
	public static void cho_option(Document doc){
			ex7.add("");
			ex8.add("0");
			ArrayList <Element> stand = new ArrayList(doc.select("#hdivStandAloneContainer div[id]"));
			
			for(int kd=0;kd<stand.size();kd++){
				System.out.println(stand.get(kd).attr("id")+"|"+stand.size());
				ArrayList <Element> stand_sodyd = new ArrayList(doc.select("div #hddlStandAloneOrderText_"+kd+" option"));
				ArrayList <String> option_list1 = new ArrayList();
				
				
				for(int k=0;k<stand_sodyd.size();k++){
					if(!stand_sodyd.get(k).text().contains("(�ʼ�) �����ϼ���")){		
						option_list1.add(stand_sodyd.get(k).text());
					}				
				}
				ftitle(option_list1);
				stand_sodyd.clear();
			}
			
			for(int k=0;k<ex7.size();k++){
				ex4.add((Integer.parseInt(ex3)+Integer.parseInt(ex8.get(k)))+"");
				
			}
			
			
			
	}
	public static void ftitle(ArrayList<String> arg) {
		ArrayList <String> dataoutput=new ArrayList();
		ArrayList <String> dataoutputpride=new ArrayList();
		
		int nums=0;
		for(int i=0;i<arg.size();i++){
			String price_new=stringfind(arg.get(i),"\\([+|-].+\\)");
			if(price_new.equals("")){
				price_new="0";
			}
			for(int j=0;j<ex7.size();j++){
				dataoutput.add(arg.get(i)+"|"+ex7.get(j));
				String price_old=ex8.get(j);
				dataoutputpride.add((Integer.parseInt(price_new)+Integer.parseInt(price_old))+"");
			}
		}
		ex7=dataoutput;
		ex8=dataoutputpride;
	}
	public static void pic_option(Document doc){
		Elements main_Price=doc.select("div#ucOrderInfo_ucRequest_rpStandAlone__ctl0_hdivStandAloneObjective ul li img[alt]");

		int k=0;
		for(Element de: main_Price ){
			ex7.add(de.attr("alt"));
			String result3=stringfind(ex7.get(k),"\\([+|-].+\\)");
			if(result3.equals("")){
				result3="0";
			}
			ex8.add(result3);
			ex4.add((Integer.parseInt(ex3)+Integer.parseInt(result3))+"");
			k++;
		}
	}
	public final static void finalfullexl() throws Exception{
		int fnums=0;
		for(int k=0;k<list.size();k++){
			fnums=fnums+Integer.parseInt(list.get(k)[0][0])+1;
			System.out.println("list.get(0)[0][0]="+list.get(k)[0][0]);
			System.out.println("fnums="+fnums);
			
		}
		String[][] final_full_exl=new String [fnums][11];
		int nums=0;
		System.out.println("final_full_exl+fnums"+fnums);
		for(int k=0;k<list.size();k++){
			String[][] e5=list.get(k);
			System.out.println("e5.length="+e5.length);
			System.out.println("e5[0].length="+e5[0].length);
			
			for(int i=0;i<e5.length;i++){
				for(int j=0;j<e5[0].length;j++){
					final_full_exl[i+nums][j]=e5[i][j];
					//System.out.println(e5[i][j]);
				}
			}
			nums=nums+Integer.parseInt(e5[0][0])+1;
		}
		File f=new File(file);
		simpleExcelWrite(f ,rjatordj, final_full_exl);
		
	}
	
	public final static void simpleExcelWrite(File file ,String rjatordj, String data[][]) throws Exception{
	  	  
    	WritableWorkbook workbook = null;
    	WritableSheet sheet = null;
    	  
    	try{
    	   
    		workbook = Workbook.createWorkbook(file);     //������ ���ϸ� ��η� ��ũ�� �� ���������� ����ϴ�.
    		workbook.createSheet(rjatordj, 0);                    //������ ��ũ�Ͽ� ��Ʈ�� ����ϴ�. "Sheet" �� ��Ʈ���� �˴ϴ�.
    		sheet = workbook.getSheet(0);                         //��Ʈ�� �����ɴϴ�.
    	   
    		WritableCellFormat cellFormat = new WritableCellFormat();    //���� ��Ÿ���� �����ϱ� ���� �κ��Դϴ�.
    		cellFormat.setBorder(Border.ALL , BorderLineStyle.THIN);      //���� ��Ÿ���� �����մϴ�. �׵θ��� ���α׸��°ſ���
    	   
    		// ���ۺ��� �����鼭 ������ �����͸� �ۼ��մϴ�.
    		String[][] data2=new String [1][11];
    		data2[0][0]="������ȣ";data2[0][1]="�ּ�";data2[0][2]="����";data2[0][3]="main ����";data2[0][4]="(�ɼ�+main)����";data2[0][5]="(�߰�����+main)����";data2[0][6]="(�ɼ�+�߰�����+main)����";data2[0][7]="�ɼ� ����";data2[0][8]="�ɼ� ����";data2[0][9]="�߰����� ����";data2[0][10]="�߰����� ����";
    		
    		for(int col = 0 ; col < data[0].length ; col++){
    			Label label1 = new jxl.write.Label(col , 0 ,  data[0][col] , cellFormat);
    			sheet.addCell(label1);
    		}
    		
    		for(int row = 0 ; row<data.length ; row ++){
    			for(int col = 0 ; col < data[0].length ; col++){
    				
    				if(row==0){
    					Label label = new jxl.write.Label(col , 0 , (String) data2[0][col] , cellFormat);
    					sheet.addCell(label);
    				}
	    			Label label = new jxl.write.Label(col , row+1 , (String) data[row][col] , cellFormat);	
	    			sheet.addCell(label);
    				
    			}
    		}
    	   
    		workbook.write();
    		System.out.println("���� ���Ⱑ �Ϸ� �Ǿ����ϴ�.");
    	   
    	}catch (Exception e){
    		e.printStackTrace();
    		throw e;
    	}finally{
    		try{
    			if(workbook != null) workbook.close();
    		}catch (WriteException e){
    			//e.printStackTrace();
    		}catch (IOException e){
    			//e.printStackTrace();
    		}
    	}
    	  
    
	}
}


