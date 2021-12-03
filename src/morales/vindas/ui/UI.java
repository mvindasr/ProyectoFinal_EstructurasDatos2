package morales.vindas.ui;


public class UI extends ApplicationUI {

    @Override
    public void mostrarMenu() {
        out.println("╔═════════════════════════════╗");
        out.println("       Menú de opciones");
        out.println("╚═════════════════════════════╝");
        out.println("1. Mostrar ubicaciones.");
        out.println("2. Buscar ubicación.");
        out.println("3. Mostrar camino mínimo.");
        out.println("4. Mostrar camino máximo.");
        out.println("4. Salir.");
        out.println("═══════════════════════════════");
        out.println("Digite la opción: ");
    }

}

