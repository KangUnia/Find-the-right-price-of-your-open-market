package rkrurwhtk1;

public class ation2 {
	static String data0[]=null;
	static String dataqhrhks[]=null;
	public static void main(String[] args) throws InterruptedException {
		String data[]=new String[3];
		data[0]="a";data[1]="b";data[2]="c";
		
		String data2[]=new String[5];
		data2[0]="e";data2[1]="f";data2[2]="g"; data2[3]="h";data2[4]="r";
		
		String data3[]=new String[5];
		data3[0]="e";data3[1]="f";data3[2]="g"; data3[3]="h";data3[4]="r";

		f(data,data2);
		for(int k=0;k<data0.length;k++){
			System.out.println(data0[k]);
			
		}
		for(int e=0;e<3;e++){
			f(data,data2);
		}
		
		
	}
	
	public static void f(String[] arg,String[] arg1) {
		data0=new String[arg.length*arg1.length];
		int nums=0;
		for(int i=0;i<arg.length;i++){
			for(int j=0;j<arg1.length;j++){
				data0[nums]=arg[i]+arg1[j];
				System.out.println(data0[nums]);
				nums++;
				
			}
		}
		dataqhrhks=data0;
	}
	
}
