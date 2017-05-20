package Analizor;
public class Analizor_Sintactic {
										
	private static String str;	//= "bv=(c+c+c);w(c+c)dv=(c+c+c);bv=(c+c+c);wcdv=(c+c+c)ee";          //"bv=c;w(c+c)dc+(c+c+c)e";    "bv=(c+c+c);wcdv=(c+c+c);bv=(c+c+c);wcdv=(c+c+c);ee"
	private int position = 0;			
	private char ch;					
										
//									simboluri chaie:
	
//						b = BEGIEN				x = expresie
//						e = END					c = constanta
//						d = do 					i = instructiune
//						w = while				l = lista de instructiuni
	
	public void RunAnalizer(String str){
		this.str = str;
		program();
	}
	
	public void NextChar(){
		ch = str.charAt(position);
		if (position+1 < str.length())
			position++;
	}
	
	public boolean program(){
		
		NextChar();
		if (ch == 'b'){
			lista_instructiuni();
			NextChar();
			if(ch == 'e'){
				return true;
			}else{
				System.out.println("Error at pos. " + position + ": programul nu se termina in END");
			}
		
		}else{
			System.out.println("Error at pos. " + position + ": programul nu incepe cu BEGINE");
		}
		return false;
	}
	
	public void lista_instructiuni(){
		instructiune();
		while(ch == ';'){
			instructiune();
		}
	}
	
	public void instructiune(){
		
		NextChar();
		if (ch == 'w'){
			expresie();
			
			if(ch == 'd'){
				instructiune();

			}else{
				System.out.println("Error at pos. " + position + ": nu este DO dupa While");
			}
		}
		
		else if(ch == 'b'){
			lista_instructiuni();
			NextChar();
			if(ch == 'e'){
//					return true;
			}else{
				System.out.println("Error at pos. " + position + ": Nu se termina in END");
			}
		}
		
		else if(ch == 'v'){
			NextChar();
			if (ch == '='){
				expresie();
			}else{
				System.out.println("Error at pos. " + position);
			}
		} else {
			System.out.println("Error at pos. " + position);
		}
	}
	
	public void expresie(){

		termen();
		while(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
			termen();
		}
	}
	
	public void termen(){
		NextChar();
		if(ch == 'c'){
			NextChar();
		}
		else if(ch == '('){
			expresie();
			if(ch == ')'){
				NextChar();
			}else{
				System.out.println("Error at pos. " + position + ": Expresia nu se termina in )");
			}
		}else{
			System.out.println("Error at pos. " + position + ": Lipseste C sau ( ");
		}
		
	}
	
}
