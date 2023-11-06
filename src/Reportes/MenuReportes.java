package Reportes;
import Interfaces_Generales.MenuPrincipal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public final class MenuReportes extends JFrame{
private int Ancho = 600, Alto = 650, ancho_boton = 200, alto_boton=50;
        public MenuReportes(){
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
        VerPerfilDeUsuario(fondo);
        add(menu);
    }
     private void elementos(JLabel fondo) {
        JLabel Titulo = new JLabel("Menu Reportes");
        Titulo.setFont(new Font("Arial", 0, 30));
        Titulo.setBounds(200, 30, 250, 50);
        Titulo.setForeground(Color.WHITE);
        EventosRealizados(fondo);
        EventoFuturo(fondo);
        Cancelados(fondo);
        Regresar(fondo,this);
        GenFecha(fondo);
        fondo.add(Titulo);
    }

    public void EventosRealizados(JLabel fondo) {
        JButton Boton = new JButton("Eventos realizados");
        Boton.setFont(new Font("Arial", 0, 18));
        Boton.setForeground(Color.BLACK);
        Boton.setBounds(200, 110, ancho_boton, alto_boton);
        Boton.setBackground(new Color(0, 204, 204));
        Boton.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
        EventoRealizado real = new EventoRealizado();
        real.setVisible(true);
        dispose();
            }
        });
        fondo.add(Boton);
    }

    public void EventoFuturo(JLabel fondo) {
        JButton EventoFuturo = new JButton("Eventos futuros");
        EventoFuturo.setFont(new Font("Arial", 0, 18));
        EventoFuturo.setForeground(Color.BLACK);
        EventoFuturo.setBounds(200, 200, ancho_boton, alto_boton);
        EventoFuturo.setBackground(new Color(0, 153, 102));
        EventoFuturo.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent e){
                EventoFuturo edit = new EventoFuturo();
                edit.setVisible(true);
                dispose();
               
            }
        });
        fondo.add(EventoFuturo);
    }

    public void Cancelados(JLabel fondo) {
        JButton Boton = new JButton("Eventos cancelados");
        Boton.setFont(new Font("Arial", 0, 17));
        Boton.setForeground(Color.BLACK);
        Boton.setBounds(200, 290, ancho_boton, alto_boton);
        Boton.setBackground(new Color(0, 153, 255));
        Boton.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent e){
                EventoCancelado borr = new EventoCancelado();
                borr.setVisible(true);
                dispose();
            }
        });
        fondo.add(Boton);
    }
    
    public void GenFecha(JLabel fondo) {
        JButton Boton = new JButton("Ingreso generado por fecha");
        Boton.setFont(new Font("Arial", 0, 13));
        Boton.setForeground(Color.BLACK);
        Boton.setBounds(200, 380, ancho_boton, alto_boton);
        Boton.setBackground(new Color(51, 255, 153));
        Boton.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent e){
                IGPF ver = new IGPF();
                ver.setVisible(true);
                dispose();
            }
        });
        fondo.add(Boton);
    }
    public void VerPerfilDeUsuario(JLabel fondo) {
        JButton Boton = new JButton("Ver perfil de usuario");
        Boton.setFont(new Font("Arial", 0, 18));
        Boton.setForeground(Color.WHITE);
        Boton.setBounds(200, 470, ancho_boton, alto_boton);
        Boton.setBackground(new Color(255, 153, 0));
        Boton.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mousePressed(MouseEvent e){
                VerPerfil ver = new VerPerfil();
                ver.setVisible(true);
                dispose();
            }
        });
        fondo.add(Boton);
    }

    public void Regresar(JLabel fondo,JFrame frame) {
        ImageIcon imagen = new ImageIcon("exit_icon.PNG");
        ImageIcon imagen_cambiada = new ImageIcon(imagen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton Regreso = new JButton(imagen_cambiada);
        Regreso.setBounds(275, 550, 50, 50);
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
