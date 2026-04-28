public class Tester {
    public static void main(String args[]){
        GestorGrafo grafo=new GestorGrafo();
        String nombreArchivo= "datos.json";
        grafo.cargarDesdeJSON(nombreArchivo);
        grafo.mostrarGrafo();
    }
}
