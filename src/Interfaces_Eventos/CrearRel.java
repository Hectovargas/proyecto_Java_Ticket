
package Interfaces_Eventos;
import Evento.EventoReligioso;
import Interfaces_Generales.Login;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import manejo_de_usuarios.UserManager;
import objeto_usuario.*;

public class CrearRel extends JFrame {
     private int Ancho = 700, Alto = 700, ancho_boton = 150, alto_boton = 50;
     @SuppressWarnings("LeakingThisInConstructor")
     
     public CrearRel () {
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

    private void elementos(JLabel fondo) {
        
        JLabel Titulo = new JLabel("Evento Religioso");
        Titulo.setBounds(250, 30, 200, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);
        
        JLabel labelTituloevento = new JLabel("       titulo del evento:");
        labelTituloevento.setBounds(100-25, 100-10, 170, 50);
        labelTituloevento.setFont(new Font("Arial", 0, 18));
        labelTituloevento.setForeground(Color.WHITE);
        
        JLabel labelmonto = new JLabel("     Monto Acordado:");
        labelmonto.setBounds(100-25,150-10 , 170, 50);
        labelmonto.setFont(new Font("Arial", 0, 18));
        labelmonto.setForeground(Color.WHITE);

        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(80-25, 200-10, 200, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);
        JTextField c_person = new JTextField();
        c_person.setBounds(300-25, 200, 200, 30);
        fondo.add(cant_person);
        fondo.add(c_person);
        
        
        JLabel labelDescripcion  = new JLabel("             Descripcion:");
        labelDescripcion.setBounds(100-25, 240, 170, 50);
        labelDescripcion.setFont(new Font("Arial", 0, 18));
        labelDescripcion.setForeground(Color.WHITE);
        
        JTextField tituloevento = new JTextField();
        tituloevento.setBounds(300-25, 100, 200, 30);
        
         JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane DescripcionFinal = new JScrollPane(descripcion);
        DescripcionFinal.setPreferredSize(new Dimension(200, 100));
        DescripcionFinal.setBounds(300 - 25, 250, 200, 100);
        
        JTextField monto = new JTextField();
        monto.setBounds(300-25, 150, 200, 30);
        JLabel cod = new JLabel("Codigo");
        cod.setBounds(475+15, 180, 110, 20);
        fondo.add(cod);
        JTextField code = new JTextField();
        code.setBounds(475+15, 200,110 , 30);
        int id = CREARID();
        code.setText(id+"");
        fondo.add(code);
        
        JButton cambio = new JButton("Cambio");
        cambio.setFont(new Font("Arial",0,20));
        cambio.setForeground(Color.WHITE);
        cambio.setBounds(75+(ancho_boton*2)+100,500+alto_boton,150,50);
        cambio.setBackground(Color.YELLOW);
        cambio.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            ELECCIONEVENTO();
        }
     });
        
        fondo.add(cambio);
         JButton fecha = new JButton("Colocar fecha");
        fecha.setFont(new Font("Arial", 0, 18));
        fecha.setBounds(300+190,100 , ancho_boton, alto_boton);
        fecha.setBackground(new Color(0, 153, 153));
        fecha.setForeground(new Color(255, 255, 255));
        fecha.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
         CalendarioGeneral user = new CalendarioGeneral();
         user.setVisible(true);
        }
        
    });
        fondo.add(fecha);
        JButton Exit = new JButton("Salir");
        Exit.setFont(new Font("Arial",0,20));
        Exit.setForeground(Color.WHITE);
        Exit.setBounds(75+ancho_boton+50,500+alto_boton,150,50);
        Exit.setBackground(Color.red);
        Exit.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
        MenuEvent log = new MenuEvent();
        log.setVisible(true);
        CalendarioGeneral.FECHASELECCIONADA=null;
        dispose();
        }
     });
        fondo.add(Exit);
    
        JButton boton = new JButton("Confirmar");
        boton.setFont(new Font("Arial", 0, 18));
        boton.setBounds(75,500+alto_boton , ancho_boton, alto_boton);
        boton.setBackground(new Color(0, 204, 0));
        boton.setForeground(new Color(255, 255, 255));
        boton.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
        double monto_evento;
        int cantidad;
        if(!tituloevento.getText().isBlank()&&!descripcion.getText().isBlank()&&!monto.getText().isBlank()&&!c_person.getText().isBlank()){
        if(CalendarioGeneral.FECHASELECCIONADA!=null){
            
            try {
                monto_evento = Double.parseDouble(monto.getText());
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "El monto tiene que ser un numero");
                return;
            } 
            
            try {
                cantidad = Integer.parseInt(c_person.getText());
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "la cantidad tiene que ser un numero entero");
                return;
            } 
            
            if(cantidad>=0 && monto_evento>=0){
            if(cantidad<=30000){
               
                Login.MANEJOEVENTOS.AddEvent(new EventoReligioso(id, tituloevento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, MONTOEXTRA(monto_evento), cantidad,0,0,3,false), id);
                JOptionPane.showMessageDialog(null, "Se ha creado el evento");
                
                MenuEvent log = new MenuEvent();
                log.setVisible(true);
                CalendarioGeneral.FECHASELECCIONADA = null;
                
                if(Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario()==1){
                UserAdmin us = (UserAdmin) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
                us.AgregarId(id);
                }
                
                if(Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario()==2){
                UserContenido us = (UserContenido) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
                us.AgregarId(id);    
                }
                dispose();
            
            
            }else{
               JOptionPane.showMessageDialog(null, "Ingrese valor menor a 30000"); 
            }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un numero mayor a 0");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Necesita Seleccionar una fecha");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Necesita llenar todos los campos");
        }
        
        
        }
    });
        fondo.add(Titulo);
        fondo.add(labelDescripcion);
        fondo.add(labelmonto);
        fondo.add(labelTituloevento);
        fondo.add(tituloevento);
        fondo.add(DescripcionFinal);
        fondo.add(monto);
        fondo.add(boton); 
    }
    
    private int CREARID() {
    int nuevoId = 0;
    while (Login.MANEJOEVENTOS.buscar(nuevoId) != null) {
        nuevoId++;
    }
    return nuevoId;
}
     public void ELECCIONEVENTO() {
        JFrame frame = new JFrame("¿Cambio de evento?");
        JPanel panel = new JPanel();

        JRadioButton deportivo = new JRadioButton("Deportivo");
        JRadioButton religioso = new JRadioButton("Musical");

        ButtonGroup group = new ButtonGroup();
        group.add(deportivo);
        group.add(religioso);

        panel.add(deportivo);
        panel.add(religioso);

        frame.add(panel);
        frame.setSize(250, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        deportivo.addActionListener((ActionEvent e) -> {
            CrearDep crea = new CrearDep();
            crea.setVisible(true);
            frame.dispose();
            dispose();
        });
        religioso.addActionListener((ActionEvent e) -> {
            CrearMusical crea = new CrearMusical();
            crea.setVisible(true);
            frame.dispose();
            dispose();
        });
     }
     public double MONTOEXTRA(double monto){
       return monto+2000;
    }
}

