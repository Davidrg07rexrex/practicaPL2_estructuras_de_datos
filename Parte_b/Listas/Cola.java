package Listas;

public class Cola<T extends Comparable<T>> {

    protected ElemSE<T> primero;//cabeza
    protected ElemSE<T> ultimo;//final
    protected int tamaño=0;

    public Cola() {
        this.primero=null;
        this.ultimo=null;
        this.tamaño=0;
    }

    public void encolar(T dato) {
        ElemSE<T> nuevo=new ElemSE<>(dato);
        if (primero==null) {
            primero=ultimo=nuevo;
        } else {
            ultimo.siguiente=nuevo;
            ultimo=nuevo;
        }
        tamaño++;
    }

    public T desencolar() {
        if (primero==null) return null;
        T valor=primero.dato;
        primero=primero.siguiente;
        if (primero==null) {
            ultimo=null;
        }
        tamaño--;
        return valor;
    }
    public T frente() {//Obtener el primero
        if (estaVacia()) return null;
        return primero.dato;
    }

    public boolean estaVacia() {
        return primero==null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void clear() {
        primero=null;
        ultimo=null;
        tamaño=0;
    }
    public Cola<T> invertir(Cola<T> original){
        Cola<T> invertida=new Cola<>();
        Pila<T> aux=new Pila<>();
        Cola<T> copia=new Cola<>();
        while(!original.estaVacia()){
            T dato=original.desencolar();
            aux.apilar(dato);
            copia.encolar(dato);
        }
        while(!copia.estaVacia()){
            original.encolar(copia.desencolar());
        }
        while(!aux.estaVacia()){
            invertida.encolar(aux.desapilar());
        }
        return invertida;
    }
}