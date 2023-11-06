package Evento;

import Enumeraciones.Tipodep;
import java.util.ArrayList;
import java.util.Date;

public class EventoDeportivo extends Eventos {
    private String nombreEquipo1;
    private String nombreEquipo2;
    private Tipodep tipoDeporte;
    private ArrayList Integrantes_1 = new ArrayList();
    private ArrayList Integrantes_2 = new ArrayList();
   

    public EventoDeportivo(int codigoUnico, String titulo, String descripcion, Date fecha, double montoRenta,int cant,
                           String nombreEquipo1, String nombreEquipo2, Tipodep tipoDeporte,double multa,int tipoEvento,ArrayList arreglo_1,ArrayList arreglo_2,boolean borrado) {
        super(codigoUnico, titulo, descripcion, fecha, montoRenta,cant,multa,tipoEvento, borrado);
        this.nombreEquipo1 = nombreEquipo1;
        this.nombreEquipo2 = nombreEquipo2;
        this.tipoDeporte = tipoDeporte;
        this.Integrantes_1=arreglo_1;
        this.Integrantes_2=arreglo_2;
    }

    public void AgregarNombre1(ArrayList Arreglo) {
        Integrantes_1.clear();
        Integrantes_1.addAll(Arreglo);
    }
     public void eliminarArreglo1() {
        Integrantes_1.clear();
    }
      public void EliminarArreglo2() {
        Integrantes_1.clear();
    }
    public void AgregarNombre2(ArrayList Arreglo) {
        Integrantes_2.clear();
        Integrantes_2.addAll(Arreglo);
    }
    public ArrayList getArrayList(){
        return Integrantes_1;
    }
    public ArrayList getArrayList2(){
        return Integrantes_2;
    }

    public String getNombreEquipo1() {
        return nombreEquipo1;
    }

    public void setNombreEquipo1(String nombreEquipo1) {
        this.nombreEquipo1 = nombreEquipo1;
    }

    public String getNombreEquipo2() {
        return nombreEquipo2;
    }

    public void setNombreEquipo2(String nombreEquipo2) {
        this.nombreEquipo2 = nombreEquipo2;
    }

    public Tipodep getTipoDeporte() {
        return tipoDeporte;
    }

    public void setTipoDeporte(Tipodep tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nTipo de evento: Evento Deportivo" + "\nTipo de deporte: " +getTipoDeporte() + "\nNombre del equipo 1: " + getNombreEquipo1() + "\nIntegrantes: " + getArrayList() + "\nNombre del equipo 2: " + getNombreEquipo2() + "\nIntegrantes: " + getArrayList2();
    }

}
