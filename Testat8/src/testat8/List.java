package testat8;
import java.io.File;

public class List {
	private static final String USAGE="List -r -DateiOderVerzeichnisname";
	private static boolean check=false;
	private static FileVisitor v;
	private static FileSystem fileSystem;
	public static void main(String[]argv){
		System.out.print(USAGE);
		//Ueberpruefe, ob r mitgegeben wurde
		if(argv[0]!=null && argv[0]=="r"){
			check=true;
			if(argv[1]!=null){
				File path=new File(argv[1]);
				fileSystem=new FileSystem(path);
			}
		}
		//wenn kein r
		else{
		//Verzeichnis angegeben? 
			if(argv[0]!=null){
				File path=new File(argv[0]);
				fileSystem=new FileSystem(path);
			}
		//Sonst waehle aktuelles Verzeichnis	
			else{
				String hilf = System.getProperty("user.dir");
				File path=new File(hilf);
				
				fileSystem=new FileSystem(path);
			}
		}
		
		if(check==false){
			fileSystem.accept(v);
		}
		else{
			fileSystem.accept(v);
		}
			
		
	}
	
}
