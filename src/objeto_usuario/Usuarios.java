
package objeto_usuario;

public class Usuarios {

    private String contraseña;
    private int tipo_usuario; //0 el admin //1 administradores //2 contenidos //3 limitado
    private String nombre_real;
    private String nombre_usuario;
    private int edad;
        
    public Usuarios(String nombre_usuario, String contraseña, int tipo_usuario, int edad, String nombre_real) {
        this.tipo_usuario = tipo_usuario;
        this.contraseña = contraseña;
        this.nombre_real = nombre_real;
        this.nombre_usuario = nombre_usuario;
        this.edad = edad;
    }

   public String getContraseña() {
        return contraseña;
    }
   public void setNombre_real(String nombre_real) {
        this.nombre_real = nombre_real;
    }
    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

     public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    public int getTipo_usuario() {
        return tipo_usuario;
    }
    
    public int getEdad() {
        return edad;
    }

    public String getNombre_real() {
        return nombre_real;
    }

 
}
