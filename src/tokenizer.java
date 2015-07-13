import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class tokenizer {

	private static File fileBuilder = new File();

	public static void main(String[] args) throws IOException {

		BufferedReader br = null;

		String line;

		StringBuilder sb = new StringBuilder();

		br = new BufferedReader(new FileReader("/Users/srijanmagapu/Desktop/NLP_test/nlp_data.txt"));

		while ((line = br.readLine()) != null) {

			if (line.trim() != "") {
				sb.append(line);
				fileBuilder.addParagraph(sb.toString());
				sb = new StringBuilder();
			}
			fileBuilder.addParagraph(sb.toString());
		}

		createFile( "/Users/srijanmagapu/Desktop/NLP_test/output.xml", (outputXML(fileBuilder)), false);

	}

	private static String outputXML(File fileBuilder) {
		StringBuffer sb = new StringBuffer();
		sb.append("<File>\n");
		for (Paragraphs paragraphs : fileBuilder.getParagraphs()) {
			int sentenceIndex = 0;
			
			if(paragraphs.getSentence().get(sentenceIndex).getProgressiveWordCount() == 0)
				continue;
			
			sb.append("<Paragraph>\n");
			for (Sentence sentence : paragraphs.getSentence()) {

				sentenceIndex++;
				HashMap<Integer, String> words = sentence.getWords();
				HashMap<Integer, String> punct = sentence.getPunct();

				sb.append("<Sentance>\n");
				for (int index = 1; true; index++) {
					if (words.containsKey(index)) {
						sb.append("<word>" + words.get(index) + "</word>\n");
					} else if (punct.containsKey(index)) {
						sb.append("<Punctuation>" + punct.get(index) + "</Punctuation>\n");
					} else {
						break;
					}
				}
				sb.append("\n</Sentance>\n");
			}
			sb.append("\n</Paragraph>");
		}

		sb.append("\n</File>");

		return sb.toString();
	}
	
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
}