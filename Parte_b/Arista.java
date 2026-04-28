//Para que funcionen las listas tiene que ser comparable
public class Arista implements Comparable<Arista> {
    String etiqueta;
    String destino;

    public Arista(String etiqueta, String destino) {
        this.etiqueta = etiqueta;
        this.destino = destino;
    }

    @Override
    public int compareTo(Arista otra) {
        return this.destino.compareTo(otra.destino);
    }

    @Override
    public String toString() {
        return "[" + etiqueta + "]--> " + destino;
    }
}