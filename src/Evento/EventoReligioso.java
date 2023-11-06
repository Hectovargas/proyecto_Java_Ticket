package Evento;

import java.util.Date;

public class EventoReligioso extends Eventos {
    private int personasConvertidas;

    public EventoReligioso(int codigoUnico, String titulo, String descripcion, Date fecha, double montoRenta,int cant,
                           int personasConvertidas,double multa,int tipoEvento,boolean borrado) {
        super(codigoUnico, titulo, descripcion, fecha, montoRenta,cant,multa,tipoEvento,borrado);
        this.personasConvertidas = personasConvertidas;
    }

    public int getPersonasConvertidas() {
        return personasConvertidas;
    }

    public void setPersonasConvertidas(int personasConvertidas) {
        this.personasConvertidas = personasConvertidas;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nTipo de evento: Evento Religioso"  + "\nPersonas convertidad: " + getPersonasConvertidas(); 
    }
    
}
