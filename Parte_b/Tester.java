public class Tester {
    public static void main(String args[]){
        GestorGrafo grafo=new GestorGrafo();
        String nombreArchivo= "datos.json";
        grafo.cargarDesdeJSON(nombreArchivo);
        grafo.mostrarGrafo();

        GestorGrafo g1 = new GestorGrafo();
        //Grafo Conexo
        g1.cargarDesdeJSON("grafo_conexo.json");
        System.out.println("¿Es disjunto el conexo?: " + g1.esDisjunto());//Debería salir falso

        //Grafo Disjunto
        GestorGrafo g2 = new GestorGrafo();//Creamos otro gestor para limpiar datos
        g2.cargarDesdeJSON("grafo_disjunto.json");
        System.out.println("¿Es disjunto el disjunto?: " + g2.esDisjunto());//Debería salir true
    }
    }

