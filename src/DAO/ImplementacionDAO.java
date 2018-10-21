package DAO;

import BaseDeDatos.ConexionBD;
import ClasesBase.Alumno;
import ClasesBase.Pago;
import ClasesBase.RegistroAsistencia;
import ClasesBase.Sala;
import ClasesBase.Tutor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ImplementacionDAO implements DAO {

    @Override//FALTA LA PARTE DE HERMANOS
    public void altaAlumno(Alumno alumno) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO PERSONA VALUES("+alumno.getDni()+",'"+alumno.getApellidoYNombre()+"','1')");
            s.execute("INSERT INTO ALUMNO VALUES (" + alumno.getDni() + ",'"+
                                                      alumno.getFechaDeNacimiento() + "', '" +
                                                      alumno.getLugarNacimiento() + "', " +
                                                      alumno.isControlMedico() + ", " +
                                                      alumno.isVacunas() + ", " +
                                                      alumno.isControlNatacion() + ", '" +
                                                      alumno.getDomicilio() + "', " +
                                                      alumno.isTraeMateriales() + ", " +
                                                      alumno.getTelefono() + ", '" +
                                                      alumno.getOtrosDatos() +
                                                  "')");
            Map mapSala = alumno.getSalas();
            List listSala  = (List) mapSala.keySet();
            int cl;
            cl = (int) listSala.get(0);
            Sala sala = (Sala) mapSala.get(cl);
            s.execute("INSERT INTO ES_ALUMNO VALUES("
                    + alumno.getDni()+ ", " 
                    + sala.getIdSala() + ", "
                    + cl + ""
                    + ")");
            List setRA = (List)alumno.getRa();
            RegistroAsistencia ra = (RegistroAsistencia) setRA.get(0);
            altaRegistroAsistencia(ra);
            Set<Tutor> tutores = alumno.getTutores();
            Iterator i = tutores.iterator();
            while(i.hasNext()){
                altaTutor((Tutor)i.next());
            }
            
            s.close();
        } catch (SQLException ex) {
            System.err.println("Algo ha fallado mijo, en el alta Alumno, fijese");
        }
    }

    @Override
    public void altaPago(Pago pago) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            int tipoPago;
            switch(pago.getTipoPago()){
                case "INSCRIPCION": tipoPago = 1; break;
                case "MATERIALES": tipoPago = 2; break;
                case "CUOTA": tipoPago = 3; break;
                case "SAC": tipoPago = 4; break;
                case "NATACION": tipoPago =5; break;
                default: tipoPago = 0;
            }
            s.execute("INSERT INTO PAGO VALUES("+pago.getIdPago()+",'"+pago.getFecha().toString()+"',"+pago.getMontoTotal()
                      +","+pago.getMontoPagado()+",'"+pago.getPeriodo()+"',"+tipoPago+","+pago.getCuotas()+")");
            Iterator i = pago.getAlumnos().iterator();
            while(i.hasNext()){
                Alumno a = (Alumno) i.next();
                s.execute("INSERT INTO CORRESPONDE_PAGO VALUES("+pago.getIdPago()+","+a.getDni()+")");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void altaRegistroAsistencia(RegistroAsistencia registroAsistencia) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO REGISTROASISTENCIA VALUES("+registroAsistencia.getIdRA()+","+registroAsistencia.getPersona().getDni()+
                      ","+registroAsistencia.getAñoLectivo()+")");
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void altaTutor(Tutor tutor) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO PERSONA VALUES("+tutor.getDni()+",'"+tutor.getApellidoYNombre()+"','2')");
            s.execute("INSERT INTO TUTOR VALUES('"+tutor.getTipoDni()+"',"+tutor.getDni()+",'"+tutor.getOcupacion()+"','"
                      +tutor.getRelacion()+"',"+tutor.getTelefonoPersonal()+","+tutor.getTelefonoTrabajo()+")");
            Iterator i = tutor.getAtutorados().iterator();
            while(i.hasNext()){
                Alumno a = (Alumno)i.next();
                s.execute("INSERT INTO ES_TUTOR VALUES("+tutor.getDni()+","+a.getDni()+")");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override//MODIFICA SOLO LOS CAMPOS PROPIOS, NO LAS RELACIONES
    public void modificarAlumno(Alumno alumno) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("UPDATE PERSONA SET APELLIDOYNOMBRE="+alumno.getApellidoYNombre()+"WHERE DNI="+alumno.getDni());
            s.execute("UPDATE ALUMNO"+
                      "SET FECHADENACIMIENTO='"+alumno.getFechaDeNacimiento().getDate()+"', LUGARDENACIMIENTO='"+alumno.getLugarNacimiento()+
                      "', CONTROLMEDICO="+alumno.isControlMedico()+", VACUNAS="+alumno.isVacunas()+", CONTROLNATACION="+alumno.isVacunas()+
                      ", DOMICILIO='"+alumno.getDomicilio()+"', TRAEMATERIALES="+alumno.isTraeMateriales()+", TELEFONO="+alumno.getTelefono()+
                      ", OTROSDATOS='"+alumno.getOtrosDatos()+"' "+
                      " WHERE DNI="+alumno.getDni());  
        } catch (SQLException ex) {ex.printStackTrace();}
    }

    @Override//NO GUARDA NADA EN LOS SETS
    public Tutor obtenerTutor(int dni) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            Statement sAux = c.createStatement();
            ResultSet rsTutor = s.executeQuery("SELECT * FROM TUTOR WHERE DNI="+dni);
            rsTutor.next();
            ResultSet rsNombre = sAux.executeQuery("SELECT APELLIDOYNOMBRE FROM PERSONA WHERE DNI="+dni);
            rsNombre.next();
            String apellidoYNombre = rsNombre.getString("APELLIDOYNOMBRE");
            Tutor tutor = new Tutor(rsTutor.getString("OCUPACION"),rsTutor.getString("TIPODNI"),
                                    rsTutor.getLong("TELEFONOPERSONAL"),rsTutor.getLong("TELEFONOTRABAJO"),
                                    rsTutor.getString("RELACION"),null,null,dni,apellidoYNombre);
            s.close();
            sAux.close();
            return tutor;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }

    @Override//NO GUARDA NADA EN LOS SETS
    public List obtenerTodosAlumno() {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet rsAlumno = null;
            rsAlumno = s.executeQuery("SELECT * FROM ALUMNO");
            List<Alumno> listaDeAlumnos = new ArrayList();
            Statement sAux = c.createStatement();
            while(rsAlumno.next()){
                int dni = rsAlumno.getInt("DNI");
                ResultSet rsNombre = sAux.executeQuery("SELECT APELLIDOYNOMBRE FROM PERSONA WHERE DNI="+dni);
                rsNombre.next();
                String apellidoYNombre = rsNombre.getString("APELLIDOYNOMBRE");
                Alumno alumno = new Alumno(rsAlumno.getDate("FECHADENACIMIENTO"),rsAlumno.getString("LUGARDENACIMIENTO"),
                                           rsAlumno.getString("DOMICILIO"),rsAlumno.getLong("TELEFONO"),
                                           rsAlumno.getBoolean("CONTROLMEDICO"),rsAlumno.getBoolean("VACUNAS"),
                                           rsAlumno.getBoolean("CONTROLNATACION"),rsAlumno.getBoolean("TRAEMATERIALES"),
                                           rsAlumno.getString("OTROSDATOS"),null,null,null,null,null,dni,apellidoYNombre);
                listaDeAlumnos.add(alumno);
            }
            s.close();
            sAux.close();
            return listaDeAlumnos;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }

    @Override//PARA EL CASO DE LA INSCRIPCION CON ALUMNO YA CARGADO EN EL SISTEMA
    public void agregarAñoLectivo(int dni, int idSala, int añoLectivo) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO ES_ALUMNO VALUES("+dni+","+idSala+","+añoLectivo+")");
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
