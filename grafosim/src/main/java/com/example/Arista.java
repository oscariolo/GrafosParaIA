package com.example;

public class Arista{
    private Nodo origen;
    private Nodo destino;
    private int peso;
    public Arista(Nodo origen, Nodo destino, int peso){
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Nodo get_destino(){
        return this.destino;
    }

    public Nodo get_origen(){
        return this.origen;
    }

    public int get_peso(){
        return this.peso;
    }

    public void set_peso(int peso){
        this.peso = peso;
    }
    

}