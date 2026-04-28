package Listas;

public class ElemDE<T> {
    T dato;//El dato que sea
    ElemDE<T> siguiente;//Puntero que apunta a el siguiente elemento
    ElemDE<T> anterior;//Puntero que apunta al anterior

    public ElemDE(T dato){
        this.dato=dato;
    }
}
