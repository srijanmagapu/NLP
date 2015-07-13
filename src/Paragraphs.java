import java.util.ArrayList;

public class Paragraphs {
	private ArrayList<Sentence> sentence = new ArrayList<>();

	public Paragraphs(String paragraph) {
		addSentence(paragraph);
	}

	public ArrayList<Sentence> getSentence() {
		return sentence;
	}

	public void setSentence(ArrayList<Sentence> sentence) {
		this.sentence = sentence;
	}

	public void addSentence(String paragraph) {

		String[] sentences = paragraph.split("\\.");

		if (sentences.length != 0) {
			for(String sentence : sentences)
			this.sentence.add(new Sentence(sentence));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Sentence s : sentence) {
			sb.append(s.toString());
		}
		return "<Paragraph>\n" + sb.toString() + "\n</Paragraph>";
	}
}
