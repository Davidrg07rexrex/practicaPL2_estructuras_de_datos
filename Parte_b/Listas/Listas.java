package Listas;

public interface Listas<T extends Comparable<T>> {
    //Todo lo que tienen que hacer nuestras estructuras de datos como minimo
    void add(T dato);
    T get(T dato);
    T del(T dato);
    boolean estaVacia();
    int getTamaño();

    // extras
    void addFirst(T dato);
    int numApariciones(T dato);
    boolean contains(T dato);
    T getDatoEn(int pos);
    void clear();
    void imprimir();
}
