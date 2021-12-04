package morales.vindas.bl.logic;

import morales.vindas.dl.Data;

/**
 * Clase que se encarga de gestar la comunicación entre el controlador y la capa de datos
 * @author Carlos Morales, Milton Vindas - Estructuras de Datos 2 - III-2021
 */
public class Gestor {

    private final Data db;

    public Gestor() {
        this.db = new Data();
    }

    public String agregarVertice(String pVertice, double pLat, double pLon) {
        return db.agregarVertice(pVertice, pLat, pLon);
    }

    public String agregarArco(String pOrigen, String pDestino) {
        return db.agregarArco(pOrigen, pDestino);
    }

    public String mostrarUbicaciones() {
        return db.mostrarUbicaciones();
    }

    public String mostrarUbicacion(String pUbicacion) {
        return db.mostrarUbicacion(pUbicacion);
    }

    public String mostrarRecorrido(String pOrigen, String pDestino, int pRecorrido) {
        return db.mostrarRecorrido(pOrigen, pDestino, pRecorrido);
    }

}
