import java.io.IOException;

import Analizor.Analizor_Lexical;

public class Application_Startup {
	public static void main(String[] args) {
		
		//ReadFile string = new ReadFile();
		Analizor_Lexical lex = new Analizor_Lexical();
		
		//String str = string.Read();
		try {
			lex.LoadString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		lex.Analize();
	}
}
