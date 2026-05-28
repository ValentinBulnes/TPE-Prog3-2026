import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Camion> camiones = CSVParser.parsearCamiones("Camiones.csv");
        List<Paquete> paquetes = CSVParser.parsearPaquetes("Paquetes.csv");

        System.out.println("=== CAMIONES (" + camiones.size() + ") ===");
        for (Camion c : camiones) System.out.println(c);

        System.out.println("\n=== PAQUETES (" + paquetes.size() + ") ===");
        for (Paquete p : paquetes) System.out.println(p);
    }
}
