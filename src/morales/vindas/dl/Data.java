package morales.vindas.dl;

import morales.vindas.bl.entities.Grafo;

public class Data {
    private Grafo g;

    public Data() {
        g = new Grafo(true);
    }

    public String agregarVertice(String pVertice, double pLat, double pLon) {
        return g.agregarVertice(pVertice, pLat, pLon);
    }

    public String agregarArco(String pOrigen, String pDestino) {
        return g.agregarArco(pOrigen, pDestino);
    }

    public String mostrarUbicaciones() {
        return g.mostrarUbicaciones();
    }

    public String mostrarUbicacion(String pUbicacion) {
        return g.mostrarUbicacion(pUbicacion);
    }

    public String mostrarRecorrido(String pOrigen, String pDestino, int pRecorrido) {
        return g.mostrarRecorrido(pOrigen, pDestino, pRecorrido);
    }


}