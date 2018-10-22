/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBase.Managers;

import ClasesBase.Alumno;
import ClasesBase.Retiro;
import ClasesBase.Tutor;
import DAO.ImplementacionDAO;
import java.util.Set;
import java.util.regex.*;

/**
 *
 * @author Facu SSD
 */
public class ManagerTutor {
    
    private Tutor tutor;
    private String ocupacion;
    private String tipoDni;
    private long telefonoPersonal;
    private long telefonoTrabajo;
    private String relacion;
    private Set<Alumno> atutorados;
    private Set<Retiro> retiros;
    private int dni;
    private String apellidoYNombre;
    private static ManagerTutor manager = null;
    
    /**
     * @param tutor puede ser null
     * @param ocupacion
     * @param tipoDni
     * @param telefonoPersonal
     * @param telefonoTrabajo
     * @param relacion
     * @param atutorados
     * @param retiros
     * @param dni
     * @param apellidoYNombre 
     */
    private ManagerTutor(Tutor tutor, String ocupacion, String tipoDni, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre) {
        this.tutor = tutor;
        this.ocupacion = ocupacion;
        this.tipoDni = tipoDni;
        this.telefonoPersonal = telefonoPersonal;
        this.telefonoTrabajo = telefonoTrabajo;
        this.relacion = relacion;
        this.atutorados = atutorados;
        this.retiros = retiros;
        this.dni = dni;
        this.apellidoYNombre = apellidoYNombre;
    }
    
    private ManagerTutor() {
        this.tutor = null;
        this.ocupacion = null;
        this.tipoDni = null;
        this.telefonoPersonal = 0;
        this.telefonoTrabajo = 0;
        this.relacion = null;
        this.atutorados = null;
        this.retiros = null;
        this.dni = 0;
        this.apellidoYNombre = null;
    }
    
    public ManagerTutor GetManager(Tutor tutor, String ocupacion, String tipoDni, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre){
        if(manager == null)
            manager = new ManagerTutor(tutor, ocupacion, tipoDni, telefonoPersonal, telefonoTrabajo, relacion, atutorados, retiros, dni, apellidoYNombre);
        return manager;
    }
    
    public ManagerTutor GetManager(){
        if(manager == null)
            manager = new ManagerTutor();
        return manager;
    }

    public Tutor nuevoTutor (String ocupacion, String tipoDni, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre) throws IllegalArgumentException, Exception{
        Pattern patron = Pattern.compile("[a-zA-Z&&[ x0Bf ]&&[^0-9]]");
        Matcher encaja;
        
        encaja = patron.matcher(ocupacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Ocupacion contiene simbolos invalidos");
        
        encaja = patron.matcher(tipoDni);
        if(!encaja.matches())
            throw IllegalArgumentException("tipo DNI contiene simbolos invalidos");
        
        encaja = patron.matcher(relacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Relacion contiene simbolos invalidos");
        
        encaja = patron.matcher(ocupacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Apellido y nombre contiene simbolos invalidos");
        
        tutor = new Tutor(ocupacion, tipoDni, telefonoPersonal, telefonoTrabajo, relacion, atutorados, retiros, dni, apellidoYNombre);
        
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        dao.altaTutor(tutor);
        
        return tutor;
    }

    private Exception IllegalArgumentException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellidoYNombre() {
        return apellidoYNombre;
    }

    public void setApellidoYNombre(String apellidoYNombre) {
        this.apellidoYNombre = apellidoYNombre;
    }
    
}

