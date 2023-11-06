package manejo_de_usuarios;

import Enumeraciones.*;
import Evento.*;
import Interfaces_Generales.Login;
import java.util.*;
import javax.swing.JOptionPane;
import objeto_usuario.*;

public class EventManager {

    private static ArrayList<Eventos> Events = new ArrayList<>();
    private Calendar cal = Calendar.getInstance();

    public Eventos buscar(int code) {
        for (Eventos us : Events) {
            if (us != null && us.getCodigoUnico() == code) {
                return us;
            }
        }
        return null;
    }

    public boolean borrarEvent(int code) {
        Eventos evento = buscar(code);

        if (evento != null && !evento.Cancelado()) {
            Calendar calendarActual = Calendar.getInstance();
            Calendar fechaEvento = Calendar.getInstance();
            fechaEvento.setTime(evento.getFecha());
            UserAdmin userAdmin = null;
            UserContenido Uscontenido = null;

            int tipoUsuario = Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario();

            if (tipoUsuario == 1) {
                userAdmin = (UserAdmin) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
            } else if (tipoUsuario == 2) {
                Uscontenido = (UserContenido) Login.FUNCIONES.buscar(UserManager.USERACTUAL);
            }
           
            if (Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() == 1 && userAdmin.revisar(code) || Login.FUNCIONES.buscar(UserManager.USERACTUAL).getTipo_usuario() == 2 && Uscontenido.revisar(code)) {
                if (calendarActual.before(fechaEvento)) {
                     long diferenciaDias = ((fechaEvento.getTimeInMillis() - calendarActual.getTimeInMillis()) / (24 * 60 * 60 * 1000));
                    if (diferenciaDias <= 1 && buscar(code).getTipoEvento() != 3) {
                        double montoIndemnizacion = buscar(code).getMontoRenta() * 0.5;
                        buscar(code).setMulta(buscar(code).getMulta() + montoIndemnizacion);
                        JOptionPane.showMessageDialog(null, "la multa es de: " + montoIndemnizacion + " $");
                    }
                    buscar(code).setBorrado(true);
                    JOptionPane.showMessageDialog(null, "Evento borrado correctamente");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes borrar un evento cuya fecha ha pasado");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Solo el creador del evento puedo borrar este evento");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "El evento con código " + code + " no existe o ya fue borrado.");
            return false;
        }
    }

    public void AddEvent(Eventos eve, int id) {
        if (buscar(id) == null) {
            Events.add(eve);
        }

    }

    public void editar_religioso(int id, int PersonasConvertidas, String titulo, String descripcion, Date fecha, double montoRenta, int cantidad_personas) {
            Eventos evento = buscar(id);
            EventoReligioso eve= (EventoReligioso) evento;
            eve.setPersonasConvertidas(PersonasConvertidas);
            buscar(id).setCantidad_personas(cantidad_personas);
            buscar(id).setDescripcion(descripcion);
            buscar(id).setFecha(fecha);
            buscar(id).setMontoRenta(montoRenta);
            buscar(id).setTitulo(titulo);
        
    }

    
    public void editar_deportivo(int id, String equipo1, String equipo2, Tipodep deporte, ArrayList integrante_1, ArrayList integrante2, String titulo, String descripcion, Date fecha, double montoRenta, int cantidad_personas) {
        Eventos evento = buscar(id);
            EventoDeportivo eve= (EventoDeportivo) evento;
            eve.setNombreEquipo1(equipo1);
            eve.setNombreEquipo2(equipo2);
            eve.setTipoDeporte(deporte);
            eve.AgregarNombre1(integrante_1);
            eve.AgregarNombre2(integrante2);
            evento.setCantidad_personas(cantidad_personas);
            evento.setDescripcion(descripcion);
            evento.setFecha(fecha);
            evento.setMontoRenta(montoRenta);
            evento.setTitulo(titulo);
        
    }

    public void editar_musical(int id, ArrayList integrantes, TipoMus musica, String titulo, String descripcion, Date fecha, double montoRenta, int cantidad_personas) {
            Eventos evento = buscar(id);
            EventoMusical eve= (EventoMusical) evento;
            eve.AgregarNombre1(integrantes);
            eve.setTipoMusica(musica);
            buscar(id).setCantidad_personas(cantidad_personas);
            buscar(id).setDescripcion(descripcion);
            buscar(id).setFecha(fecha);
            buscar(id).setMontoRenta(montoRenta);
            buscar(id).setTitulo(titulo);
            
    }

    
    public String datos(int code) {
        if (buscar(code) != null) {
            String texto_principal="";
            switch (buscar(code).getTipoEvento()) {
                case 1:
                    EventoDeportivo eve = (EventoDeportivo) buscar(code);
                    texto_principal=eve.toString();
                    break;

                case 2:
                    EventoMusical evm = (EventoMusical) buscar(code);
                    texto_principal = evm.toString();
                    break;

                case 3:
                    EventoReligioso evr = (EventoReligioso) buscar(code);
                    texto_principal = evr.toString();
                    break;
            }
            return texto_principal;
        }
        return null;
    }

    private String listarEventoscancelados(int i, String Detalles, int contador_deportivo, int contador_musical, int contador_religioso, double multa, String ListaGeneral) {
    if (Events != null) {
    if (i < Events.size()) {
        Eventos eventoActual = buscar(i);

        if (eventoActual.Cancelado()) {
            multa += eventoActual.getMulta();
            String tipo = "";
            switch (eventoActual.getTipoEvento()) {
                case 1:
                    tipo = "deportivo";
                    contador_deportivo++;
                    break;
                case 2:
                    tipo = "Musical";
                    contador_musical++;
                    break;
                case 3:
                    tipo = "Religioso";
                    contador_religioso++;
                    break;
            }

            Detalles += "---------------\nCodigo: " + eventoActual.getCodigoUnico() +
                    "\nTipo de evento: " + tipo +
                    "\nTitulo: " + eventoActual.getTitulo() +
                    "\nfecha: " + eventoActual.getFecha().toString() +
                    "\nMulta: " + eventoActual.getMulta() + "\n";
        }

        return listarEventoscancelados(i + 1, Detalles, contador_deportivo, contador_musical, contador_religioso, multa, ListaGeneral);
    }
    ListaGeneral = "Eventos deportivos cancelados: " + contador_deportivo +
            "\nEventos Musicales cancelados: " + contador_musical +
            "\nEventos religiosos cancelados: " + contador_religioso +
            "\nMulta Total: " + multa + "\n";

    return ListaGeneral + Detalles;
    }else{
    return "No hay eventos cancelados";
    }
    }

    public String listarEventoscancelados() {
        return listarEventoscancelados(0, "", 0, 0, 0, 0, "");
    }

    private String listarEventosfuturos(int i, String acumulador, int contador_deportivo, int contador_musical, int contador_religioso, double monto_dep,double monto_mus,double monto_rel, String stats) {
        if (Events != null) {
            if (i < Events.size()) {
                if (buscar(i).getFecha().after(cal.getTime())&&!buscar(i).Cancelado()) {
                    String tipo = "";
                    if (buscar(i).getTipoEvento() == 1) {
                        tipo = "deportivo";
                        contador_deportivo++;
                        monto_dep+=buscar(i).getMontoRenta();
                    }
                    if (buscar(i).getTipoEvento() == 2) {
                        tipo = "Musical";
                        contador_musical++;
                        monto_mus+=buscar(i).getMontoRenta();
                    }
                    if (buscar(i).getTipoEvento() == 3) {
                        tipo = "Religioso";
                        contador_religioso++;
                        monto_rel+=buscar(i).getMontoRenta();
                    }

                    return listarEventosfuturos(i + 1, acumulador + "---------------\nCodigo: " + buscar(i).getCodigoUnico() + "\n" + "Tipo de evento: " + tipo + "\n" + "Titulo: " + buscar(i).getTitulo() + "\n" + "fecha: " + buscar(i).getFecha().toString() + "\nmonto: " + buscar(i).getMontoRenta()+ "\n", contador_deportivo, contador_musical, contador_religioso, monto_dep,monto_mus,monto_rel, "Eventos deportivos Futuros: " + contador_deportivo + "\nEventos Musicales Futuros: " + contador_musical + "\nEventos religiosos Futuros: " + contador_religioso + "\nmonto de eventos deportivos: " + monto_dep + "\nmonto de eventos Musicales: " + monto_mus + "\nmonto de eventos religiosos: " + monto_rel+"\n" );
                } else {
                    return listarEventosfuturos(i + 1, acumulador, contador_deportivo, contador_musical, contador_religioso, monto_dep, monto_mus, monto_rel, stats);
                }
            }
            return stats + acumulador;
        } else {
            return "No hay eventos a futuro";
        }
    }
    public String listarEventosfuturos() {
        return listarEventosfuturos(0, "", 0, 0, 0, 0,0,0, "");
    }
private String listarEventosPasados(int i, String Detalles, int contador_deportivo, int contador_musical, int contador_religioso, double monto_dep,double monto_mus,double monto_rel, String ListaGeneral, List<Eventos> eventosPasados) {
    if (Events != null) {
        if (i < Events.size()) {
            if (buscar(i).getFecha().before(cal.getTime())&&!buscar(i).Cancelado()) {
                 String tipo = "";
                    if (buscar(i).getTipoEvento() == 1) {
                        tipo = "deportivo";
                        contador_deportivo++;
                        monto_dep+=buscar(i).getMontoRenta();
                    }
                    if (buscar(i).getTipoEvento() == 2) {
                        tipo = "Musical";
                        contador_musical++;
                        monto_mus+=buscar(i).getMontoRenta();
                    }
                    if (buscar(i).getTipoEvento() == 3) {
                        tipo = "Religioso";
                        contador_religioso++;
                        monto_rel+=buscar(i).getMontoRenta();
                    }
                return listarEventosPasados(i + 1, Detalles + "---------------\nCodigo: " + eventosPasados.get(i).getCodigoUnico() + "\n" + "Tipo de evento: " + tipo + "\n" + "Titulo: " + eventosPasados.get(i).getTitulo() + "\n" + "fecha: " + eventosPasados.get(i).getFecha().toString() + "\nmonto: " + eventosPasados.get(i).getMontoRenta()+ "\n", contador_deportivo, contador_musical, contador_religioso, monto_dep,monto_mus,monto_rel, "Eventos deportivos Futuros: " + contador_deportivo + "\nEventos Musicales Futuros: " + contador_musical + "\nEventos religiosos Futuros: " + contador_religioso + "\nmonto de eventos deportivos: " + monto_dep + "\nmonto de eventos Musicales: " + monto_mus + "\nmonto de eventos religiosos: " + monto_rel+"\n", eventosPasados);
            } else {
                return listarEventosPasados(i + 1, Detalles, contador_deportivo, contador_musical, contador_religioso, monto_dep, monto_mus, monto_rel, ListaGeneral, eventosPasados);
            }
        }
        return ListaGeneral + Detalles;
    } else {
        return "No hay eventos -__-";
    }
}
    public String listarEventosPasados() {
        List<Eventos> eventosPasados=new ArrayList<>();
        for(int i=0; i<Events.size();i++){
            eventosPasados.add(buscar(i));
            eventosPasados.sort(Comparator.comparing(Eventos::getFecha).reversed());
        }
        return listarEventosPasados(0, "", 0, 0, 0, 0,0,0, "",eventosPasados);
    }
        private String Rango_de_eventos(int i, String Detalles, int contador_deportivo, int contador_musical, int contador_religioso, double monto_dep,double monto_mus,double monto_rel, String ListaGeneral,Date inicio,Date fin,double multa_dep,double multa_mus,double multa_rel,double total) {
        if (Events != null) {
            if (i < Events.size()) {
                if (buscar(i).getFecha().after(inicio)&&buscar(i).getFecha().before(fin)) {
                    String tipo = "";
                    if (buscar(i).getTipoEvento() == 1) {
                        tipo = "deportivo";
                        contador_deportivo++;
                        monto_dep+=buscar(i).getMontoRenta();
                        multa_dep+=buscar(i).getMulta();
                    }
                    if (buscar(i).getTipoEvento() == 2) {
                        tipo = "Musical";
                        contador_musical++;
                        monto_mus+=buscar(i).getMontoRenta();
                        multa_mus+=buscar(i).getMulta();
                    }
                    if (buscar(i).getTipoEvento() == 3) {
                        tipo = "Religioso";
                        contador_religioso++;
                        monto_rel+=buscar(i).getMontoRenta();
                        multa_rel+=buscar(i).getMulta();
                    
                    }
                    total=monto_dep+multa_dep+monto_mus+multa_mus+monto_rel+multa_rel;

                    return Rango_de_eventos(i + 1, Detalles + "---------------\nCodigo: " + buscar(i).getCodigoUnico() + "\n" + "Tipo de evento: " + tipo + "\n" + "Titulo: " + buscar(i).getTitulo() + "\n" + "fecha: " + buscar(i).getFecha().toString() + "\nmonto: " + buscar(i).getMontoRenta()+ "\n", contador_deportivo, contador_musical, contador_religioso, monto_dep,monto_mus,monto_rel, "#Eventos deportivos: " + contador_deportivo + "\n#Eventos Musicales: " + contador_musical + "\n#Eventos religiosos: " + contador_religioso + "\nmonto de eventos deportivos: " + monto_dep+"\nmulta de eventos deportivos: " + multa_dep+ "\nmonto de eventos Musicales: " + monto_mus +"\nmulta de eventos musicales: " + multa_mus+"\nmonto de eventos religiosos: " + monto_rel+"\nmulta de eventos religiosos: " + multa_dep+"\nIngreso Generado desde el "+inicio.toString()+" Hasta el "+fin.toString()+"\nes de: "+total+"\n",inicio, fin,
                            multa_dep, multa_mus, multa_rel,total);
                } else {
                    return Rango_de_eventos(i + 1, Detalles, contador_deportivo, contador_musical, contador_religioso, monto_dep, monto_mus, monto_rel, ListaGeneral,inicio, fin,multa_dep, multa_mus, multa_rel,total);
                }
            }
            System.out.println(Detalles);
            return ListaGeneral + Detalles;
        } else {
            return "No hay eventos -__-";
        }
    }

        public String Rango_de_eventos(Date inicio,Date fin) {
        return Rango_de_eventos(0, "", 0, 0, 0, 0,0,0, "",inicio, fin,0, 0, 0,0);
    }
}
