package Laboratoare;
import javax.swing.text.Position;

//	token[3][100]	
//	token[0][x] -> tipe ('b','e','w', etc.)
//	token[1][x] -> pozitia de unde incepe cuvantul 
//	token[1][x] -> dimensiunea cuvantului

public class Token {
	private static String STR;
	private static int TOKEN[][] = new int[3][100];
	private static int Counter = 0;
	private static String sintacticString = null;; // acesta este stringul pe care il vom folosi pentru a fi parsat in Analizorul sintactic.

	public static void setSTR(String sTR) {
		STR = sTR;
	}

	// Identificator
	public void AddIdentifier(int position, int length) {
		TOKEN[0][Counter] = 1;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
		sintacticString += "i";
	}

	// Cuvinte Cheie
	public void AddKeyWords(int position, int length, int i) {
		TOKEN[0][Counter] = 2;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
		if(i==0)
			sintacticString += 'r';
		else if(i==1)
			sintacticString += 'w';
		else if(i==2)
			sintacticString += 'b';
		else if(i==3)
			sintacticString += 'e';
	}
	
	// Constante Caracter
	public void AddConstantCaracters(int position, int length) {
		TOKEN[0][Counter] = 3;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
		sintacticString += 'c';
	}

	public void AddSpecialCaracters(int position, int length) {
		TOKEN[0][Counter] = 4;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
	}

	public void AddSecvente(int position, int length) {
		TOKEN[0][Counter] = 5;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
	}
	
	public void AddError(int position, int length){
		TOKEN[0][Counter] = 6;
		TOKEN[1][Counter] = position;
		TOKEN[2][Counter] = length;
		Counter++;
	}

	public void AfiasreGrupata() {
		
		System.out.println(" IDentificatori");
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 1){
				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		
		System.out.println("\n Cuvinte chaie");
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 2){
				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		
		System.out.println("\n Constante Cracter");
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 3){
				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		
		System.out.println("\n Caractere speciale");
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 4){
				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		
		System.out.println("\n Secventa de cuvinte caracter");
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 5){
				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		
		Counter=0;
	}
	
	
	void AfisareOrdonata(){
		for (int i = 0; i <= Counter; i++) {
			if(TOKEN[0][i] == 1){
//				System.out.println("Identificator: ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Identificator: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
			else if(TOKEN[0][i] == 2){
//				System.out.println("Cuvinte cheie: ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Cuvinte cheie: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
			else if(TOKEN[0][i] == 3){
//				System.out.println("Constante Caracter: ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Constante Caracter: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
			else if(TOKEN[0][i] == 4){
//				System.out.println("Caractere speciale: ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Caractere speciale: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
			else if(TOKEN[0][i] == 5){
//				System.out.println("Secventa de cuvinte caractere: ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Secventa de caractere: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
			else if(TOKEN[0][i] == 6){
//				System.out.println("Caracter BAD ");
//				System.out.println(STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
				System.out.println("Caracter BAD: " + STR.substring(TOKEN[1][i], TOKEN[1][i]+TOKEN[2][i]));
			}
		}
		Counter=0;
	}
	
	public void ClearToken(){
		TOKEN = null;
	}

}
