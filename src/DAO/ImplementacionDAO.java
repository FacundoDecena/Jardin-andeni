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
import ClasesBase.Tutor;
import java.sql.SQLException;
import java.sql.Statement;
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
            Map sala = alumno.getSalas();
            Set clave = sala.keySet();
            s.execute("INSERT INTO ALUMNOS VALUES("
                    + alumno.getDni()+ "" 
                    + ""
                    + ""
                    + ");");
            s.execute("CREATE TABLE ES_ALUMNO(" +
                  "DNIALUMNO INT NOT NULL," +
                  "IDSALA INT NOT NULL," +
                  "ANOLECTIVO INT NOT NULL," +
                  "PRIMARY KEY (DNIALUMNO,IDSALA)," +
                  "FOREIGN KEY (IDSALA) REFERENCES SALA(IDSALA)," +
                  "FOREIGN KEY (DNIALUMNO) REFERENCES ALUMNO(DNI))" 
        );
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
