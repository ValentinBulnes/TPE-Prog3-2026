import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Servicios s = new Servicios("Camiones.csv", "Paquetes.csv");
        // System.out.println(s.servicio1("P001"));
        // System.out.println(s.servicio2(true));
        // System.out.println(s.servicio3(40, 90));

        List<Camion> camiones = CSVParser.parsearCamiones("Camiones.csv");
        List<Paquete> paquetes = CSVParser.parsearPaquetes("Paquetes.csv");

        Greedy greedy = new Greedy();
        greedy.imprimirResultados(greedy.resolver(camiones, paquetes));

    }
}
