package morales.vindas.bl.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static morales.vindas.bl.helpers.Algorithms.haversine;

/**
 * Clase que representa un grafo de lista de adyacencia
 * @author Carlos Morales, Milton Vindas - Estructuras de Datos 2 - III-2021
 */
public class Grafo {

    /**
     * El mapa de adyacencia del grafo
     */
    private Map<NodoVertice, LinkedList<NodoArco>> mapAdy;

    /**
     * Booleano que determina si el grafo es dirigido o no.
     */
    private boolean dirigido;

    public Grafo(boolean pDirigido) {
        this.mapAdy = new HashMap<>();
        this.dirigido = pDirigido;
    }

    /**
     * Método público que muestra el grafo al usuario
     * @return Retorna el String con el contenido del grafo
     */
    public String mostrarUbicaciones() {
        return mostrarUbicacionesTodas();
    }

    /**
     * Método que retorna un String con los datos de la ubicación consultada
     * @param pUbicacion El nombre del vértice
     * @return Retorna el String con la información
     */
    public String mostrarUbicacion(String pUbicacion) {
        return mostrarUbicacionUnica(pUbicacion);
    }


    /**
     * Método que retorna un string con el tipo de recorrido seleccionado
     * @param pOrigen El nombre del vértice de origen
     * @param pDestino El nombre del vértice de destino
     * @param pRecorrido El tipo de recorrido: 0 es mínimo, 1 es máximo
     * @return Retorna el String con el recorrido
     */
    public String mostrarRecorrido(String pOrigen, String pDestino, int pRecorrido) {
        return dijkstra(pOrigen, pDestino, pRecorrido);
    }

    /**
     * Método que agrega un vértice al grado
     * @param pVertice El String con el nombre del vértice
     * @param pLat La latitud de la ubicación
     * @param pLon La longitud de la ubicación
     * @return Retorna el String con el resultado de la operación
     */
    public String agregarVertice(String pVertice, double pLat, double pLon) {
        return agregarVerticeGrafo(pVertice, pLat, pLon);
    }

    /**
     * Método público que agrega un arco al grafo
     * @param pOrigen La etiqueta del vértice origen
     * @param pDestino La etiqueta del vértice destino
     * @return Retorna el String con el resultado de la operación
     */
    public String agregarArco(String pOrigen, String pDestino) {
        return agregarArcoGrafo(pOrigen, pDestino);
    }


    /**
     * Método privado que agrega un vértice al grafo
     * @param pV La etiqueta del vértice que se va a agregar
     * @param pLatitud La latitud en un mapa del vértice
     * @param pLongitud La longitud en un mapa del vértice
     * @return Retorna el resultado de la operación de agregar vértice en un String
     */
    private String agregarVerticeGrafo (String pV, double pLatitud, double pLongitud) {

        NodoVertice nuevo = new NodoVertice(pV, pLatitud, pLongitud);

        if (!mapAdy.containsKey(nuevo)) {
            mapAdy.put(nuevo, null);
            return "El vértice " + pV + " ha sido agregado.";
        }
        else {
            return "Un vértice con esa etiqueta ya ha sido registrado.";
        }
    }



    /**
     * Método privado que agrega arcos al grafo
     * @param pOrigen La etiqueta del vértice que funciona como origen
     * @param pDestino La etiqueta del vértice que se designa como destino
     * @return Retorna el String con el resultado de la operación
     */
    private String agregarArcoGrafo (String pOrigen, String pDestino) {
        Optional<NodoVertice> origen = encontrarVertice(pOrigen);
        Optional<NodoVertice> destino = encontrarVertice(pDestino);
        double peso;

        if (origen.isPresent() && destino.isPresent()) {
            peso = haversine(origen.get().getX(), destino.get().getX(), origen.get().getY(), destino.get().getY());
            NodoArco nuevo = new NodoArco(destino.get(), peso);
            insertarArcoProceso(origen.get(), nuevo);

            if (!dirigido) {
                NodoArco inverso = new NodoArco(origen.get(), peso);
                insertarArcoProceso(destino.get(), inverso);
            }
            return "Arco "+ pOrigen + " -> " + pDestino +" registrado.";
        }

        else {
            return "No se encuentran vértices destino y/o origen registrados con los nombres indicados.";
        }
    }

    /**
     * Método que inserta un arco en el mapa de adyacencia
     * @param pOrigen El vértice origen al cual se le debe agregar el arco
     * @param pArco El arco por agregar
     */
    private void insertarArcoProceso (NodoVertice pOrigen, NodoArco pArco){
        LinkedList<NodoArco> tmp = mapAdy.get(pOrigen);

        if (tmp != null) {
            tmp.remove(pArco);
        } else {
            tmp = new LinkedList<>();
        }
        tmp.add(pArco);
        mapAdy.put(pOrigen, tmp);
    }

    /**
     * Método que devuelve una variable del tipo Optional que puede contener el vértice con la etiqueta ingresada
     * @param pV La etiqueta del vértice consultado
     * @return El Optional con el resultado de la consulta
     */
    private Optional<NodoVertice> encontrarVertice(String pV) {
        NodoVertice v = new NodoVertice(pV);

        return mapAdy
                .keySet()
                .stream()
                .filter(nodoArcos -> Objects.equals(nodoArcos, v))
                .findAny();
    }

    /**
     * Método que devuelve un mensaje con el contenido actual del grafo
     * @return El String que muestra el grafo al usuario
     */
    private String mostrarUbicacionesTodas() {
        StringBuilder mensaje = new StringBuilder("\n");
        mensaje.append("╔══════════════════════════════════════════════════════════════════════════╗\n");
        mensaje.append("              Ubicaciones del mapa cantonal de Costa Rica \n");
        mensaje.append("╚══════════════════════════════════════════════════════════════════════════╝\n");

        for (NodoVertice nodo : mapAdy.keySet()) {
            mensaje.append("* ").append(nodo.getLabel()).append(" * (Lat.").append(nodo.getX()).append(",Lon.").append(nodo.getY()).append(") Destinos: ");
            int destinos = 0;
            if (mapAdy.get(nodo) != null) {
                destinos = mapAdy.get(nodo).size();
            }
            mensaje.append(destinos).append(". Orígenes: ");

            int origenes = 0;

            for (NodoVertice origen : mapAdy.keySet()) {
                if (mapAdy.get(origen) != null) {
                    for (NodoArco destino : mapAdy.get(origen)) {
                        if (destino.getDestino().getLabel().equals(nodo.getLabel())) {
                            origenes += 1;
                            break;
                        }
                    }
                }
            }
            mensaje.append(origenes).append("\n");
        }

        return String.valueOf(mensaje);
    }

    /**
     * Método que devuelve un mensaje con la información de una ubicación registrada
     * @param pUbicacion La etiqueta del vértice consultado
     * @return El String que muestra la información de la ubicación
     */
    private String mostrarUbicacionUnica(String pUbicacion) {
        Optional<NodoVertice> ubicacion = encontrarVertice(pUbicacion);
        StringBuilder mensaje = new StringBuilder("\n");

        if (ubicacion.isPresent()) {

            mensaje.append("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
            mensaje.append("                                                        Ubicación: ").append(pUbicacion).append("\n");
            mensaje.append("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");
            mensaje.append("Coordenadas: ").append(ubicacion.get().getX()).append(", ").append(ubicacion.get().getY()).append("\n");


            mensaje.append("Destinos: ");
            if (mapAdy.get(ubicacion.get()) != null) {
                for (NodoArco arco : mapAdy.get(ubicacion.get())) {
                    mensaje.append(arco.getDestino().getLabel()).append("(").append(arco.getPeso()).append(" km)  ");
                }
            }
            else {
                mensaje.append("Ninguno");
            }

            mensaje.append("\nOrígenes: ");

            for (NodoVertice origen : mapAdy.keySet()) {
                if (mapAdy.get(origen) != null) {
                    for (NodoArco destino : mapAdy.get(origen)) {
                        if (destino.getDestino().getLabel().equals(ubicacion.get().getLabel())) {
                            mensaje.append(origen.getLabel()).append("(").append(destino.getPeso()).append(" km)  ");
                            break;
                        }
                    }
                }
            }
            mensaje.append("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        }

        else {
            mensaje.append("No existe una ubicación registrada con ese nombre.");
        }

        return String.valueOf(mensaje);
    }

    /**
     * Método que devuelve el camino máximo o camino mínimo desde el origen al destino
     * @param pOrigen Nombre de la ubicación origen
     * @param pDestino Nombre de la ubicación destino
     * @param pRecorrido El tipo de recorrido: 0 para mínimo, 1 para máximo
     * @return Retorna el String con el recorrido determinado
     */
    private String dijkstra(String pOrigen, String pDestino, int pRecorrido) {

        Optional<NodoVertice> isOrigen = encontrarVertice(pOrigen);
        Optional<NodoVertice> isDestino = encontrarVertice(pDestino);
        StringBuilder mensaje = new StringBuilder("\n");


        //Si existen el vértice origen y destino
        if (isOrigen.isPresent() && isDestino.isPresent()) {
            boolean dijkstraCompleto = false;
            NodoVertice nodoOrigen = isOrigen.get();
            NodoVertice nodoDestino = isDestino.get();

            //Arreglo de rutas posibles, utilizado para validación
            List<List<NodoVertice>> rutas = new ArrayList<>();

            //Arreglo de arcos posibles, utilizado para calcular ruta max y min.
            List<List<NodoArco>> arcos = new ArrayList<>();

            //Obtener nodos siguientes a partir del origen.
            List<NodoArco> destinos = mapAdy.get(nodoOrigen);

            // Por cada nodo arco registrado para el origen
            for (NodoArco posibleDestino : destinos) {
                dijkstraCompleto = false;
                List<NodoVertice> ruta = new ArrayList<>();
                ruta.add(nodoOrigen);
                List<NodoArco> arco = new ArrayList<>();
                arco.add(posibleDestino);
                // Se encontró vértice destino
                if (posibleDestino.getDestino().equals(nodoDestino)) {
                    arcos.add(arco);
                    dijkstraCompleto = true;
                } else {
                    //No se encontró el vértice destino, iniciar recursividad.
                    dijkstraCompleto = dijkstraRecursivo(posibleDestino.getDestino(), nodoDestino, ruta, arco, rutas, arcos);
                }
            }

            if (dijkstraCompleto) {
                int i = 1;
                // Una vez encontrados todos las rutas viables con sus respectivos pesos, ordenarlas según su peso.
                SortedMap<Double, String> rutasOrdenadas = new TreeMap<>();
                for (List<NodoArco> arco : arcos) {
                    double peso = 0;
                    StringBuilder currentRuta = new StringBuilder("Ruta No. " + i + " : " + nodoOrigen.getLabel());
                    for (NodoArco nodo : arco) {
                        currentRuta.append(" -> ").append(nodo.getDestino().getLabel());
                        peso += nodo.getPeso();
                    }
                    BigDecimal bd = new BigDecimal(peso).setScale(2, RoundingMode.HALF_UP);
                    double pesoFormat = bd.doubleValue();
                    currentRuta.append(" *** Distancia: ").append(pesoFormat).append(" km ***\n");
                    rutasOrdenadas.put(pesoFormat, currentRuta.toString());
                    i++;
                }

                if (rutasOrdenadas.size()!=0) {
                    // Si el recorrido es mínimo
                    if (pRecorrido == 0) {
                        mensaje.append(rutasOrdenadas.get(rutasOrdenadas.firstKey()));
                    }
                    // Si el recorrido es máximo
                    else {
                        mensaje.append(rutasOrdenadas.get(rutasOrdenadas.lastKey()));
                    }
                }
                else {
                    mensaje.append("No es posible llegar al destino desde la ubicación origen.");
                }


            } else {
                mensaje.append("La ubicación origen se encuentra aislada.");
            }
        }


        // No hay vértices con los nombres registrados
        else {
            mensaje.append("La ubicación origen y/o destino no se encuentran registradas.");
        }

        // Retornar la cadena de Strings con la ruta óptima y su peso
        return String.valueOf(mensaje);
    }

    /**
     * Método recursivo del algoritmo de dijkstra para determinar recorridos
     * @param nodoOrigen El vértice utilizado como origen de la recursividad
     * @param nodoDestino El vértice destino definido por el usuario
     * @param ruta La lista de vértices que definen la ruta actual
     * @param arco La lista de arcos que definen la ruta actual
     * @param rutas La lista de listas de rutas viables para llegar del origen al destino
     * @param arcos La lista de listas de arcos viables para llegar del origen al destino
     * @return Retorna un booleano que indica si la recursividad actual está completa
     */
    private boolean dijkstraRecursivo(NodoVertice nodoOrigen, NodoVertice nodoDestino, List<NodoVertice> ruta, List <NodoArco> arco, List<List<NodoVertice>> rutas, List<List<NodoArco>> arcos) {

        //Proceso de obtención de nodos siguientes.
        List<NodoArco> destinosR = mapAdy.get(nodoOrigen);

        for (NodoArco posibleDestinoR : destinosR) {

            //Salvaguarda contra retornos infinitos.
            if (ruta.contains(nodoOrigen)) {
                return true;
            }

            List<NodoVertice> nRuta = new ArrayList<>(ruta);
            List<NodoArco> nArco = new ArrayList<>(arco);

            // Si llega al vértice destino
            if(posibleDestinoR.getDestino().getLabel().equals(nodoDestino.getLabel())){
                nArco.add(posibleDestinoR);
                arcos.add(nArco);
                nRuta.add(nodoOrigen);
                rutas.add(nRuta);
            }
            //Continúa buscando en la recursividad
            else {
                nRuta.add(nodoOrigen);
                nArco.add(posibleDestinoR);
                dijkstraRecursivo(posibleDestinoR.getDestino(), nodoDestino, nRuta, nArco, rutas, arcos);
            }
        }
        return true;
    }
}
