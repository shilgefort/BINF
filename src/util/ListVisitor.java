package util;

public class ListVisitor<E> implements Visitor<E> {
	
	
	
	public boolean visit(E o){
		
		System.out.print(""+o+" |");
		
			return true;
		
	}
}
