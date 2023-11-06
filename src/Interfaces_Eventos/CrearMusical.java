
package Interfaces_Eventos;

import Enumeraciones.TipoMus;
import Evento.EventoMusical;
import Interfaces_Generales.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import manejo_de_usuarios.UserManager;
import objeto_usuario.UserContenido;
import objeto_usuario.UserAdmin;

public class CrearMusical extends JFrame {
     private int Ancho = 700, Alto = 700, ancho_boton = 150, alto_boton = 50;
    private TipoMus seleccionado;
    public CrearMusical() {
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
        
        JLabel Titulo = new JLabel("Evento Musical");
        Titulo.setBounds(250, 30, 200, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);
        
        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(80-25, 200-10, 200, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);
        JTextField c_person = new JTextField();
        c_person.setBounds(300-25, 200, 200, 30);
        fondo.add(cant_person);
        fondo.add(c_person);
                
        JLabel label_Titulo_evento = new JLabel("       titulo del evento:");
        label_Titulo_evento.setBounds(100-25, 100-10, 170, 50);
        label_Titulo_evento.setFont(new Font("Arial", 0, 18));
        label_Titulo_evento.setForeground(Color.WHITE);
        
        JLabel label_monto_acordado = new JLabel("     Monto Acordado:");
        label_monto_acordado.setBounds(100-25,150-10 , 170, 50);
        label_monto_acordado.setFont(new Font("Arial", 0, 18));
        label_monto_acordado.setForeground(Color.WHITE);

        
        JLabel label_Descripcion  = new JLabel("             Descripcion:");
        label_Descripcion.setBounds(100-25, 240, 170, 50);
        label_Descripcion.setFont(new Font("Arial", 0, 18));
        label_Descripcion.setForeground(Color.WHITE);
        
        JLabel label = new JLabel("Tipo de Musica");
        label.setBounds(490, 170, 100, 30);
        label.setForeground(Color.WHITE);
        
        fondo.add(label);
        
        JComboBox comboBox = new JComboBox<>(TipoMus.values());
        comboBox.setBounds(300+190, 200, 150, 50);
        fondo.add(comboBox);
        comboBox.addActionListener((ActionEvent e) -> {
            seleccionado = (TipoMus) comboBox.getSelectedItem();
        });
        
        JTextField titulo_evento = new JTextField();
        titulo_evento.setBounds(300-25, 100, 200, 30);
        JLabel cod = new JLabel("Codigo");
        cod.setBounds(475+15, 300, 110, 20);
        fondo.add(cod);
        JTextField code = new JTextField();
        code.setBounds(475+15, 320,110 , 30);
        int id = obtenerNuevoId();
        code.setText(id+"");
        fondo.add(code);
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        scrollPane.setBounds(300 - 25, 250, 200, 100);
        
        JTextField monto = new JTextField();
        monto.setBounds(300-25, 150, 200, 30);
        
        
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
        
        JButton cambio = new JButton("Cambio");
        cambio.setFont(new Font("Arial",0,20));
        cambio.setForeground(Color.WHITE);
        cambio.setBounds(75+(ancho_boton*2)+100,500+alto_boton,150,50);
        cambio.setBackground(Color.YELLOW);
        cambio.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
        tipo_event();
        }
     });
        
        fondo.add(cambio);
        fondo.add(Exit);
     
    
        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", 0, 18));
        confirmar.setBounds(75,500+alto_boton , ancho_boton, alto_boton);
        confirmar.setBackground(new Color(0, 204, 0));
        confirmar.setForeground(new Color(255, 255, 255));
        confirmar.addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent e) {
        double monto_evento;
        int cantidad;
        if(!titulo_evento.getText().isBlank()&&!descripcion.getText().isBlank()&&!monto.getText().isBlank()){
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
            if(cantidad<=25000){
            if(seleccionado!=null){              
                Login.MANEJOEVENTOS.AddEvent(new EventoMusical(id, titulo_evento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, asignar(monto_evento),cantidad, seleccionado,0,2,new ArrayList<>(),false), id);
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
             JOptionPane.showMessageDialog(null, "no ha seleccionado ningun genero musical");       
            }
            }else{
               JOptionPane.showMessageDialog(null, "Ingrese valor menor a 25000"); 
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
        fondo.add(confirmar);
    
        
        
        
        
        fondo.add(scrollPane);
        fondo.add(Titulo);
        fondo.add(label_Descripcion);
        fondo.add(label_monto_acordado);
        fondo.add(label_Titulo_evento);
        fondo.add(titulo_evento);
        fondo.add(monto);
  
        
    }
        public void tipo_event() {
        JFrame frame = new JFrame("¿Cambio de evento?");
        JPanel panel = new JPanel();

        JRadioButton deportivo = new JRadioButton("Deportivo");
        JRadioButton religioso = new JRadioButton("Religioso");

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
            CrearRel crea = new CrearRel();
            crea.setVisible(true);
            frame.dispose();
            dispose();
        });
    }
    private int obtenerNuevoId() {
    int nuevoId = 0;
    while (Login.MANEJOEVENTOS.buscar(nuevoId) != null) {
        nuevoId++;
    }
    return nuevoId;
}
    public double asignar(double monto){
       double agregado = monto*0.3;
       return monto+agregado;
    }
    
}
