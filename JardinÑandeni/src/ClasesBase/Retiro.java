package ClasesBase;

import java.sql.Time;

public class Retiro{
    private Time hora;
    private String motivo;
    private Asistencia asistencia;
    private Tutor tutor;

    public Retiro(Time hora, String motivo, Asistencia asistencia, Tutor tutor) {
        this.hora = hora;
        this.motivo = motivo;
        this.asistencia = asistencia;
        this.tutor = tutor;
    }

    public Retiro(Time hora, String motivo, Asistencia asistencia) {
        this.hora = hora;
        this.motivo = motivo;
        this.asistencia = asistencia;
    }

    public Retiro() {
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
    
}
