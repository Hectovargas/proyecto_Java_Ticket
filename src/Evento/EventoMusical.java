package Evento;

import Enumeraciones.TipoMus;
import java.util.ArrayList;
import java.util.Date;

public class EventoMusical extends Eventos {
    private TipoMus tipoMusica;
    private ArrayList Integrantes_1 = new ArrayList();

    public EventoMusical(int codigoUnico, String titulo, String descripcion, Date fecha, double montoRenta,int cant,
                         TipoMus tipoMusica,double multa,int tipoEvento,ArrayList arreglo1,boolean borrado) {
        super(codigoUnico, titulo, descripcion, fecha, montoRenta,cant,multa,tipoEvento,borrado);
        this.tipoMusica = tipoMusica;
        this.Integrantes_1=arreglo1;
    }
   public ArrayList getArrayList(){
        return Integrantes_1;
    }
    public void eliminarArreglo() {
        Integrantes_1.clear();
    }
    
   public void AgregarNombre1(ArrayList Arreglo) {
        Integrantes_1.clear();
        Integrantes_1.addAll(Arreglo);
    }
    public TipoMus getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(TipoMus tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    @Override
    public String toString() {
        return super.toString()+"\nTipo de evento: Evento Musical" + "\nTipo de musica: " + getTipoMusica() + "\nintegrantes: " + getArrayList();
    }
  
}