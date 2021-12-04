package morales.vindas.bl.entities;

/**
 * Clase que representa un nodo vértice del grafo
 * @author Carlos Morales, Milton Vindas - Estructuras de Datos 2 - III-2021
 */
public class NodoVertice {

     /**
     * La etiqueta del vértice
     */
    private String label;

    /**
     * La coordenada x (latitud) del vértice
     */
    private double x;
    /**
     * La coordenada y (longitud) del vértice
     */
    private double y;

    public NodoVertice(String label, double x, double y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public NodoVertice(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof NodoVertice))
            return false;
        NodoVertice v = (NodoVertice) obj;
        return v.getLabel().equals(this.getLabel());
    }

    @Override
    public int hashCode() {
        int result=17;
        result= (int) (31*result+x+y);
        result=31*result+(label!=null ? label.hashCode():0);
        return result;
    }
}
