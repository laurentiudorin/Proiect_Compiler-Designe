package Token;

public class Token {
	private char code;
	
	private int line;
	private int colum;
	
	private String word;

	public void LoadData(char code, int colum, int line, String word){
		this.code = code;
		this.line = line;
		this.colum = colum;
		this.word = word;
	}
	
	public void PrintData(){
		System.out.println(word + " \t " + code + " \t " + line + " \t " + colum);
	}
}