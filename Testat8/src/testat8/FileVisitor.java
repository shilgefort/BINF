package testat8;
import java.io.File;

public class FileVisitor implements Visitor{

	@Override
	/**
	 * Besucht eine Datei/ein Verzeichnis
	 */
	public boolean visit(Object o) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * wird aufgerufen, wenn Datei besucht werden soll; gibt Namen der Datei aus
	 * @param file	zu besuchende Datei
	 * @return CONTINUE, Besuch wird fortgesetzt
	 */
	public FileVisitResult visitFile(File file){
		System.out.printf("Datei: %s"+file);
		return FileVisitResult.CONTINUE;
		
	}
	/**
	 * wird vor dem Besuch aufgerufen
	 * @param dir	zu besuchendes Verzeichnis
	 * @param name	Unterverzeichnis, das ggf. uebersprungen werden soll
	 * @return	CONTINUE, wenn Besuch fortgesetzt werden oder SKIP_SUBTREE, wenn Besuch abgebrochen werden soll
	 */
	public FileVisitResult preVisitDirectory(File dir, String name){
		if(name !="" && dir.toString()==name){
			System.out.printf("Verzeichnis %s wird uebersprungen"+name);
			return FileVisitResult.SKIP_SUBTREE;
		}
		return FileVisitResult.CONTINUE;
	}
	/**
	 * wird nach dem Besuch aufgerufen; gibt Verzeichnisnamen aus
	 * @param dir besuchtes Verzeichnis
	 * @return	CONTINUE;Besuch wird fortgesetzt
	 */
	public FileVisitResult postVisitDirectory(File dir){
		System.out.println("Verzeichnis: %s"+dir);
		return FileVisitResult.CONTINUE;
	}
	/**
	 * wird aufgerufen, wenn Besuch einer Datei fehlgeschlagen
	 * @param dir V
	 * @return TERMINATE; Besuch wird abgebrochen
	 */
	public FileVisitResult visitFileFailed(File file){
		System.out.println("Datei konnte nicht besucht werden");
		
		return FileVisitResult.TERMINATE;
		
		
	}
}
