package testat8;

import java.io.File;


public class FileSystem  {

	private File path;
	
	public FileSystem(File path) {
		
		if(!path.exists()){
		
			throw new IllegalArgumentException("Pfad existiert nicht");
		
		}
		
		this.path=path;
	}
	/**
	 * nimmt einen Visitor v an und zeigt ihm alle Dateien bzw. Verzeichnisse 
	 * @param v	Visitor
	 */
	public void accept(FileVisitor v) {
	      
		  walkFile(path, v);
		  
	  }
	/**
	 * zeigt dem Visitor die Datei bzw. die Verzeichnisse
	 * @param file	Datei/Verzeichnis, dass besucht werden soll
	 * @param v	Visitor, der die Datei besucht
	 * @return	FileVisitResult "Continue", wenn Besuch fortgesetzt, "SKIP_SUBTREE", wenn Unterverzeichnis uebersprungen werden und
	 * "Terminate" wenn Besuch abgebrochen werden soll
	 */
	 private FileVisitResult walkFile(File file, FileVisitor v) {
		//Wenn Besuch fehlgeschlagen
		if (!file.canRead()) {
	         
			return v.visitFileFailed(file);
			
	   	}
	    //Wenn es sich um Verzeichnis handelt 
	    if(file.isDirectory()){
	   		
	   	  FileVisitResult result=v.preVisitDirectory(file, ""); 
	   	//Soll Unterverzeichnis uebersprungen werden? 	
	   	  if(result==FileVisitResult.SKIP_SUBTREE){
			 
	   		  return result;
	   		  
		  }
		  else if(result==FileVisitResult.CONTINUE){
			 
			 v.postVisitDirectory(file);
		 //Fuelle files aus Unterverzeichnis in Array  
			 File[]files=file.listFiles();
		 //Besuche jede einzelne Datei	 
			 for(int i=0; i<files.length; i++){
				 v.visitFile(files[i]);
			 }
		  }
		  else{
			//Beende das Programm
			  System.exit(0);
			  
		  }
	   	}
	   	else{
	   		//Wenn es sich um eine Datei handelt
	   		return v.visitFile(file);
	   		
	   	}
		return null;
	   }
}
