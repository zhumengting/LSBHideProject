package lsb.noise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Noise {
	private static int numbersCanChange=0;
	private static int noiseNumber=0;
	private static int[] state;
	//读图片头文件找出找bmp图片的大小
	public static void init(String filename) throws IOException
	{

		File file = new File(filename);
		FileInputStream fis;
		fis = new FileInputStream(file);
		fis.skip(2);
		int size=myReadInt(fis);
		numbersCanChange=(size-54)/8;
		noiseNumber=numbersCanChange/10;
		fis.close();		
	}
	//产生随机数确定哪些位置产生椒盐噪音
	public static void generateNoisePlace() {		
		state=new int[numbersCanChange];
		for(int i=0;i<numbersCanChange;i++) {
			state[i]=-1;
		}	
		int num=1+(int)(Math.random()*(numbersCanChange-1));
		int i=0;
		while(i<noiseNumber) {
			if(state[num-1]==-1) {
				state[num-1]=1;
				i++;				
			}
			num=1+(int)(Math.random()*(numbersCanChange-1));
		}
		i=0;
		while(i<noiseNumber) {
			if(state[num-1]==-1) {
				state[num-1]=0;
				i++;				
			}
			num=1+(int)(Math.random()*(numbersCanChange-1));
		}
		System.out.println("numbersCanChange----"+numbersCanChange);
	/*	for(int j=0;j<numbersCanChange;j++) {
			System.out.print(state[j]);
		}*/
	}
	public static void AddNoise(String filename,String filename2)throws IOException {

		File file = new File(filename);
		File file2 = new File(filename2);
		FileInputStream fis;
		FileOutputStream out=new FileOutputStream(file2);
		fis = new FileInputStream(file);
		
		int temp=0;
		temp=fis.read();
		int i=0;
		int k=0;
		while(temp!=-1) {
			if(k>=54&&i<numbersCanChange) {
				if(state[i]==0) {
					for(int j=0;j<8;j++) {
						out.write(0);
						k++;
						temp=fis.read();
					}
					i++;
				}else if(state[i]==1) {
					for(int j=0;j<8;j++) {
						out.write(1);
						k++;
						temp=fis.read();
					}	
					i++;
				}else {
					for(int j=0;j<8;j++) {
						out.write(temp);
						k++;
						temp=fis.read();
					}	
					i++;
				}
				
			}else {
				out.write(temp);
				k++;
				temp=fis.read();
			}
			
		}
		
	    System.out.println("i----"+i);
		fis.close();
		out.close();
	}
	//读取integer值
	 public static int myReadInt(FileInputStream ips) throws IOException {  
	        int t1 = ips.read() & 0xff;  
	        int t2 = ips.read() & 0xff;  
	        int t3 = ips.read() & 0xff;  
	        int t4 = ips.read() & 0xff;  
	        int num = (t4 << 24) + (t3 << 16) + (t2 << 8) + t1;  
	        return num;  
	    } 
	 public static void main(String[] args) throws IOException {
		 init("result/test1.bmp");
		 generateNoisePlace();
		 AddNoise("result/test1.bmp","E:\\zmt\\eclipse_app\\RandomLsbWithKey\\noise\\test1.bmp");
		 System.out.println("finish!");
		}
}
