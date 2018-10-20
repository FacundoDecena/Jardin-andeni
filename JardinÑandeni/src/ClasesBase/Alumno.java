package ClasesBase;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

public class Alumno extends Persona {    
    private Calendar fechaDeNacimiento;
    private String lugarNacimiento;
    private String domicilio;
    private long telefono;
    private boolean controlMedico;
    private boolean vacunas;
    private boolean controlNatacion;
    private boolean traeMateriales;
    private String otrosDatos;
    private Set<Alumno> hermanos;
    private Set<Tutor> tutores;
    private Set<Pago> pagos;
    private Map<Integer,Sala> salas;
    private Set<RegistroAsistencia> ra;

    public Alumno(Calendar fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre) {
        super(dni, apellidoYNombre);
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.controlMedico = controlMedico;
        this.vacunas = vacunas;
        this.controlNatacion = controlNatacion;
        this.traeMateriales = traeMateriales;
        this.otrosDatos = otrosDatos;
        this.hermanos = hermanos;
        this.tutores = tutores;
        this.pagos = pagos;
        this.salas = salas;
        this.ra = ra;
    }


    public Alumno() {
    }

    public Calendar getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Calendar fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public boolean isControlMedico() {
        return controlMedico;
    }

    public void setControlMedico(boolean controlMedico) {
        this.controlMedico = controlMedico;
    }

    public boolean isVacunas() {
        return vacunas;
    }

    public void setVacunas(boolean vacunas) {
        this.vacunas = vacunas;
    }

    public boolean isControlNatacion() {
        return controlNatacion;
    }

    public void setControlNatacion(boolean controlNatacion) {
        this.controlNatacion = controlNatacion;
    }

    public boolean isTraeMateriales() {
        return traeMateriales;
    }

    public void setTraeMateriales(boolean traeMateriales) {
        this.traeMateriales = traeMateriales;
    }
    
    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }

    public Set<Alumno> getHermanos() {
        return hermanos;
    }

    public void setHermanos(Set<Alumno> hermanos) {
        this.hermanos = hermanos;
    }

    public Set<Tutor> getTutores() {
        return tutores;
    }

    public void setTutores(Set<Tutor> tutores) {
        this.tutores = tutores;
    }

    public Set<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Pago> pagos) {
        this.pagos = pagos;
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
