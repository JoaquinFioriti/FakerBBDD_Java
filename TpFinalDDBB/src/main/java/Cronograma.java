import java.sql.Time;

public class Cronograma {

    private int id_actividad;
    private int id_area;
    private int id_profesional;
    private String dia;
    private Time hora_inicio;
    private Time hora_fin;
    private Object periodo;

    public Cronograma(int id_actividad, int id_area, int id_profesional, String dia, Time hora_inicio, Time hora_fin, Object periodo) {
        this.id_actividad = id_actividad;
        this.id_area = id_area;
        this.id_profesional = id_profesional;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Cronograma{" +
                "id_actividad=" + id_actividad +
                ", id_area=" + id_area +
                ", id_profesional=" + id_profesional +
                ", dia='" + dia + '\'' +
                ", hora_inicio=" + hora_inicio +
                ", hora_fin=" + hora_fin +
                ", periodo=" + periodo +
                '}';
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Object getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Object periodo) {
        this.periodo = periodo;
    }
}
