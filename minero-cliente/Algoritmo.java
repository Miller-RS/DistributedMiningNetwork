import java.util.LinkedList;
import java.util.Queue;

public class Algoritmo extends Thread{
  public int zeros;
  public String palabra;
  int key;
  Queue<String> colaF;

  public Algoritmo(int num_zeros, String word, Queue<String> cola) {
    zeros = num_zeros;
    palabra = word;
    colaF = cola;
  }

  public void run() {
    // Generate random numbers
    while (true) {
      //key = (int) Math.random() * 1000000;
      key = (int)Math.round((Math.random()*1000000));
      System.out.println("key: "+ key);
      String word_concatenate = palabra + String.valueOf(key);
      SHAone digester = new SHAone();
      String hash = digester.Encript(word_concatenate.getBytes());
      // Check if the hash has the required number of zeros
      System.out.println(hash);
      if (hash.substring(0, zeros).equals("0".repeat(zeros))) {
      //  synchronized (cola) {
          colaF.add(key + " " + hash);
          break;
        //}
      }
    }
  }
}
