import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class General {
	public static void createFile(String filename, String string, Boolean append)
	{
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filename, append ? true : false));
			bw.write(string);
			System.out.println("File Written: " + filename);
		} catch (Exception e) {
			System.out.println("Error in 3: " + e);
		}
		finally
		{
		    try
		    {
		        if ( bw != null)
		        	bw.close( );
		    }
		    catch ( IOException e)
		    {
		    	System.out.println("Error in 4: " + e);
		    }
		}
	}
	
	public static BufferedReader readStream(String filename) throws UnsupportedEncodingException, FileNotFoundException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
		return br;
	}
	
	public static String getTime(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss SSS");
		return ft.format(dNow);
	}
	
}