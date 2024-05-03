package com.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;

import org.javatuples.Pair;

public class Buscador {
    public ArrayList<Nodo> busqueda_profundidad(Nodo inicio,ArrayList<Nodo> objetivos){ //pila
        Stack<Nodo> pilaNodos = new Stack<>();
        ArrayList<Nodo> lista_extraidos = new ArrayList<>();
        pilaNodos.push(inicio);
        // System.out.println("Cola   |   Extraccion");
        while(!pilaNodos.isEmpty()){    
            // // System.out.print(pilaNodos.toString());
            // // System.out.print("   |    ");
            // // System.out.println(pilaNodos.peek().get_nombre());
            // if(objetivos != null && objetivos.contains(pilaNodos.peek())){
            //     // System.out.println("Encontrado" + " Nodo: " + pilaNodos.peek().get_nombre());
            //     extraido.add(pilaNodos.peek());
            //     objetivos.remove(pilaNodos.peek());
            //     visitados.add(pilaNodos.peek());
            //     if(objetivos.isEmpty()){
            //         return extraido;
            //     }
            // }
            
            // ArrayList<Nodo> auxlist = new ArrayList<>();
            // for(Nodo hijo : pilaNodos.peek().get_hijos()){ //obtenemos los hijos y agregamos a una lista para mantener orden de obtencion de hijos para la pila
            //     if(!visitados.contains(hijo) && !pilaNodos.contains(hijo)){
            //         auxlist.add(hijo);
            //     }
            // }
            // pilaNodos.pop(); 
            // Collections.reverse(auxlist);
            // pilaNodos.addAll(auxlist);
            Nodo extraido = pilaNodos.pop();
            lista_extraidos.add(extraido);
            objetivos.remove(extraido);
            if(objetivos.isEmpty()){
                return lista_extraidos;
            }
            for(Nodo nodo: extraido.get_hijos()){
                if(!lista_extraidos.contains(nodo) && !pilaNodos.contains(nodo)){
                    pilaNodos.push(nodo);
                }
            }

        }
        return lista_extraidos;

    }

    public void busqueda_amplitud(Nodo inicio, ArrayList<Nodo> objetivos){ //cola
        ArrayList<Nodo> colaNodos = new ArrayList<>();
        ArrayList<Nodo> visitados = new ArrayList<>();
        colaNodos.add(inicio);
        System.out.println("Cola   |   Extraccion");
        while(!colaNodos.isEmpty()){
            System.out.print(colaNodos.toString());
            System.out.print("   |    ");
            System.out.println(colaNodos.get(0).toString());
            if(objetivos != null && objetivos.contains(colaNodos.get(0))){
                System.out.println("Encontrado" + " Nodo " + colaNodos.get(0).get_nombre());
                objetivos.remove(colaNodos.get(0));
                if(objetivos.isEmpty()){
                    break;
                }
            }
            for(Nodo hijo : colaNodos.get(0).get_hijos()){
                if(!visitados.contains(hijo) && !colaNodos.contains(hijo)){
                    colaNodos.add(hijo);
                }
            }
            visitados.add(colaNodos.get(0));
            colaNodos.remove(0);
        }
    }

    public class listas_visitados{
        public ArrayList<Nodo> visitados1;
        public ArrayList<Nodo> visitados2;
        public listas_visitados(){
            visitados1 = new ArrayList<>();
            visitados2 = new ArrayList<>();
        }

    }
    

    public void busqueda_bidireccional(Nodo inicio, Nodo meta){ //buscamos de un Nodo a otro y nos detenemos al encontrar un nodo comun
        
        listas_visitados visitados = new listas_visitados();
        CompletableFuture<ArrayList<Nodo>> futuro1 = busqueda_amplitud_future(inicio,visitados.visitados1,visitados.visitados2);
        CompletableFuture<ArrayList<Nodo>> futuro2 = busqueda_amplitud_future(meta, visitados.visitados2,visitados.visitados1);
        
        CompletableFuture.allOf(futuro1,futuro2).join();

        System.out.println("visitados 1");
        for(Nodo nodo : visitados.visitados1){
            System.out.println(nodo);
        }
        System.out.println("visitados 2");
        for(Nodo nodo : visitados.visitados2){
            System.out.println(nodo);
        }
    }

    private CompletableFuture<ArrayList<Nodo>> busqueda_amplitud_future(Nodo inicio,ArrayList<Nodo> visitados_propio,ArrayList<Nodo> visitados_externo){
        
        System.out.println("Cola   |   Extraccion");
        CompletableFuture<ArrayList<Nodo>> future = CompletableFuture.supplyAsync(()->{
            ArrayList<Nodo> colaNodos = new ArrayList<>();
            colaNodos.add(inicio);
            while(!colaNodos.isEmpty()){ 
                Nodo extraido = colaNodos.remove(0);
                visitados_propio.add(extraido);
                for(Nodo nodo : extraido.get_hijos()){
                    if(!visitados_propio.contains(nodo)){
                        colaNodos.add(nodo);
                    }        
                }
                ArrayList<Nodo> auxi = new ArrayList<>(visitados_propio);
                auxi.retainAll(visitados_externo);
                if(!auxi.isEmpty()){
                    break;
                }

            }    
            return visitados_propio;
        });
        return future;
    }


    //BUSQUEDA POR PROFUNDIDAD ITERATIVA///////////////(solucion recursiva)
    // public void busqueda_profundidad_iterativa(Nodo inicial,Nodo objetivo){
    //     Integer profundidad = 0;
    //     Integer MAX_DEEP = 1000;
    //     while(profundidad != MAX_DEEP){
    //         Nodo resultado = busqueda_PI_limitada(inicial, objetivo, profundidad); //reinicio busqueda desde el inicio con la nueva profundidad
    //         if(resultado != null){ //si el resultado no es nullo significa que encontro el nodo buscado
    //             System.out.println("Nodo encontrado");
    //             System.out.println(resultado.get_nombre());
    //             break;
    //         }
    //         profundidad++; //aumento la profundidad de busqueda
    //     }       
    // }

    // private Nodo busqueda_PI_limitada(Nodo nodo, Nodo objetivo, Integer profundidad){
    //     System.out.println(nodo.get_nombre());
    //     if(profundidad == 0 && nodo.equals(objetivo)){ //se encuentra en la profundidad objetiva y si es el deseado se devuelve
    //         return nodo;
    //     }
    //     else if(profundidad > 0){ //no esta en la profundidad objetivo por ende debe buscar en el siguiente nivel que son sus hijos
    //         for(Nodo hijo : nodo.get_hijos()){
    //             Nodo resultado = busqueda_PI_limitada(hijo, objetivo, profundidad - 1); //busco resultado desde le hijo con 1 menos de profundidad
    //             if(resultado != null){ //si el resultado fue encontrado se devuelve enseguida y deja de iterar por los hijos
    //                 return resultado;
    //             }
    //         }
            
    //     }
    //     return null; //en esa profundidad no fue encontrada regresa null
    // }

    // public void busqueda_profundidad_iterativa(Nodo inicial, ArrayList<Nodo> objetivos) {
    //     Integer profundidad_final = 0;
    //     Integer profundad_actual = 0;
    //     // Set<Nodo> visitados = new HashSet<>();
    //     while(true){
    //         System.out.println("Profundidad: " + profundidad_final);
    //         ArrayList<Nodo> cola_actual = new ArrayList<>();
    //         cola_actual.add(inicial);
    //         profundad_actual = 0;
    //         while(profundad_actual != profundidad_final){
    //             Integer tam = cola_actual.size(); //cuento el tamanio de los hijos sacados en ese momento
    //             for(int i = 0; i < tam; i++){ //saco los hijos de cada uno 
    //                 cola_actual.addAll(cola_actual.remove(0).get_hijos());
                    
    //             }
    //             profundad_actual++; 
    //         }
    //         if(cola_actual.isEmpty()){
    //             return;
    //         }

    //         for(Nodo nodo : cola_actual){
    //             System.out.println(nodo.get_nombre());
    //             if(objetivos.contains(nodo)){
    //                 System.out.print(nodo.get_nombre());
    //                 objetivos.remove(nodo);
    //             }
    //             if(objetivos.isEmpty()){
    //                 return;
    //             }
    //         }
    //         cola_actual.clear();
    //         // visitados.clear();
    //         profundidad_final++;
    //     }

    // }

    // public void busqueda_profundidad_iterativa(Nodo inicial, List<Nodo> objetivos) {
    // int profundidad_final = 0;
    // Set<Nodo> remainingObjetivos = new HashSet<>(objetivos);
    // Deque<Nodo> cola_actual = new ArrayDeque<>();
    // Set<Nodo> visitados = new HashSet<>();
    //     while (true) {
    //         System.out.println("Profundidad: " + profundidad_final);
            
    //         cola_actual.add(inicial);

    //         for (int profundad_actual = 0; profundad_actual < profundidad_final; profundad_actual++) {
    //             int tam = cola_actual.size();
    //             for (int i = 0; i < tam; i++) {
    //                 Nodo current = cola_actual.remove();
    //                 for(Nodo hijo : current.get_hijos()){
    //                     if(!visitados.contains(hijo)){
    //                         cola_actual.add(hijo);
    //                         visitados.add(hijo);
    //                     }
    //                 }

    //             }
    //         }

    //         if (cola_actual.isEmpty()) {
    //             return;
    //         }

    //         for (Nodo nodo : cola_actual) {
    //             System.out.println(nodo.get_nombre());
    //             if (remainingObjetivos.contains(nodo)) {
    //                 System.out.print(nodo.get_nombre());
    //                 remainingObjetivos.remove(nodo);
    //             }
    //             if (remainingObjetivos.isEmpty()) {
    //                 return;
    //             }
    //         }
            
    //         cola_actual.clear();
    //         visitados.clear();
    //         profundidad_final++;
    //     }
    // }
    
    public void busqueda_profundidad_iterativa(Nodo inicial, List<Nodo> objetivos) {
        int profundidad_final = 0;
        int profundidad_actual = 0;
        Set<Nodo> remainingObjetivos = new HashSet<>(objetivos);
        Stack<Nodo> pila_actual = new Stack<Nodo>();
        Set<Nodo> visitados = new HashSet<>();
        while(true) {
            System.out.println("Iteracion " + profundidad_final);
            pila_actual.add(inicial);
            profundidad_actual = 0;
            while(profundidad_actual < profundidad_final) { //mientras sea menor saco los hijos
                int tam = 0;
                System.out.println(pila_actual.peek().get_nombre());
                for(Nodo hijo: pila_actual.pop().get_hijos()){
                    if(!pila_actual.contains(hijo)&& !visitados.contains(hijo)){
                        pila_actual.push(hijo);
                        visitados.add(hijo);
                        tam ++;
                    }
                }
                profundidad_actual ++;
                if(profundidad_actual == profundidad_final){
                    for(int i = tam; i>0 ; i--){
                        System.out.println(pila_actual.peek().get_nombre());
                        remainingObjetivos.remove(pila_actual.pop());
                        if(remainingObjetivos.isEmpty()){
                            return;
                        }
                    }
                    profundidad_actual --; //me regreso una profundidad antes para ver los otros hijos
                }
                if(pila_actual.isEmpty()){
                    break;
                }
            }
            profundidad_final ++;
            visitados.clear();
            pila_actual.clear();
        }
    }


    /////////////////////////////////////////////
    //BUSQUEDA POR COSTO UNIFORME////////////////
    public void busqueda_costo_uniforme(Nodo inicial, Nodo objetivo){
        ArrayList<Pair<Nodo,Integer> >colaNodos_peso = new ArrayList<>();
        ArrayList<Nodo> visitados = new ArrayList<>();
        Pair<Nodo,Integer> par = Pair.with(inicial,0);
        colaNodos_peso.add(par);
        while(!colaNodos_peso.isEmpty()){
            Collections.sort(colaNodos_peso, (o1, o2) -> Integer.compare(o1.getValue1(), o2.getValue1()));

            System.out.println("Iteracion");
            for(int i = 0; i < colaNodos_peso.size(); i++){
                System.out.print(colaNodos_peso.get(i).getValue0().get_nombre() + " ");
                System.out.println(colaNodos_peso.get(i).getValue1() + " ");
            }
            //imprimir cola
            
            if(colaNodos_peso.get(0).getValue0().equals(objetivo)){
                System.out.println("Nodo encontrado");
                System.out.println(colaNodos_peso.get(0).getValue0().get_nombre());
                System.out.println("Costo: " + colaNodos_peso.get(0).getValue1());
                break;
            }
            //si no es entonces vemos los hijos
        
            for(Arista arista : colaNodos_peso.get(0).getValue0().get_aristas()){
            
                if(!visitados.contains(arista.get_destino())){
                    Pair<Nodo,Integer> par2 = Pair.with(arista.get_destino(),colaNodos_peso.get(0).getValue1() + arista.get_peso());
                    colaNodos_peso.add(par2);
                    if(!arista.get_destino().equals(objetivo)){ //para evitar agregar el final a los visitados y que vea el resto
                        visitados.add(arista.get_destino());
                    }
                }
            }
            colaNodos_peso.remove(0);
        }
    }

    //METODO DE LA GRADIENTE///
  
    public void busqueda_del_gradiente(Nodo inicio,Nodo objetivo){
        
        Nodo actual = inicio;
        ArrayList<Nodo> camino = new ArrayList<>();
        camino.add(inicio);
        while(!actual.equals(objetivo)){
            ArrayList<Nodo> auxlist = new ArrayList<>(actual.get_hijos());
            Collections.sort(auxlist, (o1, o2) -> Integer.compare(o1.get_valor(), o2.get_valor()));
            if(auxlist.get(0).get_valor() <= actual.get_valor()){
                actual = auxlist.get(0);
                camino.add(actual);
            }
            if(auxlist.get(0).equals(objetivo)){
                break;
            }
        }

        for(Nodo nodo : camino){
            System.out.println(nodo.get_nombre());
        }
    }

    //METODO PRIMERO EL MEJOR

    public void busqueda_primero_el_mejor(Nodo inicio, Nodo objetivo){
        ArrayList<Nodo> cola_prioridad = new ArrayList<>();
        cola_prioridad.add(inicio);

        while(!cola_prioridad.isEmpty()){
            Nodo m = cola_prioridad.remove(0);
            System.out.println(m.get_nombre());
            if(m.equals(objetivo)){
                break;
            }
            cola_prioridad.addAll(m.get_hijos());
            Collections.sort(cola_prioridad,(o1,o2)-> Integer.compare(o1.get_valor(), o2.get_valor()));
        }
    }

    //METODO A*

    public void busqueda_A_estrella(Nodo inicio, Nodo objetivo){
        ArrayList<Nodo> cola = new ArrayList<>();
        ArrayList<Nodo> finished_list = new ArrayList<>();
        inicio.set_G_valor(0);
        cola.add(inicio);
    
        while(!cola.isEmpty()){
            
            Nodo actual = cola.remove(0);

            if(actual.equals(objetivo)){ //encontro el nodo objetivo
                finished_list.add(actual);
                System.out.println("Encontro objetivo");
                break;
            }
            

            for(Arista arista_actual : actual.get_aristas()){  //saco las aristas del nodo actual
                
                if(finished_list.contains(arista_actual.get_destino())){
                    continue;
                }
                Integer valor_G = arista_actual.get_peso() + actual.get_G_valor(); //costo al nodo
                if(cola.contains(arista_actual.get_destino())){
                    if(valor_G < arista_actual.get_destino().get_G_valor()){
                        arista_actual.get_destino().set_G_valor(valor_G);
                        continue;
                    }
                }
                arista_actual.get_destino().set_G_valor(valor_G);
                cola.add(arista_actual.get_destino());
            }
            Collections.sort(cola,(o1,o2)-> Integer.compare(o1.get_F_valor(), o2.get_F_valor()));
            finished_list.add(actual);
        }

        System.out.println("Camino: ");
        for(Nodo nodo : finished_list){
            System.out.println(nodo.get_nombre());
        }


    }

    
}


