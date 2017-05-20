import java.util.ArrayList;

public class StringForTree {
	
	private String str = new String();
	private static ArrayList<String> arrayOfSreings = new ArrayList<>();
	char ch;
	private int position = 0;
	private int lenth = 0;
	private int wordPosition;
	
	
	public void setStr(String str){
		this.str = str;
	}
	
	public ArrayList<String> getArrayOfStrings(){
		return arrayOfSreings;
	}
	
	public void NextChar(){
		ch = str.charAt(position);
		if (position+1 < str.length())
			position++;
	}
	
	public void CreatArray(){
		
		while (position < str.length()-1){
			lenth = 0;
			wordPosition = position;
			NextChar();
			if(ch == 'v'){
				lenth++;
				NextChar();
				if (ch == '='){
					lenth++;
					while(true){
						NextChar();
						if (ch == 'c' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')'){
							lenth++;
						}else{
							break;
						}
							
					}
				}
				System.out.println(str.substring(wordPosition, wordPosition+lenth));
				arrayOfSreings.add(str.substring(wordPosition, wordPosition+lenth));	
			}
		}
	}
	
	
}
