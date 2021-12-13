package morales.vindas.bl.entities;

/**
 * Clase que representa un nodo arco del grafo
 * @author Carlos Morales, Milton Vindas - Estructuras de Datos 2 - III-2021
 */
public class NodoArco {

    /**
     * El nodo vértice que representa el destino en el arco
     */
    private NodoVertice destino;

    /**
     * El número que representa el peso del arco
     */
    private double peso;

    public NodoArco(NodoVertice pDestino, double pPeso) {
        this.destino = pDestino;
        this.peso = pPeso;
    }

    public NodoVertice getDestino() {
        return destino;
    }

    public void setDestino(NodoVertice destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof NodoArco))
            return false;
        NodoArco a = (NodoArco) obj;
        return a.getDestino().equals(this.getDestino());
    }

    @Override
    public int hashCode() {
        int result=17;
        result= (int) (31*result+peso);
        result=31*result+(destino!=null ? destino.getLabel().hashCode():0);
        return result;
    }
}
