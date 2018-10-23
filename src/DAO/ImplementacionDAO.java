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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ImplementacionDAO implements DAO {

    private Alumno alumno;
    private Pago pago;
    private RegistroAsistencia registroAsistencia;
    private Tutor tutor;
    private Sala sala;
    private static ImplementacionDAO creado = null;
    
    private ImplementacionDAO(){
        alumno = null;
        pago = null;
        registroAsistencia = null;
        tutor = null;
    }
    
    public static ImplementacionDAO getDAO(){
        if (creado == null){
            creado = new ImplementacionDAO();
        }
        return creado;
    }
    
    @Override//FALTA LA PARTE DE HERMANOS
    public void altaAlumno(Alumno palumno) {
        try {
            this.alumno = palumno;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            Calendar r = new GregorianCalendar();
            String fecha = "", parcial;
            r.setTime(alumno.getFechaDeNacimiento());
            parcial = String.valueOf(r.get(Calendar.YEAR));
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.MONTH));
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.DATE));
            fecha = fecha.concat(parcial);
            s.execute("INSERT INTO PERSONA VALUES("+alumno.getDni()+",'"+alumno.getApellidoYNombre()+"',1)");
            s.execute("INSERT INTO ALUMNO VALUES (" + alumno.getDni() + ",'"+
                                                      fecha + "', '" +
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
            Set setCLs  = mapSala.keySet();
            int cl = -1;
            Sala sala = null;
            Iterator i = setCLs.iterator();
            while(i.hasNext()){
                cl = (Integer)i.next();
                sala = (Sala)mapSala.get(cl);
            }
                    
            s.execute("INSERT INTO ES_ALUMNO VALUES("
                    + alumno.getDni()+ ", " 
                    + sala.getIdSala() + ", "
                    + cl + ""
                    + ")");
            
            RegistroAsistencia ra = new RegistroAsistencia(cl, alumno, new HashSet(), new HashSet());
            /*Set setRA = alumno.getRa(); YA QUEDA LISTO PARA ALUMNOS YA INSCRIPTOS
            RegistroAsistencia ra = null;
            i = setRA.iterator();
            Date year = new Date();
            r.setTime(year);
            int thisYear = r.get(Calendar.YEAR);
            while(i.hasNext()){
                ra = (RegistroAsistencia)i.next();
                if(ra.getAñoLectivo()== thisYear)
                    break;
            }*/
            altaRegistroAsistencia(ra);
            Set<Tutor> tutores = alumno.getTutores();
            Iterator j = tutores.iterator();
            while(j.hasNext()){
                altaTutor((Tutor)j.next());
            }
            
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void altaPago(Pago ppago) {
        try {
            this.pago = ppago;
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
    public void altaRegistroAsistencia(RegistroAsistencia pregistroAsistencia) {
        registroAsistencia = pregistroAsistencia;
        int idra = registroAsistencia.getIdRA();
        int dni = registroAsistencia.getPersona().getDni();
        int cl = registroAsistencia.getAñoLectivo();
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO REGISTROASISTENCIA VALUES("+idra+","+dni+
                      ","+cl+")");
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void altaTutor(Tutor ptutor) {
        try {
            tutor = ptutor;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO PERSONA VALUES("
                    +tutor.getDni()+",'"
                    +tutor.getApellidoYNombre()+"',2)");
            s.execute("INSERT INTO TUTOR VALUES('"
                    +tutor.getTipoDni()+"',"
                    +tutor.getDni()+",'"
                    +tutor.getOcupacion()+"','"
                    +tutor.getRelacion()+"',"
                    +tutor.getTelefonoPersonal()+","
                    +tutor.getTelefonoTrabajo()
                    +")");
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
    public void modificarAlumno(Alumno palumno) {
        try {
            alumno = palumno;
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
            tutor = new Tutor(rsTutor.getString("OCUPACION"),rsTutor.getString("TIPODNI"),
                                    rsTutor.getLong("TELEFONOPERSONAL"),rsTutor.getLong("TELEFONOTRABAJO"),
                                    rsTutor.getString("RELACION"),null,null,dni,apellidoYNombre);
            s.close();
            sAux.close();
            return tutor;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }
    
    @Override//NO GUARDA NADA EN LOS SETS
    public Sala obtenerSala(int idSala) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet rsSala = s.executeQuery("SELECT * FROM SALA WHERE IDSALA="+idSala);
            rsSala.next();
            sala = new Sala(rsSala.getInt("EDAD"),rsSala.getString("TURNO"),
                                    rsSala.getString("COLOR"),rsSala.getInt("IDSALA"),new HashSet(),new HashSet());
            s.close();
            return sala;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }

    @Override//NO GUARDA NADA EN LOS SETS
    public List obtenerTodosAlumno() {
        try {
            int dni, idSala, añoLectivo;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            List<Alumno> listaDeAlumnos = new ArrayList();
            List<Sala> listaSalas = new ArrayList<>();
            Map<Integer,Sala> mapaSalas;
            ResultSet rsAlumno = null;
            rsAlumno = s.executeQuery("SELECT * FROM ALUMNO");
            Statement sAux = c.createStatement();
            ResultSet rsSala = sAux.executeQuery("SELECT IDSALA FROM SALA");
            while(rsSala.next()){
                idSala = rsSala.getInt("IDSALA");
                listaSalas.add(obtenerSala(idSala));
            }
            while(rsAlumno.next()){
                dni = rsAlumno.getInt("DNI");
                ResultSet rsNombre = sAux.executeQuery("SELECT APELLIDOYNOMBRE FROM PERSONA WHERE DNI="+dni);
                rsNombre.next();
                String apellidoYNombre = rsNombre.getString("APELLIDOYNOMBRE");
                rsSala = sAux.executeQuery("SELECT IDSALA,ANOLECTIVO FROM ES_ALUMNO WHERE DNIALUMNO="+dni);
                mapaSalas = new HashMap();
                while(rsSala.next()){
                    añoLectivo = rsSala.getInt("ANOLECTIVO");
                    idSala = rsSala.getInt("IDSALA");
                    Iterator i = listaSalas.listIterator();
                    while(i.hasNext()){
                        Sala sala = (Sala) i.next();
                        if(sala.getIdSala() == idSala){
                            mapaSalas.put(añoLectivo,sala);
                        }
                    }
                }
                alumno = new Alumno(rsAlumno.getDate("FECHADENACIMIENTO"),rsAlumno.getString("LUGARDENACIMIENTO"),
                                           rsAlumno.getString("DOMICILIO"),rsAlumno.getLong("TELEFONO"),
                                           rsAlumno.getBoolean("CONTROLMEDICO"),rsAlumno.getBoolean("VACUNAS"),
                                           rsAlumno.getBoolean("CONTROLNATACION"),rsAlumno.getBoolean("TRAEMATERIALES"),
                                           rsAlumno.getString("OTROSDATOS"),null,null,null,mapaSalas,null,dni,apellidoYNombre);
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
