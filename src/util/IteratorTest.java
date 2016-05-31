package util;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class IteratorTest {
	
	public static void main(String[]argv){
		
		MyList<Integer> list=new MyList<Integer>();
		Integer[]array={2, 4, 8, 16, 32};
		Integer[]sorted={32, 16, 8, 4, 2};
		boolean test=true;
		
		for(int i=0; i<array.length;i++){
			list.add(array[i]);
		}
		
		Iterator<Integer> iter = list.iterator();
		
	
		
		System.out.println("+++Iterator-Test+++");
		
		for(int l=0; l<sorted.length; l++){
			if(iter.next()!=sorted[l]){
				test=false;
			}
		}
		
		System.out.println("Durchlaeuft alle Elemente?: "+test);
		try{
			iter.next();
		}
		catch(NoSuchElementException e){
				test=true;
		}
		System.out.println("NoSuchElementException(next-Methode): "+test); 
		
		test=true;
		iter=list.iterator();
		
		
		if(iter.hasNext()!=true){
			test=false;
		}
		while(iter.hasNext()==true){
			iter.next();
		}
		if(iter.hasNext()!=false){
			test=false;
		}
		
			
		System.out.println("hasNext-Methode: "+test);
		iter=list.iterator();
		test=true;
		iter.next();
		iter.remove();
		
		while(!list.endpos()){
			
			if(list.elem()==32){
				test=false;
			}
			list.advance();
		}
		list.reset();
		System.out.println("remove-Methode: "+test);
		test=false;
		iter=list.iterator();
		
		list.delete();
		
		try{
			iter.next();
		}
		catch(RuntimeException e){
			test=true;
		}
		System.out.println("Bricht ab bei parallelem Zugriff: "+test);
		
		test=false;
		iter=list.iterator();
		try{
			iter.remove();
			iter.remove();
		}
		catch(NoSuchElementException e){
			test=true;
		}
		
		System.out.println("NoSuchElementException(remove-Methode): "+test);
		
	}
}
