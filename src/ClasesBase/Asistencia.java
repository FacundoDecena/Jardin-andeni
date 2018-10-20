package ClasesBase;

import java.util.Calendar;

public class Asistencia {
    private Calendar fecha;
    private int idAsistencia;
    private Tardanza tardanza;
    private Retiro retiro;
    private RegistroAsistencia ra;

    public Asistencia(Calendar fecha, int idAsistencia, Tardanza tardanza, Retiro retiro, RegistroAsistencia ra) {
        this.fecha = fecha;
        this.idAsistencia = idAsistencia;
        this.tardanza = tardanza;
        this.retiro = retiro;
        this.ra = ra;
    }

    public Asistencia() {
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Tardanza getTardanza() {
        return tardanza;
    }

    public void setTardanza(Tardanza tardanza) {
        this.tardanza = tardanza;
    }

    public Retiro getRetiro() {
        return retiro;
    }

    public void setRetiro(Retiro retiro) {
        this.retiro = retiro;
    }

    public RegistroAsistencia getRa() {
        return ra;
    }

    public void setRa(RegistroAsistencia ra) {
        this.ra = ra;
    }
    
    
    
}
