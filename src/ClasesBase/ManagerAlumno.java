package ClasesBase;

import DAO.ImplementacionDAO;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;

public class ManagerAlumno{
    
    private Alumno alumno;
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
    private Map<Integer, Sala> salas;
    private Set<RegistroAsistencia> ra;
    private int dni;
    private String apellidoYNombre;

    public ManagerAlumno(Alumno alumno, Date fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre) {
        this.alumno = alumno;
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
        this.dni = dni;
        this.apellidoYNombre = apellidoYNombre;
    }
        
    /**
     * 
     * @param pfechaDeNacimiento
     * @param plugarNacimiento
     * @param pdomicilio
     * @param ptelefono
     * @param pcontrolMedico
     * @param pvacunas
     * @param pcontrolNatacion
     * @param ptraeMateriales
     * @param potrosDatos
     * @param phermanos
     * @param ptutores
     * @param ppagos
     * @param psalas
     * @param pra
     * @param pdni
     * @param papellidoYNombre
     * @return
     * @throws IllegalArgumentException 
     *      error de fecha: "El alumno es mayor de 6 años"
     *      error de lugar: "Lugar contiene simbolos invalidos"
     *      error de domicilio: "Domicilio contiene simbolos invalidos"
     *      error de Apellido y nombre: "Apellido y/o Nombre contiene simbolos invalidos"
     * @throws Exception 
     */
    public Alumno nuevoAlumno (Date pfechaDeNacimiento, String plugarNacimiento, String pdomicilio, long ptelefono, boolean pcontrolMedico, boolean pvacunas, boolean pcontrolNatacion, boolean ptraeMateriales, String potrosDatos, Set<Alumno> phermanos, Set<Tutor> ptutores, Set<Pago> ppagos, Map<Integer, Sala> psalas, Set<RegistroAsistencia> pra, int pdni, String papellidoYNombre) throws IllegalArgumentException, Exception{
        Date d = new Date();
        Pattern patron = Pattern.compile("[a-zA-Z&&[ x0Bf ]&&[^0-9]]");
        Matcher encaja;
        
        int a = d.getYear() - 6 - fechaDeNacimiento.getYear();
        if(a<0)
            throw IllegalArgumentException("El alumno es mayor de 6 años");
        
        encaja = patron.matcher(lugarNacimiento);
        if(!encaja.matches())
            throw IllegalArgumentException("Lugar contiene simbolos invalidos");
        
        encaja = patron.matcher(domicilio);
        if(!encaja.matches())
            throw IllegalArgumentException("Domicilio contiene simbolos invalidos");
        
        encaja = patron.matcher(apellidoYNombre);
        if(!encaja.matches())
            throw IllegalArgumentException("Apellido y/o Nombre contiene simbolos invalidos");
        
        alumno = new Alumno(pfechaDeNacimiento, plugarNacimiento, pdomicilio, ptelefono, pcontrolMedico, pvacunas, pcontrolNatacion, ptraeMateriales, potrosDatos, phermanos, ptutores, ppagos, psalas, pra, pdni, papellidoYNombre);
        
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        dao.altaAlumno(alumno);
        
        return alumno;
    }

    private Exception IllegalArgumentException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }
}
