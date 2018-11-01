/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBase.Managers;

import ClasesBase.Alumno;
import ClasesBase.Retiro;
import ClasesBase.Tutor;
import DAO.ImplementacionDAO;
import java.util.Set;
import java.util.regex.*;

/**
 *
 * @author Facu SSD
 */
public class ManagerTutor {
       
    private static ManagerTutor manager;
    
    private ManagerTutor() {
        
    }
    
    
    public static ManagerTutor getManager(){
        if(manager == null)
            manager = new ManagerTutor();
        return manager;
    }

    public Tutor nuevoTutor (String ocupacion, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre) throws IllegalArgumentException, Exception{
        Tutor tutor;
        
        Pattern patron = Pattern.compile("([a-zA-Z]*[ \\t\\n\\x0b\\r\\f]*)+");
        Matcher encaja;
        
        encaja = patron.matcher(ocupacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Ocupacion contiene simbolos invalidos");
        
        
        encaja = patron.matcher(relacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Relacion contiene simbolos invalidos");
        
        encaja = patron.matcher(ocupacion);
        if(!encaja.matches())
            throw IllegalArgumentException("Apellido y nombre contiene simbolos invalidos");
                
        ImplementacionDAO dao = ImplementacionDAO.getDAO();
        
        tutor = dao.nuevoTutor(ocupacion, telefonoPersonal, telefonoTrabajo, relacion, atutorados, retiros, dni, apellidoYNombre);

        dao.altaTutor(tutor);
        
        return tutor;
    }

    private Exception IllegalArgumentException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }
    
}