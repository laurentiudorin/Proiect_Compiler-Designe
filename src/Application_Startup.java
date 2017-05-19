import java.io.IOException;
import java.util.ArrayList;

import Analizor.Analizor_Lexical;
import Token.Token;

public class Application_Startup {
	public static void main(String[] args) {
		;
		Analizor_Lexical lex = new Analizor_Lexical();
		
		try {
			lex.LoadString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<Token> token = lex.getTokens();
		String stringToAnalize = new String();
		
		for (int i = 0; i < token.size(); i++)
		{
			stringToAnalize += token.get(i).code;
		}
		
		System.out.println(stringToAnalize);
	}
}
