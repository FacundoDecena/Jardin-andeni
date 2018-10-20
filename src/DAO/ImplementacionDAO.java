/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BaseDeDatos.ConexionBD;
import ClasesBase.Alumno;
import ClasesBase.Pago;
import ClasesBase.Persona;
import ClasesBase.RegistroAsistencia;
import ClasesBase.Sala;
import ClasesBase.Tutor;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ImplementacionDAO implements DAO {

    @Override
    public void altaAlumno(Alumno alumno) {
        Statement s = ConexionBD.getConnection();
        try {
            s.execute("INSERT INTO PERSONA VALUES (" + alumno.getDni() + ", "+
                                                      alumno.getApellidoYNombre()+ ", " +
                                                      "1" + //Tipo de persona
                                                  ");");
            s.execute("INSERT INTO ALUMNO VALUES (" + alumno.getDni() + ", "+
                                                      alumno.getFechaDeNacimiento() + ", " +
                                                      alumno.getLugarNacimiento() + ", " +
                                                      alumno.isControlMedico() + ", " +
                                                      alumno.isVacunas() + ", " +
                                                      alumno.isControlNatacion() + ", " +
                                                      alumno.getDomicilio() + ", " +
                                                      alumno.isTraeMateriales() + ", " +
                                                      alumno.getTelefono() + ", " +
                                                      alumno.getOtrosDatos() +
                                                  ");");
            Map mapSala = alumno.getSalas();
            List listSala  = (List) mapSala.keySet();
            int cl;
            cl = (int) listSala.get(0);
            Sala sala = (Sala) mapSala.get(cl);
            s.execute("INSERT INTO ES_ALUMNO VALUES("
                    + alumno.getDni()+ ", " 
                    + sala.getIdSala() + ", "
                    + cl + ""
                    + ");");
            
            List setRA = (List)alumno.getRa();
            RegistroAsistencia ra = (RegistroAsistencia) setRA.get(0);
            altaRegistroAsistencia(ra);

        } catch (SQLException ex) {
            System.err.println("Algo ha fallado mijo, en el alta Alumno, fijese");
        }
    }

    @Override
    public void altaPago(Pago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altaPersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altaRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void altaTutor(Tutor tutor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarAlumno(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tutor obtenerTutor(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List obtenerTodosAlumno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
