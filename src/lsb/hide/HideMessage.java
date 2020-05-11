package lsb.hide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HideMessage {
	private File infilename=null;
	private File outfilename=null; 
	private FileInputStream in=null;
	private FileOutputStream out=null;
	public void setname(String infile,String outfile)
	{
		infilename = new File(infile);
		outfilename=new File(outfile);
	}
	public void write(String message) throws Throwable
	{

		char[] charMessage = message.toCharArray();
		int k=0;
		int position;
		int temp=0;
		int flag=0;
		in = new FileInputStream(infilename);
		out=new FileOutputStream(outfilename);
		temp=in.read();
		while(temp!=-1)
		{
			if(flag==0&&k>=54)
			{
			
				for(int j=0;j<message.length();j++)
				{
					if(charMessage[j]=='#')
					{
						flag=1;
					}
					for(int i=0;i<8;i++)
					{
						position=(charMessage[j]>>(7-i)&0x01);
						temp=position+(temp&0xfe);
						out.write(temp);
						temp=in.read();
						k++;
					}
				}
			}
			else
			{
				out.write(temp);
				k++;
				temp=in.read();
			}
			
		}
		in.close();
		out.close();
	}
}
