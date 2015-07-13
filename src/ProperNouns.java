import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class ProperNouns {

	
	
	
	public ArrayList getAllNouns() throws IOException
	{
		BufferedReader br = null;
		br = new BufferedReader(new FileReader("NER.txt"));
		String line;
		String[] nouns;
		ArrayList<String> list = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
		nouns=line.split(" ");
		list.addAll(Arrays.asList(nouns));
		}
		
		return list;
	}
	public boolean isProperNoun(String word) throws IOException
	{
	
		 if(getAllNouns().contains(word))				
			 return true;
		 else
		return false;
	
	}
	
}
