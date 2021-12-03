package morales.vindas.tl;

import morales.vindas.bl.logic.Gestor;
import morales.vindas.ui.TipoGrafo;
import morales.vindas.ui.UI;

import java.io.IOException;


public class Controller {
    private UI interfaz;
    private TipoGrafo menuGrafo;
    private Gestor localGestor;

    public Controller() {
        interfaz = new UI();
        menuGrafo = new TipoGrafo();
    }

    public void iniciar() throws Exception {

        int opcion;

        localGestor = new Gestor();

        menuGrafo.imprimirMensaje("*******************************************************************************");
        menuGrafo.imprimirMensaje("*****      Bienvenido al Proyecto de Mapa de Estructuras de Datos 2      ******");
        menuGrafo.imprimirMensaje("*******************************************************************************");

        cargarInfo();

        do {
            interfaz.mostrarMenu();
            opcion = interfaz.leerOpcion();
            procesarOpcion(opcion);
        }
        while (opcion != 5);
    }

    public void procesarOpcion(int pOpcion) throws Exception {
        switch (pOpcion) {
            case 1:
                mostrarUbicaciones();
                break;
            case 2:
                mostrarUbicacion();
                break;
            case 3:
                mostrarMinimo();
                break;
            case 4:
                mostrarMaximo();
                break;
            default:
                interfaz.imprimirMensaje("Opción incorrecta.");
                break;
        }
        interfaz.imprimirMensaje("Presione enter para continuar...");
        interfaz.leerTexto();
    }

    public void mostrarUbicaciones() {
        interfaz.imprimirMensaje(localGestor.mostrarUbicaciones());
    }

    public void mostrarMinimo() throws IOException {
        interfaz.imprimirMensaje("Ingrese la ubicación origen:");
        String origen = interfaz.leerTexto();
        interfaz.imprimirMensaje("Ingrese la ubicación destino:");
        String destino = interfaz.leerTexto();
        interfaz.imprimirMensaje(localGestor.mostrarRecorrido(origen, destino, 0));

    }

    public void mostrarMaximo() throws IOException {
        interfaz.imprimirMensaje("Ingrese la ubicación origen:");
        String origen = interfaz.leerTexto();
        interfaz.imprimirMensaje("Ingrese la ubicación destino:");
        String destino = interfaz.leerTexto();

        interfaz.imprimirMensaje(localGestor.mostrarRecorrido(origen, destino, 1));

    }

    public void mostrarUbicacion() throws IOException {
        interfaz.imprimirMensaje("Ingrese la ubicación que desea consultar:");
        String ubicacion = interfaz.leerTexto();
        interfaz.imprimirMensaje(localGestor.mostrarUbicacion(ubicacion));
    }

    public void cargarInfo() {
        // Imprimir proceso

        String mensaje = "";

        //Insertar ubicaciones
        mensaje += localGestor.agregarVertice("Abangares", 9.9983107, -84.9904051);
        mensaje += "\n" + localGestor.agregarVertice("Buenos Aires", 9.1706097, -83.3491518);
        mensaje += "\n" + localGestor.agregarVertice("Coto Brus", 8.9540868, -83.0879566);
        mensaje += "\n" + localGestor.agregarVertice("Heredia", 10.161858, -84.122862);
        mensaje += "\n" + localGestor.agregarVertice("Garabito", 9.608331, -84.3154468);
        mensaje += "\n" + localGestor.agregarVertice("Golfito", 8.6299479, -83.1819133);
        mensaje += "\n" + localGestor.agregarVertice("Liberia", 10.6295425, -84.3154468);
        mensaje += "\n" + localGestor.agregarVertice("Los Chiles", 11.0304838,-84.7202755);
        mensaje += "\n" + localGestor.agregarVertice("Matina", 10.0798886,-83.3160427);
        mensaje += "\n" + localGestor.agregarVertice("Nicoya", 10.149463,-85.471144);
        mensaje += "\n" + localGestor.agregarVertice("Osa", 8.613868, -83.620406);
        mensaje += "\n" + localGestor.agregarVertice("Paraíso", 9.8384387, -83.8878742);
        mensaje += "\n" + localGestor.agregarVertice("Pérez Zeledón", 9.421309, -83.702215);
        mensaje += "\n" + localGestor.agregarVertice("Poás", 10.1063814, -84.3180106);
        mensaje += "\n" + localGestor.agregarVertice("Pococí", 10.621931, -83.618291);
        mensaje += "\n" + localGestor.agregarVertice("Puntarenas", 9.794318, -85.103378);
        mensaje += "\n" + localGestor.agregarVertice("Quepos", 9.434707, -84.098749);
        mensaje += "\n" + localGestor.agregarVertice("San Carlos", 10.576669, -84.403052);
        mensaje += "\n" + localGestor.agregarVertice("San José", 9.9357074, -84.1835587);
        mensaje += "\n" + localGestor.agregarVertice("San Ramón", 10.0908106, -84.4866859);
        mensaje += "\n" + localGestor.agregarVertice("Sarapiquí", 10.685857, -83.952774);
        mensaje += "\n" + localGestor.agregarVertice("Talamanca", 9.6247937, -82.8645518);
        mensaje += "\n" + localGestor.agregarVertice("Tilarán", 10.4613772,-85.0066111);
        mensaje += "\n" + localGestor.agregarVertice("Turrialba", 9.9060239, -83.699341);
        mensaje += "\n" + localGestor.agregarVertice("Upala", 10.8980836,-85.0336863);

        //Insertar Arcos (63)

        mensaje += "\n" + localGestor.agregarArco("Abangares", "Puntarenas");
        mensaje += "\n" + localGestor.agregarArco("Abangares", "Garabito");

        mensaje += "\n" + localGestor.agregarArco("Buenos Aires", "Coto Brus");
        mensaje += "\n" + localGestor.agregarArco("Buenos Aires", "Golfito");
        mensaje += "\n" + localGestor.agregarArco("Buenos Aires", "Osa");
        mensaje += "\n" + localGestor.agregarArco("Buenos Aires", "Matina");
        mensaje += "\n" + localGestor.agregarArco("Buenos Aires", "Pérez Zeledón");

        mensaje += "\n" + localGestor.agregarArco("Coto Brus", "Golfito");

        mensaje += "\n" + localGestor.agregarArco("Heredia", "Pococí");
        mensaje += "\n" + localGestor.agregarArco("Heredia", "Poás");
        mensaje += "\n" + localGestor.agregarArco("Heredia", "San José");
        mensaje += "\n" + localGestor.agregarArco("Heredia", "Sarapiquí");

        mensaje += "\n" + localGestor.agregarArco("Garabito", "Poás");
        mensaje += "\n" + localGestor.agregarArco("Garabito", "Quepos");

        mensaje += "\n" + localGestor.agregarArco("Golfito", "Coto Brus");

        mensaje += "\n" + localGestor.agregarArco("Liberia", "Nicoya");

        mensaje += "\n" + localGestor.agregarArco("Los Chiles", "Upala");
        mensaje += "\n" + localGestor.agregarArco("Los Chiles", "Sarapiquí");
        mensaje += "\n" + localGestor.agregarArco("Los Chiles", "San Carlos");

        mensaje += "\n" + localGestor.agregarArco("Matina", "Pococí");
        mensaje += "\n" + localGestor.agregarArco("Matina", "Talamanca");

        mensaje += "\n" + localGestor.agregarArco("Nicoya", "Liberia");
        mensaje += "\n" + localGestor.agregarArco("Nicoya", "Puntarenas");

        mensaje += "\n" + localGestor.agregarArco("Osa", "Golfito");

        mensaje += "\n" + localGestor.agregarArco("Paraíso", "Talamanca");
        mensaje += "\n" + localGestor.agregarArco("Paraíso", "Turrialba");
        mensaje += "\n" + localGestor.agregarArco("Paraíso", "Pérez Zeledón");
        mensaje += "\n" + localGestor.agregarArco("Paraíso", "San José");

        mensaje += "\n" + localGestor.agregarArco("Pérez Zeledón", "Turrialba");
        mensaje += "\n" + localGestor.agregarArco("Pérez Zeledón", "Paraíso");
        mensaje += "\n" + localGestor.agregarArco("Pérez Zeledón", "Buenos Aires");
        mensaje += "\n" + localGestor.agregarArco("Pérez Zeledón", "Osa");
        mensaje += "\n" + localGestor.agregarArco("Pérez Zeledón", "San José");

        mensaje += "\n" + localGestor.agregarArco("Poás", "Heredia");
        mensaje += "\n" + localGestor.agregarArco("Poás", "San Ramón");
        mensaje += "\n" + localGestor.agregarArco("Poás", "San Carlos");

        mensaje += "\n" + localGestor.agregarArco("Pococí", "Sarapiquí");
        mensaje += "\n" + localGestor.agregarArco("Pococí", "Turrialba");

        mensaje += "\n" + localGestor.agregarArco("Puntarenas", "Nicoya");

        mensaje += "\n" + localGestor.agregarArco("Quepos", "Pérez Zeledón");

        mensaje += "\n" + localGestor.agregarArco("San Carlos", "Sarapiquí");
        mensaje += "\n" + localGestor.agregarArco("San Carlos", "Tilarán");
        mensaje += "\n" + localGestor.agregarArco("San Carlos", "Los Chiles");
        mensaje += "\n" + localGestor.agregarArco("San Carlos", "Upala");

        mensaje += "\n" + localGestor.agregarArco("San José", "Heredia");
        mensaje += "\n" + localGestor.agregarArco("San José", "Poás");
        mensaje += "\n" + localGestor.agregarArco("San José", "Paraíso");
        mensaje += "\n" + localGestor.agregarArco("San José", "Quepos");
        mensaje += "\n" + localGestor.agregarArco("San José", "Pérez Zeledón");
        mensaje += "\n" + localGestor.agregarArco("San José", "Pococí");
        mensaje += "\n" + localGestor.agregarArco("San José", "Garabito");

        mensaje += "\n" + localGestor.agregarArco("San Ramón", "Abangares");
        mensaje += "\n" + localGestor.agregarArco("San Ramón", "Garabito");
        mensaje += "\n" + localGestor.agregarArco("San Ramón", "San Carlos");

        mensaje += "\n" + localGestor.agregarArco("Sarapiquí", "Los Chiles");
        mensaje += "\n" + localGestor.agregarArco("Sarapiquí", "Pococí");

        mensaje += "\n" + localGestor.agregarArco("Talamanca", "Coto Brus");
        mensaje += "\n" + localGestor.agregarArco("Talamanca", "Buenos Aires");

        mensaje += "\n" + localGestor.agregarArco("Tilarán", "Liberia");
        mensaje += "\n" + localGestor.agregarArco("Tilarán", "Abangares");
        mensaje += "\n" + localGestor.agregarArco("Tilarán", "San Ramón");
        mensaje += "\n" + localGestor.agregarArco("Tilarán", "Nicoya");

        mensaje += "\n" + localGestor.agregarArco("Turrialba", "Matina");

        mensaje += "\n" + localGestor.agregarArco("Upala", "Tilarán");
        mensaje += "\n" + localGestor.agregarArco("Upala", "Liberia");

        /*interfaz.imprimirMensaje(mensaje);*/

    }
}
