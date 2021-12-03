package morales.vindas.bl.helpers;

import morales.vindas.bl.entities.NodoArco;
import morales.vindas.bl.entities.NodoVertice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Algorithms {

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
