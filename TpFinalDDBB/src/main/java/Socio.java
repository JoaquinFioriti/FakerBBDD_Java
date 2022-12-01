import java.sql.Date;

public class Socio {
    private int idSocio;
    private int nroBase;
    private int nroOrden;
    private int idCategoria;
    private String num_celular;
    private String nombre;
    private String apellido;
    private String email;
    private Date fecha_nacimiento;
    private boolean titularidad;

    public Socio(int idSocio, int nroBase, int nroOrden, int idCategoria, String num_celular, String nombre, String apellido, String email, Date fecha_nacimiento, boolean titularidad) {
        this.idSocio = idSocio;
        this.nroBase = nroBase;
        this.nroOrden = nroOrden;
        this.idCategoria = idCategoria;
        this.num_celular = num_celular;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.titularidad = titularidad;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getNroBase() {
        return nroBase;
    }

    public void setNroBase(int nroBase) {
        this.nroBase = nroBase;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(int nroOrden) {
        this.nroOrden = nroOrden;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public boolean isTitularidad() {
        return titularidad;
    }

    public void setTitularidad(boolean titularidad) {
        this.titularidad = titularidad;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
