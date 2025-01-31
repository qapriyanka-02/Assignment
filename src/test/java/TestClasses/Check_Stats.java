package TestClasses;

import java.com.voicegames.automation.Utility.Common;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

public class Check_Stats extends Common {
@Test
public static void readtxtfile() throws IOException {
		
		File file = new File(StreaxCountFilePath);
		 String str;
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        StringTokenizer stn = null;    
	        while ((str= br.readLine())!=null) 
	            stn= new StringTokenizer(str);
	        	int stats=Integer.parseInt(stn.nextToken());
	        	stats++;
	            System.out.println(stats);
	    }
}
