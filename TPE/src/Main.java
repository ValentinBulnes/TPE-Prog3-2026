import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Servicios s = new Servicios("Camiones.csv", "Paquetes.csv");
        // System.out.println(s.servicio1("P001"));
        // System.out.println(s.servicio2(true));
        // System.out.println(s.servicio3(40, 90));

        List<Camion> camiones = CSVParser.parsearCamiones("TPE/Camiones.csv");
        List<Paquete> paquetes = CSVParser.parsearPaquetes("TPE/Paquetes.csv");

        Backtracking back = new Backtracking();
        Solucion solucion = back.resolver(camiones, paquetes);
        solucion.imprimir();

        System.out.println();

        Greedy greedy = new Greedy();
        greedy.imprimirResultados(greedy.resolver(camiones, paquetes));
    }
}
