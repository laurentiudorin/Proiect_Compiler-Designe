package Token;

public class Token {
	public char code;
	
	private int line;
	private int colum;
	
	private String word;
	
	String str;

	public void LoadData(char code, int colum, int line, String word){
		this.code = code;
		this.line = line;
		this.colum = colum;
		this.word = word;
	}
	
	public void PrintData(){
		if (word.length() < 8)
			System.out.println(word + "\t\t" + code + " \t " + line + "     " + colum);
		else if(word.length() < 16)
			System.out.println(word + "\t" + code + " \t " + line + "     " + colum);
	}
}