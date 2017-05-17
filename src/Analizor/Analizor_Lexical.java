package Analizor;

import java.io.IOException;
import java.util.ArrayList;

import FileReader.ReadFromFile;

public class Analizor_Lexical {
	
	private ArrayList<String> fileContent = new ArrayList<String>();
//	private ArrayList<Tokens> token = new ArrayList<Token>();
	
	private String[] keywords = {"repeat", "while", "begine", "end"};
	private ReadFromFile file = new ReadFromFile();

	private char ch;
	private int position = 0;
	private int vectorIndex = 0;
	
	public void LoadString() throws IOException{
		fileContent = file.Read();
		
		for (int i = 0; i < fileContent.size(); i++)
		{
			vectorIndex = i+1;
			this.Analize(fileContent.get(i));
			position = 0;
		}
	}
	
	/*** Function that load the next character ***/
	public void NextChar(String content){	
		if(position < content.length()){
			ch = content.charAt(position);
			position++;
		}
	}
	
	
	/*** Function that verify if the identifier is a keyword ***/
	public boolean CheckKeyWord(String word){
		
		for (int i=0; i < keywords.length; i++){
			if (word.equals(keywords[i])){
				return true;
			}
		}
		return false;
	}
	
	
	public void Analize(String content){
		
		int wordPosition = 0;
		int stringLength = 0;
		
		NextChar(content);
		
		/*** MAIN LOOP ***/
		while (position < content.length()){
			
			/*** Remove Backspace ***/
			while(ch == ' ')
				NextChar(content);
			
			/*** IDENTIFICATORI ***/
			if(Character.isLetter(ch)||ch == '_'){
				wordPosition = position-1;
				stringLength = 0;
				NextChar(content);
				stringLength++;
				while(Character.isLetter(ch) || Character.isDigit(ch)){
					NextChar(content);
					stringLength++;
					if(position == content.length()){
						stringLength++;
						break;
					}
				}
//				CheckKeyWord(str.substring(position - stringLength -1, position -1));
				
				//test
				if (CheckKeyWord(content.substring(wordPosition, wordPosition + stringLength))){
					System.out.println(content.substring(wordPosition, wordPosition + stringLength) + " este Cuvant Cheie" );
				}else{
					System.out.println(content.substring(wordPosition, wordPosition + stringLength) + " este Identificator" );
				}
			}
			
			/*** CONSTANTE CARACTER DE TIPUL 'a' ***/
			else if(ch == '\''){
				wordPosition = position-1;
				stringLength = 0;
				NextChar(content);
				if (Character.isLetter(ch) || Character.isDigit(ch)){
					NextChar(content);
					if(ch == '\''){
						System.out.println(content.substring(wordPosition, wordPosition + 3) + " este cosntanta caracter" );
					}
//					Varianta HARDE CORE !!!
//					else if(ch == ' ' || position == str.length()){
//						System.out.println(str.substring(wordPosition, wordPosition + 2) + " este Bad Caracter" );
//					}else{
//							
//						stringLength = 2;
//						while (ch != ' '){
//							NextChar();
//							stringLength++;
//							if(position == str.length()){
//								stringLength++;
//								break;
//							}
//						}
//						System.out.println(str.substring(wordPosition, wordPosition + stringLength) + " este Bad Caracter" );
//					}
					
//					Varianta SIMPLA !
					else{
						System.out.println(content.substring(wordPosition, wordPosition + 1) + " este Bad Caracter" );
						position -= 1;
					}
				
				/*** CONSTANTE CARACTER SPECIALE ***/
				}else if(ch == '\\'){
					NextChar(content);
					if(ch == '\'' || ch == '\"' || ch == '\\'){
						NextChar(content);
						if (ch == '\''){
							System.out.println(content.substring(wordPosition, wordPosition + 4) + " este constanta caracter" );
						}else{
							while (ch != '\'' || ch != ' '){
								NextChar(content);
							}
						}
					}
				}
			}
			
			/*** COSNTANTE CARACTER DE TIP OX1F ***/
			else if(ch == '0'){
				stringLength = 0;
				wordPosition = position-1;
				NextChar(content);
				if (ch == 'x'){
					NextChar(content);
					if (Character.isDigit(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F')){
						NextChar(content);
						if (Character.isDigit(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F')){
							NextChar(content);
							if(ch == ' '){
								System.out.println("Am identificat caracterul " + content.substring(wordPosition, wordPosition + 4)+ " pe linia " + vectorIndex + " la pozitia " + (wordPosition + 1));
							}else if(Character.isLetter(ch) || Character.isDigit(ch)){
								while(Character.isLetter(ch) || Character.isDigit(ch)){
									NextChar(content);
									stringLength++;
									if(position == content.length()){
										stringLength++;
										break;
									}
								}
								System.out.println("Am identificat caracterul " + content.substring(wordPosition, wordPosition + stringLength+2)+ " pe linia " + vectorIndex + " la pozitia " + (wordPosition + 1));
							}
						}else{
							while(Character.isLetter(ch) || Character.isDigit(ch)){
								NextChar(content);
								stringLength++;
								if(position == content.length()){
									stringLength++;
									break;
								}
							}
							System.out.println("Bad Caracter: " + content.substring(wordPosition, wordPosition + stringLength+3)+ " pe linia " + vectorIndex + " la pozitia " + (wordPosition + 1));
						}
					}else{
						while(Character.isLetter(ch) || Character.isDigit(ch)){
							NextChar(content);
							stringLength++;
							if(position == content.length()){
								stringLength++;
								break;
							}
						}
						System.out.println("Bad Caracter: " + content.substring(wordPosition, wordPosition + stringLength)+ " pe linia " + vectorIndex + " la pozitia " + (wordPosition + 1));
					}
				}else{
					while(Character.isLetter(ch) || Character.isDigit(ch)){
						NextChar(content);
						stringLength++;
						if(position == content.length()){
							stringLength++;
							break;
						}
					}
					System.out.println("Bad Caracter: " + content.substring(wordPosition, wordPosition + stringLength+1)+ " pe linia " + vectorIndex + " la pozitia " + (wordPosition + 1));
				}
			}
			
			/*** CARACTER SPECIAL [,] ***/
			else if (ch == ','){
				System.out.println("Am identificat caracterul special [,]");
				NextChar(content);
			}
			
			/*** CARACTER SPECIAL [.] / SECVENTA DE CARACTERE SPECIALE [..] ***/
			else if (ch == '.'){
				NextChar(content);
				if (ch == '.'){
					System.out.println("Am identificat secventa [..]");
					NextChar(content);
				}else{
					System.out.println("Am identificat secventa [.]");
				}
				
				// AICI O SA APARA UN BUG ENORM 
				// O SA TREACA CU UN CARACTER IN FATA
			}
			
			/*** SECVENTA DE CARACTERE SPECIALE [<=] ***/
			else if(ch == '<'){
				wordPosition = position-1;
				NextChar(content);
				if( ch == '='){
					System.out.println(content.substring(wordPosition, wordPosition + 2) + " este Secventa de caractere" );
				}else{
					System.out.println("Bad caracter");
				}
			}
			
			/*** DEFAULT ***/
			else{
				if(ch != ' '){
					wordPosition = position-1;
					stringLength = 0;
					while(Character.isLetter(ch) || Character.isDigit(ch)){
						NextChar(content);
						stringLength++;
						if(position == content.length()){
							stringLength++;
							break;
						}
					}
					System.out.println(content.substring(wordPosition, wordPosition + stringLength) + " este BadCaracter" );
				}
				NextChar(content);
			}
		}
	}
}