package interfaces_Usuario;

import Interfaces_Generales.Login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objeto_usuario.UserContenido;
import objeto_usuario.Limitado;
import objeto_usuario.UserAdmin;

public class CrearUser extends JFrame {
private int Ancho = 600, Alto = 600, ancho_boton = 150, alto_boton=50;
    
        public CrearUser(){
        setVisible(true);
        setSize(Ancho,Alto);
        setLocationRelativeTo(null);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //
        setResizable(false);
        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, Ancho, Alto);
        //
        ImageIcon imagen = new ImageIcon("fondo_de_pantalla.JPG");
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(Ancho-10, Alto-10, Image.SCALE_SMOOTH));
        JLabel fondo = new JLabel(imagenNormal);
        fondo.setBounds(0, 0, Ancho-10, Alto-10);
        elementos(fondo);
        menu.add(fondo);
        add(menu);
    }
    private void elementos(JLabel fondo) {
        JTextField nombre = new JTextField();
        nombre.setBounds(250, 120, 200, 30);
        nombre.setFont(new Font("Calibri", 0, 24));
        fondo.add(nombre);
        //_
        JTextField username = new JTextField();
        username.setBounds(250, 195, 200, 30);
        username.setFont(new Font("Calibri", 0, 24));
        fondo.add(username);
        //_
        JTextField password = new JTextField();
        password.setBounds(250, 270, 200, 30);
        password.setFont(new Font("Calibri", 0, 24));
        fondo.add(password);
        //__
        JTextField edad = new JTextField();
        edad.setBounds(250, 345, 200, 30);
        edad.setFont(new Font("Calibri", 0, 24));
        fondo.add(edad);
        //__
        JComboBox<String> tipoUsuario = new JComboBox<>(new String[]{"(Seleccione)", "1. Administrador", "2. Contenido", "3. Limitado"});
        tipoUsuario.setBounds(250, 420, 200, 30);
        fondo.add(tipoUsuario);
        
        //_
        JButton boton_regresar = new JButton();
        boton(fondo, boton_regresar, 500, 450, Color.red, "Regresar",username, edad, nombre, password, tipoUsuario);
        
        //_
        JButton boton_confirmar = new JButton();
        boton(fondo, boton_confirmar, 500, 250, Color.yellow, "Crear",username, edad, nombre, password, tipoUsuario);
        etiquetas(fondo);
       
    }
    public void etiquetas(JLabel fondo){
      JLabel label_1 = new JLabel();
        crear_etiquetas(label_1, "Nombre completo", 80, 120, fondo);
        JLabel label_2 = new JLabel();
        crear_etiquetas(label_2, "Username", 139, 195, fondo);
        JLabel label_3 = new JLabel();
        crear_etiquetas(label_3, "Contraseña", 130, 270, fondo);
        JLabel label_4 = new JLabel();
        crear_etiquetas(label_4, "Edad", 187, 345, fondo);
        JLabel label_5 = new JLabel();
        crear_etiquetas(label_5, "Tipo de Usuario", 100, 420, fondo);
        JLabel Titulo = new JLabel();
        crear_etiquetas(Titulo, "Crear Usuario", 220, 30, fondo);  
    }

    public void crear_etiquetas(JLabel texto, String text, int x, int y, JLabel fondo) {
        texto = new JLabel();
        texto.setText(text);
        texto.setBounds(x, y, 165, 30);
        texto.setFont(new Font("Calibri", 0, 20));
        if (!text.equals("Nombre completo") && !text.equals("Tipo de Usuario")) {
            texto.setFont(new Font("Calibri", 0, 22));
            texto.setBounds(x, y, 110, 30);
        }
        if (text.equals("Crear Usuario")||text.equals("Editar Usuario")) {
            texto.setFont(new Font("Calibri", 0, 30));
            texto.setBounds(x, y, 200, 35);
        }
        texto.setForeground(Color.WHITE);
        fondo.add(texto);
    }

    public void boton(JLabel fondo, JButton boton, int y, int x, Color color, String text,JTextField username,JTextField edad,JTextField nombre,JTextField password,JComboBox tipoUsuario) {
        boton = new JButton();
        boton.setBounds(x, y, 100, 40);
        boton.setBackground(color);
        boton.setText(text);
        fondo.add(boton);
        if(text.equals("Regresar")){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuUser user = new MenuUser();
                user.setVisible(true);
                dispose();
            }
        });    
        }else{
         boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
    int Edad_numero;
    String tipoUsuarioSeleccionado = (String) tipoUsuario.getSelectedItem();

    if (!username.getText().isBlank() && !edad.getText().isBlank() && !nombre.getText().isBlank() && !password.getText().isBlank()) {
        if (!tipoUsuarioSeleccionado.equals("(Seleccione)")) {
            try {
                Edad_numero = Integer.parseInt(edad.getText());
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "La edad tiene que ser un número entero");
                return; // Sale del método si hay una excepción
            } 
            if(Login.FUNCIONES.verificar(username.getText())){
            if (Edad_numero > 0 && Edad_numero < 100) {
                int T_user = lista(tipoUsuarioSeleccionado);
                if(T_user==3){
                Login.FUNCIONES.añadir(username.getText(), new Limitado(username.getText(), password.getText(), T_user, Edad_numero, nombre.getText()));
                }
                if(T_user==2){
                Login.FUNCIONES.añadir(username.getText(), new UserContenido(username.getText(), password.getText(), T_user, Edad_numero, nombre.getText())); 
                }
                if(T_user==1){
                Login.FUNCIONES.añadir(username.getText(), new UserAdmin(username.getText(), password.getText(), T_user, Edad_numero, nombre.getText()));    
                }
                JOptionPane.showMessageDialog(null, "¡Se ha creado el usuario correctamente!");

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una edad posible");
            }
            }else{
             JOptionPane.showMessageDialog(null, "Ese Username no esta Disponible");   
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tiene que seleccionar un tipo de usuario");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Tiene que llenar todos los espacios");
    }
}
        });    
        }
    }

    public int lista(String tipoUsuarioSeleccionado) {
        int numero = 0;

        switch (tipoUsuarioSeleccionado) {
            case "1. Administrador":
                numero = 1;
                break;
            case "2. Contenido":
                numero = 2;
                break;
            case "3. Limitado":
                numero = 3;
                break;
        }

        return numero;
    }

}
