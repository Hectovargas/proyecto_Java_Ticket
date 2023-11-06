
package Reportes;

import Interfaces_Generales.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class EventoFuturo extends JFrame{
     private int Ancho = 600, Alto = 600;
        public EventoFuturo(){
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
        JLabel label = new JLabel("Ver Eventos Futuros");
        label.setBounds(150, 20, 300, 55);
        label.setFont(new Font("Calibri", 0, 30));
        label.setForeground(Color.white);
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setBounds(100, 100, 400, 300);
        
        JButton ver = new JButton("Ver");
        ver.setBounds(100, 500, 150, 40);
        ver.setFont(new Font("Arial", 0, 20));
        ver.setBackground(new Color(255, 51, 0));
        ver.setForeground(Color.white);
        ver.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                descripcion.setText(Login.MANEJOEVENTOS.listarEventosfuturos());   
                if(descripcion.getText().isBlank()){
                descripcion.setText("No hay ningun evento futuro -_-");
                }
            }
        });
        JButton boton = new JButton("Regresar");
        boton.setBounds(600-100-150, 500, 150, 40);
        boton.setFont(new Font("Arial", 0, 20));
        boton.setBackground(Color.RED);
        boton.setForeground(Color.white);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuReportes user = new MenuReportes();
                user.setVisible(true);
                dispose();
            }
           
        });
        fondo.add(scrollPane);
        fondo.add(boton);
        fondo.add(ver);
        fondo.add(label);
    }
}

