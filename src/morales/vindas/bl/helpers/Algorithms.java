package morales.vindas.bl.helpers;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class Algorithms {

    /**
     * Método que devuelve un número con la distancia entre dos puntos terrestres
     * @param lat1 La latitud del punto 1
     * @param lat2 La latitud del punto 2
     * @param lon1 La longitud del punto 1
     * @param lon2 La longitud del punto 2
     * @return Devuelve la distancia entre los dos puntos
     */
    public static double haversine(double lat1, double lat2, double lon1, double lon2) {

        // Convierte grados a radianes
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Fórmula de Haversine para calcular distancia
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
        double haversine = 2 * Math.asin(Math.sqrt(a));

        // Radio de la Tierra en kilómetros. Para millas usar 3956
        double radioTierra = 6371;

        // Calcular el resultado en términos terrestres con dos decimales
        BigDecimal bd = new BigDecimal(haversine * radioTierra).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
