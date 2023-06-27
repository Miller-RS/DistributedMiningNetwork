import java.util.Queue;

public class Algoritmo {
  public int zeros;
  public String palabra;

  public Algoritmo(int num_zeros, String word) {
    zeros = num_zeros;
    palabra = word;
  }

  public void findHash(Queue<String> cola) {
    // Generate random numbers
    int key = (int) Math.random() * 1000000;
    String word_concatenate = palabra + String.valueOf(key);
    SHAone digester = new SHAone();
    String hash = digester.Encript(word_concatenate.getBytes());
    // Check if the hash has the required number of zeros
    if (hash.substring(0, zeros).equals("0".repeat(zeros))) {
      synchronized (cola) {
        cola.add(hash);
      }
    }
  }
}
