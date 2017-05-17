package FileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromFile {

	public FileReader readFile;
	public BufferedReader bufferedFileReader;
	public String fileName = "test.txt";
	public ArrayList<String> fileContent = new ArrayList<String>();
	//public String fileContent;
	
	public ArrayList<String> Read() throws IOException{
	
		this.readFile = new FileReader(fileName);
		this.bufferedFileReader = new BufferedReader(this.readFile);
		String content = bufferedFileReader.readLine();
				
			while (content != null)
				{
					fileContent.add(content);
					//fileContent += content;
					content = bufferedFileReader.readLine();
				}
			
			for (int i = 0; i < fileContent.size(); i++)
			{
				System.out.println(fileContent.get(i));
			}
			System.out.println("\n");
			
			return fileContent;
	}

}