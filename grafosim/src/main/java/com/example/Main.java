package com.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

import org.javatuples.Pair;


public class Main {
    private static boolean close_sc = false;
    public static void main(String[] args) { 
        
        Menu();
    }

    public static void Menu(){
        HashMap<String,Nodo> HashNodos = new HashMap<String,Nodo>();
        
        Pair<Nodo,ArrayList<Nodo>> nodos_seleccionados = Pair.with(HashNodos.get("H"), new ArrayList<Nodo>());
        Buscador buscador = new Buscador();    
        int opcion = 0;
        
        while(opcion != 12){
            final Nodo nodo_inicial = nodos_seleccionados.getValue0();
            final ArrayList<Nodo> nodos_seleccionados_final = new ArrayList<Nodo>(nodos_seleccionados.getValue1());
            System.out.println("1. Crear nodos");
            System.out.println("2. Seleccionar nodo inicial y finales");
            System.out.println("3. Cargar archivo");
            System.out.println("4. Busqueda amplitud");
            System.out.println("5. Busqueda profundidad");
            System.out.println("6. Busqueda profundidad iterativa");
            System.out.println("7. Busqueda bidireccional");
            System.out.println("8. Busqueda coste uniforme");
            System.out.println("9. Busqueda Gradiente");
            System.out.println("10. Busqueda primero el mejor");
            System.out.println("11. Busqueda A*");
            System.out.println("12. Salir");
            
            opcion = Integer.parseInt(readOption());
            // Runnable task = () -> {};
            Supplier<Resultado> task = null;
            final Integer max_hijos = AnalizadorGrafos.max_hijos(HashNodos);
            final Integer max_deep = AnalizadorGrafos.max_deep(nodo_inicial);
            final Integer max_aristas = AnalizadorGrafos.max_aristas(HashNodos,true);
            System.out.println("deep: " + max_deep);
            System.out.println("hijos: " + max_hijos);
            
            switch (opcion) {
                
                case 1:
                    HashNodos = creacion_nodos();
                    break;
                case 2:
                    nodos_seleccionados = seleccion_nodos(HashNodos);
                    break;
                case 3:
                    CSVGrafoParser parser = new CSVGrafoParser();
                    HashNodos = parser.load_graph();
                    nodos_seleccionados = seleccion_nodos(HashNodos);
                    break;
                case 4:
                    
                    task = () -> buscador.busqueda_amplitud(nodo_inicial, nodos_seleccionados_final);
                    AnalizadorGrafos.getComplexityResults_Not_Heurisitc("amplitud", max_hijos, max_deep);
                    break;
                case 5:
                    task = () -> buscador.busqueda_profundidad(nodo_inicial, nodos_seleccionados_final);
                    AnalizadorGrafos.getComplexityResults_Not_Heurisitc("profundidad", max_hijos, max_deep);

                    break;
                case 6:
                    double St_time = System.nanoTime();
                    buscador.busqueda_profundidad_iterativa(nodo_inicial, nodos_seleccionados_final);
                    double Ed_time = System.nanoTime();
                    double timed = (Ed_time - St_time)/1_000_000;
                    AnalizadorGrafos.getComplexityResults_Not_Heurisitc("profundidad", max_hijos, max_deep);
                    System.out.println(timed + " ms");
                    break;
                case 7:
                    double S_time = System.nanoTime();
                    buscador.busqueda_bidireccional(nodo_inicial, nodos_seleccionados_final.get(0));
                    double E_time = System.nanoTime();
                    AnalizadorGrafos.getComplexityResults_Not_Heurisitc("bidireccional", max_hijos,max_deep);
                    double time = (E_time - S_time)/1_000_000;
                    System.out.println(time + " ms");
                    break;
                case 8:
                    double C_E = buscador.busqueda_costo_uniforme(nodo_inicial, nodos_seleccionados_final).getValue1();
                    task = () -> buscador.busqueda_costo_uniforme(nodo_inicial, nodos_seleccionados_final).getValue0();
                    AnalizadorGrafos.getComplexityResults_Not_Heurisitc("costo_uniforme",max_hijos,C_E);
                    break;
                case 9:
                    task = () -> buscador.busqueda_del_gradiente(nodo_inicial, nodos_seleccionados_final);
                    AnalizadorGrafos.getComplexityResults_Heurisitc("gradiente",max_hijos, (double)HashNodos.size());
                    break;
                case 10:
                    task = () -> buscador.busqueda_primero_el_mejor(nodo_inicial, nodos_seleccionados_final);
                    AnalizadorGrafos.getComplexityResults_Heurisitc("primero_mejor", max_aristas,(double)HashNodos.size());
                    break;
                case 11:
                    task = () -> buscador.busqueda_A_estrella(nodo_inicial, nodos_seleccionados_final);
                    AnalizadorGrafos.getComplexityResults_Heurisitc("A_estrella", max_aristas,(double)HashNodos.size());
                    break;
                case 12:
                    close_sc = true;
                default:
                    break;
            }
            if(task != null){
                AnalizadorGrafos.analizar_tiempo_maquina(task);
            }
            
            

        }

    }

    public static String readOption(){
        Scanner sc = new Scanner(System.in);
        String option = "";
        if(sc.hasNext()){
            option = sc.next();
        }else{
            sc.next();
        }
        if(close_sc){
            sc.close();
        }
        return option;

    }

    public static Pair<Nodo,ArrayList<Nodo>> seleccion_nodos(HashMap<String,Nodo> mapa_nodos){
        ArrayList<Nodo> nodos_destino = new ArrayList<Nodo>();
        for(Nodo minodo2: mapa_nodos.values()){
            System.out.println(minodo2.get_nombre());
        }
        System.out.println("Nodo origen: ");
        String nodo_origen = readOption();
        System.out.println("Nodos destinos: Escriba SALIR para terminar");
        while(true){
            String nodo_destino = readOption();
            if(nodo_destino.equals("SALIR")){
                break;
            }
            nodos_destino.add(mapa_nodos.get(nodo_destino));
        }
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


