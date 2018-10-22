package ClasesBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;

public class Alumno extends Persona {    
    private Date fechaDeNacimiento;
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

    public Alumno(Date fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre) {
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

    public static String getEdad(Date fechaNacimiento) {
        if (fechaNacimiento != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            StringBuilder result = new StringBuilder();
            if (fechaNacimiento != null) {
                Calendar c = new GregorianCalendar();
                c.setTime(fechaNacimiento);
                result.append(calcularEdad(c));
            }
            return result.toString();
        }
        return "";
    }

    private static int calcularEdad(Calendar fechaNac) {
        Calendar today = Calendar.getInstance();
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }

    
    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
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
