package util;


import java.util.Iterator;
import java.util.NoSuchElementException;
import util.*;

/**
 * A simple linked list. One may go through this list by {@link #advance()}
 * until the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(Object)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class MyList<E> implements Cloneable, Iterable<E>, Visitable<E> {
	
   /**
    * Reference on the first Entry of this List
    */
   private MyEntry<E> begin;
   /**
    * References before the actual Entry of this List
    */
   private MyEntry<E> pos;

   /**
    * Create a new empty List.
    */
   private int counter;
   public MyList() {
      pos = begin = new MyEntry<E>();
      counter=0;
   }

   /**
    * Determines if this List is empty or not.
    * 
    * @return <code>true</code>, if there are no elements in this List
    */
   public boolean empty() {
      return begin.next == null;
   }

   /**
    * Determines if it is possible to {@link #advance()} in this List. Returns
    * <code>true</code> if the last position of this List has been reached. An
    * {@link #empty()} List will alway deliver <code>true</code>
    * 
    * @return <code>true</code> if the last Entry in this List already has been
    *         reached.
    */
   public boolean endpos() { // true, wenn am Ende
      return pos.next == null;
   }

   /**
    * Returns to the beginning of this List.
    */
   public void reset() {
      pos = begin;
   }

   /**
    * Advances one step in this List.
    * 
    * @throws NoSuchElementException
    *            if the last Entry of this List already has been reached.
    */
   public void advance() {
      if (endpos()) {
         throw new NoSuchElementException("Already at the end of this List");
      }
      pos = pos.next;
   }

   /**
    * Returns the actual element of this List.
    * 
    * @return the actual element
    * 
    * @throws RuntimeException
    *            if the last Entry of this List already has been reached.
    */
   public E elem() {
      if (endpos()) {
         throw new NoSuchElementException("Already at the end of this List");
      }
      return pos.next.o;
   }

   /**
    * Inserts <code>o</code> in this List. It will be placed before the actual
    * element. After insertion the inserted element will become the actual
    * element.
    * 
    * @param x
    *           the element to be inserted
    */
   public void add(E x) {
      MyEntry<E> newone = new MyEntry<E>(x, pos.next);

      pos.next = newone;
      counter++;
   }

   /**
    * Deletes the actual element of this List. The element after the actual
    * element will become the new actual element.
    * 
    * @throws NoSuchElementException
    *            if the last Entry of this List already has been reached.
    */
   public void delete() {
      if (endpos()) {
         throw new NoSuchElementException("Already at the end of this List");
      }
      pos.next = pos.next.next;
      counter++;
   }

   /**
    * Clones this MyList. Will create a new independent MyList which actual
    * position lies at the beginning of this MyList. This clone operation also
    * fulfills the optional requirements defined by the {@link Object#clone()}
    * operation. NOTE: Inserted elements will not be cloned, due to the fact,
    * that the {@link Object#clone()} is <code>protected</code>.
    * 
    * @see Object#clone()
    */
   public MyList<E> clone() {
      try {

         MyList<E> clone = (MyList<E>) super.clone();
         clone.begin = this.begin.clone();
         clone.pos = clone.begin;

         return clone;
      } catch (CloneNotSupportedException e) {
         throw new InternalError();
      }
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((begin == null) ? 0 : begin.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MyList other = (MyList) obj;
      if (!begin.equals(other.begin))
         return false;
      return true;
   }
   /**
    * erlaubt einem Visitor v, jedes Element der Liste zu durchlaufen
    * @param Visitor v  Visitor-Objekt, dass die Liste besucht
    * @throws NoSuchElementException  wenn Liste leer
    */
   @Override
   public void accept(Visitor<E> v) {
   	
   	if(empty()){
   		throw new NoSuchElementException();
   	}
   	MyEntry<E> current=begin;
   	current.next=begin.next;
   	
   	while(current!=null && v.visit(current.o)){
   		
   		current=current.next;
   		
   		
   	}
   }
/**
 * liefert einen ListIterator
 * @return  neuer ListIterator
 */
@Override
public Iterator<E> iterator() {
	return new ListIterator();
}
/**
 * implementiert das Interface Iterator
 * @author sHilg_000
 *
 */
public class ListIterator implements Iterator<E>{
	private MyEntry<E> previous;
	private MyEntry<E> obj;
	private MyEntry<E> next;
	private int failCounter=counter;
	
	private ListIterator(){
		this.obj=begin;
		this.next=begin.next;
		this.previous=null;
	}
	/**
	 * liefert zurueck, ob das aktuelle Element das letzte der Liste ist
	 * @return boolean true, wenn nicht das letzte Element, sonst false
	 */
	public boolean hasNext(){
		return next!=null;
	}
	/**
	 * liefert das naechste Objekt in der Liste
	 * @return E  naechstes Objekt in der Liste
	 * @throws NoSuchElementException wenn kein naechstes Element
	 * @throws RuntimeException wenn Liste durch parallelen Zugriff modifiziert wurde
	 */
	public E next(){
	
		if(next==null){
			throw new NoSuchElementException();
		}
		if(failCounter!=counter){
			throw new RuntimeException("Liste wurde modifiziert!");
		}	
		previous=obj;
		obj=next;
		next=next.next;
		return obj.o;
		}
	/**
	 * entfernt das aktuelle Objekt in der Liste
	 * @throws RuntimeException wenn Liste durch parallelen Zugriff modifiziert wurde
	 * @throws NoSuchElementException wenn am Ende der Liste oder next() nach dem letzten delete() nicht aufgerufen wurde
	 */
	public void remove(){
		if(failCounter!=counter){
			throw new RuntimeException("Liste wurde modifiziert!");
		}
		if(next==null|| previous==null){
			throw new NoSuchElementException();
		}
		
		previous.next=next;
		obj=null;

		
	}
	}


}
		


