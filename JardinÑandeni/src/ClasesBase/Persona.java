package ClasesBase;


public class Persona {
    private int dni;
    private String apellidoYNombre;

    public Persona(int dni, String apellidoYNombre) {
        this.dni = dni;
        this.apellidoYNombre = apellidoYNombre;
    }

    public Persona() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellidoYNombre() {
        return apellidoYNombre;
    }

    public void setApellidoYNombre(String apellidoYNombre) {
        this.apellidoYNombre = apellidoYNombre;
    }

}
