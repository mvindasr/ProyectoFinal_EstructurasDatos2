package morales.vindas.ui;

public class TipoGrafo extends ApplicationUI {

    @Override
    public void mostrarMenu() {
        out.println("╔═══════════════════════════════════════════════════╗");
        out.println("                  Tipos de Grafos");
        out.println("╚═══════════════════════════════════════════════════╝");
        out.println("1. Grafo de Matriz de Adyacencia");
        out.println("2. Grafo de Lista de Adyacencia");
        out.println("3. Grafo de Lista Encadenada Múltiple de Adyacencia");
        out.println("═════════════════════════════════════════════════════");
        out.println("Digite su tipo de grafo: ");
    }
}