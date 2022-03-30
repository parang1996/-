package typing_battle.model;

public class WordDTO {
	private int wordsLevel;
	private String wordsword;
	
	public WordDTO() {}

	public WordDTO(int wordsLevel, String wordsword) {
		super();
		this.wordsLevel = wordsLevel;
		this.wordsword = wordsword;
	}

	@Override
	public String toString() {
		return "WordDTO [wordsLevel=" + wordsLevel + ", wordsword=" + wordsword + "]";
	}

	public int getWordsLevel() {
		return wordsLevel;
	}

	public void setWordsLevel(int wordsLevel) {
		this.wordsLevel = wordsLevel;
	}

	public String getWordsword() {
		return wordsword;
	}

	public void setWordsword(String wordsword) {
		this.wordsword = wordsword;
	}
	
	
}
