import java.sql.Date;

public class SeInscribe {

    Cronograma cronograma;
    int id_socio;
    Date fecha_inscripcion;

    public SeInscribe(Cronograma cronograma, int id_socio, Date fecha_inscripcion) {
        this.cronograma = cronograma;
        this.id_socio = id_socio;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    @Override
    public String toString() {
        return "SeInscribe{" +
                "cronograma=" + cronograma +
                ", id_socio=" + id_socio +
                ", fecha_inscripcion=" + fecha_inscripcion +
                '}';
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }

    public int getId_socio() {
        return id_socio;
    }

    public void setId_socio(int id_socio) {
        this.id_socio = id_socio;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }
}
