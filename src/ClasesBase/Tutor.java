package ClasesBase;

import java.util.Set;

public class Tutor extends Persona {
    private String ocupacion;
    private String tipoDni;
    private long telefonoPersonal;
    private long telefonoTrabajo;
    private String relacion;
    private Set<Alumno> atutorados;
    private Set<Retiro> retiros;

    public Tutor(String ocupacion, String tipoDni, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre) {
        super(dni, apellidoYNombre);
        this.ocupacion = ocupacion;
        this.tipoDni = tipoDni;
        this.telefonoPersonal = telefonoPersonal;
        this.telefonoTrabajo = telefonoTrabajo;
        this.relacion = relacion;
        this.atutorados = atutorados;
        this.retiros = retiros;
    }

    public Tutor() {
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTipoDni() {
        return tipoDni;
    }

    public void setTipoDni(String tipoDni) {
        this.tipoDni = tipoDni;
    }

    public long getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(long telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public long getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(long telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Set<Alumno> getAtutorados() {
        return atutorados;
    }

    public void setAtutorados(Set<Alumno> atutorados) {
        this.atutorados = atutorados;
    }

    public Set<Retiro> getRetiros() {
        return retiros;
    }

    public void setRetiros(Set<Retiro> retiros) {
        this.retiros = retiros;
    }
    
    

    
}
