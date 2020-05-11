package lsb.hide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReFindMessage {
	public String getHideMessage(String filename) throws IOException
	{
		String result="";
		int temp=0;
		char tempchar;
		FileInputStream in = new FileInputStream(new File(filename));
		in.skip(54);
		int keyValue=0;
		while(keyValue!='#') {
			keyValue=0;
			for(int i=0;i<8;i++)
			{
				keyValue=keyValue*2;
				temp=in.read();
				temp=temp%2;
				keyValue+=temp;
				
			}
			if(keyValue!='#') {
				tempchar=(char)keyValue;
				result=result+tempchar;
			}			
		}
		in.close();
		return result;
	}
}
