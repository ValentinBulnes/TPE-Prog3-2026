import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<Camion> parsearCamiones(String pathCamiones) {
        List<Camion> camiones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathCamiones))) {
            int total = Integer.parseInt(br.readLine().trim());
            camiones = new ArrayList<>(total);

            for (int i = 0; i < total; i++) {
                String linea = br.readLine();
                if (linea == null) break;

                String[] partes = linea.trim().split(";");
                int id               = Integer.parseInt(partes[0]);
                String patente       = partes[1];
                boolean refrigerado  = partes[2].equals("1");
                int capacidad        = Integer.parseInt(partes[3]);

                camiones.add(new Camion(id, patente, refrigerado, capacidad));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de camiones: " + e.getMessage());
        }

        return camiones;
    }

    public static List<Paquete> parsearPaquetes(String pathPaquetes) {
        List<Paquete> paquetes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathPaquetes))) {
            int total = Integer.parseInt(br.readLine().trim());
            paquetes = new ArrayList<>(total);

            for (int i = 0; i < total; i++) {
                String linea = br.readLine();
                if (linea == null) break;

                String[] partes = linea.trim().split(";");
                int id                  = Integer.parseInt(partes[0]);
                String codigo           = partes[1];
                int peso                = Integer.parseInt(partes[2]);
                boolean contieneAlim    = partes[3].equals("1");
                int urgencia            = Integer.parseInt(partes[4]);

                paquetes.add(new Paquete(id, codigo, peso, contieneAlim, urgencia));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de paquetes: " + e.getMessage());
        }

        return paquetes;
    }
}
