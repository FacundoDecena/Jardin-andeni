package ClasesBase;

import java.sql.Time;

public class Tardanza {
    private Time tiempoTardanza;
    private Asistencia asistencia;

    public Tardanza(Time tiempoTardanza, Asistencia asistencia) {
        this.tiempoTardanza = tiempoTardanza;
        this.asistencia = asistencia;
    }

    public Tardanza() {
    }

    public Time getTiempoTardanza() {
        return tiempoTardanza;
    }

    public void setTiempoTardanza(Time tiempoTardanza) {
        this.tiempoTardanza = tiempoTardanza;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

}
