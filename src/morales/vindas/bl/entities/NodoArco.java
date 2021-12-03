package morales.vindas.bl.entities;

public class NodoArco {
    private NodoVertice destino;
    private double peso;

    public NodoArco(NodoVertice pDestino, double pPeso) {
        this.destino = pDestino;
        this.peso = pPeso;
    }

    public NodoArco(NodoVertice origen, NodoVertice destino, double peso) {
        this.destino = destino;
        this.peso = peso;
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
