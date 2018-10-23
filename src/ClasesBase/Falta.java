package ClasesBase;

import java.util.Calendar;

public class Falta {
    private Calendar fecha;
    private boolean estaJustificada;
    private RegistroAsistencia ra;

    public Falta(Calendar fecha, boolean estaJustificada, RegistroAsistencia ra) {
        this.fecha = fecha;
        this.estaJustificada = estaJustificada;
        this.ra = ra;
    }

    public Falta() {
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public boolean isEstaJustificada() {
        return estaJustificada;
    }

    public void setEstaJustificada(boolean estaJustificada) {
        this.estaJustificada = estaJustificada;
    }

    public RegistroAsistencia getRa() {
        return ra;
    }

    public void setRa(RegistroAsistencia ra) {
        this.ra = ra;
    }
    
    
}
