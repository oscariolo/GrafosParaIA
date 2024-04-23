package com.example;
import java.util.ArrayList;

public class Nodo{
    private String nombre;
    private int valor;
    private byte id;
    private int G_valor;
    private ArrayList<Arista> aristas;

    public Nodo(byte id, String nombre, int valor){
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        aristas = new ArrayList<>();
    }

    public Nodo(String nombre, int valor){
        this.nombre = nombre;
        this.valor = valor;
        aristas = new ArrayList<>();
    }

    public void set_G_valor(int G_valor){
        this.G_valor = G_valor;
    }
    public int get_G_valor(){
        return this.G_valor;
    }

    public int get_F_valor(){
        return this.G_valor + this.valor;
    }

    public void crear_arista(Nodo destino, int peso){
        Arista arista = new Arista(this, destino, peso);
        aristas.add(arista); // Agregar arista al nodo
    }

    public String get_nombre(){
        return this.nombre;
    }

    public int get_valor(){
        return this.valor;
    }

    public byte get_id(){
        return this.id;
    }

    public void set_nombre(String nombre){
        this.nombre = nombre;
    }

    public void set_valor(int valor){
        this.valor = valor;
    }

    public void set_id(byte id){
        this.id = id;
    }

    public ArrayList<Nodo> get_hijos(){
        ArrayList<Nodo> hijos = new ArrayList<Nodo>();
        for (Arista arista : this.aristas){
            hijos.add(arista.get_destino());
        }
        return hijos;
        ////
        
    }

    public ArrayList<Arista> get_aristas(){
        return this.aristas;
    }

    public String toString(){
        return this.nombre;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (this.nombre != other.nombre){
            return false;
        }
        return true;
    }

}