package ClasesBase;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

public class Docente extends Persona {
    private long cuit;
    private String domicilio;
    private String titulo;
    private Calendar fechaIngresoDocencia;
    private Calendar fechaIngresoInstitucion;
    private long telefono;
    private Set<Licencia> licencias;
    private Map<Integer,Sala> salas;
    private Set<RegistroAsistencia> ra;

    public Docente(long cuit, String domicilio, String titulo, Calendar fechaIngresoDocencia, Calendar fechaIngresoInstitucion, long telefono, Set<Licencia> licencias, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre) {
        super(dni, apellidoYNombre);
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.titulo = titulo;
        this.fechaIngresoDocencia = fechaIngresoDocencia;
        this.fechaIngresoInstitucion = fechaIngresoInstitucion;
        this.telefono = telefono;
        this.licencias = licencias;
        this.salas = salas;
        this.ra = ra;
    }

    public Docente() {
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Calendar getFechaIngresoDocencia() {
        return fechaIngresoDocencia;
    }

    public void setFechaIngresoDocencia(Calendar fechaIngresoDocencia) {
        this.fechaIngresoDocencia = fechaIngresoDocencia;
    }

    public Calendar getFechaIngresoInstitucion() {
        return fechaIngresoInstitucion;
    }

    public void setFechaIngresoInstitucion(Calendar fechaIngresoInstitucion) {
        this.fechaIngresoInstitucion = fechaIngresoInstitucion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public Set<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Map<Integer, Sala> getSalas() {
        return salas;
    }

    public void setSalas(Map<Integer, Sala> salas) {
        this.salas = salas;
    }

    public Set<RegistroAsistencia> getRa() {
        return ra;
    }

    public void setRa(Set<RegistroAsistencia> ra) {
        this.ra = ra;
    }
    
    
    
    
}
