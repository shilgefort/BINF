package util;
import java.util.NoSuchElementException;


public class VisitorTest {
	
	public static void main(String[]argv){
		MyList<Integer> list=new MyList<Integer>();
		Visitor<Integer> vis=new ListVisitor<Integer>();
		Integer[]array={2, 4, 8, 16, 32};
		Integer[]sorted={32, 16, 8, 4, 2};
		boolean test=false;
		try{
			list.accept(vis);
		}
		catch(NoSuchElementException e){
			test=true;
		}
		System.out.println("Leere Liste wirft Exception: "+test);
		for(int i=0; i<array.length; i++){
			list.add(array[i]);
		}
		System.out.println("Erwartete Reihenfolge: ");
		for(int j=0;j<array.length; j++){
			System.out.printf("%d | ", sorted[j]);
			
		}
		System.out.println("");
		System.out.println("Ergebnis: ");
		try{
			list.accept(vis);
		}
		catch(NullPointerException e){
			
		}
	}
}
