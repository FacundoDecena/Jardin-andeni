package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static Connection conn = null;
   
    private ConexionBD(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:derby:JardinÑandeni;create=true", null);
            inicializarTablas();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nNo es posible cargar el driver de JDBC");
        } catch (InstantiationException ie) {
            System.err.println("\nNo es posible Instanciar el driver de JDBC");
        } catch (IllegalAccessException iae) {
            System.err.println("\nNo se tiene acceso al driver de JDBC");
        } catch(SQLException e){}
    }
    
    public static Connection getConnection(){
        if (conn == null){
            new ConexionBD();
        }
        return conn;
    }
    
    public static void cerrarBD(){
        try{
            conn.close();
        } catch(SQLException sqle){
            System.err.println("Error al intentar cerrar la base de datos.");
        }
    }

    public void inicializarTablas() throws SQLException{
        Statement s = conn.createStatement();
        int largoCampos = 100;
        s.execute("CREATE TABLE TIPO_PERSONA(" +
                  "COD_TIPO SMALLINT NOT NULL PRIMARY KEY," +
                  "TIPO VARCHAR(20) NOT NULL)"
        ); 

        s.execute("INSERT INTO TIPO_PERSONA VALUES(1,'ALUMNO')");
        s.execute("INSERT INTO TIPO_PERSONA VALUES(2,'TUTOR')"); 
        s.execute("INSERT INTO TIPO_PERSONA VALUES(3,'DOCENTE')"); 
        s.execute("INSERT INTO TIPO_PERSONA VALUES(4,'TUTORYDOCENTE')");

        s.execute("CREATE TABLE PERSONA(" +
                  "DNI INT NOT NULL PRIMARY KEY, " +
                  "APELLIDOYNOMBRE VARCHAR("+largoCampos+") NOT NULL, " +
                  "COD_TIPO SMALLINT NOT NULL," + 
                  "FOREIGN KEY (COD_TIPO) REFERENCES TIPO_PERSONA(COD_TIPO))"
        );

        s.execute("CREATE TABLE ALUMNO(" +
                  "DNI INT NOT NULL PRIMARY KEY," +
                  "FECHADENACIMIENTO DATE NOT NULL," +
                  "LUGARDENACIMIENTO VARCHAR(20) NOT NULL," +
                  "CONTROLMEDICO BOOLEAN NOT NULL," + 
                  "VACUNAS BOOLEAN NOT NULL," + 
                  "CONTROLNATACION BOOLEAN NOT NULL," +
                  "DOMICILIO VARCHAR("+largoCampos+") NOT NULL," +
                  "TRAEMATERIALES BOOLEAN NOT NULL," +
                  "TELEFONO BIGINT NOT NULL," +
                  "OTROSDATOS VARCHAR("+largoCampos+")," +
                  "FOREIGN KEY (DNI) REFERENCES PERSONA(DNI))"    
        );

        s.execute("CREATE TABLE TUTOR(" +
                  "TIPODNI VARCHAR(10) NOT NULL," +
                  "DNI INT NOT NULL PRIMARY KEY," +
                  "OCUPACION VARCHAR("+largoCampos+") NOT NULL," +
                  "RELACION VARCHAR(20) NOT NULL," +
                  "TELEFONOPERSONAL BIGINT NOT NULL," +
                  "TELEFONOTRABAJO BIGINT," +
                  "FOREIGN KEY (DNI) REFERENCES PERSONA(DNI))"
        );

        s.execute("CREATE TABLE ES_TUTOR(" +
                  "DNITUTOR INT NOT NULL," +
                  "DNIALUMNO INT NOT NULL," +
                  "PRIMARY KEY (DNITUTOR,DNIALUMNO)," +
                  "FOREIGN KEY (DNITUTOR) REFERENCES TUTOR(DNI)," +
                  "FOREIGN KEY (DNIALUMNO) REFERENCES ALUMNO(DNI))" 
        );

        s.execute("CREATE TABLE ES_HERMANO(" +
                  "DNI1 INT NOT NULL," +
                  "DNI2 INT NOT NULL," +
                  "PRIMARY KEY (DNI1,DNI2)," +
                  "FOREIGN KEY (DNI1) REFERENCES ALUMNO(DNI)," +
                  "FOREIGN KEY (DNI1) REFERENCES ALUMNO(DNI))" 
         );

        s.execute("CREATE TABLE DOCENTE(" +
                  "DNI INT NOT NULL PRIMARY KEY," +
                  "CUIT BIGINT NOT NULL UNIQUE," +
                  "DOMICILIO VARCHAR ("+largoCampos+") NOT NULL," +
                  "TITULO VARCHAR("+largoCampos+") NOT NULL," +
                  "FECHAINGRESODOCENCIA DATE NOT NULL, " +
                  "FECHAINGRESOINSTITUTO DATE NOT NULL, " +
                  "TELEFONO BIGINT NOT NULL," + 
                  "FOREIGN KEY (DNI) REFERENCES PERSONA(DNI))"    
        );

        s.execute("CREATE TABLE TIPO_PAGO(" +
                  "COD_TIPO SMALLINT NOT NULL PRIMARY KEY," +
                  "TIPO VARCHAR(20) NOT NULL)"
        );

        s.execute("INSERT INTO TIPO_PAGO VALUES(1,'INSCRIPCION')");
        s.execute("INSERT INTO TIPO_PAGO VALUES(2,'MATERIALES')"); 
        s.execute("INSERT INTO TIPO_PAGO VALUES(3,'CUOTA')"); 
        s.execute("INSERT INTO TIPO_PAGO VALUES(4,'SAC')");
        s.execute("INSERT INTO TIPO_PAGO VALUES(5,'NATACION')");
        
        s.execute("CREATE TABLE VALORES(" +
                  "COD_TIPO SMALLINT NOT NULL PRIMARY KEY," +
                  "VALOR FLOAT NOT NULL," +
                  "FOREIGN KEY (COD_TIPO) REFERENCES TIPO_PAGO(COD_TIPO))"
        );
        
        s.execute("INSERT INTO VALORES VALUES(1,3000.00)");
        s.execute("INSERT INTO VALORES VALUES(2,550.00)"); 
        s.execute("INSERT INTO VALORES VALUES(3,2000.00)"); 
        s.execute("INSERT INTO VALORES VALUES(4,1000.00)");
        s.execute("INSERT INTO VALORES VALUES(5,500.00)");

        s.execute("CREATE TABLE PAGO(" +
                  "IDPAGO INT NOT NULL PRIMARY KEY," +
                  "FECHA DATE NOT NULL, " +
                  "MONTOTOTAL FLOAT NOT NULL, " +
                  "MONTOPAGADO FLOAT NOT NULL," +
                  "PERIODO VARCHAR (20) NOT NULL," +
                  "COD_TIPO SMALLINT NOT NULL," +
                  "CUOTAS SMALLINT NOT NULL," +
                  "FOREIGN KEY (COD_TIPO) REFERENCES TIPO_PAGO(COD_TIPO))"    
        );
        
        s.execute("CREATE TABLE CORRESPONDE_PAGO(" +
                  "IDPAGO INT NOT NULL," +
                  "DNIALUMNO INT NOT NULL," +
                  "PRIMARY KEY(IDPAGO,DNIALUMNO)," +
                  "FOREIGN KEY (IDPAGO) REFERENCES PAGO(IDPAGO)," +
                  "FOREIGN KEY (DNIALUMNO) REFERENCES ALUMNO(DNI))"    
        );
            

        s.execute("CREATE TABLE LICENCIA(" +
                  "IDLICENCIA INT NOT NULL PRIMARY KEY," +
                  "DNIDOCENTE INT NOT NULL," +
                  "FECHAINICIO DATE NOT NULL, " +
                  "FECHAFIN DATE NOT NULL, " +
                  "FECHASOLICITUD DATE NOT NULL, " +
                  "MOTIVO VARCHAR ("+largoCampos+") NOT NULL," +
                  "FOREIGN KEY (DNIDOCENTE) REFERENCES DOCENTE(DNI))"   
        );

        s.execute("CREATE TABLE SALA(" +
                  "IDSALA INT NOT NULL PRIMARY KEY," +
                  "EDAD SMALLINT NOT NULL," +
                  "COLOR VARCHAR(20) NOT NULL, " +
                  "TURNO VARCHAR(10) NOT NULL)"   
        );
        
        s.execute("INSERT INTO SALA VALUES(1,3,'Amarillo','Mañana')");
        s.execute("INSERT INTO SALA VALUES(2,3,'Amarillo','Tarde')");
        s.execute("INSERT INTO SALA VALUES(3,4,'Celeste','Mañana')");
        s.execute("INSERT INTO SALA VALUES(4,4,'Celeste','Tarde')");
        s.execute("INSERT INTO SALA VALUES(5,5,'Naranja','Mañana')");
        s.execute("INSERT INTO SALA VALUES(6,5,'Naranja','Tarde')");

        s.execute("CREATE TABLE ES_ALUMNO(" +
                  "DNIALUMNO INT NOT NULL," +
                  "IDSALA INT NOT NULL," +
                  "ANOLECTIVO INT NOT NULL," +
                  "PRIMARY KEY (DNIALUMNO,IDSALA,ANOLECTIVO)," +
                  "FOREIGN KEY (IDSALA) REFERENCES SALA(IDSALA)," +
                  "FOREIGN KEY (DNIALUMNO) REFERENCES ALUMNO(DNI))" 
        );

        s.execute("CREATE TABLE ES_DOCENTE(" +
                  "DNIDOCENTE INT NOT NULL," +
                  "IDSALA INT NOT NULL," +
                  "ANOLECTIVO INT NOT NULL," +
                  "PRIMARY KEY (DNIDOCENTE,IDSALA)," +
                  "FOREIGN KEY (IDSALA) REFERENCES SALA(IDSALA)," +
                  "FOREIGN KEY (DNIDOCENTE) REFERENCES DOCENTE(DNI))" 
        );

        s.execute("CREATE TABLE REGISTROASISTENCIA(" +
                  "IDRA INT NOT NULL PRIMARY KEY," +
                  "DNI INT NOT NULL," +
                  "ANOLECTIVO INT NOT NULL," +
                  "FOREIGN KEY (DNI) REFERENCES PERSONA(DNI))"
        );

        s.execute("CREATE TABLE FALTA(" +
                  "IDRA INT NOT NULL," +
                  "FECHA DATE NOT NULL," +
                  "ESTAJUSTIFICADA BOOLEAN NOT NULL," +
                  "PRIMARY KEY (IDRA,FECHA)," +
                  "FOREIGN KEY (IDRA) REFERENCES REGISTROASISTENCIA(IDRA))"
        );

        s.execute("CREATE TABLE ASISTENCIA(" +
                  "IDASISTENCIA INT NOT NULL PRIMARY KEY," +
                  "IDRA INT NOT NULL," +
                  "FECHA DATE NOT NULL," +
                  "FOREIGN KEY (IDRA) REFERENCES REGISTROASISTENCIA(IDRA))"
        );

        s.execute("CREATE TABLE TARDANZA(" +
                  "IDASISTENCIA INT NOT NULL PRIMARY KEY," +
                  "TIEMPOTARDANZA INT NOT NULL," +
                  "FOREIGN KEY (IDASISTENCIA) REFERENCES ASISTENCIA(IDASISTENCIA))"
        );

        s.execute("CREATE TABLE RETIRO(" +
                  "IDASISTENCIA INT NOT NULL PRIMARY KEY," +
                  "HORA TIME NOT NULL," +
                  "MOTIVO VARCHAR("+largoCampos+") NOT NULL," +
                  "FOREIGN KEY (IDASISTENCIA) REFERENCES ASISTENCIA(IDASISTENCIA))"
        );

        s.execute("CREATE TABLE TUTOR_RETIRO(" +
                  "DNITUTOR INT NOT NULL," +
                  "IDASISTENCIA INT NOT NULL PRIMARY KEY," +
                  "FOREIGN KEY (DNITUTOR) REFERENCES TUTOR(DNI)," +
                  "FOREIGN KEY (IDASISTENCIA) REFERENCES RETIRO(IDASISTENCIA))"
        );
        s.close();
    }
    
    public static void main(String[] args) throws SQLException{
        /*Connection c = ConexionBD.getConnection();
        Statement s = c.createStatement();
        s.execute("INSERT INTO PERSONA VALUES(59675123,'Rodriguez Ian',1)");
        s.execute("INSERT INTO PERSONA VALUES(63756456,'Velazquez Martina',1)");
        s.execute("INSERT INTO PERSONA VALUES(60896213,'Silva Jonatan',1)");
        s.execute("INSERT INTO PERSONA VALUES(61233664,'Marquez Valentina',1)");
        s.execute("INSERT INTO ALUMNO VALUES(59675123,'2014-02-23','San Luis',true,true,true,'Tomas Jofre 78',true,2664361979,'')");
        s.execute("INSERT INTO ALUMNO VALUES(63756456,'2015-05-27','San Luis',true,true,true,'Junin 66',true,2664369873,'')");
        s.execute("INSERT INTO ALUMNO VALUES(60896213,'2014-08-12','San Luis',true,true,true,'Mitre 150',true,2664612043,'')");
        s.execute("INSERT INTO ALUMNO VALUES(61233664,'2015-01-23','San Luis',true,true,true,'Irigoyen 750',true,2664727467,'')");


        s.execute("INSERT INTO PERSONA VALUES(22768699,'Rodriguez Marcelo',2)");
        s.execute("INSERT INTO PERSONA VALUES(14542703,'Velazquez Antonio',2)");
        s.execute("INSERT INTO PERSONA VALUES(23724921,'Silva Marcos',2)");
        s.execute("INSERT INTO PERSONA VALUES(29334669,'Marquez Jose',2)");

        s.execute("INSERT INTO PERSONA VALUES(21698853,'Nuñez Veronica',2)");
        s.execute("INSERT INTO PERSONA VALUES(23456987,'Puebla Maria',2)");
        s.execute("INSERT INTO PERSONA VALUES(21456213,'Yerumini Estela',2)");
        s.execute("INSERT INTO PERSONA VALUES(34123023,'Lopez Ester',2)");

        s.execute("INSERT INTO TUTOR VALUES('DNI',22768699,'Remisero','Padre',26643578945,26643615945)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',14542703,'Obrero','Padre',26643575949,26643614545)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',23724921,'Contador','Padre',2664212468,26644956122)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',29334669,'Obrero','Padre',26643575949,26643614545)");

        s.execute("INSERT INTO TUTOR VALUES('DNI',21698853,'Ama de Casa','Madre',2664367982,26648456)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',23456987,'Cheff','Madre',26643575949,2664451532)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',21456213,'Arquitecta','Madre',2664456453,2664786455)");
        s.execute("INSERT INTO TUTOR VALUES('DNI',34123023,'Ingeniera','Madre',2664612341,266445612)");

        s.execute("INSERT INTO ES_TUTOR VALUES(22768699,59675123)");
        s.execute("INSERT INTO ES_TUTOR VALUES(21698853,59675123)");
        s.execute("INSERT INTO ES_TUTOR VALUES(14542703,63756456)");
        s.execute("INSERT INTO ES_TUTOR VALUES(23456987,63756456)");
        s.execute("INSERT INTO ES_TUTOR VALUES(23724921,60896213)");
        s.execute("INSERT INTO ES_TUTOR VALUES(21456213,60896213)");
        s.execute("INSERT INTO ES_TUTOR VALUES(29334669,61233664)");
        s.execute("INSERT INTO ES_TUTOR VALUES(34123023,61233664)");

        s.execute("INSERT INTO ES_ALUMNO VALUES(59675123,3,2017)");
        s.execute("INSERT INTO ES_ALUMNO VALUES(59675123,1,2016)");
        s.execute("INSERT INTO ES_ALUMNO VALUES(63756456,1,2017)");
        s.execute("INSERT INTO ES_ALUMNO VALUES(60896213,2,2017)");
        s.execute("INSERT INTO ES_ALUMNO VALUES(61233664,6,2017)");
        s.execute("INSERT INTO ES_ALUMNO VALUES(61233664,4,2016)");*/
    }
}
