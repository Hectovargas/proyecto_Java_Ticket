package Interfaces_Eventos;

import Interfaces_Generales.Login;
import Interfaces_Generales.MenuPrincipal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import manejo_de_usuarios.UserManager;
import static Interfaces_Generales.Login.FUNCIONES;

public class MenuEvent extends JFrame{

    public static int TIPOEDIT;
    public static int CODE;
    private int Ancho = 600, Alto = 600, ancho_boton = 150, alto_boton = 50;

    public MenuEvent() {
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
        elementos(fondo,menu);
        menu.add(fondo);
        add(menu);
    }

    private void elementos(JLabel fondo,JPanel panel) {
        JLabel Titulo = new JLabel("Menu De Eventos");
        Titulo.setFont(new Font("Arial", 0, 30));
        Titulo.setBounds(185, 30, 250, 50);
        Titulo.setForeground(Color.WHITE);
        agregarIcono("agregarEvento.PNG", 225 - 54, 120);
        agregarIcono("editEvento.PNG", 225 - 54, 210);
        agregarIcono("borrarEvento.PNG", 225 -54, 300);
        agregarIcono("ver.PNG", 225 - 54, 390);
        Crear(fondo,panel);
        Editar(fondo);
        Borrar(fondo);
        Regresar(fondo,this);
        Ver(fondo);
        fondo.add(Titulo);
    }

    public void Crear(JLabel fondo,JPanel panel) {
        JButton crea = new JButton("Crear evento");
        if (FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() > 2) {
            crea.setEnabled(false);
        } else {
            crea.setEnabled(true);
        }
        crea.setFont(new Font("Arial", 0, 18));
        crea.setForeground(Color.WHITE);
        crea.setBounds(225, 120, ancho_boton, alto_boton);
        crea.setBackground(Color.GREEN);
        if (FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() <= 2) {
            crea.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ElegirEvento();
                }
            });
        }
        fondo.add(crea);
    }

    public void Editar(JLabel fondo) {
        JButton edit = new JButton("Editar Evento");
        if (FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() > 2) {
            edit.setEnabled(false);
        } else {
            edit.setEnabled(true);
        }
        edit.setFont(new Font("Arial", 0, 18));
        edit.setForeground(Color.WHITE);
        edit.setBounds(225, 210, ancho_boton, alto_boton);
        edit.setBackground(new Color(204, 0, 204));
        if (FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() <= 2) {
            edit.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    TIPOEDIT = tipo_edit();
                    if (TIPOEDIT != 0) {
                        EditEvent edit = new EditEvent();
                        edit.setVisible(true);
                        dispose();
                    }
                }
            });
        }
        fondo.add(edit);
    }

    public void Borrar(JLabel fondo) {

        JButton borra = new JButton("Eliminar evento");
        borra.setFont(new Font("Arial", 0, 17));
        borra.setForeground(Color.WHITE);
        borra.setBounds(225, 300, ancho_boton, alto_boton);
        borra.setBackground(new Color(204, 0, 51));
        borra.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                BorrarEvent borr = new BorrarEvent();
                borr.setVisible(true);
                dispose();
            }
        });
        fondo.add(borra);
    }

    public void Ver(JLabel fondo) {
        JButton ver = new JButton("Ver evento");
        ver.setFont(new Font("Arial", 0, 19));
        ver.setForeground(Color.WHITE);
        ver.setBounds(225, 390, ancho_boton, alto_boton);
        ver.setBackground(new Color(255, 153, 0));
        ver.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                VerEvento ver = new VerEvento();
                ver.setVisible(true);
                dispose();
            }
        });
        fondo.add(ver);
    }

    public void Regresar(JLabel fondo,JFrame frame) {
        ImageIcon imagen = new ImageIcon("exit_icon.PNG");
        ImageIcon imagen_cambiada = new ImageIcon(imagen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton Regreso = new JButton(imagen_cambiada);
        Regreso.setBounds(275, 500, 50, 50);
        Regreso.setBackground(new Color(255, 0, 0));
        fondo.add(Regreso);
        Regreso.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MenuPrincipal user = new MenuPrincipal();
                user.setVisible(rootPaneCheckingEnabled);
                dispose();
            }
        });

    }

    private void ElegirEvento() {
        JFrame VentanaEmergente = new JFrame("Tipo de Evento");
        JPanel paneles = new JPanel();

        JRadioButton deportivo = new JRadioButton("Deportivo");
        JRadioButton religioso = new JRadioButton("Religioso");
        JRadioButton musical = new JRadioButton("Musical");

        ButtonGroup group = new ButtonGroup();
        group.add(deportivo);
        group.add(religioso);
        group.add(musical);

        paneles.add(deportivo);
        paneles.add(religioso);
        paneles.add(musical);

        VentanaEmergente.add(paneles);
        VentanaEmergente.setSize(300, 100);
        VentanaEmergente.setLocationRelativeTo(null);
        VentanaEmergente.setVisible(true);

        // Agregar ActionListener a cada botón
        deportivo.addActionListener((ActionEvent e) -> {
            CrearDep crea = new CrearDep();
            crea.setVisible(true);
            VentanaEmergente.dispose();
            dispose();
        });

        religioso.addActionListener((ActionEvent e) -> {
            CrearRel crea = new CrearRel();
            crea.setVisible(true);
            VentanaEmergente.dispose();
            dispose();
        });

        musical.addActionListener((ActionEvent e) -> {
            CrearMusical crea = new CrearMusical();
            crea.setVisible(true);
            VentanaEmergente.dispose();
            dispose();
        });
    }

    private int tipo_edit() {
        String input = JOptionPane.showInputDialog("Por favor, ingresa el numero del evento a editar");
        try {
            if (input != null) {
                int numero = Integer.parseInt(input);
                if (Login.MANEJOEVENTOS.buscar(numero) != null && !Login.MANEJOEVENTOS.buscar(numero).Cancelado()) {
                    int eleccion = Login.MANEJOEVENTOS.buscar(numero).getTipoEvento();
                    CODE = numero;
                    switch (eleccion) {
                        case 1:
                            return 1;
                        case 2:
                            return 2;
                        case 3:
                            return 3;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No existe ningun evento con ese codigo ");
                    return 0;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No ingresaste ningún número.");
                return 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ingresa un número válido");
            return 0;
        }
        return 0;
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