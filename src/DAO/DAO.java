
package DAO;

import ClasesBase.*;
import java.util.List;



public interface DAO{
    //Altas
    public void altaAlumno(Alumno alumno);
    //public void altaAsistencia(Asistencia asistencia);
    //public void altaDocente(Docente docente);
    //public void altaFalta(Falta falta);
    //public void altaLicencia(Licencia licencia);
    public void altaPago(Pago pago);
    //public void altaPersona(Persona persona);
    public void altaRegistroAsistencia(RegistroAsistencia registroAsistencia);
    //public void altaRetiro(Retiro retiro);
    //public void altaSala(Sala sala);
    //public void altaTardanza(Tardanza tardanza);
    public void altaTutor(Tutor tutor);
    
    //Bajas
    //public void bajaAlumno(int dni);
    //public void bajaAsistencia(int idAsistencia);
    //public void bajaDocente(int dni);
    //public void bajaFalta(int idRA,Fecha fecha);
    //public void bajaLicencia(int idLicencia);
    //public void bajaPago(int idPago);
    //public void bajaPersona(int dni);
    //public void bajaRegistroAsistencia(int idRA);
    //public void bajaRetiro(int idAsistencia);
    //public void bajaSala(int idSala);
    //public void altaTardanza(int idAsistencia);
    //public void bajaTutor(int dni);
    
    //Modificaciones
    public void modificarAlumno(Alumno alumno);
    //public void modificarAsistencia(Asistencia asistencia);
    //public void modificarDocente(Docente docente);
    //public void modificarFalta(Falta falta);
    //public void modificarLicencia(Licencia licencia);
    //public void modificarPago(Pago pago);
    //public void modificarPersona(Persona persona);
    //public void modificarRegistroAsistencia(RegistroAsistencia registroAsistencia);
    //public void modificarRetiro(Retiro retiro);
    //public void modificarSala(Sala sala);
    //public void modificarTardanza(Tardanza tardanza);
    //public void modificarTutor(Tutor tutor);
    
    //ObtenerUno
    //public Alumno obtenerAlumno(int dni);
    //public Asistencia obtenerAsistencia(int idAsistencia);
    //public Docente obtenerDocente(int dni);
    //public Falta obtenerFalta(int idRA,Fecha fecha);
    //public Licencia obtenerLicencia(int idLicencia);
    //public Pago obtenerPago(int idPago);
    //public Persona obtenerPersona(int dni);
    //public RegistroAsistencia obtenerRegistroAsistencia(int idRA);
    //public Retiro obtenerRetiro(int idAsistencia);
    //public Sala obtenerSala(int idSala);
    //public Tardanza obtenerTardanza(int idAsistencia);
    public Tutor obtenerTutor(int dni);
    
    //ObtenerTodos
    public List obtenerTodosAlumno();
    //public List obtenerTodosAsistencia();
    //public List obtenerTodosDocente();
    //public List obtenerTodosFalta();
    //public List obtenerTodosLicencia();
    //public List obtenerTodosPago();
    //public List obtenerTodosPersona();
    //public List obtenerTodosRegistroAsistencia();
    //public List obtenerTodosRetiro();
    //public List obtenerTodosSala();
    //public List obtenerTodosTardanza();
    //public List obtenerTodosTutor();
    
    //Otros
    public void agregarAñoLectivo(int dni, int idSala, int añoLectivo);
    
    
    
    
}
