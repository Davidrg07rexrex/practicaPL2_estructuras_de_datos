import com.google.gson.Gson;
import java.io.FileReader;
import java.util.*;
import Listas.*;

public class GestorGrafo {
    private Map<String, ListaSimplementeEnlazada<Arista>> grafo = new HashMap<>();

    public void cargarDesdeJSON(String ruta) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ruta)) {//Por si hay fallos
            //1.Convertir JSON a objetos Java
            GrafoDatos datos = gson.fromJson(reader, GrafoDatos.class);

            //2.Pasar de la lista a la estructura de grafo
            for (Tripletas t : datos.tripletas) {
                //si el sujeto no existe en el mapa, lo creamos
                grafo.putIfAbsent(t.s, new ListaSimplementeEnlazada<Arista>());
                //Añadimos la arista
                grafo.get(t.s).add(new Arista(t.p, t.o));
                //nos aseguramos de que tambien esta el objeto
                grafo.putIfAbsent(t.o, new ListaSimplementeEnlazada<Arista>());
            }
            System.out.println("Grafo cargado con exito.");

        } catch (Exception e) {//Por si pasa algo
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
    public void mostrarGrafo() {
        for (String sujeto : grafo.keySet()) {
            System.out.println("Nodo: " + sujeto);
            ListaSimplementeEnlazada<Arista> aristas = grafo.get(sujeto);
            for (int i = 0; i < aristas.getTamaño(); i++) {
                System.out.println("   " + aristas.getDatoEn(i));
            }
        }
    }
    //Metodo para obtener el minimo camino de A a B
    public ListaSimplementeEnlazada<String> obtenerCaminoMinimo(String inicio, String fin) {
        Cola<String> cola = new Cola<String>();            //cola para el orden de visita FIFO
        Map<String, String> rastro = new HashMap<>();       //guarda para reconstruir la ruta
        Set<String> visitados = new HashSet<>();            //registro de nodos ya procesados

        cola.encolar(inicio);
        visitados.add(inicio);          //Visitamos ya el origen
        rastro.put(inicio, null);       //el origen no tiene antecesor

        while (!cola.estaVacia()) {
            String actual = cola.desencolar();        //sacamos el siguiente nodo

            if (actual.equals(fin)) {                       //si llegamos al destino
                return reconstruirRuta(rastro, fin);     //devuelve la lista del camino
            }

            if (grafo.containsKey(actual)) {           //si el nodo tiene conexiones
                ListaSimplementeEnlazada<Arista> conexiones = grafo.get(actual);
                for (int i = 0; i < conexiones.getTamaño(); i++) {//recorre cada flecha que sale del nodo
                    String vecino = conexiones.getDatoEn(i).destino;       //obtiene hacia donde apunta

                    if (!visitados.contains(vecino)) {   //si el vecino no ha sido visitado aun
                        visitados.add(vecino);             //le visitamos
                        rastro.put(vecino, actual);       //guarda de que nodo veniamos
                        cola.encolar(vecino);                   //lo pone en espera para revisar sus hijos
                    }
                }
            }
        }
        return null;//si termina y no hay ruta devuelve nulo
    }
    //Lo necesitamos para que no desaparezca
    private ListaSimplementeEnlazada<String> reconstruirRuta(Map<String, String> rastro, String destino) {
        ListaSimplementeEnlazada<String> camino = new ListaSimplementeEnlazada<String>();
        String paso = destino;

        while (paso != null) {
            camino.addFirst(paso);
            paso = rastro.get(paso);
        }
        return camino;//devuelve el camino ordenado
    }

    //Comprueba si el grafo es disjunto
    public boolean esDisjunto() {
        if (grafo.isEmpty()) return false;//si no hay datos, no es disjunto

        String inicio = grafo.keySet().iterator().next();//el primer nodo que encontremos
        Set<String> visitados = new HashSet<>();
        Cola<String> cola = new Cola<String>();//cola para explorar el grafo

        cola.encolar(inicio);
        visitados.add(inicio);//el primero ya cuenta como visitado

        while (!cola.estaVacia()) {
            String actual = cola.desencolar();//sacamos uno para ver sus vecinos
            if (grafo.containsKey(actual)) {
                ListaSimplementeEnlazada<Arista> lista = grafo.get(actual);
                for (int i = 0; i < lista.getTamaño(); i++) {//recorremos sus flechas
                    String destino = lista.getDatoEn(i).destino;
                    if (!visitados.contains(destino)) {//si el vecino no ha sido visitado lo marcamos como alcazado
                        visitados.add(destino);
                        cola.encolar(destino);
                    }
                }
            }
        }

        int nodosTotales = grafo.size();//numero total de nodos en el HashMap
        int nodosVisitados = visitados.size();//numero de nodos a los que llegamos

        //Si a los que llegamos son menos que los que hay,es que es disjunto
        return nodosVisitados < nodosTotales;
    }


    //Metodo para resolver la pregunta de Einstein
    public String consultarFisicoMismaCiudad(String nombreFamoso) {
        String ciudadBusqueda = null;//guardara la ciudad de origen

        if (grafo.containsKey(nombreFamoso)) {//buscamos datos del famoso
            ListaSimplementeEnlazada<Arista> relaciones = grafo.get(nombreFamoso);
            for (int i = 0; i < relaciones.getTamaño(); i++) {
                if (relaciones.getDatoEn(i).etiqueta.equals("nace_en")) {
                    ciudadBusqueda = relaciones.getDatoEn(i).destino;//guardamos la ciudad
                    break;//salimos
                }
            }
        }

        if (ciudadBusqueda == null) return null;//si no tiene ciudad, salimos

        for (String sujeto : grafo.keySet()) {//recorremos todos los sujetos
            if (sujeto.equals(nombreFamoso)) continue;//no queremos volver a Einstein

            boolean mismaCiudad = false;//filtro ciudad
            boolean esFisico = false;//filtro profesion

            ListaSimplementeEnlazada<Arista> relSujeto = grafo.get(sujeto);
            for (int j = 0; j < relSujeto.getTamaño(); j++) {
                Arista r = relSujeto.getDatoEn(j);
                if (r.etiqueta.equals("nace_en") && r.destino.equals(ciudadBusqueda)) mismaCiudad = true;
                if (r.etiqueta.equals("profesion") && r.destino.equals("fisico")) esFisico = true;
            }

            if (mismaCiudad && esFisico) return sujeto;//si cumple ambos, es el bueno
        }
        return null;//si no hay nadie
    }
}
