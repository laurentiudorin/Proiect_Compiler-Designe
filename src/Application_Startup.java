import java.io.IOException;
import java.util.ArrayList;

import Analizor.Analizor_Lexical;
import Analizor.Analizor_Sintactic;
import Token.Token;
import tree.Builder.ThreeAddressBuilder;
import tree.Elements.Instruction;

public class Application_Startup {
	
	public static int MAX_ROUNDS = 25;
	
	public static void main(String[] args) {
		
		/*** LEXICAL ANALYZER ***/
		Analizor_Lexical lex = new Analizor_Lexical();
		Analizor_Sintactic sinc = new Analizor_Sintactic();
		try {
			lex.LoadString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*** SINTACTIC ANALYZER ***/
		ArrayList<Token> token = lex.getTokens();
		String stringToAnalize = new String();
		for (int i = 0; i < token.size(); i++)
		{
			stringToAnalize += token.get(i).code;
		}
		
		System.out.println(stringToAnalize);
		sinc.RunAnalizer(stringToAnalize);
		
		System.out.println("\n");
		
		/*** INTERMEDIAR CODE GENERATOR ***/
		StringForTree sft = new StringForTree();
		sft.setStr(stringToAnalize);
		sft.CreatArray();
        
        // Convert expressions in 3-Address code (unoptimized)
        ArrayList<Instruction> ins =  ThreeAddressBuilder.Build(sft.getArrayOfStrings());
        
        
        // Print unoptimized 3-Address code
        System.out.println("\n\nUnoptimized 3-Address Code:");
        for(Instruction i : ins){
            System.out.println(i.toString());
        }
        
//        Optimizer op = new Optimizer();
//        
//        
//        // Perform successive round of optimizations
//        // Stop when no more reduction in complexity or too many rounds
//        boolean optimized = true;
//        int i = 1;
//        
//        while(optimized && i < MAX_ROUNDS){   
//            optimized = false;
//            
//            System.out.println("\n\nCode after " + i + " optimization round:");
//            optimized = op.Optimize(ins);
//            System.out.println(Optimizer.ToText(ins, 1));
//            i++;
//        }
//        
//        if(optimized) System.out.println("\n\nMaximum number of rounds is limited to " + MAX_ROUNDS);
    
		
	}
}
