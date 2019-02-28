package rkrurwhtk1;

import java.io.File;
import java.io.IOException;
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

 class datawkfy121211 {
	String[][] data0=new String[11][];
	ArrayList<String [][]> AllList=new ArrayList();
}

public class ation extends datawkfy121211{
	static datawkfy121211 data=new datawkfy121211();
	
	public static void main(String[] args) throws Exception{
		String wnth="http://search.auction.co.kr/search/search.aspx?keyword=e%B6%F3%C7%C7%B5%E5&itemno=&nickname=&arraycategory=&frm=&dom=auction&isSuggestion=No&retry=&Fwk=e%B6%F3%C7%C7%B5%E5&acode=SRP_SU_0100&encKeyword=e%25EB%259D%25BC%25ED%2594%25BC%25EB%2593%259C";
		//String wnth="http://itempage3.auction.co.kr/DetailView.aspx?ItemNo=A914987406&keyword=%be%c6%c0%cc%c6%d0%b5%e5&scoredtype=2"
		pagefine_1(wnth);
		String rjatordj="e���ǵ�";
		//�˻���
		String file="C:\\Users\\unia\\Downloads\\ation price.xls";
		File f = new File(file);
		//�������� ���
		simpleExcelWrite(f ,rjatordj);
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
		for(int l=77;l<listwnth.size();l++){
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
		data.data0[0]=new String[1];
		data.data0[1]=new String[1];
		data.data0[2]=new String[1];
		data.data0[3]=new String[1];
		
		data.data0[1][0]=args;
		data.data0[2][0]=title;
		data.data0[3][0]=price+"";
		if(suboption.size()==0){
			data.data0[5]=new String[1];//���� ��ȣ�� +1
			data.data0[9]=new String[1];
			data.data0[10]=new String[1];
			data.data0[5][0]=price+"";//���� ��ȣ�� +1
			data.data0[9][0]="";
			data.data0[10][0]="0";
			
			System.out.println("�߰����� ����");
		}else{
			
			for(int k=0;k<suboption.size();k++){
				if(suboption.get(k).text().equals("���þ���")){
					suboption.remove(k);
				}
			}
			data.data0[5]=new String[suboption.size()];//���� ��ȣ�� +1
			data.data0[9]=new String[suboption.size()];
			data.data0[10]=new String[suboption.size()];
			int nums=0;
			for(int k=0;k<suboption.size();k++){
				if(!suboption.get(k).text().equals("���þ���")){
					data.data0[9][nums]= suboption.get(k).text().replaceAll("^[\\d]\\.", "");
					data.data0[10][nums]=stringfind(data.data0[9][nums].replace(",", ""),"\\([+|-].+\\)");	
					if(data.data0[10][nums].equals("")){
						data.data0[10][nums]="0";	
					}
					System.out.println(data.data0[10][nums]);
					data.data0[5][nums]=(price+Integer.parseInt(data.data0[10][nums]))+"";		
					nums++;
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
			data.data0[7]=new String[1];
			data.data0[4]=new String[1];
			data.data0[7][0]="";
			data.data0[8]=new String[1];
			data.data0[8][0]="0";
			data.data0[4][0]=data.data0[3][0];
		}
		
		/////////data.data0[0][1]ä���
		if(data.data0[7].length>=data.data0[9].length){
			data.data0[0][0]=data.data0[7].length+"";	
		}else{
			data.data0[0][0]=data.data0[9].length+"";
		}
		/////////m����+�ɼ�+�߰� ����/////
		
		
		
		
		data.data0[6]=new String [Integer.parseInt(data.data0[0][0])];
		for(int k=0;k<data.data0[6].length;k++){
			System.out.println("data.data0[6]"+data.data0[6][k]);
			data.data0[6][k]="";
		}
		for(int k=0;k<data.data0[6].length;k++){
			int option_price1=0;
			if(k<data.data0[8].length){
				option_price1=Integer.parseInt(data.data0[8][k]);	
			}
			int subotion_price1=0;
			if(k<data.data0[10].length){
				subotion_price1=Integer.parseInt(data.data0[10][k]);
			}
			data.data0[6][k]=(price+option_price1+subotion_price1)+"";
		}

		System.out.println("4����"+data.data0[6].length);
		
		for(int i=0;i<data.data0.length;i++){
			for(int j=0;j<data.data0[i].length;j++){
				System.out.print("data.data0["+i+"]["+j+"]"+data.data0[i][j]+"	");
			}
			System.out.println("");
		}
		System.out.println("+");
		
		data.AllList.add(data.data0);
		
		for(int k=0;k<data.data0.length;k++){
			data.data0[k]=null;	
		}
		
		System.out.println("data.Ale2342lList.size()+ "+data.AllList.size());
		
		
		
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
		data.data0[7]=list.split(":");
		data.data0[8]=new String[data.data0[7].length];
		data.data0[4]=new String[data.data0[7].length];
		for(int k=0;k<data.data0[7].length;k++){
				data.data0[8][k]=stringfind(data.data0[7][k],"\\([+|-].+\\)");
				if(data.data0[8][k].equals("")){
					data.data0[8][k]="0";
				}
				data.data0[4][k]=(Integer.parseInt(data.data0[3][0])+Integer.parseInt(data.data0[8][k]))+"";
		}
		
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		driver.quit();

	}
	public static void cho_option(Document doc){
		System.out.println("stand.size()="+222222222);
		
			data.data0[7]=new String[1];
			data.data0[7][0]="";
			data.data0[8]=new String[1];
			data.data0[8][0]="0";
			ArrayList <Element> stand = new ArrayList(doc.select("#hdivStandAloneContainer div[id]"));
			
			for(int kd=0;kd<stand.size();kd++){
				String option_list1[]=null;
				System.out.println(stand.get(kd).attr("id")+"|"+stand.size());
				ArrayList <Element> stand_sodyd = new ArrayList(doc.select("div #hddlStandAloneOrderText_"+kd+" option"));
				option_list1=new String[stand_sodyd.size()-1];
				int nums=0;
				for(int k=0;k<stand_sodyd.size();k++){
					if(!stand_sodyd.get(k).text().contains("(�ʼ�) �����ϼ���")){		
						option_list1[nums]=stand_sodyd.get(k).text();
						System.out.println("option_list1["+nums+"]="+option_list1[nums]);
						nums++;
					}				
				}
				ftitle(option_list1);
				stand_sodyd.clear();
			}
			
			data.data0[4]=new String[data.data0[7].length];
			
			for(int k=0;k<data.data0[4].length;k++){
				data.data0[4][k]=(Integer.parseInt(data.data0[3][0])+Integer.parseInt(data.data0[8][k]))+"";
			}
			
			
			
	}
	public static void ftitle(String[] arg) {
		String dataoutput[]=new String[arg.length*data.data0[7].length];
		String dataoutputpride[]=new String[arg.length*data.data0[7].length];
		System.out.println("�����Ͱ���"+dataoutput.length);
		
		for(int k=0;k<dataoutput.length;k++){
			dataoutput[k]="";
			dataoutputpride[k]="0";
		}
		
		int nums=0;
		for(int i=0;i<arg.length;i++){
			String price_new=stringfind(arg[i],"\\([+|-].+\\)");
			if(price_new.equals("")){
				price_new="0";
			}
			for(int j=0;j<data.data0[7].length;j++){
				dataoutput[nums]=arg[i]+"|"+data.data0[7][j];
				String price_old=data.data0[8][j];
				dataoutputpride[nums]=(Integer.parseInt(price_new)+Integer.parseInt(price_old))+"";
				nums++;
			}
		}
		data.data0[7]=dataoutput;
		data.data0[8]=dataoutputpride;
	}
	public static void pic_option(Document doc){
		Elements main_Price=doc.select("div#ucOrderInfo_ucRequest_rpStandAlone__ctl0_hdivStandAloneObjective ul li img[alt]");
		data.data0[7]=new String[main_Price.size()];
		data.data0[8]=new String[main_Price.size()];
		data.data0[4]=new String[main_Price.size()];
		int k=0;
		for(Element de: main_Price ){
			data.data0[7][k]=de.attr("alt");
			data.data0[8][k]=stringfind(data.data0[7][k],"\\([+|-].+\\)");	
			if(data.data0[8][k].equals("")){
				data.data0[8][k]="0";
			}
			data.data0[4][k]=(Integer.parseInt(data.data0[3][0])+Integer.parseInt(data.data0[8][k]))+"";
		
			k++;
		}
	}
	
	public final static void simpleExcelWrite(File file ,String rjatordj) throws Exception{
		  	  
	  	WritableWorkbook workbook = null;
	  	WritableSheet sheet = null;
	  	
	  	

	  	try{
		    	   
	  		workbook = Workbook.createWorkbook(file);     //������ ���ϸ� ��η� ��ũ�� �� ���������� ����ϴ�.
	  		workbook.createSheet(rjatordj, 0);                    //������ ��ũ�Ͽ� ��Ʈ�� ����ϴ�. "Sheet" �� ��Ʈ���� �˴ϴ�.
	  		sheet = workbook.getSheet(0);                         //��Ʈ�� �����ɴϴ�.
		    	   
	  		WritableCellFormat cellFormat = new WritableCellFormat();    //���� ��Ÿ���� �����ϱ� ���� �κ��Դϴ�.
	  		cellFormat.setBorder(Border.ALL , BorderLineStyle.THIN);      //���� ��Ÿ���� �����մϴ�. �׵θ��� ���α׸��°ſ���
	  		
	  		int cocl=1;
	  		String datacka[][]=new String [1][11];
	  		datacka[0][0]="������ȣ";datacka[0][1]="�ּ�";datacka[0][2]="����";datacka[0][3]="main ����";datacka[0][4]="(�ɼ�+main)����";datacka[0][5]="(�߰�����+main)����";datacka[0][6]="(�ɼ�+�߰�����+main)����";datacka[0][7]="�ɼ� ����";datacka[0][8]="�ɼ� ����";datacka[0][9]="�߰����� ����";datacka[0][10]="�߰����� ����";
	  		
	  		
	  		System.out.println("data.AllList.size()="+data.AllList.size());
	  		String [][] datafull=new String[1][1];
	  		datafull=data.AllList.get(0);
	  		System.out.println("datafull[ik]"+datafull.length);
	  		System.out.println("datafull[ik]"+data.AllList.get(0)[0][0]);
	  		
	  		for(int ik=0;ik<datafull[ik].length;ik++){
	  			for(int jk=0;jk<datafull.length;jk++){
	  				System.out.println("datafull["+ik+"]["+jk+"]"+datafull[ik][jk]);		
	  			}
	  		}
	  		
	  //		for(int k=0;k<data.AllList.size();k++){
	  	//		data1.data0=data.AllList.get(k);
		    		// ���ۺ��� �����鼭 ������ �����͸� �ۼ��մϴ�.
	  	//		System.out.println("-------------------------------------------------");
	  //			for(int k1=0;k1<data1.data0[0].length;k1++){
	  //				System.out.println("data1.data0[0]="+data1.data0[0][k]);
	  //			}
	  //			System.out.println("data1.data0[0].size()="+data1.data0[0].length);
	  	/*		System.out.println("");
	  			for(int k1=0;k<data.data0[1].length;k++){
	  				System.out.println("data.data0[1]="+data.data0[1][k]);
	  			}
	  			System.out.println("data.data0[1].size()="+data.data0[1].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[2].length;k++){
	  				System.out.println("data.data0[2]="+data.data0[2][k]);
	  			}
	  			System.out.println("data.data0[2].size()="+data.data0[2].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[3].length;k++){
	  				System.out.println("data.data0[3]="+data.data0[3][k]);
	  			}
	  			System.out.println("data.data0[3].size()="+data.data0[3].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[4].length;k++){
	  				System.out.println("data.data0[4]="+data.data0[4][k]);
	  			}
	  			System.out.println("data.data0[4].size()="+data.data0[4].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[5].length;k++){
	  				System.out.println("data.data0[5]="+data.data0[5][k]);
	  			}
	  			System.out.println("data.data0[5].size()="+data.data0[5].length);
	  			System.out.println("");
	  			
	  		//	for(int k=0;k<data.data0[6].length;k++){
	  		//		System.out.println("data.data0[6]="+data.data0[6][k]);
	  		//	}
	  		//	System.out.println("data.data0[6].size()="+data.data0[6].length);
	  		//	System.out.println("");
	  			for(int k1=0;k<data.data0[7].length;k++){
	  				System.out.println("data.data0[7]="+data.data0[7][k]);
	  			}
	  			System.out.println("data.data0[7].size()="+data.data0[7].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[8].length;k++){
	  				System.out.println("data.data0[8]="+data.data0[8][k]);
	  			}
	  			System.out.println("data.data0[8].size()="+data.data0[8].length);
	  			System.out.println("");
	  			for(int k1=0;k<data.data0[9].length;k++){
	  				System.out.println("data.data0[9]="+data.data0[9][k]);
	  			}
	  			System.out.println("data.data0[9].size()="+data.data0[9].length);
	  			System.out.println("");
	  			for(int k=0;k<data.data0[10].length;k++){
	  				System.out.println("data.data0[10]="+data.data0[10][k]);
	  			}
	  			System.out.println("data.data0[10].size()="+data.data0[10].length);
	  		*/	System.out.println("");
	  			System.out.println("----------------------------------------------");
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  	//		String full_data[][]=new String [Integer.parseInt(data1.data0[0][0])][11];
		  			
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  	//		for(int i=0;i<full_data.length;i++){
	  	//			for(int j=0;j<full_data.length;j++){
	  					try{
	  	//					full_data[i][j]=data1.data0[j][i];	
	  					}catch(Exception e){
	  						e.printStackTrace();
	  	//					full_data[i][j]="";
	  					}
	  	//				
	  	//			}
	  	//		}
	  			
	  	//		if(k==0){
	  				for(int col = 0 ; col < datacka[0].length ; col++){
	  					cocl=1;	
	  					Label label1 = new jxl.write.Label(col , 0 , (String) datacka[0][col] , cellFormat);
	  					sheet.addCell(label1);
	  				}	
			//	}
	  			
	  		//	for(int row=0; row<data1.data0.length ; row++){
	  		//		for(int col = 0 ; col < data1.data0[row].length ; col++){
	  		//			Label label = new jxl.write.Label(col , row+cocl , (String) full_data[row][col] , cellFormat);
		  	//			sheet.addCell(label);		
	  		//		}
	  		//	}
	  		//	cocl+=Integer.parseInt(data1.data0[0][0])+1;
	  			
	  			
	  		//}	   
	  		workbook.write();
		    	   
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
