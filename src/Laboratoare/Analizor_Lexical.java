package Laboratoare;
import Laboratoare.Token;

public class Analizor_Lexical {

	private String str;
	
	
	Token token = new Token();
	
//	private int LINE 	= 0;
//	private int COLUM 	= 0;
	
	
	int tokenizator = 0;
	private int length 	= 0;
	private int pozitiedepistata = 0;
	
	private String[] keywords = { "repeat", "while", "begin", "end"};
	
	public void LoadString(String str){
		this.str = str;
	}
	
	
	public void Analize(){

		
		token.setSTR(str);
		
		//Pasul 1 Se elimina spatiile albe si \n-ul 
//		str = Remove(str);
		
		
		//pasul 2 Identificarea 
		for (int i=0; i<str.length(); i++){
			
			//Identificator\\
			if (Character.isLetter(str.charAt(i))){
				while(Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)) || (str.charAt(i) == '_')){
					length++;
					i++;
					if (i >= str.length()){
						break;
					}
				}
				if (str.substring(i-length, i).equals(keywords[0]) || str.substring(i-length, i).equals(keywords[1])){
					token.AddKeyWords(i-length, length, i);
				}
				else{
					token.AddIdentifier(i-length, length);
//					System.out.println(str.substring(i-length, i));		
				}
				i--;
				length = 0;
			}
			
			//Constanta Caracter
			else if (str.charAt(i) == '\''){
				if (i+1 < str.length()){
					if (Character.isLetter(str.charAt(i+1))){
						if (i+2 < str.length()){
							if (str.charAt(i+2) == '\''){
//								token.SetConstantCaracters(i+1, 1);
								token.AddConstantCaracters(i, 3);
								i=i+2;
							}else{
								token.AddError(i, 1);
							}
						}
					}else if(str.charAt(i+1) == '\\'){
						if (i+2 < str.length()){
							if (str.charAt(i+2) == '\'' || str.charAt(i+2) == '\\'){
								if (i+3 < str.length()){
									if (str.charAt(i+2) == '\''){
//										token.SetConstantCaracters(i+1, 1);
										token.AddConstantCaracters(i, 3);
										i=i+3;
									}
								}
							}
						}
					}else{
						token.AddError(i, 1);
					}
				}
			}
			
			else if (str.charAt(i) == '0'){
				if (i+1 < str.length()){
					if (str.charAt(i+1) == 'x'){
						if (i+2 < str.length()){
							if (Character.isDigit(str.charAt(i+2))){
//								if(Character.isDigit(str.charAt(i+3))){
//									if(Character.isDigit(str.charAt(i+4))){
//										token.AddError(i, 5);
//										tokenizator = 1;
//										i=i+6;
//									}
//									token.SetConstantCaracters(i+1, 1);
									token.AddConstantCaracters(i, 4);
									i=i+3;
								}else{
									if(tokenizator == 0){
										token.AddError(i, 4);
										i=i+3;
									}
								}
							}
							else{
								if(tokenizator == 0){
									token.AddError(i, 3);
									i=i+3;
								}
							}
						}
					}else{
						token.AddError(i, 1);
					}
				tokenizator = 0;
				}
				
			
			//Caracter special / secventa de caractere speciale [.] / [..]
			else if(str.charAt(i) == '.'){
//				System.out.println("am identificat un caracter special [.]");
				if (i+1 < str.length()){
						if ((str.charAt(i+1) == '.')){
//							System.out.println("am identificat secventa de caractere [..]");
							token.AddSecvente(i, 2);
							i++;
						}
						else{
							token.AddSpecialCaracters(i, 1);
						}
					}
				else{
					token.AddSpecialCaracters(i, 1);
				}
			}
			
			else if(str.charAt(i) == ','){
				token.AddSpecialCaracters(i, 1);
			}
			
			
			
			//Secventa de caractere speciale [<=]
			else if(str.charAt(i) == '<'){
				if (i < str.length()){
					if(str.charAt(i+1) == '='){
//						System.out.println("am identificat O segventa de caractere speciale [ <= ]");
						token.AddSecvente(i, 2);
						i++;
					}
					else{
						token.AddError(i, 1);
					}
				}
			}
			else{
				if(str.charAt(i)!=' '){
					if (i <= str.length()){
						pozitiedepistata = i;
						while(true){
								i++;
								length++;
								
							if (i >= str.length()){
								break;
							}
							if (str.charAt(i) == ' '){
								break;
							}
							if (str.charAt(i) == '\n'){
								break;
							}
							if (str.charAt(i) == ','){
								break;
							}
							if (str.charAt(i) == '.'){
								break;
							}
						}
						token.AddError(pozitiedepistata, length);
						length = 0;
						pozitiedepistata = 0;
						i--;
					}
				}
			}
			
			
		}
//		token.AfiasreGrupata();
//		token.AfisareOrdonata();
	}
	
	
		
	// Preia stringul din interfata grafica si il incarca in variabila str.
	public void SetString(String str){
		this.str = str;
//		this.str = Remove(str);						// Test de contol - verifica funtia Remove
//		System.out.println(this.str);				// Test de contol - Afisaza in consla rezultatul
	}
	
	//int char,voi...<=.'a''0x10,0xz 0x123,'\\' '\'' 123 _abc123 abc_123 123a
	
}
