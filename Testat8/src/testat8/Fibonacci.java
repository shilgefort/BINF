package testat8;
import java.io.Serializable;
import java.util.HashMap;
import java.io.*;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class Fibonacci implements Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<Integer, Long> fibonacciHash;
	private static String name = "./hashMap.ser";
	private static File file;
	
   /**
    * Fill HashMap with initial key-value-pairs.
    */
	

   /**
    * Calculates the fibonacci value of n.
    * 
    * @param n
    *           a natural number >= 0 to calculate the fibonacci value of
    * 
    * @return fibonacci value of n
    */
   public static long fibonacci(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("n = " + n);
      }
      return getFibonacci(n);
   }

   private static long getFibonacci(int n) {
      if (fibonacciHash.containsKey(n)) {
         return fibonacciHash.get(n);
      } else {
         long nMinus1 = getFibonacci(n - 1);
         long nMinus2 = getFibonacci(n - 2);
         long fibonacci = nMinus1 + nMinus2;

         fibonacciHash.put(n, fibonacci);
         return fibonacci;
      }
   }

   public static void main(String[] args) {
      if (args.length != 1) {
         printUsage();
      }
      
      //Wenn die Datei zum ersten Mal aufgerufen wird
      if(file==null){
      //Lege neue Datei an, Konstruktor fuer HashMap	  	  
    	  	  file=new File(name);
    	      fibonacciHash = new HashMap<>();
    	      fibonacciHash.put(0, 0L);
    	      fibonacciHash.put(1, 1L);
      //schreibe in File
    	      writeFile();
    	  	
      }
      //Wenn nicht erster Aufruf
      else{
      //Lese aus File	 
    	   try  {
    		   	ObjectInputStream in = new ObjectInputStream(new FileInputStream("hashMap.ser"));
    		   	in.readObject();
    		   	in.close();
    		   	

    	      } catch (FileNotFoundException e) {
    	         e.printStackTrace();
    	      } catch (IOException e) {
    	         e.printStackTrace();
    	      } catch (ClassNotFoundException e) {
    	        e.printStackTrace();
    	      }
      }
      
      try {
         System.out.println(fibonacci(Integer.parseInt(args[0])));

      } catch (IllegalArgumentException ex) {
         printUsage();
      }
      //Schreibe in File
      writeFile();
   }

   private static void printUsage() {
      System.out.println("java calc/Fibonacci n");
      System.out.println("n must be a natural number >= 0");
   }
   /**
    * Deserialisierung
    * @param s	InputStream
    * @throws IOException
    */
   private void readObject(ObjectInputStream s)throws IOException{
	 try{  
	    fibonacciHash = (HashMap<Integer, Long>)s.readObject();
	 }
	 catch(ClassNotFoundException e){
		 System.out.println(""+e.getStackTrace());
	 }
   }
   /**
    * Serialisierung
    * @param s	OutputStream
    */
   private void writeObject(ObjectOutputStream s){
	   try{
	   	
	   	s.writeObject(fibonacciHash);
	   }
	   catch(java.io.IOException e){
		   System.out.println(""+e.getStackTrace());
	   }
   }
   /**
    * Ruft die writeObject-Methode auf 
    */
   //Code-Stueck wurde an zwei Zeilen verwendet, daher Methode angelegt
   private static void writeFile(){
	   try  {
		   	 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hashMap.ser"));
	         out.writeObject(fibonacciHash);
	         out.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
   }
  

}
