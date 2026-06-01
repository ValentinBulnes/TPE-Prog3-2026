import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servicios {

    private List<Camion> camiones;
    private HashMap<String, Paquete> paquetesPorCodigo;

    //Complejidad Temporal Constructor: O(c + p) c cantidad de camiones, p cantidad de paquetes
    public Servicios(String pathCamiones, String pathPaquetes) {
        camiones = CSVParser.parsearCamiones(pathCamiones);
        paquetesPorCodigo = new HashMap<>();

        List<Paquete> paquetes = CSVParser.parsearPaquetes(pathPaquetes);
        for (Paquete p : paquetes) {
            paquetesPorCodigo.put(p.getCodigoPaquete(), p);
        }
    }

   //Complejidad Temporal Servicio1: O(1)
    public Paquete servicio1(String codigoPaquete) {
        Paquete salida = (paquetesPorCodigo.containsKey(codigoPaquete)) ? paquetesPorCodigo.get(codigoPaquete) : null;
        return salida;
    }


    //Complejidad Temporal Servicio2: O(n) n cantidad de paquetes
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        List<Paquete> salida = new ArrayList<>();
        for(String codigo : paquetesPorCodigo.keySet()) {
            Paquete p = paquetesPorCodigo.get(codigo);
            if(p.contieneAlimentos() == contieneAlimentos) salida.add(p);
        }
        return salida;
    }

    //Complejidad Temporal Servicio3: O(n) n cantidad de paquetes
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> salida = new ArrayList<>();
        for(String codigo : paquetesPorCodigo.keySet()) {
            Paquete p = paquetesPorCodigo.get(codigo);
            if(p.getNivelUrgencia() >= urgenciaMinima && p.getNivelUrgencia() <= urgenciaMaxima) salida.add(p);
        }
        return salida;
    }
}
