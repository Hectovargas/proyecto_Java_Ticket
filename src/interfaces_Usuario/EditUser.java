package interfaces_Usuario;

import Interfaces_Generales.Login;
import Interfaces_Generales.MenuPrincipal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import manejo_de_usuarios.UserManager;

public class EditUser extends JFrame {
    private int Ancho = 600, Alto = 600, ancho_boton = 150, alto_boton=50;
    String tipoUsuarioSeleccionado;
        public EditUser(){
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
    public void elementos(JLabel fondo) {
        JTextField nombre = new JTextField();
        nombre.setBounds(250, 120, 200, 30);
        nombre.setFont(new Font("Calibri", 0, 24));
        nombre.setText(Login.FUNCIONES.buscar(UserManager.usuario_Edit).getNombre_real());
        fondo.add(nombre);
        //_
        JTextField username = new JTextField();
        username.setText(Login.FUNCIONES.buscar(UserManager.usuario_Edit).getNombre_usuario());
        username.setBounds(250, 195, 200, 30);
        username.setFont(new Font("Calibri", 0, 24));
        fondo.add(username);
        //_
        JTextField password = new JTextField();
        password.setText(Login.FUNCIONES.buscar(UserManager.usuario_Edit).getContraseña());
        password.setBounds(250, 270, 200, 30);
        password.setFont(new Font("Calibri", 0, 24));
        fondo.add(password);
        //__
        JTextField edad = new JTextField();
        edad.setText(Login.FUNCIONES.buscar(UserManager.usuario_Edit).getEdad() + "");
        edad.setBounds(250, 345, 200, 30);
        edad.setFont(new Font("Calibri", 0, 24));
        fondo.add(edad);
        //___
        JComboBox<String> tipoUsuario = new JComboBox<>(new String[]{"(Seleccione)", "1. Administrador", "2. Contenido", "3. Limitado"});
        tipoUsuario.setBounds(250, 420, 200, 30);
        fondo.add(tipoUsuario);

        //_
        JButton boton_regresar = new JButton();
        boton(fondo, boton_regresar, 500, 450, Color.red, "Regresar", username, edad, nombre, password, tipoUsuario);

        //_
        JButton boton_confirmar = new JButton();
        boton(fondo, boton_confirmar, 500, 250, Color.yellow, "Editar", username, edad, nombre, password, tipoUsuario);
        etiquetas(fondo);
    }

    public void etiquetas(JLabel fondo) {
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
        crear_etiquetas(Titulo, "Editar Usuario", 220, 30, fondo);

    }

    public void boton(JLabel fondo, JButton boton, int y, int x, Color color, String text, JTextField username, JTextField edad, JTextField nombre, JTextField password, JComboBox tipoUsuario) {
        boton = new JButton();
        boton.setBounds(x, y, 100, 40);
        boton.setBackground(color);
        boton.setText(text);
        fondo.add(boton);
        if (text.equals("Regresar")) {
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    MenuUser user = new MenuUser();
                    user.setVisible(true);
                    dispose();
                }
            });
        } else {
            boton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int Edad_numero;
                    int T_user;
                     tipoUsuarioSeleccionado = (String) tipoUsuario.getSelectedItem();
                     if (tipoUsuarioSeleccionado.equals("(Seleccione)")) {
                      T_user=Login.FUNCIONES.buscar(UserManager.usuario_Edit).getTipo_usuario();
                     }else{
                     T_user = lista(tipoUsuarioSeleccionado);
                     }
                    if (!username.getText().isBlank() && !edad.getText().isBlank() && !nombre.getText().isBlank() && !password.getText().isBlank()) {
                            try {
                                Edad_numero = Integer.parseInt(edad.getText());
                            } catch (NumberFormatException er) {
                                JOptionPane.showMessageDialog(null, "La edad tiene que ser un número entero");
                                return;
                            }
                            if (Login.FUNCIONES.verificar(username.getText()) || username.getText().equals(UserManager.usuario_Edit)) {
                                if (Edad_numero > 0 && Edad_numero < 100) {
                                    if (Login.FUNCIONES.buscar(UserManager.usuario_Edit).getTipo_usuario()!=0) {
                                        Login.FUNCIONES.editar(nombre.getText(), username.getText(), password.getText(), Edad_numero, T_user);
                                        JOptionPane.showMessageDialog(null, "¡Se ha editado el usuario correctamente!");
                                        MenuPrincipal menu = new MenuPrincipal();
                                        menu.setVisible(true);
                                        dispose();

                                    } else {
                                        JOptionPane.showMessageDialog(null, "No puede editar este usuario");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ingrese una edad posible");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ese Username no esta Disponible");
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

    public void crear_etiquetas(JLabel texto, String text, int x, int y, JLabel fondo) {
        texto = new JLabel();
        texto.setText(text);
        texto.setBounds(x, y, 165, 30);
        texto.setFont(new Font("Calibri", 0, 20));
        if (!text.equals("Nombre completo") && !text.equals("Tipo de Usuario")) {
            texto.setFont(new Font("Calibri", 0, 22));
            texto.setBounds(x, y, 110, 30);
        }
        if (text.equals("Crear Usuario") || text.equals("Editar Usuario")) {
            texto.setFont(new Font("Calibri", 0, 30));
            texto.setBounds(x, y, 200, 35);
        }
        texto.setForeground(Color.WHITE);
        fondo.add(texto);
    }

}
