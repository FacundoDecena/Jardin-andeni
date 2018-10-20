package ClasesBase;

import java.util.Calendar;

public class Licencia {
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private String motivo;
    private Calendar fechaSolicitud;
    private int idLicencia;

    public Licencia(Calendar fechaInicio, Calendar fechaFin, String motivo, Calendar fechaSolicitud, int idLicencia) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
        this.fechaSolicitud = fechaSolicitud;
        this.idLicencia = idLicencia;
    }

    public Licencia() {
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Calendar getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Calendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }
    
    
    
}
