import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class MineroOrigin {

    public static void main(String[] args) {
        SHAone digester = new SHAone();
        int sumz = 0;
        int zeros = 1;
        int tmp;
        int PUERTO=12345;
        String HOST ="localhost";
        Socket cs;
        PrintWriter out;
        BufferedReader in;
        String word = " ";
        int num_zeros = 1;
        int cont = 0;
        String clave, hash;

        Queue<String> cola = new LinkedList<>();

        try {
            //
        cs = new Socket(HOST, PUERTO);
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cs.getOutputStream())), true);
        
                cont++;
                
                in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                
                
                    System.out.println("flag 1");
                    String resp = in.readLine();
                    System.out.println(resp);
                    System.out.println("flag 2");
                    String[] arr = resp.split(" ");
                    word = arr[0];
                    num_zeros = Integer.parseInt(arr[1]);
               
                int H = 4;
                Thread t[] = new Thread[H];
                for (int i = 0; i < H; i++) {
                    t[i] = new Algoritmo(num_zeros, word, cola);
                    t[i].start();
                }
                System.out.println("flag 3");
                for (int i = 0; i < H; i++) {
                    try {
                        t[i].join();
                    } catch (InterruptedException ex) {
                        System.out.println("error" + ex);
                    }
                }
                System.out.println("flag 4");
                System.out.println("cola: " + cola);
                out.println(cola.poll());

                String resp2 = in.readLine();
                System.out.println("Resp2: "+resp2);
                String[] arr2 = resp2.split(" ");
                clave = arr2[0];
                hash = arr2[1];

                String word_concatenate2 = word + clave;
                SHAone digester2 = new SHAone();
                String hash2 = digester2.Encript(word_concatenate2.getBytes());

                System.out.println("hash2: " + hash2);

                if(hash.equals(hash2)){
                    System.out.println("hashes iguales");
                    out.println("OK");
                }else{
                    System.out.println("hashes diferentes");
                }
                
                cs.close();
    
        } catch (Exception ex) {
        
        }
        
    }
}
