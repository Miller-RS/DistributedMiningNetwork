package com.servidortareas;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
  String path = VariablesConexion.PATH;
  String ip = VariablesConexion.IP;
  int puerto = VariablesConexion.PORT;
  // Create list of strings
  List<String> words = new ArrayList<String>();

  public void iniciarServidor() {
    try {
      ServerSocket ss = new ServerSocket(puerto);
      System.out.println("Iniciando el servidor en el Puerto: " + puerto + "...");

      while (true) {
        // Esperamos a que un cliente se conecte
        Socket sc = ss.accept(); // Acepta la conexion entrante
        System.out.println("Cliente conectado desde la IP: " + sc.getInetAddress() + ":" + sc.getPort());

        // Creamos un objeto PrintWriter para enviar datos al cliente
        PrintWriter out = new PrintWriter(new OutputStreamWriter(sc.getOutputStream(), "ISO-8859-1"), true);
        // BufferedReader in = new BufferedReader(new
        // InputStreamReader(sc.getInputStream()));
        // String mensajeCliente = in.readLine();
        // System.out.println("Mensaje del cliente: " + mensajeCliente);

        // Enviamos un mensaje al cliente
        // out.println("¡Conexion exitosa!");
        words = this.leerArchivo();
        System.out.println(words);
        // Enviar solo un elemento de words
        out.println(words.get(0));
        // Enviar todos los elementos de words
        // for (String word : words) {
        // out.println(word);
        // }

        // Cerramos la conexion con el cliente
        sc.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<String> leerArchivo() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    List<String> palabras = new ArrayList<String>();

    try {
      // Apertura del fichero y creacion de BufferedReader para poder
      // hacer una lectura comoda (disponer del metodo readLine()).
      archivo = new File(path);
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);

      // Lectura del fichero
      String linea = br.readLine();
      while (linea != null) {
        // System.out.println(linea);
        palabras.add(linea);
        linea = br.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // En el finally cerramos el fichero, para asegurarnos
      // que se cierra tanto si todo va bien como si salta
      // una excepcion.
      try {
        if (br != null) {
          br.close();
        }
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return palabras;
  }

}
