
package Interfaces_Eventos;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Hector
 */
public final class VerEvento extends JFrame{
       private int Ancho = 600, Alto = 600, ancho_boton = 150, alto_boton=50;
    
        public VerEvento(){
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
        JLabel label = new JLabel("Ver Evento");
        label.setBounds(225, 20, 200, 55);
        label.setFont(new Font("Calibri", 0, 35));
        label.setForeground(Color.white);
        JTextField codigo = new JTextField();
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBounds(100, 200, 400, 200);
        
        codigo.setBounds(250, 120, 200, 30);
        codigo.setFont(new Font("Calibri", 0, 24));
        fondo.add(codigo);
        JLabel label_1 = new JLabel("Codigo del evento");
        label_1.setBounds(80, 120, 165, 30);
        label_1.setFont(new Font("Calibri", 0, 20));
        label_1.setForeground(Color.WHITE);
        fondo.add(label_1);
        
        JButton ver = new JButton("Ver");
        ver.setBounds(100, 500, 150, 40);
        ver.setFont(new Font("Arial", 0, 20));
        ver.setBackground(new Color(255, 51, 0));
        ver.setForeground(Color.white);
        ver.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            int code;
            
             try {
                code = Integer.parseInt(codigo.getText());
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "El codigo tiene que ser un numero entero");
                return;
            } 
            if(Login.MANEJOEVENTOS.buscar(code)!=null){
                descripcion.setText(Login.MANEJOEVENTOS.datos(code));
            }else{
                descripcion.setText("No hay ningun evento con ese codigo");
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
                MenuEvent user = new MenuEvent();
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

