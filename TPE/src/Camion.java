public class Camion {
    private int id;
    private String patente;
    private boolean estaRefrigerado;
    private int capacidadKg;

    public Camion(int id, String patente, boolean estaRefrigerado, int capacidadKg) {
        this.id = id;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidadKg = capacidadKg;
    }

    public int getId() {
        return id;
    }
    public String getPatente() {
        return patente;
    }
    public boolean estaRefrigerado() {
        return estaRefrigerado;
    }
    public int getCapacidadKg() {
        return capacidadKg;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", estaRefrigerado=" + estaRefrigerado +
                ", capacidadKg=" + capacidadKg +
                '}';
    }
}
