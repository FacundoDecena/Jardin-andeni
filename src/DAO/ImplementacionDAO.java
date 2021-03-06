package DAO;

import BaseDeDatos.ConexionBD;
import ClasesBase.Alumno;
import ClasesBase.Pago;
import ClasesBase.RegistroAsistencia;
import ClasesBase.Retiro;
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
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;



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
    public boolean altaAlumno(Alumno palumno) {
        try {
            this.alumno = palumno;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            Calendar r = new GregorianCalendar();
            String fecha = "", parcial;
            r.setTime(alumno.getFechaDeNacimiento());
            parcial = String.valueOf(r.get(Calendar.YEAR));
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.MONTH)+1);
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
            /*Iterator j = tutores.iterator();
            while(j.hasNext()){
                altaTutor((Tutor)j.next());
            }
            */
            s.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Alumno nuevoAlumno(){
        return new Alumno();
    }
    
    public Alumno nuevoAlumno(Date fechaDeNacimiento, String lugarNacimiento, String domicilio, long telefono, boolean controlMedico, boolean vacunas, boolean controlNatacion, boolean traeMateriales, String otrosDatos, Set<Alumno> hermanos, Set<Tutor> tutores, Set<Pago> pagos, Map<Integer, Sala> salas, Set<RegistroAsistencia> ra, int dni, String apellidoYNombre){
        return new Alumno(fechaDeNacimiento, lugarNacimiento, domicilio, telefono, controlMedico, vacunas, controlNatacion, traeMateriales, otrosDatos, hermanos, tutores, pagos, salas, ra, dni, apellidoYNombre);
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
            Calendar r = new GregorianCalendar();
            String fecha = "", parcial;
            r.setTime(pago.getFecha().get(0));
            parcial = String.valueOf(r.get(Calendar.YEAR));
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.MONTH)+1);
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.DATE));
            fecha = fecha.concat(parcial);
            int idPago = (obtenerMaximoIdPago()+1);
            s.execute("INSERT INTO PAGO VALUES("+idPago+","+pago.getMontoTotal()
                      +","+pago.getMontoPagado()+",'"+pago.getPeriodo()+"',"+tipoPago+","+pago.getCuotas()+")");
            s.execute("INSERT INTO FECHA_PAGO VALUES("+idPago+",'"+fecha+"')");
            Iterator i = pago.getAlumnos().iterator();
            while(i.hasNext()){
                Alumno a = (Alumno) i.next();
                s.execute("INSERT INTO CORRESPONDE_PAGO VALUES("+idPago+","+a.getDni()+")");
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
            System.out.println("");
            
        }
    }

    public Tutor nuevoTutor(){
        return new Tutor();
    }
    
    public Tutor nuevoTutor(String ocupacion, long telefonoPersonal, long telefonoTrabajo, String relacion, Set<Alumno> atutorados, Set<Retiro> retiros, int dni, String apellidoYNombre){
        return new Tutor(ocupacion, telefonoPersonal, telefonoTrabajo, relacion, atutorados, retiros, dni, apellidoYNombre);
    }
    
    @Override
    public boolean altaTutor(Tutor ptutor) throws SQLException {
        try {
            tutor = ptutor;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO PERSONA VALUES("
                    +tutor.getDni()+",'"
                    +tutor.getApellidoYNombre()+"',2)");
            s.execute("INSERT INTO TUTOR VALUES("
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
            return true;
        }
        catch (DerbySQLIntegrityConstraintViolationException e){
            return false;
        }
    }

    public void nuevoEs_tutor(int dniT, int dniA){
        try{
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO ES_TUTOR VALUES("+dniT+","+dniA+")");
        }
        catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void nuevoEs_Hermano(int dniH1, int dniH2){
        try{
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("INSERT INTO ES_HERMANO VALUES("+dniH1+","+dniH2+")");
        }
        catch (SQLException ex) {
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
    
    @Override
    public void modificarPago(Pago pago) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            s.execute("UPDATE PAGO"+
                      " SET MONTOPAGADO="+pago.getMontoPagado()+
                      " WHERE IDPAGO="+pago.getIdPago());
            Calendar r = new GregorianCalendar();
            List<Date> fechas = pago.getFecha();
            int tamaño = fechas.size();
            Date d = fechas.get(tamaño-1);
            String fecha = "", parcial;
            r.setTime(d);
            parcial = String.valueOf(r.get(Calendar.YEAR));
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.MONTH)+1);
            fecha = fecha.concat(parcial+"-");
            parcial = String.valueOf(r.get(Calendar.DATE));
            fecha = fecha.concat(parcial);
            s.execute("INSERT INTO FECHA_PAGO VALUES("+pago.getIdPago()+",'"+fecha+"')");

        } catch (SQLException ex) {ex.printStackTrace();}
    }

    /*@Override
    public Alumno obtenerAlumno(int dniA){
        try {
            int idSala, añoLectivo,idPago, dniT;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            List<Tutor> listTutores = new ArrayList();
            List<Sala> listaSalas = new ArrayList<>();
            List<Pago> listaPagos = new ArrayList<>();
            Map<Integer,Sala> mapaSalas;
            Set<Pago> pagos;
            Set<Tutor> tutores;
            ResultSet rsAlumno;
            rsAlumno = s.executeQuery("SELECT * FROM ALUMNO");
            Statement sAux = c.createStatement();
            ResultSet rsSala = sAux.executeQuery("SELECT IDSALA FROM SALA");
            while(rsSala.next()){
                idSala = rsSala.getInt("IDSALA");
                listaSalas.add(obtenerSala(idSala));
            }
            ResultSet rsPago = sAux.executeQuery("SELECT IDPAGO FROM PAGO");
            while(rsPago.next()){
                idPago = rsPago.getInt("IDPAGO");
                listaPagos.add(obtenerPago(idPago));
            }
            ResultSet rsTutor = sAux.executeQuery("SELECT DNI FROM TUTOR");
            while(rsTutor.next()){
                dniT = rsTutor.getInt("DNI");
                listTutores.add(obtenerTutor(dniT));
            }
            while(rsAlumno.next()){
                ResultSet rsNombre = sAux.executeQuery("SELECT APELLIDOYNOMBRE FROM PERSONA WHERE DNI="+dniA);
                rsNombre.next();
                String apellidoYNombre = rsNombre.getString("APELLIDOYNOMBRE");
                rsSala = sAux.executeQuery("SELECT IDSALA,ANOLECTIVO FROM ES_ALUMNO WHERE DNIALUMNO="+dniA);
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
                pagos = new HashSet();
                rsPago = sAux.executeQuery("SELECT IDPAGO FROM CORRESPONDE_PAGO WHERE DNIALUMNO="+dniA);
                while(rsPago.next()){
                    idPago = rsPago.getInt("IDPAGO");
                    Iterator i = listaPagos.listIterator();
                    while(i.hasNext()){
                        Pago p = (Pago) i.next();
                        if(p.getIdPago() == idPago)
                            pagos.add(p);
                    }
                }
                tutores = new HashSet();
                rsTutor = sAux.executeQuery("SELECT DNITUTOR FROM ES_TUTOR WHERE DNIALUMNO="+dniA);
                while(rsTutor.next()){
                    dniT = rsTutor.getInt("DNITUTOR");
                    Iterator i = listTutores.listIterator();
                    while(i.hasNext()){
                        Tutor t = (Tutor) i.next();
                        if(t.getDni()== dniT)
                            tutores.add(t);
                    }
                }
                alumno = new Alumno(rsAlumno.getDate("FECHADENACIMIENTO"),rsAlumno.getString("LUGARDENACIMIENTO"),
                                           rsAlumno.getString("DOMICILIO"),rsAlumno.getLong("TELEFONO"),
                                           rsAlumno.getBoolean("CONTROLMEDICO"),rsAlumno.getBoolean("VACUNAS"),
                                           rsAlumno.getBoolean("CONTROLNATACION"),rsAlumno.getBoolean("TRAEMATERIALES"),
                                           rsAlumno.getString("OTROSDATOS"),null,tutores,pagos,mapaSalas,null,dniA,apellidoYNombre);
            }
            s.close();
            sAux.close();
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }*/
    
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
            tutor = new Tutor(rsTutor.getString("OCUPACION"),
                                    rsTutor.getLong("TELEFONOPERSONAL"),rsTutor.getLong("TELEFONOTRABAJO"),
                                    rsTutor.getString("RELACION"),new HashSet(),new HashSet(),dni,apellidoYNombre);
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
                                    rsSala.getString("COLOR"),rsSala.getInt("IDSALA"),new HashMap(),new HashMap());
            s.close();
            return sala;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }
    
    @Override
    public Pago obtenerPago(int idPago) {
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet rsPago = s.executeQuery("SELECT * FROM PAGO WHERE IDPAGO="+idPago);
            rsPago.next();
            Statement sAux = c.createStatement();
            ResultSet rsTipoPago = sAux.executeQuery("SELECT TIPO FROM TIPO_PAGO WHERE COD_TIPO="+rsPago.getInt("COD_TIPO"));
            rsTipoPago.next();
            String tipoPago = rsTipoPago.getString("TIPO");
            ResultSet rsFechaPago = sAux.executeQuery("SELECT FECHAPAGO FROM FECHA_PAGO WHERE IDPAGO="+idPago);
            List<Date> fecha = new ArrayList();
            while(rsFechaPago.next())
                fecha.add(rsFechaPago.getDate("FECHAPAGO"));
            Pago pago = new Pago(fecha,tipoPago,rsPago.getString("PERIODO"),
                                   rsPago.getInt("CUOTAS"),rsPago.getFloat("MONTOPAGADO"),rsPago.getFloat("MONTOTOTAL"),
                                   rsPago.getInt("IDPAGO"),null);
            s.close();
            return pago;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }

    @Override//NO GUARDA NADA EN LOS SETS
    public List obtenerTodosAlumno() {
        try {
            int dni, idSala, añoLectivo,idPago, dniT;
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            List<Alumno> listaDeAlumnos = new ArrayList();
            List<Tutor> listTutores = new ArrayList();
            List<Sala> listaSalas = new ArrayList<>();
            List<Pago> listaPagos = new ArrayList<>();
            Map<Integer,Sala> mapaSalas;
            Set<Pago> pagos;
            Set<Tutor> tutores;
            ResultSet rsAlumno = null;
            rsAlumno = s.executeQuery("SELECT ALUMNO.DNI,FECHADENACIMIENTO,LUGARDENACIMIENTO,CONTROLMEDICO,"+
                                      "VACUNAS,CONTROLNATACION,DOMICILIO,TRAEMATERIALES,TELEFONO,OTROSDATOS "+
                                       "FROM ALUMNO,PERSONA WHERE PERSONA.DNI=ALUMNO.DNI ORDER BY PERSONA.APELLIDOYNOMBRE");
            Statement sAux = c.createStatement();
            ResultSet rsSala = sAux.executeQuery("SELECT IDSALA FROM SALA");
            while(rsSala.next()){
                idSala = rsSala.getInt("IDSALA");
                listaSalas.add(obtenerSala(idSala));
            }
            ResultSet rsPago = sAux.executeQuery("SELECT IDPAGO FROM PAGO");
            while(rsPago.next()){
                idPago = rsPago.getInt("IDPAGO");
                listaPagos.add(obtenerPago(idPago));
            }
            ResultSet rsTutor = sAux.executeQuery("SELECT DNI FROM TUTOR");
            while(rsTutor.next()){
                dniT = rsTutor.getInt("DNI");
                listTutores.add(obtenerTutor(dniT));
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
                pagos = new HashSet();
                rsPago = sAux.executeQuery("SELECT IDPAGO FROM CORRESPONDE_PAGO WHERE DNIALUMNO="+dni);
                while(rsPago.next()){
                    idPago = rsPago.getInt("IDPAGO");
                    Iterator i = listaPagos.listIterator();
                    while(i.hasNext()){
                        Pago p = (Pago) i.next();
                        if(p.getIdPago() == idPago)
                            pagos.add(p);
                    }
                }
                tutores = new HashSet();
                rsTutor = sAux.executeQuery("SELECT DNITUTOR FROM ES_TUTOR WHERE DNIALUMNO="+dni);
                while(rsTutor.next()){
                    dniT = rsTutor.getInt("DNITUTOR");
                    Iterator i = listTutores.listIterator();
                    while(i.hasNext()){
                        Tutor t = (Tutor) i.next();
                        if(t.getDni()== dniT)
                            tutores.add(t);
                    }
                }
                alumno = new Alumno(rsAlumno.getDate("FECHADENACIMIENTO"),rsAlumno.getString("LUGARDENACIMIENTO"),
                                           rsAlumno.getString("DOMICILIO"),rsAlumno.getLong("TELEFONO"),
                                           rsAlumno.getBoolean("CONTROLMEDICO"),rsAlumno.getBoolean("VACUNAS"),
                                           rsAlumno.getBoolean("CONTROLNATACION"),rsAlumno.getBoolean("TRAEMATERIALES"),
                                           rsAlumno.getString("OTROSDATOS"),null,tutores,pagos,mapaSalas,null,dni,apellidoYNombre);
                listaDeAlumnos.add(alumno);
            }
            ResultSet rsHermanos;
            Iterator i = listaDeAlumnos.iterator();
            while(i.hasNext()){
                Alumno a = (Alumno)i.next();
                a.setHermanos(new HashSet());
                rsHermanos = sAux.executeQuery("SELECT DNI1 FROM ES_HERMANO WHERE DNI2="+a.getDni());
                while(rsHermanos.next()){
                    int dni1 = rsHermanos.getInt("DNI1");
                    Iterator j = listaDeAlumnos.iterator();
                    while(j.hasNext()){
                        Alumno b = (Alumno) j.next();
                        if(b.getDni() == dni1){
                            a.getHermanos().add(b);
                        }
                    }
                }
            }
            
            s.close();
            sAux.close();
            return listaDeAlumnos;
        } catch (SQLException ex) {ex.printStackTrace();}
        return null;
    }

    @Override//PARA EL CASO DE LA INSCRIPCION CON ALUMNO YA CARGADO EN EL SISTEMA
    public boolean agregarAñoLectivo(int dni, int idSala, int añoLectivo){
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet a;
            int dnia = 0;
            a = s.executeQuery("SELECT DNIALUMNO FROM ES_ALUMNO WHERE DNIALUMNO="+dni+"AND ANOLECTIVO="+añoLectivo);
            while(a.next())
                dnia = a.getInt("DNIALUMNO");
            if(dnia == 0)
                s.execute("INSERT INTO ES_ALUMNO VALUES("+dni+","+idSala+","+añoLectivo+")");
            else{
                return false;
            }
            s.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public float obtenerValorInscripcion() {
        float valor = 0;
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT VALOR FROM VALORES WHERE COD_TIPO=1");
            rs.next();
            valor = rs.getFloat("VALOR");
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    @Override
    public int obtenerMaximoIdPago() {
        int valor = 0;
        try {
            Connection c = ConexionBD.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(IDPAGO) FROM PAGO");
            rs.next();
            valor = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ImplementacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    private Exception SQLException(String mensaje) {
        IllegalArgumentException e = new IllegalArgumentException(mensaje, null);
        return e;
    }

    

    
    
}
