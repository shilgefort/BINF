package util;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;

public class PersistentArray implements Iterable<Integer>{
	
	private Integer[]array;
	private String name;
	private RandomAccessFile file;
	private int size;
	File dir;
	
	public PersistentArray(){
		
	}
	
	
	
	public PersistentArray(Integer[]arr, String name){
		String path="C:/Users/sHilg_000/Desktop/";
		this.array=arr;
		this.name=name;
		size=array.length;
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
	
	public void write(int i)throws java.io.IOException{
		file.writeInt(i);
		size++;
	}
	public int read()throws java.io.IOException{
		return file.readInt();
	}
	public int getSize(){
		return size;
	}
	public Iterator iterator(){
		return new ArrayIterator();
	}
	public void reset(){
		try{
			file.seek(0);
		}
		catch(java.io.IOException e){
			
		}
	}
	public class ArrayIterator implements Iterator<Integer>{
		
		public ArrayIterator(){
			reset();
		}
		
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

		@Override
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
	

