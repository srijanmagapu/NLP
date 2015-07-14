import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class tokenizer {

	private static ArrayList<File> files = new ArrayList<>();
	
	private final static String source_zip_path = "nlp_data.zip";
	private final static String destination_zip_path = "nlp_data";

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Unzipper.unzip(source_zip_path, destination_zip_path);
		
		java.io.File folder = new java.io.File(destination_zip_path + "/nlp_data");
		java.io.File[] listOfFiles = folder.listFiles();

		for (java.io.File file : listOfFiles) {
		    if (file.isFile() && !file.getName().equals(".DS_Store")) {
		    	files.add( fileBuilder( destination_zip_path + "/nlp_data/" + file.getName() ) );
		    }
		}
		
		General.createFile( "output.xml", (outputXML(files)), false);

	}
	
	private static File fileBuilder(String filename) throws IOException, InterruptedException {
		
		File fileBuilder = new File();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				BufferedReader br = null;
				try {
					br = General.readStream(filename);
				} catch (UnsupportedEncodingException | FileNotFoundException e) {
					e.printStackTrace();
				}	
				StringBuilder sb = new StringBuilder();
				String line = "";
				
				try {
					while ((line = br.readLine()) != null) {

						if (line.trim() != "") {
							sb.append(line);
							fileBuilder.addParagraph(sb.toString());
							sb = new StringBuilder();
						}
						fileBuilder.addParagraph(sb.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		
		thread.join();
		
		return fileBuilder;
	}

	private static String outputXML(ArrayList<File> files) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8' standalone='no' ?>\n");
		sb.append("<Files>\n");
		for(File fileBuilder : files) {
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
					ArrayList<String> properNouns;
					sb.append("<Sentance>\n");
					ProperNouns pn=new ProperNouns();
					for (int index = 1; true; index++) {
						if (words.containsKey(index)) {
							if(pn.isProperNoun(words.get(index)))
							{
							sb.append("<word ProperNoun='true'>" + words.get(index) + "</word>\n");
							}
							else{
								sb.append("<word>" + words.get(index) + "</word>\n");
							}
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
		}
		
		sb.append("</Files>\n");

		return sb.toString();
	}
}