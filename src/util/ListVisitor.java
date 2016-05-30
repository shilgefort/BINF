package util;


public class ListVisitor<E> implements Visitor<E> {
	
	MyList<E> list=new MyList<E>();
	
	public boolean visit(E o){
		
		return true;
			
	}
	
}
