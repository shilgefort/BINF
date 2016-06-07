package testat8;

import java.io.IOException;
import java.io.InputStreamReader;

public class Searcher{
	
	private static final String USAGE="Benutzung: java Searcher 'Regex' < Datei";

	public static void main(String[] args){
		//Wenn keine Argumente a
		if(args.length == 0){
			System.out.println(USAGE);
			System.exit(1);
		}
	//gesuchter Regex
	String regex = args[0];
	
	LineReader reader;
	
	try{
		//Neuer LineReader wird aufgerufen mit neuem InputStreamReader und gesuchtem Regex
		//InputStreamReader liest aus System.in, da in System.in geschrieben wird
		reader = new LineReader(new InputStreamReader(System.in), regex);
		String line;
		//solange Zeile!=null(Dateiende) suche nach Uebereinstimmung
		while((line = reader.readLine()) != null){
			
			if(reader.getAmountOfMatches() > 0) {
			
				System.out.printf("%d Match(es) in Zeile %d: %s %n", reader.getAmountOfMatches(), reader.getLineNumber(), line);
			
			}
		 
		}
	
	} catch (IOException e){
		e.printStackTrace();
	} 
	}
		
	
	
}