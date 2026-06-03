import java.util.HashMap;
import java.util.List;

public class Solucion {

    private HashMap<Camion,List<Paquete>> resultado;
    private int pesoNoAsignado;
    private int estadosGenerados;

    public Solucion(HashMap<Camion,List<Paquete>> resultado, int pesoNoAsignado, int estadosGenerados) {
        this.resultado = resultado;
        this.pesoNoAsignado = pesoNoAsignado;
        this.estadosGenerados = estadosGenerados;
    }

    public HashMap<Camion, List<Paquete>> getResultado() {
        return resultado;
    }

    public int getPesoNoAsignado() {
        return pesoNoAsignado;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public void imprimir() {
        System.out.println("Backtracking");
        System.out.println("Solución obtenida:");

        for (Camion c : resultado.keySet()) {
            List<Paquete> carga = resultado.get(c);

            System.out.print("Camión " + c.getId() + " -> Paquetes asignados: ");

            if (carga.isEmpty()) {
                System.out.println("Ninguno");
            } else {
                System.out.print("[");
                for (int i = 0; i < carga.size(); i++) {
                    System.out.print(carga.get(i).getId());
                    if (i < carga.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }
        System.out.println("Peso no asignado: " + pesoNoAsignado + " kg.");
        System.out.println("Cantidad de estados generados: " + estadosGenerados);
    }
}
