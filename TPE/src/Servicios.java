import java.util.ArrayList;
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
   //               O(1)??
    public Paquete servicio1(String codigoPaquete) {
        Paquete salida = (paquetesPorCodigo.containsKey(codigoPaquete)) ? paquetesPorCodigo.get(codigoPaquete) : null;
        return salida;
    }


    //Expresar la complejidad temporal del servicio 2.
    //               O(n)??
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        List<Paquete> salida = new ArrayList<>();
        for(String codigo : paquetesPorCodigo.keySet()) {
            Paquete p = paquetesPorCodigo.get(codigo);
            if(p.contieneAlimentos() == contieneAlimentos) salida.add(p);
        }
        return salida;
    }

    //Expresar la complejidad temporal del servicio 3.
    //                O(n)??
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> salida = new ArrayList<>();
        for(String codigo : paquetesPorCodigo.keySet()) {
            Paquete p = paquetesPorCodigo.get(codigo);
            if(p.getNivelUrgencia() >= urgenciaMaxima && p.getNivelUrgencia() <= urgenciaMaxima) salida.add(p);
        }
        return salida;
    }
}
