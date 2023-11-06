package interfaces_Usuario;

import Interfaces_Generales.MenuPrincipal;
import Interfaces_Generales.Login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import manejo_de_usuarios.UserManager;

public class MenuUser extends JFrame {

    private int Ancho = 600, Alto = 600, ancho_boton = 150, alto_boton = 50;

    public MenuUser() {

        setVisible(true);
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //
        setResizable(false);
        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, Ancho, Alto);
        //
        ImageIcon imagen = new ImageIcon("fondo_de_pantalla.JPG");
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(Ancho - 10, Alto - 10, Image.SCALE_SMOOTH));
        JLabel fondo = new JLabel(imagenNormal);
        fondo.setBounds(0, 0, Ancho - 10, Alto - 10);
        elementos(fondo);
        menu.add(fondo);
        add(menu);
    }

    public final void elementos(JLabel fondo) {
        JLabel Titulo = new JLabel("Menu De Usuario");
        Titulo.setFont(new Font("Arial", 0, 30));
        Titulo.setBounds(185, 40, 250, 50);
        Titulo.setForeground(Color.WHITE);
        agregarIcono("agregarEvento.PNG", 225 - 54, 130);
        agregarIcono("editEvento.PNG", 225 - 54, 230);
        agregarIcono("borrarEvento.PNG", 225 -54, 330);
        Crear(fondo);
        Editar(fondo);
        Borrar(fondo);
        Regresar(fondo, this);
        fondo.add(Titulo);
    }

    public void Crear(JLabel fondo) {
        JButton crea = new JButton("Crear Usuario");
        crea.setFont(new Font("Arial", 0, 18));
        crea.setForeground(Color.WHITE);
        crea.setBounds(225, 130, ancho_boton, alto_boton);
        crea.setBackground(Color.GREEN);
        crea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CrearUser cre = new CrearUser();
                cre.setVisible(true);
                dispose();
            }
        });
        fondo.add(crea);
    }

    public void Editar(JLabel fondo) {
        JButton edit = new JButton("Editar Usuario");
        edit.setFont(new Font("Arial", 0, 18));
        edit.setForeground(Color.WHITE);
        edit.setBounds(225, 230, ancho_boton, alto_boton);
        edit.setBackground(new Color(204, 0, 204));
        edit.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                String Nombre = JOptionPane.showInputDialog("Por favor, ingresa el numero del evento a editar");
                if (Login.FUNCIONES.buscar(Nombre) != null) {
                    UserManager.usuario_Edit = Nombre;
                    EditUser user = new EditUser();
                    user.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ese usuario no existe");
                }

            }
        });
        fondo.add(edit);
    }

    public void Borrar(JLabel fondo) {
        JButton borra = new JButton("Borrar usuario");
        borra.setFont(new Font("Arial", 0, 18));
        borra.setForeground(Color.WHITE);
        borra.setBounds(225, 330, ancho_boton, alto_boton);
        borra.setBackground(new Color(204, 0, 51));
        borra.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                BorrarUser edit = new BorrarUser();
                edit.setVisible(true);

                dispose();
            }
        });
        fondo.add(borra);
    }

    public void Regresar(JLabel fondo, JFrame frame) {
        ImageIcon imagen = new ImageIcon("exit_icon.PNG");
        ImageIcon imagen_cambiada = new ImageIcon(imagen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton Regreso = new JButton(imagen_cambiada);
        Regreso.setBounds(275, 430, 50, 50);
        Regreso.setBackground(new Color(255, 0, 0));
        fondo.add(Regreso);
        Regreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuPrincipal user = new MenuPrincipal();
                user.setVisible(true);
                dispose();
            }
        });

    }
 private void agregarIcono(String nombreImagen, int x, int y) {
        int alto = 50, ancho = 50;
        ImageIcon imagen = new ImageIcon(nombreImagen);
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        JLabel icono = new JLabel(imagenNormal);
        icono.setBounds(x, y, ancho, alto);
        getContentPane().add(icono);
    }
}
