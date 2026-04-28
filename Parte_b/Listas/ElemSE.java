package Listas;

public class ElemSE<T> {
    T dato; //El tipo de dato que sea
    ElemSE<T> siguiente; //Puntero que apunta al siguiente elemento
    //Constructor
    public ElemSE(T dato){
        this.dato=dato;
        this.siguiente=null;//Al crear un nodo o elemento este al principio no apunta a nada
    }
}
