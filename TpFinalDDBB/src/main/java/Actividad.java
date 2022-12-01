public class Actividad {

    private int id;
    private String nombre;


    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public Actividad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
