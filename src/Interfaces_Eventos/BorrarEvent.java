
package Interfaces_Eventos;

import Interfaces_Generales.Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class BorrarEvent extends JFrame{
    private int Ancho = 500, Alto = 350;
    
        public BorrarEvent(){
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
       JLabel label = new JLabel("Borrar Usuario");
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", 0, 30));
        label.setBounds(150, 50, 250, 35);
        //
        JLabel label2 = new JLabel("Usuario a borrar: ");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Arial", 0, 21));
        label2.setBounds(50, 120, 175, 30);
        //
        JTextField field = new JTextField();
        field.setBounds(250, 120, 200, 30);
        //
        JButton Borrar = new JButton("Borrar");
        Borrar.setBounds(50, 180, 100, 40);
        Borrar.setFont(new Font("Arial", 0, 20));
        Borrar.setBackground(new Color(255, 51, 0));
        Borrar.setForeground(Color.white);
        Borrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            int code;
            
             try {
                code = Integer.parseInt(field.getText());
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "El codigo tiene que ser un numero entero");
                return;
            }
                
            if(Login.MANEJOEVENTOS.borrarEvent(code)){
            Login.MANEJOEVENTOS.borrarEvent(code);
            }
            }
        });
        JButton boton = new JButton("Volver");
        boton.setBounds(250, 180, 150, 40);
        boton.setFont(new Font("Arial", 0, 18));
        boton.setBackground(Color.RED);
        boton.setForeground(Color.white);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuEvent user = new MenuEvent();
                user.setVisible(true);
                dispose();
            }
           
        });
        fondo.add(field);
        fondo.add(label);
        fondo.add(label2);
        fondo.add(boton);
        fondo.add(Borrar);
    }
}
