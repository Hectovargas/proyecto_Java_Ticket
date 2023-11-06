package Interfaces_Eventos;

import Enumeraciones.*;
import Evento.*;
import Interfaces_Generales.Login;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import manejo_de_usuarios.UserManager;
import objeto_usuario.*;

public final class CrearDep extends JFrame{

    private int Ancho = 700, Alto = 700, ancho_boton = 150, alto_boton = 50;
    private Tipodep seleccionado;
    
    public CrearDep() {
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
   
    public void elementos(JLabel fondo) {

        JLabel Titulo = new JLabel("Evento Deportivo");
        Titulo.setBounds(250, 30, 200, 50);
        Titulo.setFont(new Font("Arial", 0, 26));
        Titulo.setForeground(Color.WHITE);

        JLabel label_Titulo_evento = new JLabel("       titulo del evento:");
        label_Titulo_evento.setBounds(100 - 25, 100 - 10, 170, 50);
        label_Titulo_evento.setFont(new Font("Arial", 0, 18));
        label_Titulo_evento.setForeground(Color.WHITE);

        JLabel label_monto_acordado = new JLabel("     Monto Acordado:");
        label_monto_acordado.setBounds(100 - 25, 150 - 10, 170, 50);
        label_monto_acordado.setFont(new Font("Arial", 0, 18));
        label_monto_acordado.setForeground(Color.WHITE);

        JLabel cant_person = new JLabel("Cantidad de personas:");
        cant_person.setBounds(80 - 25, 200 - 10, 200, 50);
        cant_person.setFont(new Font("Arial", 0, 18));
        cant_person.setForeground(Color.WHITE);

        JLabel label_Titulo_equip_1 = new JLabel("nombre del equipo 1:");
        label_Titulo_equip_1.setBounds(100 - 25, 250 - 10, 170, 50);
        label_Titulo_equip_1.setFont(new Font("Arial", 0, 18));
        label_Titulo_equip_1.setForeground(Color.WHITE);

        JLabel label_Titulo_equip_2 = new JLabel("nombre del equipo 2:");
        label_Titulo_equip_2.setBounds(100 - 25, 300 - 10, 170, 50);
        label_Titulo_equip_2.setFont(new Font("Arial", 0, 18));
        label_Titulo_equip_2.setForeground(Color.WHITE);

        JLabel label_Descripcion = new JLabel("             Descripcion:");
        label_Descripcion.setBounds(100 - 25, 350 - 10, 170, 50);
        label_Descripcion.setFont(new Font("Arial", 0, 18));
        label_Descripcion.setForeground(Color.WHITE);

        JLabel label = new JLabel("Tipo de deporte");
        label.setBounds(490, 270, 100, 30);
        label.setForeground(Color.WHITE);

        fondo.add(label);

        JComboBox comboBox = new JComboBox<>(Tipodep.values());
        comboBox.setBounds(300 + 190, 300, 150, 50);
        fondo.add(comboBox);
        comboBox.addActionListener((ActionEvent e) -> {
            seleccionado = (Tipodep) comboBox.getSelectedItem();
        });

        JTextField titulo_evento = new JTextField();
        titulo_evento.setBounds(300 - 25, 100, 200, 30);

        JTextField fielCantidad = new JTextField();
        fielCantidad.setBounds(300 - 25, 200, 200, 30);
        
        JTextField code = new JTextField();
        code.setBounds(475+15, 200,110 , 30);
        int id = obtenerNuevoId();
        code.setText(id+"");
        fondo.add(code);
        JLabel cod = new JLabel("Codigo");
        cod.setBounds(475+15, 180, 110, 20);
        fondo.add(cod);
        JTextArea descripcion = new JTextArea();
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcion);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        scrollPane.setBounds(300 - 25, 350, 200, 100);

        descripcion.setBounds(0, 0, 200, 100);

        JTextField monto = new JTextField();
        monto.setBounds(300 - 25, 150, 200, 30);

        JTextField equipo_1 = new JTextField();
        equipo_1.setBounds(300 - 25, 250, 200, 30);

        JTextField equipo_2 = new JTextField();
        equipo_2.setBounds(300 - 25, 300, 200, 30);

        JButton fecha = new JButton("Colocar fecha");
        fecha.setFont(new Font("Arial", 0, 18));
        fecha.setBounds(300 + 190, 100, ancho_boton, alto_boton);
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
        Exit.setBounds(75 + ancho_boton + 50, 500 + alto_boton, 150, 50);
        Exit.setBackground(Color.red);
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuEvent log = new MenuEvent();
                log.setVisible(true);
                dispose();
                CalendarioGeneral.FECHASELECCIONADA = null;
            }
        });
        fondo.add(Exit);

        JButton cambio = new JButton("Cambio");
        cambio.setFont(new Font("Arial", 0, 20));
        cambio.setForeground(Color.WHITE);
        cambio.setBounds(75 + (ancho_boton + ancho_boton) + 100, 500 + alto_boton, 150, 50);
        cambio.setBackground(Color.YELLOW);
        cambio.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tipo_event();
            }
        });

        fondo.add(cambio);

        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("Arial", 0, 18));
        confirmar.setBounds(75, 500 + alto_boton, ancho_boton, alto_boton);
        confirmar.setBackground(new Color(0, 204, 0));
        confirmar.setForeground(new Color(255, 255, 255));
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double monto_evento;
                int cantidad;
                if (!titulo_evento.getText().isBlank() && !descripcion.getText().isBlank() && !equipo_2.getText().isBlank() && !equipo_1.getText().isBlank() && !monto.getText().isBlank() && !fielCantidad.getText().isBlank()) {
                    if (CalendarioGeneral.FECHASELECCIONADA != null) {
                        try {
                            monto_evento = Double.parseDouble(monto.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "el monto tiene que ser un numero");
                            return;
                        }

                        try {
                            cantidad = Integer.parseInt(fielCantidad.getText());
                        } catch (NumberFormatException er) {
                            JOptionPane.showMessageDialog(null, "la cantidad tiene que ser un numero entero");
                            return;
                        }

                        if (cantidad >= 0 && monto_evento >= 0) {
                            if (cantidad <= 20000) {
                                if (seleccionado != null) {

                                    Eventos eve = new EventoDeportivo(id, titulo_evento.getText(), descripcion.getText(), CalendarioGeneral.FECHASELECCIONADA, monto_evento, cantidad, equipo_1.getText(), equipo_2.getText(), seleccionado, 0, 1, new ArrayList(), new ArrayList(), false);
                                    Login.MANEJOEVENTOS.AddEvent(eve, id);
                                    JOptionPane.showMessageDialog(null, "Se ha creado el evento");
                                    MenuEvent log = new MenuEvent();
                                    log.setVisible(true);
                                    CalendarioGeneral.FECHASELECCIONADA = null;

                                    if (Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() == 1) {
                                        UserAdmin us = (UserAdmin) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
                                        us.AgregarId(id);
                                    }
                                    if (Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() == 2) {
                                        UserContenido us = (UserContenido) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
                                        us.AgregarId(id);
                                    }
                                    dispose();
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
        fondo.add(fielCantidad);
        fondo.add(cant_person);

    }

    private int obtenerNuevoId() {
        int nuevoId = 0;
        while (Login.MANEJOEVENTOS.buscar(nuevoId) != null) {
            nuevoId++;
        }
        return nuevoId;
    }

    public void tipo_event() {
        JFrame frame = new JFrame("¿Cambio de evento?");
        JPanel panel = new JPanel();

        JRadioButton deportivo = new JRadioButton("Musical");
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
            CrearMusical crea = new CrearMusical();
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
}
