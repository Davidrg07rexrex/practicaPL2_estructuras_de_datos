package Listas;

public class Pila<T extends Comparable<T>> {
    protected ElemSE<T> tope;//cima
    protected int tamaño=0;

    //Constructor
    public Pila() {
        this.tope=null;
        this.tamaño=0;
    }

    //Metodos
    public void apilar(T dato) {//O push
        //Objetivo:poner un elemento arriba
        ElemSE<T> nuevo=new ElemSE<>(dato);
        nuevo.siguiente=tope;// el siguiente del nuevo es el viejo tope
        tope=nuevo;// ahora el nuevo es la cima
        tamaño++;//tamaño+1
    }

    public T desapilar() {//O pop
        //Objetivo:Quitar el primero y devolverlo(LIFO)
        if (tope==null) return null; //nada que desapilar

        T valor=tope.dato;//guardamos dato para devolver
        tope=tope.siguiente;//movemos el tope al siguiente
        tamaño--;//tamaño -1

        return valor;
    }
    public T elemArriba() {//Nos da el de arriba
        if (estaVacia()) return null;
        return tope.dato;
    }

    public boolean estaVacia() {
        return tope==null; //True si no es null
    }

    public int getTamaño() {
        return tamaño;//Da el tamaño, num de elementos
    }

    public void clear() {
        //Objetivo:Vaciar la pila
        tope=null;
        tamaño=0;
    }

    public Pila<T> invertirPila(Pila<T> original) {
        Pila<T> invertida=new Pila<>();
        Pila<T> copia=new Pila<>();
        Cola<T> aux=new Cola<>();

        while (!original.estaVacia()) {
            T dato=original.desapilar();
            aux.encolar(dato);
            copia.apilar(dato);
        }
        while (!copia.estaVacia()) {
            original.apilar(copia.desapilar());
        }
        while (!aux.estaVacia()) {
            invertida.apilar(aux.desencolar());
        }
        return invertida;
    }
}