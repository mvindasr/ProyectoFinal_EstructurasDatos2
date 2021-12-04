package morales.vindas;

import morales.vindas.tl.Controller;

/**
 * Clase principal que inicializa la aplicación
 * @author Carlos Morales, Milton Vindas - Estructuras de Datos 2 - III-2021
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Controller controlador = new Controller();
        controlador.iniciar();
    }
}
