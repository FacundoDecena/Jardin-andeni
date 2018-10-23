package ClasesBase;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Pago {
    private Date fecha;
    private String tipoPago;
    private String periodo;
    private int cuotas;
    private float montoPagado;
    private float montoTotal;
    private int idPago;
    private Set<Alumno> alumnos;

    public Pago(Date fecha, String tipoPago, String periodo, int cuotas ,float montoPagado, float montoTotal, int idPago, Set<Alumno> alumnos) {
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.periodo = periodo;
        this.cuotas = cuotas;
        this.montoPagado = montoPagado;
        this.montoTotal = montoTotal;
        this.idPago = idPago;
        this.alumnos = alumnos;
    }

    public Pago() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    
    public float getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    
        
}
