
package Interfaces_Generales;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import manejo_de_usuarios.EventManager;
import manejo_de_usuarios.UserManager;

public final class Login extends JFrame{
    public static UserManager FUNCIONES = new UserManager();
    public static EventManager MANEJOEVENTOS = new EventManager();
    private final int Ancho = 600;
    private final int Alto = 600;

    public Login() {
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, Ancho, Alto);

        ImageIcon imagen = new ImageIcon("fondo_de_pantalla.JPG");
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(Ancho - 10, Alto - 10, Image.SCALE_SMOOTH));
        JLabel FondoPantalla = new JLabel(imagenNormal);
        FondoPantalla.setBounds(0, 0, Ancho - 10, Alto - 10);
        
        elementos(FondoPantalla,menu);
        menu.add(FondoPantalla);
        add(menu);
    }
    public void elementos(JLabel fondo,JPanel panel) {
        // Icono de usuario
        JLabel label_2 = new JLabel(new ImageIcon("user_icon.PNG"));
        label_2.setBounds(330, 40, 70, 70);
        fondo.add(label_2);
        // Áreas de texto
        JTextField Usertext = crearTextField(175, 150, 250, 30, "Ingrese Su Usuario", new Font("Arial", 0, 22));
        JTextField Contratext = crearTextField(175, 230, 250, 30, "Ingrese Su Contraseña", new Font("Arial", 0, 22));
        // Título
        JLabel Titulo = new JLabel("Login");
        Titulo.setFont(new Font("Arial", 0, 40));
        Titulo.setBounds(230, 50, 150, 50);
        Titulo.setForeground(Color.white);
        fondo.add(Titulo);
        fondo.add(Usertext);
        fondo.add(Contratext);
        botones(fondo, Usertext, Contratext,panel,this);
    }
    public JTextField crearTextField(int x, int y, int ancho, int alto, String placeholder, Font font) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, ancho, alto);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
        textField.setText(placeholder);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        return textField;
    }
    public void botones(JLabel label, JTextField Username_text, JTextField Password_text,JPanel panel,JFrame frame) {
        JButton ini_sesion = new JButton("Iniciar Sesión");
        ini_sesion.setBounds(220, 300, 200, 60);
        ini_sesion.setFont(new Font("Arial", Font.PLAIN, 16));
        ImageIcon imagen = new ImageIcon("boton_enter.PNG");
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(130, 80, Image.SCALE_SMOOTH));
        Image image = imagen.getImage().getScaledInstance(130, 70, Image.SCALE_SMOOTH);
        ImageIcon imagenPequena = new ImageIcon(image);

        ini_sesion.setIcon(imagenNormal);
        ini_sesion.setBackground(new Color(0, 0, 0, 0));
        ini_sesion.setForeground(new Color(0, 0, 0, 0));
        ini_sesion.setOpaque(false);
        ini_sesion.setUI(new BasicButtonUI());
        ini_sesion.setBorderPainted(false);

        ini_sesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ini_sesion.setIcon(imagenPequena);
                if (!Username_text.getText().isBlank() && !Password_text.getText().isBlank()) {
                    UserManager.USERACTUAL = Username_text.getText();
                    if (FUNCIONES.buscar(Username_text.getText()) != null) {
                        if (FUNCIONES.buscar(Username_text.getText()).getContraseña().equals(Password_text.getText())) {
                            new MenuPrincipal().setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña o nombre de usuario incorrectos");
                            ini_sesion.setIcon(imagenNormal);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no registrado");
                        ini_sesion.setIcon(imagenNormal);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No deje los espacios en blanco");
                    ini_sesion.setIcon(imagenNormal);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ini_sesion.setIcon(imagenNormal);
            }
        });
                label.add(ini_sesion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
