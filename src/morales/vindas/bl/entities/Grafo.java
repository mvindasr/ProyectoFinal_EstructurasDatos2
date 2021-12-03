package morales.vindas.bl.entities;

import java.util.*;

import static morales.vindas.bl.helpers.Algorithms.haversine;

public class Grafo {


    private Map<NodoVertice, LinkedList<NodoArco>> mapAdy;

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

    public String mostrarUbicacion(String pUbicacion) {
        return mostrarUbicacionUnica(pUbicacion);
    }


    public String mostrarRecorrido(String pOrigen, String pDestino, int pRecorrido) {
        return "";
    }

    public String mostrarMaximo(String pOrigen, String pDestino) {
        return "";
    }

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
     * @return El String que muestra la información de la ubicación
     */
    private String mostrarUbicacionUnica(String pUbicacion) {
        Optional<NodoVertice> ubicacion = encontrarVertice(pUbicacion);
        StringBuilder mensaje = new StringBuilder("\n");

        if (ubicacion.isPresent()) {

            mensaje.append("╔════════════════════════════════════════════════╗\n");
            mensaje.append("             Ubicación: ").append(pUbicacion).append("\n");
            mensaje.append("╚════════════════════════════════════════════════╝\n");
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
        }


        return String.valueOf(mensaje);
    }

    /*private StringBuilder dijkstra(String pOrigen, String pDestino, int pRecorrido) {

        Optional<NodoVertice> isOrigen = encontrarVertice(pOrigen);
        Optional<NodoVertice> isDestino = encontrarVertice(pDestino);
        StringBuilder mensaje = new StringBuilder("\n");

        if (isOrigen.isPresent() && isDestino.isPresent()) {
            NodoVertice nodoOrigen = isOrigen.get();
            NodoVertice nodoDestino = isDestino.get();

            //Arreglo de vértices posibles
            List<List<NodoVertice>> rutas = new ArrayList<>();

            //Arreglo de arcos posibles
            List<List<NodoArco>> arcos = new ArrayList<>();

            //Obtener nodos siguientes a partir del origen.
            List<NodoArco> destinos = mapAdy.get(nodoOrigen);


            for (NodoArco posibleDestino : destinos) {
                if(posibleDestino.getDestino().equals(nodoDestino)) {
                    mensaje.append("\n");
                }
                else {
                    //No se encontró lo que se buscaba.
                    if (dijkstraRecursivo(posibleDestino.getDestino(), nodoDestino, new ArrayList<>(), rutas, arcos))
                    {
                        System.out.println(rutas);
                    }

                }
            }
            System.out.println(rutas);

            int i = 0;
            SortedMap<Integer, String> rutasOrdenadas = new TreeMap<>();
            for (List<NodoArco> ruta : rutas) {
                i++;
                int peso = 0;
                String currentVerose = "Ruta: " + i + " : " + ruta.get(0).getOrigen().getLabel();
                for ( NodoArco nodo : ruta) {
                    currentVerose += " -> " + nodo.getDestino().getLabel() ;
                    peso += nodo.getPeso();
                }
                currentVerose += " Peso: " + peso + '\n';
                System.out.print(currentVerose);
                verboseRutas.put(peso, currentVerose);
            }

            System.out.print("La mas rapida: " + verboseRutas.get(verboseRutas.firstKey()));
            System.out.println("La mas lenta: " + verboseRutas.get(verboseRutas.lastKey()));

        }

        else {
            mensaje.append("La ubicación origen y/o destino no se encuentran registradas.");
        }

        return mensaje;


    }

    public boolean dijkstraRecursivo(NodoVertice nodoOrigen, NodoVertice nodoDestino, List<NodoVertice> ruta, List<List<NodoVertice>> rutas, List<List<NodoArco>> arcos) {



        //processo de obtencion de nodos siguientes.
        List<NodoArco> destinos = mapAdy.get(nodoOrigen);
        System.out.println("Nodo actual: " + nodoDePartida.getOrigen().getLabel());
        ruta.add(nodoDePartida);



        for (NodoArco posibleDestino : destinos) {

            //Salva guarda contra retornos infinitos.
            if(posibleDestino.getDestino().equals(ruta.get(0)))
                return  false;

            List<NodoArcoLista> nRuta = new ArrayList<>();
            nRuta.addAll(ruta);
            if(posibleDestino.getDestino().getLabel().equals(claveDeBusqueda)){
                System.out.println("Eureca!");
                nRuta.add(posibleDestino);
                rutas.add(nRuta);
            } else {
                SubBusqueda(posibleDestino, claveDeBusqueda, nRuta, nodoVerticeList, nodoArcoListaList,rutas);
            }
        }
        return false;


    }*/
}
