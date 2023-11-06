package Evento;

import java.util.Date;

    public class Eventos {
    private int codigoUnico;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private double montoRenta;
    private int cantidad_personas;
    private double multa;
    private int tipoEvento;
    private boolean borrado;

    public Eventos(int codigoUnico, String titulo, String descripcion, Date fecha, double montoRenta, int cantidad_personas, double multa, int tipoEvento, boolean borrado) {
        this.codigoUnico = codigoUnico;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.montoRenta = montoRenta;
        this.cantidad_personas = cantidad_personas;
        this.multa = 0;
        this.tipoEvento = tipoEvento;
        this.borrado = borrado;
    }

    public int getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(int tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    

    public double getMulta() {
        return multa;
    }

    public boolean Cancelado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
   
    public void setMulta(double multa) {
        this.multa = multa;
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(int codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoRenta() {
        return montoRenta;
    }

    public void setMontoRenta(double montoRenta) {
        this.montoRenta = montoRenta;
    }

    public int getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(int cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }
   
    @Override
    public String toString() {
    return "Codigo: " + getCodigoUnico() + 
           "\nTitulo: " + getTitulo() + 
           "\nMonto: " + getMontoRenta() + 
           "\nPersonas: " + getCantidad_personas() + 
           "\nFecha: " + getFecha().toString() + 
           "\nDescripcion: " + getDescripcion();
}
    
}

