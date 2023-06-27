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

    String salir = "n";
    sc = new Scanner(System.in);
    System.out.println("Cliente bandera 01");
    while (!salir.equals("s")) {
      salir = sc.nextLine();
      ClienteEnvia(salir);
    }
    System.out.println("Cliente bandera 02");

  }

  void ClienteRecibe(String llego) { // TODO Servidor envia mensaje
    System.out.println("El mensaje que recibe del servidor::" + llego);
    String arrayString[] = llego.split("\\s+");

    String word = arrayString[0];
    int n_zeros = Integer.parseInt(arrayString[1]);
    System.out.println("Word: " + word + "\n" + "Num of zeros: " + n_zeros);
    // procesar(funcion, min, max, num_segment);
  }

  private static double evaluarExpresion(String expresion, double x) {
    double resultado = 0.0;

    // "7x^1+8x^2"
    // Eliminar espacios en blanco y separar por el signo '+'
    String[] terminos = expresion.replaceAll("\\s+", "").split("\\+");
    // 7x^1
    // 8x^2

    // Evaluar cada término y sumarlos al resultado
    for (String termino : terminos) {
      // Extraer el coeficiente y el exponente
      String[] partes = termino.split("x\\^");
      // 7 1
      double coeficiente = Double.parseDouble(partes[0]);
      int exponente = Integer.parseInt(partes[1]);

      // Evaluar el término y sumarlo al resultado
      resultado += coeficiente * Math.pow(x, exponente);
    }

    return resultado;
  }

  void ClienteEnvia(String envia) {
    if (mTcpClient != null) {
      mTcpClient.sendMessage(envia);
    }
  }

  double funcion(int fin) {
    double sum = 0;
    for (int j = 0; j <= fin; j++) {
      sum = sum + Math.sin(j * Math.random());
    }
    return sum;
  }

  void procesar(int n_zeros, String word) {
    int H = 4;
    Thread t[] = new Thread[H];
    for (int i = 0; i < H; i++) {
      t[i] = new SHAone().Encript(word.getBytes());
      t[i].start();
    }
  }

  public class tarea0101 extends Thread {

    public double max, min;
    double segments;
    int id;
    String funtion;

    tarea0101(double min_, double max_, String funtion_, double segments_, int id_) {
      max = max_;
      min = min_;
      segments = segments_;
      funtion = funtion_;
      id = id_;
    }

    public void run() {
      System.out.println("bandera 1");
      // double area = 0, ancho, ultimo_ancho;
      // ancho = (max - min) / segments;
      // System.out.println("bandera 2");
      // int lugaresDecimales = 1, count = 0;
      // // double factor = Math.pow(10, lugaresDecimales);
      // // ancho = Math.floor(ancho * factor) / factor;
      // // System.out.println("bandera 3");
      // // System.out.println(ancho);
      // for (double i = min; i < max; i += ancho) {

      // System.out.println("valores del minimo: " + i + "del id: " + id);
      // // System.out.println("bandera 3.5");
      // area += ancho * evaluarExpresion(funtion, i);// 195
      // // .out.println("bandera 3.8");
      // System.out.println("valores del area: " + id + " " + area);
      // count++;

      // }
      // // 195
      // System.out.println("bandera 4");
      // // ultimo_ancho = max - ancho * count;
      // // area = area + ancho * evaluarExpresion(funtion, min + ancho * count);
      // sum[id] = area;
      // System.out.println("bandera 5");
      // // System.out.println(" min:" + min + " max:" + (max - 1) + " id:" + id + "
      // // suma:" + suma);
    }
  }

}
