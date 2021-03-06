package ClasesBase;

import java.util.Map;
import java.util.Set;

public class Sala {
    private int edad;
    private String turno;
    private String color;
    private int idSala;
    private Map<Integer,Docente> docentes;
    private Map<Integer,Alumno> alumnos;

    /**
     * 
     * @param edad
     * @param turno
     * @param color
     * @param idSala 1: 3TM, 2: 3TT, 3: 4TM, 4: 4TT, 5: 5TM, 6: 5TT (3 años = 0, 4 años = 2, 5 años = 4, TM = 1, TT = 2. id es la suma. )
     * @param docentes
     * @param alumnos 
     */
    public Sala(int edad, String turno, String color, int idSala, Map<Integer, Docente> docentes, Map<Integer, Alumno> alumnos) {
        this.edad = edad;
        this.turno = turno;
        this.color = color;
        this.idSala = idSala;
        this.docentes = docentes;
        this.alumnos = alumnos;
    }

    public Sala() {
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Map<Integer, Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(Map<Integer, Docente> docentes) {
        this.docentes = docentes;
    }

    public Map<Integer, Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Map<Integer, Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
}

