package util;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;

public class PersistentArray implements Iterable<Integer>, AutoCloseable{
	
	private Integer[]array;
	private String name;
	private RandomAccessFile file;
	private int size;
	File dir;
	/**
	 * default-Konstruktor
	 */
	public PersistentArray(){
		
	}
	
	
	/**
	 * Konstruktor mit Array und Dateiname
	 * @param arr	zu speicherndes Array
	 * @param name	Name, unter dem das Array gespeichert werden soll
	 */
	public PersistentArray(Integer[]arr, String name){
		String path="C:/Users/sHilg_000/Desktop/";
		this.array=arr;
		this.name=name;
		size=array.length-1;
		dir=new File(path, "Directory");
		dir.mkdir();
		if(file==null){
			try{
				file=new RandomAccessFile(""+path+""+name+".txt", "rw");
			}
			catch(java.io.FileNotFoundException e){
				this.name=name+"1";
			}
			
				for(int i=0; i<array.length; i++){
					try{
						file.writeInt(array[i]);
					}
					catch(java.io.IOException e){
						System.out.println("Fehler bei der Eingabe");
					}
					
			}
		}	
		else{	
			try{
				file.close();
				file=null;
			}
			catch(java.io.IOException e){
				
			}
			finally{
				try{
					file=new RandomAccessFile("src/test/"+name+".txt", "rw");
			
				}
				catch(java.io.FileNotFoundException e){
					this.name=name+"1";
					
				}
				finally{
					for(int i=0; i<array.length; i++){
						try{
							file.writeInt(array[i]);
					
						}
						catch(java.io.IOException e){
							System.out.println("Fehler bei der Eingabe");
						}
					}	
				}
				
			}	
		
		}
		
	}
	/**
	 * schreibt ein int in die Datei
	 * @param i int, die hinzugefuegt wird
	 * @throws java.io.IOException
	 */
	public void write(int i)throws java.io.IOException{
		file.writeInt(i);
		size++;
	}
	/**
	 * liest int an der aktuellen Stelle aus
	 * @return	int an der aktuellen Position
	 * @throws java.io.IOException bei IO-Fehlern
	 */
	public int read()throws java.io.IOException{
		return file.readInt();
	}
	/**
	 * gibt die Menge der gespeicherten Datei an
	 * @return	Menge der gespeicherten ints
	 */
	public int getSize(){
		return size;
	}
	/**
	 * liefert einen Iterator fuer die Datei
	 * @return  ArrayIterator
	 */
	public Iterator iterator(){
		return new ArrayIterator();
	}
	/**
	 * setzt den Zeiger auf den Anfang der Datei
	 **/
	public void reset(){
		try{
			file.seek(0);
		}
		catch(java.io.IOException e){
			
		}
	}
	/**
	 * schliesst die Datei, wenn moeglich
	 * @throws RuntimeException  wenn Datei nicht geschlossen werden kann
	 */
	@Override
	public void close() throws RuntimeException {
		try{
			file.close();
		}
		catch(java.io.IOException e){
			throw new RuntimeException("Schliessen nicht erfolgreich");
		}
		
	}
	public boolean pathExists(){
		return dir.exists();
	}
	/**
	 * Implementiert das Interface Iterator fuer ein PersistentArray 		
	 * 
	 *
	 */
	public class ArrayIterator implements Iterator<Integer>{
		
		public ArrayIterator(){
			reset();
		}
		/**
		 * liefert zurueck, ob das aktuelle Element das letzte ist
		 * @return boolean  true, wenn nicht das letzte Element, sonst false
		 */
		public boolean hasNext() {
			boolean hasNext=false;
			try{
				hasNext=!(file.getFilePointer()==file.length());
			}
			catch(java.io.IOException e){
				System.out.println("IO-Fehler");
			}
			return hasNext;
		}

		/**
		 * liefert das naechste Objekt, setzt den Iterator eine Position weiter
		 *@return Integer  naechstes Objekt
		 *@throws NoSuchElementException  wenn bereits am Ende der Datei
		 */
		
		public Integer next() {
			Integer temp=null;
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			try{
				file.seek(file.getFilePointer()+4);
				
			}
			catch(java.io.IOException e){
				System.out.println("IO-Fehler");
			}
			try{
				temp=file.readInt();
			}
			catch(java.io.IOException e){
				System.out.println("IO-Fehler");
			}
			return temp;
		}
		
	}
	
}
	

