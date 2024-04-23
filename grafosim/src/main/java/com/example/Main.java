package com.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.javatuples.Pair;


public class Main {
    public static void main(String[] args) {
        HashMap<String,Nodo> HashNodos = new HashMap<String,Nodo>(); //Map seria mejor para guardar los nodoss
        // //creamos nodos 
        // HashNodos = creacion_nodos();
        // creacion_de_grafo(HashNodos);

       
        HashNodos.put("H", new Nodo("H", 0));
        HashNodos.put("A", new Nodo("A", 1));
        HashNodos.put("B", new Nodo("B", 2));
        HashNodos.put("C", new Nodo("C", 3));
        HashNodos.put("D", new Nodo("D", 4));
        HashNodos.put("E", new Nodo("E", 5));
        HashNodos.put("F", new Nodo("F", 6));
        HashNodos.put("G", new Nodo("G", 7));
        HashNodos.put("J", new Nodo("J", 8));
        HashNodos.put("K", new Nodo("K", 9));
        HashNodos.put("L", new Nodo("L", 10));
        
        // Create edges (aristas) between nodes
        HashNodos.get("H").crear_arista(HashNodos.get("A"), 1);
        HashNodos.get("A").crear_arista(HashNodos.get("H"), 1);

        HashNodos.get("H").crear_arista(HashNodos.get("B"), 1);
        HashNodos.get("B").crear_arista(HashNodos.get("H"), 1);
        
        HashNodos.get("H").crear_arista(HashNodos.get("C"), 1);
        HashNodos.get("C").crear_arista(HashNodos.get("H"), 1);

        HashNodos.get("A").crear_arista(HashNodos.get("D"), 1);
        HashNodos.get("D").crear_arista(HashNodos.get("A"), 1);

        HashNodos.get("A").crear_arista(HashNodos.get("E"), 1);
        HashNodos.get("E").crear_arista(HashNodos.get("A"), 1);
        
        HashNodos.get("B").crear_arista(HashNodos.get("F"), 1);
        HashNodos.get("F").crear_arista(HashNodos.get("B"), 1);

        HashNodos.get("C").crear_arista(HashNodos.get("G"), 1);
        HashNodos.get("G").crear_arista(HashNodos.get("C"), 1);

        HashNodos.get("C").crear_arista(HashNodos.get("J"), 1);
        HashNodos.get("J").crear_arista(HashNodos.get("C"), 1);

        HashNodos.get("D").crear_arista(HashNodos.get("K"), 1);
        HashNodos.get("K").crear_arista(HashNodos.get("D"), 1);

        HashNodos.get("D").crear_arista(HashNodos.get("L"), 1);
        HashNodos.get("L").crear_arista(HashNodos.get("D"), 1);

        HashNodos.get("L").crear_arista(HashNodos.get("F"), 0);
        HashNodos.get("F").crear_arista(HashNodos.get("L"), 1);

        //en este caso los pesos son los valores heuristicos
        HashNodos.put("s0", new Nodo("s0", 0));
        HashNodos.put("s1", new Nodo("s1", 20));
        HashNodos.put("s2", new Nodo("s2", 20));
        HashNodos.put("s3", new Nodo("s3", 10));
        HashNodos.put("s4", new Nodo("s4", 40));
        HashNodos.put("s5", new Nodo("s5", 100));
        HashNodos.put("s6", new Nodo("s6", 110));
        HashNodos.put("s7", new Nodo("s7", 222));
        HashNodos.put("s8", new Nodo("s8", 0));
        
        // Create edges (aristas) between nodes
        HashNodos.get("s0").crear_arista(HashNodos.get("s1"), 10);
        HashNodos.get("s0").crear_arista(HashNodos.get("s4"), 10);
        HashNodos.get("s0").crear_arista(HashNodos.get("s5"), 10);
        HashNodos.get("s0").crear_arista(HashNodos.get("s6"), 20);
        HashNodos.get("s1").crear_arista(HashNodos.get("s2"), 100);
        HashNodos.get("s2").crear_arista(HashNodos.get("s3"), 25);
        HashNodos.get("s3").crear_arista(HashNodos.get("s8"), 5);
        HashNodos.get("s4").crear_arista(HashNodos.get("s2"), 80);
        HashNodos.get("s5").crear_arista(HashNodos.get("s3"), 20);
        

    // HashMap to store nodes with their name as the key

// Add nodes to the HashMap
// HashNodos.put("S", new Nodo("S", 10));
// HashNodos.put("A", new Nodo("A", 9));
// HashNodos.put("B", new Nodo("B", 7));
// HashNodos.put("D", new Nodo("D", 8));
// HashNodos.put("H", new Nodo("H", 6));
// HashNodos.put("F", new Nodo("F", 6));
// HashNodos.put("G", new Nodo("G", 3));
// HashNodos.put("E", new Nodo("E", 0));
// HashNodos.put("C", new Nodo("C", 8));
// HashNodos.put("L", new Nodo("L", 6));
// HashNodos.put("I", new Nodo("I", 4));
// HashNodos.put("J", new Nodo("J", 4));
// HashNodos.put("K", new Nodo("K", 3));

// Create edges (aristas) between nodes (bidirectional)
// HashNodos.get("S").crear_arista(HashNodos.get("B"), 2);
// HashNodos.get("B").crear_arista(HashNodos.get("S"), 2); // Add the edge in the opposite direction
// HashNodos.get("S").crear_arista(HashNodos.get("A"), 7);
// HashNodos.get("B").crear_arista(HashNodos.get("A"), 3);
// HashNodos.get("A").crear_arista(HashNodos.get("B"), 3);
// HashNodos.get("A").crear_arista(HashNodos.get("S"), 7); // Add the edge in the opposite direction
// HashNodos.get("S").crear_arista(HashNodos.get("C"), 3);
// HashNodos.get("C").crear_arista(HashNodos.get("S"), 3); // Add the edge in the opposite direction
// HashNodos.get("D").crear_arista(HashNodos.get("A"), 4);
// HashNodos.get("A").crear_arista(HashNodos.get("D"), 4); // Add the edge in the opposite direction
// HashNodos.get("D").crear_arista(HashNodos.get("B"), 4);
// HashNodos.get("B").crear_arista(HashNodos.get("D"), 4); // Add the edge in the opposite direction
// HashNodos.get("D").crear_arista(HashNodos.get("F"), 5);
// HashNodos.get("F").crear_arista(HashNodos.get("D"), 5); // Add the edge in the opposite direction
// HashNodos.get("H").crear_arista(HashNodos.get("B"), 1);
// HashNodos.get("B").crear_arista(HashNodos.get("H"), 1); // Add the edge in the opposite direction
// HashNodos.get("H").crear_arista(HashNodos.get("F"), 3);
// HashNodos.get("F").crear_arista(HashNodos.get("H"), 3); // Add the edge in the opposite direction
// HashNodos.get("H").crear_arista(HashNodos.get("G"), 2);
// HashNodos.get("G").crear_arista(HashNodos.get("H"), 2); // Add the edge in the opposite direction
// HashNodos.get("G").crear_arista(HashNodos.get("E"), 2);
// HashNodos.get("E").crear_arista(HashNodos.get("G"), 2); // Add the edge in the opposite direction
// HashNodos.get("C").crear_arista(HashNodos.get("L"), 2);
// HashNodos.get("L").crear_arista(HashNodos.get("C"), 2); // Add the edge in the opposite direction
// HashNodos.get("L").crear_arista(HashNodos.get("I"), 4);
// HashNodos.get("I").crear_arista(HashNodos.get("L"), 4); // Add the edge in the opposite direction
// HashNodos.get("L").crear_arista(HashNodos.get("J"), 4);
// HashNodos.get("J").crear_arista(HashNodos.get("L"), 4);
// HashNodos.get("I").crear_arista(HashNodos.get("J"), 6);
// HashNodos.get("J").crear_arista(HashNodos.get("I"), 6);
// HashNodos.get("J").crear_arista(HashNodos.get("K"), 4);
// HashNodos.get("K").crear_arista(HashNodos.get("J"), 4);
// HashNodos.get("I").crear_arista(HashNodos.get("K"), 4);
// HashNodos.get("K").crear_arista(HashNodos.get("I"), 4);
// HashNodos.get("K").crear_arista(HashNodos.get("E"), 5);
// HashNodos.get("E").crear_arista(HashNodos.get("K"), 5);

        //recorremos segun escoje
        // ListaNodos.get(0).recorre_profundidad(null);     
        
        Buscador buscador = new Buscador();
        //ArrayList<Nodo> objetivos = new ArrayList<Nodo>();
        //objetivos.add(HashNodos.get("F"));
        try {
            buscador.busqueda_bidireccional(HashNodos.get("H"),HashNodos.get("F"));
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        //Menu();
    }

    public static void Menu(){
        HashMap<String,Nodo> HashNodos = new HashMap<String,Nodo>();
        HashNodos.put("H", new Nodo("H", 0));
        HashNodos.put("A", new Nodo("A", 1));
        HashNodos.put("B", new Nodo("B", 2));
        HashNodos.put("C", new Nodo("C", 3));
        HashNodos.put("D", new Nodo("D", 4));
        HashNodos.put("E", new Nodo("E", 5));
        HashNodos.put("F", new Nodo("F", 6));
        HashNodos.put("G", new Nodo("G", 7));
        HashNodos.put("J", new Nodo("J", 8));
        HashNodos.put("K", new Nodo("K", 9));
        HashNodos.put("L", new Nodo("L", 10));
        
        // Create edges (aristas) between nodes
        HashNodos.get("H").crear_arista(HashNodos.get("A"), 1);
        HashNodos.get("A").crear_arista(HashNodos.get("H"), 1);
        HashNodos.get("H").crear_arista(HashNodos.get("B"), 1);
        HashNodos.get("B").crear_arista(HashNodos.get("H"), 1);
        HashNodos.get("H").crear_arista(HashNodos.get("C"), 1);
        HashNodos.get("C").crear_arista(HashNodos.get("H"), 1);
        HashNodos.get("A").crear_arista(HashNodos.get("D"), 1);
        HashNodos.get("A").crear_arista(HashNodos.get("E"), 1);
        HashNodos.get("B").crear_arista(HashNodos.get("F"), 1);
        HashNodos.get("F").crear_arista(HashNodos.get("B"), 1);
        HashNodos.get("C").crear_arista(HashNodos.get("G"), 1);
        HashNodos.get("C").crear_arista(HashNodos.get("J"), 1);
        HashNodos.get("D").crear_arista(HashNodos.get("K"), 1);
        HashNodos.get("D").crear_arista(HashNodos.get("L"), 1);
        HashNodos.get("L").crear_arista(HashNodos.get("F"), 0);






        Pair<Nodo,ArrayList<Nodo>> nodos_seleccionados = seleccion_nodos(HashNodos);
        Buscador buscador = new Buscador();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while(opcion != 11){
            System.out.println("1. Crear nodos");
            System.out.println("2. Crear grafo");
            System.out.println("3. Cargar archivo");
            System.out.println("4. Busqueda amplitud");
            System.out.println("5. Busqueda profundidad");
            System.out.println("6. Busqueda profundidad iterativa");
            System.out.println("7. Busqueda costo uniforme");
            System.out.println("8. Busqueda Gradiente");
            System.out.println("9. Busqueda primero el mejor");
            System.out.println("10. Busqueda A*");
            System.out.println("11. Salir");

            opcion = sc.nextInt();
            switch (opcion) {
                
                case 1:
                    HashNodos = creacion_nodos();
                    break;
                case 2:
                    creacion_de_grafo(HashNodos);
                    break;
                case 3:

                    break;
                case 4:
                    nodos_seleccionados = seleccion_nodos(HashNodos);
                    buscador.busqueda_amplitud(nodos_seleccionados.getValue0(), nodos_seleccionados.getValue1());
                    break;
                case 5:
                    nodos_seleccionados = seleccion_nodos(HashNodos);
                    buscador.busqueda_profundidad(nodos_seleccionados.getValue0(), nodos_seleccionados.getValue1());
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    break;
            }
        }
        sc.close();

    }

    public static Pair<Nodo,ArrayList<Nodo>> seleccion_nodos(HashMap<String,Nodo> mapa_nodos){
        int counter = 0;
        ArrayList<Nodo> nodos_destino = new ArrayList<Nodo>();
        for(Nodo minodo2: mapa_nodos.values()){
            System.out.println(counter + ". " + minodo2.get_nombre());
            counter++;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Nodo origen: ");
        String nodo_origen = sc.next();
        System.out.println("Nodos destinos: Escriba SALIR para terminar");
        while(true){
            String nodo_destino = sc.next();
            if(nodo_destino.equals("SALIR")){
                break;
            }
            nodos_destino.add(mapa_nodos.get(nodo_destino));
        }
        sc.close();

        return new Pair<Nodo,ArrayList<Nodo>>(mapa_nodos.get(nodo_origen),nodos_destino);
    }


    public static HashMap<String,Nodo> creacion_nodos(){
        HashMap<String,Nodo> nuevoMapa = new HashMap<>();
        String salir = "n";
        int contador_nodos = 0;
        while(!salir.equals("y")){
            System.out.println("Nodos actuales: " + contador_nodos);
            System.out.println("-------------------");
            System.out.println("Nombre: ");
            Scanner sc = new Scanner(System.in);
            String nombre = sc.next();
            System.out.println("Valor: ");
            int valor = sc.nextInt();
            nuevoMapa.put(nombre, new Nodo(nombre, valor));
            sc.close();
            System.out.println("Desea salir y/n");
            Scanner sc2 = new Scanner(System.in);
            salir = sc2.next();
            sc2.close();
        }


        return nuevoMapa;
    }

    public static void creacion_de_grafo(HashMap<String,Nodo> mapa_nodos){

        String salir = "n";
        while(!salir.equals("y")){
            int counter = 0;
            for(Nodo minodo2: mapa_nodos.values()){
                System.out.println(counter + ". " + minodo2.get_nombre());
                counter++;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Nodo origen: ");
            String nodo_origen = sc.next();
            System.out.println("Nodo destino: ");
            String nodo_destino = sc.next();
            System.out.println("Peso de la arista: ");
            int peso = sc.nextInt();
            mapa_nodos.get(nodo_origen).crear_arista(mapa_nodos.get(nodo_destino), peso);
            System.out.println("Desea salir? s/n");
            sc.close();
            Scanner sc2 = new Scanner(System.in);
            salir = sc2.nextLine();
            sc2.close();
            
        }

    }
}


