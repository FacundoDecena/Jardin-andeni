package ClasesBase.Managers;

import ClasesBase.Alumno;
import ClasesBase.Pago;
import ClasesBase.RegistroAsistencia;
import ClasesBase.Sala;
import ClasesBase.Tutor;
import DAO.ImplementacionDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;

public class ManagerAlumno{
    
    private static ManagerAlumno manager = null;
    
    private ManagerAlumno() {
    }
    
    public static ManagerAlumno getManager(){
        if(manager == null)
            manager = new ManagerAlumno();
        return manager;
    }
    
    public Alumno nuevoAlumno(Date fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre) throws IllegalArgumentException, Exception{
        Date d = new Date();
        Pattern patron = Pattern.compile("([a-zA-Z]*[ \\t\\n\\x0b\\r\\f]*)+");
        Matcher encaja;
        
        int a = new Integer(Alumno.getEdad(fechaDeNacimiento));
        if(a>5)
            throw IllegalArgumentException("El alumno es mayor de 5 años");
        
        encaja = patron.matcher(lugarNacimiento);
        if(!encaja.matches())
            throw IllegalArgumentException("Lugar contiene simbolos invalidos");
             
        encaja = patron.matcher(apellidoYNombre);
        if(!encaja.matches())
            throw IllegalArgumentException("Apellido y/o Nombre contiene simbolos invalidos");
        
        patron = Pattern.compile("([a-zA-Z]*[ \\t\\n\\x0b\\r\\f]*[0-9]*)+");
        encaja = patron.matcher(domicilio);
        if(!encaja.matches())
            throw IllegalArgumentException("Domicilio contiene simbolos invalidos");
                
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        Alumno alumno = dao.nuevoAlumno();
        
        alumno = this.cargarAlumno(fechaDeNacimiento, lugarNacimiento, domicilio, telefono, controlMedico, vacunas, controlNatacion, traeMateriales, otrosDatos, hermanos, tutores, pagos, salas, ra, dni, apellidoYNombre);
        
        return alumno;
    }
    
    public void altaAlumno(Alumno alumno)throws Exception{
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        if(!dao.altaAlumno(alumno)){
            throw SQLException(alumno.getApellidoYNombre()+" Ya esta cargada");
        }
        Set<Tutor> tutores = alumno.getTutores();
        //Si llegó aca, los tutores ya estan en la base de datos.
        Iterator i = tutores.iterator();
        while(i.hasNext()){
            Tutor t = (Tutor)i.next();
            int dniT = t.getDni();
            dao.nuevoEs_tutor(dniT,alumno.getDni());
        }
        Set<Alumno> hermanos = alumno.getHermanos();
        i = hermanos.iterator();
        while(i.hasNext()){
            Alumno h = (Alumno)i.next();
            int dniH = h.getDni();
            dao.nuevoEs_Hermano(dniH,alumno.getDni());
        }
    }

    public static List<Alumno> obtenerTodosAlumno(){
        return ImplementacionDAO.getDAO().obtenerTodosAlumno();
    }
    
    private Alumno cargarAlumno(Date fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre){
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        Alumno alumno = dao.nuevoAlumno();
        alumno.setFechaDeNacimiento(fechaDeNacimiento);
        alumno.setLugarNacimiento(lugarNacimiento);
        alumno.setDomicilio(domicilio);
        alumno.setTelefono(telefono);
        alumno.setControlMedico(controlMedico);
        alumno.setVacunas(vacunas);
        alumno.setControlNatacion(controlNatacion);
        alumno.setTraeMateriales(traeMateriales);
        alumno.setOtrosDatos(otrosDatos);
        alumno.setHermanos(hermanos);
        alumno.setTutores(tutores);
        alumno.setPagos(pagos);
        alumno.setSalas(salas);
        alumno.setRa(ra);
        alumno.setDni(dni);
        alumno.setApellidoYNombre(apellidoYNombre);
        return alumno;
    }
    
    /*public Alumno getAlumno(int dni){
        return ImplementacionDAO.getDAO().obtenerAlumno(dni);
    }*/
    
    public boolean actualizarAñoLectivo(int dni, int idSala, int añoLectivo){
        try{
            if(ImplementacionDAO.getDAO().agregarAñoLectivo(dni, idSala, añoLectivo))
            return true;
        }
        catch(Exception e){
            return false;
        }
        return false;
    }
    
    
    private Exception IllegalArgumentException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }
    
    private Exception SQLException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }
}
