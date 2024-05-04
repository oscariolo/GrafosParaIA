package com.example;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AnalizadorGrafos {
    
    public static void analizar_tiempo_maquina(Supplier<ArrayList<Nodo>> task){
        ArrayList<Nodo> camino = new ArrayList<>();
        long startTime = System.nanoTime();
        camino = task.get();
        long endTime = System.nanoTime();
        for(Nodo nodo: camino){
            System.out.println(nodo.get_nombre());
        }
        double tiempo = (endTime - startTime)/1_000_000.0;
        System.out.println("TIEMPO DE EJECUCION: ");
        System.out.println(tiempo + " ms");
        System.out.println("------------------");
    }

    public static double analizar_complejidad_temporal(){
        return 0.0;
    }

    public static double analizar_complejidad_espacial(){
        return 0.0;
    }

    

    


}
