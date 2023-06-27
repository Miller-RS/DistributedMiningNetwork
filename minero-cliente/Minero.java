import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Minero {

  public double sum[] = new double[40];
  TCPMinero mTcpClient;
  Scanner sc;
  Queue<String> cola = new LinkedList<>();

  public static void main(String[] args) {
    Minero objcli = new Minero();
    objcli.iniciar();
  }

  void iniciar() {
    new Thread(
        new Runnable() {
          @Override
          public void run() {
            mTcpClient = new TCPMinero("localhost",
                new TCPMinero.OnMessageReceived() {
                  @Override
                  public void messageReceived(String message) {
                    ClienteRecibe(message);
                  }
                });
            mTcpClient.run();
          }
        }).start();
    // ---------------------------

    // String salir = "n";
    // sc = new Scanner(System.in);
    // System.out.println("Cliente bandera 01");
    // while (!salir.equals("s")) {
    // salir = sc.nextLine();
    // ClienteEnvia(salir);
    // }
    // System.out.println("Cliente bandera 02");
  }

  void ClienteRecibe(String llego) { // TODO Servidor envia mensaje
    System.out.println("El mensaje que recibe del servidor::" + llego);
    String arrayString[] = llego.split("\\s+");

    String word = arrayString[0];
    int n_zeros = Integer.parseInt(arrayString[1]);
    System.out.println("Word: " + word + "\n" + "Num of zeros: " + n_zeros);
    // procesar(n_zer os,word);
  }

  void ClienteEnvia(String envia) {
    if (mTcpClient != null) {
      mTcpClient.sendMessage(envia);
    }
  }

  void procesar(int n_zeros, String word) {
    int H = 4;
    Thread t[] = new Thread[H];
    for (int i = 0; i < H; i++) {
      t[i] = new Algoritmo(n_zeros, word);
      t[i].start();
    }
    for (int i = 0; i < H; i++) {
      try {
        t[i].join();
      } catch (InterruptedException ex) {
        System.out.println("error" + ex);
      }
    }
    // Print the elements of the queue
    // System.out.println("Elements of the queue: " + cola);
  }
}
