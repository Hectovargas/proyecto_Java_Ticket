package Interfaces_Eventos;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarioGeneral extends JFrame implements ActionListener {
    private JCalendar calendario;
    private JComboBox<String> horasComboBox;
    private JComboBox<String> minutosComboBox;
    private JButton confirmarButton;
    public static Date FECHASELECCIONADA; 

    public CalendarioGeneral(){
        setLayout(null);
        calendario = new JCalendar();
        calendario.setBounds(10, 10, 300, 200);
        Calendar calendarActual = Calendar.getInstance();
        Date fechaActual = calendarActual.getTime();

        

        add(calendario);
        String[] horas = new String[24];
        String[] minutos = new String[60];

        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d", i);
        }
        for (int i = 0; i < 60; i++) {
            minutos[i] = String.format("%02d", i);
        }

        horasComboBox = new JComboBox<>(horas);

        JLabel label = new JLabel("Hor");
        label.setBounds(15, 200, 50, 35);
        add(label);
        JLabel label2 = new JLabel("Min");
        label2.setBounds(75, 200, 50, 35);
        add(label2);
        horasComboBox.setBounds(10, 230, 50, 30);
        add(horasComboBox);
        minutosComboBox = new JComboBox<>(minutos);
        minutosComboBox.setBounds(70, 230, 50, 30);
        add(minutosComboBox);
        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBounds(10, 270, 100, 30);
        confirmarButton.addActionListener(this);
        add(confirmarButton);
        setSize(330, 340);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            String hora = horasComboBox.getSelectedItem() + ":" + minutosComboBox.getSelectedItem();

            int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = calendario.getCalendar().get(Calendar.MONTH);
            int año = calendario.getCalendar().get(Calendar.YEAR);

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String fechaString = String.format("%02d/%02d/%04d %s", dia, mes + 1, año, hora);

            try {
                FECHASELECCIONADA = formato.parse(fechaString);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Fecha seleccionada: " + fechaString);
            dispose();
        }
    }

}