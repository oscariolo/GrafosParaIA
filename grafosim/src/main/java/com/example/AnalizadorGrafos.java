package com.example;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AnalizadorGrafos {
    


    public static void analizar_tiempo_maquina(Supplier<Resultado> task){
        // ArrayList<Nodo> camino = new ArrayList<>();
        // long startTime = System.nanoTime();
        // camino = task.get();
        // long endTime = System.nanoTime();
        // for(Nodo nodo: camino){
        //     System.out.println(nodo.get_nombre());
        // }
        // double tiempo = (endTime - startTime)/1_000_000.0;
        // System.out.println("TIEMPO DE EJECUCION: ");
        // System.out.println(tiempo + " ms");
        // System.out.println("------------------");
        long startTime = System.nanoTime();
        Resultado resultado = task.get();
        long endTime = System.nanoTime();
        // System.out.println("COLA");
        // for(ArrayList<Nodo> cola: resultado.historial_cola){
        //     for(Nodo nodo: cola){
        //         System.out.print(" " + nodo.get_nombre());
        //     }
        //     System.out.println("EXTRACCION: ");
        //     System.out.println(resultado.historial_extraccion.remove(0).get_nombre());
        // }
        AnalizadorGrafos.imprimir_resultado(resultado);
        double tiempo = (endTime - startTime)/1_000_000.0;
        System.out.println(tiempo + " ms");
        
    }

    private static void imprimir_resultado(Resultado resultado){
        System.out.printf("%-50s | %-50s%n", "COLA", "EXTRACCION");
        for(ArrayList<Nodo> cola: resultado.historial_cola){
            StringBuilder colaString = new StringBuilder();
            for(Nodo nodo: cola){
                colaString.append(nodo.get_nombre()).append(" ");
            }
            String extraccion = resultado.historial_extraccion.remove(0).get_nombre();
            System.out.printf("%-50s | %-50s%n", colaString.toString(), extraccion);
        }

    }

    public static double analizar_complejidad_temporal(){
        return 0.0;
    }

    public static double analizar_complejidad_espacial(){
        return 0.0;
    }

    

    


}
