import java.util.HashMap;
import java.util.List;

public class Servicios {
    //Completar con las estructuras y métodos privados que se requieran.

    private List<Camion> camiones;
    private HashMap<String, Paquete> paquetesPorCodigo;

    //Expresar la complejidad temporal del constructor.
    public Servicios(String pathCamiones, String pathPaquetes) {
        camiones = CSVParser.parsearCamiones(pathCamiones);
        paquetesPorCodigo = new HashMap<>();

        List<Paquete> paquetes = CSVParser.parsearPaquetes(pathPaquetes);
        for (Paquete p : paquetes) {
            paquetesPorCodigo.put(p.getCodigoPaquete(), p);
        }
    }

   //Expresar la complejidad temporal del servicio 1.
    public Paquete servicio1(String codigoPaquete) {
        return paquetesPorCodigo.get(codigoPaquete);
    }


    //Expresar la complejidad temporal del servicio 2.
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return null;
    }

    //Expresar la complejidad temporal del servicio 3.
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        return null;
    }
}
