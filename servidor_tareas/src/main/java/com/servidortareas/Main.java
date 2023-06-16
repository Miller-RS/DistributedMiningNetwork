package com.servidortareas;

import com.servidortareas.Servidor;

class GlobalVals {
    static final String PATH = "E:\\Documents\\Aplicaciones\\Java\\DistributedMiningNetwork\\servidor_tareas\\src\\main\\java\\com\\servidortareas\\listapalabras1.txt";
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world! Miller");
        System.out.println("Hello world! Gerald");
        System.out.println("Hello world! Miller branch and Gerald branch");
        Servidor sr = new Servidor(GlobalVals.PATH);
        sr.leerArchivo();
    }
}