package Listas;

public class ListaDoblementeEnlazada<T extends Comparable<T>> implements Listas<T>{
    protected ElemDE<T> primero, ultimo;
    protected int tamaño=0;//Inicialmente el tamaño es 0

    @Override
    public void add(T dato){
        //Objetivo:insertar el dato proporcionado al final
        ElemDE<T> nuevo=new ElemDE<>(dato);//Creamos un nuevo nodo con el dato
        //Si esta vacia,el nuevo es el primero y el ultimo(solo hay uno)
        if(estaVacia()) primero=ultimo=nuevo;
            //Si no, lo ponemos al final,ajustando los punteros
        else{
            ultimo.siguiente=nuevo;//El ultimo apunta al nuevo
            nuevo.anterior=ultimo;//El anterior del nuevo(que esta al final), es el que antes era ultimo
            ultimo=nuevo;//Como lo metemos al final, el ultimo es el nuevo
        }
        tamaño++;//Incrementa en 1 el tamaño de la lista
    }
    @Override
    public T get(T dato){
        //Objetivo:Obtener el dato pedido
        ElemDE<T> auxiliar=primero;//Vamos a usarlo para ir recorriendo la lista
        while(auxiliar!=null) {
            if (auxiliar.dato.compareTo(dato)==0) {//es decir, son iguales(compareTo retrona 0
                return auxiliar.dato;
            }
            auxiliar=auxiliar.siguiente;//Pasamos al siguiente
        }
        return null;//Si no se ha encontrado ninguno igual
    }

    @Override
    public T del(T dato){
        //Objetivo:Borrar y devolver el dato requerido, ajustando los punteros correspondientes
        ElemDE<T> auxiliar=primero;//Igual, recorreremos la lista
        while (auxiliar!=null) {
            if (auxiliar.dato.compareTo(dato)==0) {//Hemos encontrado el que queriamos
                //3 casos, es el primero, ultimo o esta en medio
                if(auxiliar==primero){
                    primero=primero.siguiente;//Llamar primero al siguiente, y "conectarlo" a null
                    if(primero!=null)//Por si acaso es de solo 1 elemento
                        primero.anterior=null;
                    else
                        ultimo=null;
                } else if (auxiliar==ultimo) {
                    ultimo=ultimo.anterior; //Llamar ultimo al anterior y "conectarlo" a null
                    if(ultimo!=null)//Por si acaso tiene un elemento solo
                        ultimo.siguiente=null;
                    else
                        primero=null;
                } else{
                    auxiliar.anterior.siguiente=auxiliar.siguiente;//El siguiente del anterior es el que estaba delante del que eliminamos
                    auxiliar.siguiente.anterior=auxiliar.anterior;//El anterior del siguiente es el que estaba antes del que eliminamos
                }
                tamaño--;//Hemos quitado uno
                return auxiliar.dato;
            }
            auxiliar=auxiliar.siguiente;//Ir al siguiente
        }
        return null;//No lo hemos encontrado

    }
    @Override public boolean estaVacia() { return primero==null; }//Si el primero no existe, da True
    @Override public int getTamaño() { return tamaño; }//Nos da el tamaño, que hemos ido modificando antes

    //Metodos extras
    @Override
    public void addFirst(T dato){
        //Objetivo:Insertar el dato al principio de la lista

        ElemDE<T> nuevo=new ElemDE<>(dato);//Creamos el nuevo nodo

        if(estaVacia())//Si esta vacia, es pel rimero y el ultimo
            primero=ultimo=nuevo;

        else{
            nuevo.siguiente=primero;//El siguiente del nuevo es el primero actual
            primero.anterior=nuevo;//El anterior del primero es el nuevo
            primero=nuevo;//Ahora el primero es el nuevo
        }

        tamaño++;//Aumentamos el tamaño
    }
    @Override
    public int numApariciones(T dato){
        //Devuelve el número de veces que aparece cierto dato
        ElemDE<T> aux=primero;
        int contador=0;
        while(aux!=null){//Recorre la lista
            if(aux.dato.compareTo(dato)==0)
                contador++;//Si aparece el dato aumenta el contador
            aux=aux.siguiente;
        }
        return contador;
    }
    @Override
    public boolean contains(T dato){
        //Objetivo:comprobar si existe el dato en la lista

        ElemDE<T> auxiliar=primero;//recorrerremos la lista

        while(auxiliar!=null){
            if(auxiliar.dato.compareTo(dato)==0)
                return true;

            auxiliar=auxiliar.siguiente;//siguiente
        }

        return false;//No lo hemos encontrado
    }
    @Override
    public T getDatoEn(int pos){
        //Objetivo:Obtener el dato que esta en la posicion

        if(pos<0 || pos>=tamaño)//Comprobar si vale
            return null;

        ElemDE<T> auxiliar=primero;
        int contador=0;//Lo usaremos para saber cuando hemos llegado a la posicion

        while(auxiliar!=null){

            if(contador==pos)//Hemos llegado a la posicion
                return auxiliar.dato;//, como voy a ir incrementando el auxiliar a la vez, cuando se llegue, retornamos ese auxiliar

            auxiliar=auxiliar.siguiente;//Ir al siguiente
            contador++;
        }
        return null;//Si no se encuentra
    }
    @Override
    public void clear(){
        //Objetivo:Vaciar la lista
        primero=null;
        ultimo=null;
        tamaño=0;
    }
    @Override
    public void imprimir(){
        //Objetivo:Mostrar los elementos de la lista
        ElemDE<T> auxiliar=primero;

        while(auxiliar!=null){
            System.out.println(auxiliar.dato);//Vamos imprimiendo 1 a 1
            auxiliar=auxiliar.siguiente;//Ir al siguiente
        }
    }
}