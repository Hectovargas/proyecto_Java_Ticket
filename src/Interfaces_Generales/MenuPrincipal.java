package Interfaces_Generales;

import Interfaces_Eventos.MenuEvent;
import Reportes.MenuReportes;
import interfaces_Usuario.MenuUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static manejo_de_usuarios.UserManager.USERACTUAL;
import static Interfaces_Generales.Login.FUNCIONES;

public class MenuPrincipal extends JFrame{

    public MenuPrincipal() {
        initUI();
    }

    private void initUI() {
        setVisible(true);
        setSize(600,600);
        setLocationRelativeTo(null);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ImageIcon imagen = new ImageIcon("fondo_de_pantalla.JPG");
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(590, 590, Image.SCALE_SMOOTH));
        JLabel fondo = new JLabel(imagenNormal);
        fondo.setBounds(0, 0, 590, 590);

        JPanel menu = new JPanel(null);
        menu.setBounds(0, 0, 590, 590);
        menu.add(fondo);
        elementos();
        add(menu);
    }

    private void elementos() {
        agregarBoton("Eventos", 225, 130, Color.GREEN, (e) -> {this.adminEventos(e);});
        agregarBoton("Usuarios", 225, 230, Color.ORANGE,(e) -> {this.adminUsuarios(e);});
        agregarBoton("Reportes", 225, 330, new Color(0, 204, 204),(e) -> {this.abrirReportes(e);});
        agregarBoton("Salir", 225, 430, Color.RED,(e) -> {this.cerrarVentana(e);});

        agregarIcono("icon_calendar.PNG", 225 - 55, 130);
        agregarIcono("user_icon.PNG", 225 - 55, 230);
        agregarIcono("report_icon.PNG", 225 - 55, 330);
        agregarIcono("exit_icon.PNG", 225 - 55, 430);

        agregarTitulo("Menu Principal", 200, 30, 30);
    }

    private void agregarBoton(String texto, int x, int y, Color color, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 20));
        boton.setForeground(Color.WHITE);
        boton.setBounds(x, y, 150, 50);
        boton.setBackground(color);
        if (FUNCIONES.buscar(USERACTUAL).getTipo_usuario() > 1 && boton.getText().equalsIgnoreCase("Usuarios")) {
            boton.setEnabled(false);
        } else {
            boton.addActionListener(actionListener);
        }
        getContentPane().add(boton);
    }

    private void agregarIcono(String nombreImagen, int x, int y) {
        int alto = 50, ancho = 50;
        ImageIcon imagen = new ImageIcon(nombreImagen);
        ImageIcon imagenNormal = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        JLabel icono = new JLabel(imagenNormal);
        icono.setBounds(x, y, ancho, alto);
        getContentPane().add(icono);
    }

    private void agregarTitulo(String texto, int x, int y, int fontSize) {
        JLabel titulo = new JLabel(texto);
        titulo.setFont(new Font("Arial", Font.PLAIN, fontSize));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(x, y, 200, 40);
        getContentPane().add(titulo);
    }

    private void adminEventos(ActionEvent e) {
        MenuEvent user = new MenuEvent();
        user.setVisible(true);
        dispose();
        
    }

    private void adminUsuarios(ActionEvent e) {
        MenuUser user = new MenuUser();
        user.setVisible(true);
        dispose();
    }

    private void abrirReportes(ActionEvent e) {
        MenuReportes log = new MenuReportes();
        log.setVisible(true);
        dispose();
    }

    private void cerrarVentana(ActionEvent e) {
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }


}
