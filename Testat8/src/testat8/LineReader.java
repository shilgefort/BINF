package testat8;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LineReader extends LineNumberReader {

	private final Pattern PATTERN;

	private int counter;
	//Konstruktor
	public LineReader(Reader in, String search){
		//Ruft Konstruktor von LineNumberReader auf
		super(in);
		//Gesuchtes Pattern
		PATTERN = Pattern.compile(search);
		counter = 0;
		
	}
	/**
	 * liest eine Zeile aus einer Datei ein und sucht nach Uebereinstimmungen
	 */
	public String readLine() throws IOException {
		
		String line = super.readLine();
		
		if(line==null){
			
			return null;
			
		}
		
		Matcher matcher = PATTERN.matcher(line);
		//Zaehlt Matches
		if(matcher.find()){
			
			counter++;
			
		}
		//ersetze Zeilenumbrueche
		line.replace("%n", "");
		//Gibt die aktuelle Zeile zurueck
		return line;
		
	}
	/**
	 * Liefert die aktuelle Zeilennummer
	 * @return	aktuelle Zeilennummer
	 */
	public int LineNumber(){
		return super.getLineNumber();
	}
	/**
	 * liefert die Anzahl der Matches in einer Zeile
	 * @return	Anzahl der Matches
	 */
	public int getAmountOfMatches(){
		return counter;
	}
}