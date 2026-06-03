import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Backtracking {
    
    // Datos de entrada
    private List<Camion> camiones;
    private List<Paquete> paquetes;

    // Estado de la busqueda
    private HashMap<Camion,Integer> capacidadDisponible;
    private HashMap<Camion,List<Paquete>> solucionActual;
    private HashMap<Camion,List<Paquete>> mejorSolucion;
    private int[] pesoAcumulado;
    private int mayorPesoAsignado;
    private int estadosGenerados;

    public Solucion resolver(List<Camion> camionesEntrada, List<Paquete> paquetesEntrada) {
        
        // Inicialización

        this.camiones = new ArrayList<>(camionesEntrada);
        this.paquetes = new ArrayList<>(paquetesEntrada);

        // Ordeno lo paquetes, por peso, de mayor a menor
        this.paquetes.sort(Comparator.comparingInt(Paquete::getPesoKg).reversed());

        mejorSolucion = new HashMap<>();
        capacidadDisponible = new HashMap<>();
        solucionActual = new HashMap<>();
        for(Camion c : this.camiones) {
            capacidadDisponible.put(c, c.getCapacidadKg());
            solucionActual.put(c, new ArrayList<>());
        }

        mayorPesoAsignado = -1;
        estadosGenerados = 0;
        int pesoTotalPaquetes = 0;
        for(Paquete p : this.paquetes) {
            pesoTotalPaquetes += p.getPesoKg();
        }

        pesoAcumulado = obtenerPesoAcumulado();

        // Algoritmo recursivo
        backtracking(0,0);

        // Solución
        return new Solucion(mejorSolucion, pesoTotalPaquetes-mayorPesoAsignado, estadosGenerados);
    }

    /*
        La estrategia implementada procesa los paquetes uno por uno. Para cada paquete se consideran todas las decisiones posibles: 
        asignarlo a cualquiera de los camiones que cumpla las restricciones de capacidad y refrigeración, o bien dejarlo sin asignar.

        Antes de iniciar la búsqueda, los paquetes se ordenan de forma descendente según su peso.
        Esto permite considerar primero los paquetes más pesados, que suelen ser los más difíciles de asignar debido a las restricciones de capacidad de los camiones.

        Durante la exploración se mantiene una solución parcial que registra qué paquetes fueron asignados a cada camión y la capacidad disponible restante de cada uno.

        Además, se incorporó una poda para reducir el espacio de búsqueda. Antes de continuar explorando una rama, 
        se calcula el peso máximo que aún podría agregarse con los paquetes restantes. 
        Si incluso asignando todos esos paquetes no es posible superar la mejor solución encontrada hasta el momento, la rama se descarta sin seguir explorándola.

        Complejidad temporal:

        En el peor caso, para cada paquete se generan hasta C+1 alternativas, 
        donde C es la cantidad de camiones disponibles, mas la opción de dejarlo sin asignar. 
        Por esto, el árbol de búsqueda puede contener hasta (C+1)^P nodos, donde P es la cantidad de paquetes. 
        Es decir, la complejidad temporal del algoritmo es O((C+1)^P), equivalente a O(C^P).
    */
    private void backtracking(int indicePaquete, int pesoAsignado) {
        
        estadosGenerados++;

        if(indicePaquete == paquetes.size()) {
            if(pesoAsignado > mayorPesoAsignado) {
                mayorPesoAsignado = pesoAsignado;
                guardarMejorSolucion();
            }
            return;
        }

        // Poda en base al peso que queda por asignar
        if(pesoAsignado + pesoAcumulado[indicePaquete] <= mayorPesoAsignado) {
            return;
        }

        Paquete p = paquetes.get(indicePaquete);
        for(Camion c : camiones) {
            if(puedeAsignarse(p,c)) {
                capacidadDisponible.put(c, capacidadDisponible.get(c) - p.getPesoKg());
                solucionActual.get(c).add(p);
                backtracking(indicePaquete+1, pesoAsignado + p.getPesoKg());
                solucionActual.get(c).remove(p);
                capacidadDisponible.put(c, capacidadDisponible.get(c) + p.getPesoKg());
            }
        }
        // Rama en la cual el paquete no es asignado(para no perder posibles soluciónes)
        backtracking(indicePaquete+1, pesoAsignado);
    }

    // Genera un arreglo con los pesos acumulados de los paquetes, esto ayuda a tener una complejidad de O(1) a la hora de hacer la poda
    private int[] obtenerPesoAcumulado() {
        int[] salida = new int[paquetes.size()];
        
        salida[paquetes.size()-1] = paquetes.get(paquetes.size()- 1).getPesoKg();

        for(int i = paquetes.size()-2; i >= 0; i--) {
            salida[i] = paquetes.get(i).getPesoKg() + salida[i+1];
        }
        return salida;
    }

    // Comprueba si un paquete puede asignarse a un camión
    private boolean puedeAsignarse(Paquete p, Camion c) {
        if(p.contieneAlimentos() && !c.estaRefrigerado()) return false;
        return p.getPesoKg() <= capacidadDisponible.get(c);
    }

    private void guardarMejorSolucion() {
        mejorSolucion = new HashMap<>();
        for(Camion c : solucionActual.keySet()) {
            mejorSolucion.put(c, new ArrayList<>(solucionActual.get(c)));
        }
    }
}
