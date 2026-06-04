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
     *
     * Complejidad Temporal: O(PxC) donde P es la cantidad de paquetes y C la cantidad de camiones.
     * Ya que por cada paquete se itera la lista de camiones buscando el adecuado.
     * (Sin tener en cuenta el de ordenar las listas)
     */
    public Solucion resolver(List<Camion> camiones, List<Paquete> paquetes) {
        //se crea la estructura para la solucion
        HashMap<Camion, List<Paquete>> asignaciones = new HashMap<>();
        HashMap<Camion, Integer> capacidadRestante = new HashMap<>();

        for (Camion c : camiones) {
            asignaciones.put(c, new ArrayList<>());
            capacidadRestante.put(c, c.getCapacidadKg());
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

            while (i < camionesOrdenados.size() && !pudoSerAsignado) {
                Camion c = camionesOrdenados.get(i);

                boolean entraPorPeso = p.getPesoKg() <= capacidadRestante.get(c);
                boolean cumpleFrio = !p.contieneAlimentos() || c.estaRefrigerado();

                if (entraPorPeso && cumpleFrio) {
                    asignaciones.get(c).add(p);
                    capacidadRestante.put(c, capacidadRestante.get(c) - p.getPesoKg());
                    pudoSerAsignado = true;
                }
                i++;
            }

            if (!pudoSerAsignado) {
                this.pesoNoAsignado += p.getPesoKg();
            }
        }
        return new Solucion(asignaciones, this.pesoNoAsignado, this.candidatosConsiderados);
    }
}