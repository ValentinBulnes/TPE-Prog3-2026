import java.util.*;

public class Greedy {
    private int pesoNoAsignado;
    private int candidatosConsiderados;

    public Greedy() {
        this.pesoNoAsignado = 0;
        this.candidatosConsiderados = 0;
    }

    /*
     * La estrategia greedy utilizada es la de intentar cargar el camion de mayor capacidad
     * con el paquete de mayor peso posible. Como se busca minimizar el peso total de paquetes no asignados
     * se usa esta estrategia para evitar que los paquetes mas pesados no queden sin cargar. Para eso primero
     * se ordenan ambas listas, los camiones de mayor capacidad a menor y los paquetes de mayor peso a menor.
     * Luego se itera sobre la lista de paquetes ordenada y se intenta cargarlos en el camion de mayor capacidad,
     * para eso se itera ahora sobre la lista de camiones ordenada, y si es factible cargar el paquete en el
     * camion (el peso del paquete es menor o igual a la capacidad del camion, no se supera la capacidad maxima
     * del camion y si el paquete contiene alimentos el camion debe ser refrigerado) se agrega el paquete, si no
     * se puede cargar el paquete se suma el peso al peso no asignado. Luego de iterar sobre todos los paquetes
     * se retorna la solucion.
     * La solucion es un HashMap donde la key es el camion y cada valor es una lista de paquetes que se cargaron
     * en ese camion.
     * Se incluye un metodo para imprimir los resultados de la solucion.
     *
     * Complejidad Temporal: O(n^2) donde n es la cantidad de paquetes y camiones.
     */
    public Map<Camion, List<Paquete>> resolver(List<Camion> camiones, List<Paquete> paquetes) {
        //se crea la estructura para la solucion
        Map<Camion, List<Paquete>> asignaciones = new HashMap<>();

        for (Camion c : camiones) {
            asignaciones.put(c, new ArrayList<>());
        }

        //ordenar camiones por capacidad de mayor a menor
        List<Camion> camionesOrdenados = new ArrayList<>(camiones);
        camionesOrdenados.sort(Comparator.comparingInt(Camion::getCapacidadKg).reversed());

        //ordenar paquetes por peso de mayor a menor
        List<Paquete> paquetesOrdenados = new ArrayList<>(paquetes);
        paquetesOrdenados.sort(Comparator.comparingInt(Paquete::getPesoKg).reversed());

        for (Paquete p : paquetesOrdenados) {
            this.candidatosConsiderados++;
            boolean pudoSerAsignado = false;
            int i = 0;

            while (i < camiones.size() && !pudoSerAsignado) {
                Camion c = camionesOrdenados.get(i);

                boolean entraPorPeso = p.getPesoKg() <= c.getCapacidadKg();
                boolean cumpleFrio = !p.contieneAlimentos() || c.estaRefrigerado();

                if (entraPorPeso && cumpleFrio) {
                    asignaciones.get(c).add(p);
                    pudoSerAsignado = true;
                    c.restarCapacidad(p.getPesoKg());
                }
                i++;
            }

            if (!pudoSerAsignado) {
                this.pesoNoAsignado += p.getPesoKg();
            }
        }
        return asignaciones;
    }



    public void imprimirResultados(Map<Camion, List<Paquete>> solucion) {
        System.out.println("Greedy");
        System.out.println("Solución obtenida:");

        for (Camion c : solucion.keySet()) {
            List<Paquete> carga = solucion.get(c);

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
        System.out.println("Peso no asignado: " + this.pesoNoAsignado + " kg.");
        System.out.println("Cantidad de candidatos considerados: " + this.candidatosConsiderados);
    }
}