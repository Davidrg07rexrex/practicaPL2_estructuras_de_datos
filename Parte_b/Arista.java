public class Arista {//para guardar hacia donde vamos y que relacion nos lleva
    String predicado;
    String destino;

    public Arista(String p, String d) {
        this.predicado = p;
        this.destino = d;
    }
    @Override
    public String toString() {
        return " [" + predicado + "]--> " + destino;
    }
}