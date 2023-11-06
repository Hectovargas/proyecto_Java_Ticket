package Reportes;

import Interfaces_Generales.Login;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
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



public class IGPF extends JFrame{
    private java.util.Date selectedDate;
    private java.util.Date selectedDate2;
    private int Ancho = 600, Alto = 600;
        public IGPF(){
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
        JLabel label = new JLabel("Ingresos Generados por fecha");
        label.setBounds(100, 20, 350, 55);
        label.setFont(new Font("Calibri", 0, 28));
        label.setForeground(Color.white);
        //
        JLabel instruccion = new JLabel("fecha inicial");
        instruccion.setBounds(100, 70,120 , 25);
        instruccion.setFont(new Font("Arial", 0, 14));
        instruccion.setForeground(Color.white);
        fondo.add(instruccion);
        //
        JTextField textField = new JTextField();
        textField.setBounds(100,70+25+5 , 140, 20);
        textField.setEditable(false);
        // Crear un JButton para abrir el calendario
        JButton button = new JButton("elegir Fecha");
        
        button.setBounds(100, 70+25+5+20+5, 140, 40);
        button.addActionListener((ActionEvent e) -> {
            JCalendar calendar = new JCalendar();

                // Obtener el Calendar seleccionado
                Calendar selectedCalendarInicial = calendar.getCalendar();
                
                // Establecer las horas, minutos, segundos y milisegundos a cero
                selectedCalendarInicial.set(Calendar.HOUR_OF_DAY, 0);
                selectedCalendarInicial.set(Calendar.MINUTE, 0);
                selectedCalendarInicial.set(Calendar.SECOND, 0);
                selectedCalendarInicial.set(Calendar.MILLISECOND, 0);

                // Mostrar el Calendar actualizado
                calendar.setCalendar(selectedCalendarInicial);

                int result = JOptionPane.showOptionDialog(null, calendar, "Selecciona una Fecha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (result == JOptionPane.OK_OPTION) {
                     selectedDate = calendar.getDate();
                    textField.setText(selectedDate.toString());
                }
        });
        
        fondo.add(textField);
        fondo.add(button);
        
         JLabel instruccion2 = new JLabel("fecha Final");
        instruccion2.setBounds(350, 70,120 , 25);
        instruccion2.setFont(new Font("Arial", 0, 14));
        instruccion2.setForeground(Color.white);
        fondo.add(instruccion2);
        //
        JTextField textField2 = new JTextField();
        textField2.setBounds(350,70+25+5 , 140, 20);
        textField2.setEditable(false);
        // Crear un JButton para abrir el calendario
        JButton butto2 = new JButton("elegir Fecha");
        
       butto2.setBounds(350, 70+25+5+20+5, 140, 40);
        butto2.addActionListener((ActionEvent e) -> {
            JCalendar calendar = new JCalendar();

                // Obtener el Calendar seleccionado
                Calendar selectedCalendarFinal = calendar.getCalendar();
                
                // Establecer las horas, minutos, segundos y milisegundos a cero
                selectedCalendarFinal.set(Calendar.HOUR_OF_DAY, 23);
                selectedCalendarFinal.set(Calendar.MINUTE, 59);
                selectedCalendarFinal.set(Calendar.SECOND, 59);
                selectedCalendarFinal.set(Calendar.MILLISECOND, 999);

                // Mostrar el Calendar actualizado
                calendar.setCalendar(selectedCalendarFinal);

                int result = JOptionPane.showOptionDialog(null, calendar, "Selecciona una Fecha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (result == JOptionPane.OK_OPTION) {
                    selectedDate2 = calendar.getDate();
                    textField2.setText(selectedDate2.toString());
                }
        });
        fondo.add(textField2);
        fondo.add(butto2);
        
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setBounds(100, 200, 400, 250);
        //
        JButton ver = new JButton("Ver");
        ver.setBounds(100, 500, 150, 40);
        ver.setFont(new Font("Arial", 0, 20));
        ver.setBackground(new Color(255, 51, 0));
        ver.setForeground(Color.white);
        ver.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 if(selectedDate!=null&&selectedDate2!=null){
                if(selectedDate.before(selectedDate2)){
                   
                descripcion.setText(Login.MANEJOEVENTOS.Rango_de_eventos(selectedDate, selectedDate2));   
                if(descripcion.getText().isBlank()){
                    descripcion.setText("no ocurrieron eventos en este lapso de tiempo");
                }
                   
                }else{
                    JOptionPane.showMessageDialog(null, "La fecha de inicio tiene que estar cronologicamente antes que la fecha final");
                }
                 }else{
                        JOptionPane.showMessageDialog(null, "necesita seleccionar una fecha");
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

