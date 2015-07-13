import java.util.ArrayList;
import java.util.HashMap;

public class Sentence {
	private HashMap<Integer, String> words = new HashMap<>();
	private HashMap<Integer, String> punct = new HashMap<>();
	private int progressiveWordCount = 0;



	public Sentence(String sentences) {
		getWordPunctuations(sentences);
	}

	public HashMap<Integer, String> getWords() {
		return words;
	}


	public void setWords(HashMap<Integer, String> words) {
		this.words = words;
	}


	public HashMap<Integer, String> getPunct() {
		return punct;
	}


	public void setPunct(HashMap<Integer, String> punct) {
		this.punct = punct;
	}


	public int getProgressiveWordCount() {
		return progressiveWordCount;
	}

	public void setProgressiveWordCount(int progressiveWordCount) {
		this.progressiveWordCount = progressiveWordCount;
	}

	private void getWordPunctuations(String sentences) {
		String[] chars = sentences.split("");
		String word = "";
		
		for(String str : chars)
		{
			if( str.toCharArray().length > 0 && isSpecialChar(str.toCharArray()[0] ) )
			{
				progressiveWordCount++;
				if(word != "")
				{
					words.put(progressiveWordCount, word);
					progressiveWordCount++;
					punct.put(progressiveWordCount, str);
					word = "";
				} else {
					punct.put(progressiveWordCount, str);
				}
			} else {
				word += str;
			}
		}
		
		if( words.size() != 0 && punct.size() != 0 && word != "")
		{
			if (words.containsKey(progressiveWordCount) || punct.containsKey(progressiveWordCount))
				progressiveWordCount++;
			
			words.put(progressiveWordCount, word);
			progressiveWordCount++;
			punct.put(progressiveWordCount, ".");
			word = "";
		}
		
	}
	
	private boolean isSpecialChar(char singleChar) {
		int ascii = (int) singleChar;
		return ! ((48 <= ascii && ascii <= 57) || (65 <= ascii && ascii <= 90) || ( 97 <= ascii && ascii <= 122));
	}
}
