public class Test {
  public static void main(String[] args) {
    SHAone digester = new SHAone();
    String hash = digester.Encript("hola".getBytes());
    System.out.println("Hash: "+ hash);
  }
}
