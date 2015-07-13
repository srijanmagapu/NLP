import java.util.ArrayList;

public class File {
	private ArrayList<Paragraphs> paragraphs = new ArrayList<>();
	
	File() {}

	public ArrayList<Paragraphs> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(ArrayList<Paragraphs> paragraphs) {
		this.paragraphs = paragraphs;
	}
	
	public void addParagraph(String paragraph){
		Paragraphs para = new Paragraphs(paragraph);
		paragraphs.add(para);
	}
	
	public String toString() {
		return "<File>\n" + paragraphs.toString() + "\n</File>";
	}
}
