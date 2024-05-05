package com.example;
import java.util.ArrayList;
import java.util.Collection;
// import java.util.Collection;


public class Resultado {
    ArrayList<ArrayList<Nodo>> historial_cola;
    ArrayList<Nodo> historial_extraccion;

    public Resultado(){
        historial_cola = new ArrayList<>();
        historial_extraccion = new ArrayList<>();
    }

    public void copy_queue(Collection<Nodo> cola){
        ArrayList<Nodo> copy = new ArrayList<>(cola);
        this.historial_cola.add(copy);
    }

    public void copy_extraction(Nodo extraction){
        historial_extraccion.add(extraction);
    }

}
