package testat8;
import java.io.*;
public class HeapTest {

	public static void main(String[]argv){
		
		Heap<Integer> heap=new Heap<Integer>();
		Heap<Integer> copyHeap=null;
		int[]array={1,2,3,4,5,6,7,8};
		
		for(int i=0; i<array.length; i++){
			
			heap.insert(array[i]);
			
		}
		 File file = new File("Heap.ser");
		 //Serialisieren
		  try {
		      FileOutputStream out = new FileOutputStream(file);
		      ObjectOutputStream output = new ObjectOutputStream(out);

		      output.writeObject(heap);
		      output.close();
		    } catch (IOException e) {
		    	System.out.println("Konnte heap nicht serialisieren");
		    	System.out.println(""+e.getStackTrace());
		    }
		 
		  //Deserialisieren
		  try{
			  FileInputStream in = new FileInputStream(file);
			  ObjectInputStream input = new ObjectInputStream(in);
			  copyHeap=(Heap<Integer>)input.readObject();
			  
			  input.close();
		  }
		  catch(IOException e){
			  System.out.println("Konnte heap nicht deserialisieren");
			  e.printStackTrace();
		  }
		  catch(ClassNotFoundException e){
			  System.out.println(""+e.getStackTrace());
		  }
		  
		  System.out.println("Test auf Gleichheit: "+heapTest(heap, copyHeap));
		  //Loeschen der Wurzel
		  heap.deleteFirst();
		  System.out.println("Keine Seiteneffekte?: "+(heap.getFirst()!=copyHeap.getFirst()));
		  
	}
	public static boolean heapTest(Heap<Integer> soll, Heap<Integer> test){
		boolean check=true;
		Integer[]a=new Integer[soll.size()];
		Integer[]b=new Integer[test.size()];
			
		
		for(int i=0; i==soll.size();i++){
			a[i]=soll.deleteFirst();
			b[i]=test.deleteFirst();
		}
		
		for(int j=0; j<a.length; j++){
			if(a[j]!=b[j]){
				check=false;
			}
		}
		
		return check;
	}

}
