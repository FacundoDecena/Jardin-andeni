package ClasesBase.Managers;

import ClasesBase.Alumno;
import ClasesBase.Pago;
import ClasesBase.RegistroAsistencia;
import ClasesBase.Sala;
import ClasesBase.Tutor;
import DAO.ImplementacionDAO;
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
        
        Alumno alumno = new Alumno(fechaDeNacimiento, lugarNacimiento, domicilio, telefono, controlMedico, vacunas, controlNatacion, traeMateriales, otrosDatos, hermanos, tutores, pagos, salas, ra, dni, apellidoYNombre);
        
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        dao.altaAlumno(alumno);
        
        return alumno;
    }

    public static List<Alumno> obtenerTodosAlumno(){
        return ImplementacionDAO.getDAO().obtenerTodosAlumno();
    }
    
    public void actualizarAñoLectivo(int dni, int idSala, int añoLectivo){
        ImplementacionDAO.getDAO().agregarAñoLectivo(dni, idSala, añoLectivo);
    }
    
    
    private Exception IllegalArgumentException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }
}
