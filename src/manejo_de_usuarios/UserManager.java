package manejo_de_usuarios;

import Interfaces_Generales.Login;
import java.util.ArrayList;
import objeto_usuario.*;

public class UserManager {

    public static String USERACTUAL = "";
    public static ArrayList<Usuarios> Users;
    public static String usuario_Edit = "";

    public UserManager() {
        Users = new ArrayList<>();
        añadirAdmin();
    }

    public void añadir(String nombre_usuario, Usuarios us) {
        if (verificar(nombre_usuario)) {
            Users.add(us);
        }
    }

    public boolean verificar_borrado(String Username) {
        if (buscar(Username) != null && buscar(Username).getTipo_usuario() != 0) {
            return true;
        }
        return false;
    }

    public void borrar(String Username) {
        if (verificar_borrado(Username)) {
            Users.remove(buscar(Username));
        }

    }

    public final void añadirAdmin() {
        UserAdmin us = new UserAdmin("admin", "supersecreto", 0, 0, "");
        Users.add(0, us);
    }

    public void editar(String name, String user, String password, int edad, int tipo) {
        if (buscar(usuario_Edit) != null) {
            buscar(usuario_Edit).setEdad(edad);
            buscar(usuario_Edit).setTipo_usuario(tipo);
            buscar(usuario_Edit).setNombre_real(name);
            buscar(usuario_Edit).setContraseña(password);
            buscar(usuario_Edit).setNombre_usuario(user);
        }
    }

    public Usuarios buscar(String nombre_usuario) {
        for (Usuarios us : Users) {
            if (us != null && us.getNombre_usuario().equals(nombre_usuario)) {
                return us;
            }
        }
        return null;
    }

    public String datos() {
        String Texto = "nombre: " + buscar(USERACTUAL).getNombre_real()
                + "\nNombre de usuario: " + buscar(USERACTUAL).getNombre_usuario()
                + "\nContraseña: " + buscar(USERACTUAL).getContraseña()
                + "\nEdad: " + buscar(USERACTUAL).getEdad();
        switch (buscar(USERACTUAL).getTipo_usuario()) {
            case 1:
                Texto += "\nTipo de usuario: Administrador\n";
                break;
            case 2:
                Texto += "\nTipo de usuario: Contenido\n";
                break;
            case 3:
                Texto += "\nTipo de usuario: Limitado\n";
                break;
        }

        if (buscar(USERACTUAL) instanceof UserContenido) {
            UserContenido us = (UserContenido) buscar(USERACTUAL);
            Texto += verEventos(USERACTUAL, us.getIds());
        }

        if (buscar(USERACTUAL) instanceof UserAdmin) {
            UserAdmin us = (UserAdmin) buscar(USERACTUAL);
            Texto += verEventos(USERACTUAL, us.getIds());
        }
        if(buscar(USERACTUAL) instanceof Limitado){
            Limitado us = (Limitado) buscar(USERACTUAL);
            Texto += verEventos(USERACTUAL, us.getIds());
        }

        return Texto;
    }

    private String verEventos(int i, ArrayList array, String acumulador) {

        if (i < array.size()) {
            if (Login.MANEJOEVENTOS.buscar((int) array.get(i)) != null) {
                String tipo = "";
                String Estado;
                if (Login.MANEJOEVENTOS.buscar((int) array.get(i)).getTipoEvento() == 1) {
                    tipo = "deportivo";
                }
                if (Login.MANEJOEVENTOS.buscar((int) array.get(i)).getTipoEvento() == 2) {
                    tipo = "Musical";
                }
                if (Login.MANEJOEVENTOS.buscar((int) array.get(i)).getTipoEvento() == 3) {
                    tipo = "Religioso";
                }
                if (Login.MANEJOEVENTOS.buscar((int) array.get(i)).Cancelado()) {
                    Estado = "cancelado";
                } else {
                    Estado = "Activo";
                }
                return verEventos(i + 1, array, acumulador + "---------------\nCodigo: " + Login.MANEJOEVENTOS.buscar((int) array.get(i)).getCodigoUnico() + "\n" + "Tipo de evento: " + tipo + "\n" + "Titulo: " + Login.MANEJOEVENTOS.buscar((int) array.get(i)).getTitulo() + "\n" + "Estado: " + Estado + "\nMonto: " + Login.MANEJOEVENTOS.buscar((int) array.get(i)).getMontoRenta() + "\n");
            } else {
                return "";
            }
        }
        System.out.println(acumulador);
        return acumulador;
    }

    public String verEventos(String name, ArrayList array) {
        return verEventos(0, array, "");
    }

    public boolean verificar(String nombre_usuario) {
        if (buscar(nombre_usuario) != null) {
            return false;
        }

        return true;
    }
}
