package ClasesBase;

import java.util.Set;

public class RegistroAsistencia {
    private int añoLectivo;
    private int idRA;
    private static int cantidad = 0;
    private Persona persona;
    private Set<Falta> faltas;
    private Set<Asistencia> asistencias;

    public RegistroAsistencia(int añoLectivo/*, int idRA*/, Persona persona, Set<Falta> faltas, Set<Asistencia> asistencias) {
        this.añoLectivo = añoLectivo;
        this.idRA = ++cantidad;
        this.persona = persona;
        this.faltas = faltas;
        this.asistencias = asistencias;
        
    }

    public RegistroAsistencia() {
        cantidad++;
        this.idRA=cantidad;
    }

    public int getAñoLectivo() {
        return añoLectivo;
    }

    public void setAñoLectivo(int añoLectivo) {
        this.añoLectivo = añoLectivo;
    }

    public int getIdRA() {
        return idRA;
    }

    public void setIdRA(int idRA) {
        this.idRA = idRA;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<Falta> getFaltas() {
        return faltas;
    }

    public void setFaltas(Set<Falta> faltas) {
        this.faltas = faltas;
    }

    public Set<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Set<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

   
    
    
}
