import com.google.gson.Gson;
import java.io.FileReader;
import java.util.*;

public class GestorGrafo {
    private Map<String, List<Arista>> grafo = new HashMap<>();

    public void cargarDesdeJSON(String ruta) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ruta)) {//Por si hay fallos
            //1.Convertir JSON a objetos Java
            GrafoDatos datos = gson.fromJson(reader, GrafoDatos.class);

            //2.Pasar de la lista a la estructura de grafo
            for (Tripletas t : datos.tripletas) {
                //si el sujeto no existe en el mapa, lo creamos
                grafo.putIfAbsent(t.s, new ArrayList<>());
                //Añadimos la arista
                grafo.get(t.s).add(new Arista(t.p, t.o));
                //nos aseguramos de que tambien esta el objeto
                grafo.putIfAbsent(t.o, new ArrayList<>());
            }
            System.out.println("Grafo cargado con exito.");

        } catch (Exception e) {//Por si pasa algo
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
    public void mostrarGrafo() {
        for (String sujeto : grafo.keySet()) {
            System.out.println("Nodo: " + sujeto);
            for (Arista a : grafo.get(sujeto)) {
                System.out.println("   " + a);
            }
        }
    }
}