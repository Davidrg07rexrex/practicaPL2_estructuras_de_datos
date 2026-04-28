package Listas;

public class ListaSimplementeEnlazada<T extends Comparable<T>> implements Listas<T>{

    protected ElemSE<T> primero;
    protected int tamaño=0;//Inicialmente el tamaño es 0


    @Override
    public void add(T dato){
        //Objetivo:Insertar el dato proporcionado al final
        ElemSE<T> nuevo=new ElemSE<>(dato);//Creamos el nuevo nodo

        if(estaVacia())//Si esta vacia
            primero=nuevo;

        else{
            ElemSE<T> auxiliar=primero;//Recorreremos la lista

            while(auxiliar.siguiente!=null)//Ir hasta el ultimo
                auxiliar = auxiliar.siguiente;

            auxiliar.siguiente=nuevo;//El ultimo apunta al nuevo
        }
        tamaño++;//Incrementa el tamaño
    }


    @Override
    public T get(T dato){
        //Objetivo:Obtener el dato pedido
        ElemSE<T> auxiliar=primero;

        while(auxiliar!=null){
            if(auxiliar.dato.compareTo(dato) == 0)
                return auxiliar.dato;
            auxiliar=auxiliar.siguiente;//Pasar al siguiente
        }
        return null;//No encontrado
    }


    @Override
    public T del(T dato){
        //Objetivo:Borrar y devolver el dato requerido
        ElemSE<T> auxiliar=primero;
        ElemSE<T> anterior=null;
        while(auxiliar!=null){
            if(auxiliar.dato.compareTo(dato) == 0){//Igualues(0)
                if(anterior==null)//Si es el primero
                    primero=auxiliar.siguiente;
                else//Si esta en medio o al final
                    anterior.siguiente = auxiliar.siguiente;
                tamaño--;
                return auxiliar.dato;
            }
            anterior=auxiliar;//Guardamos el de antes para usarlo en la siguiente pasada
            auxiliar=auxiliar.siguiente;//Avanzamos
        }
        return null;//No lo hemos encontrado
    }


    @Override
    public boolean estaVacia(){
        return primero==null;//Si no hay primero, esta vacia
    }


    @Override
    public int getTamaño(){
        return tamaño;//Devuelve el tamaño
    }

    //extra
    @Override
    public int numApariciones(T dato){
        //Devuelve el número de veces que aparece cierto dato
        ElemSE<T> aux=primero;
        int contador=0;
        while(aux!=null){//Recorre la lista
            if(aux.dato.compareTo(dato)==0)
                contador++;//Si aparece el dato aumenta el contador
            aux=aux.siguiente;
        }
        return contador;
    }
    @Override
    public void addFirst(T dato){
        //Objetivo:Insertar el dato al prinipio
        ElemSE<T> nuevo=new ElemSE<>(dato);

        nuevo.siguiente=primero;//El siguiente del nuevo es el primero
        primero=nuevo;//El primero pasa a ser el nuevo
        tamaño++;
    }
    @Override
    public boolean contains(T dato){
        //Objetivo:Comprobar si existe un dato
        ElemSE<T> auxiliar=primero;
        while(auxiliar!=null){
            if(auxiliar.dato.compareTo(dato)==0)
                return true;
            auxiliar=auxiliar.siguiente;
        }
        return false;//No encontrado
    }
    @Override
    public T getDatoEn(int pos){
        //Objetivo:Obtener el dato en una posicion
        if(pos<0||pos>=tamaño)
            return null;
        ElemSE<T> auxiliar=primero;
        int contador=0;
        while(auxiliar!=null){
            if(contador==pos)
                return auxiliar.dato;//Si el contador coincide, ya tenemos el elem en la pos
            auxiliar = auxiliar.siguiente;
            contador++;
        }
        return null;
    }
    @Override
    public void clear(){
        //Objetivo:Vaciar la lista
        primero=null;
        tamaño=0;
    }
    @Override
    public void imprimir(){
        //Objetivo:Mostrar los elementos de la lista
        ElemSE<T> auxiliar=primero;
        while(auxiliar!=null){
            System.out.println(auxiliar.dato);//Vamos imprimiendo los datos
            auxiliar=auxiliar.siguiente;
        }
    }
}