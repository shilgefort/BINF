package util;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTest {
	
	public static void main(String[]argv){
		Integer[]array={1, 2, 4, 8, 16, 32};
		boolean test=true;
		PersistentArray p=new PersistentArray(array ,"persArray");
		Iterator<Integer> iter=p.iterator();
		int i=0;
		while(i<array.length && iter.hasNext()){
			test=(array[i]==iter.next());
			i++;
		}
		System.out.println("Nimmt alle Integers auf?: "+test);
		
		p.reset();
		Integer hilf=null;
		try{
			hilf=p.read();
		}
		catch(java.io.IOException e){
			System.out.println("IO-Exception(read-Methode) geworfen");
		}
		finally{
			System.out.println("read()-Methode: "+(hilf!=null));
		}
		//write()-Test
		try{
			p.write(64);
		}
		catch(java.io.IOException e){
			System.out.println("IO-Exception(write()-Methode) wurde geworfen");
		}
		finally{
			test=false;
			iter=p.iterator();
			int l=0;
			Integer[]temp=new Integer[10];
			while(iter.hasNext()){
				temp[l]=iter.next();
			}
			for(int j=0; j<p.getSize(); j++){
				
				if(temp[j]!=null &&temp[j]==64){
					test=true;
				}
				
			}
			System.out.println("write-Methode: "+test);
		}
		test=false;
		
		iter=p.iterator();
		
		while(iter.hasNext()){
			iter.next();
		}
		
		try{
			iter.next();
		}
		catch(NoSuchElementException e){
			test=true;
		}
		finally{
			System.out.println("NoSuchElementException(Iterator am Ende der Datei): "+test);
		}
		
	}

}
