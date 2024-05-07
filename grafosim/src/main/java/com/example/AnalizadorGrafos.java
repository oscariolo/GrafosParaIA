package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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

    public static Integer max_aristas(HashMap<String,Nodo> HashNodos, boolean dirigido){
        Integer total_aristas = 0;
        Set<Nodo> visitados = new HashSet<>();
        
        for(Nodo padre: HashNodos.values()){
            Integer desconteo = 0;
            Integer num_aristas = padre.get_aristas().size();
            if(!dirigido){
                for(Nodo nodo:padre.get_hijos()){
                    if(visitados.contains(nodo)){
                        desconteo--;
                    }
                }
            }
            total_aristas = total_aristas + num_aristas + desconteo;
            visitados.add(padre);
        }

        return total_aristas;
    }

    public static Integer max_hijos(HashMap<String,Nodo> nodos){
        Integer current_max = 0;
        Integer ctd_hijos = 0;
        for(Nodo padre: nodos.values()){
            ctd_hijos = padre.get_aristas().size();
            if(ctd_hijos > current_max){
                current_max = ctd_hijos;
            }
        }
        return current_max;
    }

    public static Integer max_deep(Nodo nodo){
        Set<Nodo> visitados = new HashSet<>();
        ArrayList<Nodo> cola = new ArrayList<>();
        cola.add(nodo);
        Integer profundidad = 1;
        while(!cola.isEmpty()){
            ArrayList<Nodo> hijos = new ArrayList<>();
            for(Nodo hijo: cola){
                if(!visitados.contains(hijo)){
                    hijos.add(hijo);
                    visitados.add(hijo);
                }
            }
            cola = hijos;
            profundidad ++;
        }
        return profundidad;
    }

    private static void imprimir_resultado(Resultado resultado){
        System.out.printf("%-50s | %-50s%n", "COLA", "EXTRACCION");
        for(ArrayList<Nodo> cola: resultado.historial_cola){
            StringBuilder colaString = new StringBuilder();
            for(Nodo nodo: cola){
                colaString.append(nodo.get_nombre()).append(" ");
            }
            if(!resultado.historial_extraccion.isEmpty()){
                String extraccion = resultado.historial_extraccion.remove(0).get_nombre();
                System.out.printf("%-50s | %-50s%n", colaString.toString(), extraccion);
            }
            
        }

    }


    public static void getComplexityResults_Not_Heurisitc(String methodname,double hijos, double profundidad){
        double temporal_complexity = 0.0;
        double spatial_complexity = 0.0;
        switch(methodname){
            case ("amplitud"):
            temporal_complexity = Math.pow(hijos,profundidad+1);
            spatial_complexity = temporal_complexity;
            break;
            case("profundidad"):
            temporal_complexity = Math.pow(hijos,profundidad);
            spatial_complexity = hijos * profundidad;
            break;
            case("bidireccional"):
            temporal_complexity = Math.pow(hijos,profundidad/2);
            spatial_complexity = temporal_complexity;
            break;
            case("costo_uniforme"):
            temporal_complexity = Math.pow(hijos, profundidad);
            spatial_complexity = temporal_complexity;
            break;
            default:
            throw new IllegalArgumentException("Invalid method name: " + methodname);
        }
        System.out.println("COMPLEJIDAD: ");
        System.out.println("Temporal: " + temporal_complexity);
        System.out.println("Espacial: " + spatial_complexity);
    }

    public static void getComplexityResults_Heurisitc(String methodname,double aristas,double num_nodos){
        double temporal_complexity = 0.0;
        double spatial_complexity = 0.0;

        switch(methodname){
            case ("gradiente"):
            temporal_complexity = 1.0;
            spatial_complexity = Math.pow(aristas,num_nodos);
            break;
            case ("primero_mejor"):
            temporal_complexity = Math.log(num_nodos) * aristas;
            spatial_complexity = aristas + num_nodos;
            break;
            case("A_estrella"):
            temporal_complexity = Math.log(num_nodos) * aristas;
            spatial_complexity = aristas + num_nodos;
            break;
            default:
            throw new IllegalArgumentException("Invalid method name: " + methodname);
        }
        System.out.println("COMPLEJIDAD: ");
        System.out.println("Temporal: " + temporal_complexity);
        System.out.println("Espacial: " + spatial_complexity);
    }

    


    

    


}
