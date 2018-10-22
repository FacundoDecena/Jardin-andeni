package ClasesBase.Managers;

import BaseDeDatos.ConexionBD;
import ClasesBase.Alumno;
import ClasesBase.Docente;
import ClasesBase.Sala;
import DAO.ImplementacionDAO;
import java.util.Set;

/**
 *
 * @author Facu SSD
 */
public class ManagerSala {
    private int edad;
    private String turno;
    private String color;
    private int idSala;
    private Set<Docente> docentes;
    private Set<Alumno> alumnos;
    private static ManagerSala manager;
    
    private ManagerSala(){
        edad = -1;
        turno = "";
        color = "";
        idSala = -1;
        docentes = null;
        alumnos = null;
    }
    
    public static ManagerSala getManagerSala(){
        if(manager == null)
            manager = new ManagerSala();
        return manager;
    }
    
    public Sala getSala(int idSala){
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        return dao.obtenerSala(idSala);
    }
    
}
