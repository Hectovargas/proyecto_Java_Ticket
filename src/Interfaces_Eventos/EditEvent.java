package Interfaces_Eventos;

import Enumeraciones.Tipodep;
import Enumeraciones.TipoMus;
import Evento.EventoDeportivo;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EditEvent extends JFrame {

    private ArrayList arreglo1 = new ArrayList();
    private ArrayList arreglo2 = new ArrayList();
    private int Ancho = 800, Alto = 700, ancho_boton = 150, alto_boton = 50;
    private Tipodep seleccionado;
    private TipoMus seleccion;
    
    public EditEvent() {
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
        
        menu.add(fondo);
        add(menu);
        TipoEvento(fondo);
    }
    
    private void TipoEvento(JLabel fondo) {
        int eleccion = MenuEvent.TIPOEDIT;
        switch (eleccion) {
            case 1:
                Deportes(fondo);
                break;
            case 2:
                musica(fondo);
                break;
            case 3:
                Religioso(fondo);
                break;
        }
    }
    
    public void musica(JLabel fondo) {
        JLabel Titulo = new JLabel("Editar Evento musical");
        Titulo.setBounds(250, 10, 280, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);
        
        JLabel label_Titulo_evento = new JLabel("titulo del evento:");
        label_Titulo_evento.setBounds(20, 70 - 10, 170, 50);
        label_Titulo_evento.setFont(new Font("Arial", 0, 18));
        label_Titulo_evento.setForeground(Color.WHITE);
        
        JLabel label_monto_acordado = new JLabel("Monto Acordado:");
        label_monto_acordado.setBounds(20, 120 - 10, 170, 50);
        label_monto_acordado.setFont(new Font("Arial", 0, 18));
        label_monto_acordado.setForeground(Color.WHITE);
        
        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(20, 170 - 10, 190, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);
        
        JLabel label_Descripcion = new JLabel("Descripcion:");
        label_Descripcion.setBounds(20, 220 - 10, 170, 50);
        label_Descripcion.setFont(new Font("Arial", 0, 18));
        label_Descripcion.setForeground(Color.WHITE);
        
        JLabel integrantes = new JLabel("nombre : ");
        integrantes.setBounds(420, 110, 100, 50);
        integrantes.setFont(new Font("Arial", 0, 18));
        integrantes.setForeground(Color.WHITE);
        
        JLabel integrantes_2 = new JLabel("Cargo : ");
        integrantes_2.setBounds(420, 160, 100, 50);
        integrantes_2.setFont(new Font("Arial", 0, 18));
        integrantes_2.setForeground(Color.WHITE);
        
        JTextField integrante_1 = new JTextField();
        integrante_1.setBounds(420 + 100 + 10, 120, 180, 30);
        
        JTextField Cargo = new JTextField();
        Cargo.setBounds(420 + 100 + 10, 170, 180, 30);
        
        JComboBox comboBox = new JComboBox<>(TipoMus.values());
        comboBox.setBounds(400 + 20, 220, 150, 50);
        fondo.add(comboBox);
        comboBox.addActionListener((ActionEvent e) -> {
            seleccion = (TipoMus) comboBox.getSelectedItem();
        });
        
        JTextField titulo_evento = new JTextField();
        titulo_evento.setBounds(300 - 80, 70, 180, 30);
        
        JTextField c_person = new JTextField();
        c_person.setBounds(300 - 80, 170, 180, 30);
        
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        scrollPane.setBounds(300 - 80, 220, 180, 100);
        
        JTextField monto = new JTextField();
        monto.setBounds(300 - 80, 120, 180, 30);
        
        titulo_evento.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getTitulo());
        monto.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getMontoRenta() + "");
        c_person.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getCantidad_personas() + "");
        descripcion.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getDescripcion());
        CalendarioGeneral.FECHASELECCIONADA = Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getFecha();
        
        EventoMusical eveM = (EventoMusical) Login.MANEJOEVENTOS.buscar(MenuEvent.CODE);
        seleccion = eveM.getTipoMusica();
        arreglo1.addAll(eveM.getArrayList());
        System.out.println(arreglo1);
        
        JTextField mostrar_tipo = new JTextField();
        mostrar_tipo.setBounds(400 + 20 + 25, 285, 100, 30);
        mostrar_tipo.setText(seleccion.toString());
        mostrar_tipo.setEditable(false);
        fondo.add(mostrar_tipo);
        
        JTextField mostrar_fecha = new JTextField();
        mostrar_fecha.setBounds(400 + 190, 285, 150, 30);
        mostrar_fecha.setText(CalendarioGeneral.FECHASELECCIONADA.toString());
        mostrar_fecha.setFont(new Font("arial", 0, 12));
        mostrar_fecha.setEditable(false);
        fondo.add(mostrar_fecha);
        
        JButton fecha = new JButton("Colocar fecha");
        fecha.setFont(new Font("Arial", 0, 18));
        fecha.setBounds(400 + 190, 220, ancho_boton, alto_boton);
        fecha.setBackground(new Color(0, 153, 153));
        fecha.setForeground(new Color(255, 255, 255));
        fecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CalendarioGeneral user = new CalendarioGeneral();
                user.setVisible(true);
            }
            
        });
        fondo.add(fecha);
        
        JButton Exit = new JButton("Salir");
        Exit.setFont(new Font("Arial", 0, 20));
        Exit.setForeground(Color.WHITE);
        Exit.setBounds(800 - ancho_boton - 100, 500 + alto_boton, 150, 50);
        Exit.setBackground(Color.red);
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuEvent log = new MenuEvent();
                log.setVisible(true);
                CalendarioGeneral.FECHASELECCIONADA = null;
                dispose();
            }
        });
        fondo.add(Exit);
        
        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", 0, 18));
        confirmar.setBounds(100, 500 + alto_boton, ancho_boton, alto_boton);
        confirmar.setBackground(new Color(0, 204, 0));
        confirmar.setForeground(new Color(255, 255, 255));
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double monto_evento;
                int cantidad;
                if (!titulo_evento.getText().isBlank() && !descripcion.getText().isBlank() && !monto.getText().isBlank() && !c_person.getText().isBlank()) {
                    if (CalendarioGeneral.FECHASELECCIONADA != null) {
                        try {
                            monto_evento = Double.parseDouble(monto.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "el monto tiene que ser un numero");
                            return;
                        }                        
                        
                        try {
                            cantidad = Integer.parseInt(c_person.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "la cantidad tiene que ser un numero entero");
                            return;
                        }                        
                        
                        if (cantidad >= 0 && monto_evento >= 0) {
                            if (cantidad <= 25000) {
                                if (seleccion != null) {
                                    if (!arreglo1.isEmpty()) {
                                        Login.MANEJOEVENTOS.editar_musical(MenuEvent.CODE, arreglo1, seleccion, titulo_evento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, monto_evento, cantidad);
                                        MenuEvent menu = new MenuEvent();
                                        eveM.AgregarNombre1(arreglo1);
                                        CalendarioGeneral.FECHASELECCIONADA = null;
                                        arreglo1.clear();
                                        System.out.println(eveM.getArrayList());
                                        menu.setVisible(true);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Necesita llenar la lista de miembros");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "no ha seleccionado ningun tipo de musica");                                    
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "no puede haber mas de 20000 personas");                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "tiene que haber una cantidad positiva");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Necesita Seleccionar una fecha");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Necesita llenar todos los campos");
                }
            }
            
        });
        fondo.add(confirmar);
        JButton add1 = new JButton("+");
        add1.setFont(new Font("Arial", 0, 10));
        add1.setForeground(Color.BLACK);
        add1.setBounds(420 + 120 + 180 + 5, 140, 50, 30);
        add1.setBackground(Color.YELLOW);
        add1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!integrante_1.getText().isBlank() && !Cargo.getText().isBlank()) {
                    arreglo1.add(integrante_1.getText() + " a cargo de:" + Cargo.getText());
                    Cargo.setText("");
                    System.out.println(arreglo1);
                    integrante_1.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "no puede dejar datos en blanco");
                }
            }
        });
        fondo.add(add1);
        
        JButton reiniciar_lista_1 = new JButton("Reiniciar lista");
        reiniciar_lista_1.setFont(new Font("Arial", 0, 11));
        reiniciar_lista_1.setForeground(Color.BLACK);
        reiniciar_lista_1.setBounds(530 + 30, 70, 120, 40);
        reiniciar_lista_1.setBackground(Color.YELLOW);
        reiniciar_lista_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                arreglo1.clear();
                EventoMusical eve = (EventoMusical) Login.MANEJOEVENTOS.buscar(MenuEvent.CODE);
                eve.eliminarArreglo();
                JOptionPane.showMessageDialog(null, "La lista de integrantes se ha reiniciado");
            }
        });
        fondo.add(reiniciar_lista_1);
        
        fondo.add(scrollPane);
        fondo.add(Titulo);
        fondo.add(label_Descripcion);
        fondo.add(label_monto_acordado);
        fondo.add(label_Titulo_evento);
        fondo.add(titulo_evento);
        fondo.add(monto);
        fondo.add(c_person);
        fondo.add(cant_person);
        fondo.add(integrantes);
        fondo.add(integrantes_2);
        fondo.add(integrante_1);
        fondo.add(Cargo);
    }

    public void Religioso(JLabel fondo) {
        JLabel Titulo = new JLabel("Editar Evento Religioso");
        Titulo.setBounds(250, 10, 280, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);
        
        JLabel label_Titulo_evento = new JLabel("titulo del evento:");
        label_Titulo_evento.setBounds(20, 70 - 10, 170, 50);
        label_Titulo_evento.setFont(new Font("Arial", 0, 18));
        label_Titulo_evento.setForeground(Color.WHITE);
        
        JLabel label_monto_acordado = new JLabel("Monto Acordado:");
        label_monto_acordado.setBounds(20, 120 - 10, 170, 50);
        label_monto_acordado.setFont(new Font("Arial", 0, 18));
        label_monto_acordado.setForeground(Color.WHITE);
        
        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(20, 170 - 10, 190, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);
        
        JLabel label_Descripcion = new JLabel("Descripcion:");
        label_Descripcion.setBounds(20, 270 - 10, 170, 50);
        label_Descripcion.setFont(new Font("Arial", 0, 18));
        label_Descripcion.setForeground(Color.WHITE);
        
        JLabel p_convert = new JLabel("personas convertidas:");
        p_convert.setBounds(20, 220 - 10, 190, 50);
        p_convert.setFont(new Font("Arial", 0, 18));
        p_convert.setForeground(Color.WHITE);
        fondo.add(p_convert);
        
        JTextField titulo_evento = new JTextField();
        titulo_evento.setBounds(300 - 80, 70, 180, 30);
        
        JTextField c_person = new JTextField();
        c_person.setBounds(300 - 80, 170, 180, 30);
        
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        scrollPane.setBounds(300 - 80, 270, 180, 100);
        
        JTextField monto = new JTextField();
        monto.setBounds(300 - 80, 120, 180, 30);
        
        JTextField convertidas = new JTextField();
        convertidas.setBounds(300-80, 220, 180, 30);
        fondo.add(convertidas);
        
        titulo_evento.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getTitulo());
        monto.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getMontoRenta() + "");
        c_person.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getCantidad_personas() + "");
        descripcion.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getDescripcion());
        CalendarioGeneral.FECHASELECCIONADA = Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getFecha();
        
        JTextField mostrar_fecha = new JTextField();
        mostrar_fecha.setBounds(400 + 90, 235, 150, 30);
        mostrar_fecha.setText(CalendarioGeneral.FECHASELECCIONADA.toString());
        mostrar_fecha.setFont(new Font("arial", 0, 12));
        mostrar_fecha.setEditable(false);
        fondo.add(mostrar_fecha);
        
        JButton fecha = new JButton("Colocar fecha");
        fecha.setFont(new Font("Arial", 0, 18));
        fecha.setBounds(400 + 90, 170, ancho_boton, alto_boton);
        fecha.setBackground(new Color(0, 153, 153));
        fecha.setForeground(new Color(255, 255, 255));
        fecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CalendarioGeneral user = new CalendarioGeneral();
                user.setVisible(true);
            }
            
        });
        fondo.add(fecha);
        
        JButton Exit = new JButton("Salir");
        Exit.setFont(new Font("Arial", 0, 20));
        Exit.setForeground(Color.WHITE);
        Exit.setBounds(800 - ancho_boton - 100, 500 + alto_boton, 150, 50);
        Exit.setBackground(Color.red);
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuEvent log = new MenuEvent();
                log.setVisible(true);
                CalendarioGeneral.FECHASELECCIONADA = null;
                dispose();
            }
        });
        fondo.add(Exit);
        
        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", 0, 18));
        confirmar.setBounds(100, 500 + alto_boton, ancho_boton, alto_boton);
        confirmar.setBackground(new Color(0, 204, 0));
        confirmar.setForeground(new Color(255, 255, 255));
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double monto_evento;
                int cantidad;
                int convertido;
                if (!titulo_evento.getText().isBlank() && !descripcion.getText().isBlank() && !monto.getText().isBlank() && !c_person.getText().isBlank()&&!convertidas.getText().isBlank()) {
                    if (CalendarioGeneral.FECHASELECCIONADA != null) {
                        try {
                            monto_evento = Double.parseDouble(monto.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "el monto tiene que ser un numero");
                            return;
                        }            
                        
                         try {
                            convertido= Integer.parseInt(convertidas.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "las personas convertidas tienen que ser un numero entero");
                            return;
                        }            
                        
                        
                        try {
                            cantidad = Integer.parseInt(c_person.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "la cantidad de personas tienen que ser un numero entero");
                            return;
                        }                        
                        
                        if (cantidad >= 0 && monto_evento >= 0) {
                            if (cantidad <= 30000) {
                                Login.MANEJOEVENTOS.editar_religioso(MenuEvent.CODE, convertido, titulo_evento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, monto_evento, cantidad);
                                MenuEvent menu = new MenuEvent();
                                CalendarioGeneral.FECHASELECCIONADA = null;
                                menu.setVisible(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "no puede haber mas de 20000 personas");                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "tiene que haber una cantidad positiva");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Necesita Seleccionar una fecha");
                    }
                } else {
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
        fondo.add(c_person);
        fondo.add(cant_person);
        
    }

    public void Deportes(JLabel fondo) {
        JLabel Titulo = new JLabel("Editar Evento Deportivo");
        Titulo.setBounds(270, 10, 280, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);
        
        JLabel label_Titulo_evento = new JLabel("titulo del evento:");
        label_Titulo_evento.setBounds(20, 70 - 10, 170, 50);
        label_Titulo_evento.setFont(new Font("Arial", 0, 18));
        label_Titulo_evento.setForeground(Color.WHITE);
        
        JLabel label_monto_acordado = new JLabel("Monto Acordado:");
        label_monto_acordado.setBounds(20, 120 - 10, 170, 50);
        label_monto_acordado.setFont(new Font("Arial", 0, 18));
        label_monto_acordado.setForeground(Color.WHITE);
        
        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(20, 170 - 10, 190, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);
        
        JLabel label_Titulo_equip_1 = new JLabel("nombre del equipo 1:");
        label_Titulo_equip_1.setBounds(20, 220 - 10, 170, 50);
        label_Titulo_equip_1.setFont(new Font("Arial", 0, 18));
        label_Titulo_equip_1.setForeground(Color.WHITE);
        
        JLabel label_Titulo_equip_2 = new JLabel("nombre del equipo 2:");
        label_Titulo_equip_2.setBounds(20, 270 - 10, 170, 50);
        label_Titulo_equip_2.setFont(new Font("Arial", 0, 18));
        label_Titulo_equip_2.setForeground(Color.WHITE);
        
        JLabel label_Descripcion = new JLabel("Descripcion:");
        label_Descripcion.setBounds(20, 320 - 10, 170, 50);
        label_Descripcion.setFont(new Font("Arial", 0, 18));
        label_Descripcion.setForeground(Color.WHITE);
        
        JLabel label = new JLabel("Tipo de deporte");
        label.setBounds(445, 290, 100, 30);
        label.setForeground(Color.WHITE);
        
        fondo.add(label);
        
        JLabel integrantes = new JLabel("Equipo 1: ");
        integrantes.setBounds(420, 110, 100, 50);
        integrantes.setFont(new Font("Arial", 0, 18));
        integrantes.setForeground(Color.WHITE);
        
        JLabel integrantes_2 = new JLabel("Equipo 2: ");
        integrantes_2.setBounds(420, 160, 100, 50);
        integrantes_2.setFont(new Font("Arial", 0, 18));
        integrantes_2.setForeground(Color.WHITE);
        
        JTextField integrante_1 = new JTextField();
        integrante_1.setBounds(420 + 100 + 10, 120, 180, 30);
        
        JTextField integrante_2 = new JTextField();
        integrante_2.setBounds(420 + 100 + 10, 170, 180, 30);
        
        JComboBox comboBox = new JComboBox<>(Tipodep.values());
        comboBox.setBounds(400 + 20, 320, 150, 50);
        fondo.add(comboBox);
        comboBox.addActionListener((ActionEvent e) -> {
            seleccionado = (Tipodep) comboBox.getSelectedItem();
        });
        
        JTextField titulo_evento = new JTextField();
        titulo_evento.setBounds(300 - 80, 70, 180, 30);
        
        JTextField c_person = new JTextField();
        c_person.setBounds(300 - 80, 170, 180, 30);
        
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(180, 100));
        scrollPane.setBounds(300 - 80, 320, 180, 100);
        
        JTextField monto = new JTextField();
        monto.setBounds(300 - 80, 120, 180, 30);
        
        titulo_evento.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getTitulo());
        monto.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getMontoRenta() + "");
        c_person.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getCantidad_personas() + "");
        descripcion.setText(Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getDescripcion());
        CalendarioGeneral.FECHASELECCIONADA = Login.MANEJOEVENTOS.buscar(MenuEvent.CODE).getFecha();
        
        JTextField equipo_1 = new JTextField();
        equipo_1.setBounds(300 - 80, 220, 180, 30);
        
        JTextField equipo_2 = new JTextField();
        equipo_2.setBounds(300 - 80, 270, 180, 30);
        
        EventoDeportivo eve = (EventoDeportivo) Login.MANEJOEVENTOS.buscar(MenuEvent.CODE);
        equipo_1.setText(eve.getNombreEquipo1());
        equipo_2.setText(eve.getNombreEquipo2());
        seleccionado = eve.getTipoDeporte();
        arreglo1.addAll(eve.getArrayList());
        arreglo2.addAll(eve.getArrayList2());
        
        JTextField mostrar_tipo = new JTextField();
        mostrar_tipo.setBounds(400 + 20 + 25, 385, 100, 30);
        mostrar_tipo.setText(seleccionado.toString());
        mostrar_tipo.setEditable(false);
        fondo.add(mostrar_tipo);
        
        JTextField mostrar_fecha = new JTextField();
        mostrar_fecha.setBounds(400 + 190, 385, 150, 30);
        mostrar_fecha.setText(CalendarioGeneral.FECHASELECCIONADA.toString());
        mostrar_fecha.setFont(new Font("arial", 0, 12));
        mostrar_fecha.setEditable(false);
        fondo.add(mostrar_fecha);
        
        JButton fecha = new JButton("Colocar fecha");
        fecha.setFont(new Font("Arial", 0, 18));
        fecha.setBounds(400 + 190, 320, ancho_boton, alto_boton);
        fecha.setBackground(new Color(0, 153, 153));
        fecha.setForeground(new Color(255, 255, 255));
        fecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CalendarioGeneral user = new CalendarioGeneral();
                user.setVisible(true);
            }
            
        });
        fondo.add(fecha);
        
        JButton Exit = new JButton("Salir");
        Exit.setFont(new Font("Arial", 0, 20));
        Exit.setForeground(Color.WHITE);
        Exit.setBounds(800 - ancho_boton - 100, 500 + alto_boton, 150, 50);
        Exit.setBackground(Color.red);
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuEvent log = new MenuEvent();
                log.setVisible(true);
                CalendarioGeneral.FECHASELECCIONADA = null;
                dispose();
            }
        });
        fondo.add(Exit);
        
        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", 0, 18));
        confirmar.setBounds(100, 500 + alto_boton, ancho_boton, alto_boton);
        confirmar.setBackground(new Color(0, 204, 0));
        confirmar.setForeground(new Color(255, 255, 255));
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double monto_evento;
                int cantidad;
                if (!titulo_evento.getText().isBlank() && !descripcion.getText().isBlank() && !equipo_2.getText().isBlank() && !equipo_1.getText().isBlank() && !monto.getText().isBlank() && !c_person.getText().isBlank()) {
                    if (CalendarioGeneral.FECHASELECCIONADA != null) {
                        try {
                            monto_evento = Double.parseDouble(monto.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "el monto tiene que ser un numero");
                            return;
                        }                        
                        
                        try {
                            cantidad = Integer.parseInt(c_person.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "la cantidad tiene que ser un numero entero");
                            return;
                        }                        
                        
                        if (cantidad >= 0 && monto_evento >= 0) {
                            if (cantidad <= 20000) {
                                if (seleccionado != null) {
                                    if (!arreglo1.isEmpty() && !arreglo2.isEmpty()) {
                                        Login.MANEJOEVENTOS.editar_deportivo(MenuEvent.CODE, equipo_1.getText(), equipo_2.getText(), seleccionado, arreglo1, arreglo2, titulo_evento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, monto_evento, cantidad);
                                        MenuEvent menu = new MenuEvent();
                                        System.out.println(eve.getArrayList());
                                        System.out.println(eve.getArrayList2());
                                        CalendarioGeneral.FECHASELECCIONADA = null;
                                        arreglo1.clear();
                                        arreglo2.clear();
                                        menu.setVisible(true);
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Necesita llenar la lista de miembros de cada equipo");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "no ha seleccionado ningun tipo de deporte");                                    
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "no puede haber mas de 20000 personas");                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "tiene que haber una cantidad positiva");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Necesita Seleccionar una fecha");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Necesita llenar todos los campos");
                }
            }
            
        });
        fondo.add(confirmar);
        JButton add1 = new JButton("+");
        add1.setFont(new Font("Arial", 0, 10));
        add1.setForeground(Color.BLACK);
        add1.setBounds(420 + 120 + 180 + 5, 120, 50, 30);
        add1.setBackground(Color.YELLOW);
        add1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!integrante_1.getText().isBlank()) {
                    arreglo1.add(integrante_1.getText());
                    System.out.println(arreglo1);
                    integrante_1.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "no puede ingresar nombres en blanco");
                }
            }
        });
        fondo.add(add1);
        JButton add2 = new JButton("+");
        add2.setFont(new Font("Arial", 0, 10));
        add2.setForeground(Color.BLACK);
        add2.setBounds(420 + 120 + 180 + 5, 170, 50, 30);
        add2.setBackground(Color.GREEN);
        add2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!integrante_2.getText().isBlank()) {
                    arreglo2.add(integrante_2.getText());
                    integrante_2.setText("");
                    System.out.println(arreglo2);
                } else {
                    JOptionPane.showMessageDialog(null, "no puede ingresar nombres en blanco");
                }
            }
        });
        fondo.add(add2);
        
        JButton reiniciar_lista_1 = new JButton("Reiniciar equipo 1");
        reiniciar_lista_1.setFont(new Font("Arial", 0, 11));
        reiniciar_lista_1.setForeground(Color.BLACK);
        reiniciar_lista_1.setBounds(460, 70, 120, 40);
        reiniciar_lista_1.setBackground(Color.YELLOW);
        reiniciar_lista_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                arreglo1.clear();
                EventoDeportivo eve = (EventoDeportivo) Login.MANEJOEVENTOS.buscar(MenuEvent.CODE);
                eve.eliminarArreglo1();
                JOptionPane.showMessageDialog(null, "La lista de integrantes del equipo " + equipo_1.getText() + " se ha reiniciado");
            }
        });
        fondo.add(reiniciar_lista_1);
        
        JButton reiniciar_lista_2 = new JButton("Reiniciar equipo 2");
        reiniciar_lista_2.setFont(new Font("Arial", 0, 11));
        reiniciar_lista_2.setForeground(Color.BLACK);
        reiniciar_lista_2.setBounds(420 + 200, 70, 120, 40);
        reiniciar_lista_2.setBackground(Color.GREEN);
        reiniciar_lista_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                arreglo2.clear();
                EventoDeportivo eve = (EventoDeportivo) Login.MANEJOEVENTOS.buscar(MenuEvent.CODE);
                eve.EliminarArreglo2();
                JOptionPane.showMessageDialog(null, "La lista de integrantes del equipo " + equipo_2.getText() + " se ha reiniciado");
            }
        });
        
        fondo.add(reiniciar_lista_2);
        fondo.add(scrollPane);
        fondo.add(Titulo);
        fondo.add(label_Descripcion);
        fondo.add(label_Titulo_equip_1);
        fondo.add(label_Titulo_equip_2);
        fondo.add(label_monto_acordado);
        fondo.add(label_Titulo_evento);
        fondo.add(titulo_evento);
        fondo.add(monto);
        fondo.add(equipo_1);
        fondo.add(equipo_2);
        fondo.add(c_person);
        fondo.add(cant_person);
        fondo.add(integrantes);
        fondo.add(integrantes_2);
        fondo.add(integrante_1);
        fondo.add(integrante_2);
        
    }
    
}
