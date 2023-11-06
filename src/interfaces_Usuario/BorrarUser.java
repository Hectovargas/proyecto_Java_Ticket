package interfaces_Usuario;

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
import manejo_de_usuarios.UserManager;

public class BorrarUser extends JFrame{

 private int Ancho = 500, Alto = 400;
    
        public BorrarUser(){
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
        JLabel fondoPantalla = new JLabel(imagenNormal);
        fondoPantalla.setBounds(0, 0, Ancho-10, Alto-10);
        elementos(fondoPantalla);
        menu.add(fondoPantalla);
        add(menu);
    }
    private void elementos(JLabel fondo){
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
            if(!field.getText().isBlank()){
            if(Login.FUNCIONES.buscar(field.getText())!=null){
            if(Login.FUNCIONES.verificar_borrado(field.getText())){
            Login.FUNCIONES.borrar(field.getText());
            JOptionPane.showMessageDialog(null, "Usuario borrado correctamente");
            if(UserManager.USERACTUAL.equals(field.getText())){
                Login log = new Login();
                log.setVisible(true);
                dispose();
            }
            }else{
            JOptionPane.showMessageDialog(null,"No puede borrar este usuario");
            }
            }else{
            JOptionPane.showMessageDialog(null,"Ese usuario No existe");     
            }
            }else{
            JOptionPane.showMessageDialog(null,"No Ingreso ningun usario");    
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
                MenuUser user = new MenuUser();
                user.setVisible(true);
                dispose();
            }
        });
        fondo.add(label);
        fondo.add(Borrar);
        fondo.add(boton);
        fondo.add(field);
        fondo.add(label2);
        
    }
}
